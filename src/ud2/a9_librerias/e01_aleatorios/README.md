# Números aleatorios

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 9. Librerías de concurrencia
- **Concepto:** `ThreadLocalRandom`
- **Conocimientos previos:** Métodos estáticos
- **Nivel:** 1. Inicial · **Tipo:** Demostración · **Criterios:** RA2.j

## Objetivo

Generar valores para simulaciones con la clase auxiliar `Azar`.

## Clases

- [`PruebaAzar.java`](PruebaAzar.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a9_librerias/e01_aleatorios/*.java
java -cp bin ud2.a9_librerias.e01_aleatorios.PruebaAzar
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Entero cualquiera: -714018612
Entero entre 0 y 10: 10
Entero entre 5 y 15: 13
Real entre 0.0 y 1.0: 0.5679525777728258
Real entre 0.0 y 10.0: 5.20800287767412
Real entre 2.5 y 5.5: 4.469951467013228

Diez enteros entre 1 y 6 (simulando un dado):
2 1 1 2 5 3 2 1 2 4 

Cinco reales entre 0.0 y 100.0:
62.06984436643113
...
```

## Qué debes observar

- `ud2.comun.Azar` incluye el límite superior; `nextInt` no.

## Continuación

Es el último ejemplo de la unidad.
