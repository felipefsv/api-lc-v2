package com.lc.apilc;

import com.lc.apilc.models.entity.Department;
import com.lc.apilc.models.entity.User;
import com.lc.apilc.models.services.DepartmentService;
import com.lc.apilc.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication()
public class ApiLcApplication {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("appName")
	private String applicationName;

	public static void main(String[] args) {
		SpringApplication.run(ApiLcApplication.class, args);
	}

	@Bean
	public CommandLineRunner run () {
		return args -> {
			List<Department> departments = this.departmentService.getDepartments();
			System.out.println("######### DEPARTAMENTOS ############");
			departments.forEach(department -> System.out.println(department.toString()));
			System.out.println("####################################");
			List<User> users = this.userService.getUsers();
			System.out.println("######### USUÁRIOS ############");
			users.forEach(user -> System.out.println(user.toString()));
			System.out.println("###############################");
			System.out.println("RUNNING: " + this.applicationName);
		};
	}

}
