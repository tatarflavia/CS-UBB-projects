package core.service;

import core.model.Grade;
import core.model.Pair;
import core.model.Problem;
import core.model.Student;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

public interface AssignmentServiceInterface {
    void assignProblemToStudent(Grade grade);

    List<Grade> showAllGraded();

    List<Grade> showAllUngraded();

    List<Grade> showAll();

    @Transactional
    void giveGradeToStudent(Grade updateGrade);

    void deleteGrade(Long studentID, Long problemID);

    void deleteGrade(Pair<Long, Long> id);

    void deleteAllGradesStudent(long id);

    void deleteAllGradesProblem(long id);

    Student studentHighestGPA();

    Set<Student> topStudentsWithMaxGradeOnAChapter(String chapter, int number);

    List<Problem> gradingInProgressProblem();

    //Iterable<Grade> sortGradesByValue();
}
