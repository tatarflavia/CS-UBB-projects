package Service;

import Domain.Problem;
import Domain.Validators.Validator;
import Exception.DuplicateIDException;
import Exception.RejectedInputException;
import Exception.ValidatorException;
import Repository.ProblemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles the communication between the repository containing the problems and the console layer.
 */
@Service
public class ProblemService  {
    @Autowired
    private ProblemRepository repository;
    @Autowired
    private Validator<Problem> validator;
    private static Logger LOGGER = LoggerFactory.getLogger(ProblemService.class);


    /**
     * Adds a problem to the repository
     * @param problem problem that can be assigned to students
     * @throws ValidatorException if the problem is invalid according to the validator
     */
    public void addProblem(Problem problem) throws RejectedInputException {
        LOGGER.info("addProblem- method entered:");
        validator.validate(problem);
        Optional<Problem> previous=repository.findById(problem.getId());
        previous.ifPresent(s -> {
            throw new DuplicateIDException("The problem ID is already in use.");
        });
        repository.save(problem);
        LOGGER.info("addProblem- method finished");
    }

    /**
     * @return list of all the problems
     */
    public List<Problem> getAllProblems() {
        return repository.findAll();
    }


    /**
     * Deletes a problem from the repository.
     * @param id id of the problem that must be deleted
     * @throws RejectedInputException if the id was not in the repository
     */
    public void deleteProblem(Long id) throws RejectedInputException
    {
        LOGGER.info("deleteProblem- method entered:");
        Optional<Problem> toBeDeleted=repository.findById(id);
        toBeDeleted.orElseThrow(() -> new RejectedInputException("id must not be null/must exist!"));
        repository.deleteById(id);
        LOGGER.info("deleteProblem- method finished");
    }


    /**
     * Updates a problem that has the id=problem.id from the repository with the fields of the problem param
     * @param problem problem entity from which the fields will be taken to update the last problem that
     *                had the same id in the repository
     * @throws RejectedInputException if the problem is invalid or if it was not in the repository
     */
    @Transactional
    public void updateProblem(Problem problem) throws RejectedInputException
    {
        LOGGER.info("updateProblem- method entered:");
        validator.validate(problem);
        Optional<Problem> toBeUpdated=repository.findById(problem.getId());
        toBeUpdated.ifPresentOrElse(p->{
            p.setChapter(problem.getChapter());
            p.setNumber(problem.getNumber());
            p.setText(problem.getText());
        },()->{throw new DuplicateIDException("the problem doesn't exist!");});
        LOGGER.info("updateProblem- method finished");
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
        return repository.findById(id);
    }

    public Iterable<Problem> sortProblemsByChapterNumberID() {
        Sort sort=new Sort(Sort.Direction.ASC,"chapter").and(new Sort("number")).and(new Sort("id"));
        return sort.sort(repository.findAll().stream()
                .map(problem -> (Object)problem)
                .collect(Collectors.toList()))
                .stream().map(problem->(Problem)problem)
                .collect(Collectors.toList());
    }


}
