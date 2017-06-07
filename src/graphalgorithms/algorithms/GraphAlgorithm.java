package graphalgorithms.algorithms;

import graphalgorithms.graph.Graph;

public abstract class GraphAlgorithm {
    protected boolean hasRun;
    protected Graph graph;
    
    public GraphAlgorithm(Graph graph) {
        this.graph = graph;
        hasRun = false;
    }
    
    public abstract GraphAlgorithm run();
    
    public boolean hasRun() {
        return hasRun;
    }
}
