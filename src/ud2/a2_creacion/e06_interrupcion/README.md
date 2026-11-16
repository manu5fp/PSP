# Cancelar con interrupt()

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 2. Creación, inicio y finalización de hilos
- **Concepto:** Interrupción de hilos
- **Conocimientos previos:** `sleep`, excepciones
- **Nivel:** 1. Inicial · **Tipo:** Guiado · **Criterios:** RA2.b

## Objetivo

Cancelar una tarea en curso de forma cooperativa.

## Clases

- [`EjemploInterrupt.java`](EjemploInterrupt.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a2_creacion/e06_interrupcion/*.java
java -cp bin ud2.a2_creacion.e06_interrupcion.EjemploInterrupt
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Descargando: 0%
Descargando: 10%
Descargando: 20%
Descargando: 30%
Descargando: 40%
Descargando: 50%
Descargando: 60%
Descargando: 70%
Descargando: 80%
Descargando: 90%
Descargando: 100%
Descarga completada.
...
```

## Qué debes observar

- La descarga se cancela hacia el 30 o 40 %.
- `interrupt()` no mata el hilo: es el `catch` quien decide terminar.

## Continuación

Siguiente: [Prioridades e interrupción](../../a3_estados/e01_prioridades/README.md)
