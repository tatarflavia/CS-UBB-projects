package config;

import container.Pair;
import domain.Grade;
import domain.validator.GradeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.SortingRepository;
import service.*;

@Configuration
public class AssignmentServiceConfiguration {

    @Autowired
    StudentServiceInterface studentService;

    @Autowired
    ProblemServiceInterface problemService;

    @Autowired
    SortingRepository<Pair<Long,Long>, Grade> sortingAssignmentRepository;

    @Bean
    RmiServiceExporter rmiAssignmentServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("AssignmentService");
        rmiServiceExporter.setServiceInterface(AssignmentServiceInterface.class);
        rmiServiceExporter.setService(assignmentService());
        rmiServiceExporter.setRegistryPort(1100);
        return rmiServiceExporter;
    }

    @Bean
    AssignmentServiceInterface assignmentService() {
        GradeValidator gradeValidator=new GradeValidator();
        SortingRepository<Pair<Long,Long>, Grade> gradeRepository=sortingAssignmentRepository;
        return new AssignmentService(gradeValidator,gradeRepository,problemService,studentService);
    }
}
