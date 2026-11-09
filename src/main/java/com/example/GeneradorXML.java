package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GeneradorXML {

    private static void añadirTexto(Document doc, Element padre, String nombre, String texto) {
        Element e = doc.createElement(nombre);
        e.setTextContent(texto);
        padre.appendChild(e);
    }

    public static void generar(List<Libro> libros, String ruta) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

        Element biblioteca = doc.createElement("biblioteca");
        doc.appendChild(biblioteca);

        for (Libro libro : libros) {
            Element el = doc.createElement("libro");
            el.setAttribute("isbn", String.valueOf(libro.getIsbn()));

            añadirTexto(doc, el, "autor", libro.getAutor());
            añadirTexto(doc, el, "titulo", libro.getTitulo());

            Element clasificacion = doc.createElement("clasificacion");
            for (String genero : libro.getGeneros()) {
                añadirTexto(doc, clasificacion, "genero", genero);
            }
            el.appendChild(clasificacion);

            añadirTexto(doc, el, "precio", String.valueOf(libro.getPrecio()));
            añadirTexto(doc, el, "fechaPublicacion", String.valueOf(libro.getFechaPublicacion()));
            añadirTexto(doc, el, "sede", libro.getSede());

            biblioteca.appendChild(el);
        }

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        t.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
    }

    public static void main(String[] args) throws Exception {
        var campos = LectorCampos.leerCampos("campos.dat");
        List<Libro> todos = LectorBiblioteca.leerLibros("datos-bib.dat", campos);

        List<Libro> juveniles = new ArrayList<>();
        List<Libro> resto = new ArrayList<>();
        SeparadorCatalogo.separar(todos, juveniles, resto);

        for (Libro libro : juveniles) {
            libro.setSede("JUVENIL");
        }

        generar(juveniles, "biblioteca-juvenil.xml");
        System.out.println("XML generado con " + juveniles.size() + " libros");
    }
}