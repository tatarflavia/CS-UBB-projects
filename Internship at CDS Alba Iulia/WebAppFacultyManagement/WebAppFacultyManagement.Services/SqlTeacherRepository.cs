using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public class SqlTeacherRepository : ITeacherRepository
    {
        public AppDbContext Context { get; }

        public SqlTeacherRepository(AppDbContext context)
        {
            Context = context;
        }

        

        public Teacher Add(Teacher newTeacher)
        {
            if (newTeacher == null) {
                return newTeacher;
            }
            var Name = new SqlParameter("@Name", newTeacher.Name);
            var Email = new SqlParameter("@Email", newTeacher.Email);
            var Webadress = new SqlParameter("@Webadress", newTeacher.Webadress);
            var CNP = new SqlParameter("@CNP", newTeacher.CNP);
            var PhoneNumber = new SqlParameter("@PhoneNumber", newTeacher.PhoneNumber);
            var Address = new SqlParameter("@Address", newTeacher.Address);
            if (newTeacher.Photopath != null)
            {
                var Photopath = new SqlParameter("@Photopath", newTeacher.Photopath);
                Context.Database.ExecuteSqlCommand("INSERT INTO Teachers (Name, Email,Webadress, CNP,PhoneNumber ,Address,Photopath) VALUES(@Name,@Email,@Webadress,@CNP, @PhoneNumber,@Address,@Photopath)", Name, Email, Webadress, CNP, PhoneNumber, Address, Photopath);
            }
            else {
                Context.Database.ExecuteSqlCommand("INSERT INTO Teachers (Name, Email,Webadress, CNP,PhoneNumber ,Address) VALUES(@Name,@Email,@Webadress,@CNP, @PhoneNumber,@Address)", Name, Email, Webadress, CNP, PhoneNumber, Address);
            }
            return newTeacher;
        }

        public Teacher Delete(int id)
        {
            Teacher teacher = Context.Teachers.Find(id);
            if (teacher != null) {
                Context.Teachers.Remove(teacher);
                Context.SaveChanges();
            }
            
            return teacher;
        }

        public IEnumerable<Teacher> GetAllTeachers()
        {
            return Context.Teachers ;
        }

        public Teacher Update(Teacher updatedTeacher)
        {
            if (updatedTeacher == null)
            {
                return updatedTeacher;
            }
            var TeacherID = new SqlParameter("@TeacherID", updatedTeacher.TeacherID);
            var Name = new SqlParameter("@Name", updatedTeacher.Name);
            var Email = new SqlParameter("@Email", updatedTeacher.Email);
            var Webadress = new SqlParameter("@Webadress", updatedTeacher.Webadress);
            var CNP = new SqlParameter("@CNP", updatedTeacher.CNP);
            var PhoneNumber = new SqlParameter("@PhoneNumber", updatedTeacher.PhoneNumber);
            var Address = new SqlParameter("@Address", updatedTeacher.Address);
            var Photopath = new SqlParameter("@Photopath", updatedTeacher.Photopath);
            if (updatedTeacher.Photopath != null)
            {
                Context.Database.ExecuteSqlCommand("UPDATE Teachers SET Name=@Name,Email=@Email,Webadress=@Webadress,CNP=@CNP,PhoneNumber=@PhoneNumber,Address=@Address,Photopath=@Photopath WHERE TeacherID=@TeacherID", Name, Email, Webadress, CNP, PhoneNumber, Address, Photopath, TeacherID);
            }
            else {
                Context.Database.ExecuteSqlCommand("UPDATE Teachers SET Name=@Name,Email=@Email,Webadress=@Webadress,CNP=@CNP,PhoneNumber=@PhoneNumber,Address=@Address WHERE TeacherID=@TeacherID", Name, Email, Webadress, CNP, PhoneNumber, Address, TeacherID);
            }
            return updatedTeacher;
        }

        public Teacher GetTeacher(int id)
        {
            return Context.Teachers.Find(id);
        }

        public Teacher GetTeacherByName(string Name)
        {
            foreach (var teacher in Context.Teachers) {
                if (teacher.Name == Name)
                    return teacher;
            }
            return null;
        }
    }
}
