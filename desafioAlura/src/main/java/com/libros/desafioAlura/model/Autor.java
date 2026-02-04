package com.libros.desafioAlura.model;


import com.libros.desafioAlura.dto.AutorDTO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer diaNacimiento;
    private Integer diaMuerte;
    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {
    }

    public Autor(AutorDTO autorDTO) {
        this.nombre = autorDTO.nombre();
        this.diaNacimiento = autorDTO.diaNacimiento();
        this.diaMuerte= autorDTO.diaMuerte();
    }


    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDiaNacimiento() {
        return diaNacimiento;
    }

    public void setDiaNacimiento(Integer diaNacimiento) {
        this.diaNacimiento = diaNacimiento;
    }

    public Integer getDiaMuerte() {
        return diaMuerte;
    }

    public void setDiaMuerte(Integer diaMuerte) {
        this.diaMuerte = diaMuerte;
    }

    @Override
    public String toString() {
        String librosString = "";

        if (libros != null && !libros.isEmpty()) {
            for (Libro libro : libros) {
                librosString += "  • " + libro.getTitulo() + "\n";
            }
        }
        return String.format("""
        Autor
        ━━━━━━━━━━━━━━━━━━━━━━
        ID: %d
        Nombre: %s
        Nacimiento: %s
        Fallecimiento: %s
        
        %s
        """, id, nombre, diaNacimiento != null ? diaNacimiento : "Desconocido", diaMuerte != null ? diaMuerte : "Vivo", librosString);
    }
}
