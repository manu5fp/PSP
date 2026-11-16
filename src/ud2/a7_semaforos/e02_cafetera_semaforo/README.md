# Cafetera con semáforo

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** Señalización con semáforos
- **Conocimientos previos:** `a6/e03`
- **Nivel:** 2. Desarrollo · **Tipo:** Guiado · **Criterios:** RA2.f

## Objetivo

Resolver con un semáforo la cafetera de a6.

## Clases

- [`ProdConsumSema.java`](ProdConsumSema.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e02_cafetera_semaforo/*.java
java -cp bin ud2.a7_semaforos.e02_cafetera_semaforo.ProdConsumSema
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Cliente mario esperando el café...
Cliente alfonso esperando el café...
Cliente bea esperando el café...
Cliente ana esperando el café...
Cliente miguel esperando el café...
Cliente jose esperando el café...
Cliente antonia esperando el café...
Cliente juan esperando el café...
Cliente ricardo esperando el café...
 El camarero aprendiz está preparando un café...
Café listo, despertando a 1 cliente.
 El camarero aprendiz está preparando un café...
...
```

## Qué debes observar

- Un permiso no se pierde aunque el aviso llegue antes.

## Continuación

Siguiente: [Semáforo equitativo](../e03_semaforo_fifo/README.md)
