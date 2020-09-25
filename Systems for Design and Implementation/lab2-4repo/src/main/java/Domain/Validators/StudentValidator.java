package Domain.Validators;

import Domain.Student;
import Exception.ValidatorException;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * It decides whether a student is accepted or rejected as an input.
 */
public class StudentValidator implements Validator<Student>{
    ArrayList<Predicate<Student>> conditions;

    public StudentValidator() {
        conditions=new ArrayList<>();
        Predicate<Student> nameCondition=student -> {
            String name=student.getName();
            Stream<Character> characterStream1 = name.chars().mapToObj(c -> (char) c);
            Stream<Character> characterStream2 = name.chars().mapToObj(c -> (char) c);
            return name!=null && name.length()>3 && !characterStream1.anyMatch(c->Character.isDigit(c)) && characterStream2.anyMatch(c->Character.isLetter(c));
        };
        conditions.add(nameCondition);
        Predicate<Student> idCondition=student -> {
            long id=student.getId();
            return id>0;
        };
        conditions.add(idCondition);
    }

    /**
     *Validates a student. It can accept or reject it.
     * @param entity a student that is given by the user and is not saved yet in the repository
     * @throws ValidatorException if the student's name is null, is shorter than 3 characters, contains a digit
     * or does not contain or a letter, or if the id is non-positive
     */
    @Override
    public void validate(Student entity) throws ValidatorException {
        conditions.stream()
                .map(condition->condition.test(entity))
                .filter(correct -> !correct)
                .anyMatch(incorrect -> {
                    throw new ValidatorException("Student's name or ID is invalid.");
                });
    }
}
