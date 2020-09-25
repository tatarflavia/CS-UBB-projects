package client.ui;

import core.model.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import web.dto.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Handles the interactions with the user.
 */
@Component
public class Console {

    public static final String URL = "http://localhost:8080/api";
    private RestTemplate restTemplate;

    public Console(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
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
        System.out.println("12. Show all grades.");
        System.out.println("13. Show all ungraded assignments.");
        System.out.println("14. Show all problems from a certain chapter.");
        System.out.println("15. Show the student with the highest GPA.");
        System.out.println("16. Show all problems that are in progress of grading.");
        System.out.println("17. Show top of students that managed to get the maximum grade for a certain chapter.");
        System.out.println("18. Sort students by name and id.");
        System.out.println("19. Sort problems by chapter, number, id in ascending order.");
        System.out.println("20. Sort grades by student id in ascending order and by grade value in descending order.");
    }

    /**
     * Prints all the problems.
     */
    private void showAllProblems() {
        System.out.println("These are the problems so far:");
        ProblemsDto allProblems=restTemplate.getForObject(URL+"/problems", ProblemsDto.class);
        List<ProblemDto> problems=allProblems.getProblems();
        problems.forEach(System.out::println);
    }

    /**
     * Prints all the students
     */
    private void showAllStudents() {
        System.out.println("These are the students so far:");
        StudentsDto allStudents = restTemplate.getForObject(URL+"/students", StudentsDto.class);
        List<StudentDto> students= allStudents.getStudents();
        students.forEach(System.out::println);
    }

    /*
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
            ProblemDto problemDto=new ProblemDto(text,number,chapter);
            problemDto.setId(id);
            try {
                restTemplate.postForObject(URL+"/problems", problemDto, ResponseEntity.class);
                System.out.println("Problem was added.");
            }
            catch (RestClientException e) {
                System.out.println("Could not add problem.");
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
            StudentDto studentDto=new StudentDto(name);
            studentDto.setId(id);
            try {
                restTemplate.postForObject(URL+"/students", studentDto, ResponseEntity.class);
                System.out.println("Student was added.");
            }
            catch (RestClientException e) {
                System.out.println("Could not add student.");
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
                restTemplate.delete(URL+"/grades/deleteAllGradesProblem/{id}",id);
                restTemplate.delete(URL + "/problems/{id}", id);
                System.out.println("Problem is deleted.");
            }
            catch (RestClientException e) {
                System.out.println("Could not delete problem.");
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
            StudentDto studentDto=new StudentDto(name);
            studentDto.setId(id);
            try {
                restTemplate.put(URL + "/students/{id}", studentDto, id);
                System.out.println("Student was updated.");
            }
            catch (RestClientException e) {
                System.out.println("Could not update student.");
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
                restTemplate.delete(URL+"/grades/deleteAllGradesStudent/{id}",id);
                restTemplate.delete(URL + "/students/{id}", id);
                System.out.println("Student is deleted.");
            }
            catch (RestClientException e) {
                System.out.println("Could not delete student.");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    public void updateProblem() {
        Scanner scanner=new Scanner(System.in);
        long id=0;
        int number=0;
        String text,chapter;
        try {
            System.out.println("Please give a number for the problem id:");
            id=scanner.nextLong();
            System.out.println("Please give a number for the new problem number:");
            number=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please give the new problem chapter:");
            chapter = scanner.nextLine();
            System.out.println("Please give the new problem text:");
            text = scanner.nextLine();
            ProblemDto problemDto=new ProblemDto(text,number,chapter);
            problemDto.setId(id);
            try {
                restTemplate.put(URL + "/problems/{id}", problemDto, id);
                System.out.println("Problem was updated.");
            }
            catch (RestClientException e) {
                System.out.println("Could not update problem.");
            }
        }
        catch (InputMismatchException i) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    public void assignProblemToStudent() {
        Scanner scanner = new Scanner(System.in);
        long studentID = 0, problemID = 0;
        try {
            System.out.println("Please give an id for the student that will receive this task:");
            studentID = scanner.nextLong();
            System.out.println("Please give an id for the problem that the student will have to resolve:");
            problemID = scanner.nextLong();
            GradeDto gradeDto = new GradeDto(-1, studentID, problemID);
            try {
                restTemplate.postForObject(URL + "/grades", gradeDto, ResponseEntity.class);
                System.out.println("Problem was assigned.");
            } catch (RestClientException e) {
                System.out.println("Could not assign problem.");}

            } catch (InputMismatchException i) {
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }



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
            GradeDto gradeDto=new GradeDto(gradeValue,studentID,problemID);
            try {
                restTemplate.put(URL+"/grades/{studentID}/{problemID}",gradeDto,studentID,problemID);
                System.out.println("Grade was updated.");
            }
            catch (RestClientException e)
            {System.out.println("Could not update grade.");}
        }
        catch (InputMismatchException i) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }





    public void deleteGrade() {
        Scanner scanner=new Scanner(System.in);
        long studentID=0,problemID=0;
        try {
            System.out.println("Student ID:");
            studentID=scanner.nextLong();
            System.out.println("Problem ID:");
            problemID=scanner.nextLong();
            try{
                restTemplate.delete(URL+"/grades/{studentID}/{problemID}",studentID,problemID);
                System.out.println("Grade is deleted.");
            }
            catch (RestClientException e)
            {System.out.println("Could not delete grade");}
        }
        catch (InputMismatchException i) {
            System.out.println("Invalid input!");
            scanner.nextLine();
        }
    }

    public void showAllGrades() {
        System.out.println("These are the grades so far:");
        GradesDto allGrades=restTemplate.getForObject(URL+"/grades/graded", GradesDto.class);
        List<GradeDto> grades=allGrades.getGrades();
        grades.forEach(System.out::println);
    }

    public void showAllUngraded() {
        System.out.println("These are the assignments so far:");
        List<GradeDto> grades=restTemplate.getForObject(URL+"/grades/ungraded",GradesDto.class).getGrades();
        grades.forEach(System.out::println);}


    public void showProblemsFilteredByChapter() {
        Scanner scanner=new Scanner(System.in);
        String s="";
        System.out.println("Please give a string for the chapter:");
        s=scanner.nextLine();
        ProblemsDto allProblems=restTemplate.getForObject(URL+"/problems/filter?chapter={chapter}", ProblemsDto.class,s);
        System.out.println("These are the requested problems:");
        List<ProblemDto> problems=allProblems.getProblems();
        problems.forEach(System.out::println);
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
            StudentsDto students=restTemplate.getForObject(URL+"/grades/studentMaxGrade?chapter={chapter}&number={number}",StudentsDto.class,s,numberForTop);
            List<StudentDto> studentDtos=students.getStudents();
            studentDtos.forEach(System.out::println);
        }
        catch (InputMismatchException i) {
            System.out.println("Invalid input!");}
    }


    public void studentHighestGPA() {
        StudentDto student=restTemplate.getForObject(URL+"/grades/studentHighest",StudentDto.class);
        if(student.getName().equals("null")){
            System.out.println("No such student.");
        }
        else{
            System.out.println("The student is:"+student);
        }}


    public void sortStudentsByNameID() {
        System.out.println("These are the students sorted by name and id:");
        Sort sort=new Sort(Sort.Direction.DESC,"name").and(new Sort("id"));
        StudentsDto allStudents = restTemplate.getForObject(URL+"/students", StudentsDto.class);
        List<StudentDto> students= allStudents.getStudents();
        List<StudentDto> sorted=sort.sort(students.stream()
                .map(s -> (Object)s)
                .collect(Collectors.toList()))
                .stream().map(s->(StudentDto)s)
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }


    public void sortProblemsByChapterNumberID(){
        System.out.println("These are the problems sorted by chapter:");
        Sort sort=new Sort(Sort.Direction.ASC,"chapter").and(new Sort("number")).and(new Sort("id"));
        ProblemsDto allProblems=restTemplate.getForObject(URL+"/problems", ProblemsDto.class);
        List<ProblemDto> problems=allProblems.getProblems();
        List<ProblemDto> sorted= sort.sort(problems.stream()
                .map(problem -> (Object)problem)
                .collect(Collectors.toList()))
                .stream().map(problem->(ProblemDto)problem)
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }


    private void sortGradesByValue(){
        System.out.println("These are the grades sorted by student id and value:");
        Sort sort=new Sort(Sort.Direction.ASC,"studentId").and(new Sort(Sort.Direction.DESC,"gradeByTeacher"));
        GradesDto allGrades=restTemplate.getForObject(URL+"/grades",GradesDto.class);
        List<GradeDto> grades=allGrades.getGrades();
        List<GradeDto> sorted=sort.sort(grades.stream()
                .map(grade -> (Object)grade)
                .collect(Collectors.toList()))
                .stream().map(grade->(GradeDto)grade)
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);
    }

    private void gradingInProgressProblem() {
        ProblemsDto problemsDto=restTemplate.getForObject(URL+"/grades/gradingInProgress",ProblemsDto.class);
        if(problemsDto.getProblems().size()==0){
            System.out.println("No such problems.");
        }
        else{
            System.out.println("The problems still in grading are:");
            problemsDto.getProblems().forEach(System.out::println);
        }
    }



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