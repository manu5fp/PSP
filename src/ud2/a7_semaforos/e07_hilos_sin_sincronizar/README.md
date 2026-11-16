# Secuencia 2: hilos sin sincronizar

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** Resultados incorrectos
- **Conocimientos previos:** Ejemplo anterior
- **Nivel:** 2. Desarrollo · **Tipo:** Guiado · **Criterios:** RA2.k

## Objetivo

Ver qué falla si solo se añaden hilos.

## Clases

- [`SincroHilos.java`](SincroHilos.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e07_hilos_sin_sincronizar/*.java
java -cp bin ud2.a7_semaforos.e07_hilos_sin_sincronizar.SincroHilos
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
A: Leyendo x...
B: Leyendo y...
C: Leyendo z...
C: z vale 1344
C: La suma es: 0
A: x vale 752
A: Calculando suma...
B: y vale 361
B: Calculando producto...
C: El producto es: 0
Tiempo empleado: 3.833811 milisegundos.
```

## Qué debes observar

- **Error intencionado**: puede usar valores aún sin leer.
- Además, `main` no espera a los hilos, por lo que el tiempo no es válido.

## Continuación

Siguiente: [Secuencia 3: cinco semáforos](../e08_cinco_semaforos/README.md)
