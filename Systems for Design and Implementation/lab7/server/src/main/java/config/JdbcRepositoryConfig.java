package config;

import container.Pair;
import domain.Grade;
import domain.Problem;
import domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import repository.AssignmentDBRepository;
import repository.ProblemDBRepository;
import repository.SortingRepository;
import repository.StudentDBRepository;

@Configuration
public class JdbcRepositoryConfig {
    @Autowired
    JdbcOperations jdbcOperations;

    @Bean
    SortingRepository<Long, Student> sortingStudentRepository() {return new StudentDBRepository(jdbcOperations); }

    @Bean
    SortingRepository<Long, Problem> sortingProblemRepository(){return new ProblemDBRepository(jdbcOperations);}

    @Bean
    SortingRepository<Pair<Long,Long>, Grade> sortingAssignmentRepository(){return new AssignmentDBRepository(jdbcOperations);}

}
