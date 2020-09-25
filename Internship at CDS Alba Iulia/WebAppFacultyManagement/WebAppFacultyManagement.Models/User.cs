using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace WebAppFacultyManagement.Models
{
    public class User
    {
        public int UserID { get; set; }

        [Required]
        [MinLength(3, ErrorMessage = "Name must have at least 3 characters.")]
        [MaxLength(50)]
        public string Username { get; set; }

        [Required]
        [MinLength(3, ErrorMessage = "Password must have at least 3 characters.")]
        [MaxLength(50)]
        public string Password { get; set; }

        public bool HasAdminRights { get; set; }
        
    }
}
