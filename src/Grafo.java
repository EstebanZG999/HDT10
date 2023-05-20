import java.util.*;

public class Grafo {
    private int numVertices;
    private Map<Integer, List<Integer>> adyacencias;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.adyacencias = new HashMap<>();
        for (int i = 1; i <= numVertices; i++) {
            adyacencias.put(i, new ArrayList<>());
        }
    }

    public void agregarArista(int origen, int destino) {
        if (origen <= 0 || origen > numVertices || destino <= 0 || destino > numVertices) {
            throw new IllegalArgumentException("Los vértices están fuera del rango válido");
        }
        adyacencias.get(origen).add(destino);
    }

    public List<Integer> obtenerVecinos(int vertice) {
        if (vertice <= 0 || vertice > numVertices) {
            throw new IllegalArgumentException("El vértice está fuera del rango válido");
        }
        return adyacencias.get(vertice);
    }
}
