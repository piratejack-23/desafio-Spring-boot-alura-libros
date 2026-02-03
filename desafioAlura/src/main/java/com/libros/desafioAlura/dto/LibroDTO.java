package com.libros.desafioAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDTO(

         @JsonAlias("title") String titulo,
         @JsonAlias("languages") List<String> idiomas,
         @JsonAlias("subjects") List<String> tematicas,
         @JsonAlias("bookshelves") List<String> categorias,
         @JsonAlias("download_count") Integer numeroDescargas,
         @JsonAlias("authors")List<AutorDTO> autores
) {

}
