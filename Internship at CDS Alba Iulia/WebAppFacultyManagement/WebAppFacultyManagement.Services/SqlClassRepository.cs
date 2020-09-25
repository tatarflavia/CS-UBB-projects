using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public class SqlClassRepository : IClassRepository
    {
        public AppDbContext Context { get; }

        public SqlClassRepository(AppDbContext context)
        {
            Context = context;
        }

        public Class Add(Class newClass)
        {
            if (newClass == null)
            {
                return newClass;
            }
            var Name = new SqlParameter("@Name", newClass.Name);
            var Profile = new SqlParameter("@Profile", newClass.Profile);
            var Photopath = new SqlParameter("@Photopath", newClass.Photopath);
            var TeacherID = new SqlParameter("@TeacherID", newClass.ClassID);

            if (newClass.Photopath != null)
            {
                Context.Database.ExecuteSqlCommand("INSERT INTO Classes(Name, Profile,Photopath, TeacherID) VALUES(@Name,@Profile,@Photopath,@TeacherID)", Name, Profile, Photopath, TeacherID);
            }
            else {
                Context.Database.ExecuteSqlCommand("INSERT INTO Classes(Name, Profile, TeacherID) VALUES(@Name,@Profile,@TeacherID)", Name,Profile, TeacherID);
            } 
            return newClass;
        }

        public Class Delete(int id)
        {
            Class classToBeDeleted = Context.Classes.Find(id);
            if (classToBeDeleted != null)
            {
                Context.Classes.Remove(classToBeDeleted);
                Context.SaveChanges();
            }

            return classToBeDeleted;
        }

        public IEnumerable<Class> GetAllClasses()
        {
            return Context.Classes;
        }

        public Class GetClass(int id)
        {
            return Context.Classes.Find(id);
        }

        public Class Update(Class updatedClass)
        {
            if (updatedClass == null)
            {
                return updatedClass;
            }
            var ClassID = new SqlParameter("@ClassID", updatedClass.ClassID);
            var Name = new SqlParameter("@Name", updatedClass.Name);
            var Profile = new SqlParameter("@Profile", updatedClass.Profile);
            var Photopath = new SqlParameter("@Photopath", updatedClass.Photopath);

            if (updatedClass.Photopath != null)
            {
                Context.Database.ExecuteSqlCommand("UPDATE Classes SET Name=@Name,Profile=@Profile,Photopath=@Photopath WHERE ClassID=@ClassID", Name, Profile, Photopath, ClassID);
            }
            else {
                Context.Database.ExecuteSqlCommand("UPDATE Classes SET Name=@Name,Profile=@Profile WHERE ClassID=@ClassID", Name, Profile, ClassID);
            }
            return updatedClass;
        }

        public Class ChangeTeacher(int TeacherID,int ClassID)
        {
            Class classToBeChanged = Context.Classes.Find(ClassID);
            if (classToBeChanged == null)
            {
                return classToBeChanged;
            }
            var ClassIDSql = new SqlParameter("@ClassID", ClassID);  
            var TeacherIDSql = new SqlParameter("@TeacherID", TeacherID);

            Context.Database.ExecuteSqlCommand("UPDATE Classes SET TeacherID=@TeacherID WHERE ClassID=@ClassID", TeacherIDSql, ClassIDSql);
            return Context.Classes.Find(ClassID);
        }

        public Class FindClassByTeacherID(int TeacherID)
        {
            foreach (var selectedClass in Context.Classes) {
                if (selectedClass.TeacherID == TeacherID)
                    return selectedClass;
            }
            return null;
        }

        public Class GetClassByName(string Name)
        {
            foreach (var selectedClass in Context.Classes) {
                if (selectedClass.Name == Name)
                    return selectedClass;
            }
            return null;
        }


    }
}
