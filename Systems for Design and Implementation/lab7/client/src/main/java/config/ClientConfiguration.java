package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.AssignmentServiceInterface;
import service.ProblemServiceInterface;
import service.StudentServiceInterface;

@Configuration
public class ClientConfiguration {

    @Bean
    RmiProxyFactoryBean rmiStudentProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(StudentServiceInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1100/StudentService");
        return rmiProxyFactoryBean;
    }

    @Bean
    RmiProxyFactoryBean rmiProblemProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(ProblemServiceInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1100/ProblemService");
        return rmiProxyFactoryBean;
    }


    @Bean
    RmiProxyFactoryBean rmiAssignmentProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(AssignmentServiceInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1100/AssignmentService");
        return rmiProxyFactoryBean;
    }
}
