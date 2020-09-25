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

namespace WebAppFacultyManagement.Pages.Students
{
    public class UpdateModel : PageModel
    {
        private readonly IStudentRepository StudentRepository;
        private readonly IClassRepository ClassRepository;
        private readonly IHostingEnvironment webHostEnvironment;

        public UpdateModel(IStudentRepository StudentRepository, IClassRepository ClassRepository, IHostingEnvironment webHostEnvironment)
        {
            this.StudentRepository = StudentRepository;
            this.ClassRepository = ClassRepository;
            this.webHostEnvironment = webHostEnvironment;
        }

        [BindProperty]
        public Student SelectedStudent { get; set; }


        [BindProperty]
        public string SelectedClassName { get; set; }
        public IEnumerable<Class> Classes { get; set; }
        public bool HasAdminRights { get; private set; }

        //for photo upload
        [BindProperty]
        public IFormFile Photo { get; set; }

        public IActionResult OnGet(int? id)
        {
            if (id.HasValue)
            {
                SelectedStudent = StudentRepository.GetStudent(id.Value);
                Classes = ClassRepository.GetAllClasses();
            }
            else
            {
                SelectedStudent = new Student();
            }

            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }

            if (SelectedStudent == null)
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
                    if (SelectedStudent.Photopath != null)
                    {
                        //delete old photo
                        string filePath = Path.Combine(webHostEnvironment.WebRootPath,
                        "images", SelectedStudent.Photopath);
                        System.IO.File.Delete(filePath);
                    }
                    //save new photo to images folder
                    SelectedStudent.Photopath = ProcessUploadedFile();
                }
                if (SelectedStudent.StudentID > 0)
                {
                    SelectedStudent = StudentRepository.Update(SelectedStudent);
                }
                else
                {
                    SelectedStudent = StudentRepository.Add(SelectedStudent);
                }
                return RedirectToPage("/Students/Index");
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

        public IActionResult OnPostUpdateClassForSelectedStudent(int? id)
        {
            if (id.HasValue)
            {
                SelectedStudent = StudentRepository.GetStudent(id.Value);
                Classes = ClassRepository.GetAllClasses();

                if (SelectedClassName != null)
                {
                    Class SelectedClass = ClassRepository.GetClassByName(SelectedClassName);
                    SelectedStudent = StudentRepository.ChangeClass(SelectedClass.ClassID, id.Value);
                    return RedirectToPage("/Students/Index");
                }
            }
            return Page();


        }
    }
}