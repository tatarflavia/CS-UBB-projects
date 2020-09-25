using System;
using System.Collections.Generic;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public interface IStudentRepository
    {
        IEnumerable<Student> GetAllStudents();
        Student Add(Student newStudent);
        Student Delete(int id);
        Student Update(Student updatedStudent);
        Student GetStudent(int id);
        IEnumerable<Student> GetAllStudentsFromAClass(int ClassID);
        Student ChangeClass(int ClassID, int StudentID);
        Student GetStudentByName(string Name);
        void DeleteAClassForAllStudents(int ClassID);
    }
}
