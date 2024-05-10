package Principal;


public class run {
    static Grafo grafo = new Grafo();
       
    
    public static void main(String[] args) {
        Nodo nodoA =  new Nodo("A");
        Nodo nodoB = new Nodo("B");
        Nodo nodoC = new Nodo("C");
        Nodo nodoD = new Nodo("D");
        Nodo nodoE = new Nodo("E");
        Nodo nodoF = new Nodo("F");
        Nodo nodoG = new Nodo("G");
        Nodo nodoH = new Nodo("H");
        
        grafo.addNode(nodoA);
        grafo.addNode(nodoB);
        grafo.addNode(nodoC);
        grafo.addNode(nodoD);
        grafo.addNode(nodoE);
        grafo.addNode(nodoF);
        grafo.addNode(nodoG);
        grafo.addNode(nodoH);
       
        System.out.println("Enlaces entre los nodos: \n");
        
        grafo.newLink(nodoA, nodoD);
        grafo.newLink(nodoA, nodoB);
        grafo.newLink(nodoA, nodoE);
        grafo.newLink(nodoA, nodoC);
        
        grafo.newLink(nodoB, nodoE);
        grafo.newLink(nodoB, nodoG);
        grafo.newLink(nodoB, nodoD);
        
        grafo.newLink(nodoC, nodoE);
        grafo.newLink(nodoC, nodoH);
        
        grafo.newLink(nodoD, nodoG);
        grafo.newLink(nodoD, nodoH);
        
        grafo.newLink(nodoE, nodoF);
        
        grafo.newLink(nodoF, nodoG);
        
        System.out.println("\nMatriz de adyacencia: \n");
        System.out.println(grafo.mostrar());
        
        grafo = grafo.dijkstra(grafo, nodoA);
        
    }
    
}
