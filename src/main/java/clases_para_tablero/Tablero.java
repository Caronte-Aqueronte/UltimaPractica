package clases_para_tablero;

public class Tablero {

    private Casilla[][] miTablero;

    public Tablero(int x, int y) {
        miTablero = new Casilla[x][y];
        llenarTableroDefault();
    }

    /**
     * este metodo se ejecuta cada vez creamos un nuevo tablero
     */
    private void llenarTableroDefault() {
        for (int x = 0; x < miTablero.length; x++) {
            for (int y = 0; y < miTablero[x].length; y++) {
                miTablero[x][y] = new Casilla(false, false, false, false, false, false);
            }
        }
    }

    public Casilla[][] getMiTablero() {
        return miTablero;
    }

}
