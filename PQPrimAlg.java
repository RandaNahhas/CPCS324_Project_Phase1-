package CPCS324_Project_Phase1;
//-----------------------------------------------------------------------------------------------//
/////////////////////////               Class : PQ-ALG                     ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

//imports : 
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Prim's algorithm is a minimum spanning tree algorithm that takes a graph as
 * input and finds the subset of the edges of that graph which form a tree that
 * includes every vertex and has the minimum sum of weights among all the trees
 * that can be formed from the graph USING PRIORITY QUEUE DATA STRUCTURE
 *
 * @author Enas, Munera, Randa
 */
public class PQPrimAlg extends MSTAlgorithm {

    //----------------------------Methods Section----------------------------
    /**
     * This method to apply the prim algorithm using min heap and find the MST
     *
     * @param graph is an object of Graph class
     * @return cost of the minimum spanning tree using prim algorithm
     * implemented using priority queue
     */
    public int PQPrimAlg(Graph graph) {
        // initialize a PriorityQueue that will keep track of the possible edges that
        // we can add to the tree we are forming, and will allow us to select the 
        // edge of least cost every step of the way
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(graph.verticesNo, Comparator.comparingInt(o -> o.weight));
        // mark the initial vertex as visited
        graph.vertices[0].isVisited = true;

        // for every edge connected to the source, add it to the PriorityQueue
        for (Edge edge : graph.vertices[0].adjList) {
            priorityQueue.add(edge);
        }

        //counter to count the edges
        int edgeCounter = 0;
        //Declaration of the superclass attribute 
        MSTResultList = new Edge[graph.verticesNo - 1];
        // keep adding edges until the PriorityQueue is empty
        while (edgeCounter < graph.verticesNo - 1 && !priorityQueue.isEmpty()) {
            //remove the front from the priority queue(least weight)
            Edge e = priorityQueue.remove();

            // if we have already visited the opposite vertex, go to the next edge
            if (graph.vertices[e.destination.label].isVisited) {
                continue;
            }
            // mark the opposite vertex as visited
            graph.vertices[e.destination.label].isVisited = true;
            //add the edge to the final set 
            MSTResultList[edgeCounter] = e;
            //increment the index of the number of edges 
            edgeCounter++;
            // for every edge connected to the opposite vertex, add it to the PriorityQueue
            for (Edge neighbor : graph.vertices[e.destination.label].adjList) {
                priorityQueue.add(neighbor);
            }
        }
        //return the cost by call displayResultingMST method to calculate the cost
        int cost = displayResultingMST(MSTResultList);
        return cost;
    }

    /**
     * This method will accept the linked list of edge of minimum spanning tree
     * and display the edge and calculate the cost then return it
     *
     * @param edgeList -- which is the array of edges that is included in the
     * minimum spanning tree
     * @return the cost of minimum spanning tree
     */
    @Override
    public int displayResultingMST(Edge[] edgeList) {
        int cost = 0;
        //for loop to calculate the cost of all edge in minimum spanning tree 
        for (int i = 0; i < edgeList.length; i++) {
            Edge edge = edgeList[i];
            //add weight to the cost
            cost += edge.weight;
        }

        //return cost
        return cost;
    }
}
