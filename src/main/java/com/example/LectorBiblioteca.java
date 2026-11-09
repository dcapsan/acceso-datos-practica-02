package com.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class LectorBiblioteca {
    private static String leerTexto(RandomAccessFile raf, int bytes) throws IOException {
        byte[] buffer = new byte[bytes];
        raf.readFully(buffer);
        return new String(buffer, StandardCharsets.UTF_8).trim();
    }

    public static List<Libro> leerLibros(String ruta,
            LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> campos) throws IOException {

        List<Libro> libros = new ArrayList<>();

        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                Libro libro = new Libro();

                for (var campo : campos) {
                    String nombre = campo.getKey();
                    int tamano = campo.getValue();

                    switch (nombre) {
                        case "isbn" -> libro.setIsbn(raf.readInt());
                        case "autor" -> libro.setAutor(leerTexto(raf, tamano));
                        case "titulo" -> libro.setTitulo(leerTexto(raf, tamano));
                        case "genero" -> {
                            libro.getGeneros().add(leerTexto(raf, tamano));
                            libro.getGeneros().add(leerTexto(raf, tamano));
                        }
                        case "precio" -> libro.setPrecio(raf.readDouble());
                        case "fechaPublicacion" -> libro.setFechaPublicacion(raf.readInt());
                        case "sede" -> libro.setSede(leerTexto(raf, tamano));
                    }
                }
                libros.add(libro);
            }
        }
        return libros;
    }

    public static void main(String[] args) throws Exception {
        var campos = LectorCampos.leerCampos("campos.dat");
        List<Libro> libros = leerLibros("datos-bib.dat", campos);

        System.out.println("Libros leídos: " + libros.size());
        for (Libro l : libros) {
            System.out.println(l);
        }
    }
}