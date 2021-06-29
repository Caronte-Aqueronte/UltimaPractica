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

    /**
     * Nesecitamos estos fatos para poder leer el tablero que se ingresara
     *
     * @param tablero
     * @param panelAsiertos
     * @param panelErrores
     */
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
                if (contador == 0 && !campos[0].equals("tablero")) { //si el contador es 0 significa que es la primera linea del archivo y esta debe de ser uns intruccion "tablero" de lo contrario no se puede seguir leyendo
                    JOptionPane.showMessageDialog(null, "Archivo de texto invalido, primera linea debe indicar tamaño del tablero");
                    return false;
                } else if (contador == 0 && campos[0].equals("tablero")) { // si el contador es 0 entonces es la primera linea del archivo ademas se crea un nuevo objeto
                    tablero.setXY(Integer.valueOf(campos[1]), Integer.valueOf(campos[2]));
                    panelAsiertos.append(linea + "\n");
                    contador++;
                } else {
                    registro = construirRegistro(campos);
                    if (registro == true) {//si se concreto la entrda corrctamente entonces agregamos al panel de entradas correctas
                        panelAsiertos.append(linea + "\n");
                    } else {//sino entonces agregamos a los incorrectos
                        panelErrores.append(linea + "\n");
                    }
                    contador++; //este contador nos sirve para saber cual es la primeralineaF
                }
            }
            fr.close();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en la entrada");
            return false;
        }
    }

    //depende de la instruccion se dara un caso y se reescribira la casilla con su nuevo comportamiento
    public boolean construirRegistro(String[] campos) {
        try {
            Casilla[][] miTablero = tablero.getMiTablero();
            switch (campos[0]) {
                case "pierdeturno":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla("pierdeturno");
                    return true;
                case "tirardados":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla("tirardados");
                    return true;
                case "avanza":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla("avanza", Integer.valueOf(campos[3]));
                    return true;
                case "retorcede":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla("retorcede", Integer.valueOf(campos[3]));
                    return true;
                case "subida":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla("subida", Integer.valueOf(campos[3]), Integer.valueOf(campos[4]));
                    return true;
                case "bajada":
                    miTablero[Integer.valueOf(campos[1])][Integer.valueOf(campos[2])] = new Casilla("bajada", Integer.valueOf(campos[3]), Integer.valueOf(campos[4]));
                    return true;
                default:
                    return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al crear casilla");
            return false;
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

}
