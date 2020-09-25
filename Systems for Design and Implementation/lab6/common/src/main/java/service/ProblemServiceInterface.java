package service;

import domain.Problem;
import exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface ProblemServiceInterface {
    String ADD_PROBLEM="add problem";
    String GET_PROBLEMS="get problems";
    String DELETE_PROBLEM="delete problem";
    String UPDATE_PROBLEM="update problem";
    String FILTER_PROBLEMS_CHAPTER="filter problems chapter";
    String SORT_PROBLEMS_CHAPTER_NUMBER_ID="sort problems chapter number id";

    CompletableFuture<Void> addProblem(Problem problem) throws ServiceException;

    CompletableFuture<ArrayList<Problem>> getAllProblems();

    CompletableFuture<Void> deleteProblem(Long id) throws ServiceException;

    CompletableFuture<Void> updateProblem(Problem problem) throws ServiceException;

    CompletableFuture<ArrayList<Problem>> filterProblemsByChapter(String s);

    CompletableFuture<ArrayList<Problem>> sortProblemsByChapterNumberID();
}
