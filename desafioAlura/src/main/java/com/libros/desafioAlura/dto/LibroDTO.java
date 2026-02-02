package com.libros.desafioAlura.dto;

import com.libros.desafioAlura.model.Autor;
import java.util.List;

public record LibroDTO(

         String titulo,
         String idiomas,
         List<String> tematicas,
         List<String> categorias,
         int numeroDescargas,
         Autor autor
) {

}
