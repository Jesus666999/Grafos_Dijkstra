package Principal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Grafo {

    Nodo primero, ultimo;

    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    public Grafo() {
        primero = null;
        ultimo = null;
    }

    public boolean isEmpty() {
        return primero == null;
    }

    public boolean existeVertice(Nodo node) {
        boolean existe = false;
        if (!isEmpty()) {
            Nodo temp = primero;
            while (temp != null && !existe) {
                if (temp.getDato().equals(node.getDato())) {
                    existe = true;
                }
                temp = temp.getSiguiente();
            }
        }
        return existe;
    }

    public void newLink(Nodo origen, Nodo destino) {
        if (existeVertice(origen) && existeVertice(destino)) {
            Nodo position = primero;
            while (!position.dato.equals(origen.getDato())) {
                position = position.getSiguiente();
            }
            int costo = (int) (Math.random() * (20 - 1) + 1);
            position.lista.nuevaAdyacencia(destino, costo);

            position = primero;
            while (!position.dato.equals(destino.getDato())) {
                position = position.getSiguiente();
            }
            position.lista.nuevaAdyacencia(origen, costo);

            System.out.println("Origen: " + origen.getDato() + " Costo: " + costo + " Destino: " + destino.getDato());
        }
    }

    public void newLink(Nodo origen, Nodo destino, int costo) {
        if (existeVertice(origen) && existeVertice(destino)) {
            Nodo position = primero;
            while (!position.dato.equals(origen.getDato())) {
                position = position.getSiguiente();
            }
            position.lista.nuevaAdyacencia(destino, costo);
        }
    }

    public void addNode(Nodo node) {
        if (!existeVertice(node)) {
            if (isEmpty()) {
                primero = node;
                ultimo = node;
            } else {
                if (node.getDato().compareTo(primero.getDato()) <= 0) {
                    node.setSiguiente(primero);
                    primero = node;
                } else {
                    if (node.getDato().compareTo(primero.getDato()) >= 0) {
                        ultimo.setSiguiente(node);
                        ultimo = node;
                    } else {
                        Nodo temp = primero;
                        while (node.getDato().compareTo(primero.getDato()) != 0) {
                            temp = temp.getSiguiente();
                        }
                        node.setSiguiente(temp.getSiguiente());
                        temp.setSiguiente(node);
                    }
                }
            }
        }
    }

    public String mostrar() {
        String valores = "   ";
        Nodo temp = primero;
        while (temp != null) {
            valores += temp.dato + " ";
            temp = temp.siguiente;
        }
        valores += "\n";
        temp = primero;
        while (temp != null) {
            valores += temp.dato + "->" + temp.lista.mostrar(primero) + "\n";
            temp = temp.siguiente;
        }
        return valores;
    }

    public Grafo dijkstra(Grafo grafo, Nodo source) {
        source.setCosto(0);
        Set<Nodo> visitados = new HashSet<>();
        Set<Nodo> noVisitados = new HashSet<>();

        noVisitados.add(source);
        while (noVisitados.size() != 0) {
            Nodo actual = getLowestDistanceNode(noVisitados);
            noVisitados.remove(actual);
            ArrayList<Link> links = actual.getLista().getLinks();
            for (Link link : links) {
                Nodo adjacentNode = link.getDestino();
                int costoLink = link.getCosto();
                if (!visitados.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, costoLink, actual);
                    noVisitados.add(adjacentNode);
                }
            }
            visitados.add(actual);
        }
        Nodo temp = primero;
        while (temp != null) {
            String route = "";
            System.out.println("Nodo: " + temp.getDato() + " costo: " + temp.getCosto());
            for(Nodo node : temp.getShortestPath()){
                route += node.getDato() + " -> ";
            }
            System.out.println("Ruta mas corta: " + route);
            temp = temp.getSiguiente();
        }
        return grafo;
    }

    public Nodo getLowestDistanceNode(Set<Nodo> noVisitados) {
        Nodo lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;

        for (Nodo node : noVisitados) {
            int distancia = node.getCosto();
            if (distancia < lowestDistance) {
                lowestDistance = distancia;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    public void calculateMinimumDistance(Nodo destino, int costoLink, Nodo source) {
        int costoSource = source.getCosto();
        if (costoSource + costoLink < destino.getCosto()) {
            destino.setCosto(costoSource + costoLink);
            LinkedList<Nodo> shortestPath = new LinkedList<>(source.getShortestPath());
            shortestPath.add(source);
            destino.setShortestPath(shortestPath);
        }
    }
}
