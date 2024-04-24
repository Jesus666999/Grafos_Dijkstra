package Principal;


public class run {
    static Grafo grafo = new Grafo();
       
    
    public static void main(String[] args) {
        Nodo nodo1 = new Nodo("A");
        Nodo nodo2 = new Nodo("B");
        Nodo nodo3 = new Nodo("C");
        Nodo nodo4 = new Nodo("D");
        Nodo nodo5 = new Nodo("E");
        
        grafo.addNode(nodo1);
        grafo.addNode(nodo2);
        grafo.addNode(nodo3);
        grafo.addNode(nodo4);
        grafo.addNode(nodo5);
        
        grafo.newLink(nodo1, nodo4);
        grafo.newLink(nodo2, nodo5);
        grafo.newLink(nodo2, nodo1);
        grafo.newLink(nodo3, nodo1);
        grafo.newLink(nodo4, nodo2);
        grafo.newLink(nodo5, nodo3);
        
        System.out.println(grafo.mostrar());
        
        grafo = grafo.dijkstra(grafo, nodo1);
        
    }
    
}
