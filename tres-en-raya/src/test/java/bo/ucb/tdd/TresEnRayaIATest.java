package bo.ucb.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas GENERADAS POR IA a partir del prompt en PROMPT_IA.md.
 * Se dejan tal como las propuso la IA (solo se ajusto el paquete)
 * para poder compararlas con TresEnRayaTest (pruebas propias).
 */
@DisplayName("Tres en Raya - pruebas generadas por IA")
class TresEnRayaIATest {

    private TresEnRaya juego;

    @BeforeEach
    void setUp() {
        juego = new TresEnRaya();
    }

    @Nested
    @DisplayName("Requerimiento 1: colocacion de piezas")
    class Colocacion {

        @ParameterizedTest(name = "x={0} fuera del tablero")
        @CsvSource({"0", "4", "-1", "100"})
        void xFueraDeRango_lanzaExcepcion(int x) {
            RuntimeException ex = assertThrows(RuntimeException.class, () -> juego.jugar(x, 2));
            assertTrue(ex.getMessage().contains("X"));
        }

        @ParameterizedTest(name = "y={0} fuera del tablero")
        @CsvSource({"0", "4", "-1", "100"})
        void yFueraDeRango_lanzaExcepcion(int y) {
            RuntimeException ex = assertThrows(RuntimeException.class, () -> juego.jugar(2, y));
            assertTrue(ex.getMessage().contains("Y"));
        }

        @ParameterizedTest(name = "casilla valida ({0},{1})")
        @CsvSource({"1,1", "1,3", "3,1", "3,3", "2,2"})
        void casillaValida_noLanzaExcepcion(int x, int y) {
            assertDoesNotThrow(() -> juego.jugar(x, y));
        }

        @Test
        void casillaOcupada_lanzaExcepcion() {
            juego.jugar(2, 1);
            assertThrows(RuntimeException.class, () -> juego.jugar(2, 1));
        }

        @Test
        void movimientoInvalido_noConsumeElTurno() {
            juego.jugar(1, 1); // X
            assertThrows(RuntimeException.class, () -> juego.jugar(1, 1));
            assertEquals('+', juego.siguienteJugador());
        }
    }

    @Nested
    @DisplayName("Requerimiento 2: turnos")
    class Turnos {

        @Test
        void primerTurnoEsX() {
            assertEquals('X', juego.siguienteJugador());
        }

        @Test
        void despuesDeXJuegaMas() {
            juego.jugar(1, 1);
            assertEquals('+', juego.siguienteJugador());
        }

        @Test
        void despuesDeMasJuegaX() {
            juego.jugar(1, 1);
            juego.jugar(1, 2);
            assertEquals('X', juego.siguienteJugador());
        }

        @Test
        void turnosAlternanDuranteVariasJugadas() {
            int[][] jugadas = {{1, 1}, {1, 2}, {1, 3}, {2, 1}};
            char esperado = 'X';
            for (int[] j : jugadas) {
                assertEquals(esperado, juego.siguienteJugador());
                juego.jugar(j[0], j[1]);
                esperado = esperado == 'X' ? '+' : 'X';
            }
        }
    }

    @Nested
    @DisplayName("Requerimiento 3: condicion de victoria")
    class Victoria {

        @Test
        void sinLineaCompleta_noHayGanador() {
            assertEquals("Sin ganador", juego.jugar(1, 1));
        }

        @ParameterizedTest(name = "X gana en fila y={0}")
        @CsvSource({"1", "2", "3"})
        void xGanaEnCualquierFilaHorizontal(int y) {
            int otraY = y == 1 ? 2 : 1;
            juego.jugar(1, y);
            juego.jugar(1, otraY);
            juego.jugar(2, y);
            juego.jugar(2, otraY);
            assertEquals("X es el ganador", juego.jugar(3, y));
        }

        @ParameterizedTest(name = "X gana en columna x={0}")
        @CsvSource({"1", "2", "3"})
        void xGanaEnCualquierColumnaVertical(int x) {
            int otraX = x == 1 ? 2 : 1;
            juego.jugar(x, 1);
            juego.jugar(otraX, 1);
            juego.jugar(x, 2);
            juego.jugar(otraX, 2);
            assertEquals("X es el ganador", juego.jugar(x, 3));
        }

        @Test
        void xGanaEnDiagonalPrincipal() {
            juego.jugar(1, 1);
            juego.jugar(1, 2);
            juego.jugar(2, 2);
            juego.jugar(1, 3);
            assertEquals("X es el ganador", juego.jugar(3, 3));
        }

        @Test
        void xGanaEnDiagonalInversa() {
            juego.jugar(1, 3);
            juego.jugar(1, 1);
            juego.jugar(2, 2);
            juego.jugar(1, 2);
            assertEquals("X es el ganador", juego.jugar(3, 1));
        }

        @Test
        void masTambienPuedeGanar() {
            juego.jugar(1, 1); // X
            juego.jugar(2, 1); // +
            juego.jugar(3, 3); // X
            juego.jugar(2, 2); // +
            juego.jugar(1, 2); // X
            assertEquals("+ es el ganador", juego.jugar(2, 3));
        }

        @Test
        @Disabled("La IA asumio un requerimiento de EMPATE que NO existe en la kata")
        void tableroLlenoSinLinea_esEmpate() {
            juego.jugar(1, 1); juego.jugar(1, 2); juego.jugar(1, 3);
            juego.jugar(2, 2); juego.jugar(2, 1); juego.jugar(2, 3);
            juego.jugar(3, 2); juego.jugar(3, 1);
            assertEquals("Empate", juego.jugar(3, 3));
        }
    }
}
