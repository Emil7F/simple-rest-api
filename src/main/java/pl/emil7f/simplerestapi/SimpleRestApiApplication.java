package pl.emil7f.simplerestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleRestApiApplication {

    private UserProperties userProperties;

    public SimpleRestApiApplication(UserProperties userProperties) {
        this.userProperties = userProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApiApplication.class, args);
    }

    @Bean
    public void showMyUserProperties(){
        System.out.println(userProperties.getLogin() + " " + userProperties.getPassword());
    }
}
