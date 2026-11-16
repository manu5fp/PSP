# Prioridades e interrupción

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 3. Estados y prioridades de los hilos
- **Concepto:** `setPriority()` y sus límites
- **Conocimientos previos:** `interrupt()`
- **Nivel:** 2. Desarrollo · **Tipo:** Demostración · **Criterios:** RA2.d, RA2.g

## Objetivo

Ver que una prioridad más alta no garantiza terminar antes.

## Clases

- [`Calculador.java`](Calculador.java)
- [`ControlHilosCalculador.java`](ControlHilosCalculador.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a3_estados/e01_prioridades/*.java
java -cp bin ud2.a3_estados.e01_prioridades.ControlHilosCalculador
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Hilo rápido calculando...0
Hilo lento calculando...0
Hilo rápido calculando...1
Hilo lento calculando...1
Hilo rápido calculando...2
Hilo lento calculando...2
Hilo rápido calculando...3
Hilo lento calculando...3
Hilo rápido calculando...4
Hilo lento calculando...4
Hilo rápido finalizado.
Hilo lento finalizado.
...
```

## Qué debes observar

- Puede que el hilo de prioridad baja termine o sea interrumpido.
- Ejecútalo varias veces: el resultado puede cambiar.

## Continuación

Siguiente: [Condición de carrera](../../a4_compartir/e01_contador_inseguro/README.md)
