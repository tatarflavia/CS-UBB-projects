using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using WebAppFacultyManagement.Models;
using WebAppFacultyManagement.Services;

namespace WebAppFacultyManagement.Pages.Teachers
{
    public class IndexModel : PageModel
    {

        private readonly ITeacherRepository TeacherRepository;
        public IEnumerable<Teacher> Teachers { get; set; }
        public bool HasAdminRights { get; set; }

        public IndexModel(ITeacherRepository teacherRepository)
        {
            TeacherRepository = teacherRepository;
        }

        

        public void OnGet()
        {
            Teachers = TeacherRepository.GetAllTeachers();
            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else {
                HasAdminRights = false;
            }
        }
    }
}