# BlockingQueue

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 6. Comunicación entre hilos
- **Concepto:** Colas bloqueantes
- **Conocimientos previos:** Ejemplo anterior
- **Nivel:** 1. Inicial · **Tipo:** Demostración · **Criterios:** RA2.j

## Objetivo

Resolver el mismo problema con la biblioteca de Java.

## Clases

- [`EjemploBlockingQueue.java`](EjemploBlockingQueue.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a6_comunicacion/e06_blocking_queue/*.java
java -cp bin ud2.a6_comunicacion.e06_blocking_queue.EjemploBlockingQueue
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Consumido: 0
Producido: 0
Producido: 1
Producido: 2
Producido: 3
Producido: 4
Producido: 5
Consumido: 1
Consumido: 2
Consumido: 3
Producido: 6
Producido: 7
...
```

## Qué debes observar

- No se pierde ningún dato.
- El orden de los mensajes de consola puede cruzarse.

## Continuación

Siguiente: [Semáforo: recurso limitado](../../a7_semaforos/e01_recurso_limitado/README.md)
