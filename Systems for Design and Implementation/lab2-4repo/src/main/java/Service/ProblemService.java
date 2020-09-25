package Service;

import Domain.Problem;
import Domain.Student;
import Domain.Validators.Validator;
import Exception.DuplicateIDException;
import Exception.RejectedInputException;
import Exception.ValidatorException;
import Repository.Sort;
import Repository.SortingRepository;

import java.util.*;

/**
 * Handles the communication between the repository containing the problems and the console layer.
 */
public class ProblemService  {
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
    public void addProblem(Problem problem) throws RejectedInputException {
        validator.validate(problem);
        Optional<Problem> previous=repository.save(problem);
        previous.ifPresent(s -> {
            throw new DuplicateIDException("The problem ID is already in use.");
        });
    }

    /**
     * @return list of all the problems
     */
    public List<Problem> getAllProblems() {
        List<Problem> problems=new ArrayList<>();
        repository.findAll().forEach(problems::add);
        return problems;
    }


    /**
     * Deletes a problem from the repository.
     * @param id id of the problem that must be deleted
     * @throws RejectedInputException if the id was not in the repository
     */
    public void deleteProblem(Long id) throws RejectedInputException
    {
        Optional<Problem> deleted=repository.delete(id);
        deleted.orElseThrow(() -> new RejectedInputException("id must not be null/must exist!"));
    }


    /**
     * Updates a problem that has the id=problem.id from the repository with the fields of the problem param
     * @param problem problem entity from which the fields will be taken to update the last problem that
     *                had the same id in the repository
     * @throws RejectedInputException if the problem is invalid or if it was not in the repository
     */
    public void updateProblem(Problem problem) throws RejectedInputException
    {
        validator.validate(problem);
        Optional<Problem> updated=repository.update(problem);
        updated.orElseThrow(()->new DuplicateIDException("the problem doesn't exist!"));
    }

    /**
     * Filters the problem list by chapter which contains the given string.
     * @param s string that needs to be compared to the chapter name
     * @return a set of filtered problems
     */
    public Set<Problem> filterProblemsByChapter(String s)
    {
        Iterable<Problem> problems=repository.findAll();
        Set<Problem> filtered=new HashSet<>();
        problems.forEach(filtered::add);
        filtered.removeIf(problem -> !problem.getChapter().contains(s));
        return filtered;
    }

    public Optional<Problem> findOne(Long id) {
        return repository.findOne(id);
    }

    public Iterable<Problem> sortProblemsByChapterNumberID() {
        Sort sort=new Sort(Sort.Direction.ASC,"chapter").and(new Sort("number")).and(new Sort("id"));
        return repository.findAll(sort);
    }


}
