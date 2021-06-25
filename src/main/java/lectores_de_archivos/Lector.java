package lectores_de_archivos;

public class Lector {

    public String[] separarCampos(String linea, String identificador) {
        //identificamos la instruccion
        String[] inicioRegistro = linea.split("\\"+identificador);
        //igualamos un string a la instruccion
        String inicioRegistroReal = inicioRegistro[0];
        //creamos la linea de entrada
        String lineaDeCampos;
        //seteamos la linea de entrada
        lineaDeCampos = inicioRegistroReal + "," + linea.substring(inicioRegistroReal.length() + 1, linea.length() - 1);
        //cambiamos todos los espacios entre coma y texto para evitar errores
        String lineaDeCamposFinal = lineaDeCampos.replaceAll(", ", ",");
        //separamos las entradas por comas
        String[] campos = lineaDeCamposFinal.split(",");
        return campos;
    }
}
