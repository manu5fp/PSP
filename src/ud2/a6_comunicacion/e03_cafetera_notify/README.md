# Cafetera: notify y notifyAll

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 6. Comunicación entre hilos
- **Concepto:** `notify()` frente a `notifyAll()`
- **Conocimientos previos:** Ejemplos anteriores
- **Nivel:** 2. Desarrollo · **Tipo:** Demostración · **Criterios:** RA2.f

## Objetivo

Comparar despertar de uno en uno con despertar a todos.

## Clases

- [`ProdConsum.java`](ProdConsum.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a6_comunicacion/e03_cafetera_notify/*.java
java -cp bin ud2.a6_comunicacion.e03_cafetera_notify.ProdConsum
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Cliente mario esperando el café...
Cliente ana esperando el café...
Cliente ricardo esperando el café...
Cliente alfonso esperando el café...
Cliente miguel esperando el café...
Cliente antonia esperando el café...
Cliente juan esperando el café...
Cliente jose esperando el café...
Cliente bea esperando el café...
 El camarero aprendiz está preparando el café...
Café listo, notificando al consumidor...
 El camarero aprendiz está preparando el café...
...
```

## Qué debes observar

- El camarero se elige al azar: ejecútalo varias veces.
- El contador `cafes` evita perder un aviso.

## Continuación

Siguiente: [Rondas con notifyAll](../e04_cola_jugadores/README.md)
