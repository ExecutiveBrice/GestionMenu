package com.wild.corp;

import com.wild.corp.configuration.JpaConfiguration;
import com.wild.corp.configuration.SecurityConfig;
import com.wild.corp.model.RecetteType;
import com.wild.corp.service.JourService;
import com.wild.corp.service.RecetteTypeService;
import com.wild.corp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;


@Import({JpaConfiguration.class, SecurityConfig.class})
@SpringBootApplication(scanBasePackages={"com.wild.corp"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class RunWebApp {

	@Autowired
	private RecetteTypeService recetteTypeService;

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(RunWebApp.class);
		springApplication.addListeners(new ApplicationPidFileWriter("gestion_benevole.pid"));
		springApplication.run(args);
	}


	@PostConstruct
	private void init() {

		recetteTypeService.add("Apéritifs",1);
		recetteTypeService.add("Entrées",2);
		recetteTypeService.add("Plats",3);
		recetteTypeService.add("Desserts",4);
		recetteTypeService.add("Bases",5);
		recetteTypeService.add("Boissons",6);
	}
}