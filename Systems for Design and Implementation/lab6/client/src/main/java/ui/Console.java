package ui;

import container.Pair;
import domain.Grade;
import domain.Problem;
import domain.Student;
import exception.ServiceException;
import service.AssignmentService;
import service.ProblemService;
import service.StudentService;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Handles the interactions with the user.
 */
public class Console {
    private ProblemService problemService;
    private AssignmentService assignmentService;
    private StudentService studentService;

    /**
     * @param problemService handles the operations regarding the problems
     * @param studentService handles the operations regarding the students
     */
    public Console(ProblemService problemService,StudentService studentService,AssignmentService assignmentService) {
        this.problemService = problemService;
        this.assignmentService = assignmentService;
        this.studentService = studentService;
    }

    /**
     * Prints the menu with the operations that the user can choose.
     */
    private void optionsMenu() {
        String menu="\n";
        menu+="0. Exit.\n";
        menu+="1. Add a problem.\n";
        menu+="2. Update a problem.\n";
        menu+="3. Delete a problem.\n";
        menu+="4. Show all problems.\n";
        menu+="5. Add a student.\n";
        menu+="6. Update a student.\n";
        menu+="7. Delete a student.\n";
        menu+="8. Show all students.\n";
        menu+="9. Assign a problem to a student.\n";
        menu+="10. Grade an assignment.\n";
        menu+="11. Delete a grade.\n";
        menu+="12. Show all grades.\n";
        menu+="13. Show all ungraded assignments.\n";
        menu+="14. Show all problems from a certain chapter.\n";
        menu+="15. Show the student with the highest GPA.\n";
        menu+="16. Show all problems that are in progress of grading.\n";
        menu+="17. Sort students by name and id.\n";
        menu+="18. Sort problems by chapter, number, id in ascending order.\n";
        menu+="19. Sort grades by student id in ascending order and by grade value in descending order.\n";
        System.out.println(menu);
    }

    private void printMessageAndList(String message, ArrayList<?> list) {
        String result=message+"\n";
        for (Object item: list) {
            result+=item+"\n";
        }
        System.out.println(result);
    }

    /**
     * Prints all the problems.
     */
    private void showAllProblems() {
        problemService.getAllProblems().whenCompleteAsync((input,exception) -> {
            ArrayList<Problem> problems=input;
            printMessageAndList("The problems are:",problems);
        });
    }

    /**
     * Prints all the students
     */
    private void showAllStudents() {
        studentService.getAllStudents().whenCompleteAsync((input,exception) -> {
            ArrayList<Student> students= input;
            printMessageAndList("The students are:",students);
        });
    }

    /**
     * Asks for input (id, number, chapter, text) in order to add a new problem.
     */
    private void addProblem() {
        Scanner in=new Scanner(System.in);
        long id=0;
        int number=0;
        String text,chapter;
        try {
            System.out.println("Please give an integer for the problem id:");
            id = in.nextLong();
            System.out.println("Please give an integer for the problem number:");
            number = in.nextInt();
            in.nextLine();
            System.out.println("Please give the problem chapter:");
            chapter = in.nextLine();
            System.out.println("Please give the problem text:");
            text = in.nextLine();
            Problem prb = new Problem(number, chapter, text, id);
            problemService.addProblem(prb).whenCompleteAsync((input, exception) -> {
                if (exception != null) {
                    System.out.println("Could not add problem. " + exception.getCause().getMessage());
                } else {
                    System.out.println("Problem was added.");
                }
            });
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            in.nextLine();
        }
    }

    /**
     * Asks for input (id, name) in order to add a new problem.
     */
    private void addStudent() {
        Scanner scanner=new Scanner(System.in);
        long id=-1;
        String name="";
        try {
            System.out.println("Please input an integer for the student's id:");
            id=scanner.nextLong();
            scanner.nextLine();
            System.out.println("Please input the student's name:");
            name=scanner.nextLine();
            Student student=new Student(name,id);
            studentService.addStudent(student).whenCompleteAsync((input, exception) -> {
                if (exception != null) {
                    System.out.println("Could not add student. "+exception.getCause().getMessage());
                } else {
                    System.out.println("Student was added.");
                }
            });
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    /**
     * Asks for id of the problem that must be deleted from the repository in order to remove it.
     */
    public void deleteProblem() {
        Scanner scanner=new Scanner(System.in);
        long id=-1;
        try {
            System.out.println("Please give an id for the problem to be deleted:");
            id = scanner.nextLong();
            long finalId = id;
            assignmentService.deleteAllGradesProblem(id).whenCompleteAsync((input, exception)->{
                problemService.deleteProblem(finalId).whenCompleteAsync((i, e) -> {
                    if (e != null) {
                        System.out.println("Could not delete problem. " + e.getCause().getMessage());
                    } else {
                        System.out.println("Problem was deleted.");
                    }
                });
            });
        }
        catch (InputMismatchException i)
        {
            System.out.println(i.getMessage());
            scanner.nextLine();
        }
    }

    /**
     *Updates student with a new name, given their ID.
     */
    private void updateStudent() {
        Scanner scanner=new Scanner(System.in);
        long id=-1;
        String name="";
        try {
            System.out.println("Please input the student's id: ");
            id = scanner.nextLong();
            scanner.nextLine();
            System.out.println("Please input the student's new name:");
            name = scanner.nextLine();
            Student student = new Student(name, id);
            studentService.updateStudent(student).whenCompleteAsync((input, exception) -> {
                if (exception != null) {
                    System.out.println("Could not update student. " + exception.getCause().getMessage());
                } else {
                    System.out.println("Student was updated.");
                }
            });
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    private void deleteStudent() {
        Scanner scanner=new Scanner(System.in);
        long id=-1;
        try {
            System.out.println("Please input the student's id:");
            id = scanner.nextLong();
            scanner.nextLine();
            long finalId = id;
            assignmentService.deleteAllGradesStudent(id).whenCompleteAsync((input, exception)->{
                studentService.deleteStudent(finalId).whenCompleteAsync((i, e) -> {
                    if (e != null) {
                        System.out.println("Could not delete student. " + e.getCause().getMessage());
                    } else {
                        System.out.println("Student was deleted.");
                    }
                });
            });
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    /**
     * Asks for (id,new number,new chapter,new text) for the problem to be updated.
     */

    public void updateProblem() {
        Scanner scanner=new Scanner(System.in);
        long id=0;
        int number=0;
        String text,chapter;
        try {
            System.out.println("Please give a number for the problem id:");
            id = scanner.nextLong();
            System.out.println("Please give a number for the new problem number:");
            number = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please give the new problem chapter:");
            chapter = scanner.nextLine();
            System.out.println("Please give the new problem text:");
            text = scanner.nextLine();
            Problem prb = new Problem(number, chapter, text, id);
            problemService.updateProblem(prb).whenCompleteAsync((input, exception) -> {
                if (exception != null) {
                    System.out.println("Could not update problem. " + exception.getCause().getMessage());
                } else {
                    System.out.println("Problem was updated.");
                }
            });
        }
        catch (InputMismatchException i)
        {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    /**
     * Asks for a grade id for the new Assignment, student id and problem id for the new grade.
     */
    public void assignProblemToStudent() {
        Scanner scanner=new Scanner(System.in);
        long studentID=0,problemID=0;
        try {
            System.out.println("Please give an id for the student that will receive this task:");
            studentID=scanner.nextLong();
            System.out.println("Please give an id for the problem that the student will have to resolve:");
            problemID=scanner.nextLong();
            Grade grd=new Grade(studentID,problemID);
            assignmentService.assignProblemToStudent(grd).whenCompleteAsync((input,exception)->{
                if(exception !=null)
                    System.out.println("Could not add assignment. "+exception.getCause().getMessage());
                else{
                    System.out.println("Assignment was added.");
                }
            });
        }
        catch (InputMismatchException i) {
            System.out.println("Invalid input!");
            scanner.nextLine();}
    }



    /**
     * Asks for the grade id and the value that is to be added to the entity corresponding to the id.
     */
    public void giveGradeToStudent() {
        Scanner scanner=new Scanner(System.in);
        int gradeValue=0;
        long studentID=0,problemID=0;
        try {
            System.out.println("Student ID:");
            studentID=scanner.nextLong();
            System.out.println("Problem ID:");
            problemID=scanner.nextLong();
            System.out.println("Grade:");
            gradeValue=scanner.nextInt();
            assignmentService.giveGradeToStudent(new Grade(studentID,problemID,gradeValue)).whenCompleteAsync((input,exception)->{
                if(exception != null)
                    System.out.println("Could not grade the assignment. "+exception.getCause().getMessage());
                else{
                    System.out.println("The assignment was graded.");
                }
            });
        }
        catch (InputMismatchException i) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    /**
     * Asks for a grade id that corresponds to the grade that is to be deleted.
     */
    public void deleteGrade() {
        Scanner scanner=new Scanner(System.in);
        long studentID=0,problemID=0;
        try {
            System.out.println("Student ID:");
            studentID=scanner.nextLong();
            System.out.println("Problem ID:");
            problemID=scanner.nextLong();
            assignmentService.deleteGrade(new Pair<>(studentID,problemID)).whenCompleteAsync((input,exception)->{
                if(exception!=null)
                    System.out.println("Could not delete grade. "+exception.getCause().getMessage());
                else{
                    System.out.println("The assignment was deleted.");
                }
            });
        }
        catch (InputMismatchException i) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    /**
     * Prints all grades.
     */
    public void showAllGrades() {
        assignmentService.showAllGraded().whenCompleteAsync((input,exception)->{
            ArrayList<Grade> grades=input;
            printMessageAndList("These are the graded assignments:\n",grades);
        });
    }

    public void showAllUngraded() {
        assignmentService.showAllUngraded().whenCompleteAsync((input,exception)->{
            ArrayList<Grade> grades=input;
            printMessageAndList("These are the ungraded assignments:\n",grades);
        });
    }

    public void showProblemsFilteredByChapter() {
        Scanner scanner=new Scanner(System.in);
        String s="";
        System.out.println("Please give a string for the chapter:");
        s=scanner.nextLine();
        problemService.filterProblemsByChapter(s).whenCompleteAsync((input, exception) -> {
            ArrayList<Problem> problems=input;
            printMessageAndList("The list of problems, filtered by chapter",problems);
        });
    }

    public void studentHighestGPA() {
        assignmentService.studentHighestGPA().whenCompleteAsync((input,exception)->{
            if(exception!=null)
                System.out.println("Could not find the student with the highest gpa. "+exception.getCause().getMessage());
            else{
                Student std=input;
                System.out.println("The student with highest gpa is: \n"+std.toString());
        }
        });
    }

    public void sortStudentsByNameID() {
        studentService.sortStudentsByNameID().whenCompleteAsync((input,exception) -> {
            ArrayList<Student> students= input;
            printMessageAndList("The list of students, sorted by name and id:",students);
        });
    }

    public void sortProblemsByChapterNumberID(){
        try{
            problemService.sortProblemsByChapterNumberID().whenCompleteAsync((input,exception) -> {
                ArrayList<Problem> problems=input;
                printMessageAndList("The list of problems, sorted by chapter, number and id:",problems);
            });
        }
        catch (ServiceException e){
            System.out.println(e.getMessage());
        }
    }
    private void sortGradesByValue(){
        assignmentService.sortGradesByValue().whenCompleteAsync((input,exception)->{
            ArrayList<Grade> grades=input;
            printMessageAndList("The grades sorted by value are:",grades);
        });
    }

    private void gradingInProgressProblem() {
        assignmentService.gradingInProgressProblem().whenCompleteAsync((input,exception)->{
            if(exception!=null)
                System.out.println("Could not find the problems still in the grading process. "+exception.getCause().getMessage());
            else{
                ArrayList<Problem> problems=input;
                printMessageAndList("The problems that are still in the grading process are:",problems);
        }
        });
    }



    /**
     * The user chooses which action to perform.
     */
    public void runConsole() {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            try {
                optionsMenu();
                int choiceFromMenu=-1;
                System.out.println("\nPlease choose a number from the menu:\n");
                choiceFromMenu = scanner.nextInt();
                switch (choiceFromMenu) {
                    case 0:
                        return;
                    case 1:
                        addProblem();
                        break;
                    case 2:
                        updateProblem();
                        break;
                    case 3:
                        deleteProblem();
                        break;
                    case 4:
                        showAllProblems();
                        break;
                    case 5:
                        addStudent();
                        break;
                    case 6:
                        updateStudent();
                        break;
                    case 7:
                        deleteStudent();
                        break;
                    case 8:
                        showAllStudents();
                        break;
                    case 9:
                        assignProblemToStudent();
                        break;
                    case 10:
                        giveGradeToStudent();
                        break;
                    case 11:
                        deleteGrade();
                        break;
                    case 12:
                        showAllGrades();
                        break;
                    case 13:
                        showAllUngraded();
                        break;
                    case 14:
                        showProblemsFilteredByChapter();
                        break;
                    case 15:
                        studentHighestGPA();
                        break;
                    case 16:
                        gradingInProgressProblem();
                        break;
                    case 17:
                        sortStudentsByNameID();
                        break;
                    case 18:
                        sortProblemsByChapterNumberID();
                        break;
                    case 19:
                        sortGradesByValue();
                        break;
                    default:
                        System.out.println("Unrecognized!");
                        break;
                }
            }
            catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input!");
            }
        }
    }
}