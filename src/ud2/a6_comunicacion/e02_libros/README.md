# Lectores y escritor

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 6. Comunicación entre hilos
- **Concepto:** Espera de una condición
- **Conocimientos previos:** Ejemplo anterior
- **Nivel:** 2. Desarrollo · **Tipo:** Guiado · **Criterios:** RA2.f

## Objetivo

Hacer que unos hilos esperen a que otro complete un trabajo.

## Clases

- [`Escritor.java`](Escritor.java)
- [`Lector.java`](Lector.java)
- [`Libro.java`](Libro.java)
- [`LibrosApp.java`](LibrosApp.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a6_comunicacion/e02_libros/*.java
java -cp bin ud2.a6_comunicacion.e02_libros.LibrosApp
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Diego esperando que se escriba el libro: Watchmen
Manu esperando que se escriba el libro: Watchmen
Alan Moore está escribiendo el libro: Watchmen
El libro se ha terminado
despertando a los lectores...
Diego: El libro se ha escrito!! puedes leerlo
Diego ha leído el libro Watchmen puede leerlo el siguiente ...
Manu: El libro se ha escrito!! puedes leerlo
Manu ha leído el libro Watchmen puede leerlo el siguiente ...
```

## Qué debes observar

- Los lectores se avisan en cadena con `notify()`.
- `wait()` va dentro de un `while`.

## Continuación

Siguiente: [Cafetera: notify y notifyAll](../e03_cafetera_notify/README.md)
