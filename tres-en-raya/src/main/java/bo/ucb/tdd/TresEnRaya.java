package bo.ucb.tdd;

/**
 * Kata Tres en Raya resuelta con TDD (Red - Green - Refactor).
 * Jugadores: 'X' y '+'. Tablero 3x3, coordenadas de 1 a 3.
 */
public class TresEnRaya {

    public static final char VACIO = '\0';
    public static final String SIN_GANADOR = "Sin ganador";
    private static final int TAM = 3;

    private final char[][] tablero = new char[TAM][TAM];
    private char ultimoJugador = '+';

    /**
     * Coloca la pieza del jugador actual en (x, y).
     * @return "Sin ganador" o "X es el ganador" / "+ es el ganador"
     */
    public String jugar(int x, int y) {
        validarEje(x, "X");
        validarEje(y, "Y");
        validarCasillaLibre(x, y);
        ultimoJugador = siguienteJugador();
        tablero[x - 1][y - 1] = ultimoJugador;
        return esGanador() ? ultimoJugador + " es el ganador" : SIN_GANADOR;
    }

    public char siguienteJugador() {
        return ultimoJugador == 'X' ? '+' : 'X';
    }

    // ---- Requerimiento 1 ----
    private void validarEje(int valor, String eje) {
        if (valor < 1 || valor > TAM) {
            throw new RuntimeException(eje + " esta fuera del tablero");
        }
    }

    private void validarCasillaLibre(int x, int y) {
        if (tablero[x - 1][y - 1] != VACIO) {
            throw new RuntimeException("La casilla esta ocupada");
        }
    }

    // ---- Requerimiento 3 ----
    private boolean esGanador() {
        char p = ultimoJugador;
        for (int i = 0; i < TAM; i++) {
            if (linea(p, tablero[i][0], tablero[i][1], tablero[i][2])) return true; // horizontal
            if (linea(p, tablero[0][i], tablero[1][i], tablero[2][i])) return true; // vertical
        }
        return linea(p, tablero[0][0], tablero[1][1], tablero[2][2])   // diagonal
            || linea(p, tablero[0][2], tablero[1][1], tablero[2][0]);  // diagonal inversa
    }

    private boolean linea(char p, char a, char b, char c) {
        return a == p && b == p && c == p;
    }
}
