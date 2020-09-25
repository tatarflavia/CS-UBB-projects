using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace WebLab8Asp.Models
{
    public class User
    {
        public User() {
        }

        public User(int userID,string userName,string password) {
            UserID = userID;
            UserName = userName;
            Password = password;
        }


        public int UserID { get; set; }

        [Required]
        [MinLength(3)]
        [MaxLength(40)]
        public string UserName { get; set; }

        [Required]
        [MinLength(3)]
        [MaxLength(80)]
        public string Password { get; set; }
    }
}