using System;
using System.Collections.Generic;
using System.Text;

namespace WebAppFacultyManagement.Models
{
    public class Grade
    {
        public int GradeID { get; set; }
        public int SubjectID { get; set; }

        public int StudentID { get; set; }

        public int GradeValue { get; set; }

        
    }
}
