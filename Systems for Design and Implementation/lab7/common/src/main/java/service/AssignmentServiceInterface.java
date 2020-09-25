package service;

import container.Pair;
import domain.Grade;
import domain.Problem;
import domain.Student;
import exception.ServiceException;
import java.util.ArrayList;

public interface AssignmentServiceInterface {
    void assignProblemToStudent(Grade grade) throws ServiceException;

    ArrayList<Grade> showAllGraded();

    ArrayList<Grade> showAllUngraded();

    void deleteGrade(Pair<Long, Long> id) throws ServiceException;

    void deleteAllGradesStudent(long id);

    void deleteAllGradesProblem(long id);

    Student studentHighestGPA() throws ServiceException;

    ArrayList<Problem> gradingInProgressProblem() throws ServiceException;

    ArrayList<Grade> sortGradesByValue();

    void giveGradeToStudent(Grade grade) throws ServiceException;
}
