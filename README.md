# Práctica 02 - Acceso a datos: Biblioteca

Reparto del catálogo de una biblioteca entre dos sedes (CENTRAL y JUVENIL).

## Ficheros de entrada
- `datos-bib.dat`: catálogo en fichero de acceso aleatorio (campos de tamaño fijo).
- `campos.dat`: objeto serializado con el nombre y tamaño en bytes de cada campo.
- `biblioteca.xsd`: esquema para validar el XML.

## Progreso
- [x] Bloque 1: lectura de `campos.dat` (`LectorCampos`)
- [ ] Bloque 2: clase `Libro`
- [ ] Bloque 3: lectura de `datos-bib.dat`
- [ ] Bloque 4: separar libros por género
- [ ] Bloque 5: generar `biblioteca-juvenil.xml`
- [ ] Bloque 6: generar `biblioteca.json`
- [ ] Bloque 7: validar el XML con el XSD
- [ ] Bloque 8: actualizar la sede en `datos-bib.dat`

## Bloque 1: lectura de campos.dat
`campos.dat` contiene un `LinkedHashSet<AbstractMap.SimpleEntry<String,Integer>>`.
Se lee con `ObjectInputStream`. El set mantiene el orden de los campos.
El campo `genero` aparece una vez, pero se leerá dos veces en el fichero de acceso aleatorio.