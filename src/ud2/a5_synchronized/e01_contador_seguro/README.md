# Contador con synchronized

[← Índice de la unidad](../../README.md)

## Ubicación didáctica

- **Unidad:** UD2. Programación multihilo
- **Apartado de los apuntes:** 5. Exclusión mutua con synchronized
- **Concepto:** Exclusión mutua
- **Conocimientos previos:** `a4/e01`
- **Nivel:** 1. Inicial · **Tipo:** Demostración · **Criterios:** RA2.f

## Objetivo

Corregir la condición de carrera del contador.

## Clases

- [`ContadorSeguro.java`](ContadorSeguro.java)
- [`ContadorSeguroApp.java`](ContadorSeguroApp.java) (contiene el `main` que debes ejecutar)

## Ejecución

Desde la **raíz del repositorio**:

```bash
javac -encoding UTF-8 -sourcepath src -d bin src/ud2/a5_synchronized/e01_contador_seguro/*.java
java -cp bin ud2.a5_synchronized.e01_contador_seguro.ContadorSeguroApp
```

## Resultado

Salida de una ejecución real (OpenJDK 21, Linux). Con hilos, procesos o red, el orden y los valores pueden cambiar.

```text
Cuenta final: 1000000 (esperada: 1000000)
```

## Qué debes observar

- Todos los accesos usan el mismo monitor, también la lectura.

## Continuación

Siguiente: [Productor-consumidor simple](../../a6_comunicacion/e01_productor_consumidor/README.md)
