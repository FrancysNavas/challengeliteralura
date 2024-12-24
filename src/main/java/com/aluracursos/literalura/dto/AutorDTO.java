package com.aluracursos.literalura.dto;

import com.aluracursos.literalura.model.Libro;

import java.util.List;

public record AutorDTO(Long id,
                       String nombre,
                       String fechaDeNacimiento,
                       List<Libro> libros) {
}
