package Domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DirectedGraph {
    //for every node we keep note of its neighbours aka edges
    private List<List<Integer>> edgesKeeper;
    //vertexes/nodes are hold into this list
    private List<Integer> vertexes;


    public DirectedGraph(int vertexCount) {
        //in the constructor we only initialise parameters with empty
        this.edgesKeeper=new ArrayList<>();
        this.vertexes=new ArrayList<>();

        //add empty list for the neighbours of every node
        for(int i=0;i<vertexCount;i++){
            this.vertexes.add(i);
            this.edgesKeeper.add(new ArrayList<>());
        }

        //generate random edges

        //get the nodes and shuffle them
        List<Integer> nodes=vertexes;
        Collections.shuffle(nodes);
        //add edges from i to i+1 in shuffled array
        for(int i=1;i<nodes.size();i++){
            this.addEdge(nodes.get(i-1),nodes.get(i));
        }
        //add even more random edges using random
        Random random=new Random();
        for(int i=0;i<vertexCount/2;i++){
            int firstVertex= random.nextInt(vertexCount-1);
            int secondVertex=random.nextInt(vertexCount-1);

            this.addEdge(firstVertex,secondVertex);
        }

    }

    public DirectedGraph(List<List<Integer>> edgesKeeper, List<Integer> vertexes) {
        this.edgesKeeper = edgesKeeper;
        this.vertexes = vertexes;
    }

    //get nodes
    public List<Integer> getVertexes() {
        return vertexes;
    }

    //get how many nodes we have in the graph
    public int getSizeOfGraph(){
        return edgesKeeper.size();
    }

    //add a new edge by adding a new neighbour to firstV pos in edgesKeeper
    public void addEdge(int firstVertex,int secondVertex){
        this.edgesKeeper.get(firstVertex).add(secondVertex);
    }

    public List<Integer> getNeighboursOfGivenVertex(int vertex){
        return this.edgesKeeper.get(vertex);
    }

    @Override
    public String toString() {
        return "DirectedGraph{" +
                "edgesKeeper=" + edgesKeeper +
                ", vertexes=" + vertexes +
                '}';
    }
}
