package com.libros.desafioAlura.repository;



import com.libros.desafioAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTitulo(String titulo);

    @Query("SELECT DISTINCT l FROM Libro l WHERE :idioma MEMBER OF l.idiomas")
    List<Libro> buscarPorIdioma(String idioma);

}
