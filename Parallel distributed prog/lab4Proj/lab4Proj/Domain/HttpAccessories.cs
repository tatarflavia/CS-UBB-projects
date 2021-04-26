using System;

namespace lab4Proj.Domain
{
    public class HttpAccessories
    {
        public static readonly int HTTP_PORT = 80;

        public static string GetResponseBody(string responseContent) {
            //the response is what we get from the server, it has: response headers, 2 empty lines and response body(html)

            var splits = responseContent.Split(new[] { "\r\n\r\n" }, StringSplitOptions.RemoveEmptyEntries);

            //if response body is empty, then we return an empty string(it still hasn't got it completely from the server)
            if (splits.Length > 1)
            {
                return splits[1];
            }
            else { return ""; }
        }

        public static bool ResponseHeadersAreObtained(string responseContent) {
            //the headers are obtained if the content has 2 empty lines(cause the next part is the body(html of the site))
            return responseContent.Contains("\r\n\r\n");
        }

        public static int GetValueFromContentLengthHeaderLine(string responseContent) {
            //gets the value from the header named content length which is how much chars the response body has
            var splits = responseContent.Split('\r','\n');
            foreach (var line in splits) {
                //separator for headers is ":"
                var headerParts = line.Split(':');
                if (headerParts[0].Equals( "Content-Length")) {
                    return int.Parse( headerParts[1]);
                }
            }
            return 0;
        }

        public static string GetRequestString(string hostname, string endpoint)
        {
            //creates the get request for this specific server hostname(domain of the site) adding the end of the path from the endpoint var

            return "GET " + endpoint + " HTTP/1.1\r\n" +
                   "Host: " + hostname + "\r\n" +
                   "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36\r\n" +
                   "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,#1#*;q=0.8\r\n" +
                   "Accept-Language: en-US,en;q=0.9,ro;q=0.8\r\n" +
                   // the server will add the content-length header ONLY if the data comes archived (gzip)
                   "Accept-Encoding: gzip, deflate\r\n" +
                   "Connection: keep-alive\r\n" +
                   "Upgrade-Insecure-Requests: 1\r\n" +
                   "Pragma: no-cache\r\n" +
                   "Cache-Control: no-cache\r\n" +
                   "Content-Length: 0\r\n\r\n";
        }


    }
}
