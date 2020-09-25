package service;

import domain.Problem;
import exception.ServiceException;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class ProblemService {
    ProblemServiceInterface problemService;

    public ProblemService(ProblemServiceInterface problemService) {
        this.problemService=problemService;
    }

    public CompletableFuture<Void> addProblem(Problem problem)  throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            problemService.addProblem(problem);
        });
    }


    public CompletableFuture<ArrayList<Problem>> getAllProblems() {
        return CompletableFuture.supplyAsync(() -> problemService.getAllProblems());
    }


    public CompletableFuture<Void> deleteProblem(Long id) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
           problemService.deleteProblem(id);
        });
    }

    public CompletableFuture<Void> updateProblem(Problem problem)  throws ServiceException{
        return CompletableFuture.runAsync(() -> {
            problemService.updateProblem(problem);
        });
    }

    public CompletableFuture<ArrayList<Problem>> filterProblemsByChapter(String s) {
        return CompletableFuture.supplyAsync(() -> problemService.filterProblemsByChapter(s));
    }

    public CompletableFuture<ArrayList<Problem>> sortProblemsByChapterNumberID() {
        return CompletableFuture.supplyAsync(() -> problemService.sortProblemsByChapterNumberID());
    }
}
