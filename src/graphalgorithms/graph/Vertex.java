package graphalgorithms.graph;

import graphalgorithms.algorithms.GraphAlgorithm;
import java.util.*;

public class Vertex implements Iterable<Edge> {
    List<Edge> incidenceList;
    Map<GraphAlgorithm, Map<String, Object>> variables;
    
    public Vertex(Map<GraphAlgorithm, Collection<String>> variableNames) {
        incidenceList = new ArrayList<>();
        variables = new HashMap<>();
        
        Set<GraphAlgorithm> algorithms = variableNames.keySet();
        for (GraphAlgorithm algorithm : algorithms) {
            addVariables(algorithm, variableNames.get(algorithm));
        }
    }
    
    public void addEdge(Edge edge) {
        incidenceList.add(edge);
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
    
    public Iterator<Edge> incidenceIterator() {
        return incidenceList.iterator();
    }
    
    @Override
    public Iterator<Edge> iterator() {
        return incidenceIterator();
    }
}