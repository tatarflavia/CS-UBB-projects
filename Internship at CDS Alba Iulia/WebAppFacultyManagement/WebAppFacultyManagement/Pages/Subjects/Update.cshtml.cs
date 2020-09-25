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

namespace WebAppFacultyManagement.Pages.Subjects
{
    public class UpdateModel : PageModel
    {
        private readonly ISubjectRepository SubjectRepository;
        private readonly IHostingEnvironment webHostEnvironment;

        public UpdateModel(ISubjectRepository SubjectRepository, IHostingEnvironment webHostEnvironment)
        {
            this.SubjectRepository = SubjectRepository;
            this.webHostEnvironment = webHostEnvironment;
        }

        [BindProperty]
        public Subject SelectedSubject { get; set; }
        public bool HasAdminRights { get; private set; }

        //for photo upload
        [BindProperty]
        public IFormFile Photo { get; set; }

        public IActionResult OnGet(int? id)
        {
            if (id.HasValue)
            {
                SelectedSubject = SubjectRepository.GetSubject(id.Value);
            }
            else
            {
                SelectedSubject = new Subject();
            }

            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }

            if (SelectedSubject == null)
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
                    if (SelectedSubject.Photopath != null)
                    {
                        //delete old photo
                        string filePath = Path.Combine(webHostEnvironment.WebRootPath,
                        "images", SelectedSubject.Photopath);
                        System.IO.File.Delete(filePath);
                    }
                    //save new photo to images folder
                    SelectedSubject.Photopath = ProcessUploadedFile();
                }
                if (SelectedSubject.SubjectID > 0)
                {
                    SelectedSubject = SubjectRepository.Update(SelectedSubject);
                }
                else
                {
                    SelectedSubject = SubjectRepository.Add(SelectedSubject);
                }
                return RedirectToPage("/Subjects/Index");
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