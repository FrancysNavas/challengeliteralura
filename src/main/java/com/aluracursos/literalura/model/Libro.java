package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.OptionalDouble;

import static java.lang.String.valueOf;
import static org.aspectj.util.LangUtil.split;

@Entity
@Table(name = "libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idiomas;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;


    public Libro() {
    }

    public Libro(DatosLibro datosLibro, DatosAutor datosAutor){
        this.titulo = datosLibro.titulo();
        this.idiomas = valueOf(datosLibro.idiomas());
        this.autor = new Autor();
    }

    public Libro(Long id, String titulo, String idiomas, Autor autor) {
        this.id = id;
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.autor = autor;
    }

    public Libro(DatosLibro datos) {
    }

    public Libro(Datos datos) {
    }

    public Libro(String titulo, Autor autor,String idiomas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;

    }

    public Libro(String titulo, String nombre, String idiomas) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idiomas=" + idiomas +
                ", autor=" + autor +
                '}';
    }
}
