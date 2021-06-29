package clases_para_nueva_partida;

import javax.swing.JLabel;

public class HiloNumeroAleatorio extends Thread {

    private JLabel dado1;
    private JLabel dado2;
    private boolean detener = false;

    public HiloNumeroAleatorio(String msg, JLabel dado1, JLabel dado2) {
        super(msg);
        this.dado1 = dado1;
        this.dado2 = dado2;
    }

    @Override
    public void run() {
        int dado1;
        int dado2;
        while (detener == false) {
            dado1 = (int) (Math.random() * (6 - 1 + 1) + 1);
            dado2 = (int) (Math.random() * (6 - 1 + 1) + 1);
            this.dado1.setText(String.valueOf(dado1));
            this.dado2.setText(String.valueOf(dado2));
        }
    }

    public void detener() {
        detener = true;
    }
}
