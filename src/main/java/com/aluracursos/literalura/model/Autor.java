package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fechaDeNacimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Libro> libros;

    public Autor() {

    }
    public Autor(DatosLibro dl, DatosAutor d){
        this.nombre = d.nombre();
        this.fechaDeNacimiento = d.fechaDeNacimiento();


    }
    public Autor(DatosAutor d){
        this.nombre = d.nombre();
        this.fechaDeNacimiento = d.fechaDeNacimiento();
    }

    public Autor(List<Libro> libros, String fechaDeNacimiento, String nombre, Long id) {
        this.libros = libros;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.nombre = nombre;
        this.id = id;
    }

    public Autor(String nombre, String s) {
        this.nombre = nombre;
        this.fechaDeNacimiento = s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {

        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", libros=" + libros +
                '}';
    }
}
