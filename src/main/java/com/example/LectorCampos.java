package es.dario;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.AbstractMap;
import java.util.LinkedHashSet;

public class LectorCampos {

    @SuppressWarnings("unchecked")
    public static LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>> leerCampos(String ruta)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (LinkedHashSet<AbstractMap.SimpleEntry<String, Integer>>) ois.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        var campos = leerCampos("campos.dat");
        for (var campo : campos) {
            System.out.println(campo.getKey() + " -> " + campo.getValue() + " bytes");
        }
    }
}