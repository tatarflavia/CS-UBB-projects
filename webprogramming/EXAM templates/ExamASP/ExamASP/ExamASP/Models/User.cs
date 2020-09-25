using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace ExamASP.Models
{
    public class User
    {
        public User()
        {
        }

        public User(int userid, string userName, string passWord)
        {
            Id =userid;
            Username = userName;
            Password = passWord;
        }


        public int Id { get; set; }

        [Required]
        [MinLength(3)]
        [MaxLength(40)]
        public string Username { get; set; }

        [Required]
        [MinLength(3)]
        [MaxLength(80)]
        public string Password { get; set; }
    }
}