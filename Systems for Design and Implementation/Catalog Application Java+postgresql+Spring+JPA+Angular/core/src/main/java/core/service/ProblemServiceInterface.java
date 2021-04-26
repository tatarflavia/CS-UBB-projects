package core.service;

import core.exception.ServiceException;
import core.model.Problem;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProblemServiceInterface {
    void addProblem(Problem problem);

    List<Problem> getAllProblems();

    void deleteProblem(Long id);

    @Transactional
    void updateProblem(Problem problem);

    Set<Problem> filterProblemsByChapter(String s);

    Optional<Problem> findOne(Long id);

    //Iterable<Problem> sortProblemsByChapterNumberID();
}
