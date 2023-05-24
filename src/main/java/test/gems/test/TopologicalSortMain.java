package test.gems.test;

import java.util.*;

class Graph {
    int vertices;
    LinkedList<Integer>[] adjList;

    Graph(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];

        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    void addEdge(int start, int end) {
        adjList[start].add(end);
    }

    void topologicalSort() {
        boolean visited[] = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        System.out.println("Topological Sort: ");
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.print(stack.pop() + " ");
        }
    }

    void topologicalSortUtil(int start, boolean[] visited, Stack<Integer> stack) {
        visited[start] = true;

        for (int i = 0; i < adjList[start].size(); i++) {
            int vertex = adjList[start].get(i);
            if (!visited[vertex])
                topologicalSortUtil(vertex, visited, stack);
        }

        stack.push(start);
    }
}

public class TopologicalSortMain {
    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
//        graph.addEdge(5, 2);
//        graph.addEdge(5, 0);
//        graph.addEdge(4, 0);
//        graph.addEdge(4, 1);
//        graph.addEdge(2, 3);
//        graph.addEdge(3, 1);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.topologicalSort();
    }
}