package config;

import domain.Problem;
import domain.validator.ProblemValidator;
import domain.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.SortingRepository;
import service.ProblemService;
import service.ProblemServiceInterface;

@Configuration
public class ProblemServiceConfiguration {
    @Autowired
    SortingRepository<Long, Problem> sortingProblemRepository;
    @Bean
    RmiServiceExporter rmiProblemServiceExporter() {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ProblemService");
        rmiServiceExporter.setServiceInterface(ProblemServiceInterface.class);
        rmiServiceExporter.setService(problemService());
        rmiServiceExporter.setRegistryPort(1100);
        return rmiServiceExporter;
    }

    @Bean
    ProblemServiceInterface problemService() {
        Validator<Problem> problemValidator = new ProblemValidator();
        SortingRepository<Long,Problem> problemRepository=sortingProblemRepository;
        return new ProblemService(problemRepository,problemValidator);
    }
}

