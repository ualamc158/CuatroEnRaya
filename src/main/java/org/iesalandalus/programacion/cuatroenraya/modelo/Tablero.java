package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Tablero {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
    private Casilla[][] casillas;

    public Tablero() {
        Casilla[][] casillas = new Casilla[FILAS][COLUMNAS];
    }

    private boolean columnaVacia(int columna) {
        return columna != 0;
    }

    public boolean estaVacio() {
        boolean salida = false;

        for (int columna = 0; columna < COLUMNAS &&; columna++) {
            if (columnaVacia(columna)) {
                salida = true;
            } else {
                salida = false;
            }
        }
        return salida;
    }

}
