package service;

import domain.Student;
import domain.validator.Validator;
import exception.DuplicateIDException;
import exception.ServiceException;
import repository.Sort;
import repository.Sort.Direction;
import repository.SortingRepository;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Handles the communication between the repository containing the students and the console layer.
 */
public class StudentService implements StudentServiceInterface {

    private SortingRepository<Long,Student> repository;
    private Validator<Student> validator;
    private ExecutorService executorService;

    /**
     * Creates it with an empty repository.
     * @param repository empty repository to hold the students
     * @param validator student validator to decide if its fields are valid
     */
    public StudentService(SortingRepository<Long,Student> repository, Validator<Student> validator, ExecutorService executorService) {
        this.repository=repository;
        this.validator=validator;
        this.executorService=executorService;
    }

    /**
     * Adds the student to the repository.
     * @param student student that can be assigned problems
     * @throws ServiceException if the student ID already exists or the validator decides that it is invalid
     */
    @Override
    public CompletableFuture<Void> addStudent(Student student) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            validator.validate(student);
            Optional<Student> previous=repository.save(student);
            previous.ifPresent(s ->{
                throw new DuplicateIDException("The student ID is already in use.");
            });
        },executorService);
    }

    /**
     * Updates a student's name using their ID.
     * @param student student with a unique ID
     * @throws ServiceException if the student's new name is invalid or the student isn't already in the repository
     */
    @Override
    public CompletableFuture<Void> updateStudent(Student student) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            validator.validate(student);
            Optional<Student> savedValue = repository.update(student);
            savedValue.orElseThrow(() -> new DuplicateIDException("The student does not exist."));
        },executorService);
    }

    /**
     * @return list of all the students
     */
    @Override
    public CompletableFuture<ArrayList<Student>> getAllStudents() {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Student> students=new ArrayList<>();
            repository.findAll().forEach(students::add);
            return students;
        },executorService);
    }

    /**
     * Deletes a student based on their ID
     * @param id unique identifier
     */
    @Override
    public CompletableFuture<Void> deleteStudent(long id) {
        return CompletableFuture.runAsync(() -> {
            Optional<Student> deleted = repository.delete(id);
            deleted.orElseThrow(() -> new ServiceException("Student does not exist."));
        },executorService);
    }

    public Optional<Student> findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public CompletableFuture<ArrayList<Student>> sortStudentsByNameID() {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort=new Sort(Direction.DESC,"name").and(new Sort("id"));
            ArrayList<Student> students=new ArrayList<>();
            repository.findAll(sort).forEach(students::add);
            return students;
        },executorService);
    }
}
