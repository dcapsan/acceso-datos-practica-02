package com.example;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.List;

public class ModificadorSede {

    public static void actualizar(String ruta, List<Libro> todos,
            LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> campos) throws IOException {

        int tamanoRegistro = 0;
        int posicionSede = 0;

        for (var campo : campos) {
            if (campo.getKey().equals("sede")) {
                posicionSede = tamanoRegistro;
            }
            int veces = campo.getKey().equals("genero") ? 2 : 1;
            tamanoRegistro += campo.getValue() * veces;
        }

        byte[] nuevaSede = "JUVENIL".getBytes(StandardCharsets.ISO_8859_1);

        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            for (int i = 0; i < todos.size(); i++) {
                if (SeparadorCatalogo.esJuvenil(todos.get(i))) {
                    raf.seek((long) i * tamanoRegistro + posicionSede);
                    raf.write(nuevaSede);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        var campos = LectorCampos.leerCampos("campos.dat");
        List<Libro> todos = LectorBiblioteca.leerLibros("datos-bib.dat", campos);

        actualizar("datos-bib.dat", todos, campos);

        List<Libro> despues = LectorBiblioteca.leerLibros("datos-bib.dat", campos);
        int juvenil = 0;
        int central = 0;
        for (Libro l : despues) {
            if (l.getSede().equals("JUVENIL")) juvenil++;
            if (l.getSede().equals("CENTRAL")) central++;
        }
        System.out.println("Sede JUVENIL: " + juvenil + " | Sede CENTRAL: " + central);
    }
}