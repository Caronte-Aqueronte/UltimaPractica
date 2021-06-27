package lectores_de_archivos;

import clases_para_tablero.*;
import interfaces.*;
import java.io.*;
import java.util.Arrays;
import javax.swing.*;

public class LectorDeTexto extends Lector implements Leer {

    private Tablero tablero;
    private JTextArea panelAsiertos;
    private JTextArea panelErrores;

    public LectorDeTexto(Tablero tablero, JTextArea panelAsiertos, JTextArea panelErrores) {
        this.tablero = tablero;
        this.panelAsiertos = panelAsiertos;
        this.panelErrores = panelErrores;
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
            boolean registro = false;
            //

            while ((linea = br.readLine()) != null) { //se repetira hasta que lea la utima linea del archivo
                campos = separarCampos(linea, "("); //obtenemos un array con los campos
                System.out.println(Arrays.toString(campos));
                if (contador == 0 && !campos[0].equals("tablero")) { //si el contador es 0 significa que es la primera linea del archivo y esta debe de ser uns intruccion "tablero" de lo contrario no se puede seguir leyendo
                    JOptionPane.showMessageDialog(null, "Archivo de texto invalido, primera linea debe indicar tamaño del tablero");
                    return false;
                } else if (contador == 0 && campos[0].equals("tablero")) { // si el contador es 0 entonces es la primera linea del archivo ademas se crea un nuevo objeto
                    tablero = new Tablero(Integer.valueOf(campos[1]), Integer.valueOf(campos[2]));
                    panelAsiertos.append(linea + "\n");
                    contador++;
                } else {
                    registro = construirRegistro(campos);
                    if (registro == true) {
                        //registros.add(registro);
                        panelAsiertos.append(linea + "\n");
                    } else {
                        panelErrores.append(linea + "\n");
                    }
                    contador++;
                }
            }

            fr.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en la entrada");
        }
         return false;
    }

    public boolean construirRegistro(String[] campos) {
        try {
            Casilla[][] miTablero = tablero.getMiTablero();
            switch (campos[0]) {
                case "pierdeturno":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(true, false, false, false, false, false);
                    return true;
                case "tirardados":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, true, false, false, false, false);
                    return true;
                case "avanza":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, false, true, false, false, false);
                    return true;
                case "retorcede":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, false, false, true, false, false);
                    return true;
                case "subida":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, false, false, false, true, false);
                    return true;
                case "bajada":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla(false, false, false, false, false, true);
                    return true;
                default:
                    return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al crear casilla");
            return false;
        }
    }
}
