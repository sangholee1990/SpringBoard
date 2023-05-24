package test.gems.test;
import java.util.*;

class Algorithm {
    String name;
    List<Algorithm> prerequisites;

    Algorithm(String name) {
        this.name = name;
        this.prerequisites = new ArrayList<>();
    }

    void addPrerequisite(Algorithm prerequisite) {
        prerequisites.add(prerequisite);
    }
}

public class AlgorithmMain {
    public static void main(String[] args) {
        Algorithm a = new Algorithm("A");
        Algorithm b = new Algorithm("B");
        Algorithm c = new Algorithm("C");
        Algorithm d = new Algorithm("D");
        Algorithm e = new Algorithm("E");
        Algorithm f = new Algorithm("F");

        b.addPrerequisite(a); // B depends on A
        c.addPrerequisite(a); // C depends on A
        d.addPrerequisite(b); // D depends on B
        d.addPrerequisite(c); // D also depends on C
        e.addPrerequisite(c); // E depends on C
        f.addPrerequisite(d); // F depends on D
        f.addPrerequisite(e); // F also depends on E

        printAlgorithm(a);
        printAlgorithm(b);
        printAlgorithm(c);
        printAlgorithm(d);
        printAlgorithm(e);
        printAlgorithm(f);
    }

    public static void printAlgorithm(Algorithm algorithm) {
        System.out.println("Algorithm: " + algorithm.name);
        System.out.print("Prerequisites: ");
        for (Algorithm prerequisite : algorithm.prerequisites) {
            System.out.print(prerequisite.name + " ");
        }
        System.out.println("\n");
    }
}
