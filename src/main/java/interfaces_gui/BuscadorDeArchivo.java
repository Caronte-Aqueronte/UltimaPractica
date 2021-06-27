package interfaces_gui;

import clases_para_tablero.Tablero;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import lectores_de_archivos.LectorDeTexto;

public class BuscadorDeArchivo extends javax.swing.JFrame {
    Tablero tablero;
    public BuscadorDeArchivo() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtpath = new javax.swing.JTextField();
        btnBucar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtpath.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtpath.setEnabled(false);
        jPanel1.add(txtpath, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 452, 31));

        btnBucar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search-alt-2-regular-24 (1).png"))); // NOI18N
        btnBucar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBucarActionPerformed(evt);
            }
        });
        jPanel1.add(btnBucar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 60, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
        );

        pack();
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
            LectorDeTexto lectorDeTexto = new LectorDeTexto(tablero);
            try {
                boolean leerArchivo = lectorDeTexto.leerArchivo(fichero);
                if(leerArchivo == true){
                    
                }else{
                    MenuPrincipal menuPrincipal = new MenuPrincipal();
                    menuPrincipal.setVisible(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(BuscadorDeArchivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnBucarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBucar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtpath;
    // End of variables declaration//GEN-END:variables
}
