# AtomicInteger

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 4. Compartir información. Condiciones de carrera
- **Concepto:** Operaciones atómicas
- **Conocimientos previos:** Ejemplo anterior
- **Nivel:** 1. Inicial · **Tipo:** Demostración · **Criterios:** RA2.j, RA2.k

## Objetivo

Comparar un `int` con un `AtomicInteger`.

## Clases

- [`AtomicIntegerExample.java`](AtomicIntegerExample.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a4_compartir/e02_atomic/*.java
java -cp bin ud2.a4_compartir.e02_atomic.AtomicIntegerExample
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
--- Demostración con int normal (NO Thread-Safe) ---
Valor final del contador NO atómico: 98974
Valor esperado (NO atómico): 100000
¡Advertencia: Hubo una condición de carrera en el contador NO atómico!

--- Demostración con AtomicInteger (Thread-Safe) ---
Valor final del contador ATÓMICO: 100000
Valor esperado (ATÓMICO): 100000
¡El contador ATÓMICO es correcto, como se esperaba!
```

## Qué debes observar

- Con menos carga, el `int` podría salir bien por casualidad: no demuestra nada.

## Continuación

Siguiente: [Contador con synchronized](../../a5_synchronized/e01_contador_seguro/README.md)
