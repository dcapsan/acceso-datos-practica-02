package com.example;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class ValidadorXML {

    public static boolean validar(String rutaXml, String rutaXsd) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(rutaXsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(rutaXml)));
            return true;
        } catch (SAXException e) {
            System.out.println("XML NO válido: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error al validar: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        if (validar("biblioteca-juvenil.xml", "biblioteca.xsd")) {
            System.out.println("El XML es válido según biblioteca.xsd");
        }
    }
}