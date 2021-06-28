package interfaces_gui;

import clases_para_tabla.Tabla;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ElegirJugadores extends javax.swing.JFrame {

    private Tabla tabla = new Tabla();
    //este sera el modelo que tomara nuetra jtabble cada que se cree un nuevo jugador
    private DefaultTableModel modelo = new DefaultTableModel() {
        /**
         * este metodo hace que la tabla no sea editable por el usuario
         *
         * @param filas
         * @param columnas
         * @return
         */
        @Override
        public boolean isCellEditable(int filas, int columnas) {
            return false;
        }
    };
    private DefaultTableModel modelo2 = tabla.getModeloAux();

    public ElegirJugadores() {
        initComponents();
        tabla.cargarTablaDeJugadores(dgvJugadores);
        llenarClumnasTablaJUgadoresListos();
    }

    public void llenarClumnasTablaJUgadoresListos() {

        //agramamos la columnas
        ArrayList<Object> nombrecolumna = new ArrayList<>();
        nombrecolumna.add("Id");
        nombrecolumna.add("Nombre");
        nombrecolumna.add("Apellido");
        nombrecolumna.add("Partidas jugadas");
        nombrecolumna.add("Partidas ganadas");
        nombrecolumna.add("Partidas perdidas");
        //
        //agregamos las columnas
        for (Object objeto : nombrecolumna) {
            modelo.addColumn(objeto);
        }
        //le damos a la tabla el modelo
        dgvJugadoresListos.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvJugadores = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        dgvJugadoresListos = new javax.swing.JTable();
        btnPasar = new javax.swing.JButton();
        btnJugar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dgvJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dgvJugadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dgvJugadoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dgvJugadores);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 350, 240));

        dgvJugadoresListos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(dgvJugadoresListos);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 350, 240));

        btnPasar.setBackground(new java.awt.Color(255, 255, 255));
        btnPasar.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        btnPasar.setText("Pasar");
        btnPasar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnPasar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnPasar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 190, 50));

        btnJugar.setBackground(new java.awt.Color(255, 255, 255));
        btnJugar.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        btnJugar.setText("Jugar");
        btnJugar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnJugar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(btnJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 450, 270, 80));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Jugadores existentes");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Jugadores preparados");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dgvJugadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dgvJugadoresMouseClicked
        int filaSeleccionada = dgvJugadores.getSelectedRow();
        if (filaSeleccionada >= 0) {
            if (dgvJugadoresListos.getRowCount() < 6) {
                //obtenemos todos los valores segun la tabla de jugadores
                String[] datos = new String[6];
                datos[0] = dgvJugadores.getValueAt(filaSeleccionada, 0).toString();
                datos[1] = dgvJugadores.getValueAt(filaSeleccionada, 1).toString();
                datos[2] = dgvJugadores.getValueAt(filaSeleccionada, 2).toString();
                datos[3] = dgvJugadores.getValueAt(filaSeleccionada, 3).toString();
                datos[4] = dgvJugadores.getValueAt(filaSeleccionada, 4).toString();
                datos[5] = dgvJugadores.getValueAt(filaSeleccionada, 5).toString();
                //agregamos la fila al modelo de tabla
                modelo.addRow(datos);
                //borramos la tabla jugadores listos
                tabla.borrarTabla(dgvJugadoresListos);
                //le damos el modelo nuevo a la tabla
                dgvJugadoresListos.setModel(modelo);
                //eliminamos la fila del modelo de la tabla jugadores ingresados
                modelo2.removeRow(filaSeleccionada);
                //eliminamos la tabla
                tabla.borrarTabla(dgvJugadores);
                //le damos el nuevo modelo
                dgvJugadores.setModel(modelo2);

            } else {
                JOptionPane.showMessageDialog(null, "Limite de jugadores alcanzado");
            }
        }
    }//GEN-LAST:event_dgvJugadoresMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnPasar;
    private javax.swing.JTable dgvJugadores;
    private javax.swing.JTable dgvJugadoresListos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
