# Secuencia 5: tres semáforos

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** Reutilizar semáforos
- **Conocimientos previos:** Ejemplo anterior
- **Nivel:** 3. Integración · **Tipo:** Integración · **Criterios:** RA2.f

## Objetivo

Razonar por qué sigue siendo correcto con menos semáforos.

## Clases

- [`Sincro3Semaforos.java`](Sincro3Semaforos.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e10_tres_semaforos/*.java
java -cp bin ud2.a7_semaforos.e10_tres_semaforos.Sincro3Semaforos
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
A: Leyendo x...
B: Leyendo y...
C: Leyendo z...
A: x = 845
B: y = 250
B: Calculando producto...
A: Calculando suma...
C: z = 1746
C: La suma es: 1996
C: El producto es: 211250
Tiempo empleado: 82.454302 milisegundos.
```

## Qué debes observar

- `acquire(2)` no puede robar el permiso único destinado a otro hilo.
- Compáralo con el razonamiento de «7. Semáforos» de los apuntes.

## Continuación

Siguiente: [Números aleatorios](../../a9_librerias/e01_aleatorios/README.md)
