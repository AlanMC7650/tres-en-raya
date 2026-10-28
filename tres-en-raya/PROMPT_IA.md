# Prompt usado para generar las pruebas unitarias con IA

```
Actúa como un desarrollador Java experto en TDD y JUnit 5.

Necesito las pruebas unitarias para la kata "Tres en Raya" (Tic-Tac-Toe).
La clase a probar es:

    package bo.ucb.tdd;
    public class TresEnRaya {
        public String jugar(int x, int y);   // coloca la pieza del jugador actual
        public char siguienteJugador();      // devuelve 'X' o '+'
    }

Reglas:
- Tablero 3x3, coordenadas x e y van de 1 a 3.
- Jugadores: 'X' y '+'. Siempre empieza 'X' y luego se alternan.
- jugar() devuelve "Sin ganador", "X es el ganador" o "+ es el ganador".
- Se lanza RuntimeException si x está fuera del tablero, si y está fuera
  del tablero, o si la casilla ya está ocupada.

Requerimientos a cubrir:
1. Una pieza puede colocarse en un espacio del tablero 3x3
   (fuera del eje X -> excepción, fuera del eje Y -> excepción,
    casilla ocupada -> excepción).
2. Determinar qué jugador juega después (primero 'X', después de 'X' va '+',
   después de '+' va 'X').
3. Un jugador gana si completa una línea horizontal, vertical o diagonal;
   si no, no hay ganador.

Genera una clase de pruebas JUnit 5 llamada TresEnRayaIATest con:
- Casos límite (0, 4, negativos) y casos normales.
- Todas las filas, columnas y ambas diagonales.
- Nombres de pruebas descriptivos en español.
- Usa @BeforeEach, @Nested, @DisplayName y @ParameterizedTest donde convenga.
Devuelve solo el código Java.
```

El resultado está en `src/test/java/bo/ucb/tdd/TresEnRayaIATest.java`.
