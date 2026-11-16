# Varios hilos y join()

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 2. Creación, inicio y finalización de hilos
- **Concepto:** Espera de finalización
- **Conocimientos previos:** Ejemplos anteriores
- **Nivel:** 2. Desarrollo · **Tipo:** Guiado · **Criterios:** RA2.b, RA2.c, RA2.g

## Objetivo

Lanzar varios hilos y esperar a todos.

## Clases

- [`LanzaTareas.java`](LanzaTareas.java) (contiene el `main` que debes ejecutar)
- [`TareaCompleja.java`](TareaCompleja.java)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a2_creacion/e04_join/*.java
java -cp bin ud2.a2_creacion.e04_join.LanzaTareas
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Soy el hilo Hilo-0 y mi valor de avance es 0
Soy el hilo Hilo-4 y mi valor de avance es 0
Soy el hilo Hilo-2 y mi valor de avance es 0
Soy el hilo Hilo-1 y mi valor de avance es 0
Soy el hilo Hilo-4 y mi valor de avance es 1
Soy el hilo Hilo-3 y mi valor de avance es 0
Soy el hilo Hilo-0 y mi valor de avance es 1
Soy el hilo Hilo-2 y mi valor de avance es 1
Soy el hilo Hilo-1 y mi valor de avance es 1
Soy el hilo Hilo-2 y mi valor de avance es 2
Soy el hilo Hilo-1 y mi valor de avance es 2
Soy el hilo Hilo-4 y mi valor de avance es 2
...
```

## Qué debes observar

- El mensaje final aparece siempre el último, gracias a `join()`.
- Se asignan prioridades: observa que no garantizan el orden.

## Continuación

Siguiente: [Sumar una matriz con hilos](../e05_suma_matriz/README.md)
