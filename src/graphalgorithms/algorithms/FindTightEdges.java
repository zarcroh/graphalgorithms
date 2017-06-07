package graphalgorithms.algorithms;

import graphalgorithms.graph.*;
import java.util.*;

public class FindTightEdges extends SingleSourceGraphAlgorithm {
    private Dijkstra dijkstra;
    
    public FindTightEdges(Dijkstra dijkstra) {
        super(dijkstra.graph, dijkstra.source);
        this.dijkstra = dijkstra;
        
        String[] varNames = {"isTight"};
        graph.addEdgeVariables(this, Arrays.asList(varNames));
    }
    
    public FindTightEdges(Graph graph, Vertex source) {
        super(graph, source);
        this.dijkstra = new Dijkstra(graph, source);
        
        String[] varNames = {"isTight"};
        graph.addEdgeVariables(this, Arrays.asList(varNames));
    }
    
    Dijkstra getDijkstra() {
        return dijkstra;
    }
    
    public GraphAlgorithm run() {        
        if (!dijkstra.hasRun()) {
            dijkstra.run();
        }
        
        Iterator<Edge> it = graph.edgeIterator();
        Edge cur;
        
        while (it.hasNext()) {
            cur = it.next();
            if((Integer)cur.from().get(dijkstra, "cost") + cur.weight() == (Integer)cur.to().get(dijkstra, "cost")) {
                cur.set(this, "isTight", true);
            }
            else {
                cur.set(this, "isTight", false);
            }
        }
        
        hasRun = true;
        return this;
    }
}
