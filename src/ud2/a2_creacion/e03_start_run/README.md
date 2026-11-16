# start() frente a run()

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 2. Creación, inicio y finalización de hilos
- **Concepto:** Diferencia entre `start()` y `run()`
- **Conocimientos previos:** Ejemplos anteriores
- **Nivel:** 1. Inicial · **Tipo:** Guiado · **Criterios:** RA2.b

## Objetivo

Comprobar que `run()` no crea concurrencia.

## Clases

- [`LanzarHilos.java`](LanzarHilos.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a2_creacion/e03_start_run/*.java
java -cp bin ud2.a2_creacion.e03_start_run.LanzarHilos start
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Con start(): hilos nuevos
Comienza la cuenta atrás!
#1 (4)
#0 (5)
#1 (3)
#0 (4)
#1 (2)
#0 (3)
#1 (1)
#0 (2)
#0 (1)
Lanzamiento (1)
...
```

## Qué debes observar

- Ejecútalo sin argumento y con `start`: compara cómo se mezclan las cuentas.
- Sin argumento, el mensaje final aparece el último porque todo es secuencial.

## Continuación

Siguiente: [Varios hilos y join()](../e04_join/README.md)
