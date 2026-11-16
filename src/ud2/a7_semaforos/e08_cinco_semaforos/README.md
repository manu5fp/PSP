# Secuencia 3: cinco semáforos

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 7. Semáforos
- **Concepto:** Un semáforo por condición
- **Conocimientos previos:** Ejemplo anterior
- **Nivel:** 2. Desarrollo · **Tipo:** Guiado · **Criterios:** RA2.f

## Objetivo

Solucionar el problema de la forma más directa.

## Clases

- [`Sincro5Semaforos.java`](Sincro5Semaforos.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a7_semaforos/e08_cinco_semaforos/*.java
java -cp bin ud2.a7_semaforos.e08_cinco_semaforos.Sincro5Semaforos
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
A: leyendo x...
B: leyendo y...
C: leyendo z...
C: z = 414
B: y = 317
B: Calculando producto...
A: x = 665
A: Calculando suma...
C: la suma es 731
C: el producto es 210805
Tiempo empleado: 66.936842 milisegundos.
```

## Qué debes observar

- Cada semáforo responde a una condición.

## Continuación

Siguiente: [Secuencia 4: cuatro semáforos](../e09_cuatro_semaforos/README.md)
