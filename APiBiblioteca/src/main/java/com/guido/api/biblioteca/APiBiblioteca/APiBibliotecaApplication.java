package com.guido.api.biblioteca.APiBiblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class APiBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(APiBibliotecaApplication.class, args);
	}

        @GetMapping("/hello")
    public String hello() {
        String nombre = "guido";
      return nombre;
    }
}
