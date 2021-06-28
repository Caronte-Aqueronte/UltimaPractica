package jugador;

import java.io.*;
import java.util.ArrayList;

public class VectorDeJugador {
    
    ArrayList<Jugador> jugadores = new ArrayList<>();
    int contadorId = 1;
    
    public void ingresarJugadorNuevo(String nombre, String apellido) throws IOException {
        Jugador jugador = new Jugador(contadorId, nombre, apellido, 0, 0, 0);
        jugadores.add(jugador);
        guardarDatosEnBinario(jugador);
        contadorId++;
    }
    
    public void ingresarJugadorConRegistro(int id, String nombre, String apellido, int partidasJugadas, int partidasGanadas, int partidasPerdias) {
        jugadores.add(new Jugador(id, nombre, apellido, partidasJugadas, partidasGanadas, partidasPerdias));
        contadorId++;
    }

    //este codio se ejecutara cada que se crea un nuevo jugador
    public void guardarDatosEnBinario(Jugador jugador) throws FileNotFoundException, IOException {
        File file; //creamos el archivo
        FileOutputStream fileOutputStream; // vemos los out pusts necesarios
        ObjectOutputStream objectOutputStream;
        file = new File("datosdelosjugadore.bin"); //le ponemos nombre al archivo
        fileOutputStream = new FileOutputStream(file); //escribims el archivo
        objectOutputStream = new ObjectOutputStream(fileOutputStream); //preparamos para escribir el objeto
        objectOutputStream.writeObject(jugador); //escribirmos el objeto
    }
}
