using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using WebAppFacultyManagement.Models;
using WebAppFacultyManagement.Services;

namespace WebAppFacultyManagement.Pages.Teachers
{
    public class DeleteModel : PageModel
    {
        private readonly ITeacherRepository teacherRepository;
        private readonly IClassRepository ClassRepository;

        public DeleteModel(ITeacherRepository teacherRepository,IClassRepository ClassRepository)
        {
            this.teacherRepository = teacherRepository;
            this.ClassRepository = ClassRepository;
        }

        [BindProperty]
        public Teacher Teacher { get; set; }
        public bool HasAdminRights { get; private set; }

        public IActionResult OnGet(int id)
        {
            Teacher= teacherRepository.GetTeacher(id);
            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }
            if (Teacher == null) {
                return RedirectToPage("/Error");
            }
            return Page();
        }
        public IActionResult OnPost() {
            Teacher deletedTeacher=teacherRepository.Delete(Teacher.TeacherID);
            if (deletedTeacher == null){
                return RedirectToPage("/Error");
            }
            //delete from class too
            Class toChangeTeacher = ClassRepository.FindClassByTeacherID(deletedTeacher.TeacherID);
            if (toChangeTeacher != null) {
                toChangeTeacher = ClassRepository.ChangeTeacher(0, toChangeTeacher.ClassID);
            }
            return RedirectToPage("/Teachers/Index");
        }
    }
}