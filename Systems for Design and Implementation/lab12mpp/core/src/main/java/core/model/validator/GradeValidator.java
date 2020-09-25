package core.model.validator;

import core.exception.ValidatorException;
import core.model.Grade;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * It decides whether a  grade is rejected or accepted.
 */
@Component
public class GradeValidator implements Validator<Grade> {
    ArrayList<Predicate<Grade>> conditions;

    public GradeValidator() {
        this.conditions = new ArrayList<>();
        Predicate<Grade> idOfStudentCondition= grade -> {
            long idOfStudent=grade.getStudentId();
            return idOfStudent>0;
        };
        conditions.add(idOfStudentCondition);
        Predicate<Grade> idOfTheProblemCondition=grade -> {
            long idOfTheProblem=grade.getProblemId();
            return idOfTheProblem>0;
        };
        conditions.add(idOfTheProblemCondition);
        Predicate<Grade> gradeCondition= grade -> (grade.getGradeByTeacher()>=1 && grade.getGradeByTeacher()<=10)
                || grade.getGradeByTeacher()==-1;
        conditions.add(gradeCondition);
    }

    /**
     * Validates a problem. It can accept or reject it.
     * @param entity a grade that is given by the user that is not yet saved in the repository
     * @throws ValidatorException if the student id is not bigger than 0 or if the problem id is not bigger than 0
     */
    @Override
    public void validate(Grade entity) throws ValidatorException {
        conditions.stream()
                .map(condition -> condition.test(entity))
                .filter(correct -> !correct)
                .forEach(incorrect -> {
                    throw new ValidatorException("Invalid grade!");
                });
    }
}
