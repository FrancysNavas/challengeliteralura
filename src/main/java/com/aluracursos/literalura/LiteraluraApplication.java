package com.aluracursos.literalura;

import com.aluracursos.literalura.principal.Principal;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {


	@Autowired
	private LibroRepository librosRepo;

	@Autowired
	private AutorRepository autorRepo;

	public static void main(String[] args) {SpringApplication.run(LiteraluraApplication.class, args);
	}


	public void run(String... args) throws Exception {
		Principal principal = new Principal(librosRepo, autorRepo);
		principal.muestraElMenu();

	}
}
