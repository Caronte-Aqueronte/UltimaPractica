package clases_para_tabla;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jugador.Jugador;
import principal.Principal;

public class Tabla {

    //Este metodo carga la tabla anyadiendo coliumnas y filas traidas del principal
    public void cargarTablaDeJugadores(JTable tabla) {
        //este sera el modelo que tomara nuetra jtabble cada que se cree un nuevo jugador
        DefaultTableModel modelo = new DefaultTableModel() {
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
        //borramos la tabla
        borrarTabla(tabla);
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
        tabla.setModel(modelo);
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
        tabla.setModel(modelo);
    }

    //Este metodo crea un nuevo table model y lo iguala al modelo de la tabla
    public void borrarTabla(JTable tabla) {
        DefaultTableModel modelo2 = new DefaultTableModel();
        tabla.setModel(modelo2);
    }
}
