using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using WebAppFacultyManagement.Models;
using WebAppFacultyManagement.Services;

namespace WebAppFacultyManagement.Pages.Subjects
{
    public class IndexModel : PageModel
    {
        private readonly ISubjectRepository SubjectRepository;
        private readonly IGradeRepository GradeRepository;

        public IEnumerable<Subject> Subjects { get; set; }
        public bool HasAdminRights { get; private set; }

        public IndexModel(ISubjectRepository SubjectRepository,IGradeRepository GradeRepository)
        {
            this.SubjectRepository = SubjectRepository;
            this.GradeRepository = GradeRepository;
        }

        public float GetAverageGradeForSubj(int subjectID) {
            IEnumerable<Grade> grades = GradeRepository.GetAllGradesForASubject(subjectID);
            if (grades.Count() == 0)
                return 0;
            int total = 0;
            foreach (var grade in grades) {
                total += grade.GradeValue;
            }
            
            return total / grades.Count();
        }

        public void OnGet()
        {
            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }
            Subjects = SubjectRepository.GetAllSubjects();
        }
    }
}