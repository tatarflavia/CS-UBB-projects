package service;

import domain.Student;
import exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface StudentServiceInterface {
    String ADD_STUDENT="add student";
    String UPDATE_STUDENT="update student";
    String GET_ALL_STUDENTS="get all students";
    String DELETE_STUDENT="delete student";
    String SORT_STUDENTS_NAME_ID="sort student name id";

    CompletableFuture<Void> addStudent(Student student) throws ServiceException;

    CompletableFuture<Void> updateStudent(Student student) throws ServiceException;

    CompletableFuture<ArrayList<Student>> getAllStudents();

    CompletableFuture<Void> deleteStudent(long id);

    CompletableFuture<ArrayList<Student>> sortStudentsByNameID();
}
