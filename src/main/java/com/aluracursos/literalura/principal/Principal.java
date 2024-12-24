package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private List<DatosLibro> datosLibros = new ArrayList<DatosLibro>();
    private LibroRepository librosRepository;
    private AutorRepository autorRepository;
    private List<Libro> libros;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.librosRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    *****      Bienvenidos a LiterAlura        ********
                    *                                                 *
                    *   Ingrese la opción del menú que le interese:   *
                    *    1 - Buscar libro por titulo                  *
                    *    2 - Lista de Libros registrados              *
                    *    3 - Lista de Autores registrados             *
                    *    4 - Lista de Autores por año de vida         *
                    *    5 - Lista de Libros por Idioma               *
                    *    0 - Salir de la aplicación                   *
                    *                                                 *
                    ---------------------------------------------------
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresPorAnio();
                    break;
                case 5:
                    listarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroWeb() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search="+ tituloLibro.replace(" ", "+"));

        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .findFirst();

        if (libroBuscado.isPresent()) {

            Autor autor = new Autor(libroBuscado.get().autor().get(0).nombre(),
                    libroBuscado.get().autor().get(0).fechaDeNacimiento());
            autorRepository.save(autor);

            Libro libro = new Libro(libroBuscado.get().titulo(),
                    autor,
                    libroBuscado.get().idiomas().get(0));
            librosRepository.save(libro);

            System.out.println("-----------   LIBRO   ---------------- ");
            System.out.println("  Nombre del Libro: " + libro.getTitulo() +" \n"+
                               "  Autor: " + libro.getAutor().getNombre() +" \n"+
                               "  Idioma: " + libro.getIdiomas());
        } else {
           System.out.println(">>> Libro no encontrado o el libro está en un idioma no permitido. <<<");
        }
    }

    private void listarLibros(){
        List<Libro> listaLibros = librosRepository.findAll();
        System.out.println("----------------------------------------------- ");
        System.out.println("-----    Lista de Libros en LiterAlura    ----- ");
        System.out.println("----------------------------------------------- ");
        listaLibros.forEach(libro ->
                System.out.println(" Titulo: " + libro.getTitulo() + " \n"
                        + " Autor: " + libro.getAutor().getNombre()+ " \n"
                        + " Idioma: " + libro.getIdiomas() + " \n"
                        + "--------------------------------------------------"));
        System.out.println("-------------------------------------------------");
    }

    private void listarAutores(){
        List<Autor> listaAutores = autorRepository.findAll();
        System.out.println("------------------------------------------------ ");
        System.out.println("-----    Lista de Autores en LiterAlura    ----- ");
        System.out.println("------------------------------------------------ ");
        listaAutores.forEach(a ->
                System.out.println(" Nombre del Autor: " + a.getNombre() + " \n"
                                    + " Fecha de Nacimiento: " + a.getFechaDeNacimiento() + " \n"
                                    + "------------------------------------------------"));
        System.out.println("-------------------------------------------------");
    }

    private void listarAutoresPorAnio(){
        System.out.println("Ingrese la fecha que desea ubicar al autor: ");
        var fechaTeclado = teclado.nextLine();

        List<Autor> autoresPorAnio = autorRepository.findByAutoresAnio(fechaTeclado);

        autoresPorAnio.forEach(a ->
                System.out.printf("----Fecha ingresada: " + fechaTeclado + "---------\n" +
                                " Nombre del autor: " + a.getNombre() + " \n" +
                                " Fecha de Nacimiento: " + a.getFechaDeNacimiento() + " \n"));
    }

    private void listarLibroPorIdioma(){
        var opcion = "";
            var idioma = """
                        Seleccione el idioma por el que desea hacer la busqueda:
                        es - Español
                        en - Inglés
                        fr - Francés
                        pt - Portugués
                        """;
            System.out.println(idioma);
            opcion = teclado.nextLine();

            if (opcion.equals("es") || opcion.equals("en") || opcion.equals("hu")){
                List<Libro> librosPorIdioma = librosRepository.findByIdioma(opcion);
                System.out.println("--------Listado de Libros por el idioma: " + opcion);
                librosPorIdioma.forEach(l ->
                        System.out.println(" Titulo: " + l.getTitulo() + " \n"
                                + " Autor: " + l.getAutor().getNombre()+ " \n"
                                + " Idioma: " + l.getIdiomas() + " \n"
                                + "--------------------------------------------------"));
                System.out.println("-------------------------------------------------");
            }else{
                System.out.println("La opción ingresada no tiene registros en la base de datos.");
            }
    }

}