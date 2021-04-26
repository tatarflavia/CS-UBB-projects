package core.service;

import core.exception.IDException;
import core.exception.ServiceException;
import core.model.Student;
import core.model.validator.Validator;
import core.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles the communication between the repository containing the students and the console layer.
 */
@Service
public class StudentService implements StudentServiceInterface {
    private static Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository repository;

    @Autowired
    private Validator<Student> validator;


    /**
     * Adds the student to the repository.
     * @param student student that can be assigned problems
     * @throws ServiceException if the student ID already exists or the validator decides that it is invalid
     */
    @Override
    public void addStudent(Student student) throws ServiceException {
        LOGGER.info("addStudent- method entered:");
        validator.validate(student);
        Optional<Student> previous=repository.findById(student.getId());
        previous.ifPresent(s -> {
            throw new IDException("The student ID is already in use.");
        });
        repository.save(student);
        LOGGER.info("addStudent- method finished");
    }

    /**
     * Updates a student's name using their ID.
     * @param student student with a unique ID
     * @throws ServiceException if the student's new name is invalid or the student isn't already in the repository
     */
    @Override
    @Transactional
    public void updateStudent(Student student) throws ServiceException {
        LOGGER.info("updateStudent- method entered:");
        validator.validate(student);
        Optional<Student> repoValue=repository.findById(student.getId());
        //repoValue.orElseThrow(() -> new DuplicateIDException("The student does not exist."));
        repoValue.ifPresentOrElse(s->{s.setName(student.getName());},
                ()->{throw new IDException("The student does not exist.");});
        LOGGER.info("updateStudent- method finished");
    }

    /**
     * @return list of all the students
     */
    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    /**
     * Deletes a student based on their ID
     * @param id unique identifier
     */
    @Override
    public void deleteStudent(long id) throws ServiceException{
        LOGGER.info("deleteStudent- method entered:");
        Optional<Student> toBeDeleted=repository.findById(id);
        toBeDeleted.orElseThrow(() -> new IDException("Student does not exist."));
        repository.deleteById(id);
        LOGGER.info("deleteStudent- method finished");
    }

    @Override
    public Optional<Student> findOne(Long id) {
        return repository.findById(id);
    }

    /*@Override
    public Iterable<Student> sortStudentsByNameID() {
        Sort sort=new Sort(Sort.Direction.DESC,"name").and(new Sort("id"));
        return sort.sort(repository.findAll().stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(Student)s)
                .collect(Collectors.toList());
    }*/
}
