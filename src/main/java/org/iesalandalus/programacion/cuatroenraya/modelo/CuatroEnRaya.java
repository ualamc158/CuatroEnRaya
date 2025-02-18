package org.iesalandalus.programacion.cuatroenraya.modelo;

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

    }

    private boolean tirar(Jugador jugador){

    }
}
