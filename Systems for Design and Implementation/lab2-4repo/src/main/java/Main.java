import Container.Pair;
import Domain.Grade;
import Domain.Problem;
import Domain.Student;
import Domain.Validators.GradeValidator;
import Domain.Validators.ProblemValidator;
import Domain.Validators.StudentValidator;
import Domain.Validators.Validator;
import Exception.RejectedInputException;
import Repository.AssignmentDBRepository;
import Repository.ProblemDBRepository;
import Repository.Repository;
import Repository.StudentDBRepository;
import Repository.SortingRepository;
import Service.AssignmentService;
import Service.ProblemService;
import Service.StudentService;
import UI.Console;

public class Main {
    public static void main(String[] args) {
        try {
            Validator<Problem> problemValidator = new ProblemValidator();
            //Repository<Long, Problem> problemRepository= new InMemoryRepository<Long, Problem>();
            //Repository<Long, Problem> problemRepository = new ProblemFileRepository("problems.txt");
            //Repository<Long,Problem> problemRepository=new ProblemXMLRepository("problems.xml");
            SortingRepository<Long,Problem> problemRepository=new ProblemDBRepository();
            ProblemService problemService = new ProblemService(problemRepository, problemValidator);
            Validator<Student> studentValidator = new StudentValidator();
            //Repository<Long,Student> studentRepository=new InMemoryRepository<Long,Student>();
            //Repository<Long, Student> studentRepository = new StudentFileRepository("students.txt");
            //Repository<Long,Student> studentRepository=new StudentXMLRepository("students.xml");
            SortingRepository<Long,Student> studentRepository=new StudentDBRepository();
            StudentService studentService = new StudentService(studentRepository, studentValidator);
            GradeValidator gradeValidator=new GradeValidator();
            //Repository<Long,Grade> gradeRepository=new InMemoryRepository<Long, Grade>();
            //Repository<Pair<Long,Long>,Grade> gradeRepository=new AssignmentFileRepository("grades.txt");
            //Repository<Pair<Long,Long>,Grade> gradeRepository=new AssignmentXMLRepository("grades.xml");
            SortingRepository<Pair<Long,Long>,Grade> gradeRepository=new AssignmentDBRepository();
            AssignmentService assignmentService=new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
            Console console=new Console(problemService,studentService,assignmentService);
            console.runApplication();
        }
        catch (RejectedInputException e) {
            System.out.println(e.getMessage());
        }
    }
}