package service;

import container.Pair;
import domain.Grade;
import domain.Problem;
import domain.Student;
import exception.ServiceException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class AssignmentService {

    AssignmentServiceInterface assignmentService;

    public AssignmentService(AssignmentServiceInterface assignmentService) {
        this.assignmentService=assignmentService;
    }

    public CompletableFuture<Void> assignProblemToStudent(Grade grade) throws ServiceException {
        return CompletableFuture.runAsync(()-> {
            assignmentService.assignProblemToStudent(grade);
        });
    }

    public CompletableFuture<ArrayList<Grade>> showAllGraded() {
        return CompletableFuture.supplyAsync(() -> assignmentService.showAllGraded());
    }

    public CompletableFuture<ArrayList<Grade>> showAllUngraded() {
        return CompletableFuture.supplyAsync(() -> assignmentService.showAllUngraded());
    }

    public CompletableFuture<Void> deleteGrade(Pair<Long, Long> id) throws ServiceException{
        return CompletableFuture.runAsync(() -> {
            assignmentService.deleteGrade(id);
        });
    }

    public CompletableFuture<Void> deleteAllGradesStudent(long id) {
        return CompletableFuture.runAsync(() -> {
            assignmentService.deleteAllGradesStudent(id);
        });
    }

    public CompletableFuture<Void> deleteAllGradesProblem(long id) {
        return CompletableFuture.runAsync(() -> {
            assignmentService.deleteAllGradesProblem(id);
        });
    }

    public CompletableFuture<Student> studentHighestGPA() {
        return CompletableFuture.supplyAsync(() -> assignmentService.studentHighestGPA());
    }

    public CompletableFuture<ArrayList<Problem>> gradingInProgressProblem() {
        return CompletableFuture.supplyAsync(() -> assignmentService.gradingInProgressProblem());
    }

    public CompletableFuture<ArrayList<Grade>> sortGradesByValue() {
        return CompletableFuture.supplyAsync(() -> assignmentService.sortGradesByValue());
    }

    public CompletableFuture<Void> giveGradeToStudent(Grade grade) throws ServiceException {
        return CompletableFuture.runAsync(() -> {
            assignmentService.giveGradeToStudent(grade);
        });
    }
}
