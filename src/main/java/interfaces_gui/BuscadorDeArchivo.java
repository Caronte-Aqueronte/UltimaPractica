package interfaces_gui;

import clases_para_nueva_partida.NuevaPartida;
import clases_para_tablero.Tablero;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.*;
import jugador.Jugador;
import lectores_de_archivos.LectorDeTexto;

public class BuscadorDeArchivo extends javax.swing.JFrame {

    public Tablero tablero = new Tablero(2,2);
    private ArrayList<Jugador> jugadores;

    public BuscadorDeArchivo(ArrayList<Jugador> jugadores) {
        initComponents();
        this.jugadores = jugadores;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtpath = new javax.swing.JTextField();
        btnBucar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtErrores = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtAsiertos = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtpath.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtpath.setEnabled(false);
        jPanel1.add(txtpath, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 452, 31));

        btnBucar.setBackground(new java.awt.Color(255, 255, 255));
        btnBucar.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        btnBucar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.png"))); // NOI18N
        btnBucar.setText("Buscar");
        btnBucar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBucarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBucar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 200, 30));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel1.setText("Errores:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 170, -1));

        txtErrores.setEditable(false);
        txtErrores.setColumns(20);
        txtErrores.setRows(5);
        jScrollPane3.setViewportView(txtErrores);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 780, 190));

        txtAsiertos.setEditable(false);
        txtAsiertos.setColumns(20);
        txtAsiertos.setRows(5);
        jScrollPane4.setViewportView(txtAsiertos);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 780, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnBucarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBucarActionPerformed
        //creamos un JFileChoooser que es la ventanan que deja al ususario elegir el archivo
        JFileChooser filechooser = new JFileChooser();
        //creamos el filtro de txt
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
        //anadimos el filtro al jfilechoooser
        filechooser.setFileFilter(filtro);
        //eliminamos la opcion todos los archivos delJFileChooser
        filechooser.setAcceptAllFileFilterUsed(false);
        //abrimos el filechosssr
        int seleccion = filechooser.showOpenDialog(this);
        //validamos la eleccion del ususairo
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //decimos que el fichero sera igual al archivo elegido
            File fichero = filechooser.getSelectedFile();
            //el txtxpatch escribira la direccion del archivo
            txtpath.setText(fichero.getAbsolutePath());
            LectorDeTexto lectorDeTexto = new LectorDeTexto(tablero, txtAsiertos, txtErrores);
            try {
                boolean leerArchivo = lectorDeTexto.leerArchivo(fichero);
                if (leerArchivo == true) {
                    NuevaPartida nv = new NuevaPartida(tablero, jugadores); 
                    this.dispose();
                    nv.setVisible(true);
                } else {
                    MenuPrincipal menuPrincipal = new MenuPrincipal();
                    this.dispose();
                    menuPrincipal.setVisible(true);

                }
            } catch (Exception ex) {

            }
        }
    }//GEN-LAST:event_btnBucarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBucar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea txtAsiertos;
    private javax.swing.JTextArea txtErrores;
    private javax.swing.JTextField txtpath;
    // End of variables declaration//GEN-END:variables
}
