package tester;

import graphalgorithms.graph.*;
import graphalgorithms.algorithms.*;
import java.util.*;

public class Tester {
    private static int getIndex(Object[] arr, Object obj) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == obj) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        //create test Graph
        
        Graph graph = new Graph();
        
        Vertex[] vertices = new Vertex[8];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = graph.addVertex();
        }
        
        graph.addEdge(vertices[0], vertices[1], 3);
        graph.addEdge(vertices[0], vertices[2], 2);
        graph.addEdge(vertices[0], vertices[3], 7);
        graph.addEdge(vertices[1], vertices[5], 4);
        graph.addEdge(vertices[1], vertices[7], 6);
        graph.addEdge(vertices[2], vertices[1], 1);
        graph.addEdge(vertices[2], vertices[3], 5);
        graph.addEdge(vertices[2], vertices[5], 5);
        graph.addEdge(vertices[3], vertices[4], 4);
        graph.addEdge(vertices[4], vertices[2], 1);
        graph.addEdge(vertices[4], vertices[5], 9);
        graph.addEdge(vertices[5], vertices[6], 1);
        graph.addEdge(vertices[5], vertices[7], 2);
        graph.addEdge(vertices[6], vertices[4], 3);
        graph.addEdge(vertices[6], vertices[7], 1);
              
        //Find all shortest paths from vertex 0
        
        Dijkstra dijkstra = new Dijkstra(graph, vertices[0]);
        dijkstra.run();
        
        System.out.print("Dijkstra:\n\n");
        System.out.printf("%6s%6s%6s\n", "Vertex", "Cost", "Prev");
        for (int i = 0; i < vertices.length; i++) {
            System.out.printf("%6d%6s%6d\n", i, vertices[i].get(dijkstra, "cost"), getIndex(vertices, vertices[i].get(dijkstra, "prev")));
        }
        
        //Find all tight edges in graph (i.e. those that are in a shortest path from vertex 0)
        
        FindTightEdges tightEdges = new FindTightEdges(dijkstra);
        tightEdges.run();
        
        System.out.print("\nTight Edges:\n\n");
        System.out.printf("%-10s%-6s\n", "Edge", "Tight?");
        Iterator<Edge> eit = graph.edgeIterator();
        Edge temp;
        while (eit.hasNext()) {
            temp = eit.next();
            System.out.printf("%-10s%-6s\n", 
                                String.format("%d-%d->%d", getIndex(vertices, temp.from()), temp.weight(), getIndex(vertices, temp.to())),
                                temp.get(tightEdges, "isTight"));
        }
        
        //Find number of shortest paths from vertex 0 to each vertex
        
        FindNumShortestPaths numPaths = new FindNumShortestPaths(tightEdges);
        numPaths.run();
        
        System.out.print("\nNumber of Shortest Paths:\n\n");
        System.out.printf("%6s%7s\n", "Vertex", "Paths");
        for (int i = 0; i < vertices.length; i++) {
            System.out.printf("%6d%7s\n", i, vertices[i].get(numPaths, "numShortestPaths"));
        }
    }
}