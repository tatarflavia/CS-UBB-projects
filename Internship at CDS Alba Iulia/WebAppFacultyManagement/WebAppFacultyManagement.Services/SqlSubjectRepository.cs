using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public class SqlSubjectRepository : ISubjectRepository
    {
        public AppDbContext Context { get; }

        public SqlSubjectRepository(AppDbContext context)
        {
            Context = context;
        }

        public Subject Add(Subject newSubject)
        {
            if (newSubject == null)
            {
                return newSubject;
            }
            var Name = new SqlParameter("@Name", newSubject.Name);
            var Description = new SqlParameter("@Description", newSubject.Description);
            var Photopath = new SqlParameter("@Photopath", newSubject.Photopath);
            if (newSubject.Photopath != null)
            {
                Context.Database.ExecuteSqlCommand("INSERT INTO Subjects(Name, Description,Photopath) VALUES(@Name,@Description,@Photopath)", Name, Description, Photopath);
            }
            else {
                Context.Database.ExecuteSqlCommand("INSERT INTO Subjects(Name, Description) VALUES(@Name,@Description)", Name,Description);
            }
            return newSubject;
        }

        public Subject Delete(int id)
        {
            Subject subjectToBeDeleted = Context.Subjects.Find(id);
            if (subjectToBeDeleted != null)
            {
                Context.Subjects.Remove(subjectToBeDeleted);
                Context.SaveChanges();
            }

            return subjectToBeDeleted;
        }

        public IEnumerable<Subject> GetAllSubjects()
        {
            return Context.Subjects;
        }

        public Subject GetSubject(int id)
        {
            return Context.Subjects.Find(id);
        }

        public Subject Update(Subject updatedSubject)
        {
            if (updatedSubject == null)
            {
                return updatedSubject;
            }
            var SubjectID = new SqlParameter("@SubjectID", updatedSubject.SubjectID);
            var Name = new SqlParameter("@Name", updatedSubject.Name);
            var Description = new SqlParameter("@Description", updatedSubject.Description);
            var Photopath = new SqlParameter("@Photopath", updatedSubject.Photopath);
            if (Photopath != null)
            {
                Context.Database.ExecuteSqlCommand("UPDATE Subjects SET Name=@Name,Description=@Description,Photopath=@Photopath WHERE SubjectID=@SubjectID", Name, Description, Photopath, SubjectID);
            }
            else {
                Context.Database.ExecuteSqlCommand("UPDATE Subjects SET Name=@Name,Description=@Description WHERE SubjectID=@SubjectID", Name, Description,SubjectID);
            }
            return updatedSubject;
        }
    }
}
