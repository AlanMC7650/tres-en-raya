package bo.ucb.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas escritas a mano siguiendo TDD, una por cada prueba
 * descrita en la presentacion (Requerimientos 1, 2 y 3).
 */
class TresEnRayaTest {

    private TresEnRaya juego;

    @BeforeEach
    void setUp() {
        juego = new TresEnRaya();
    }

    // ===== Requerimiento 1: colocar pieza en tablero 3x3 =====

    @Test
    void cuandoPiezaFueraDelEjeX_lanzaExcepcion() {
        assertThrows(RuntimeException.class, () -> juego.jugar(5, 2));
    }

    @Test
    void cuandoPiezaFueraDelEjeY_lanzaExcepcion() {
        assertThrows(RuntimeException.class, () -> juego.jugar(2, 5));
    }

    @Test
    void cuandoPiezaEnLugarOcupado_lanzaExcepcion() {
        juego.jugar(2, 1);
        assertThrows(RuntimeException.class, () -> juego.jugar(2, 1));
    }

    // ===== Requerimiento 2: turno del siguiente jugador =====

    @Test
    void primerTurno_esDeX() {
        assertEquals('X', juego.siguienteJugador());
    }

    @Test
    void siUltimoTurnoFueX_siguienteEsMas() {
        juego.jugar(1, 1);
        assertEquals('+', juego.siguienteJugador());
    }

    @Test
    void siUltimoTurnoFueMas_siguienteEsX() {
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // +
        assertEquals('X', juego.siguienteJugador());
    }

    // ===== Requerimiento 3: condicion de victoria =====

    @Test
    void sinCondicionDeVictoria_noHayGanador() {
        assertEquals("Sin ganador", juego.jugar(1, 1));
    }

    @Test
    void lineaHorizontal_gana() {
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // +
        juego.jugar(2, 1); // X
        juego.jugar(2, 2); // +
        assertEquals("X es el ganador", juego.jugar(3, 1)); // X
    }

    @Test
    void lineaVertical_gana() {
        juego.jugar(2, 1); // X
        juego.jugar(1, 1); // +
        juego.jugar(3, 1); // X
        juego.jugar(1, 2); // +
        juego.jugar(2, 2); // X
        assertEquals("+ es el ganador", juego.jugar(1, 3)); // +
    }

    @Test
    void lineaDiagonal_gana() {
        juego.jugar(1, 1); // X
        juego.jugar(1, 2); // +
        juego.jugar(2, 2); // X
        juego.jugar(1, 3); // +
        assertEquals("X es el ganador", juego.jugar(3, 3)); // X
    }
}
