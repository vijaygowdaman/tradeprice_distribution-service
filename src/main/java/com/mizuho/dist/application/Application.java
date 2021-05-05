package com.mizuho.dist.application;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * Entry point for the distribution service application to distribute the traded instruments details
 * to the downstream clients. 
 *
 */
@SpringBootApplication
//@ImportResource(value = {"classpath:distribution-integration.xml"})
public class Application {

	public static void main(String[] args) {
		
		 SpringApplication.run(Application.class, args);
	}
	
	/**
	 * Utility method to inspect the beans provided by the Spring Boot
	 * @param ctx
	 * @return
	 */
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
