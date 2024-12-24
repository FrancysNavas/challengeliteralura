package com.aluracursos.literalura.dto;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Idioma;

public record LibroDTO(Long id,
                       String titulo,
                       String idiomas,
                       Autor autor) {
}
