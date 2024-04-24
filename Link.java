package Principal;


public class Link {
    Nodo destino;
    int costo;
    Link siguiente;

    public Link(Nodo destino) {
        this.destino = destino;
        siguiente = null;
    }

    public Link(Nodo destino, int costo) {
        this.destino = destino;
        this.costo = costo;
        siguiente = null;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public Link getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Link siguiente) {
        this.siguiente = siguiente;
    }
    
    
        
    
}