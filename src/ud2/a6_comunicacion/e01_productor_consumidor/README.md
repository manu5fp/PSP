# Productor-consumidor simple

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 6. Comunicación entre hilos
- **Concepto:** `wait()` y `notifyAll()`
- **Conocimientos previos:** `synchronized`
- **Nivel:** 2. Desarrollo · **Tipo:** Guiado · **Criterios:** RA2.e, RA2.f

## Objetivo

Coordinar dos hilos con un dato compartido.

## Clases

- [`Cola.java`](Cola.java)
- [`Consumidor.java`](Consumidor.java)
- [`EjemploPC.java`](EjemploPC.java) (contiene el `main` que debes ejecutar)
- [`Productor.java`](Productor.java)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a6_comunicacion/e01_productor_consumidor/*.java
java -cp bin ud2.a6_comunicacion.e01_productor_consumidor.EjemploPC
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Consumido: 0
Producido: 0
Producido: 1
Producido: 2
Consumido: 1
Producido: 3
Consumido: 2
Producido: 4
Consumido: 3
Consumido: 4
```

## Qué debes observar

- Los valores se consumen en orden y sin repetirse.
- Los mensajes pueden aparecer cruzados porque se imprimen fuera del monitor.

## Continuación

Siguiente: [Lectores y escritor](../e02_libros/README.md)
