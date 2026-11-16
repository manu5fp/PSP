# Runnable y lambdas

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 2. Creación, inicio y finalización de hilos
- **Concepto:** Tareas con `Runnable`
- **Conocimientos previos:** Ejemplo anterior, lambdas
- **Nivel:** 1. Inicial · **Tipo:** Demostración · **Criterios:** RA2.b

## Objetivo

Separar la tarea del hilo.

## Clases

- [`HilosLambda.java`](HilosLambda.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a2_creacion/e02_runnable_lambda/*.java
java -cp bin ud2.a2_creacion.e02_runnable_lambda.HilosLambda
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Hola desde un hilo con lambda
Hola desde un hilo con lambda
Hola desde un hilo con clase anónima
```

## Qué debes observar

- Las tres formas ejecutan una tarea `Runnable`.

## Continuación

Siguiente: [start() frente a run()](../e03_start_run/README.md)
