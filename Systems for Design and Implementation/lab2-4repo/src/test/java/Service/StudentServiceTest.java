package Service;

import Domain.Student;
import Domain.Validators.StudentValidator;
import Domain.Validators.Validator;
import Repository.InMemoryRepository;
import Repository.Repository;
import org.junit.Assert;
import org.junit.Test;

public class StudentServiceTest {

    @Test
    public void addStudent() {
        Validator<Student> studentValidator=new StudentValidator();
        Repository<Long,Student> studentRepository=new InMemoryRepository<Long,Student>();
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        Student std2=new Student("razv",2);
        studentService.addStudent(std2);
        Assert.assertEquals(1,studentService.getAllStudents().size());
        Student std=new Student("alina",9);
        studentService.addStudent(std);
        Assert.assertEquals(2,studentService.getAllStudents().size());
    }

    @Test
    public void getAllStudents(){
        Validator<Student> studentValidator=new StudentValidator();
        Repository<Long,Student> studentRepository=new InMemoryRepository<Long,Student>();
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        Student std2=new Student("razv",2);
        studentService.addStudent(std2);
        Assert.assertEquals(1,studentService.getAllStudents().size());
        Student std=new Student("alina",9);
        studentService.addStudent(std);
        Assert.assertEquals(2,studentService.getAllStudents().size());
    }

    @Test
    public void updateStudent() {
        Validator<Student> studentValidator=new StudentValidator();
        Repository<Long,Student> studentRepository=new InMemoryRepository<Long,Student>();
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        studentRepository.save(new Student("Student Name",1));
        studentRepository.save(new Student("Name Name",2));
        studentRepository.save(new Student("First Last",3));
        studentService.updateStudent(new Student("New Name",2));
        Student updatedStudent=studentRepository.findOne(2L).get();
        Assert.assertEquals(updatedStudent.getName(),"New Name");
    }

    @Test
    public void deleteStudent() {
        Validator<Student> studentValidator=new StudentValidator();
        Repository<Long,Student> studentRepository=new InMemoryRepository<Long,Student>();
        StudentService studentService=new StudentService(studentRepository,studentValidator);
        Student std2=new Student("razv",2);
        studentService.addStudent(std2);
        Student std=new Student("alina",9);
        studentService.addStudent(std);
        Assert.assertEquals(2,studentService.getAllStudents().size());
        studentService.deleteStudent(2);
        Assert.assertEquals(1,studentService.getAllStudents().size());
        studentService.deleteStudent(9);
        Assert.assertEquals(0,studentService.getAllStudents().size());
    }
}