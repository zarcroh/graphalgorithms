package graphalgorithms.graph;

import graphalgorithms.algorithms.GraphAlgorithm;
import java.util.*;

public class Edge {
    Vertex from, to;
    int weight;
    Map<GraphAlgorithm, Map<String, Object>> variables;
    
    public Edge(Vertex u, Vertex v, int w, Map<GraphAlgorithm, Collection<String>> variableNames) {
        from = u;
        to = v;
        weight = w;
        variables = new HashMap<>();
        
        Set<GraphAlgorithm> algorithms = variableNames.keySet();
        for (GraphAlgorithm algorithm : algorithms) {
            addVariables(algorithm, variableNames.get(algorithm));
        }
    }
    
    public Edge(Vertex u, Vertex v, Map<GraphAlgorithm, Collection<String>> variableNames) {
        this(u, v, 1, variableNames);
    }
    
    void addVariables(GraphAlgorithm algorithm, Collection<String> variableNames) {
        Map<String, Object> algoVars = new HashMap<>(variableNames.size() * 2);
        for (String name : variableNames) {
            algoVars.put(name, null);
        }
        variables.put(algorithm, algoVars);
    }
    
    public void set(GraphAlgorithm algorithm, String variableName, Object val) {
        Map<String, Object> algoVars = variables.get(algorithm);
        algoVars.put(variableName, val);
    }
    
    public Object get(GraphAlgorithm algorithm, String variableName) {
        Map<String, Object> algoVars = variables.get(algorithm);
        return algoVars.get(variableName);
    }
    
    public int weight() {
        return weight;
    }
    
    public Vertex from() {
        return from;
    }
    
    public Vertex to() {
        return to;
    }
}
