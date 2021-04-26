using MPI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSproj
{
    public class PolynomialOperations
    {
        public static Polynomial MPIMultiply(Polynomial polynomial1, Polynomial polynomial2, int begin, int end)
        {
            //Simple O(n^2) polynomial multiplication with mpi distributivity

            // we get 2 polynomials and one interval of result coeffs to compute and then return the result poly
            Polynomial result;
            int maxDegree = Math.Max(polynomial1.Degree, polynomial2.Degree);
            result = new Polynomial(maxDegree * 2);

            //calculate coefficients for result polynomial from [start index till end index-1]
            for (int i = begin; i < end; i++)
                for (int j = 0; j < polynomial2.size; j++)
                    result.Coefficients[i + j] += polynomial1.Coefficients[i] * polynomial2.Coefficients[j];

            //return the computed polynomial
            return result;
        }

        public static Polynomial AsynchronousKaratsubaMultiply(Polynomial p1, Polynomial p2)
        {
            //async method for one process karatsuba multiplication of the 2 given polynomials
            Polynomial result = new Polynomial(p1.Degree + p2.Degree);
            //we call the recursive function which does all computations
            result.Coefficients = AsynchronousKaratsubaMultiplyRecursive(p1.Coefficients, p2.Coefficients);
            
            return result;
        }

        public static int[] AsynchronousKaratsubaMultiplyRecursive(int[] coefficients1, int[] coefficients2)
        {
            //we are given 2 sets of coefficients that we must multiply with karatsuba method

            int[] product = new int[2 * coefficients1.Length];

            //recursive base case where we can't half the polynomials anymore
            //Handle the base case where the polynomial has only one coefficient
            if (coefficients1.Length == 1)
            {
                product[0] = coefficients1[0] * coefficients2[0];
                return product;
            }

            int halfArraySize = coefficients1.Length / 2;

            //first we half those 2 polynomials
            int[] coefficients1Low = new int[halfArraySize];
            int[] coefficients1High = new int[halfArraySize];
            int[] coefficients2Low = new int[halfArraySize];
            int[] coefficients2High = new int[halfArraySize];

            int[] coefficients1LowHigh = new int[halfArraySize];
            int[] coefficients2LowHigh = new int[halfArraySize];

            //we prepare the formula for the multiplication
            //c0=low A * low B,     c1= high A * high B,      c2=(low A+ high A)*(low B+high B)-c0-c1

            //first prepare lowA, lowB, high A, high B, low A+ high A, low B+high B
            for (int halfSizeIndex = 0; halfSizeIndex < halfArraySize; halfSizeIndex++)
            {

                coefficients1Low[halfSizeIndex] = coefficients1[halfSizeIndex];
                coefficients1High[halfSizeIndex] = coefficients1[halfSizeIndex + halfArraySize];
                coefficients1LowHigh[halfSizeIndex] = coefficients1Low[halfSizeIndex] + coefficients1High[halfSizeIndex];

                coefficients2Low[halfSizeIndex] = coefficients2[halfSizeIndex];
                coefficients2High[halfSizeIndex] = coefficients2[halfSizeIndex + halfArraySize];
                coefficients2LowHigh[halfSizeIndex] = coefficients2Low[halfSizeIndex] + coefficients2High[halfSizeIndex];

            }

            //call this function to get the needed products: low A * low B, high A * high B, (low A+ high A)*(low B+high B)
            Task<int[]> t1 = Task<int[]>.Factory.StartNew(() =>
            {
                return AsynchronousKaratsubaMultiplyRecursive(coefficients1Low, coefficients2Low);
            });

            Task<int[]> t2 = Task<int[]>.Factory.StartNew(() =>
            {
                return AsynchronousKaratsubaMultiplyRecursive(coefficients1High, coefficients2High);
            });

            Task<int[]> t3 = Task<int[]>.Factory.StartNew(() =>
            {
                return AsynchronousKaratsubaMultiplyRecursive(coefficients1LowHigh, coefficients2LowHigh);
            });

            //get the results from the computations which are done
            int[] productLow = t1.Result;
            int[] productHigh = t2.Result;
            int[] productLowHigh = t3.Result;

            //do the sustraction for c2=(low A+ high A)*(low B+high B)-c0-c1 by substraction the needed c0 and c1 from the results above
            int[] productMiddle = new int[coefficients1.Length];
            for (int halfSizeIndex = 0; halfSizeIndex < coefficients1.Length; halfSizeIndex++)
                productMiddle[halfSizeIndex] = productLowHigh[halfSizeIndex] - productLow[halfSizeIndex] - productHigh[halfSizeIndex];

            //calc final result for multiplication by AXB=c1(shifted len*2) pos + c2(shifted len pos) + c0
            //only thing we need is the shift and the add of all
            for (int halfSizeIndex = 0, middleOffset = coefficients1.Length / 2; halfSizeIndex < coefficients1.Length; ++halfSizeIndex)
            {
                product[halfSizeIndex] += productLow[halfSizeIndex];
                product[halfSizeIndex + coefficients1.Length] += productHigh[halfSizeIndex];
                product[halfSizeIndex + middleOffset] += productMiddle[halfSizeIndex];
            }

            //result is ready so we return it
            return product;

        }

        public static void MPIKaratsubaMultiply()
        {
            //mpi karatsuba method for more than 1 process
            //before we sent to the communicator a 0 from process 0, the 2 poly, 
                    //and an array which is empty if only 2 processes in the prog or all the process numbers if we have more than 3
            
            //first get the receive destination
            int from = Communicator.world.Receive<int>(Communicator.anySource, 0);
            //then get the polynomials which we must multiply
            int[] coefficients1 = Communicator.world.Receive<int[]>(Communicator.anySource, 0);
            int[] coefficients2 = Communicator.world.Receive<int[]>(Communicator.anySource, 0);
            //get the array to know to what process we should send further
            int[] sendTo = Communicator.world.Receive<int[]>(Communicator.anySource, 0);

            //here we prepare the result coefficients
            int[] product = new int[2 * coefficients1.Length];

            //base case where we have only 1 coeff=> we can't half the polynomials anymore so we stop
            if (coefficients1.Length == 1)
            {
                product[0] = coefficients1[0] * coefficients2[0];

                //we send the result to the main destination process chosen and we stop
                Communicator.world.Send<int[]>(product, from, 0);
                return;
            }

            int halfArraySize = coefficients1.Length / 2;

            //first we half those 2 polynomials

            int[] coefficients1Low = new int[halfArraySize];
            int[] coefficients1High = new int[halfArraySize];
            int[] coefficients2Low = new int[halfArraySize];
            int[] coefficients2High = new int[halfArraySize];

            int[] coefficients1LowHigh = new int[halfArraySize];
            int[] coefficients2LowHigh = new int[halfArraySize];

            //we prepare the formula for the multiplication
            //c0=low A * low B,     c1= high A * high B,      c2=(low A+ high A)*(low B+high B)-c0-c1

            //first prepare lowA, lowB, high A, high B, low A+ high A, low B+high B
            for (int halfSizeIndex = 0; halfSizeIndex < halfArraySize; halfSizeIndex++)
            {

                coefficients1Low[halfSizeIndex] = coefficients1[halfSizeIndex];
                coefficients1High[halfSizeIndex] = coefficients1[halfSizeIndex + halfArraySize];
                coefficients1LowHigh[halfSizeIndex] = coefficients1Low[halfSizeIndex] + coefficients1High[halfSizeIndex];

                coefficients2Low[halfSizeIndex] = coefficients2[halfSizeIndex];
                coefficients2High[halfSizeIndex] = coefficients2[halfSizeIndex + halfArraySize];
                coefficients2LowHigh[halfSizeIndex] = coefficients2Low[halfSizeIndex] + coefficients2High[halfSizeIndex];

            }

            //call this function to get the needed products: low A * low B, high A * high B, (low A+ high A)*(low B+high B)
            int[] productLow, productHigh, productLowHigh;

            //but we have to know how many processes we have to know the level of distribution we take for solving 
            if (sendTo.Length == 0)
            {
                //case where we only have a process to do the computations so we call the async function

                //get the results from the computations which are done
                productLow = AsynchronousKaratsubaMultiplyRecursive(coefficients1Low, coefficients2Low);
                productHigh = AsynchronousKaratsubaMultiplyRecursive(coefficients1High, coefficients2High);
                productLowHigh = AsynchronousKaratsubaMultiplyRecursive(coefficients1LowHigh, coefficients2LowHigh);
            }
            else if (sendTo.Length == 1)
            {
                //case where we have 2 processes to do the computations so we distribute like before 
                //one distributivity and then call to async function

                Communicator.world.Send<int>(Communicator.world.Rank, sendTo[0], 0);
                Communicator.world.Send<int[]>(coefficients1Low, sendTo[0], 0);
                Communicator.world.Send<int[]>(coefficients2Low, sendTo[0], 0);
                Communicator.world.Send<int[]>(new int[0], sendTo[0], 0);

                //get the results from the computations which are done
                productHigh = AsynchronousKaratsubaMultiplyRecursive(coefficients1High, coefficients2High);
                productLowHigh = AsynchronousKaratsubaMultiplyRecursive(coefficients1LowHigh, coefficients2LowHigh);

                productLow = Communicator.world.Receive<int[]>(sendTo[0], 0);
            }
            else if (sendTo.Length == 2)
            {
                //case where we have 3 processes to do the computations so we distribute once more like before 
                //2 distributivity and then call to async function

                Communicator.world.Send<int>(Communicator.world.Rank, sendTo[0], 0);
                Communicator.world.Send<int[]>(coefficients1Low, sendTo[0], 0);
                Communicator.world.Send<int[]>(coefficients2Low, sendTo[0], 0);
                Communicator.world.Send<int[]>(new int[0], sendTo[0], 0);

                Communicator.world.Send<int>(Communicator.world.Rank, sendTo[1], 0);
                Communicator.world.Send<int[]>(coefficients1High, sendTo[1], 0);
                Communicator.world.Send<int[]>(coefficients2High, sendTo[1], 0);
                Communicator.world.Send<int[]>(new int[0], sendTo[1], 0);

                //get the results from the computations which are done
                productLowHigh = AsynchronousKaratsubaMultiplyRecursive(coefficients1LowHigh, coefficients2LowHigh);

                productLow = Communicator.world.Receive<int[]>(sendTo[0], 0);
                productHigh = Communicator.world.Receive<int[]>(sendTo[1], 0);
            }
            else if (sendTo.Length == 3)
            {
                //case where we have 4 processes to do the computations so we distribute 2 more times like before 
                //3 distributivity and then call to async function

                Communicator.world.Send<int>(Communicator.world.Rank, sendTo[0], 0);
                Communicator.world.Send<int[]>(coefficients1Low, sendTo[0], 0);
                Communicator.world.Send<int[]>(coefficients2Low, sendTo[0], 0);
                Communicator.world.Send<int[]>(new int[0], sendTo[0], 0);


                Communicator.world.Send<int>(Communicator.world.Rank, sendTo[1], 0);
                Communicator.world.Send<int[]>(coefficients1High, sendTo[1], 0);
                Communicator.world.Send<int[]>(coefficients2High, sendTo[1], 0);
                Communicator.world.Send<int[]>(new int[0], sendTo[1], 0);

                Communicator.world.Send<int>(Communicator.world.Rank, sendTo[2], 0);
                Communicator.world.Send<int[]>(coefficients1LowHigh, sendTo[2], 0);
                Communicator.world.Send<int[]>(coefficients2LowHigh, sendTo[2], 0);
                Communicator.world.Send<int[]>(new int[0], sendTo[2], 0);

                //get the results from the computations which are done
                productLow = Communicator.world.Receive<int[]>(sendTo[0], 0);
                productHigh = Communicator.world.Receive<int[]>(sendTo[1], 0);
                productLowHigh = Communicator.world.Receive<int[]>(sendTo[2], 0);
            }
            else
            {
                //case where we have more than 4 to do the computations 
                //first distribute multiple times and then call the async function to do the computations

                int[] auxSendTo = sendTo.Skip(3).ToArray();
                int auxLength = auxSendTo.Length / 3;

                Communicator.world.Send<int>(Communicator.world.Rank, sendTo[0], 0);
                Communicator.world.Send<int[]>(coefficients1Low, sendTo[0], 0);
                Communicator.world.Send<int[]>(coefficients2Low, sendTo[0], 0);
                Communicator.world.Send<int[]>(auxSendTo.Take(auxLength).ToArray(), sendTo[0], 0);

                Communicator.world.Send<int>(Communicator.world.Rank, sendTo[1], 0);
                Communicator.world.Send<int[]>(coefficients1High, sendTo[1], 0);
                Communicator.world.Send<int[]>(coefficients2High, sendTo[1], 0);
                Communicator.world.Send<int[]>(auxSendTo.Skip(auxLength).Take(auxLength).ToArray(), sendTo[1], 0);

                Communicator.world.Send<int>(Communicator.world.Rank, sendTo[2], 0);
                Communicator.world.Send<int[]>(coefficients1LowHigh, sendTo[2], 0);
                Communicator.world.Send<int[]>(coefficients2LowHigh, sendTo[2], 0);
                Communicator.world.Send<int[]>(auxSendTo.Skip(2 * auxLength).ToArray(), sendTo[2], 0);

                //get the results from the computations which are done
                productLow = Communicator.world.Receive<int[]>(sendTo[0], 0);
                productHigh = Communicator.world.Receive<int[]>(sendTo[1], 0);
                productLowHigh = Communicator.world.Receive<int[]>(sendTo[2], 0);
            }

            //do the sustraction for c2=(low A+ high A)*(low B+high B)-c0-c1 by substraction the needed c0 and c1 from the results above
            int[] productMiddle = new int[coefficients1.Length];
            for (int halfSizeIndex = 0; halfSizeIndex < coefficients1.Length; halfSizeIndex++)
            {
                productMiddle[halfSizeIndex] = productLowHigh[halfSizeIndex] - productLow[halfSizeIndex] - productHigh[halfSizeIndex];
            }

            //calc final result for multiplication by AXB=c1(shifted len*2) pos + c2(shifted len pos) + c0
            //only thing we need is the shift and the add of all
            for (int halfSizeIndex = 0, middleOffset = coefficients1.Length / 2; halfSizeIndex < coefficients1.Length; ++halfSizeIndex)
            {
                product[halfSizeIndex] += productLow[halfSizeIndex];
                product[halfSizeIndex + coefficients1.Length] += productHigh[halfSizeIndex];
                product[halfSizeIndex + middleOffset] += productMiddle[halfSizeIndex];
            }

            //the result is done so we send it to the destination process chosen 
            Communicator.world.Send<int[]>(product, from, 0);

        }
    }
}
