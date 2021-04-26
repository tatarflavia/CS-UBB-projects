using lab4Proj.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace lab4Proj.Services
{
    //Wraps the connect/send/receive operations in tasks, with the callback setting the result of the task
    public class TaskMechanism
    {
        private static List<string> Hosts;

        //call initialise client for each host(one client a host)
        public static void StartMechanism(List<string> hostNames)
        {
            Hosts = hostNames;

            var tasks = new List<Task>();

            for (int i = 0; i < hostNames.Count; i++)
            {
                tasks.Add(Task.Factory.StartNew(InitialiseClient, i));
            }
            Task.WaitAll(tasks.ToArray());
        }

        //call start activity for client to begin connecting to the desired server
        private static void InitialiseClient(object idObject)
        {
            var clientID = (int)idObject;
            StartActivityForClient(Hosts[clientID], clientID);
        }

        //this function connects the client with the client id to the server that has the name=hostname
        private static void StartActivityForClient(string hostName, int clientID)
        {
            //first get the server ip by connecting to a http port and a DNS server adress providing the hostName
            var dnsHostIps = Dns.GetHostEntry(hostName.Split('/')[0]); //get ip entries from dns by providing only the domain host name
            var ip = dnsHostIps.AddressList[0]; //get first ip and transform it to IPEndPoint
            var remoteServerEndPoint = new IPEndPoint(ip, HttpAccessories.HTTP_PORT); //get ip of the server

            //get the endpoint for the server
            var endpoint = "/";
            if (hostName.Contains("/"))
            {
                endpoint = hostName.Substring(hostName.IndexOf("/"));
            }

            //create the client tcp socket
            var clientSocket = new Socket(ip.AddressFamily, SocketType.Stream, ProtocolType.Tcp);

            //create state which holds the info for the connection
            var state = new StateObject
            {
                clientSocket = clientSocket,
                serverHostname = hostName.Split('/')[0],
                serverEndpoint = endpoint,
                serverIPfromEndpoint = remoteServerEndPoint,
                clientID = clientID
            };
            

            // connect to the server
            ConnectWrapper(state).Wait();

            // request data from the server
            SendWrapper(state, HttpAccessories.GetRequestString(state.serverHostname, state.serverEndpoint)).Wait();

            // receive the response from the server
            ReceiveWrapper(state).Wait();

            // write the response details to the console
            foreach (var i in state.responseContent.ToString().Split('\r', '\n'))
                Console.WriteLine(i);
            Console.WriteLine("Client " + clientID.ToString() + " socket received response from " + state.serverHostname.ToString());
            Console.WriteLine("Content lenght value said: " +HttpAccessories.GetValueFromContentLengthHeaderLine(state.responseContent.ToString()) + " chars, got " + HttpAccessories.GetResponseBody(state.responseContent.ToString()).Length + " chars in body");

            //close conn, release socket
            clientSocket.Shutdown(SocketShutdown.Both);
            clientSocket.Close();
        }

        private static Task ConnectWrapper(StateObject state)
        {
            Console.WriteLine("We begin connection for client " + state.clientID.ToString() + " to the hostname " + state.serverHostname.ToString());
            state.clientSocket.BeginConnect(state.serverIPfromEndpoint, ConnectCallBack, state);

            return Task.FromResult(state.connectDone.WaitOne());
        }

        //here we handle the ending of the connection to the server 
        private static void ConnectCallBack(IAsyncResult ar)
        {
            //get the details from the state of the connection
            var state = (StateObject)ar.AsyncState;
            var clientSocket = state.clientSocket;
            var clientID = state.clientID;
            var serverHostname = state.serverHostname;

            //end the conn to the server
            clientSocket.EndConnect(ar);
            Console.WriteLine("Client " + clientID.ToString() + " socket connected to " + serverHostname + " on ip server address " + clientSocket.RemoteEndPoint);

            // signal that the connection has been made 
            state.connectDone.Set();


        }
        // beginning of the sending request to the server
        private static Task SendWrapper(StateObject state, string data)
        {
            //form the request string and transform it into bytes
            var bytes = Encoding.ASCII.GetBytes(data);

            Console.WriteLine("We begin sending the request for client " + state.clientID.ToString() + " to the hostname " + state.serverHostname.ToString());
            //begin making the request aka sending the byte data formed above
            state.clientSocket.BeginSend(bytes, 0, bytes.Length, 0, SendCallBack, state);
  
            return Task.FromResult(state.sendDone.WaitOne());
        }

       
        
        //here we handle the ending of sending request to the server
        private static void SendCallBack(IAsyncResult ar)
        {
            //get the details from the state of the connection
            var state = (StateObject)ar.AsyncState;
            var clientSocket = state.clientSocket;
            var clientID = state.clientID;

            //end sending the data to the server
            var bytesSent = clientSocket.EndSend(ar);
            Console.WriteLine("Client " + clientID.ToString() + " sent " + bytesSent + " to the server taken from hostname " + state.serverHostname.ToString());

            // signal that all bytes have been sent
            state.sendDone.Set();

        }

        //here we handle the beggining of getting the response from the server
        private static Task ReceiveWrapper(StateObject state)
        {
            Console.WriteLine("We begin receiving for client " + state.clientID.ToString() + " from the hostname " + state.serverHostname.ToString());
           
            //begin getting the response from the server
            state.clientSocket.BeginReceive(state.receivingBuffer, 0, StateObject.receivingBufferSize, 0, ReceiveCallBack, state);
            return Task.FromResult(state.receiveDone.WaitOne());
        }



        

        //here we handle the ending of getting the response from the server + closing the connection
        private static void ReceiveCallBack(IAsyncResult ar)
        {
            //get the details from the state of the connection
            var state = (StateObject)ar.AsyncState;
            var clientSocket = state.clientSocket;
            var clientID = state.clientID;

            //end receiving data from the server
            var bytesRead = clientSocket.EndReceive(ar);

            //get from the receiving buffer characters and put them into response content of the state as long as nb chars<=buffer size
            state.responseContent.Append(Encoding.ASCII.GetString(state.receivingBuffer, 0, bytesRead));

            //if the response content taken above doesn't have headers, we make another receiving operation
            if (!HttpAccessories.ResponseHeadersAreObtained(state.responseContent.ToString()))
            {
                state.clientSocket.BeginReceive(state.receivingBuffer, 0, StateObject.receivingBufferSize, 0, ReceiveCallBack, state);
            }
            else
            {
                //headers are obtained fully

                //get the response body aka html of the site
                var responseBody = HttpAccessories.GetResponseBody(state.responseContent.ToString());

                //we get the content length val from header inside the response to check if the data received in the body has the desired len
                var contentLenHeaderLineVal = HttpAccessories.GetValueFromContentLengthHeaderLine(state.responseContent.ToString());
                if (responseBody.Length < contentLenHeaderLineVal)
                {
                    //it means we have to make another receiving operation
                    state.clientSocket.BeginReceive(state.receivingBuffer, 0, StateObject.receivingBufferSize, 0, ReceiveCallBack, state);
                }
                else
                {
                    //we have all the info we requested from the server, we can now signal that receiving is done
                    state.receiveDone.Set();
                    
                }
            }

        }
    }
}
