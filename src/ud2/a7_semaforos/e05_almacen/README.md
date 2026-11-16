# Almacén con tres semáforos

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** Búfer acotado
- **Conocimientos previos:** Ejemplos anteriores
- **Nivel:** 3. Integración · **Tipo:** Integración · **Criterios:** RA2.f

## Objetivo

Combinar cupo, señalización y exclusión mutua.

## Clases

- [`Almacen.java`](Almacen.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e05_almacen/*.java
java -cp bin ud2.a7_semaforos.e05_almacen.Almacen
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
productor 19 produce. Productos: 1
productor 25 produce. Productos: 2
productor 27 produce. Productos: 3
productor 20 produce. Productos: 4
productor 17 produce. Productos: 5
productor 29 produce. Productos: 6
productor 7 produce. Productos: 7
productor 30 produce. Productos: 8
productor 9 produce. Productos: 9
productor 3 produce. Productos: 10
productor 31 produce. Productos: 11
productor 4 produce. Productos: 12
...
```

## Qué debes observar

- `productor` limita, `consumidor` señala y `mutex` protege.
- Primero la condición y después el `mutex`.

## Continuación

Siguiente: [Secuencia 1: sin hilos](../e06_sin_hilos/README.md)
