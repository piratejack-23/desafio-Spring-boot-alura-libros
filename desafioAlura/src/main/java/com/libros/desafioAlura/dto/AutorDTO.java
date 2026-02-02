package com.libros.desafioAlura.dto;

import com.libros.desafioAlura.model.Libro;
import java.util.List;

public record AutorDTO(

         String nombre,
         String diaNacimiento,
         String diaMuerte,
         List<Libro> libros

) {
}
