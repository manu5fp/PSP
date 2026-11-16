# Semáforo sin orden

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** Semáforo no equitativo
- **Conocimientos previos:** Ejemplo anterior
- **Nivel:** 2. Desarrollo · **Tipo:** Demostración · **Criterios:** RA2.f

## Objetivo

Comparar llegada y consumo sin orden garantizado.

## Clases

- [`ProdConsumSemaNoFifo.java`](ProdConsumSemaNoFifo.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e04_semaforo_no_fifo/*.java
java -cp bin ud2.a7_semaforos.e04_semaforo_no_fifo.ProdConsumSemaNoFifo
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
mario → llegada: 1 | consumo: 1
juan → llegada: 2 | consumo: 2
bea → llegada: 3 | consumo: 3
jose → llegada: 4 | consumo: 4
antonia → llegada: 5 | consumo: 5
miguel → llegada: 6 | consumo: 6
alfonso → llegada: 7 | consumo: 7
ricardo → llegada: 8 | consumo: 8
ana → llegada: 9 | consumo: 9
```

## Qué debes observar

- Compara las columnas de llegada y consumo con varias ejecuciones.

## Continuación

Siguiente: [Almacén con tres semáforos](../e05_almacen/README.md)
