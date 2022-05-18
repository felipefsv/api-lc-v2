package com.lc.apilc;

import com.lc.apilc.models.entity.Department;
import com.lc.apilc.models.entity.User;
import com.lc.apilc.services.DepartmentService;
import com.lc.apilc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication()
public class ApiLcApplication {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ApiLcApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("######### RUNNING ############");
        };
    }

}
