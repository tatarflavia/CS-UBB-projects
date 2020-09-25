import container.Message;
import container.Pair;
import domain.Grade;
import domain.Problem;
import domain.Student;
import domain.validator.GradeValidator;
import domain.validator.ProblemValidator;
import domain.validator.StudentValidator;
import domain.validator.Validator;
import exception.ServiceException;
import repository.AssignmentDBRepository;
import repository.ProblemDBRepository;
import repository.SortingRepository;
import repository.StudentDBRepository;
import service.AssignmentService;
import service.ProblemService;
import service.StudentService;
import tcp.TcpServer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ServerMain {

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(
                    Runtime.getRuntime().availableProcessors()
            );
            Validator<Problem> problemValidator = new ProblemValidator();
            SortingRepository<Long,Problem> problemRepository=new ProblemDBRepository();
            ProblemService problemService = new ProblemService(problemRepository, problemValidator,executorService);
            Validator<Student> studentValidator = new StudentValidator();
            SortingRepository<Long,Student> studentRepository=new StudentDBRepository();
            StudentService studentService = new StudentService(studentRepository, studentValidator,executorService);
            GradeValidator gradeValidator=new GradeValidator();
            SortingRepository<Pair<Long,Long>, Grade> gradeRepository=new AssignmentDBRepository();
            AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService,executorService);
            TcpServer tcpServer = new TcpServer(executorService);

            tcpServer.addHandler(StudentService.ADD_STUDENT, request -> {
                try {
                    Student student = (Student) request.getBody();
                    studentService.addStudent(student).get();
                    Message message = new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                } catch (InterruptedException | ExecutionException e) {
                    Message message = new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }
            });

            tcpServer.addHandler(studentService.UPDATE_STUDENT,request -> {
                try {
                    Student student=(Student) request.getBody();
                    studentService.updateStudent(student).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }
            });

            tcpServer.addHandler(studentService.DELETE_STUDENT,request -> {
                try {
                    Long studentID=(Long) request.getBody();
                    studentService.deleteStudent(studentID).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }
            });

            tcpServer.addHandler(studentService.GET_ALL_STUDENTS,request -> {
                try {
                    CompletableFuture<ArrayList<Student>> students=studentService.getAllStudents();
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(students.get());
                    return message;
                } catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not retrieve students."));
                    return message;
                }
            });

            tcpServer.addHandler(studentService.SORT_STUDENTS_NAME_ID,request -> {
                try {
                    CompletableFuture<ArrayList<Student>> students=studentService.sortStudentsByNameID();
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(students.get());
                    return message;
                } catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not retrieve sorted students."));
                    return message;
                }
            });

            tcpServer.addHandler(problemService.ADD_PROBLEM, request -> {
                try {
                    Problem problem=(Problem) request.getBody();
                    problemService.addProblem(problem).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }
            });

            tcpServer.addHandler(problemService.DELETE_PROBLEM, request -> {
                try {
                    Long problemID=(Long) request.getBody();
                    problemService.deleteProblem(problemID).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }
            });

            tcpServer.addHandler(problemService.UPDATE_PROBLEM, request -> {
                try {
                    Problem problem=(Problem) request.getBody();
                    problemService.updateProblem(problem).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }
            });

            tcpServer.addHandler(problemService.GET_PROBLEMS, request -> {
                try {
                    CompletableFuture<ArrayList<Problem>> problems=problemService.getAllProblems();
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(problems.get());
                    return message;
                } catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not retrieve problems."));
                    return message;
                }
            });

            tcpServer.addHandler(problemService.FILTER_PROBLEMS_CHAPTER, request -> {
                try {
                    String chapter= (String) request.getBody();
                    CompletableFuture<ArrayList<Problem>> problems=problemService.filterProblemsByChapter(chapter);
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(problems.get());
                    return message;
                } catch (InterruptedException | ExecutionException e) {
                    Message message = new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not retrieve problems filtered by chapter."));
                    return message;
                }
            });

            tcpServer.addHandler(problemService.SORT_PROBLEMS_CHAPTER_NUMBER_ID, request -> {
                try {
                    CompletableFuture<ArrayList<Problem>> problems=problemService.sortProblemsByChapterNumberID();
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(problems.get());
                    return message;
                } catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not retrieve sorted problems."));
                    return message;
                }
            });

            tcpServer.addHandler(assignmentService.ASSIGN_PROBLEM,request->{
                try{
                    Grade grade=(Grade) request.getBody();
                    assignmentService.assignProblemToStudent(grade).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e) {
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }
            });

            tcpServer.addHandler(assignmentService.SHOW_GRADED,request->{
                try{
                    CompletableFuture<ArrayList<Grade>> grades=assignmentService.showAllGraded();
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(grades.get());
                    return message;
                }
                catch (InterruptedException | ExecutionException e){
                    Message message=new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not retrieve graded assignments."));
                    return message;
                }
            });

            tcpServer.addHandler(assignmentService.SHOW_UNGRADED,request->{
                try{
                    CompletableFuture<ArrayList<Grade>> grades=assignmentService.showAllUngraded();
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(grades.get());
                    return message;
                }
                catch (InterruptedException | ExecutionException e){
                    Message message=new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not retrieve ungraded assignments."));
                    return message;
                }
            });

            tcpServer.addHandler(assignmentService.GIVE_GRADE,request->{
                try{
                    Grade grade=(Grade) request.getBody();
                    assignmentService.giveGradeToStudent(grade).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e){
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }
            });

            tcpServer.addHandler(assignmentService.DELETE_GRADE,request->{
                try{
                    Pair<Long,Long> gradeID=(Pair<Long,Long>)request.getBody();
                    assignmentService.deleteGrade(gradeID).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e)
                {
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }
            });

            tcpServer.addHandler(assignmentService.DELETE_GRADES_PROBLEM,request->{
                try{
                    Long id=(Long)request.getBody();
                    assignmentService.deleteAllGradesProblem(id).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e){
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }

            });

            tcpServer.addHandler(assignmentService.DELETE_GRADES_STUDENT,request->{
                try{
                    Long id=(Long)request.getBody();
                    assignmentService.deleteAllGradesStudent(id).get();
                    Message message=new Message();
                    message.setSuccess();
                    message.setNoParameter();
                    return message;
                }
                catch (InterruptedException | ExecutionException e){
                    Message message=new Message();
                    message.setError();
                    message.setBody(e.getCause());
                    return message;
                }

            });

            tcpServer.addHandler(assignmentService.STUDENT_HIGHEST_GPA,request->{
                try{
                    CompletableFuture<Student> student=assignmentService.studentHighestGPA();
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(student.get());
                    return message;
                }
                catch (InterruptedException | ExecutionException e) {
                    Message message = new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not retrieve student with highest gpa."));
                    return message;
                }
            });

            tcpServer.addHandler(assignmentService.GRADING_IN_PROGRESS,request->{
                try{
                    CompletableFuture<ArrayList<Problem>> problems=assignmentService.gradingInProgressProblem();
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(problems.get());
                    return message;
                }
                catch (InterruptedException | ExecutionException e){
                    Message message = new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not retrieve problems that are still in grading process."));
                    return message;
                }
            });

            tcpServer.addHandler(assignmentService.SORT_GRADES,request->{
                try{
                    CompletableFuture<ArrayList<Grade>> grades=assignmentService.sortGradesByValue();
                    Message message=new Message();
                    message.setSuccess();
                    message.setBody(grades.get());
                    return message;
                }
                catch (InterruptedException | ExecutionException e) {
                    Message message = new Message();
                    message.setError();
                    message.setBody(new ServiceException("Could not sort grades by value."));
                    return message;
                }
            });

            tcpServer.startServer();

            executorService.shutdown();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

    }
}