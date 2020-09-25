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
    public class DeleteModel : PageModel
    {
        private readonly IStudentRepository StudentRepository;
        private readonly IGradeRepository GradeRepository;

        public DeleteModel(IStudentRepository StudentRepository,IGradeRepository GradeRepository)
        {
            this.StudentRepository = StudentRepository;
            this.GradeRepository = GradeRepository;
        }

        [BindProperty]
        public Student Student { get; set; }
        public bool HasAdminRights { get; private set; }

        public IActionResult OnGet(int id)
        {
            Student = StudentRepository.GetStudent(id);
            if (Student == null)
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
            Student deletedStudent = StudentRepository.Delete(Student.StudentID);
            if (deletedStudent == null)
            {
                return RedirectToPage("/Error");
            }
            //delete all grades for this student
            GradeRepository.DeleteAllGradesForDeletedStudent(deletedStudent.StudentID);
            return RedirectToPage("/Students/Index");
        }
    }
}