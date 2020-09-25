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
    public class StudentListModel : PageModel
    {
        private readonly IClassRepository ClassRepository;
        private readonly IStudentRepository StudentRepository;

        public StudentListModel(IClassRepository ClassRepository,IStudentRepository StudentRepository)
        {
            this.ClassRepository = ClassRepository;
            this.StudentRepository = StudentRepository;
        }

        public Class SelectedClass { get; set; }

        public IEnumerable<Student> Students { get; set; }
        public bool HasAdminRights { get; private set; }

        public IActionResult OnGet(int id)
        {
            
            SelectedClass = ClassRepository.GetClass(id);
            if (SelectedClass == null)
            {
                return RedirectToPage("/Error");
            }
            Students = StudentRepository.GetAllStudentsFromAClass(SelectedClass.ClassID);
            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }

            return Page();
        }
    }
}