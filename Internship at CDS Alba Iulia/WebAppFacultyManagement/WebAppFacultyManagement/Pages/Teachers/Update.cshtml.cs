using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using WebAppFacultyManagement.Models;
using WebAppFacultyManagement.Services;
using Microsoft.AspNetCore.Hosting;
using System.IO;
using System;

namespace WebAppFacultyManagement.Pages.Teachers
{
    public class UpdateModel : PageModel
    {
        private readonly ITeacherRepository TeacherRepository;
        private readonly IHostingEnvironment webHostEnvironment;

        public UpdateModel(ITeacherRepository teacherRepository, IHostingEnvironment webHostEnvironment)
        {
            TeacherRepository = teacherRepository;
            this.webHostEnvironment = webHostEnvironment;
        }

        [BindProperty]
        public Teacher SelectedTeacher { get; set; }
        public bool HasAdminRights { get; private set; }

        //for images
        // We use this property to store and process
        // the newly uploaded photo
        [BindProperty]
        public IFormFile Photo { get; set; }

        public IActionResult OnGet(int? id)
        {
            if (id.HasValue)
            {
                SelectedTeacher = TeacherRepository.GetTeacher(id.Value);
            }
            else {
                SelectedTeacher = new Teacher();
                }

            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }

            if (SelectedTeacher == null)
            {
                return RedirectToPage("/Error");
            }
            return Page();
        }

        public IActionResult OnPost()
        {
            if (ModelState.IsValid) {
                if (Photo != null) {
                    //=>there is a photo uploaded
                    //check if the teacher had a photo or not
                    if (SelectedTeacher.Photopath != null) {
                        //delete old photo
                        string filePath = Path.Combine(webHostEnvironment.WebRootPath,
                        "images", SelectedTeacher.Photopath);
                        System.IO.File.Delete(filePath);
                    }
                    //save new photo to images folder
                    SelectedTeacher.Photopath = ProcessUploadedFile();
                }

                if (SelectedTeacher.TeacherID > 0)
                {
                    SelectedTeacher = TeacherRepository.Update(SelectedTeacher);
                }
                else {
                    SelectedTeacher = TeacherRepository.Add(SelectedTeacher);
                }
                return RedirectToPage("/Teachers/Index");
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
    }
}