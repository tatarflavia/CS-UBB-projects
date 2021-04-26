import Domain.DirectedGraph;
import Thread.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    //gets a number of threads and a list of graphs
    //calls the function which does the search for each one
    //each graph will be handled by a number of threads= threadNumber given param
    private static void prepareForStartSearchForGraphs(List<DirectedGraph> directedGraphs, int threadNumber){
        for(int i=0;i<directedGraphs.size();i++){
            prepareSearchForGraph(i,directedGraphs.get(i),threadNumber);
        }
    }

    //function which does the numbering of time between start and finish for search for the given graph
    //we need level to know the number of the graph like an id
    private static void prepareSearchForGraph(int level,DirectedGraph directedGraph, int threadNumber){
        long start=System.nanoTime();
        findHamiltonianCycleForGivenThreadsAndGraph(directedGraph,threadNumber);
        long finish=System.nanoTime();
        long duration=(finish-start)/1000000;
        if(level ==0|| level==10 || level == 30|| level==50){
            System.out.println("\n\nGraph number: "+level+" that has "+directedGraph.getSizeOfGraph()+" nodes finished and it took: "+duration+"\n\n");
        }
    }

    //function which does the search with a number of threads for a given directed graph
    //the search will be handled by a number of threads= threadNumber given param
    private static void findHamiltonianCycleForGivenThreadsAndGraph(DirectedGraph directedGraph,int threadNumber){
        //we use a thread pool that has a number of threadNumber given
        ExecutorService threadPool= Executors.newFixedThreadPool(threadNumber);
        //we need a lock for every task/thread
        Lock lock=new ReentrantLock();
        //we need a list where the result aka the path of the cycle will be held
        List<Integer> resultPathForFoundCycle=new ArrayList<>();
        //we have a for that takes each vertex of the graph to start the search from each vertex to be sure we got the result and tried all possibilities
        //for each starting vertex we have a task
        //the tasks will be handled by the given number of threads in the function parameters
        for(int i=0;i<directedGraph.getSizeOfGraph();i++){
            //we give tasks to each thread from the thread pool
            threadPool.execute(new Task(directedGraph,i,lock,resultPathForFoundCycle));
        }
        //after finished. shutdown the thread pool
        threadPool.shutdown();
        try{
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args)  {
        //list of graphs where we perform the search for cycles
        List<DirectedGraph> graphs = new ArrayList<>();

//        for (int i = 1; i <= 51; i++) {
//            //we add new Graphs that have a number of nodes from 10 to 510
//            graphs.add(new DirectedGraph(i*10));
//        }

        /* Let us create the following graph
           (0)->(1)->(2)
            ^   ^ \   |
            |  /   \  |
            | /     ↓ ↓
           (3)<------(4)    */

        List<Integer> nodes=new ArrayList<>();
        nodes.add(0);
        nodes.add(1);
        nodes.add(2);
        nodes.add(3);
        nodes.add(4);
        List<List<Integer>> edges=new ArrayList<>();
        List<Integer> edges0=new ArrayList<>();
        edges0.add(1);
        List<Integer> edges1=new ArrayList<>();
        edges1.add(2);
        edges1.add(4);
        List<Integer> edges2=new ArrayList<>();
        edges2.add(4);
        List<Integer> edges3=new ArrayList<>();
        edges3.add(0);
        edges3.add(1);
        List<Integer> edges4=new ArrayList<>();
        edges4.add(3);
        edges.add(edges0);
        edges.add(edges1);
        edges.add(edges2);
        edges.add(edges3);
        edges.add(edges4);
        graphs.add(new DirectedGraph(edges,nodes));
        System.out.println("The graph is: "+graphs.get(0));

        System.out.println("Non parallel method starts...");
        prepareForStartSearchForGraphs(graphs, 1);

        System.out.println("Parallel method starts...");
        prepareForStartSearchForGraphs(graphs, 4);

    }
}
