//Reynoso Garcia Jesus Salvador 22310400
package Principal;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.JOptionPane;

public class Grafo {

    Graphics g;

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

    public Nodo getNode(String nombre) {
        Nodo current = primero;
        boolean found = false;
        if (primero == null) {
            JOptionPane.showMessageDialog(null, "El grafo esta vacio");
            return null;
        } else {
            while (found == false && current != null) {
                if (current.getDato().equals(nombre)) {
                    found = true;
                } else {
                    current = current.getSiguiente();
                }
            }
            if (found == false) {
                JOptionPane.showMessageDialog(null, "El nodo no existe");
                current = null;
            }
            return current;
        }

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

            System.out.println("Origen: " + origen.getDato() + "\tCosto: " + costo + "\tDestino: " + destino.getDato());
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
        System.out.println("\nNODOS Y SUS PESOS, CALCULADOS DESDE: " + source.getDato());
        while (temp != null) {
            String route = "";
            System.out.println("Nodo: " + temp.getDato() + " costo: " + temp.getCosto());
            for (Nodo node : temp.getShortestPath()) {
                route += node.getDato() + " -> ";
            }
            System.out.println("Ruta mas corta: " + route + temp.getDato() + "\n");
            temp = temp.getSiguiente();
        }
        return grafo;
    }

    public void paintNode(int mX, int mY, String nombre) {
        g.drawOval(mX, mY, 50, 50);
        g.drawString(nombre, mX + 30, mY);
    }

    public Graphics dijkstra(Grafo grafo, Nodo source, Graphics graf) {
        g = graf;
        g.setColor(Color.red);
        g.drawOval(source.getCoord().x - 25, source.getCoord().y - 25, 50, 50);
        g.drawString("Origen", source.getCoord().x + 30, source.getCoord().y);
        g.setColor(Color.BLUE);
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
        System.out.println("\nNODOS Y SUS PESOS, CALCULADOS DESDE: " + source.getDato());
        while (temp != null) {

            String route = "";
            System.out.println("Nodo: " + temp.getDato() + " costo: " + temp.getCosto());
            for (Nodo node : temp.getShortestPath()) {
                route += node.getDato() + " -> ";
            }
            System.out.println("Ruta mas corta: " + route + temp.getDato() + "\n");
            g.drawString(Integer.toString(temp.getCosto()), temp.getCoord().x + 30, temp.getCoord().y);
            g.drawString("Ruta mas corta: " + route + temp.getDato(), temp.getCoord().x + 30, temp.getCoord().y + 30);
            temp = temp.getSiguiente();
        }
        return g;
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
