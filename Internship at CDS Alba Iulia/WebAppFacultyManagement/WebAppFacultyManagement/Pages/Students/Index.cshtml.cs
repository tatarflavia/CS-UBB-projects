using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using WebAppFacultyManagement.Models;
using WebAppFacultyManagement.Services;

namespace WebAppFacultyManagement.Pages.Students
{
    public class IndexModel : PageModel
    {
        private readonly IStudentRepository StudentRepository;
        private readonly IClassRepository ClassRepository;

        public IEnumerable<Student> Students { get; set; }
        public IEnumerable<Class> Classes { get; set; }
        public bool HasAdminRights { get; private set; }

        public IndexModel(IStudentRepository Repository,IClassRepository ClassRepository)
        {
            StudentRepository = Repository;
            this.ClassRepository = ClassRepository;
        }



        public void OnGet()
        {
            Students = StudentRepository.GetAllStudents();
            Classes = ClassRepository.GetAllClasses();
            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }

        }
    }
}