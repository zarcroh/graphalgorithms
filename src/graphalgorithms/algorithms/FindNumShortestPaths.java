package graphalgorithms.algorithms;

import graphalgorithms.graph.*;
import java.util.*;

public class FindNumShortestPaths extends SingleSourceGraphAlgorithm {
    private Dijkstra dijkstra;
    private FindTightEdges tightEdges;
    
    public FindNumShortestPaths(Dijkstra dijkstra) {
        super(dijkstra.graph, dijkstra.source);
        this.dijkstra = dijkstra;
        this.tightEdges = new FindTightEdges(this.dijkstra);
        
        String[] varNames = {"numShortestPaths"};
        graph.addVertexVariables(this, Arrays.asList(varNames));
    }
    
    public FindNumShortestPaths(FindTightEdges tightEdges) {
        super(tightEdges.graph, tightEdges.source);
        this.dijkstra = tightEdges.getDijkstra();
        this.tightEdges = tightEdges;
    
        String[] varNames = {"numShortestPaths"};
        graph.addVertexVariables(this, Arrays.asList(varNames));
    }
    
    public FindNumShortestPaths(Graph graph, Vertex source) {
        super(graph, source);
        this.dijkstra = new Dijkstra(graph, source);
        this.tightEdges = new FindTightEdges(this.dijkstra);
        
        String[] varNames = {"numShortestPaths"};
        graph.addVertexVariables(this, Arrays.asList(varNames));
    }
    
    public GraphAlgorithm run() {
        if(!tightEdges.hasRun()) {
            tightEdges.run();
        }
        
        for(Vertex v : graph) {
            v.set(this, "numShortestPaths", 0);
        }
        
        Vertex v;
        Set<Vertex> vertexSet = new HashSet<>();
        
        vertexSet.add(source);
        source.set(this, "numShortestPaths", 1);
        
        while (!vertexSet.isEmpty()) {
            v = dijkstra.getMin(vertexSet);
            vertexSet.remove(v);
            
            for (Edge e : v) {
                if ((Boolean)e.get(tightEdges, "isTight") == true) {
                    e.to().set(this, "numShortestPaths", (Integer)e.to().get(this, "numShortestPaths") + (Integer)v.get(this, "numShortestPaths"));
                    if (!vertexSet.contains(e.to())) {
                        vertexSet.add(e.to());
                    }
                }
            }
        }
        
        hasRun = true;
        return this;
    }
    
}
