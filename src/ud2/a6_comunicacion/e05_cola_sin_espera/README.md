# Cola limitada sin espera

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 6. Comunicación entre hilos
- **Concepto:** Por qué hace falta esperar
- **Conocimientos previos:** Ejemplos anteriores
- **Nivel:** 2. Desarrollo · **Tipo:** Demostración · **Criterios:** RA2.k

## Objetivo

Ver qué se pierde si una cola rechaza en lugar de bloquear.

## Clases

- [`ColaLimitada.java`](ColaLimitada.java)
- [`ConsumidorLimite.java`](ConsumidorLimite.java)
- [`EjemploPCLimite.java`](EjemploPCLimite.java) (contiene el `main` que debes ejecutar)
- [`ProductorLimite.java`](ProductorLimite.java)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a6_comunicacion/e05_cola_sin_espera/*.java
java -cp bin ud2.a6_comunicacion.e05_cola_sin_espera.EjemploPCLimite
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Consumido: 0
Cola vacía: nada que consumir
Cola vacía: nada que consumir
Cola vacía: nada que consumir
Cola vacía: nada que consumir
Producido: 0
Producido: 1
Producido: 2
Producido: 3
Producido: 4
```

## Qué debes observar

- Puede haber datos perdidos y lecturas sin datos.
- Es un ejemplo de lo que hay que evitar: compáralo con el siguiente.

## Continuación

Siguiente: [BlockingQueue](../e06_blocking_queue/README.md)
