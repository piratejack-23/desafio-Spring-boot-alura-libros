package com.libros.desafioAlura.service;

public interface IConvierteDatos {
    <T>  T obtenerDatos(String json,Class<T> clase);
}
