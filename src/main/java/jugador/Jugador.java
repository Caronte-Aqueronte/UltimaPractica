package jugador;

import java.io.Serializable;

public class Jugador implements Serializable {

    int id;
    String nombre;
    String apellido;
    int partidasJugadas;
    int partidasGanadas;
    int partidasPerdidas;

    /**
     * Este es el contructor que tiene que tener cada jugador nuevo
     *
     * @param id
     * @param nombre
     * @param Apellido
     * @param partidasJugadas
     * @param partidasGanadas
     * @param partidasPerdidas
     */
    public Jugador(int id, String nombre, String Apellido, int partidasJugadas, int partidasGanadas, int partidasPerdidas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = Apellido;
        this.partidasJugadas = partidasJugadas;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
    }

    //Setters y getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String Apellido) {
        this.apellido = Apellido;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    public Object[] toObject() {
        Object[] aDevolver = {id, nombre, apellido, partidasJugadas, partidasGanadas, partidasPerdidas};
        return aDevolver;
    }
//
}
