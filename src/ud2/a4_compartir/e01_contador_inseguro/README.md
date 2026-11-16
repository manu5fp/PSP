# Condición de carrera

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 4. Compartir información. Condiciones de carrera
- **Concepto:** Variables compartidas sin protección
- **Conocimientos previos:** `join()`
- **Nivel:** 1. Inicial · **Tipo:** Demostración · **Criterios:** RA2.e, RA2.k

## Objetivo

Ver cómo se pierden incrementos.

## Clases

- [`ContadorApp.java`](ContadorApp.java) (contiene el `main` que debes ejecutar)
- [`ContadorInseguro.java`](ContadorInseguro.java)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a4_compartir/e01_contador_inseguro/*.java
java -cp bin ud2.a4_compartir.e01_contador_inseguro.ContadorApp
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Cuenta final: 780273
```

## Qué debes observar

- El resultado es menor que 1000000 y cambia en cada ejecución.
- Este error es intencionado; la corrección es el ejemplo de a5.

## Continuación

Siguiente: [AtomicInteger](../e02_atomic/README.md)
