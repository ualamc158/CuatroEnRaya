package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Objects;

public class Tablero {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
    private Casilla[][] tablero;

    public Tablero() {
        tablero = new Casilla[FILAS][COLUMNAS];
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++){
                tablero[fila][columna] = new Casilla();
            }
        }
    }

    private boolean columnaVacia(int columna) {
        return columna != 0;
    }

    public boolean estaVacio() {
        boolean vacio = true;
        for (int columna = 0; columna < COLUMNAS && vacio; columna++) {
            vacio = columnaVacia(columna);
        }
        return vacio;
    }

    private boolean columnaLlena(int columna) {
        return tablero[FILAS - 1][columna].estaOcupada();
    }

    public boolean estaLleno() {
        boolean lleno = true;
        for (int columna = 0; columna < COLUMNAS && lleno; columna++) {
            if (!columnaLlena(columna)) {
                lleno = false;
            }
        }
        return lleno;
    }

    public boolean introducirFicha(int columna, Ficha ficha) throws CuatroEnRayaExcepcion {
        comprobarColumna(columna);
        comprobarFicha(ficha);
        if(columnaLlena(columna)){
            throw new CuatroEnRayaExcepcion("La columna esta llena.");
        }
        tablero[getPrimeraFilaVacia(columna)][columna].setFicha(ficha);
        return true;
    }

    private void comprobarFicha(Ficha ficha) {
        Objects.requireNonNull(ficha, "La ficha no puede ser nula.");
    }

    private void comprobarColumna(int columna) {
        if (columna < 0 || columna > COLUMNAS) {
            throw new IllegalArgumentException("La columna es erronea");
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        comprobarColumna(columna);
        int fila;
        boolean encontrada = false;
        for (fila = 0; fila < FILAS && !encontrada; fila++) {
            encontrada = !tablero[fila][columna].estaOcupada();
        }
        return fila;
    }

    /*private boolean objetivoAlcanzado(int fichasIgualesConsecutivas){

    }*/

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int contador = 0;
        for (int columna = 0; columna < COLUMNAS; columna++) {
            if (ficha == tablero[fila][columna].getFicha()) {
                contador++;
            }
        }
        return contador >= 4;
    }

    /*private boolean comprobarVertical(int columna, Ficha ficha){
        int contador = 0;
        for
    }*/

}
