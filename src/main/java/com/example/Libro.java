package com.example;

import java.util.ArrayList;
import java.util.List;

public class Libro {

    private int isbn;
    private String autor;
    private String titulo;
    private List<String> generos = new ArrayList<>();
    private double precio;
    private int fechaPublicacion;
    private String sede;

    public Libro() {
    }

    public Libro(int isbn, String autor, String titulo, List<String> generos,
                 double precio, int fechaPublicacion, String sede) {
        this.isbn = isbn;
        this.autor = autor;
        this.titulo = titulo;
        this.generos = generos;
        this.precio = precio;
        this.fechaPublicacion = fechaPublicacion;
        this.sede = sede;
    }

    public int getIsbn() { return isbn; }
    public void setIsbn(int isbn) { this.isbn = isbn; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<String> getGeneros() { return generos; }
    public void setGeneros(List<String> generos) { this.generos = generos; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(int fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public String getSede() { return sede; }
    public void setSede(String sede) { this.sede = sede; }

    @Override
    public String toString() {
        return "Libro{isbn=" + isbn + ", autor=" + autor + ", titulo=" + titulo
                + ", generos=" + generos + ", precio=" + precio
                + ", fechaPublicacion=" + fechaPublicacion + ", sede=" + sede + "}";
    }
}