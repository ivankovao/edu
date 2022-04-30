package app.config;

import app.config.model.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = "app")
public class AppConfig {



    @Bean
    @Scope("prototype")
    Timer timer(){
        return new Timer();
    }
}
