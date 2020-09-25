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
    public class DeleteModel : PageModel
    {
        private readonly IClassRepository ClassRepository;
        private readonly IStudentRepository StudentRepository;

        public DeleteModel(IClassRepository ClassRepository,IStudentRepository StudentRepository)
        {
            this.ClassRepository = ClassRepository;
            this.StudentRepository = StudentRepository;
        }

        [BindProperty]
        public Class ClassToBeDeleted { get; set; }
        public bool HasAdminRights { get; private set; }

        public IActionResult OnGet(int id)
        {
            ClassToBeDeleted = ClassRepository.GetClass (id);
            if (ClassToBeDeleted == null)
            {
                return RedirectToPage("/Error");
            }
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
        public IActionResult OnPost()
        {
            Class deletedClass = ClassRepository.Delete(ClassToBeDeleted.ClassID);
            if (deletedClass == null)
            {
                return RedirectToPage("/Error");
            }
            //delete the class from all students from it
            StudentRepository.DeleteAClassForAllStudents(deletedClass.ClassID);
            return RedirectToPage("/Classes/Index");
        }
    }
}