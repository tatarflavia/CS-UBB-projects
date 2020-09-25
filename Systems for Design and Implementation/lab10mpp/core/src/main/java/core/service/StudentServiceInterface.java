package core.service;

import core.exception.ServiceException;
import core.model.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentServiceInterface {
    void addStudent(Student student);

    @Transactional
    void updateStudent(Student student);

    List<Student> getAllStudents();

    void deleteStudent(long id);

    Optional<Student> findOne(Long id);

    //Iterable<Student> sortStudentsByNameID();
}
