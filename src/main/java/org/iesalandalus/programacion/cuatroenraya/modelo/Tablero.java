package org.iesalandalus.programacion.cuatroenraya.modelo;

import java.util.Objects;

public class Tablero {
    public static final int FILAS = 6;
    public static final int COLUMNAS = 7;
    public static final int FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS = 4;
    private final Casilla[][] casillas;

    public Tablero() {
        casillas = new Casilla[FILAS][COLUMNAS];
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                casillas[fila][columna] = new Casilla();
            }
        }
    }

    private boolean columnaVacia(int columna) {
        return !casillas[0][columna].estaOcupada();
    }

    public boolean estaVacio() {
        boolean vacio = true;
        for (int columna = 0; columna < COLUMNAS && vacio; columna++) {
            vacio = columnaVacia(columna);
        }
        return vacio;
    }

    private boolean columnaLlena(int columna) {
        return casillas[FILAS - 1][columna].estaOcupada();
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
        int fila = getPrimeraFilaVacia(columna);
        if (columnaLlena(columna)) {
            throw new CuatroEnRayaExcepcion("Columna llena.");
        }
        casillas[fila][columna].setFicha(ficha);
        return comprobarTirada(fila, columna);
    }

    private void comprobarFicha(Ficha ficha) {
        Objects.requireNonNull(ficha, "La ficha no puede ser nula.");
    }

    private void comprobarColumna(int columna) {
        if (columna < 0 || columna >= COLUMNAS) {
            throw new IllegalArgumentException("Columna incorrecta.");
        }
    }

    private int getPrimeraFilaVacia(int columna) {
        int fila;
        boolean encontrada = false;
        for (fila = 0; fila < FILAS && !encontrada; fila++) {
            encontrada = !casillas[fila][columna].estaOcupada();
        }
        return fila - 1;
    }

    private boolean objetivoAlcanzado(int fichasIgualesConsecutivas) {
        return fichasIgualesConsecutivas >= FICHAS_IGUALES_CONSECUTIVAS_NECESARIAS;
    }

    private boolean comprobarHorizontal(int fila, Ficha ficha) {
        int contador = 0;
        for (int columna = 0; columna < COLUMNAS && !objetivoAlcanzado(contador); columna++) {
            if (ficha == casillas[fila][columna].getFicha()) {
                contador++;
            } else {
                contador = 0;
            }
        }
        return objetivoAlcanzado(contador);
    }

    private boolean comprobarVertical(int columna, Ficha ficha) {
        int contador = 0;
        for (int fila = 0; fila < FILAS && !objetivoAlcanzado(contador); fila++) {
            if (ficha == casillas[fila][columna].getFicha()) {
                contador++;
            } else {
                contador = 0;
            }
        }
        return objetivoAlcanzado(contador);
    }

    private boolean comprobarDiagonalNE(int filaActual, int columnaActual, Ficha ficha) {
        int desplazamiento = menor(filaActual, columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual - desplazamiento;
        int contador = 0;

        for (int fila = filaInicial, columna = columnaInicial; fila < FILAS && columna < COLUMNAS && !objetivoAlcanzado(contador); fila++, columna++) {
            if (casillas[fila][columna].estaOcupada() && ficha == casillas[fila][columna].getFicha()) {
                contador++;
            } else {
                contador = 0;
            }
        }
        return objetivoAlcanzado(contador);
    }

    private boolean comprobarDiagonalNO(int filaActual, int columnaActual, Ficha ficha) {
        int desplazamiento = menor(filaActual, COLUMNAS - 1 - columnaActual);
        int filaInicial = filaActual - desplazamiento;
        int columnaInicial = columnaActual + desplazamiento;
        int contador = 0;
        for (int fila = filaInicial, columna = columnaInicial; fila < FILAS && columna >= 0 && !objetivoAlcanzado(contador); fila++, columna--) {
            if (casillas[fila][columna].estaOcupada() && ficha == casillas[fila][columna].getFicha()) {
                contador++;
            } else {
                contador = 0;
            }
        }
        return objetivoAlcanzado(contador);
    }

    private int menor(int fila, int columna) {
        return Math.min(fila, columna);
    }

    private boolean comprobarTirada(int fila, int columna) {
        Ficha fichaActual = casillas[fila][columna].getFicha();
        return comprobarVertical(columna, fichaActual) ||
                comprobarHorizontal(fila, fichaActual) ||
                comprobarDiagonalNE(fila, columna, fichaActual) ||
                comprobarDiagonalNO(fila, columna, fichaActual);
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder();
        for (int i = FILAS - 1; i >= 0; i--){
            salida.append("|");
            for (int j = 0; j < COLUMNAS; j++) {
                salida.append(casillas[i][j]);
            }
            salida.append("|").append("\n");
        }
        salida.append(" ").append("-".repeat(COLUMNAS)).append("\n");
        return salida.toString();
    }
}
