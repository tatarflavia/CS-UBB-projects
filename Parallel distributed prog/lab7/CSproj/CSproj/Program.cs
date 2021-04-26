using MPI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace CSproj
{
    class Program
    {
        public static Polynomial ComputeFinalResult(Polynomial[] results)
        {
            Polynomial result = new Polynomial(results[0].Degree);

            //add all results into one pol
            for (int i = 0; i < result.size; i++)
                for (int j = 0; j < results.Length; j++)
                    result.Coefficients[i] += results[j].Coefficients[i];

            return result;
        }

        public static void MPIMultiplicationMain(Polynomial polynomial1, Polynomial polynomial2)
        {
            DateTime start = DateTime.Now;

            //we start by dividing the result coeffs in intervals, every interval given to the communicator
            Console.WriteLine("starting MPI O(n^2) method...");
            int n = Communicator.world.Size;

            int begin = 0;
            int end = 0;
            int length = polynomial1.size / (n - 1);
            Console.WriteLine("starting diving by intervals the result coeffs according to how many processes we have...");

            //one by one we send the intervals for which we must compute the result coefficients
            for (int i = 1; i < n; i++)
            {
                begin = end;
                end = end + length;
                if (i == n - 1)
                    end = polynomial1.size;

                //send to process i the 2 pols and the coef for the interval to be computed
                Communicator.world.Send(polynomial1, i, 0);
                Communicator.world.Send(polynomial2, i, 0);
                Communicator.world.Send(begin, i, 0);
                Communicator.world.Send(end, i, 0);
            }

            //pol array for results
            Polynomial[] results = new Polynomial[n - 1];

            Console.WriteLine("starting receiving the computed coefficients...");
            //start to receive all the computed polynomials(a part of the result) and then add them up
            for (int i = 1; i < n; i++) {
                Polynomial pol = Communicator.world.Receive<Polynomial>(i, 0);
                Console.WriteLine("received: " + pol);
                results[i - 1] = pol;
            }
                

            Polynomial result = ComputeFinalResult(results);

            double time = (DateTime.Now - start).Milliseconds;
            Console.WriteLine("MPI O(n^2) method finished with result: " + result.ToString() + " and it took: " + time.ToString() + " millisec");
        }

        public static void MPIMultiplicationChild()
        {
            //here we continue working for MPI O(n^2) method

            //start receiving what we sent before: pols and interval to be taken from them to be put into the result
            Console.WriteLine("starting receiving the coefficients to be computed...");
            Polynomial polynomial1 = Communicator.world.Receive<Polynomial>(0, 0);
            Polynomial polynomial2 = Communicator.world.Receive<Polynomial>(0, 0);

            int begin = Communicator.world.Receive<int>(0, 0);
            int end = Communicator.world.Receive<int>(0, 0);
            Console.WriteLine("received the interval to be computed: ["+ begin+","+end+"]\n");

            //start computing a result pol that is actually part of the final result, it only has coeffs diff from 0 on the sent interval
            Polynomial result = PolynomialOperations.MPIMultiply(polynomial1, polynomial2, begin, end);

            Communicator.world.Send(result, 0, 0);
        }
        public static void MPIKaratsubaMain(Polynomial polynomial1, Polynomial polynomial2)
        {

            //we start by dividing between processes
            Console.WriteLine("starting MPI Karatsuba method...");
            DateTime start = DateTime.Now;


            //prepare the result pol
            Polynomial result = new Polynomial(polynomial1.Degree * 2);

            //verify the number of processors, if only 1 we call the async method, else we distribute between processes
            if (Communicator.world.Size == 1)
            {
                //this is the simple multiplication done by only 1 process
                Console.WriteLine("starting calculating since we have only 1 process...");
                result = PolynomialOperations.AsynchronousKaratsubaMultiply(polynomial1, polynomial2);
            }
            else
            {

                //else start distributing by sending intervals according to the number of processes
                Console.WriteLine("starting distributing work between processes...");

                //send to process number 1(which will do the computations) the 2 polynomials 
                Communicator.world.Send<int>(0, 1, 0);
                Communicator.world.Send<int[]>(polynomial1.Coefficients, 1, 0);
                Communicator.world.Send<int[]>(polynomial2.Coefficients, 1, 0);
                //then continue dis
                if (Communicator.world.Size == 2)
                    //we only have 2 processes so we don't have to distribute anymore
                    Communicator.world.Send<int[]>(new int[0], 1, 0);
                else
                    //else we have more than 2 processes and => we send to process 1 an array with numbers of the other processes
                    Communicator.world.Send<int[]>(Enumerable.Range(2, Communicator.world.Size - 2).ToArray(), 1, 0);

                //here we receive from the communicator from process 1 the coefficients for the result polynomial at the end of the computations
                int[] coefs = Communicator.world.Receive<int[]>(1, 0);
                result.Coefficients = coefs;
            }

            double time = (DateTime.Now - start).Milliseconds;

            Console.WriteLine("MPI Karatsuba method finished with result: " + result.ToString() + " and it took: " + time.ToString() + " millisec");
        }

        public static void MPIKaratsubaChild()
        {
            //any other process besides the main one calls the function from operations to continue working

            PolynomialOperations.MPIKaratsubaMultiply();
        }

        static void Main(string[] args)
        {
            using (new MPI.Environment(ref args))
            {
                if (Communicator.world.Rank == 0)
                {
                    //main as in first process

                    //take the number of total processors
                    int totalProcessors = Communicator.world.Size - 1;

                    Console.WriteLine("Number of processes:" + Communicator.world.Size);

                    //init for the 2 polynomials
                    int firstLength = 7;
                    int secondLength = 7;
                    Polynomial polynomial1 = new Polynomial(firstLength);
                    polynomial1.GenerateRandom();
                    Thread.Sleep(500);
                    Polynomial polynomial2 = new Polynomial(secondLength);
                    polynomial2.GenerateRandom();

                    //if they don't have the same len => we make them to be 
                    if (firstLength > secondLength)
                        polynomial2 = polynomial2.AddZerosLeft(firstLength - secondLength);
                    else if (secondLength > firstLength)
                        polynomial1 = polynomial1.AddZerosLeft(secondLength - firstLength);

                    Console.WriteLine("First pol :" + polynomial1.ToString());
                    Console.WriteLine("\nSecond pol : "+ polynomial2.ToString());

                    //start computations

                    //start for O(n^2) method
                    MPIMultiplicationMain(polynomial1, polynomial2);
                    
                    //start for karatsuba method
                    MPIKaratsubaMain(polynomial1, polynomial2);
                }
                else
                {
                    // any other process

                    //continue the work for O(n^2) method
                    MPIMultiplicationChild();

                    //continue the work for Karatsuba method
                    MPIKaratsubaChild();
                }
            }
        }
    }
}
