package Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"Repository","Service","UI","Domain.Validators"})
public class AppConfig {
}
