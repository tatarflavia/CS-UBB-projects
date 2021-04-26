import model.Graph;

import java.util.Enumeration;

public class Main {

    public static void main(String[] args) {
        int nbOfThreads=8;
        Graph g = new Graph(10,nbOfThreads);
        g.addEdge(0,1);
        g.addEdge(0,3);
        g.addEdge(0,7);
        g.addEdge(1,3);
        g.addEdge(1,6);
        g.addEdge(1,8);
        g.addEdge(2,4);
        g.addEdge(2,6);
        g.addEdge(3,5);
        g.addEdge(4,9);
        g.addEdge(5,7);
        g.addEdge(5,9);
        g.addEdge(6,7);
        g.addEdge(7,9);
        g.addEdge(8,9);
        System.out.println("The graph is:\n"+g.toString());
        System.out.println("The nb of threads is: "+ nbOfThreads);
        long startTime = System.nanoTime();
        System.out.println("Starting coloring the graph...\n");
        g.colorGraph();
        long endTime = System.nanoTime();
        System.out.println("Coloring done...\n");

        long duration = (endTime - startTime);
        System.out.println("The colors are: ("+g.getNumberOfColorsUsed()+" colors used)"+"\n");
        g.printColors();

        System.out.println("Coloring took: " + duration/1000000 + " milliseconds");
    }
}
