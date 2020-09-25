using System;
using System.ComponentModel.DataAnnotations;

namespace WebAppFacultyManagement.Models
{
    public class Teacher
    {
        public int TeacherID { get; set; }

        [Required]
        [MinLength(3,ErrorMessage ="Name must have at least 3 characters.")]
        [MaxLength(50)]
        public string Name { get; set; }

        [Required]
        [MinLength(3)]
        [MaxLength(50)]
        [Display(Name = "Office Email")]
        [RegularExpression(@"^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$",
        ErrorMessage = "Invalid email format.")]
        public string Email { get; set; }


        [Required]
        [MinLength(22)]
        [MaxLength(200)]
        [Display(Name = "Web Page")]
        [RegularExpression(@"^http\:\/\/gilbertschool\/\~[a-zA-Z]+(\/*[a-zA-Z]*)*$",
        ErrorMessage = "Invalid web page format.")]
        public string Webadress { get; set; }



        [Required]
        [StringLength(13, ErrorMessage = "CNP length must be 13 digits.")]
        [RegularExpression(@"^[0-9]*$",
        ErrorMessage = "Invalid number format.")]
        public string CNP { get; set; }

        
        [Required, MinLength(10, ErrorMessage = "PhoneNumber length must be between 10 and 11 digits.")]
        [MaxLength(11)]
        [RegularExpression(@"^[0-9]*$",
        ErrorMessage = "Invalid number format.")]
        public string PhoneNumber { get; set; }

        [Required(ErrorMessage ="Address is required.")]
        public string Address { get; set; }


        public string Photopath { get; set; }

        
    }
}
