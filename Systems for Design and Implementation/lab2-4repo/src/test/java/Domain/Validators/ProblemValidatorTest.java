package Domain.Validators;
import Exception.*;
import Domain.Problem;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProblemValidatorTest {

    @Test(expected = ValidatorException.class)
    public void validate() {
        Validator<Problem> problemValidator=new ProblemValidator();
        Problem prob2=new Problem(2,"AI","bb",-20);
        problemValidator.validate(prob2);
        Problem prob3=new Problem(6,"AI","bb",2);
        problemValidator.validate(prob3);
    }
}