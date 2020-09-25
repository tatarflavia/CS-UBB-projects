import service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ui.Console;


public class ClientMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext("config");
        StudentService studentService=new StudentService(context.getBean(StudentServiceInterface.class));
        ProblemService problemService=new ProblemService(context.getBean(ProblemServiceInterface.class));
        AssignmentService assignmentService=new AssignmentService(context.getBean(AssignmentServiceInterface.class));
        Console console = new Console(problemService,studentService,assignmentService);
        console.runConsole();
    }
}