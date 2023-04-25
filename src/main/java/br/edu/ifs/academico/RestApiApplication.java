package br.edu.ifs.academico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Classe para iniciar a aplicação Spring Boot
 * @author Glauco Luiz Rezende de Carvalho
 * @email glauco.carvalho@academico.ifs.edu.br
 * @version 1.02
 * Date: 25/04/2023
 **/
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
