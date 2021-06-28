package clases_para_tablero;

public class Casilla {
    private boolean pierdeTurno;
    private boolean tirarDados;
    private boolean avanza;
    private boolean retrocede;
    private boolean subida;
    private boolean bajada;
    /**
     * Contructor de casilla cada boolean representa el accionar de cada casilla
     * @param pierdeTurno
     * @param tirarDados
     * @param avanza
     * @param retrocede
     * @param subida
     * @param bajada 
     */
    public Casilla(boolean pierdeTurno, boolean tirarDados, boolean avanza, boolean retrocede, boolean subida, boolean bajada) {
        this.pierdeTurno = pierdeTurno;
        this.tirarDados = tirarDados;
        this.avanza = avanza;
        this.retrocede = retrocede;
        this.subida = subida;
        this.bajada = bajada;
    }
    //Getters y setters
    public boolean isPierdeTurno() {
        return pierdeTurno;
    }

    public void setPierdeTurno(boolean pierdeTurno) {
        this.pierdeTurno = pierdeTurno;
    }

    public boolean isTirarDados() {
        return tirarDados;
    }

    public void setTirarDados(boolean tirarDados) {
        this.tirarDados = tirarDados;
    }

    public boolean isAvanza() {
        return avanza;
    }

    public void setAvanza(boolean avanza) {
        this.avanza = avanza;
    }

    public boolean isRetrocede() {
        return retrocede;
    }

    public void setRetrocede(boolean retrocede) {
        this.retrocede = retrocede;
    }

    public boolean isSubida() {
        return subida;
    }

    public void setSubida(boolean subida) {
        this.subida = subida;
    }

    public boolean isBajada() {
        return bajada;
    }

    public void setBajada(boolean bajada) {
        this.bajada = bajada;
    }
    
}
