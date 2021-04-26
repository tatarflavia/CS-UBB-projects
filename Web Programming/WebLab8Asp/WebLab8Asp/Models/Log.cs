using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebLab8Asp.Models
{
    public class Log
    {
        public Log(int logID,string type,string severity ,string logDate,string userName,string logMessage) {
            LogID = logID;
            Type = type;
            Severity = severity;
            LogDate = logDate;
            UserName = userName;
            LogMessage = logMessage;
        }


        public Log() {
        }
        public int LogID { get; set; }

        
        public string Type { get; set; }

        [Required]
        [MinLength(3)]
        [MaxLength(30)]
        public string Severity { get; set; }

        [Required]
        [MinLength(8)]
        [MaxLength(11)]
        public string LogDate { get; set; }

        
        public string UserName { get; set; }

        public string LogMessage { get; set; }
    }
}