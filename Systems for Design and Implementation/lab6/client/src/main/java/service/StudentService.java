package service;

import container.Message;
import domain.Student;
import exception.ServiceException;
import tcp.TcpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class StudentService implements StudentServiceInterface {

    ExecutorService executorService;
    TcpClient tcpClient;

    public StudentService(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService=executorService;
        this.tcpClient=tcpClient;
    }

    @Override
    public CompletableFuture<Void> addStudent(Student student) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            Message request=new Message(StudentService.ADD_STUDENT,student);
            Message response=tcpClient.sendAndReceive(request);
            if (response.isError()) {
                throw (ServiceException) response.getBody();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Void> updateStudent(Student student) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            Message request=new Message(StudentService.UPDATE_STUDENT,student);
            Message response=tcpClient.sendAndReceive(request);
            if (response.isError()) {
                throw (ServiceException) response.getBody();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<ArrayList<Student>> getAllStudents() {
        return CompletableFuture.supplyAsync(() -> {
            Message request=new Message();
            request.setHeader(StudentService.GET_ALL_STUDENTS);
            request.setNoParameter();
            Message response=tcpClient.sendAndReceive(request);
            return (ArrayList<Student>)response.getBody();
        },executorService);
    }

    @Override
    public CompletableFuture<Void> deleteStudent(long id) {
        return CompletableFuture.runAsync(() -> {
            Message request=new Message(StudentService.DELETE_STUDENT,id);
            Message response=tcpClient.sendAndReceive(request);
            if (response.isError()) {
                throw (ServiceException) response.getBody();
            }
        },executorService);
    }


    @Override
    public CompletableFuture<ArrayList<Student>> sortStudentsByNameID() {
        return CompletableFuture.supplyAsync(() -> {
            Message request=new Message();
            request.setHeader(StudentService.SORT_STUDENTS_NAME_ID);
            request.setNoParameter();
            Message response=tcpClient.sendAndReceive(request);
            return (ArrayList<Student>)response.getBody();
        },executorService);
    }
}
