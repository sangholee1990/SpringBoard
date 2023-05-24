package test.gems.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

class DAG {
    private int vertices;
    private ArrayList<Integer>[] adjList;

    @SuppressWarnings("unchecked")
    public DAG(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList[vertices];
        for(int i = 0; i < vertices; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        Integer i;
        Iterator<Integer> it = adjList[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }
        stack.push(v);
    }
}

public class DagMain {
    public static void main(String[] args) {
        DAG graph = new DAG(6);

        graph.addEdge(5, 2);
        graph.addEdge(5, 0);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        System.out.println("Topological sort of the given graph is:");
        graph.topologicalSort();
    }
}