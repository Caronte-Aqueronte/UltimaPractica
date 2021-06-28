package interfaces_gui;

import java.io.IOException;
import lectores_de_archivos.LectorBinario;
import principal.Principal;

public class MenuPrincipal extends javax.swing.JFrame {

    //constructor
    public MenuPrincipal() throws IOException {
       initComponents();
       //leemos el fichero binario que es statico para usarlo en todo el programa
       LectorBinario lectorBinario = new LectorBinario(Principal.jugadores, Principal.file);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevaPartida = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevaPartida.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevaPartida.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btnNuevaPartida.setForeground(new java.awt.Color(51, 51, 51));
        btnNuevaPartida.setText("Nueva Partida");
        btnNuevaPartida.setFocusable(false);
        btnNuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaPartidaActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevaPartida, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 290, 61));

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(51, 51, 51));
        btnAgregar.setText("Agregar jugador nuevo");
        btnAgregar.setFocusable(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 290, 61));

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bienvenido");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Serpientes.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    //abrimos el form que correspde 
    private void btnNuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaPartidaActionPerformed
        ElegirJugadores ejegir = new ElegirJugadores();
        ejegir.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNuevaPartidaActionPerformed
    //abrimos el form que correspde 
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        IngresoDeJugadores ingreso = new IngresoDeJugadores(this, true);
        ingreso.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnNuevaPartida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
