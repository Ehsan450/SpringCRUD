package io.github.hossensyedriadh.springcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"io.github.hossensyedriadh.springcrud.repository"})
@SpringBootApplication
public class SpringCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCrudApplication.class, args);
    }

}
