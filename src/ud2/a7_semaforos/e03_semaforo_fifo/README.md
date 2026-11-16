# Semáforo equitativo

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** Orden de llegada
- **Conocimientos previos:** Ejemplo anterior
- **Nivel:** 2. Desarrollo · **Tipo:** Demostración · **Criterios:** RA2.f

## Objetivo

Ver el efecto de `new Semaphore(0, true)`.

## Clases

- [`ProdConsumSemaforoFifo.java`](ProdConsumSemaforoFifo.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e03_semaforo_fifo/*.java
java -cp bin ud2.a7_semaforos.e03_semaforo_fifo.ProdConsumSemaforoFifo
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Cliente mario esperando el café...
Cliente juan esperando el café...
Cliente bea esperando el café...
Cliente jose esperando el café...
Cliente antonia esperando el café...
Cliente miguel esperando el café...
Cliente alfonso esperando el café...
Cliente ricardo esperando el café...
Cliente ana esperando el café...
El camarero veterano está preparando cafés para todos...
Cafés listos, despertando a TODOS los clientes.
juan con orden FIFO = 2
...
```

## Qué debes observar

- Tiende a atender por orden de llegada; `miNumero` se obtiene antes de `acquire()`, de modo que el número no prueba por sí solo el orden exacto de la cola.

## Continuación

Siguiente: [Semáforo sin orden](../e04_semaforo_no_fifo/README.md)
