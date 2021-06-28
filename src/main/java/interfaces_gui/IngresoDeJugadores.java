package interfaces_gui;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jugador.Jugador;
import principal.Principal;

public class IngresoDeJugadores extends javax.swing.JDialog {

    //este sera el modelo que tomara nuetra jtabble cada que se cree un nuevo jugador
    private DefaultTableModel modelo = new DefaultTableModel();

    /**
     * Este es el contructor
     *
     * @param parent
     * @param modal
     */
    public IngresoDeJugadores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //atomaticamnete cargamos la tabla
        cargarTabla();
    }

    //Este metodo carga la tabla anyadiendo coliumnas y filas traidas del principal
    public void cargarTabla() {
        //borramos la tabla
        borrarTabla();
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
        dgvJugadores.setModel(modelo);
        //regorremos el array que es estatico
        for (Jugador item : Principal.jugadores.getJugadores()) {
            //cargamos cada parametro del objeto jugador que esta en la iteracion
            String id = Integer.toString(item.getId());
            String nombre = item.getNombre();
            String apellido = item.getApellido();
            String partidasJugadas = Integer.toString(item.getPartidasJugadas());
            String partidasGanadas = Integer.toString(item.getPartidasGanadas());
            String partidasPerdias = Integer.toString(item.getPartidasPerdidas());
            //creamos un array en orden segun las columnas
            String[] campos = new String[]{id, nombre, apellido, partidasJugadas, partidasGanadas, partidasPerdias};
            //anyadimos la nueva fila de paramtros al modelo
            modelo.addRow(campos);
        }
        //cargamos el modelo
        dgvJugadores.setModel(modelo);
    }

    //Este metodo crea un nuevo table model y lo iguala al gloval para que se resetee
    public void borrarTabla() {
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo = modelo2;
        dgvJugadores.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dgvJugadores = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 102));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 90, 21));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Jugadores Ingresados");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 240, 21));
        jPanel1.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 240, -1));
        jPanel1.add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 240, -1));

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 90, 21));

        dgvJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Apellido", "Partidas Jugadas", "Partidas Ganadas", "Partidas Perdias"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dgvJugadores);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 520, 230));

        jButton1.setText("Ingresar nuevo jugador");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 230, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Este metodo envia los parametros de los txt para que sean ingresados por
     * primera vez
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!txtnombre.getText().equals("") && !txtapellido.getText().equals("")) {
            try {
                System.out.println(txtapellido.getText());
                Principal.jugadores.ingresarJugadorNuevo(txtnombre.getText(), txtapellido.getText());
                JOptionPane.showMessageDialog(null, "Jugador agregado con exito");
                cargarTabla();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al ingresar jugador nuevo");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Parametros vacios");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dgvJugadores;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
