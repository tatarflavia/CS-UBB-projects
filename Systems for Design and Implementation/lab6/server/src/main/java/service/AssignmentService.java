package service;


import container.Pair;
import domain.Grade;
import domain.Problem;
import domain.Student;
import domain.validator.Validator;
import exception.DuplicateIDException;
import exception.ServiceException;
import repository.Sort;
import repository.SortingRepository;
import exception.NoGradeException;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Handles the communication between the repository containing the grades and the console layer.
 */
public class AssignmentService implements AssignmentServiceInterface {
    private Validator<Grade> validator;
    private SortingRepository<Pair<Long,Long>,Grade> gradeRepository;
    private ProblemService problemService;
    private StudentService studentService;
    private ExecutorService executorService;

    /**
     * Creates the Assignment Service with 3 repositories.
     * @param validator gradeValidator to check if the fields are valid
     * @param gradeRepository empty repository where grades are kept
     */
    public AssignmentService(Validator<Grade> validator, SortingRepository<Pair<Long,Long>, Grade> gradeRepository, ProblemService problemService,StudentService studentService,ExecutorService executorService) {
        this.validator = validator;
        this.gradeRepository = gradeRepository;
        this.problemService=problemService;
        this.studentService=studentService;
        this.executorService=executorService;
    }

    /**
     * Adds a grade to the grade repository.
     * @param grade grade entity that is to be added to the repository
     * @throws ServiceException if the problem id does not exits or if the student is does not exist or if the grade id is already in use
     */
    @Override
    public CompletableFuture<Void> assignProblemToStudent(Grade grade) throws ServiceException {
        return CompletableFuture.runAsync(()->{
            Optional<Problem> problem=problemService.findOne(grade.getIdOfTheProblem());
            problem.orElseThrow(()-> new ServiceException("problem id must exist!"));
            Optional<Student> student=studentService.findOne(grade.getIdOfTheStudent());
            student.orElseThrow(()->new ServiceException("Student id must exist!"));
            validator.validate(grade);
            Optional<Grade> assignment=gradeRepository.save(grade);
            assignment.ifPresent(s->
            {throw new DuplicateIDException("Grade id is already in use!");});
        },executorService);

    }


    /**
     * @return all assignments that were graded
     */
     @Override
     public CompletableFuture<ArrayList<Grade>> showAllGraded() {
         return CompletableFuture.supplyAsync(()->{
             Iterable<Grade> grades=gradeRepository.findAll();
             ArrayList<Grade> filteredGrades= StreamSupport.stream(grades.spliterator(),false)
                     .filter(grade -> grade.getGradeGivenByTeacher()>=1)
                     .collect(Collectors.toCollection(ArrayList::new));
             return filteredGrades;
         },executorService);

    }

    /**
     * @return all assignments that were not graded
     */
    @Override
    public CompletableFuture<ArrayList<Grade>> showAllUngraded() {
        return CompletableFuture.supplyAsync(()->{
            Iterable<Grade> grades=gradeRepository.findAll();
            ArrayList<Grade> filteredGrades= StreamSupport.stream(grades.spliterator(),false)
                    .filter(grade -> grade.getGradeGivenByTeacher()==-1)
                    .collect(Collectors.toCollection(ArrayList::new));
            return filteredGrades;
        },executorService);

    }

    /**
     * Updates a grade with the actual value of the evaluation of the student
     * @throws ServiceException if the grade id is not in the repository, if the problem id is not in the repository,
     * if the student id is not in the repository
     */
    @Override
    public CompletableFuture<Void> giveGradeToStudent(Grade grade) throws ServiceException {
        return CompletableFuture.runAsync(()->{
            long studentID=grade.getIdOfTheStudent();
            long problemID=grade.getIdOfTheProblem();
            int gradeValue=grade.getGradeGivenByTeacher();
            Optional<Grade> previousGrade=gradeRepository.findOne(new Pair<>(studentID,problemID));
            previousGrade.orElseThrow(()->new ServiceException("Grade id is not in repository!"));
            Optional<Problem> problem=problemService.findOne(previousGrade.get().getIdOfTheProblem());
            problem.orElseThrow(()-> new ServiceException("problem id must exist!"));
            Optional<Student> student=studentService.findOne(previousGrade.get().getIdOfTheStudent());
            student.orElseThrow(()->new ServiceException("Student id must exist!"));
            Grade toBeUpdated=new Grade(student.get().getId(),problem.get().getId());
            toBeUpdated.setGradeGivenByTeacher(gradeValue);
            validator.validate(toBeUpdated);
            gradeRepository.update(toBeUpdated);
        },executorService);

    }

    /**
     * Removes a grade entity from the grade repository.
     * @param id unique identifier of the grade consisting of a student and a problem id
     * @throws ServiceException if the grade does not exist
     */
    @Override
    public CompletableFuture<Void> deleteGrade(Pair<Long, Long> id) throws ServiceException {
        return CompletableFuture.runAsync(()->{
            Optional<Grade> deleted=gradeRepository.delete(id);
            deleted.orElseThrow(()->new ServiceException("Grade id is not in the repository!"));
        },executorService);

    }

    /**
     * Removes all the grades of the student that has the given ID.
     * @param id student identifier
     */
    @Override
    public CompletableFuture<Void> deleteAllGradesStudent(long id) {
        return CompletableFuture.runAsync(()->{
            Iterable<Grade> grades=gradeRepository.findAll();
            StreamSupport.stream(grades.spliterator(),false)
                    .filter(grade-> grade.getIdOfTheStudent()==id)
                    .forEach(grade-> {
                        deleteGrade(grade.getId());});
        },executorService);

    }

    /**
     * Removes all the grades corresponding to the problem that has the given ID.
     * @param id problem identifier
     */
    @Override
    public CompletableFuture<Void> deleteAllGradesProblem(long id) {
        return CompletableFuture.runAsync(()->{
            Iterable<Grade> grades=gradeRepository.findAll();
            StreamSupport.stream(grades.spliterator(),false)
                    .filter(grade-> grade.getIdOfTheProblem()==id)
                    .forEach(grade-> deleteGrade(grade.getId()));
        },executorService);

    }

    /**
     * @return the student with the highest GPA, counting only the graded assignments
     * @throws NoGradeException when there are no graded assignments
     */
    @Override
    public CompletableFuture<Student> studentHighestGPA() throws NoGradeException{
        return CompletableFuture.supplyAsync(()->{
            Iterable<Grade> grades=gradeRepository.findAll();
            HashMap<Long,Pair<Integer,Integer>> studentsGPA = new HashMap<>();
            StreamSupport.stream(grades.spliterator(),false)
                    .filter(grade -> grade.getGradeGivenByTeacher()>=1)
                    .forEach(grade -> {
                        Long studentID=grade.getIdOfTheStudent();
                        studentsGPA.computeIfPresent(studentID, (k,v) -> new Pair<>(v.first+grade.getGradeGivenByTeacher(),v.second+1));
                        studentsGPA.computeIfAbsent(studentID, k -> new Pair<>(grade.getGradeGivenByTeacher(),1));
                    });
            try {
                Long studentID = Collections.max(studentsGPA.entrySet(), (student1, student2) -> {
                    Float gpa1 = (float) student1.getValue().first / student1.getValue().second;
                    Float gpa2 = (float) student2.getValue().first / student2.getValue().second;
                    return gpa1.compareTo(gpa2);
                }).getKey();
                return studentService.findOne(studentID).orElseThrow(()-> new NoGradeException("Students do not have grades yet."));
            }
            catch (NoSuchElementException e) {
                throw new NoGradeException("Students do not have grades yet.");
            }
        },executorService);

    }


    /**
     * @return all assignments that have both graded and ungraded instances
     * aka their grading is in progress
     */
    @Override
    public CompletableFuture<ArrayList<Problem>> gradingInProgressProblem() throws NoGradeException {
        return CompletableFuture.supplyAsync(()->{
            Iterable<Grade> grades=gradeRepository.findAll();
            HashMap<Long,Pair<Integer,Integer>> problems = new HashMap<>();
            StreamSupport.stream(grades.spliterator(),false)
                    .filter(grade -> grade.getGradeGivenByTeacher()==-1)
                    .forEach(grade -> {
                        Long problemID=grade.getIdOfTheProblem();
                        problems.computeIfPresent(problemID, (k,v) -> new Pair<>(v.first+1,v.second));
                        problems.computeIfAbsent(problemID, k -> new Pair<>(1,0));
                    });
            StreamSupport.stream(grades.spliterator(),false)
                    .filter(grade -> grade.getGradeGivenByTeacher()>=1)
                    .forEach(grade -> problems.computeIfPresent(grade.getIdOfTheProblem(),
                            (k,v) -> new Pair<>(v.first,v.second+1)));
            return problems.entrySet().stream()
                    .filter(problem -> {
                        Pair<Integer,Integer> counter=problem.getValue();
                        return counter.second>=1;
                    }).map(problem -> problemService.findOne(problem.getKey()).orElseThrow(()-> new NoGradeException("Students do not have grades yet.")))
                    .collect(Collectors.toCollection(ArrayList::new));
        },executorService);
    }

    @Override
    public CompletableFuture<ArrayList<Grade>> sortGradesByValue(){
        return CompletableFuture.supplyAsync(()->{
            Sort sort=new Sort(Sort.Direction.ASC,"student_id").and(new Sort(Sort.Direction.DESC,"gradeByTeacher"));
            Iterable<Grade> sortedGrades=gradeRepository.findAll(sort);
            ArrayList<Grade> result=StreamSupport.stream(sortedGrades.spliterator(),false).collect(Collectors.toCollection(ArrayList::new));
            return result;
        },executorService);

    }



}
