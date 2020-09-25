using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using WebAppFacultyManagement.Models;
using WebAppFacultyManagement.Services;

namespace WebAppFacultyManagement.Pages.Classes
{
    public class IndexModel : PageModel
    {
        private readonly IClassRepository ClassRepository;
        private readonly ITeacherRepository TeacherRepository;

        public IEnumerable<Class> Classes { get; set; }
        public IEnumerable<Teacher> Teachers { get; set; }
        public bool HasAdminRights { get; private set; }

        public IndexModel(IClassRepository ClassRepository, ITeacherRepository TeacherRepository)
        {
            this.ClassRepository = ClassRepository;
            this.TeacherRepository = TeacherRepository;
        }



        public void OnGet()
        {
            Classes = ClassRepository.GetAllClasses();
            Teachers = TeacherRepository.GetAllTeachers();
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