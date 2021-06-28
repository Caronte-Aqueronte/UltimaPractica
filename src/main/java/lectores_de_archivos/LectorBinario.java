package lectores_de_archivos;

import interfaces.Leer;
import java.io.*;
import jugador.*;

public class LectorBinario implements Leer {

    private VectorDeJugador jugadores;

    public LectorBinario(VectorDeJugador jugadores, File file) throws IOException {
        this.jugadores = jugadores;
        leerArchivo(file);
    }

    /**
     * Este metodo lee el archivo, se ejecuta cada vez que se abre el programa
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    @Override
    public boolean leerArchivo(File file) throws FileNotFoundException, IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file); //mandamos el path que es el archivo binario
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream); //preparamos para un blujo de salida
            while (true) {//este cliclo NO es infinito pues lee hasta el final del archivo donde es false y se convierte en false
                Object objeto = objectInputStream.readObject(); //creamos un nuevo objeto a partir de nuestra dta
                jugadores.ingresarJugadorConRegistro((Jugador) objeto); //ingresamos el objeto guardado al array
            }
        } catch (Exception ex) {

        }
        return false;
    }
}
