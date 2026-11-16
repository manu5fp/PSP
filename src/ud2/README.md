# UD2. Programación multihilo

Hilos, condiciones de carrera, monitores, `wait`/`notifyAll` y semáforos.

Resultado de aprendizaje: **RA2**. Lee cada apartado en los apuntes, abre el ejemplo, ejecútalo y sigue con el siguiente. [← Inicio](../../README.md)

| Orden | Apartado de los apuntes | Ejemplo | Objetivo | Nivel | Tipo | Criterios |
|---|---|---|---|---|---|---|
| 1 | 2. Creación, inicio y finalización de hilos | [Heredar de Thread](a2_creacion/e01_heredar_thread/README.md) | Crear un primer hilo, con una clase propia y una anónima. | 1 | Demostración | RA2.b |
| 2 | 2. Creación, inicio y finalización de hilos | [Runnable y lambdas](a2_creacion/e02_runnable_lambda/README.md) | Separar la tarea del hilo. | 1 | Demostración | RA2.b |
| 3 | 2. Creación, inicio y finalización de hilos | [start() frente a run()](a2_creacion/e03_start_run/README.md) | Comprobar que `run()` no crea concurrencia. | 1 | Guiado | RA2.b |
| 4 | 2. Creación, inicio y finalización de hilos | [Varios hilos y join()](a2_creacion/e04_join/README.md) | Lanzar varios hilos y esperar a todos. | 2 | Guiado | RA2.b, RA2.c, RA2.g |
| 5 | 2. Creación, inicio y finalización de hilos | [Sumar una matriz con hilos](a2_creacion/e05_suma_matriz/README.md) | Comparar el tiempo secuencial y con hilos sobre el mismo cálculo. | 2 | Demostración | RA2.a, RA2.c |
| 6 | 2. Creación, inicio y finalización de hilos | [Cancelar con interrupt()](a2_creacion/e06_interrupcion/README.md) | Cancelar una tarea en curso de forma cooperativa. | 1 | Guiado | RA2.b |
| 7 | 3. Estados y prioridades de los hilos | [Prioridades e interrupción](a3_estados/e01_prioridades/README.md) | Ver que una prioridad más alta no garantiza terminar antes. | 2 | Demostración | RA2.d, RA2.g |
| 8 | 4. Compartir información. Condiciones de carrera | [Condición de carrera](a4_compartir/e01_contador_inseguro/README.md) | Ver cómo se pierden incrementos. | 1 | Demostración | RA2.e, RA2.k |
| 9 | 4. Compartir información. Condiciones de carrera | [AtomicInteger](a4_compartir/e02_atomic/README.md) | Comparar un `int` con un `AtomicInteger`. | 1 | Demostración | RA2.j, RA2.k |
| 10 | 5. Exclusión mutua con synchronized | [Contador con synchronized](a5_synchronized/e01_contador_seguro/README.md) | Corregir la condición de carrera del contador. | 1 | Demostración | RA2.f |
| 11 | 6. Comunicación entre hilos | [Productor-consumidor simple](a6_comunicacion/e01_productor_consumidor/README.md) | Coordinar dos hilos con un dato compartido. | 2 | Guiado | RA2.e, RA2.f |
| 12 | 6. Comunicación entre hilos | [Lectores y escritor](a6_comunicacion/e02_libros/README.md) | Hacer que unos hilos esperen a que otro complete un trabajo. | 2 | Guiado | RA2.f |
| 13 | 6. Comunicación entre hilos | [Cafetera: notify y notifyAll](a6_comunicacion/e03_cafetera_notify/README.md) | Comparar despertar de uno en uno con despertar a todos. | 2 | Demostración | RA2.f |
| 14 | 6. Comunicación entre hilos | [Rondas con notifyAll](a6_comunicacion/e04_cola_jugadores/README.md) | Sincronizar a varios hilos en varias rondas sin perder avisos. | 3 | Ampliación | RA2.f |
| 15 | 6. Comunicación entre hilos | [Cola limitada sin espera](a6_comunicacion/e05_cola_sin_espera/README.md) | Ver qué se pierde si una cola rechaza en lugar de bloquear. | 2 | Demostración | RA2.k |
| 16 | 6. Comunicación entre hilos | [BlockingQueue](a6_comunicacion/e06_blocking_queue/README.md) | Resolver el mismo problema con la biblioteca de Java. | 1 | Demostración | RA2.j |
| 17 | 7. Semáforos | [Semáforo: recurso limitado](a7_semaforos/e01_recurso_limitado/README.md) | Limitar a dos los hilos que usan un recurso. | 1 | Demostración | RA2.f, RA2.j |
| 18 | 7. Semáforos | [Cafetera con semáforo](a7_semaforos/e02_cafetera_semaforo/README.md) | Resolver con un semáforo la cafetera de a6. | 2 | Guiado | RA2.f |
| 19 | 7. Semáforos | [Semáforo equitativo](a7_semaforos/e03_semaforo_fifo/README.md) | Ver el efecto de `new Semaphore(0, true)`. | 2 | Demostración | RA2.f |
| 20 | 7. Semáforos | [Semáforo sin orden](a7_semaforos/e04_semaforo_no_fifo/README.md) | Comparar llegada y consumo sin orden garantizado. | 2 | Demostración | RA2.f |
| 21 | 7. Semáforos | [Almacén con tres semáforos](a7_semaforos/e05_almacen/README.md) | Combinar cupo, señalización y exclusión mutua. | 3 | Integración | RA2.f |
| 22 | 7. Semáforos | [Secuencia 1: sin hilos](a7_semaforos/e06_sin_hilos/README.md) | Calcular suma y producto de forma secuencial. | 1 | Guiado | RA2.a |
| 23 | 7. Semáforos | [Secuencia 2: hilos sin sincronizar](a7_semaforos/e07_hilos_sin_sincronizar/README.md) | Ver qué falla si solo se añaden hilos. | 2 | Guiado | RA2.k |
| 24 | 7. Semáforos | [Secuencia 3: cinco semáforos](a7_semaforos/e08_cinco_semaforos/README.md) | Solucionar el problema de la forma más directa. | 2 | Guiado | RA2.f |
| 25 | 7. Semáforos | [Secuencia 4: cuatro semáforos](a7_semaforos/e09_cuatro_semaforos/README.md) | Agrupar dos señales que espera el mismo hilo. | 3 | Guiado | RA2.f |
| 26 | 7. Semáforos | [Secuencia 5: tres semáforos](a7_semaforos/e10_tres_semaforos/README.md) | Razonar por qué sigue siendo correcto con menos semáforos. | 3 | Integración | RA2.f |
| 27 | 9. Librerías de concurrencia | [Números aleatorios](a9_librerias/e01_aleatorios/README.md) | Generar valores para simulaciones con la clase auxiliar `Azar`. | 1 | Demostración | RA2.j |

La clase auxiliar [`comun`](comun) (`Azar`, `Crono`) la usan varios ejemplos. No hay que hacer nada: `-sourcepath src` la encuentra.

**Itinerario de semáforos**: los órdenes 22 a 26 resuelven el mismo problema, primero sin hilos, después sin sincronizar y luego con cinco, cuatro y tres semáforos.

