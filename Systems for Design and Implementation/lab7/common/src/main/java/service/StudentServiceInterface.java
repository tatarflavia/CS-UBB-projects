package service;

import domain.Student;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Optional;

public interface StudentServiceInterface {

    void addStudent(Student student) throws ServiceException;

    void updateStudent(Student student) throws ServiceException;

    ArrayList<Student> getAllStudents();

    void deleteStudent(long id);

    ArrayList<Student> sortStudentsByNameID();

    Optional<Student> findOne(Long idOfTheStudent);
}
