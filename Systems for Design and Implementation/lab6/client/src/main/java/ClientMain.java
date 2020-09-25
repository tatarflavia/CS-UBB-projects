import service.AssignmentService;
import service.ProblemService;
import service.StudentService;
import tcp.TcpClient;
import ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient();
        StudentService studentService=new StudentService(executorService,tcpClient);
        ProblemService problemService=new ProblemService(executorService,tcpClient);
        AssignmentService assignmentService=new AssignmentService(executorService,tcpClient);
        Console console = new Console(problemService,studentService,assignmentService);
        console.runConsole();
        executorService.shutdown();
    }
}