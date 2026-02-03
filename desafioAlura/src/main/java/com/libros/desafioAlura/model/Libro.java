package com.libros.desafioAlura.model;


import com.libros.desafioAlura.dto.LibroDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private List<String> idiomas;
    private List<String> tematicas;
    private List<String> categorias;
    private Integer numeroDescargas;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {
    }

    public Libro(LibroDTO libroDTO) {
       this.titulo = libroDTO.titulo();
       this.idiomas = libroDTO.idiomas();
       this.tematicas =libroDTO.tematicas();
       this.categorias= libroDTO.categorias();
       this.numeroDescargas = libroDTO.numeroDescargas();

    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public List<String> getTematicas() {
        return tematicas;
    }

    public void setTematicas(List<String> tematicas) {
        this.tematicas = tematicas;
    }

    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", titulo='" + titulo +
                ", idiomas=" + idiomas +
                ", tematicas=" + tematicas +
                ", categorias=" + categorias +
                ", numeroDescargas=" + numeroDescargas +
                ", autor=" + autor ;
    }
}
