using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace WebAppFacultyManagement.Models
{
    public class Class
    {
        public int ClassID { get; set; }

        [Required]
        [MinLength(2, ErrorMessage = "Name must have at least 2 characters.")]
        [MaxLength(50)]
        public string Name { get; set; }

        [Required]
        [MinLength(3, ErrorMessage = "Profile name must have at least 3 characters.")]
        [MaxLength(100)]
        public string Profile { get; set; }


        public string Photopath { get; set; }

        public int TeacherID { get; set; }


    }
}
