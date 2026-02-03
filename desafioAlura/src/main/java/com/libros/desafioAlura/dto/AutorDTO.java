package com.libros.desafioAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDTO(
        @JsonAlias("authors") List<AutorDTO> autores,
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year")Integer diaNacimiento,
        @JsonAlias("death_year")Integer diaMuerte


) {
}
