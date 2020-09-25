package Service;

import Domain.Student;
import Domain.Validators.Validator;
import Exception.DuplicateIDException;
import Exception.RejectedInputException;
import Repository.Sort;
import Repository.Sort.Direction;
import Repository.SortingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles the communication between the repository containing the students and the console layer.
 */
public class StudentService {

    private SortingRepository<Long,Student> repository;
    private Validator<Student> validator;

    /**
     * Creates it with an empty repository.
     * @param repository empty repository to hold the students
     * @param validator student validator to decide if its fields are valid
     */
    public StudentService(SortingRepository<Long,Student> repository, Validator<Student> validator) {
        this.repository=repository;
        this.validator=validator;
    }

    /**
     * Adds the student to the repository.
     * @param student student that can be assigned problems
     * @throws RejectedInputException if the student ID already exists or the validator decides that it is invalid
     */
    public void addStudent(Student student) throws RejectedInputException {
        validator.validate(student);
        Optional<Student> previous=repository.save(student);
        previous.ifPresent(s -> {
            throw new DuplicateIDException("The student ID is already in use.");
        });
    }

    /**
     * Updates a student's name using their ID.
     * @param student student with a unique ID
     * @throws RejectedInputException if the student's new name is invalid or the student isn't already in the repository
     */
    public void updateStudent(Student student) throws RejectedInputException {
        validator.validate(student);
        Optional<Student> savedValue=repository.update(student);
        savedValue.orElseThrow(() -> new DuplicateIDException("The student does not exist."));
    }

    /**
     * @return list of all the students
     */
    public List<Student> getAllStudents() {
        List<Student> students=new ArrayList<>();
        repository.findAll().forEach(students::add);
        return students;
    }

    /**
     * Deletes a student based on their ID
     * @param id unique identifier
     */
    public void deleteStudent(long id) {
        Optional<Student> deleted=repository.delete(id);
        deleted.orElseThrow(() -> new RejectedInputException("Student does not exist."));
    }

    public Optional<Student> findOne(Long id) {
        return repository.findOne(id);
    }

    public Iterable<Student> sortStudentsByNameID() {
        Sort sort=new Sort(Direction.DESC,"name").and(new Sort("id"));
        return repository.findAll(sort);
    }
}
