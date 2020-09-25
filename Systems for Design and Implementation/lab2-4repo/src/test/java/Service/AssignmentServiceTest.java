package Service;

import Container.Pair;
import Domain.Grade;
import Domain.Problem;
import Domain.Student;
import Domain.Validators.GradeValidator;
import Domain.Validators.ProblemValidator;
import Domain.Validators.StudentValidator;
import Domain.Validators.Validator;
import Repository.InMemoryRepository;
import Repository.Repository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class AssignmentServiceTest {

    @Test
    public void assignProblemToStudent() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,3L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));

        List<Grade> grades=assignmentService.showAllUngraded();
        Assert.assertEquals(grades.size(),4);
        Assert.assertTrue(grades.contains(new Grade(1L,1L)));
        Assert.assertTrue(grades.contains(new Grade(1L,2L)));
        Assert.assertTrue(grades.contains(new Grade(2L,3L)));
        Assert.assertTrue(grades.contains(new Grade(3L,2L)));
    }

    @Test
    public void showAllGraded() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,3L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));
        assignmentService.giveGradeToStudent(1L,1L,4);
        assignmentService.giveGradeToStudent(2L,3L,8);

        List<Grade> graded=assignmentService.showAllGraded();
        Assert.assertEquals(graded.size(),2);
        Assert.assertTrue(graded.contains(new Grade(1L,1L)));
        Assert.assertTrue(graded.contains(new Grade(2L,3L)));
    }

    @Test
    public void showAllUngraded() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,3L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));
        assignmentService.giveGradeToStudent(1L,1L,4);
        assignmentService.giveGradeToStudent(2L,3L,8);

        List<Grade> graded=assignmentService.showAllUngraded();
        Assert.assertEquals(graded.size(),2);
        Assert.assertTrue(graded.contains(new Grade(1L,2L)));
        Assert.assertTrue(graded.contains(new Grade(3L,2L)));
    }

    @Test
    public void giveGradeToStudent() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,3L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));
        assignmentService.giveGradeToStudent(1L,1L,4);
        assignmentService.giveGradeToStudent(2L,3L,8);

        List<Grade> graded=assignmentService.showAllGraded();
        Assert.assertEquals(graded.size(),2);
        Assert.assertTrue(graded.contains(new Grade(1L,1L)));
        Assert.assertTrue(graded.contains(new Grade(2L,3L)));
    }

    @Test
    public void deleteGrade() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,3L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));
        assignmentService.giveGradeToStudent(1L,1L,4);
        assignmentService.giveGradeToStudent(2L,3L,8);
        assignmentService.deleteGrade(1L,1L);
        assignmentService.deleteGrade(3L,2L);

        List<Grade> graded=assignmentService.showAllGraded();
        Assert.assertEquals(graded.size(),1);
        List<Grade> ungraded=assignmentService.showAllUngraded();
        Assert.assertEquals(ungraded.size(),1);
        Assert.assertFalse(graded.contains(new Grade(1L,1L)));
        Assert.assertTrue(graded.contains(new Grade(2L,3L)));
    }

    @Test
    public void deleteAllGradesStudent() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,3L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));
        assignmentService.giveGradeToStudent(1L,1L,4);
        assignmentService.giveGradeToStudent(2L,3L,8);
        assignmentService.deleteAllGradesStudent(1L);

        List<Grade> graded=assignmentService.showAllGraded();
        List<Grade> ungraded=assignmentService.showAllUngraded();
        Assert.assertEquals(graded.size(),1);
        Assert.assertEquals(ungraded.size(),1);
    }

    @Test
    public void deleteAllGradesProblem() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,3L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));
        assignmentService.giveGradeToStudent(1L,1L,4);
        assignmentService.giveGradeToStudent(2L,3L,8);
        assignmentService.deleteAllGradesProblem(2L);

        List<Grade> graded=assignmentService.showAllGraded();
        List<Grade> ungraded=assignmentService.showAllUngraded();
        Assert.assertEquals(graded.size(),2);
        Assert.assertEquals(ungraded.size(),0);
    }

    @Test
    public void studentHighestGPA() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,3L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));
        assignmentService.giveGradeToStudent(1L,1L,4);
        assignmentService.giveGradeToStudent(2L,3L,8);

        Assert.assertTrue(assignmentService.studentHighestGPA().getName().equals("Name Name"));
    }

    @Test
    public void gradingInProgressProblem() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another chapter","text",3));
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,3L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));
        assignmentService.giveGradeToStudent(1L,1L,4);
        assignmentService.giveGradeToStudent(3L,2L,8);

        List<Problem> progressGrading=assignmentService.gradingInProgressProblem();
        Assert.assertEquals(progressGrading.size(),1);
    }

    @Test
    public void topStudentsWithMaxGradeOnAChapter() {
        Validator<Grade> gradeValidator=new GradeValidator();
        Repository<Pair<Long,Long>, Grade> gradeRepository=new InMemoryRepository<Pair<Long,Long>,Grade>();
        Repository<Long, Problem> problemRepository=new InMemoryRepository<Long,Problem>();
        Repository<Long, Student> studentRepository=new InMemoryRepository<Long,Student>();
        problemRepository.save(new Problem(12,"chapter","problem text",1));
        problemRepository.save(new Problem(7,"chapter","text",2));
        problemRepository.save(new Problem(3,"another","text",3));
        Student student1=new Student("Student",1);
        Student student2=new Student("Name Name",2);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(new Student("First Last",3));
        Validator<Problem> problemValidator=new ProblemValidator();
        Validator<Student> studentValidator=new StudentValidator();
        ProblemService problemService=new ProblemService(problemRepository,problemValidator);
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
        assignmentService.assignProblemToStudent(new Grade(1L,1L));
        assignmentService.assignProblemToStudent(new Grade(1L,2L));
        assignmentService.assignProblemToStudent(new Grade(2L,2L));
        assignmentService.assignProblemToStudent(new Grade(3L,2L));
        assignmentService.giveGradeToStudent(1L,1L,10);
        assignmentService.giveGradeToStudent(2L,2L,10);

        Assert.assertEquals(assignmentService.topStudentsWithMaxGradeOnAChapter("chap",3).size(),2);
        Assert.assertTrue(assignmentService.topStudentsWithMaxGradeOnAChapter("chap",3).contains(student1));
        Assert.assertTrue(assignmentService.topStudentsWithMaxGradeOnAChapter("chap",3).contains(student2));

    }
}