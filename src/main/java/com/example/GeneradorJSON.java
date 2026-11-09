package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GeneradorJSON {

    public static void generar(List<Libro> libros, String ruta) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(libros);
        Files.writeString(Path.of(ruta), json, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        var campos = LectorCampos.leerCampos("campos.dat");
        List<Libro> todos = LectorBiblioteca.leerLibros("datos-bib.dat", campos);

        List<Libro> juveniles = new ArrayList<>();
        List<Libro> resto = new ArrayList<>();
        SeparadorCatalogo.separar(todos, juveniles, resto);

        generar(resto, "biblioteca.json");
        System.out.println("JSON generado con " + resto.size() + " libros");
    }
}