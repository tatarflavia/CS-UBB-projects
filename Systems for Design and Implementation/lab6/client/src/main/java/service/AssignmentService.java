package service;

import container.Message;
import container.Pair;
import domain.Grade;
import domain.Problem;
import domain.Student;
import exception.ServiceException;
import tcp.TcpClient;

import java.lang.reflect.Array;
import java.security.MessageDigestSpi;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class AssignmentService implements AssignmentServiceInterface {

    ExecutorService executorService;
    TcpClient tcpClient;

    public AssignmentService(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Void> assignProblemToStudent(Grade grade) throws ServiceException {
        return CompletableFuture.runAsync(
                ()->{
                    Message request=new Message(AssignmentService.ASSIGN_PROBLEM,grade);
                    Message response=tcpClient.sendAndReceive(request);
                    if(response.isError()){
                        throw (ServiceException) response.getBody();
                    }
                },executorService
        );
    }


    @Override
    public CompletableFuture<ArrayList<Grade>> showAllGraded() {
        return CompletableFuture.supplyAsync(()->{
            Message request=new Message();
            request.setHeader(AssignmentService.SHOW_GRADED);
            request.setNoParameter();
            Message response=tcpClient.sendAndReceive(request);
            return (ArrayList<Grade>)response.getBody();
                },executorService
        );
    }

    @Override
    public CompletableFuture<ArrayList<Grade>> showAllUngraded() {
        return CompletableFuture.supplyAsync(()->{
            Message request=new Message();
            request.setHeader(AssignmentService.SHOW_UNGRADED);
            request.setNoParameter();
            Message response=tcpClient.sendAndReceive(request);
            return (ArrayList<Grade>)response.getBody();
        },executorService);
    }

    @Override
    public CompletableFuture<Void> deleteGrade(Pair<Long, Long> id) throws ServiceException {
        return CompletableFuture.runAsync(()-> {
            Message request=new Message(AssignmentService.DELETE_GRADE,id);
            Message response=tcpClient.sendAndReceive(request);
            if(response.isError()){
                throw (ServiceException) response.getBody();
            }
        },executorService);
    }

    @Override
    public CompletableFuture<Void> deleteAllGradesStudent(long id) {
        return CompletableFuture.runAsync(()->{
            Message request=new Message(AssignmentService.DELETE_GRADES_STUDENT,id);
            Message response=tcpClient.sendAndReceive(request);
        },executorService);
    }

    @Override
    public CompletableFuture<Void> deleteAllGradesProblem(long id) {
        return CompletableFuture.runAsync(()-> {
            Message request=new Message(AssignmentService.DELETE_GRADES_PROBLEM,id);
            Message response=tcpClient.sendAndReceive(request);
        },executorService);
    }

    @Override
    public CompletableFuture<Student> studentHighestGPA() throws ServiceException {
        return CompletableFuture.supplyAsync(()->{
            Message request=new Message();
            request.setHeader(AssignmentService.STUDENT_HIGHEST_GPA);
            request.setNoParameter();
            Message response=tcpClient.sendAndReceive(request);
            if(response.isError()){
                throw (ServiceException) response.getBody();
            }
            return (Student)response.getBody();
        },executorService);
    }

    @Override
    public CompletableFuture<ArrayList<Problem>> gradingInProgressProblem() throws ServiceException {
        return CompletableFuture.supplyAsync(()->{
            Message request=new Message();
            request.setHeader(AssignmentService.GRADING_IN_PROGRESS);
            request.setNoParameter();
            Message response=tcpClient.sendAndReceive(request);
            if(response.isError()){
                throw (ServiceException) response.getBody();
            }
            return (ArrayList<Problem>)response.getBody();
        },executorService);
    }

    @Override
    public CompletableFuture<ArrayList<Grade>> sortGradesByValue() {
        return CompletableFuture.supplyAsync(()->{
            Message request=new Message();
            request.setHeader(AssignmentService.SORT_GRADES);
            request.setNoParameter();
            Message response=tcpClient.sendAndReceive(request);
            return (ArrayList<Grade>)response.getBody();
        },executorService);
    }

    @Override
    public CompletableFuture<Void> giveGradeToStudent(Grade grade) throws ServiceException {
        return CompletableFuture.runAsync(()->{
            Message request=new Message(AssignmentService.GIVE_GRADE,grade);
            Message response=tcpClient.sendAndReceive(request);
            if(response.isError()){
                throw (ServiceException) response.getBody();
            }
        },executorService);
    }
}
