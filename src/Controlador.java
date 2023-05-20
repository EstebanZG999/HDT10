import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Controlador {
    private Grafo grafo;
    private FloydWarshall floydWarshall;
    private Reader reader;

    public Controlador() {
        grafo = null;
        floydWarshall = null;
        reader = new Reader();
    }

    public void cargarGrafoDesdeArchivo(String nombreArchivo) {
        reader.Leer(nombreArchivo);
        ArrayList<String> lineas = reader.getLineas();

        // Obtener el tamaño del grafo
        int matrizSize = lineas.size() - 1;

        // Crear el grafo
        grafo = new Grafo(matrizSize);

        // Leer los datos de las ciudades y las distancias
        for (int i = 1; i < lineas.size(); i++) {
            String[] valores = lineas.get(i).split(" ");
            int ciudad1 = Integer.parseInt(valores[0]);
            int ciudad2 = Integer.parseInt(valores[1]);
            grafo.agregarArista(ciudad1, ciudad2);
        }

        // Crear el algoritmo de Floyd-Warshall
        floydWarshall = new FloydWarshall(grafo.getMatrizAdyacencia(), grafo.getMatrizAdyacencia(), matrizSize);
    }

    public void mostrarMatrizAdyacencia() {
        if (grafo != null) {
            int[][] matrizAdyacencia = grafo.getMatrizAdyacencia();
            for (int i = 0; i < grafo.getNumVertices(); i++) {
                for (int j = 0; j < grafo.getNumVertices(); j++) {
                    System.out.print(matrizAdyacencia[i][j] + "\t");
                }
                System.out.println();
            }
        } else {
            System.out.println("No se ha cargado ningún grafo.");
        }
    }

    public void calcularRutaMasCorta() {
        if (grafo != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el número de la ciudad origen: ");
            int origen = scanner.nextInt();
            System.out.print("Ingrese el número de la ciudad destino: ");
            int destino = scanner.nextInt();

            if (origen >= 1 && origen <= grafo.getNumVertices() && destino >= 1 && destino <= grafo.getNumVertices()) {
                floydWarshall.CalcularRutas();
                int[][] distancias = floydWarshall.getDistancias();
                String[][] recorridos = floydWarshall.getRecorridos();
                int distanciaMasCorta = distancias[origen - 1][destino - 1];
                System.out.println("La distancia más corta entre las ciudades " + origen + " y " + destino + " es: " + distanciaMasCorta);

                System.out.print("El recorrido más corto es: ");
                int i = origen - 1;
                int j = destino - 1;
                String recorrido = Integer.toString(destino);
                while (recorridos[i][j] != null) {
                    int siguienteCiudad = Integer.parseInt(recorridos[i][j]);
                    recorrido = siguienteCiudad + " -> " + recorrido;
                    j = siguienteCiudad - 1;
                }
                recorrido = origen + " -> " + recorrido;
                System.out.println(recorrido);
            } else {
                System.out.println("Los números de las ciudades ingresadas son inválidos.");
            }
        } else {
            System.out.println("No se ha cargado ningún grafo.");
        }
    }

    public void calcularCentroGrafo() {
        if (grafo != null) {
            floydWarshall.CalcularRutas();
            int centro = floydWarshall.calcularCentroGrafo();
            System.out.println("El centro del grafo es la ciudad: " + centro);
        } else {
            System.out.println("No se ha cargado ningún grafo.");
        }
    }

    public void modificarGrafo() {
        // Implementa la lógica para modificar el grafo según los requerimientos
    }

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        controlador.cargarGrafoDesdeArchivo("logistica.txt");

        // Menú de opciones
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("----- Menú de opciones -----");
            System.out.println("1. Mostrar matriz de adyacencia");
            System.out.println("2. Calcular ruta más corta");
            System.out.println("3. Calcular centro del grafo");
            System.out.println("4. Modificar el grafo");
            System.out.println("5. Salir");
            System.out.print("Ingrese el número de opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    controlador.mostrarMatrizAdyacencia();
                    break;
                case 2:
                    controlador.calcularRutaMasCorta();
                    break;
                case 3:
                    controlador.calcularCentroGrafo();
                    break;
                case 4:
                    controlador.modificarGrafo();
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número válido.");
                    break;
            }

            System.out.println();
        } while (opcion != 5);
    }
}
