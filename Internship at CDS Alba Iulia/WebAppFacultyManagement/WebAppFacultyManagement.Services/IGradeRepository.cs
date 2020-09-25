using System;
using System.Collections.Generic;
using System.Text;
using WebAppFacultyManagement.Models;

namespace WebAppFacultyManagement.Services
{
    public interface IGradeRepository
    {
        IEnumerable<Grade> GetAllGrades();
        IEnumerable<Grade> GetAllGradesForASubject(int SubjectID);
        Grade Add(Grade newGrade);
        Grade AddByParams(int StudentID, int SubjectID, int GradeValue);
        Grade Delete(int id);
        Grade Update(Grade updatedGrade);
        Grade GetGrade(int id);
        bool CheckIfIdComboExists(int StudentID, int SubjectID);
        Grade GetGradeByIDCombo(int StudentID, int SubjectID);
        void DeleteAllGradesForDeletedStudent(int StudentID);
        void DeleteAllGradesForDeletedSubject(int SubjectID);
    }
}
