# Práctica 02 - Acceso a datos: Biblioteca

Reparto del catálogo de una biblioteca entre dos sedes (CENTRAL y JUVENIL).

## Ficheros de entrada
- `datos-bib.dat`: catálogo en fichero de acceso aleatorio (campos de tamaño fijo).
- `campos.dat`: objeto serializado con el nombre y tamaño en bytes de cada campo.
- `biblioteca.xsd`: esquema para validar el XML.

## Progreso
- [x] Bloque 1: lectura de `campos.dat` (`LectorCampos`)
- [x] Bloque 2: clase `Libro`
- [x] Bloque 3: lectura de `datos-bib.dat`
- [x] Bloque 4: separar libros por género
- [x] Bloque 5: generar `biblioteca-juvenil.xml`
- [ ] Bloque 6: generar `biblioteca.json`
- [ ] Bloque 7: validar el XML con el XSD
- [ ] Bloque 8: actualizar la sede en `datos-bib.dat`

## Bloque 1: lectura de campos.dat
`campos.dat` contiene un `LinkedHashSet<AbstractMap.SimpleEntry<String,Integer>>`.
Se lee con `ObjectInputStream`. El set mantiene el orden de los campos.
El campo `genero` aparece una vez, pero se leerá dos veces en el fichero de acceso aleatorio.
Campos y tamaños leídos de `campos.dat`:
isbn (4), autor (20), titulo (30), genero (15, dos veces), precio (8), fechaPublicacion (4), sede (10).
Tamaño de cada registro: 106 bytes.

## Bloque 2: clase Libro
Clase `Libro` con los 7 campos. `generos` es una lista de 2 elementos.

## Bloque 3: lectura de datos-bib.dat
Clase `LectorBiblioteca`. Se usa `RandomAccessFile` y se recorren los campos en el orden de `campos.dat`.
`genero` se lee dos veces por libro. Registro de 106 bytes, 30 libros.
Los textos del fichero están en ISO-8859-1 (1 byte por carácter), por eso se lee con ese charset.
Ejecutar: java -cp target\classes com.example.LectorBiblioteca

## Bloque 4: separar por género
Clase `SeparadorCatalogo`. Un libro es juvenil si alguno de sus dos géneros es Infantil o Juvenil.
Se separa por género, no por la sede que traiga el fichero.
Ejecutar: java -cp target\classes com.example.SeparadorCatalogo

## Bloque 5: generar biblioteca-juvenil.xml
Clase `GeneradorXML`, con DOM (incluido en Java). Raíz `<biblioteca>` y un `<libro isbn="...">` por libro, en el orden del XSD.
Los libros juveniles se escriben con sede JUVENIL.
Ejecutar: java -cp target\classes com.example.GeneradorXML

## Cómo compilar y ejecutar
Se requiere JDK 17 o posterior.

mvn compile
java -cp target\classes com.example.LectorCampos