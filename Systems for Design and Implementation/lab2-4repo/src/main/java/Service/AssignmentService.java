package Service;


import Container.Pair;
import Domain.Grade;
import Domain.Problem;
import Domain.Student;
import Domain.Validators.Validator;
import Exception.DuplicateIDException;
import Exception.RejectedInputException;
import Repository.Sort;
import Repository.SortingRepository;
import Exception.NoGradeException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Handles the communication between the repository containing the grades and the console layer.
 */
public class AssignmentService {
    private Validator<Grade> validator;
    private SortingRepository<Pair<Long,Long>,Grade> gradeRepository;
    //private Repository<Long, Problem>  problemRepository;
    //private Repository<Long, Student> studentRepository;
    private ProblemService problemService;
    private StudentService studentService;

    /**
     * Creates the Assignment Service with 3 repositories.
     * @param validator gradeValidator to check if the fields are valid
     * @param gradeRepository empty repository where grades are kept
     */
    public AssignmentService(Validator<Grade> validator, SortingRepository<Pair<Long,Long>, Grade> gradeRepository, ProblemService problemService,StudentService studentService) {
        this.validator = validator;
        this.gradeRepository = gradeRepository;
        this.problemService=problemService;
        this.studentService=studentService;
    }

    /**
     * Adds a grade to the grade repository.
     * @param grade grade entity that is to be added to the repository
     * @throws RejectedInputException if the problem id does not exits or if the student is does not exist or if the grade id is already in use
     */
    public void assignProblemToStudent(Grade grade) throws RejectedInputException{
        Optional<Problem> problem=problemService.findOne(grade.getIdOfTheProblem());
        problem.orElseThrow(()-> new RejectedInputException("problem id must exist!"));
        Optional<Student> student=studentService.findOne(grade.getIdOfTheStudent());
        student.orElseThrow(()->new RejectedInputException("Student id must exist!"));
        validator.validate(grade);
        Optional<Grade> assignment=gradeRepository.save(grade);
        assignment.ifPresent(s->
        {throw new DuplicateIDException("grade id is already in use!");});
    }

    /**
     * @return all assignments that were graded
     */
     public List<Grade> showAllGraded() {
         Iterable<Grade> grades=gradeRepository.findAll();
         List<Grade> filteredGrades= StreamSupport.stream(grades.spliterator(),false)
                 .filter(grade -> grade.getGradeGivenByTeacher()>=1)
                 .collect(Collectors.toList());
         return filteredGrades;
    }

    /**
     * @return all assignments that were not graded
     */
    public List<Grade> showAllUngraded() {
        Iterable<Grade> grades=gradeRepository.findAll();
        List<Grade> filteredGrades= StreamSupport.stream(grades.spliterator(),false)
                .filter(grade -> grade.getGradeGivenByTeacher()==-1)
                .collect(Collectors.toList());
        return filteredGrades;
    }

    /**
     * Updates a grade with the actual value of the evaluation of the student

     * @throws RejectedInputException if the grade id is not in the repository, if the problem id is not in the repository,
     * if the student id is not in the repository
     */
    public void giveGradeToStudent(Grade gradeToUpdateWith) throws RejectedInputException {
        Optional<Grade> grade=gradeRepository.findOne(new Pair(gradeToUpdateWith.getIdOfTheStudent(),gradeToUpdateWith.getIdOfTheProblem()));
        grade.orElseThrow(()->new RejectedInputException("Grade id is not in repository!"));
        Optional<Problem> problem=problemService.findOne(grade.get().getIdOfTheProblem());
        problem.orElseThrow(()-> new RejectedInputException("problem id must exist!"));
        Optional<Student> student=studentService.findOne(grade.get().getIdOfTheStudent());
        student.orElseThrow(()->new RejectedInputException("Student id must exist!"));
        validator.validate(gradeToUpdateWith);
        gradeRepository.update(gradeToUpdateWith);
    }

    /**
     * Removes a grade entity from the grade repository.
     * @param studentID unique identifier of the student
     * @param problemID unique identifier of the problem
     * @throws RejectedInputException if the grade does not exist
     */
    public void deleteGrade(Long studentID, Long problemID) throws RejectedInputException {
        Optional<Grade> deleted=gradeRepository.delete(new Pair(studentID,problemID));
        deleted.orElseThrow(()->new RejectedInputException("Grade id is not in the repository!"));
    }

    /**
     * Removes a grade entity from the grade repository.
     * @param id unique identifier of the grade consisting of a student and a problem id
     * @throws RejectedInputException if the grade does not exist
     */
    public void deleteGrade(Pair<Long,Long> id) throws RejectedInputException {
        Optional<Grade> deleted=gradeRepository.delete(id);
        deleted.orElseThrow(()->new RejectedInputException("Grade id is not in the repository!"));
    }

    /**
     * Removes all the grades of the student that has the given ID.
     * @param id student identifier
     */
    public void deleteAllGradesStudent(long id) {
        Iterable<Grade> grades=gradeRepository.findAll();
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade-> grade.getIdOfTheStudent()==id)
                .forEach(grade-> {
                    deleteGrade(grade.getId());});
    }

    /**
     * Removes all the grades corresponding to the problem that has the given ID.
     * @param id problem identifier
     */
    public void deleteAllGradesProblem(long id) {
        Iterable<Grade> grades=gradeRepository.findAll();
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade-> grade.getIdOfTheProblem()==id)
                .forEach(grade-> deleteGrade(grade.getId()));
    }

    /**
     * @return the student with the highest GPA, counting only the graded assignments
     * @throws NoGradeException when there are no graded assignments
     */
    public Student studentHighestGPA() throws NoGradeException{
        Iterable<Grade> grades=gradeRepository.findAll();
        HashMap<Long,Pair<Integer,Integer>> studentsGPA = new HashMap<>();
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade -> grade.getGradeGivenByTeacher()>=1)
                .forEach(grade -> {
                    Long studentID=grade.getIdOfTheStudent();
                    studentsGPA.computeIfPresent(studentID, (k,v) -> new Pair(v.first+grade.getGradeGivenByTeacher(),v.second+1));
                    studentsGPA.computeIfAbsent(studentID, k -> new Pair(grade.getGradeGivenByTeacher(),1));
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
    }

    /**
     *
     * @param chapter a string contained in the chapter asked for by the user
     * @param number a limit for the size of the set of the students that is returned
     * @return the set of number students that have gained a max grade on a chapter asked for
     */
    public Set<Student> topStudentsWithMaxGradeOnAChapter(String chapter,int number)
    {
        //first take the problems id's from the chapter asked for
        Iterable<Problem> problems=problemService.getAllProblems();
        Set<Problem> filteredProblems=new HashSet<>();
        problems.forEach(filteredProblems::add);
        filteredProblems.removeIf(problem -> !problem.getChapter().contains(chapter));
        Set<Long> filteredProblemIDs=new HashSet<>();
        filteredProblemIDs=filteredProblems.stream().map(Problem::getId).collect(Collectors.toSet());


        //then take the students id's that meet the criteria
            //first check the problem id's in every grade and that the grade is maximum
        Iterable<Grade> grades=gradeRepository.findAll();
        Set<Grade> filteredGrades=new HashSet<>();
        grades.forEach(filteredGrades::add);
        Set<Long> finalFilteredProblemIDs = filteredProblemIDs;
        filteredGrades.removeIf(grade -> !(grade.getGradeGivenByTeacher()==10 && finalFilteredProblemIDs.contains(grade.getIdOfTheProblem())));
            //then get only the students id's from these grades
        Set<Long> studentsIDs=filteredGrades.stream().map(Grade::getIdOfTheStudent).collect(Collectors.toSet());

        //then we take the students from these ids
        Iterable<Student> students=studentService.getAllStudents();
        Set<Student> studentSet=new HashSet<>();
        students.forEach(studentSet::add);
        studentSet=studentSet.stream().filter(student -> studentsIDs.contains(student.getId())).collect(Collectors.toSet());
        studentSet=studentSet.stream().limit(number).collect(Collectors.toSet());
        return studentSet;
    }

    /**
     * @return all assignments that have both graded and ungraded instances
     * aka their grading is in progress
     */
    public List<Problem> gradingInProgressProblem() throws NoGradeException {
        Iterable<Grade> grades=gradeRepository.findAll();
        HashMap<Long,Pair<Integer,Integer>> problems = new HashMap<>();
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade -> grade.getGradeGivenByTeacher()==-1)
                .forEach(grade -> {
                    Long problemID=grade.getIdOfTheProblem();
                    problems.computeIfPresent(problemID, (k,v) -> new Pair(v.first+1,v.second));
                    problems.computeIfAbsent(problemID, k -> new Pair(1,0));
                });
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade -> grade.getGradeGivenByTeacher()>=1)
                .forEach(grade -> problems.computeIfPresent(grade.getIdOfTheProblem(),
                        (k,v) -> new Pair(v.first,v.second+1)));
        return problems.entrySet().stream()
                .filter(problem -> {
                    Pair<Integer,Integer> counter=problem.getValue();
                    return counter.second>=1;
                }).map(problem -> problemService.findOne(problem.getKey()).orElseThrow(()-> new NoGradeException("Students do not have grades yet.")))
                .collect(Collectors.toList());
    }

    public Iterable<Grade> sortGradesByValue(){
        Sort sort=new Sort(Sort.Direction.ASC,"student_id").and(new Sort(Sort.Direction.DESC,"gradeByTeacher"));
        return gradeRepository.findAll(sort);
    }


}
