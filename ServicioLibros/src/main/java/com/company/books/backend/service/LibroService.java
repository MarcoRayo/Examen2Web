package com.company.books.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.books.backend.entity.Libro;
import com.company.books.backend.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository repo;
	
	public List<Libro> getListLibro(){
		return repo.findAll();
	}
	
	public Optional<Libro> getById(Long id){
		return repo.findById(id);
	}
	
	public Libro saveLibro(Libro bean) {
		return repo.save(bean);
	}
	
	public Set<Libro> findPeliculasNombre(String buscar){
		List<Libro> libroNombre = repo.findByNombreLibro(buscar);
		List<Libro> nombreAutor = repo.findByNombreAutor(buscar);
		List<Libro> fechaPublicacion = repo.findByFechaPublicacion(buscar);
		Set<Libro> respuesta = new HashSet<>();
		respuesta.addAll(libroNombre);		
		respuesta.addAll(nombreAutor);
		respuesta.addAll(fechaPublicacion);
		return respuesta;
	}


	public List<Libro> getLibroOrderedByAutor() {
		return repo.findByOrderByNombreAutor();
	}

	public List<Libro> findByOrderByFechaPublicacionAsc() {
		return repo.findByOrderByFechaPublicacionAsc();
	}

	public boolean deleteLibro(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}else {
			return false;
		}
	}

	public Libro updateLibro(Long id, Libro libro) {
		Optional<Libro> libroOptionalExistente = repo.findById(id);
		if (libroOptionalExistente.isPresent()) {
			Libro LibroExistente = libroOptionalExistente.get();
			LibroExistente.setNombreAutor(libro.getNombreLibro());
			LibroExistente.setNombreAutor(libro.getNombreAutor());
			LibroExistente.setFechaPublicacion(libro.getFechaPublicacion());
			return repo.save(LibroExistente);
		}else {
			return null;
		}
	}
}
