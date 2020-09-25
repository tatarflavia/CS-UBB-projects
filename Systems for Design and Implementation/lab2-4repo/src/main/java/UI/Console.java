package UI;

import Domain.Grade;
import Domain.Problem;
import Domain.Student;
import Exception.RejectedInputException;
import Service.AssignmentService;
import Service.ProblemService;
import Service.StudentService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
        System.out.println();
        System.out.println("0. Exit.");
        System.out.println("1. Add a problem.");
        System.out.println("2. Update a problem.");
        System.out.println("3. Delete a problem.");
        System.out.println("4. Show all problems.");
        System.out.println("5. Add a student.");
        System.out.println("6. Update a student.");
        System.out.println("7. Delete a student.");
        System.out.println("8. Show all students.");
        System.out.println("9. Assign a problem to a student.");
        System.out.println("10. Grade an assignment.");
        System.out.println("11. Delete a grade.");
        System.out.println("12. Show all grades."); //filter
        System.out.println("13. Show all ungraded assignments."); //filter
        System.out.println("14. Show all problems from a certain chapter."); //filter
        System.out.println("15. Show the student with the highest GPA."); //report
        System.out.println("16. Show all problems that are in progress of grading."); //filter
        System.out.println("17. Show top of students that managed to get the maximum grade for a certain chapter."); //report
        System.out.println("18. Sort students by name and id.");
        System.out.println("19. Sort problems by chapter, number, id in ascending order.");
        System.out.println("20. Sort grades by student id in ascending order and by grade value in descending order.");
    }

    /**
     * Prints all the problems.
     */
    private void showAllProblems() {
        System.out.println("The problems are:\n");
        List<Problem> problems=problemService.getAllProblems();
        problems.forEach(System.out::println);
    }

    /**
     * Prints all the students
     */
    private void showAllStudents() {
        System.out.println("The students are:\n");
        List<Student> students =studentService.getAllStudents();
        students.forEach(System.out::println);
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
            try {
                problemService.addProblem(prb);
            }
            catch (RejectedInputException e) {
                System.out.println(e.getMessage());
            }
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
            try {
                studentService.addStudent(student);
            }
            catch (RejectedInputException e) {
                System.out.println(e.getMessage());
            }
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
        try
        {
            System.out.println("Please give an id for the problem to be deleted:");
            id=scanner.nextLong();
            try {
                assignmentService.deleteAllGradesProblem(id);
                problemService.deleteProblem(id);
            }
            catch (RejectedInputException e)
            {
                System.out.println(e.getMessage());
            }

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
            id=scanner.nextLong();
            scanner.nextLine();
            System.out.println("Please input the student's new name:");
            name=scanner.nextLine();
            Student student=new Student(name,id);
            try {
                studentService.updateStudent(student);
            }
            catch (RejectedInputException e) {
                System.out.println(e.getMessage());
            }
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
            id=scanner.nextLong();
            scanner.nextLine();
            try {
                assignmentService.deleteAllGradesStudent(id);
                studentService.deleteStudent(id);
            }
            catch (RejectedInputException e) {
                System.out.println(e.getMessage());
            }
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
        try{
            System.out.println("Please give a number for the problem id:");
            id=scanner.nextLong();
            System.out.println("Please give a number for the new problem number:");
            number=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please give the new problem chapter:");
            chapter = scanner.nextLine();
            System.out.println("Please give the new problem text:");
            text = scanner.nextLine();
            Problem prb = new Problem(number, chapter, text, id);
            try{
                problemService.updateProblem(prb);
            }
            catch (RejectedInputException r)
            {
                System.out.println(r.getMessage());
            }
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
            try{
                assignmentService.assignProblemToStudent(grd);
            }
            catch (RejectedInputException e)
            {System.out.println(e.getMessage());}
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
            try {
                assignmentService.giveGradeToStudent(new Grade(studentID,problemID,gradeValue));
            }
            catch (RejectedInputException e)
            {System.out.println(e.getMessage());}
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
            try{
                assignmentService.deleteGrade(studentID,problemID);
            }
            catch (RejectedInputException e)
            {System.out.println(e.getMessage());}
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
        System.out.println("These are the grades so far:");
        List<Grade> grades=assignmentService.showAllGraded();
        grades.forEach(System.out::println);
    }

    public void showAllUngraded() {
        List<Grade> grades=assignmentService.showAllUngraded();
        grades.forEach(System.out::println);
    }

    public void showProblemsFilteredByChapter() {
        Scanner scanner=new Scanner(System.in);
        String s="";
        System.out.println("Please give a string for the chapter:");
        s=scanner.nextLine();
        System.out.println("These are the filtered problems:");
        Set<Problem> filtered=problemService.filterProblemsByChapter(s);
        filtered.forEach(System.out::println);
    }

    public void showTopStudentsWithMaxGradeOnACertainChapter() {
        Scanner scanner=new Scanner(System.in);
        String s="";
        int  numberForTop=0;
        try {
            System.out.println("Please give a number for the limit of the top size:");
            numberForTop=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please give a string that is contained in the chapter wished for the top:");
            s=scanner.nextLine();
            System.out.println("These are the top students:");
            Set<Student> bestStudents= assignmentService.topStudentsWithMaxGradeOnAChapter(s,numberForTop);
            bestStudents.forEach(System.out::println);
        }
        catch (InputMismatchException i) {
            System.out.println("Invalid input!");}


    }

    public void studentHighestGPA() {
        try {
            System.out.println(assignmentService.studentHighestGPA());
        }
        catch (RejectedInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortStudentsByNameID() {
        try {
            studentService.sortStudentsByNameID().forEach(System.out::println);
        }
        catch (RejectedInputException e) {
            System.out.println(e.getMessage());
        }
    }
    public void sortProblemsByChapterNumberID(){
        try{
            problemService.sortProblemsByChapterNumberID().forEach(System.out::println);
        }
        catch (RejectedInputException e){
            System.out.println(e.getMessage());
        }
    }
    private void sortGradesByValue(){
        try{
            assignmentService.sortGradesByValue().forEach(System.out::println);
        }
        catch (RejectedInputException e){
            System.out.println(e.getMessage());
        }
    }

    private void gradingInProgressProblem() {
        try {
            assignmentService.gradingInProgressProblem().forEach(System.out::println);
        }
        catch (RejectedInputException e) {
            System.out.println(e.getMessage());
        }
    }



    /**
     * The user chooses which action to perform.
     */
    public void runApplication() {
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
                        showTopStudentsWithMaxGradeOnACertainChapter();
                        break;
                    case 18:
                        sortStudentsByNameID();
                        break;
                    case 19:
                        sortProblemsByChapterNumberID();
                        break;
                    case 20:
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