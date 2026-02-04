package com.libros.desafioAlura.principal;

import com.libros.desafioAlura.dto.AutorDTO;
import com.libros.desafioAlura.dto.LibroDTO;
import com.libros.desafioAlura.dto.ResultadoDTO;
import com.libros.desafioAlura.model.Autor;
import com.libros.desafioAlura.model.Libro;
import com.libros.desafioAlura.repository.AutorRepository;
import com.libros.desafioAlura.repository.LibroRepository;
import com.libros.desafioAlura.service.ConsumoAPI;
import com.libros.desafioAlura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private String URL_API ="https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private LibroRepository repositorylibro;
    private AutorRepository repositoryautor;
    private ConvierteDatos conversor =  new ConvierteDatos();
    private String json;
    List<Libro> libros;
    List<Autor> autores;

    public Principal(LibroRepository repositorylibro, AutorRepository repositoryautor) {
        this.repositorylibro =repositorylibro;
        this.repositoryautor= repositoryautor;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """                   
                    1 - Buscar Libro web por nombre y guardarlo
                    2 - Listar libros guardados
                    3 - Listar autores guardados
                    4 - listar autores vivos en determinado año
                    5 - listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    obtenerLibroWeb();
                    break;
                case 2:
                    obtenerLibrosGuardados();
                    break;
                case 3:
                    obtenerAutoresGuardados();
                    break;
                case 4:
                    obtenerAutoresVivos();
                    break;
                case 5:
                    obtenerLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }



    private void obtenerLibroWeb() {

        System.out.println("Dame el nombre del libro que quieras buscar");
        String libroname= teclado.nextLine();
        String urlBusqueda = URL_API + "?search=" + libroname.replace(" ","+");
        json = consumoAPI.obtenerDatos(urlBusqueda);
        ResultadoDTO resultadoDTO = conversor.obtenerDatos(json, ResultadoDTO.class);
        if (resultadoDTO.libros().isEmpty()) {
            System.out.println("No se encontraron libros con ese nombre");
            return;
        }
        LibroDTO libroDTO = resultadoDTO.libros().get(0);
        AutorDTO autorDTO = libroDTO.autores().get(0);
        Autor autor = repositoryautor
                .findByNombre(autorDTO.nombre())
                .orElseGet(() -> {
                    Autor nuevoAutor = new Autor(autorDTO);
                    return repositoryautor.save(nuevoAutor);
                });

        if(repositorylibro.findByTitulo(libroDTO.titulo()).isPresent()){
            System.out.println("El libro ya fue guardado");
        }else {
            Libro nuevoLibro = new Libro(libroDTO);
            nuevoLibro.setAutor(autor);
            repositorylibro.save(nuevoLibro);
        }
        System.out.println("libro guardado correctamente");

    }

    private void obtenerLibrosGuardados() {

        libros = repositorylibro.findAll();
        libros.forEach(System.out::println);

    }

    private void obtenerAutoresGuardados() {

        autores = repositoryautor.findAll();
        autores.forEach(System.out::println);
    }

    private void obtenerAutoresVivos() {
        System.out.println("Dame el año en el cual quieres consultar que autores estan vivos");
        Integer fecha = teclado.nextInt();
        autores = repositoryautor.obtenerAutoresVivos(fecha);
        if (autores.isEmpty()){
            System.out.println("no hay actores vivos en esa fecha registrados");
        }else {
            autores.forEach(System.out::println);
        }
    }

    private void obtenerLibroPorIdioma() {
        System.out.println("""
                Inglés (en)
                Espanol (es)
                Frances (fr)
                """);

        System.out.println("elige la nomenclatura del idioma de los cuales saldran los libros");
        String idioma = teclado.nextLine();;
        libros = repositorylibro.buscarPorIdioma(idioma);
        if(libros.isEmpty()){
            System.out.println("no hay libros con ese idioma registrados");
        }else{
            libros.forEach(System.out::println);
        }



    }

}
