package service;

import domain.Problem;
import domain.validator.Validator;
import exception.DuplicateIDException;
import exception.ServiceException;
import exception.ValidatorException;
import repository.Sort;
import repository.SortingRepository;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Handles the communication between the repository containing the problems and the console layer.
 */
public class ProblemService implements ProblemServiceInterface {
    private SortingRepository<Long, Problem> repository;
    private Validator<Problem> validator;
    private ExecutorService executorService;

    /**
     * Creates it with a repository.
     * @param repository empty repository to hold problems
     * @param validator problem validator to decide if its fields are valid
     */
    public ProblemService(SortingRepository<Long, Problem> repository, Validator<Problem> validator, ExecutorService executorService) {
        this.repository = repository;
        this.validator=validator;
        this.executorService=executorService;
    }

    /**
     * Adds a problem to the repository
     * @param problem problem that can be assigned to students
     * @throws ValidatorException if the problem is invalid according to the validator
     */
    @Override
    public CompletableFuture<Void> addProblem(Problem problem) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            validator.validate(problem);
            Optional<Problem> previous=repository.save(problem);
            previous.ifPresent(p ->{
                throw new DuplicateIDException("The problem ID is already in use.");
            });
        },executorService);
    }

    /**
     * @return list of all the problems
     */
    @Override
    public CompletableFuture<ArrayList<Problem>> getAllProblems() {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Problem> problems=new ArrayList<>();
            repository.findAll().forEach(problems::add);
            return problems;
        },executorService);
    }


    /**
     * Deletes a problem from the repository.
     * @param id id of the problem that must be deleted
     * @throws ServiceException if the id was not in the repository
     * @return
     */
    @Override
    public CompletableFuture<Void> deleteProblem(Long id) throws ServiceException
    {
        return CompletableFuture.runAsync(() -> {
            Optional<Problem> deleted=repository.delete(id);
            deleted.orElseThrow(() -> new ServiceException("Problem ID does not exist!"));
        },executorService);
    }


    /**
     * Updates a problem that has the id=problem.id from the repository with the fields of the problem param
     * @param problem problem entity from which the fields will be taken to update the last problem that
     *                had the same id in the repository
     * @throws ServiceException if the problem is invalid or if it was not in the repository
     */
    @Override
    public CompletableFuture<Void> updateProblem(Problem problem) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            validator.validate(problem);
            Optional<Problem> updated=repository.update(problem);
            updated.orElseThrow(()->new DuplicateIDException("The problem does not exist!"));
        },executorService);
    }

    /**
     * Filters the problem list by chapter which contains the given string.
     * @param s string that needs to be compared to the chapter name
     * @return a set of filtered problems
     */
    @Override
    public CompletableFuture<ArrayList<Problem>> filterProblemsByChapter(String s)
    {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<Problem> problems=repository.findAll();
            Set<Problem> filtered=new HashSet<>();
            problems.forEach(filtered::add);
            filtered.removeIf(problem -> !problem.getChapter().contains(s));
            ArrayList<Problem> filteredProblems=new ArrayList<>();
            filtered.forEach(filteredProblems::add);
            return filteredProblems;
        },executorService);
    }

    public Optional<Problem> findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public CompletableFuture<ArrayList<Problem>> sortProblemsByChapterNumberID() {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort=new Sort(Sort.Direction.ASC,"chapter").and(new Sort("number")).and(new Sort("id"));
            ArrayList<Problem> problems=new ArrayList<>();
            repository.findAll(sort).forEach(problems::add);
            return problems;
        },executorService);
    }


}
