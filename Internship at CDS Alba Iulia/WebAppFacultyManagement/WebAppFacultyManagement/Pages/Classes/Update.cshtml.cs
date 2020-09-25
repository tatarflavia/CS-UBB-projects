using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using WebAppFacultyManagement.Models;
using WebAppFacultyManagement.Services;

namespace WebAppFacultyManagement.Pages.Classes
{
    public class UpdateModel : PageModel
    {
        private readonly IClassRepository ClassRepository;
        private readonly ITeacherRepository TeacherRepository;
        private readonly IHostingEnvironment webHostEnvironment;

        public UpdateModel(IClassRepository ClassRepository,ITeacherRepository TeacherRepository, IHostingEnvironment webHostEnvironment)
        {
            this.ClassRepository = ClassRepository;
            this.TeacherRepository = TeacherRepository;
            this.webHostEnvironment = webHostEnvironment;
        }

        [BindProperty]
        public Class SelectedClass { get; set; }


        [BindProperty]
        public string SelectedTeacherName { get; set; }
        public IEnumerable<Teacher> Teachers { get; set; }
        public bool HasAdminRights { get; private set; }

        //for image upload
        [BindProperty]
        public IFormFile Photo { get; set; }

        public IActionResult OnGet(int? id)
        {
            if (id.HasValue)
            {
                SelectedClass = ClassRepository.GetClass(id.Value);
                Teachers = TeacherRepository.GetAllTeachers();
            }
            else
            {
                SelectedClass = new Class();
            }

            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }

            if (SelectedClass == null)
            {
                return RedirectToPage("/Error");
            }
            
            return Page();
        }

        public IActionResult OnPost()
        {
            if (ModelState.IsValid)
            {
                if (Photo != null)
                {
                    //=>there is a photo uploaded
                    //check if the teacher had a photo or not
                    if (SelectedClass.Photopath != null)
                    {
                        //delete old photo
                        string filePath = Path.Combine(webHostEnvironment.WebRootPath,
                        "images", SelectedClass.Photopath);
                        System.IO.File.Delete(filePath);
                    }
                    //save new photo to images folder
                    SelectedClass.Photopath = ProcessUploadedFile();
                }
                if (SelectedClass.ClassID > 0)
                {
                    SelectedClass = ClassRepository.Update(SelectedClass);
                }
                else
                {
                    SelectedClass = ClassRepository.Add(SelectedClass);
                }
                return RedirectToPage("/Classes/Index");
            }
            return Page();
        }

        //gets a unique name for the uploaded photo
        private string ProcessUploadedFile()
        {
            string uniqueFileName = null;

            if (Photo != null)
            {
                //makes path for images folder
                string uploadsFolder = Path.Combine(webHostEnvironment.WebRootPath, "images");
                uniqueFileName = Guid.NewGuid().ToString() + "_" + Photo.FileName;
                string filePath = Path.Combine(uploadsFolder, uniqueFileName);
                //puts the new photo in the folder
                using (var fileStream = new FileStream(filePath, FileMode.Create))
                {
                    Photo.CopyTo(fileStream);
                }
            }

            return uniqueFileName;
        }

        public IActionResult OnPostUpdateTeacherForSelectedClass(int? id)
        {
            if (id.HasValue) {
                SelectedClass = ClassRepository.GetClass(id.Value);
                Teachers = TeacherRepository.GetAllTeachers();

                if (SelectedTeacherName != null) {
                    Teacher SelectedTeacher = TeacherRepository.GetTeacherByName(SelectedTeacherName);
                    //replacing the old classroom that had that teacher with an empty teacher place
                    Class classByTeacher = ClassRepository.FindClassByTeacherID(SelectedTeacher.TeacherID);
                    if (classByTeacher != null){
                        classByTeacher = ClassRepository.ChangeTeacher(0, classByTeacher.ClassID);
                    }
                    //actually changing the teacher
                    SelectedClass = ClassRepository.ChangeTeacher(SelectedTeacher.TeacherID, id.Value);               
                    return RedirectToPage("/Classes/Index");
                }
            }
            return Page();
            

        }
    }
}