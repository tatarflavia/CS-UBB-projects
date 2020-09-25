using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace WebAppFacultyManagement.Models
{
    public class Subject
    {
        public int SubjectID { get; set; }

        [Required]
        [MinLength(3, ErrorMessage = "Name must have at least 3 characters.")]
        [MaxLength(50)]
        public string Name { get; set; }

        [Required]
        [MinLength(10, ErrorMessage = "Description must have at least 10 characters.")]
        [MaxLength(500)]
        public string Description { get; set; }

        public string Photopath { get; set; }


    }
}
