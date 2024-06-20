//Reynoso Garcia Jesus Salvador 22310400    
package Principal;

import java.util.ArrayList;

public class ListaAdyacencia {

    Link primero, ultimo;

    public Link getPrimero() {
        return primero;
    }

    public void setPrimero(Link primero) {
        this.primero = primero;
    }

    public Link getUltimo() {
        return ultimo;
    }

    public void setUltimo(Link ultimo) {
        this.ultimo = ultimo;
    }

    public ListaAdyacencia() {
        primero = null;
        ultimo = null;
    }

    public boolean isEmpty() {
        return primero == null;
    }

    public void nuevaAdyacencia(Nodo destino, int costo) {
        if (!isAdjacent(destino)) {
            Link nodo = new Link(destino, costo);
            insert(nodo, destino);
        }
    }

    public void insert(Link node, Nodo destino) {
        if (isEmpty()) {
            primero = node;
            ultimo = node;
        } else {
            if (destino.getDato().compareTo(primero.destino.getDato()) <= 0) {
                node.setSiguiente(primero);
                primero = node;
            } else {
                if (destino.getDato().compareTo(primero.destino.getDato()) >= 0) {
                    ultimo.setSiguiente(node);
                    ultimo = node;
                } else {
                    Link position = primero;
                    while (destino.getDato().compareTo(primero.destino.getDato()) != 0) {
                        position = position.getSiguiente();
                    }
                    node.setSiguiente(position.getSiguiente());
                    position.setSiguiente(node);
                }
            }
        }
    }

    public boolean isAdjacent(Nodo node) {
        Link actual;
        boolean found = false;
        actual = primero;
        while (actual != null && !node.getDato().equals(actual.destino.getDato())) {
            actual = actual.getSiguiente();
        }
        if (actual != null) {
            found = true;
        }
        return found;
    }

    public String mostrar(Nodo first) {
        String valores = "";
        
        Link temp = primero;
        Nodo nodo = first;
        while (temp != null) {
            while (nodo != null) {
                if (isAdjacent(nodo)) {
                    valores += 1 + " ";
                } else {
                    valores += 0 + " ";
                }
                nodo = nodo.siguiente;
            }
            temp = temp.siguiente;
        }
        return valores;
    }
    
    public ArrayList getLinks(){
        ArrayList<Link> links = new ArrayList<Link>();
        Link temp = primero;
        while (temp != null){
            links.add(temp);
            temp = temp.getSiguiente();
        }
        return links;
    }    
    
}