# Taller #6 – TDD: Kata Tres en Raya

Alan Ariel Maldonado Carvajal – UCB, SIS-213

Kata del Tres en Raya resuelta con **TDD (Red → Green → Refactor)** en Java 17 + JUnit 5 + Maven.

## Estructura

```
src/main/java/bo/ucb/tdd/TresEnRaya.java        -> código del programa
src/test/java/bo/ucb/tdd/TresEnRayaTest.java    -> pruebas propias (TDD)
src/test/java/bo/ucb/tdd/TresEnRayaIATest.java  -> pruebas generadas por IA
PROMPT_IA.md                                    -> prompt usado con la IA
.github/workflows/maven.yml                     -> CI: corre las pruebas en cada push
```

## Requerimientos (de la presentación de CI/CD)

1. Una pieza puede colocarse en un espacio de un tablero 3x3 (excepciones fuera de X, fuera de Y, casilla ocupada).
2. Determinar qué jugador juega después (`X` empieza, luego alternan `X` y `+`).
3. Un jugador gana al completar una línea horizontal, vertical o diagonal.

## Ejecutar pruebas

```
mvn test
```
