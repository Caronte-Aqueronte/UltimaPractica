package lectores_de_archivos;

import clases_para_tablero.*;
import interfaces.*;
import java.awt.HeadlessException;
import java.io.*;
import javax.swing.JOptionPane;

public class LectorDeTexto extends Lector implements Leer {

    private Tablero tablero;

    public LectorDeTexto(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public boolean leerArchivo(File archivo) throws FileNotFoundException, IOException {
        try {
            //Estas son las variables y objetos que serviran para leer el fichero de entrada
            String linea;
            String[] campos;
            FileReader fr = new FileReader(archivo);
            int contador = 0;
            BufferedReader br = new BufferedReader(fr);
            //

            while ((linea = br.readLine()) != null) { //se repetira hasta que lea la utima linea del archivo
                campos = separarCampos(linea, "("); //obtenemos un array con los campos
                if (contador == 0 && !campos[0].equals("tablero")) { //si el contador es 0 significa que es la primera linea del archivo y esta debe de ser uns intruccion "tablero" de lo contrario no se puede seguir leyendo
                    JOptionPane.showMessageDialog(null, "Archivo de texto invalido, primera linea debe indicar tamaño del tablero");
                    return false;
                } else if (contador == 0 && campos[0].equals("tablero")) { // si el contador es 0 entonces es la primera linea del archivo ademas se crea un nuevo objeto
                    tablero = new Tablero(Integer.valueOf(campos[1]), Integer.valueOf(campos[2]));
                    contador++;
                } else {
                    construirRegistro(campos);
                    contador++;
                }
            }
            /*
            if (registro != null) {
                //registros.add(registro);
                CargaMasiva.txtcontenedor.append(linea + "\n");
            } else {
                CargaMasiva.txterror.append(linea + "\n");
            }*/
            fr.close();
            return true;
        } catch (HeadlessException | IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }

    public void construirRegistro(String[] campos) {
        try {
            Casilla[][] miTablero = tablero.getMiTablero();
            switch (campos[0]) {
                case "pierdeturno":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(true, false, false, false, false, false);
                    break;
                case "tirardados":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, true, false, false, false, false);
                    break;
                case "avanza":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, false, true, false, false, false);
                    break;
                case "retorcede":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, false, false, true, false, false);
                    break;
                case "subida":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, false, false, false, true, false);
                    break;
                case "bajada":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, false, false, false, false, true);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
}
