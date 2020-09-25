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
    public class GradeListModel : PageModel
    {
        private readonly IStudentRepository StudentRepository;
        private readonly IGradeRepository GradeRepository;
        private readonly ISubjectRepository SubjectRepository;

        public GradeListModel(IStudentRepository StudentRepository,IGradeRepository GradeRepository,ISubjectRepository SubjectRepository)
        {
            this.StudentRepository = StudentRepository;
            this.GradeRepository = GradeRepository;
            this.SubjectRepository = SubjectRepository;
        }

        //for listing
        public IEnumerable<Student> Students { get; set; }
        public Subject SelectedSubject { get; set; }
        public IEnumerable<Grade> GradesForSelectedSubject { get; set; }


        //for add form
        [BindProperty]
        public int GradeValueForAddGrade { get; set; }
        [BindProperty]
        public string SelectedStudentNameForAddGrade { get; set; }
        public string Message ;


        //for delete form
        [BindProperty]
        public int GradeIDToBeDeleted { get; set; }
        public bool HasAdminRights { get; private set; }

        public IActionResult OnGet(int id)
        {
            SelectedSubject = SubjectRepository.GetSubject(id);
            if (SelectedSubject == null)
            {
                return RedirectToPage("/Error");
            }
            Students = StudentRepository.GetAllStudents();
            GradesForSelectedSubject = GradeRepository.GetAllGradesForASubject(id);
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

        public IActionResult OnPost(int id)
        {
            //trebuie un input care tine id ul lui selected subj si o lista de studenti de ales numele 
            //plus verificare daca mai exista aceeasi combo de id uri :stud plus subj
            if (SelectedStudentNameForAddGrade != null && GradeValueForAddGrade >= 0 && GradeValueForAddGrade <= 10)
            {
                if (HttpContext.Session.GetString("HasAdminRights") == "yes")
                {
                    HasAdminRights = true;
                }
                else
                {
                    HasAdminRights = false;
                }
                SelectedSubject = SubjectRepository.GetSubject(id);
                Students = StudentRepository.GetAllStudents();
                GradesForSelectedSubject = GradeRepository.GetAllGradesForASubject(id);
                Student selectedStudent = StudentRepository.GetStudentByName(SelectedStudentNameForAddGrade);
                //check to see if there is such a grade already
                Grade GradeToBeAdded = GradeRepository.GetGradeByIDCombo(selectedStudent.StudentID, SelectedSubject.SubjectID);
                if (GradeToBeAdded == null)
                {
                    //then add
                    GradeToBeAdded = GradeRepository.AddByParams(selectedStudent.StudentID, SelectedSubject.SubjectID, GradeValueForAddGrade);
                    if (GradeToBeAdded != null) {
                        Message = "Add was successfull.";
                    }
                    GradesForSelectedSubject = GradeRepository.GetAllGradesForASubject(id);
                    return Page();
                }
                else {
                    //then update
                    GradeToBeAdded.GradeValue = GradeValueForAddGrade;
                    GradeToBeAdded = GradeRepository.Update(GradeToBeAdded);
                    if (GradeToBeAdded != null)
                    {
                        Message = "The grade was updated.";
                    } 
                    return Page();
                }
                
            }
            else {
                Message = "You must select a student name and write a value for the grade.";
                SelectedSubject = SubjectRepository.GetSubject(id);
                Students = StudentRepository.GetAllStudents();
                GradesForSelectedSubject = GradeRepository.GetAllGradesForASubject(id);
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

        public IActionResult OnPostDeleteGrade(int id) {
            SelectedSubject = SubjectRepository.GetSubject(id);
            Students = StudentRepository.GetAllStudents();
            GradesForSelectedSubject = GradeRepository.GetAllGradesForASubject(id);
            if (HttpContext.Session.GetString("HasAdminRights") == "yes")
            {
                HasAdminRights = true;
            }
            else
            {
                HasAdminRights = false;
            }
            if (GradeIDToBeDeleted != 0) {
                Grade toBeDeleted = GradeRepository.Delete(GradeIDToBeDeleted);
                if (toBeDeleted != null) {
                    GradesForSelectedSubject = GradeRepository.GetAllGradesForASubject(id);
                    return Page();
                }   
            }
            return Page();
        }
    }
}