package config;

import domain.Student;
import domain.validator.StudentValidator;
import domain.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.SortingRepository;
import service.StudentService;
import service.StudentServiceInterface;

@Configuration
public class StudentServiceConfiguration {
    @Autowired
    SortingRepository<Long, Student> sortingStudentRepository;

    @Bean
    RmiServiceExporter rmiStudentServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("StudentService");
        rmiServiceExporter.setServiceInterface(StudentServiceInterface.class);
        rmiServiceExporter.setService(studentService());
        rmiServiceExporter.setRegistryPort(1100);
        return rmiServiceExporter;
    }

    @Bean
    StudentServiceInterface studentService() {
        //AnnotationConfigApplicationContext context= new AnnotationConfigApplicationContext("config");
        Validator<Student> studentValidator = new StudentValidator();
        SortingRepository<Long,Student> studentRepository=sortingStudentRepository;
        return new StudentService(studentRepository,studentValidator);
    }
}
