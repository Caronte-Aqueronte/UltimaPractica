package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Leer {

    public void leerArchivo(File archivo) throws FileNotFoundException, IOException;
}
