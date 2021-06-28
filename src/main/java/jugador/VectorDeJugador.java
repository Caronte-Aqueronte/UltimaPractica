package jugador;

import java.io.*;
import java.util.ArrayList;
import principal.Principal;

public class VectorDeJugador {

    private ArrayList<Jugador> jugadores = new ArrayList<>();

    /**
     * Contructor de un nuevo jugador
     *
     * @param nombre
     * @param apellido
     * @throws IOException
     */
    public void ingresarJugadorNuevo(String nombre, String apellido) throws IOException {
        Jugador jugador = new Jugador((jugadores.size() + 1), nombre, apellido, 0, 0, 0);
        jugadores.add(jugador);
        guardarDatosEnBinario();
    }

    //este metodo sirve para agregar objetos desde el lector de binarios
    public void ingresarJugadorConRegistro(Jugador jugador) {
        jugadores.add(jugador);
    }

    //este codio se ejecutara cada que se crea un nuevo jugador y guarda todos los miesmbros del array (nuevos y viejos)
    public void guardarDatosEnBinario() throws FileNotFoundException, IOException {
        File file = Principal.file; //creamos el archivo
        FileOutputStream fileOutputStream; // vemos los out pusts necesarios
        ObjectOutputStream objectOutputStream;
        fileOutputStream = new FileOutputStream(file); //escribims el archivo
        objectOutputStream = new ObjectOutputStream(fileOutputStream); //preparamos para escribir el objeto
        //recorremos el array
        for (Jugador item : jugadores) {
            objectOutputStream.writeObject(item); //escribirmos el objeto
        }
    }

    //getter
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

}
