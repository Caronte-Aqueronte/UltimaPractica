package clases_para_nueva_partida;

import clases_para_tablero.Tablero;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jugador.Jugador;

public class NuevaPartida extends javax.swing.JFrame {

    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private int espacioX;
    private int espacioY;

    /**
     * COnstructor
     *
     * @param tablero
     * @param jugadores
     */
    public NuevaPartida(Tablero tablero, ArrayList<Jugador> jugadores) {
        initComponents();
        this.tablero = tablero;
        this.jugadores = jugadores;
        generarTablero();
    }

    //este metodo crea el tablero tomano en cuenta el tamanyo de la matriz de casilla creadas en tablero
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
                    ImageIcon imagen;
                    ImageIcon imagenEscalada;
                    switch (tablero.getMiTablero()[x][y].getComportamiento()) {

                        case "pierdeturno":
                            imagen = new ImageIcon(getClass().getResource("/imagenes/pierdeturno.png"));
                            imagenEscalada = new ImageIcon(imagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                            boton.setIcon(imagenEscalada);
                            break;
                        case "tiradados":
                            imagen = new ImageIcon(getClass().getResource("/imagenes/dados.png"));
                            imagenEscalada = new ImageIcon(imagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                            boton.setIcon(imagenEscalada);
                            break;
                        case "avanza":
                            imagen = new ImageIcon(getClass().getResource("/imagenes/avanza.png"));
                            imagenEscalada = new ImageIcon(imagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                            boton.setIcon(imagenEscalada);
                            break;
                        case "retrocede":
                            imagen = new ImageIcon(getClass().getResource("/imagenes/retrocede.png"));
                            imagenEscalada = new ImageIcon(imagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                            boton.setIcon(imagenEscalada);
                            break;
                        case "subida":
                            imagen = new ImageIcon(getClass().getResource("/imagenes/subida.png"));
                            imagenEscalada = new ImageIcon(imagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                            boton.setIcon(imagenEscalada);
                            break;
                        case "bajada":
                            imagen = new ImageIcon(getClass().getResource("/imagenes/bajada.png"));
                            imagenEscalada = new ImageIcon(imagen.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                            boton.setIcon(imagenEscalada);
                            break;
                    }
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
