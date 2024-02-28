package com.company.books.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.books.backend.entity.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{

	
	List<Libro> findByNombreLibro(String nombreDeLibro);
	
	List<Libro> findByFechaPublicacion(String fechaDePublicacion);
	
	List<Libro> findByNombreAutor(String autor);

	List<Libro> findByOrderByFechaPublicacionAsc();

	List<Libro> findByOrderByNombreAutor();

}
