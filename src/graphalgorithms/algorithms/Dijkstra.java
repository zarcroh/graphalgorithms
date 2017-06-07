package graphalgorithms.algorithms;

import graphalgorithms.graph.*;
import java.util.*;

public class Dijkstra extends SingleSourceGraphAlgorithm {    
    public Dijkstra(Graph graph, Vertex source) {
        super(graph, source);
        
        String[] varNames = {"prev", "cost"};
        graph.addVertexVariables(this, Arrays.asList(varNames));
    }
    
    Vertex getMin(Set<Vertex> vertexSet) {
        int minCost = Integer.MAX_VALUE;
        Vertex minVertex = null;
        
        for (Vertex v : vertexSet) {
            if ((Integer)v.get(this, "cost") < minCost) {
                minCost = (Integer)v.get(this, "cost");
                minVertex = v;
            }
        }
        
        return minVertex;
    }
    
    public GraphAlgorithm run() {
        if(!graph.contains(source)) {
            throw new IllegalArgumentException("Invalid source for Dijkstra.");
        }
        Set<Vertex> Q = new HashSet<>();
        
        for(Vertex v : graph) {
            v.set(this, "cost", Integer.MAX_VALUE);
            v.set(this, "prev", null);
            Q.add(v);
        }
        
        source.set(this, "cost", 0);
        
        while (!Q.isEmpty()) {
            Vertex u = getMin(Q);
            Q.remove(u);
            
            for (Edge e : u) {
                int alt = (Integer)u.get(this, "cost") + e.weight();
                if (alt < (Integer)e.to().get(this, "cost")) {
                    e.to().set(this, "cost", alt);
                    e.to().set(this, "prev", u);
                }
            }
        }
        
        hasRun = true;
        return this;
    }
}
