using System;
using System.Collections.Generic;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public interface IClassRepository
    {
        IEnumerable<Class> GetAllClasses();
        Class Add(Class newClass);
        Class Delete(int id);
        Class Update(Class updatedClass);
        Class GetClass(int id);
        Class ChangeTeacher(int TeacherID,int ClassID);
        Class FindClassByTeacherID(int TeacherID);
        Class GetClassByName(string Name);
    }
}
