package graphalgorithms.algorithms;

import graphalgorithms.graph.Graph;
import graphalgorithms.graph.Vertex;

public abstract class SingleSourceGraphAlgorithm extends GraphAlgorithm{
    protected Vertex source;
    
    public SingleSourceGraphAlgorithm(Graph graph, Vertex source) {
        super(graph);
        this.source = source;
    }
}
