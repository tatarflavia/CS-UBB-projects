using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public class SqlStudentRepository : IStudentRepository
    {
        public AppDbContext Context { get; }

        public SqlStudentRepository(AppDbContext context)
        {
            Context = context;
        }

        public Student Add(Student newStudent)
        {
            if (newStudent == null)
            {
                return newStudent;
            }
            var Name = new SqlParameter("@Name", newStudent.Name);
            var Email = new SqlParameter("@Email", newStudent.Email);
            var CNP = new SqlParameter("@CNP", newStudent.CNP);
            var PhoneNumber = new SqlParameter("@PhoneNumber", newStudent.PhoneNumber);
            var Address = new SqlParameter("@Address", newStudent.Address);
            
            var ClassID = new SqlParameter("@ClassID", newStudent.ClassID);

            if (newStudent.Photopath != null)
            {
                var Photopath = new SqlParameter("@Photopath", newStudent.Photopath);
                Context.Database.ExecuteSqlCommand("INSERT INTO Students(Name, Email, CNP,PhoneNumber ,Address,Photopath,ClassID) VALUES(@Name,@Email,@CNP, @PhoneNumber,@Address,@Photopath,@ClassID)", Name, Email, CNP, PhoneNumber, Address, Photopath, ClassID);
            }
            else {
                Context.Database.ExecuteSqlCommand("INSERT INTO Students(Name, Email, CNP,PhoneNumber ,Address,ClassID) VALUES(@Name,@Email,@CNP, @PhoneNumber,@Address,@ClassID)", Name, Email, CNP, PhoneNumber, Address,ClassID);
            }
            return newStudent;
        }

        public Student Delete(int id)
        {
            Student student = Context.Students.Find(id);
            if (student != null)
            {
                Context.Students.Remove(student);
                Context.SaveChanges();
            }

            return student;
        }

        public IEnumerable<Student> GetAllStudents()
        {
            return Context.Students;
        }

        public Student GetStudent(int id)
        {
            return Context.Students.Find(id);
        }

        public Student Update(Student updatedStudent)
        {
            if (updatedStudent == null)
            {
                return updatedStudent;
            }
            var StudentID = new SqlParameter("@StudentID", updatedStudent.StudentID);
            var Name = new SqlParameter("@Name", updatedStudent.Name);
            var Email = new SqlParameter("@Email", updatedStudent.Email);
            var CNP = new SqlParameter("@CNP", updatedStudent.CNP);
            var PhoneNumber = new SqlParameter("@PhoneNumber", updatedStudent.PhoneNumber);
            var Address = new SqlParameter("@Address", updatedStudent.Address);
            var Photopath = new SqlParameter("@Photopath", updatedStudent.Photopath);
            if (updatedStudent.Photopath != null)
            {
                Context.Database.ExecuteSqlCommand("UPDATE Students SET Name=@Name,Email=@Email,CNP=@CNP,PhoneNumber=@PhoneNumber,Address=@Address,Photopath=@Photopath WHERE StudentID=@StudentID", Name, Email, CNP, PhoneNumber, Address, Photopath, StudentID);
            }
            else {
                Context.Database.ExecuteSqlCommand("UPDATE Students SET Name=@Name,Email=@Email,CNP=@CNP,PhoneNumber=@PhoneNumber,Address=@Address WHERE StudentID=@StudentID", Name, Email,CNP, PhoneNumber, Address, StudentID);
            }
             return updatedStudent;
        }

        public IEnumerable<Student> GetAllStudentsFromAClass(int ClassID)
        {
            var listOfStudents = new List<Student>();
            foreach (var student in Context.Students) {
                if (student.ClassID == ClassID) {
                    listOfStudents.Add(student);
                }
            }
            return listOfStudents;
        }

        public Student ChangeClass(int ClassID, int StudentID)
        {
            Student studentToBeChanged = Context.Students.Find(StudentID);
            if (studentToBeChanged == null)
                return studentToBeChanged;
            var StudentIDSql = new SqlParameter("@StudentID", StudentID);
            var ClassIDSql = new SqlParameter("@ClassID", ClassID);
            

            Context.Database.ExecuteSqlCommand("UPDATE Students SET ClassID=@ClassID WHERE StudentID=@StudentID", ClassIDSql, StudentIDSql);
            return Context.Students.Find(StudentID);

        }

        public Student GetStudentByName(string Name)
        {
            foreach (var student in Context.Students)
            {
                if (student.Name == Name)
                    return student;
            }
            return null;
        }

        public void DeleteAClassForAllStudents(int ClassID)
        {
            List<Student> studentsToBeChanged = new List<Student>();
            foreach (var student in Context.Students) {
                if (student.ClassID == ClassID)
                {
                    studentsToBeChanged.Add(student);
                }
            }
            //changing all of them
            foreach (var student in studentsToBeChanged) {
                ChangeClass(0, student.StudentID);
            }
        }
    }
}
