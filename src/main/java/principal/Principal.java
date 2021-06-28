package principal;

import interfaces_gui.MenuPrincipal;
import java.io.File;
import java.io.IOException;
import jugador.VectorDeJugador;

public class Principal {

    public static final File file = new File("datosdelosjugadores.bin");
    public static VectorDeJugador jugadores = new VectorDeJugador();

    public static void main(String[] args) throws IOException {
      
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        menuPrincipal.setVisible(true);
       
    }
}
