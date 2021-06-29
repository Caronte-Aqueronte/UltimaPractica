package clases_para_nueva_partida;

import clases_para_tablero.Tablero;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jugador.Jugador;

public class NuevaPartida extends javax.swing.JFrame {

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private int espacioX;
    private int espacioY;

    public NuevaPartida(Tablero tablero, ArrayList<Jugador> jugadores) {
        initComponents();
        this.tablero = tablero;
        this.jugadores = jugadores;
        generarTablero();
    }

    public void generarTablero() {
        try {
            //este contador nos sirve para darle nombre al boton
            int contador = 1;
            ajustarBotonesAlPanel(tablero.getX(), tablero.getY(), contenedor);
            //tamaño de gridLayout del panel
            contenedor.setLayout(new GridLayout(tablero.getX(), tablero.getY()));
            for (int x = 0; x < tablero.getMiTablero().length; x++) {
                for (int y = 0; y < tablero.getMiTablero()[x].length; y++) {
                    //CREAMOS EL BOTON COMO LO QUEREMOS
                    JButton boton = new JButton();
                    boton.setFont(new java.awt.Font("Verdana", 0, 16));
                    boton.setToolTipText(x + "," + y);
                    boton.setText(String.valueOf(contador));
                    boton.setSize(espacioX, espacioY);
                    //
                    contenedor.add(boton);
                    actualizarTablero(contenedor);
                    contador++;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    // este metodo da el tamano de los botones para que se acoplen al panel
    public void ajustarBotonesAlPanel(int cuadriculaColumnas, int cuadriculaFilas, JPanel panel) {
        espacioX = panel.getX() / cuadriculaColumnas;
        espacioY = panel.getY() / cuadriculaFilas;
    }

    // este metodo actiliza el tablero cada que se agrega una nueva columna
    private void actualizarTablero(JPanel panel) {
        panel.validate();
        panel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout contenedorLayout = new javax.swing.GroupLayout(contenedor);
        contenedor.setLayout(contenedorLayout);
        contenedorLayout.setHorizontalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
        );
        contenedorLayout.setVerticalGroup(
            contenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedor;
    // End of variables declaration//GEN-END:variables
}
