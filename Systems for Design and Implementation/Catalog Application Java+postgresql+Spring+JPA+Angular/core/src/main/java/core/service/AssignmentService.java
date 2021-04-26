package core.service;

import core.exception.IDException;
import core.exception.NoGradeException;
import core.exception.ServiceException;
import core.model.Grade;
import core.model.Pair;
import core.model.Problem;
import core.model.Student;
import core.model.validator.Validator;
import core.repository.AssignmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Handles the communication between the repository containing the grades and the console layer.
 */
@Service
public class AssignmentService implements AssignmentServiceInterface {
    private static Logger LOGGER = LoggerFactory.getLogger(AssignmentService.class);

    @Autowired
    private Validator<Grade> validator;

    @Autowired
    private AssignmentRepository gradeRepository;

    @Autowired
    private ProblemServiceInterface problemService;

    @Autowired
    private StudentServiceInterface studentService;


    /**
     * Adds a grade to the grade repository.
     * @param grade grade entity that is to be added to the repository
     * @throws ServiceException if the problem id does not exits or if the student is does not exist or if the grade id is already in use
     */
    @Override
    public void assignProblemToStudent(Grade grade) throws ServiceException{
        LOGGER.info("addAssignment- method entered:");
        Optional<Problem> problem=problemService.findOne(grade.getProblemId());
        problem.orElseThrow(()-> new IDException("problem id must exist!"));
        Optional<Student> student=studentService.findOne(grade.getStudentId());
        student.orElseThrow(()->new IDException("Student id must exist!"));
        validator.validate(grade);
        Optional<Grade> assignment=gradeRepository.findById(grade.getId());
        assignment.ifPresent(s->
        {throw new IDException("grade id is already in use!");});
        gradeRepository.save(grade);
        LOGGER.info("addAssignment- method finished:");
    }



    /**
     * @return all assignments that were graded
     */
     @Override
     public List<Grade> showAllGraded() {
         Iterable<Grade> grades=gradeRepository.findAll();
         return StreamSupport.stream(grades.spliterator(),false)
                 .filter(grade -> grade.getGradeByTeacher()>=1)
                 .collect(Collectors.toList());
    }

    @Override
    public List<Grade> showAll(){
         return gradeRepository.findAll();
    }

    /**
     * @return all assignments that were not graded
     */
    @Override
    public List<Grade> showAllUngraded() {
        Iterable<Grade> grades=gradeRepository.findAll();
        List<Grade> filteredGrades= StreamSupport.stream(grades.spliterator(),false)
                .filter(grade -> grade.getGradeByTeacher()==-1)
                .collect(Collectors.toList());
        return filteredGrades;
    }

    /**
     * Updates a grade with the actual value of the evaluation of the student
     * @throws ServiceException if the grade id is not in the repository, if the problem id is not in the repository,
     * if the student id is not in the repository
     */
    @Transactional
    @Override
    public void giveGradeToStudent(Grade gradeToUpdateWith) throws ServiceException {
        LOGGER.info("updateGrade- method entered:");
        validator.validate(gradeToUpdateWith);
        Optional<Grade> grade=gradeRepository.findById(gradeToUpdateWith.getId());
        grade.orElseThrow(()->new IDException("Grade id is not in repository!"));
        Optional<Problem> problem=problemService.findOne(grade.get().getProblemId());
        problem.orElseThrow(()-> new IDException("problem id must exist!"));
        Optional<Student> student=studentService.findOne(grade.get().getStudentId());
        student.orElseThrow(()->new IDException("Student id must exist!"));
        grade.ifPresent(g->{
            g.setGradeByTeacher(gradeToUpdateWith.getGradeByTeacher());
        });
        LOGGER.info("updateGrade- method finished");
    }



    /**
     * Removes a grade entity from the grade repository.
     * @param studentID unique identifier of the student
     * @param problemID unique identifier of the problem
     * @throws ServiceException if the grade does not exist
     */
    @Override
    public void deleteGrade(Long studentID, Long problemID) throws ServiceException {
        LOGGER.info("deleteGrade- method entered:");
        Optional<Grade> deleted=gradeRepository.findById(new Pair<>(studentID,problemID));
        deleted.orElseThrow(()->new IDException("Grade id is not in the repository!"));
        gradeRepository.deleteById(new Pair<>(studentID,problemID));
        LOGGER.info("deleteGrade- method finished");
    }



    /**
     * Removes a grade entity from the grade repository.
     * @param id unique identifier of the grade consisting of a student and a problem id
     * @throws ServiceException if the grade does not exist
     */
    @Override
    public void deleteGrade(Pair<Long, Long> id) throws ServiceException {
        LOGGER.info("deleteGrade- method entered:");
        gradeRepository.deleteById(id);
        LOGGER.info("deleteGrade- method finished");
    }



    /**
     * Removes all the grades of the student that has the given ID.
     * @param id student identifier
     */
    @Override
    public void deleteAllGradesStudent(long id) {
        Iterable<Grade> grades=gradeRepository.findAll();
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade-> grade.getStudentId()==id)
                .forEach(grade-> {
                    deleteGrade(grade.getId());});
    }

    /**
     * Removes all the grades corresponding to the problem that has the given ID.
     * @param id problem identifier
     */
    @Override
    public void deleteAllGradesProblem(long id) {
        Iterable<Grade> grades=gradeRepository.findAll();
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade-> grade.getProblemId()==id)
                .forEach(grade-> deleteGrade(grade.getId()));
    }

    /**
     * @return the student with the highest GPA, counting only the graded assignments
     * @throws NoGradeException when there are no graded assignments
     */
    @Override
    public Student studentHighestGPA() throws NoGradeException {
        Iterable<Grade> grades=gradeRepository.findAll();
        HashMap<Long,Pair<Integer,Integer>> studentsGPA = new HashMap<>();
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade -> grade.getGradeByTeacher()>=1)
                .forEach(grade -> {
                    Long studentID=grade.getStudentId();
                    studentsGPA.computeIfPresent(studentID, (k,v) -> new Pair<>(v.first+grade.getGradeByTeacher(),v.second+1));
                    studentsGPA.computeIfAbsent(studentID, k -> new Pair<>(grade.getGradeByTeacher(),1));
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
    @Override
    public Set<Student> topStudentsWithMaxGradeOnAChapter(String chapter, int number)
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
        filteredGrades.removeIf(grade -> !(grade.getGradeByTeacher()==10 && finalFilteredProblemIDs.contains(grade.getProblemId())));
            //then get only the students id's from these grades
        Set<Long> studentsIDs=filteredGrades.stream().map(Grade::getStudentId).collect(Collectors.toSet());

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
    @Override
    public List<Problem> gradingInProgressProblem() throws NoGradeException {
        Iterable<Grade> grades=gradeRepository.findAll();
        HashMap<Long,Pair<Integer,Integer>> problems = new HashMap<>();
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade -> grade.getGradeByTeacher()==-1)
                .forEach(grade -> {
                    Long problemID=grade.getProblemId();
                    problems.computeIfPresent(problemID, (k,v) -> new Pair<>(v.first+1,v.second));
                    problems.computeIfAbsent(problemID, k -> new Pair<>(1,0));
                });
        StreamSupport.stream(grades.spliterator(),false)
                .filter(grade -> grade.getGradeByTeacher()>=1)
                .forEach(grade -> problems.computeIfPresent(grade.getProblemId(),
                        (k,v) -> new Pair<>(v.first,v.second+1)));
        return problems.entrySet().stream()
                .filter(problem -> {
                    Pair<Integer,Integer> counter=problem.getValue();
                    return counter.second>=1;
                }).map(problem -> problemService.findOne(problem.getKey()).
                        orElseThrow(()-> new NoGradeException("Students do not have grades yet.")))
                .collect(Collectors.toList());
    }

    /*@Override
    public Iterable<Grade> sortGradesByValue(){
        Sort sort=new Sort(Sort.Direction.ASC,"studentId")
                .and(new Sort(Sort.Direction.DESC,"gradeByTeacher"));
        return sort.sort(gradeRepository.findAll().stream()
                .map(grade -> (Object)grade)
                .collect(Collectors.toList()))
                .stream().map(grade->(Grade)grade)
                .collect(Collectors.toList());
    }*/


}
