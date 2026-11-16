# Heredar de Thread

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 2. Creación, inicio y finalización de hilos
- **Concepto:** Creación de hilos con `Thread`
- **Conocimientos previos:** Herencia, clases anónimas
- **Nivel:** 1. Inicial · **Tipo:** Demostración · **Criterios:** RA2.b

## Objetivo

Crear un primer hilo, con una clase propia y una anónima.

## Clases

- [`MiHilo.java`](MiHilo.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a2_creacion/e01_heredar_thread/*.java
java -cp bin ud2.a2_creacion.e01_heredar_thread.MiHilo
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Hola, soy el hilo Thread-0
Hola, soy el hilo Thread-1
```

## Qué debes observar

- El orden de los mensajes no está garantizado.

## Continuación

Siguiente: [Runnable y lambdas](../e02_runnable_lambda/README.md)
