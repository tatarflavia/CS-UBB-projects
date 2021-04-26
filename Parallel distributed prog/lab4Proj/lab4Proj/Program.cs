using lab4Proj.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab4Proj
{
    class Program
    { 
        public static void Main(string[] args)
        {
            List<string> HOSTS = new List<string> {
            // - gzip form (compressed)
            "www.cs.ubbcluj.ro/~adriana/HCI/2020",
            // - empty body (just signals that the page has moved and the HTTPS protocol should be used from now on)
            "instagram.com",
            // - plain text
            "swish.swi-prolog.org",
        };

            //EventDrivenMechanism.StartMechanism(HOSTS);
            //TaskMechanism.StartMechanism(HOSTS);
            AsyncTaskMechanism.StartMechanism(HOSTS);
            Console.ReadLine();
        }
    }
}
