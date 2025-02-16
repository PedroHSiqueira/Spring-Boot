package io.github.cursospringboot.libraryapi;

import io.github.cursospringboot.libraryapi.model.Autor;
import io.github.cursospringboot.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext contexto = SpringApplication.run(Application.class, args);
		AutorRepository autorRepository = contexto.getBean(AutorRepository.class);

		exemploSalvarAutor(autorRepository);
	}

	public static void exemploSalvarAutor(AutorRepository autorRepository){
		Autor autor = new Autor();
		autor.setNome("Machado de Assis");
		autor.setNacionalidade("Brasileiro");
		autor.setDataNascimento(LocalDate.of(1839, 11, 29));

		Autor autorSalvo = autorRepository.save(autor);
		System.out.println("Autor Salvo: " + autorSalvo);
	}
}
