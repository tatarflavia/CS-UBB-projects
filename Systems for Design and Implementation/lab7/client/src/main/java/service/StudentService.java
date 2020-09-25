package service;

import domain.Student;
import exception.ServiceException;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class StudentService {
    StudentServiceInterface studentService;

    public StudentService(StudentServiceInterface studentService) {
        this.studentService=studentService;
    }

    public CompletableFuture<Void> addStudent(Student student) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            studentService.addStudent(student);
        });
    }

    public CompletableFuture<Void> updateStudent(Student student)  throws ServiceException{
        return CompletableFuture.runAsync(() -> {
            studentService.updateStudent(student);
        });
    }

    public CompletableFuture<ArrayList<Student>> getAllStudents() {
        return CompletableFuture.supplyAsync(() -> studentService.getAllStudents());
    }

    public CompletableFuture<Void> deleteStudent(long id) throws ServiceException{
        return CompletableFuture.runAsync(() -> {
            studentService.deleteStudent(id);
        });
    }

    public CompletableFuture<ArrayList<Student>> sortStudentsByNameID() {
        return CompletableFuture.supplyAsync(() -> studentService.sortStudentsByNameID());
    }
}
