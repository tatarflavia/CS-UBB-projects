package Domain.Validators;
import Domain.Student;
import Exception.*;
import Domain.Problem;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentValidatorTest {

    @Test(expected = ValidatorException.class)
    public void validate(){
        Validator<Student> studentValidator=new StudentValidator();
        Student std2=new Student("razv",2);
        studentValidator.validate(std2);
        Student std3=new Student("",-10);
        studentValidator.validate(std3);
    }

}