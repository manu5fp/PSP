# Sumar una matriz con hilos

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 2. Creación, inicio y finalización de hilos
- **Concepto:** Reparto del trabajo y medición
- **Conocimientos previos:** `join()`, arrays
- **Nivel:** 2. Desarrollo · **Tipo:** Demostración · **Criterios:** RA2.a, RA2.c

## Objetivo

Comparar el tiempo secuencial y con hilos sobre el mismo cálculo.

## Clases

- [`HiloSumador.java`](HiloSumador.java)
- [`MiPrueba.java`](MiPrueba.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a2_creacion/e05_suma_matriz/*.java
java -cp bin ud2.a2_creacion.e05_suma_matriz.MiPrueba
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
7.604392229 segundos.
2.186276655 segundos.
true
```

## Qué debes observar

- El `true` final indica que ambas formas coinciden.
- Cada hilo escribe solo en su posición: no hace falta sincronizar.
- Reserva unos 128 MB y la mejora depende de los núcleos.

## Continuación

Siguiente: [Cancelar con interrupt()](../e06_interrupcion/README.md)
