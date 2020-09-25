using System;

using System.Collections.Generic;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public interface ITeacherRepository
    {
        IEnumerable<Teacher> GetAllTeachers();
        Teacher Add(Teacher newTeacher);
        Teacher Delete(int id);
        Teacher Update(Teacher updatedTeacher);
        Teacher GetTeacher(int id);
        Teacher GetTeacherByName(string Name);
    }
}
