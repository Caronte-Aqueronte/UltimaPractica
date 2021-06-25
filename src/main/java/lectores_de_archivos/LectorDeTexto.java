package lectores_de_archivos;

import interfaces.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectorDeTexto extends Lector implements Leer {

    @Override
    public void leerArchivo(File archivo) throws FileNotFoundException, IOException {
        String linea;
        String[] campos;
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        while ((linea = br.readLine()) != null) {
            campos = separarCampos(linea, "(");
            if (registro != null) {
                //registros.add(registro);
                CargaMasiva.txtcontenedor.append(linea + "\n");
            } else {
                CargaMasiva.txterror.append(linea + "\n");
            }
        }
        fr.close();
    }
    public void construirRegistro(String [] campos){
        switch(campos[0]){
            case "tablero":
                break;
            case "pierdeturno":
                break;
            case "tirardados":
                break;
                
        }
    }
}
