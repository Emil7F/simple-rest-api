package pl.emil7f.simplerestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SimpleRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleRestApiApplication.class, args);
    }




}
