# Rondas con notifyAll

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 6. Comunicación entre hilos
- **Concepto:** Coordinación por rondas
- **Conocimientos previos:** Ejemplos anteriores
- **Nivel:** 3. Integración · **Tipo:** Ampliación · **Criterios:** RA2.f

## Objetivo

Sincronizar a varios hilos en varias rondas sin perder avisos.

## Clases

- [`ColaJugadores.java`](ColaJugadores.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a6_comunicacion/e04_cola_jugadores/*.java
java -cp bin ud2.a6_comunicacion.e04_cola_jugadores.ColaJugadores
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
--- Ronda 1: se despierta a todos ---
Jugador g liberado en la ronda 1
Jugador c liberado en la ronda 1
Jugador b liberado en la ronda 1
Jugador a liberado en la ronda 1
Jugador d liberado en la ronda 1
Jugador f liberado en la ronda 1
Jugador e liberado en la ronda 1
Jugador h liberado en la ronda 1
--- Ronda 2: se despierta a todos ---
Jugador g liberado en la ronda 2
Jugador c liberado en la ronda 2
...
```

## Qué debes observar

- Cada jugador aparece una vez por ronda.
- Comprueba que hay dos motivos de espera en un mismo monitor.

## Continuación

Siguiente: [Cola limitada sin espera](../e05_cola_sin_espera/README.md)
