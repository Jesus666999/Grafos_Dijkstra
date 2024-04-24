package Principal;

import java.util.LinkedList;
import java.util.List;

public class Nodo {

    String dato;
    ListaAdyacencia lista;
    Nodo siguiente;
    int costo;
    List<Nodo> shortestPath = new LinkedList<>();

    public Nodo(String dato) {
        this.dato = dato;
        costo = Integer.MAX_VALUE;
        lista = new ListaAdyacencia();
        siguiente = null;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public ListaAdyacencia getLista() {
        return lista;
    }

    public void setLista(ListaAdyacencia lista) {
        this.lista = lista;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public List<Nodo> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Nodo> shortestPath) {
        this.shortestPath = shortestPath;
    }
    
    
    
}
