package org.iesalandalus.programacion.cuatroenraya.modelo;

import org.iesalandalus.programacion.cuatroenraya.vista.Consola;

import java.sql.Array;
import java.util.Objects;

public class CuatroEnRaya {
    private static final int NUMERO_JUGADORES = 2;
    private Tablero tablero;
    private Jugador[] jugador;

    public CuatroEnRaya(Jugador jugador1, Jugador jugador2){
        Objects.requireNonNull(jugador1,"El jugador 1 no puede ser nulo");
        Objects.requireNonNull(jugador2,"El jugador 2 no puede ser nulo");
        jugador = new Jugador[NUMERO_JUGADORES];
        jugador[0] = jugador1;
        jugador[1] = jugador2;
        tablero = new Tablero();
    }

    public void jugar(){
        int turno = 0;
        boolean hayGanador = false;
        Jugador jugadorQueJuega = jugador[turno];
        while (!tablero.estaLleno() && !hayGanador) {
            jugadorQueJuega = jugador[turno++ % NUMERO_JUGADORES];
            hayGanador = tirar(jugadorQueJuega);
        }
        if (hayGanador) {
            System.out.printf("ENHORABUENA, %s has ganado!!!", jugadorQueJuega.nombre());
        } else {
            System.out.println("Habéis empatado ya que no quedan más casillas libres.");
        }
    }

    private boolean tirar(Jugador jugador){
        boolean jugadaGanadora = false;
        boolean jugadaValida = false;
        do {
            try{
                jugadaGanadora = tablero.introducirFicha(Consola.leerColumna(jugador) , jugador.colorFichas());
                System.out.printf("%n%s%n", tablero);
                jugadaValida = true;
            } catch (CuatroEnRayaExcepcion e) {
                System.out.println(e.getMessage());
            }
        } while (!jugadaValida);
        return jugadaGanadora;
    }
}
