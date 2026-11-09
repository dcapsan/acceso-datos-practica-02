package com.example;

import java.util.ArrayList;
import java.util.List;

public class SeparadorCatalogo {

    public static boolean esJuvenil(Libro libro) {
        for (String genero : libro.getGeneros()) {
            if (genero.equalsIgnoreCase("Infantil") || genero.equalsIgnoreCase("Juvenil")) {
                return true;
            }
        }
        return false;
    }

    public static void separar(List<Libro> todos, List<Libro> juveniles, List<Libro> resto) {
        for (Libro libro : todos) {
            if (esJuvenil(libro)) {
                juveniles.add(libro);
            } else {
                resto.add(libro);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        var campos = LectorCampos.leerCampos("campos.dat");
        List<Libro> todos = LectorBiblioteca.leerLibros("datos-bib.dat", campos);

        List<Libro> juveniles = new ArrayList<>();
        List<Libro> resto = new ArrayList<>();
        separar(todos, juveniles, resto);

        System.out.println("Total: " + todos.size());
        System.out.println("Juveniles (XML): " + juveniles.size());
        System.out.println("Resto (JSON): " + resto.size());

        System.out.println("--- Juveniles ---");
        for (Libro l : juveniles) {
            System.out.println(l.getIsbn() + " " + l.getTitulo() + " " + l.getGeneros());
        }
    }
}