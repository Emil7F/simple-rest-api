package pl.emil7f.simplerestapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class SimpleRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApiApplication.class, args);
    }




}
