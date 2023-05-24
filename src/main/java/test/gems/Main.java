package test.gems;

import java.util.*;

class Node {
    int id;
    List<Edge> incomingEdges;
    int earliestStart;
    int earliestFinish;

    public Node(int id) {
        this.id = id;
        this.incomingEdges = new ArrayList<>();
        this.earliestStart = 0;
    }

    void addEdge(Edge edge) {
        incomingEdges.add(edge);
        earliestFinish = Math.max(earliestFinish, earliestStart + edge.duration);
    }
}

class Edge {
    Node from;
    Node to;
    int duration;

    public Edge(Node from, Node to, int duration) {
        this.from = from;
        this.to = to;
        this.duration = duration;
    }
}

class DAG {
    Map<Integer, Node> nodes;

    public DAG() {
        this.nodes = new HashMap<>();
    }

    public void addNode(int id) {
        nodes.putIfAbsent(id, new Node(id));
    }

    public void addEdge(int from, int to, int duration) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        Edge edge = new Edge(fromNode, toNode, duration);
        fromNode.addEdge(edge);
        toNode.earliestStart = Math.max(toNode.earliestStart, fromNode.earliestStart + duration);
        toNode.earliestFinish = Math.max(toNode.earliestFinish, toNode.earliestStart + duration);
    }

    public void printEarliestStartTimes() {
        for (Node node : nodes.values()) {
            System.out.println("Node " + node.id + ": earliest start = " + node.earliestStart);
        }
    }

    void printConcurrentTasks() {
        for (Node node : nodes.values()) {
            for (Node other : nodes.values()) {
                if (other != node &&
                        other.earliestStart < node.earliestFinish &&
                        other.earliestFinish > node.earliestStart) {
                    System.out.println("Node " + node.id + " and Node " + other.id + " run concurrently.");
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DAG dag = new DAG();
        dag.addNode(0);
        dag.addNode(1);
        dag.addNode(2);
        dag.addNode(3);
        dag.addNode(4);
        dag.addNode(5);
        dag.addEdge(0, 1, 1);
        dag.addEdge(1, 2, 1);
        dag.addEdge(2, 3, 1);
        dag.addEdge(2, 4, 1);
        dag.addEdge(3, 5, 1);
        dag.addEdge(4, 5, 1);
        dag.printEarliestStartTimes();
        dag.printConcurrentTasks();
    }
}