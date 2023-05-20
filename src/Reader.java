import java.io.*;
import java.util.*;

public class Reader{
    ArrayList<String> lineas = new ArrayList<String>();

    /**
     * @param Arch Es la direccion en donde esta el archivo que se debe leer
     * @author Juan Pablo Solis
     * @return String
     */
    public void Leer(String Arch){
        try (BufferedReader br = new BufferedReader(new FileReader(Arch))) {
            String lineaActual;
            while ((lineaActual = br.readLine()) != null) {
                lineas.add(lineaActual);
            }//Por si surge error leyendo el archivo, para que no salga error en el codigo
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }
}
