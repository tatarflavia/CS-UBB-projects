using MPI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace lab8
{
    class Program
    {
        static void listener(Object obj)
        {
            //we take the dsm and the while is alive as long as this thread that is for the process is alive
            DSM dsm = (DSM)obj;

            while (true)
            {
                //here we receive all messages for this current process and then write the vars from its dsm
                Msg msg = Communicator.world.Receive<Msg>(Communicator.anySource, Communicator.anyTag);
                
                if (msg.exit) break;

                if (msg.updateMsg != null)
                {
                    Console.WriteLine("Process " + Communicator.world.Rank + " received update message: " + msg.updateMsg.var + "'s value changes to: " + msg.updateMsg.val);
                    dsm.setVar(msg.updateMsg.var, msg.updateMsg.val);
                }

                if (msg.subscribeMsg != null)
                {
                    Console.WriteLine("Process " + Communicator.world.Rank + "received subscriebe message: " + msg.subscribeMsg.rank + " subbed to: " + msg.subscribeMsg.var);
                    dsm.subscribeOther(msg.subscribeMsg.var, msg.subscribeMsg.rank);

                }
                Thread.Sleep(1000);
                Console.WriteLine("writing vars for Process " + Communicator.world.Rank + "...");
                writeVars(dsm);
                Thread.Sleep(1000);
            }
        }

        static void writeVars(DSM dsm)
        {
            //write the vars from this dsm for the current rank aka process
            Console.Write("Process " + Communicator.world.Rank +" has the vars: "+ " a= " + dsm.a + " b= " + dsm.b + " c= " + dsm.c + " d= " + dsm.d + " subscribed to following processes: \n");
            StringBuilder stringBuilder = new StringBuilder("");
            foreach (string var in dsm.subscribers.Keys)
            {
                
                stringBuilder.Append(var + " sub to{ ");
                //Console.Write(var + " subbed to { ");
                foreach (int rank in dsm.subscribers[var])
                {
                    stringBuilder.Append(rank + " ");
                    //Console.Write(rank + " ");
                }
                stringBuilder.Append("};  ");
                
            }
            stringBuilder.Append("\n");
            Console.WriteLine(stringBuilder);
            
        }

        static void Main(string[] args)
        {
            using (new MPI.Environment(ref args))
            {
                //init for the dsm and then init for 4 threads, one for each process
                DSM dsm = new DSM();

                if (Communicator.world.Rank == 0)
                {
                    //start the thread that holds this process and then write the menu
                    Thread thread = new Thread(listener);
                    thread.Start(dsm);

                    bool exit = false;

                    Console.WriteLine("process 0 subscriebing to vars a,b,c,d...");
                    dsm.subscribeTo("a");
                    //Thread.Sleep(50000);
                    dsm.subscribeTo("b");
                    dsm.subscribeTo("c");
                    dsm.subscribeTo("d");

                    while (!exit)
                    {
                       
                        Console.WriteLine("1. Set a var");
                        Console.WriteLine("2. Verify and change a var");
                        Console.WriteLine("3. See the vars from the dsm");
                        Console.WriteLine("0. Exit");

                        int answer;
                        int.TryParse(Console.ReadLine(), out answer);

                        if (answer == 0)
                        {
                            dsm.close();
                            exit = true;
                        }
                        else if (answer == 1)
                        {
                            Console.WriteLine("a,b , c,d : ");
                            string var = Console.ReadLine();

                            Console.WriteLine("val to put to chosen var: ");
                            int val;
                            int.TryParse(Console.ReadLine(), out val);

                            dsm.updateVar(var, val);
                            
                        }
                        else if (answer == 2)
                        {
                            Console.WriteLine("a,b ,c,d : ");
                            string var = Console.ReadLine();

                            Console.WriteLine("last val the chosen var had: ");
                            int val;
                            int.TryParse(Console.ReadLine(), out val);

                            Console.WriteLine("val to put to chosen var:  ");
                            int newVal;
                            int.TryParse(Console.ReadLine(), out newVal);

                            dsm.checkAndReplace(var, val, newVal);
                            
                        }
                        else if (answer == 3) {
                            Console.WriteLine("writing vars for Process " + Communicator.world.Rank + "...");
                            writeVars(dsm);
                        }
                    }

                }
                else if (Communicator.world.Rank == 1)
                {
                    //another process
                    Thread thread = new Thread(listener);
                    thread.Start(dsm);

                    dsm.subscribeTo("a");

                    thread.Join();

                }
                else if (Communicator.world.Rank == 2)
                {
                    //another process
                    Thread thread = new Thread(listener);
                    thread.Start(dsm);

                    dsm.subscribeTo("b");

                    thread.Join();
                }
                else if (Communicator.world.Rank == 3)
                {
                    //another process
                    Thread thread = new Thread(listener);
                    thread.Start(dsm);

                    dsm.subscribeTo("c");

                    thread.Join();
                }
            }
        }
    }
}
