package jugador;

import java.io.*;
import java.util.ArrayList;
import principal.Principal;

public class VectorDeJugador {

    private ArrayList<Jugador> jugadores = new ArrayList<>();

    public void ingresarJugadorNuevo(String nombre, String apellido) throws IOException {
        Jugador jugador = new Jugador((jugadores.size() + 1), nombre, apellido, 0, 0, 0);
        jugadores.add(jugador);
        guardarDatosEnBinario();
    }

   public void ingresarJugadorConRegistro(Jugador jugador) {
        jugadores.add(jugador);
    }

    //este codio se ejecutara cada que se crea un nuevo jugador
    public void guardarDatosEnBinario() throws FileNotFoundException, IOException {
        File file = Principal.file; //creamos el archivo
        FileOutputStream fileOutputStream; // vemos los out pusts necesarios
        ObjectOutputStream objectOutputStream;
        fileOutputStream = new FileOutputStream(file); //escribims el archivo
        objectOutputStream = new ObjectOutputStream(fileOutputStream); //preparamos para escribir el objeto
        //recorremos el array
        for(Jugador item: jugadores){
            objectOutputStream.writeObject(item); //escribirmos el objeto
        }
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
}
