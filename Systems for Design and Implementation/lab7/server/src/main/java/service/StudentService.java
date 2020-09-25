package service;

import domain.Student;
import domain.validator.Validator;
import exception.ServiceException;
import repository.Sort;
import repository.Sort.Direction;
import repository.SortingRepository;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Handles the communication between the repository containing the students and the console layer.
 */
public class StudentService implements StudentServiceInterface {

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
     * @throws ServiceException if the student ID already exists or the validator decides that it is invalid
     */
    @Override
    public void addStudent(Student student) throws ServiceException {
        validator.validate(student);
        repository.save(student);
    }

    /**
     * Updates a student's name using their ID.
     * @param student student with a unique ID
     * @throws ServiceException if the student's new name is invalid or the student isn't already in the repository
     */
    @Override
    public void updateStudent(Student student) throws ServiceException {
        validator.validate(student);
        repository.update(student);
    }

    @Override
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students=new ArrayList<>();
        repository.findAll().forEach(students::add);
        return students;
    }

    /**
     * Deletes a student based on their ID
     * @param id unique identifier
     */
    @Override
    public void deleteStudent(long id) throws ServiceException{
       repository.delete(id);
    }

    public Optional<Student> findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public ArrayList<Student> sortStudentsByNameID() {
        Sort sort = new Sort(Direction.DESC, "name").and(new Sort("id"));
        ArrayList<Student> students = new ArrayList<>();
        repository.findAll(sort).forEach(students::add);
        return students;
    }
}
