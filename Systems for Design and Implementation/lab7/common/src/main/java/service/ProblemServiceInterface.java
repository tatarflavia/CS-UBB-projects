package service;

import domain.Problem;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.Optional;

public interface ProblemServiceInterface {
    void addProblem(Problem problem) throws ServiceException;

    ArrayList<Problem> getAllProblems();

    void deleteProblem(Long id) throws ServiceException;

    void updateProblem(Problem problem) throws ServiceException;

    ArrayList<Problem> filterProblemsByChapter(String s);

    ArrayList<Problem> sortProblemsByChapterNumberID();

    Optional<Problem> findOne(Long idOfTheProblem);
}
