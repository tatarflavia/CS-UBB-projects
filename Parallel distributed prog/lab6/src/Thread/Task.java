package Thread;

import Domain.DirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

//this class will handle the search of the Hamiltonian Cycle for the given directed graph
public class Task implements Runnable{
    //the given graph where we search for the cycle
    private DirectedGraph directedGraph;
    //starting node from where we begin searching
    private int startingVertexForSearch;
    //the list where we hold the found till now path of the cycle
    private List<Integer> currentPathOfTheCycle;
    //the mutex we use for locking the app when the thread needs to set the result for this particular graph
    private Lock lock;
    //the list where we hold the result at the end of the search
    private List<Integer> resultOfTheSearch;

    public Task(DirectedGraph directedGraph, int startingVertexForSearch, Lock lock, List<Integer> resultOfTheSearch) {
        this.directedGraph = directedGraph;
        this.startingVertexForSearch = startingVertexForSearch;
        this.lock = lock;
        this.resultOfTheSearch = resultOfTheSearch;
        currentPathOfTheCycle=new ArrayList<>();
    }

    private void setResultAtTheEndOfTheSearch(){
        //first lock the app while we set the result with the found cycle
        this.lock.lock();
        //set the result
        this.resultOfTheSearch.clear();
        System.out.println("The found cycle starting from "+ startingVertexForSearch+" is: "+currentPathOfTheCycle);
        this.resultOfTheSearch.addAll(this.currentPathOfTheCycle);
        //unlock after result is set
        this.lock.unlock();
    }

    @Override
    public void run() {
        //start recursive call of the function that does the searching of the cycle
        System.out.println("Starting the search for a cycle starting from "+startingVertexForSearch);
        visit(startingVertexForSearch);
    }

    private void visit(int vertex){
        //recursive function called for trying to add to the path from the neighbours of the given vertex

        //add the vertex found before to the current path of the cycle
        currentPathOfTheCycle.add(vertex);

        //check for finish of the search aka base case for the recursion
        if(currentPathOfTheCycle.size()==directedGraph.getSizeOfGraph()){
            //stopping case aka base case for the recursion
            if(directedGraph.getNeighboursOfGivenVertex(vertex).contains(startingVertexForSearch)){
                //there is an edge between last vertex of the path and starting vertex => we have a cycle
                setResultAtTheEndOfTheSearch();
            }
            return;
        }

        //if we are not yet visited all nodes, we try to look for next node to put in the path for the cycle
        for(int neighbour:directedGraph.getNeighboursOfGivenVertex(vertex)){
            //check that the node has not yet been visited then call visit for it
            if(!this.currentPathOfTheCycle.contains(neighbour)){
                visit(neighbour);
            }
        }
    }



}
