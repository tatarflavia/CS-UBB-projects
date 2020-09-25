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
    public class DeleteModel : PageModel
    {
        private readonly ISubjectRepository SubjectRepository;
        private readonly IGradeRepository GradeRepository;

        public DeleteModel(ISubjectRepository SubjectRepository,IGradeRepository GradeRepository)
        {
            this.SubjectRepository = SubjectRepository;
            this.GradeRepository = GradeRepository;
        }

        [BindProperty]
        public Subject Subject { get; set; }
        public bool HasAdminRights { get; private set; }

        public IActionResult OnGet(int id)
        {
            Subject = SubjectRepository.GetSubject(id);
            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }
            if (Subject == null)
            {
                return RedirectToPage("/Error");
            }
            return Page();
        }
        public IActionResult OnPost()
        {
            Subject deletedSubject = SubjectRepository.Delete(Subject.SubjectID);
            if (deletedSubject == null)
            {
                return RedirectToPage("/Error");
            }
            //delete all grades for this subject
            GradeRepository.DeleteAllGradesForDeletedSubject(deletedSubject.SubjectID);
            return RedirectToPage("/Subjects/Index");
        }
    }
}