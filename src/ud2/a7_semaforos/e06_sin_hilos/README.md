# Secuencia 1: sin hilos

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** Problema de partida
- **Conocimientos previos:** Java básico
- **Nivel:** 1. Inicial · **Tipo:** Guiado · **Criterios:** RA2.a

## Objetivo

Calcular suma y producto de forma secuencial.

## Clases

- [`SinHilos.java`](SinHilos.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e06_sin_hilos/*.java
java -cp bin ud2.a7_semaforos.e06_sin_hilos.SinHilos
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
A: leyendo x...
A: x = 154
B: leyendo y...
B: y = 153
C: leyendo z...
C: z = 889
A: Calculando suma...
A: suma = 1042
B: Calculando producto...
B: producto = 23562
C: la suma es 1042
C: el producto es 23562
...
```

## Qué debes observar

- Es el punto de partida de los cuatro ejemplos siguientes.

## Continuación

Siguiente: [Secuencia 2: hilos sin sincronizar](../e07_hilos_sin_sincronizar/README.md)
