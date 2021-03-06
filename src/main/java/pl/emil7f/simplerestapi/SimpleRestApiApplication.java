package pl.emil7f.simplerestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SimpleRestApiApplication {



    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApiApplication.class, args);
    }

    @Bean
    public void showMyUserProperties(){
        System.out.println(userProperties().getLogin() + " " + userProperties().getPassword());
        System.out.println(databaseProperties().getLogin() + " " + databaseProperties().getPassword());
    }

    @Bean
    @ConfigurationProperties(prefix = "user")
    MyLogProperties userProperties(){
        return new MyLogProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "database")
    MyLogProperties databaseProperties(){
        return new MyLogProperties();
    }
}
