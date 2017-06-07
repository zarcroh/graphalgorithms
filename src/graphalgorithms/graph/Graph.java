package graphalgorithms.graph;

import graphalgorithms.algorithms.GraphAlgorithm;
import java.util.*;

public class Graph implements Iterable<Vertex> {
    Set<Vertex> vertexSet;
    Map<GraphAlgorithm, Collection<String>> vertexVariables;
    Map<GraphAlgorithm, Collection<String>> edgeVariables;
    
    public Graph() {
        vertexSet = new HashSet<>();
        vertexVariables = new HashMap<>();
        edgeVariables = new HashMap<>();
    }
    
    public void addVertexVariables(GraphAlgorithm algorithm, Collection<String> variableNames) {
        vertexVariables.put(algorithm, variableNames);
        
        for (Vertex v : vertexSet) {
            v.addVariables(algorithm, variableNames);
        }
    }
    
    public void addEdgeVariables(GraphAlgorithm algorithm, Collection<String> variableNames) {
        edgeVariables.put(algorithm, variableNames);
        
        Iterator<Edge> it = edgeIterator();
        
        while (it.hasNext()) {
            it.next().addVariables(algorithm, variableNames);
        }
    }
    
    public Vertex addVertex() {
        Vertex newVertex = new Vertex(vertexVariables);
        vertexSet.add(newVertex);
        return newVertex;
    }
    
    public void addEdge(Vertex from, Vertex to, int weight) {
        if (!vertexSet.contains(from) || !vertexSet.contains(to)) {
            throw new IllegalArgumentException("Cannot add edge between vertices not in graph");
        }
        
        Edge newEdge = new Edge(from, to, weight, edgeVariables);
        from.addEdge(newEdge);
    }
    
    public void addEdge(Vertex from, Vertex to) {
        addEdge(from, to, 1);
    }
    
    public boolean contains(Vertex v) {
        return vertexSet.contains(v);
    }
    
    public boolean contains(Edge e) {
        Iterator<Edge> it = edgeIterator();
        
        while (it.hasNext()) {
            if (it.next() == e) {
                return true;
            }
        }
        
        return false;
    }
    
    public Iterator<Vertex> vertexIterator() {
        return vertexSet.iterator();
    }
    
    public Iterator<Edge> edgeIterator() {
        return new EdgeIterator();
    }
    
    @Override
    public Iterator<Vertex> iterator() {
        return vertexIterator();
    }
    
    private class EdgeIterator implements Iterator<Edge> {
        private Iterator<Vertex> vertexIterator;
        private Iterator<Edge> incidenceIterator;
        
        public EdgeIterator() {
            vertexIterator = vertexIterator();
            incidenceIterator = vertexIterator.next().incidenceIterator();
        }
        
        public boolean hasNext() {
            if (incidenceIterator.hasNext()) {
                return true;
            }
            else if (vertexIterator.hasNext()) {
                incidenceIterator = vertexIterator.next().incidenceIterator();
                return this.hasNext();
            }
            else {
                return false;
            }
        }
        
        public Edge next() {
            if (incidenceIterator.hasNext()) {
                return incidenceIterator.next();
            }
            else if (vertexIterator.hasNext()) {
                incidenceIterator = vertexIterator.next().incidenceIterator();
                return this.next();
            }
            else {
                throw new NoSuchElementException();
            }
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
