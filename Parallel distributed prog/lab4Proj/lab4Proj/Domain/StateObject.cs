using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

namespace lab4Proj.Domain
{
    public class StateObject
    {
        //client
        public Socket clientSocket=null;

   
        public const int receivingBufferSize = 512;

        //the place where we keep the response
        public byte[] receivingBuffer = new byte[receivingBufferSize];

        //this will be the whole response we get from the server
        public StringBuilder responseContent = new StringBuilder();

        public int clientID;

        //the host name of the server aka the domain of the site
        public string serverHostname;

        public string serverEndpoint; //the continuation of the site after the domain aka path to which the client makes the request to

        //ip of the server we connect to
        public IPEndPoint serverIPfromEndpoint;

        // mutex for "connect" operation
        public ManualResetEvent connectDone = new ManualResetEvent(false);

        // mutex for "send" operation
        public ManualResetEvent sendDone = new ManualResetEvent(false);

        // mutex for "receive" operation
        public ManualResetEvent receiveDone = new ManualResetEvent(false);

    }
}
