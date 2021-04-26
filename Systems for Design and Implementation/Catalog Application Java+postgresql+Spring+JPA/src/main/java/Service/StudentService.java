package Service;

import Domain.Student;
import Domain.Validators.Validator;
import Exception.DuplicateIDException;
import Exception.RejectedInputException;
import Repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles the communication between the repository containing the students and the console layer.
 */
@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private Validator<Student> validator;
    private static Logger LOGGER = LoggerFactory.getLogger(ProblemService.class);



    /**
     * Adds the student to the repository.
     * @param student student that can be assigned problems
     * @throws RejectedInputException if the student ID already exists or the validator decides that it is invalid
     */
    public void addStudent(Student student) throws RejectedInputException {
        LOGGER.info("addStudent- method entered:");
        validator.validate(student);
        Optional<Student> previous=repository.findById(student.getId());
        previous.ifPresent(s -> {
            throw new DuplicateIDException("The student ID is already in use.");
        });
        repository.save(student);
        LOGGER.info("addStudent- method finished");
    }

    /**
     * Updates a student's name using their ID.
     * @param student student with a unique ID
     * @throws RejectedInputException if the student's new name is invalid or the student isn't already in the repository
     */
    @Transactional
    public void updateStudent(Student student) throws RejectedInputException {
        LOGGER.info("updateStudent- method entered:");
        validator.validate(student);
        Optional<Student> repoValue=repository.findById(student.getId());
        //repoValue.orElseThrow(() -> new DuplicateIDException("The student does not exist."));
        repoValue.ifPresentOrElse(s->{s.setName(student.getName());},
                ()->{throw new DuplicateIDException("The student does not exist.");});
        LOGGER.info("updateStudent- method finished");
    }

    /**
     * @return list of all the students
     */
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    /**
     * Deletes a student based on their ID
     * @param id unique identifier
     */
    public void deleteStudent(long id) {
        LOGGER.info("deleteStudent- method entered:");
        Optional<Student> toBeDeleted=repository.findById(id);
        toBeDeleted.orElseThrow(() -> new RejectedInputException("Student does not exist."));
        repository.deleteById(id);
        LOGGER.info("deleteStudent- method finished");
    }

    public Optional<Student> findOne(Long id) {
        return repository.findById(id);
    }

    public Iterable<Student> sortStudentsByNameID() {
        Sort sort=new Sort(Sort.Direction.DESC,"name").and(new Sort("id"));
        return sort.sort(repository.findAll().stream()
                .map(student -> (Object) student)
                .collect(Collectors.toList()))
                .stream().map(student -> (Student) student)
                .collect(Collectors.toList());
    }
}
