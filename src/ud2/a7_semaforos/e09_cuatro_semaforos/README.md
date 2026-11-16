# Secuencia 4: cuatro semáforos

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** Optimizar semáforos
- **Conocimientos previos:** Ejemplo anterior
- **Nivel:** 3. Integración · **Tipo:** Guiado · **Criterios:** RA2.f

## Objetivo

Agrupar dos señales que espera el mismo hilo.

## Clases

- [`Sincro4Semaforos.java`](Sincro4Semaforos.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e09_cuatro_semaforos/*.java
java -cp bin ud2.a7_semaforos.e09_cuatro_semaforos.Sincro4Semaforos
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
A: Leyendo x...
B: Leyendo y...
C: Leyendo z...
B: y = 500
B: Calculando producto...
A: x = 915
A: Calculando suma...
C: z = 1636
C: La suma es: 2136
C: El producto es: 457500
Tiempo empleado: 67.049747 milisegundos.
```

## Qué debes observar

- `r4.acquire(2)` funciona porque solo C espera en `r4`.

## Continuación

Siguiente: [Secuencia 5: tres semáforos](../e10_tres_semaforos/README.md)
