package Domain.Validators;

import Domain.Problem;
import Exception.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * It decides whether a problem is rejected or accepted.
 */
@Component
public class ProblemValidator implements Validator<Problem> {
    ArrayList<Predicate<Problem>> conditions;

    public ProblemValidator() {
        conditions= new ArrayList<>();
        Predicate<Problem> chapterCondition=problem -> {
            String chapter=problem.getChapter();
            return chapter!=null && chapter.length()<100 && chapter.length()>3;
        };
        conditions.add(chapterCondition);
        Predicate<Problem> numberCondition=problem -> {
            int number=problem.getNumber();
            return number>0;
        };
        conditions.add(numberCondition);
        Predicate<Problem> textCondition=problem -> {
            String text=problem.getText();
            return text!=null && text.length()>3;
        };
        conditions.add(textCondition);
        Predicate<Problem> idCondition=problem -> {
            long id=problem.getId();
            return id>0;
        };
        conditions.add(idCondition);
    }

    /**
     * Validates a problem. It can accept or reject it.
     * @param entity a problem that is given by the user and is not saved yet in the repository
     * @throws ValidatorException if the chapter is longer than 100 characters, is shorter than 3 characters
     * or is null,
     * or if the id or number is non-positive, or if the text is null or is shorter than 3 characters
     */
    @Override
    public void validate(Problem entity) throws ValidatorException {
        conditions.stream()
                .map(condition -> condition.test(entity))
                .filter(correct -> !correct)
                .forEach(incorrect -> {
                    throw new ValidatorException("Problem's chapter, text, number or ID is invalid.");
                });
    }
}
