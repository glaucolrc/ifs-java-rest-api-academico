package br.edu.ifs.academico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Classe que inicia a aplicação Spring Boot
 * @author Glauco Luiz Rezende de Carvalho
 * @email glauco.carvalho@academico.ifs.edu.br
 * @Date: 30/04/2023
 * @version 1.03
 **/
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
