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
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                Object objeto = objectInputStream.readObject();
                jugadores.ingresarJugadorConRegistro((Jugador) objeto);
            }
        } catch (Exception ex) {

        }
        return false;
    }
}
