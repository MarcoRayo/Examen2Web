package com.company.books.backend.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.company.books.backend.entity.Libro;
import com.company.books.backend.service.LibroService;

@RestController
@RequestMapping("api/libros")
@CrossOrigin(origins="http://localhost:5173/", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.PATCH,RequestMethod.DELETE})
public class LibroController {
	
	@Autowired
	LibroService service;
	
	@GetMapping
	public List<Libro> getAll(){
		return service.getListLibro();
	}	
	
	@GetMapping("{id}")
	public Libro getByIdLibro(@PathVariable Long id) {
		return service.getById(id).orElse(null);
	}
	
	@PostMapping
	public ResponseEntity<String> saveLibro(@RequestPart("nombre") String nombre,
											@RequestPart("autor") String autor,
											@RequestPart("fecha") String fecha) {
		Libro libro = new Libro();
		libro.setNombreLibro(nombre);
		libro.setFechaPublicacion(fecha);
		libro.setNombreAutor(autor);
		System.out.println("Nombre: " + nombre + ", autor: " + autor+ ", fecha: " + fecha);
		service.saveLibro(libro);
		return ResponseEntity.ok("Libro registrado exitosamente");
	}
	
	public LibroController(LibroService libroServ) {
		this.service=libroServ;
	}
	
	@GetMapping("/buscarLibroPorNombre")
	public Set<Libro> buscarLibroPorNombre(@RequestParam("nombreDeLibro")String nombreDeLibro){
		return service.findPeliculasNombre(nombreDeLibro);
	}
	
	@GetMapping("/buscarLibroPorAutor")
	public List<Libro> buscarLibroPorAutor(){
		return service.getLibroOrderedByAutor();
	}
	
	@GetMapping("/buscarLibroPorPublicacionFecha")
	public List<Libro> buscarLibroPorImagen(){
		return service.findByOrderByFechaPublicacionAsc();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLibro(@PathVariable Long id){
		boolean eliminar= service.deleteLibro(id);
		if (eliminar) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@PatchMapping("/{id}")
	public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libro){
		Libro updateLibro = service.updateLibro(id, libro);
		if (updateLibro != null) {
			return ResponseEntity.ok(updateLibro);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

}
