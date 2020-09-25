using System;
using System.Collections.Generic;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public interface ISubjectRepository
    {
        IEnumerable<Subject> GetAllSubjects();
        Subject Add(Subject newSubject);
        Subject Delete(int id);
        Subject Update(Subject updatedSubject);
        Subject GetSubject(int id);
    }
}
