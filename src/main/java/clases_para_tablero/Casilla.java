package clases_para_tablero;

public class Casilla {

    private String comportamiento;
    private int cantidadDePosiciones;
    private int filaFinal;
    private int columnaFinal;

    /**
     * Constructor
     *
     * @param comportamiento
     */
    public Casilla(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public Casilla(String comportamiento, int cantidadDePosiciones) {
        this.comportamiento = comportamiento;
        this.cantidadDePosiciones = cantidadDePosiciones;
    }

    public Casilla(String comportamiento, int filaFinal, int columnaFinal) {
        this.comportamiento = comportamiento;
        this.filaFinal = filaFinal;
        this.columnaFinal = columnaFinal;
    }

    //Getters y setters
    public String getComportamiento() {
        return comportamiento;
    }

    public void setComportamiento(String comportamiento) {
        this.comportamiento = comportamiento;
    }

    public int getCantidadDePosiciones() {
        return cantidadDePosiciones;
    }

    public int getFilaFinal() {
        return filaFinal;
    }

    public int getColumnaFinal() {
        return columnaFinal;
    }

}
