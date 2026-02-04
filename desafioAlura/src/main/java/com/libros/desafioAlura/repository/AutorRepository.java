package com.libros.desafioAlura.repository;

import com.libros.desafioAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.diaMuerte >:fecha AND a.diaNacimiento < :fecha")
    List<Autor> obtenerAutoresVivos(Integer fecha);

}
