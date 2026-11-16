# Semáforo: recurso limitado

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** `acquire()` y `release()`
- **Conocimientos previos:** Hilos
- **Nivel:** 1. Inicial · **Tipo:** Demostración · **Criterios:** RA2.f, RA2.j

## Objetivo

Limitar a dos los hilos que usan un recurso.

## Clases

- [`EjemploSemaforo.java`](EjemploSemaforo.java) (contiene el `main` que debes ejecutar)
- [`RecursoCompartido.java`](RecursoCompartido.java)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e01_recurso_limitado/*.java
java -cp bin ud2.a7_semaforos.e01_recurso_limitado.EjemploSemaforo
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Hilo 1 usando el recurso...
Hilo 0 usando el recurso...
Hilo 0 terminó.
Hilo 2 usando el recurso...
Hilo 1 terminó.
Hilo 3 usando el recurso...
Hilo 2 terminó.
Hilo 4 usando el recurso...
Hilo 4 terminó.
Hilo 5 usando el recurso...
Hilo 3 terminó.
Hilo 5 terminó.
```

## Qué debes observar

- Nunca hay más de dos usando el recurso.
- `acquire()` va fuera del `try` cuyo `finally` libera.

## Continuación

Siguiente: [Cafetera con semáforo](../e02_cafetera_semaforo/README.md)
