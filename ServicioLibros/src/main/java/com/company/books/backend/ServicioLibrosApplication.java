package com.company.books.backend;

import com.company.books.backend.entity.Libro;
import com.company.books.backend.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ServicioLibrosApplication {
	@Autowired
	private LibroRepository repo;
	public static void main(String[] args) {
		SpringApplication.run(ServicioLibrosApplication.class, args);
	}
	public List<Libro> getLibroOrderedByPublicationDateDesc() {
		return repo.findByOrderByFechaPublicacionAsc();
	}

	public List<Libro> getLibroOrderedByAutor() {
		return repo.findByOrderByNombreAutor();
	}
}
