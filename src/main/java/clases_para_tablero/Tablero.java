package clases_para_tablero;

public class Tablero {

    private Casilla[][] miTablero;
    /**
     * Este contructor indica la cantidad de espacions que tendra el tablero, ademas llena el tablero por default
     * @param x
     * @param y 
     */
    public Tablero(int x, int y) {
        miTablero = new Casilla[x][y];
        llenarTableroDefault();
    }

    /**
     * este metodo se ejecuta cada vez creamos un nuevo tablero llena todo el
     * tablero con false y se rescribira cuando el programa leea el archivo de
     * texto
     */
    private void llenarTableroDefault() {
        for (int x = 0; x < miTablero.length; x++) {
            for (int y = 0; y < miTablero[x].length; y++) {
                miTablero[x][y] = new Casilla(false, false, false, false, false, false);
            }
        }
    }
    //gettr
    public Casilla[][] getMiTablero() {
        return miTablero;
    }

}
