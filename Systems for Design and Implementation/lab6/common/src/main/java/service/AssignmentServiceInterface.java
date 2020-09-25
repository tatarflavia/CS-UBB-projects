package service;

import container.Pair;
import domain.Grade;
import domain.Problem;
import domain.Student;
import exception.ServiceException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface AssignmentServiceInterface {
    String ASSIGN_PROBLEM="assign problem";
    String SHOW_GRADED="show graded";
    String SHOW_UNGRADED="show ungraded";
    String DELETE_GRADE="delete grade";
    String DELETE_GRADES_STUDENT="delete grades student";
    String DELETE_GRADES_PROBLEM="delete grades problem";
    String STUDENT_HIGHEST_GPA="student highest gpa";
    String GRADING_IN_PROGRESS="grading in progress";
    String SORT_GRADES="sort grades";
    String GIVE_GRADE="give grade";

    CompletableFuture<Void> assignProblemToStudent(Grade grade) throws ServiceException;

    CompletableFuture<ArrayList<Grade>> showAllGraded();

    CompletableFuture<ArrayList<Grade>> showAllUngraded();

    CompletableFuture<Void> deleteGrade(Pair<Long, Long> id) throws ServiceException;

    CompletableFuture<Void> deleteAllGradesStudent(long id);

    CompletableFuture<Void> deleteAllGradesProblem(long id);

    CompletableFuture<Student> studentHighestGPA() throws ServiceException;

    CompletableFuture<ArrayList<Problem>> gradingInProgressProblem() throws ServiceException;

    CompletableFuture<ArrayList<Grade>> sortGradesByValue();

    CompletableFuture<Void> giveGradeToStudent(Grade grade) throws ServiceException;
}
