package clases_para_nueva_partida;

import javax.swing.JLabel;

public class Cronometro extends Thread {

    private JLabel min;
    private JLabel seg;
    private JLabel mili;

    public Cronometro(JLabel min, JLabel seg, JLabel mili) {
        this.min = min;
        this.seg = seg;
        this.mili = mili;
    }

    
    @Override
    public void run() {
        int minutos = 0;
        int segundos = 0;
        int milesimas = 0;
        try {
            //Mientras cronometroActivo sea verdadero entonces seguira
            //aumentando el tiempo
            while (true) {
                Thread.sleep(4);
                //Incrementamos 4 milesimas de segundo
                milesimas += 4;
                mili.setText(String.valueOf(milesimas));
                //Cuando llega a 1000 osea 1 segundo aumenta 1 segundo
                //y las milesimas de segundo de nuevo a 0
                if (milesimas == 1000) {
                    milesimas = 0;
                    segundos += 1;
                    mili.setText(String.valueOf(milesimas));
                    seg.setText(String.valueOf(segundos));
                    //Si los segundos llegan a 60 entonces aumenta 1 los minutos
                    //y los segundos vuelven a 0
                    if (segundos == 60) {
                        segundos = 0;
                        minutos++;
                        min.setText(String.valueOf(minutos));
                        seg.setText(String.valueOf(segundos));
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
