package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dto.AutorDTO;
import com.aluracursos.literalura.dto.LibroDTO;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private AutorRepository repositorio;
    private LibroRepository libroRepository;

    public List<AutorDTO> obtenerAutoresPorAnio(String fechaTeclado){
        return repositorio.findByAutoresAnio(fechaTeclado).stream()
                .map(a -> new AutorDTO(a.getId(), a.getNombre(), a.getFechaDeNacimiento(), a.getLibros()))
                .collect(Collectors.toList());
    }

    public  List<LibroDTO> obtenerLibrosPorIdioma(String opcion){
        return libroRepository.findByIdioma(opcion).stream()
                .map(a -> new LibroDTO(a.getId(), a.getTitulo(), a.getIdiomas(), a.getAutor()))
                .collect(Collectors.toList());
    }
}
