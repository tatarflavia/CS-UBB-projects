package service;

import domain.Problem;
import domain.validator.Validator;
import exception.ServiceException;
import exception.ValidatorException;
import repository.Sort;
import repository.SortingRepository;
import java.util.*;

/**
 * Handles the communication between the repository containing the problems and the console layer.
 */
public class ProblemService implements ProblemServiceInterface {
    private SortingRepository<Long, Problem> repository;
    private Validator<Problem> validator;

    /**
     * Creates it with a repository.
     * @param repository empty repository to hold problems
     * @param validator problem validator to decide if its fields are valid
     */
    public ProblemService(SortingRepository<Long, Problem> repository, Validator<Problem> validator) {
        this.repository = repository;
        this.validator=validator;
    }

    /**
     * Adds a problem to the repository
     * @param problem problem that can be assigned to students
     * @throws ValidatorException if the problem is invalid according to the validator
     */
    @Override
    public void addProblem(Problem problem) throws ServiceException {
        validator.validate(problem);
        repository.save(problem);
    }

    @Override
    public ArrayList<Problem> getAllProblems() {
        ArrayList<Problem> problems=new ArrayList<>();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repository.findAll().forEach(problems::add);
        return problems;
    }

    /**
     * Deletes a problem from the repository.
     * @param id id of the problem that must be deleted
     * @throws ServiceException if the id was not in the repository
     */
    @Override
    public void deleteProblem(Long id) throws ServiceException {
        repository.delete(id);
    }


    /**
     * Updates a problem that has the id=problem.id from the repository with the fields of the problem param
     * @param problem problem entity from which the fields will be taken to update the last problem that
     *                had the same id in the repository
     * @throws ServiceException if the problem is invalid or if it was not in the repository
     */
    @Override
    public void updateProblem(Problem problem) throws ServiceException {
        validator.validate(problem);
        repository.update(problem);
    }

    /**
     * Filters the problem list by chapter which contains the given string.
     * @param s string that needs to be compared to the chapter name
     * @return a set of filtered problems
     */
    @Override
    public ArrayList<Problem> filterProblemsByChapter(String s) {
        Iterable<Problem> problems = repository.findAll();
        Set<Problem> filtered = new HashSet<>();
        problems.forEach(filtered::add);
        filtered.removeIf(problem -> !problem.getChapter().contains(s));
        ArrayList<Problem> filteredProblems = new ArrayList<>();
        filteredProblems.addAll(filtered);
        return filteredProblems;
    }

    public Optional<Problem> findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public ArrayList<Problem> sortProblemsByChapterNumberID() {
        Sort sort = new Sort(Sort.Direction.ASC, "chapter").and(new Sort("number")).and(new Sort("id"));
        ArrayList<Problem> problems = new ArrayList<>();
        repository.findAll(sort).forEach(problems::add);
        return problems;
    }
}
