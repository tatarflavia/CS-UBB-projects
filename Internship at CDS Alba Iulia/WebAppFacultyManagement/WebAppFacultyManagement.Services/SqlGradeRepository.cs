using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public class SqlGradeRepository : IGradeRepository
    {
        public AppDbContext Context { get; }

        public SqlGradeRepository(AppDbContext context)
        {
            Context = context;
        }

        public Grade Add(Grade newGrade)
        {
            if (newGrade == null)
            {
                return newGrade;
            }
            var StudentID = new SqlParameter("@StudentID", newGrade.StudentID);
            var SubjectID = new SqlParameter("@SubjectID", newGrade.SubjectID);
            var GradeValue = new SqlParameter("@GradeValue", newGrade.GradeValue);

            Context.Database.ExecuteSqlCommand("INSERT INTO Grades(StudentID,SubjectID,GradeValue) VALUES(@StudentID,@SubjectID,@GradeValue)", StudentID, SubjectID, GradeValue);

            return newGrade;
        }

        public Grade AddByParams(int StudentID, int SubjectID, int GradeValue)
        {
            var StudentIDSql = new SqlParameter("@StudentID", StudentID);
            var SubjectIDSql = new SqlParameter("@SubjectID", SubjectID);
            var GradeValueSql = new SqlParameter("@GradeValue", GradeValue);

            Context.Database.ExecuteSqlCommand("INSERT INTO Grades(StudentID,SubjectID,GradeValue) VALUES(@StudentID,@SubjectID,@GradeValue)", StudentIDSql, SubjectIDSql, GradeValueSql);

            var GradeID= Context.Grades.Where(x => x.StudentID == StudentID && x.SubjectID== SubjectID).Select(x => x.GradeID);

            return Context.Grades.Find(GradeID.FirstOrDefault());
        }


        public Grade Delete(int id)
        {
            Grade gradeToBeDeleted = Context.Grades.Find(id);
            if (gradeToBeDeleted != null)
            {
                Context.Grades.Remove(gradeToBeDeleted);
                Context.SaveChanges();
            }

            return gradeToBeDeleted;
        }

        public IEnumerable<Grade> GetAllGrades()
        {
            return Context.Grades;
        }

        public IEnumerable<Grade> GetAllGradesForASubject(int SubjectID)
        {
            var grades = new List<Grade>();
            foreach (var grade in Context.Grades) {
                if (grade.SubjectID == SubjectID) {
                    grades.Add(grade);
                }
            }
            return grades;
        }

        public Grade GetGrade(int id)
        {
            return Context.Grades.Find(id);
        }

        public Grade Update(Grade updatedGrade)
        {
            if (updatedGrade == null)
            {
                return updatedGrade;
            }
            var GradeID = new SqlParameter("@GradeID", updatedGrade.GradeID);       
            var GradeValue = new SqlParameter("@GradeValue", updatedGrade.GradeValue);

            Context.Database.ExecuteSqlCommand("UPDATE Grades SET GradeValue=@GradeValue WHERE GradeID=@GradeID", GradeValue,GradeID);
            return updatedGrade;
        }

        public bool CheckIfIdComboExists(int StudentID, int SubjectID)
        {
            foreach (var grade in Context.Grades) {
                if (grade.SubjectID == SubjectID && grade.StudentID == StudentID)
                    return true;
            }
            return false;
        }

        public Grade GetGradeByIDCombo(int StudentID, int SubjectID)
        {
            foreach (var grade in Context.Grades)
            {
                if (grade.SubjectID == SubjectID && grade.StudentID == StudentID)
                    return grade;
            }
            return null;
        }

        public void DeleteAllGradesForDeletedStudent(int StudentID)
        {
            List<Grade> listToBeDeleted = new List<Grade>();
            foreach (var grade in Context.Grades) {
                if (grade.StudentID == StudentID) {
                    listToBeDeleted.Add(grade);
                }
            }
            //delete from big list
            foreach (var grade in listToBeDeleted)
            {
                Delete(grade.GradeID);
            }
        }

        public void DeleteAllGradesForDeletedSubject(int SubjectID)
        {
            List<Grade> listToBeDeleted = new List<Grade>();
            foreach (var grade in Context.Grades)
            {
                if (grade.SubjectID == SubjectID)
                {
                    listToBeDeleted.Add(grade);
                }
            }
            //delete from big list
            foreach (var grade in listToBeDeleted) {
                Delete(grade.GradeID);
            }
        }
    }
}
