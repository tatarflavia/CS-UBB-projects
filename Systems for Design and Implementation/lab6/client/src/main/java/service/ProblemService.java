package service;

import container.Message;
import domain.Problem;
import exception.ServiceException;
import tcp.TcpClient;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ProblemService implements ProblemServiceInterface {

    ExecutorService executorService;
    TcpClient tcpClient;

    public ProblemService(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService=executorService;
        this.tcpClient=tcpClient;
    }

    @Override
    public CompletableFuture<Void> addProblem(Problem problem) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            Message request=new Message(ProblemService.ADD_PROBLEM,problem);
            Message response=tcpClient.sendAndReceive(request);
            if (response.isError()) {
                throw (ServiceException) response.getBody();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<ArrayList<Problem>> getAllProblems() {
        return CompletableFuture.supplyAsync(() -> {
            Message request=new Message();
            request.setHeader(ProblemService.GET_PROBLEMS);
            request.setNoParameter();
            Message response=tcpClient.sendAndReceive(request);
            return (ArrayList<Problem>)response.getBody();
        },executorService);
    }

    @Override
    public CompletableFuture<Void> deleteProblem(Long id) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            Message request=new Message(ProblemService.DELETE_PROBLEM,id);
            Message response=tcpClient.sendAndReceive(request);
            if (response.isError()) {
                throw (ServiceException) response.getBody();
            }
        },executorService);
    }

    @Override
    public CompletableFuture<Void> updateProblem(Problem problem) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            Message request=new Message(ProblemService.UPDATE_PROBLEM,problem);
            Message response=tcpClient.sendAndReceive(request);
            if (response.isError()) {
                throw (ServiceException) response.getBody();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<ArrayList<Problem>> filterProblemsByChapter(String s) {
        return CompletableFuture.supplyAsync(() -> {
            Message request=new Message(ProblemService.FILTER_PROBLEMS_CHAPTER,s);
            Message response=tcpClient.sendAndReceive(request);
            return (ArrayList<Problem>)response.getBody();
        },executorService);
    }

    @Override
    public CompletableFuture<ArrayList<Problem>> sortProblemsByChapterNumberID() {
        return CompletableFuture.supplyAsync(() -> {
            Message request=new Message();
            request.setHeader(ProblemService.SORT_PROBLEMS_CHAPTER_NUMBER_ID);
            request.setNoParameter();
            Message response=tcpClient.sendAndReceive(request);
            return (ArrayList<Problem>)response.getBody();
        },executorService);
    }
}
