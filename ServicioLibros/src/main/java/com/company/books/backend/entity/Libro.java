package com.company.books.backend.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Libro {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	private String nombreLibro;
	
	private String nombreAutor;
	
	private String fechaPublicacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Libro(Long id, String nombreLibro, String nombreAutor, String fechaPublicacion) {
		super();
		this.id = id;
		this.nombreLibro = nombreLibro;
		this.nombreAutor = nombreAutor;
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public Libro() {		
	}

}
