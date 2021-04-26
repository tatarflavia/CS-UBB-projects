package model;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Graph {
    //not directed graph, edges don't have a sign

    private int numberOfEdges;
    private int numberOfVertices; //number of points on the graph aka nodes

    private final Set<Integer> independentVertexSet;  //only vertexes as ints, no additional info 

    private final Map<Integer, Node> vertexInfoMap; //vertexes have associated info: the color int and the random int

    private Set<Integer> colors; //colors are saved as ints

    private Map<Integer, Set<Integer>> graph; //a graph is a set of pairs where a pair is an edge, it has 2 vertexes

    private ExecutorService threadPool; //thread pool for threads who do the computations

    private static int getRandomIntInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int getNumberOfColorsUsed(){
        int result=-1;
        for (Map.Entry<Integer, Node> entry : vertexInfoMap.entrySet()){
            if(entry.getValue().color>result)
                result=entry.getValue().color;
        }
        return result+1;
    }
    

    public Graph(int nrOfVertices, int nbOfThreads) {
        //constructor for init of the nb of vertexes, the graph, the map for the infos of the vertexes
                    //the colors as ints, the vertexes with no additional info, and the thread pool for the threads
        
        this.numberOfVertices = nrOfVertices;
        graph = new HashMap<>();
        vertexInfoMap = new HashMap<>();
        
        //for every vertex we put its number in the graph
        //then put into the info map this vertex and a non color and a random nb till 100
        for (int i = 0; i < nrOfVertices; i++) {
            graph.put(i, new HashSet<>());
            //initially assigned random numbers
            vertexInfoMap.put(i, new Node(-1, getRandomIntInRange(0, 100)));
        }
        colors = new TreeSet<>();
        independentVertexSet = new HashSet<>(graph.keySet());
        
        //add 100 colors
        for (int i = 0; i < 100; i++) {
            colors.add(i);
        }
        //start a thread pool with a nb of fixed threads to color the graph
        threadPool = Executors.newFixedThreadPool(nbOfThreads);
    }

    

    public void addEdge(int startingVertex, int finishVertex) {
        //function which adds an indirect edge between the 2 vertexes, both ways
        numberOfEdges++;
        graph.get(startingVertex).add(finishVertex);
        graph.get(finishVertex).add(startingVertex);
    }

    public List<Integer> getNeighbours(int vertex) {
        //for the given vertex we get all its neighbours which are not yet colored
        return graph.get(vertex).stream().filter((node) -> {
            return getColor(node) == -1;
        }).collect(Collectors.toList());
    }

    //Jones Plassman ALGORITHM for graph coloring
    public void colorGraph() {
        //smallest possible nb of colors is the chromatic nb for a graph
        //find a way of coloring the vertexes such that
        //no 2 vertices of an edge should be of the same color
        //𝑈 ← 𝑉
        // while 𝑈 > 0 do
        // for all vertices 𝑣 ∈ 𝑈 do in parallel
        // 𝐼 ← 𝑣 𝑤 𝑣 > 𝑤 𝑢 ∀ 𝑢 ∈ 𝑁 𝑣 }
        // for all vertices 𝑣
        //′ ∈ 𝐼 do in parallel
        // 𝑆 ← colors of 𝑁 𝑣′
        // 𝑐 𝑣
        //′ ← minimum color ∉ 𝑆
        // 𝑈 ← 𝑈 ∖ I
        //Optimal Coloring  color using smallest color

        //Jones-Plassmann Coloring
        //Find independent set
        // Not assigned the same color
        // Color individually using smallest available color

        //we only put in the independent set at the end with no conflicts so we continue as long as we find conflicts and clear the set
        while (!independentVertexSet.isEmpty()) {
            //we get the set of correctly colored vertexes
            Set<Integer> setOfGoodColored = getIndependentSet();
            for (Integer vertex : setOfGoodColored) {
                //for every vertex we try to color it by comparing to its neighbours and then assigning the smallest possible color
                //we use the thread pool for coloring every vertex
                        //which means that a number of threads will take the job, devide it and do it, every thread with its job

                //If local maxima, assign lowest color
                threadPool.submit(() -> setSmallestPossibleColor(vertex));
            }
            //we remove from the big param the local made set and if not empty that means that not all the vertexes are colored yet
            independentVertexSet.removeAll(setOfGoodColored);
        }
        //all vertexes all colored therefore we close the threadPool
        threadPool.shutdown();
    }

    public void printColors() {
        //prints all colors from the graph
        for (Integer vertex : vertexInfoMap.keySet())
            System.out.println("Vertex nb: " + vertex + " colored with:" + getColor(vertex)
                    //+ " random int:" + getValue(vertex)
                    );
    }

    public Integer getValue(int vertex) {
        //for the given vertex we get the random int from its infos
        return vertexInfoMap.get(vertex).randomInt;
    }

    public Integer getColor(int vertex) {
        //for the given vertex we return its color, none or a good color
        return vertexInfoMap.get(vertex).color;
    }

    private boolean checkVertex(int vertex) {
        //Not necessary to create a new random permutation of vertices
        //every time
        // Use vertex number to resolve conflicts
        //function which checks if the given vertex is ok till now, checks for conflicts

        //goes through all neighbours which are not yet colored
        //all values from the neighbours must be smaller, only in this case return true
        for (Integer neighbour : getNeighbours(vertex)) {
            if (getValue(neighbour) > getValue(vertex))
                return false;
            else if (getValue(neighbour).equals(getValue(vertex)) && vertex > neighbour)
                return false;
        }
        return true;
    }

    public Set<Integer> getNeighColors(int vertex) {
        //get the colors of the neighbours that are colored
        return getAllNeighs(vertex).stream().filter((node) -> {
            return getColor(node) != -1;
        }).map(node -> getColor(node)).collect(Collectors.toSet());
    }

    public Set<Integer> getAllNeighs(int vertex) {
        //gets all neighbours of the given vertex, colored or not
        return graph.get(vertex);
    }

    public Integer getSmallestColor(int vertex) {
        //for the given vertex we return the smallest possible color to color it

        //first get the colors from the neighbours and then find the smallest color which is not in this list
        Set<Integer> neighboursColors = getNeighColors(vertex);
        for (Integer color : colors) {
            if (!neighboursColors.contains(color))
                return color;
        }
        return 0;
    }

    public void setSmallestPossibleColor(int vertex) {
        //set the color by giving the given vertex the smallest possible color to be colored in
        vertexInfoMap.get(vertex).color = getSmallestColor(vertex);
    }

    public Set<Integer> getIndependentSet() {
        //function which gets all the vertexes with no conflicts in the future
        //no two vertices share a common edge
        //Any independent set can be colored in parallel
        //How to choose independent sets in parallel?
        // Assign a random weight to each vertex

        //𝑈 ← 𝑉
        // while 𝑈 > 0 do in parallel
        // Choose an independent set 𝐼 from 𝑈
        // Color all vertices in 𝐼
        // 𝑈 ← 𝑈 ∖ 𝐼

        ArrayList<Future<Boolean>> list = new ArrayList<>();
        Set<Integer> result = new HashSet<>();
        List<Integer> independentVertexesList = new ArrayList<>(independentVertexSet);
        //for every vertex we submit to the threadPool a boolean that will tell us if a vertex is with no conflicts
        for (Integer vertex : independentVertexesList) {
            //we use the thread pool for the check of every vertex
            //which means that a number of threads will take the job, devide it and do it, every thread with its job

            //repeat, considering only uncolored vertices
            Future<Boolean> booleanFuture = threadPool.submit(() -> checkVertex(vertex));
            list.add(booleanFuture);
        }

        for (int i = 0; i < independentVertexSet.size(); i++) {
            try {
                //for every vertex we check that boolean from the future
                if (list.get(i).get())
                    //if true we add it to the result
                    result.add(independentVertexesList.get(i));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder= new StringBuilder(
                "numberOfEdges=" + numberOfEdges +
                ", numberOfVertices=" + numberOfVertices+"\n"
               );
        for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()){
            for(int neigh:entry.getValue()){
                if(neigh>entry.getKey()){
                    stringBuilder.append(entry.getKey()).append("<->");
                    stringBuilder.append(neigh).append("\n");
                }

            }
        }
        return stringBuilder.toString();
    }
}
