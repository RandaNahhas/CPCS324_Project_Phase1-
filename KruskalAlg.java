package CPCS324_Project_Phase1;
//-----------------------------------------------------------------------------------------------//
/////////////////////////               Class : KRUSKAL-ALG                ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

//imports : 
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Kruskal's algorithm is a minimum spanning tree algorithm that takes a graph
 * as input and finds the subset of the edges of that graph which form a tree
 * that includes every vertex has the minimum sum of weights among all the trees
 * that can be formed from the graph
 *
 * @author Enas, Munera, Randa
 */
public class KruskalAlg extends MSTAlgorithm {

    //----------------------------Methods Section----------------------------
    /**
     * This method to apply the kruskal algorithm and find the MST
     *
     * @param graph is an object of Graph class
     * @return cost of the minimum spanning tree using kruskal algorithm
     */
    public int KruskalAlg(Graph graph) {
        //Priorty queue to insert edge and sorted it by weight 
        PriorityQueue<Edge> priorityQueueVar = new PriorityQueue<>(graph.edgeNo, Comparator.comparingInt(o -> o.weight));

        //add all the edges to priority queue, sort the edges on weights
        for (int i = 0; i < graph.verticesNo; i++) {
            for (int j = 0; j < graph.vertices[i].adjList.size(); j++) {
                priorityQueueVar.add(graph.vertices[i].adjList.get(j));
            }
        }

        //create array of edge of length equal to verticesNo 
        //this array will make new edge object to each vertex and store the parent of the vertex in it
        Edge[] parent = new Edge[graph.verticesNo];
        //makeset --> intially every vertex will be in its own set (the parent of itself)
        makeSet(parent, graph.verticesNo);

        //Declaration of the superclass attribute 
        MSTResultList = new Edge[graph.verticesNo - 1];
        //intialize counter to count number of edges 
        int edgeCounter = 0;
        //while loop to process vertices - 1 edges
        while (edgeCounter < graph.verticesNo - 1 && !priorityQueueVar.isEmpty()) {
            //remove the front from the proirty queue (least weight)
            Edge edge = priorityQueueVar.remove();
            //check if adding this edge creates a cycle find the parent list of the source and destination of the edge
            int src_set = find_parent(parent, edge.source.label);
            int dst_set = find_parent(parent, edge.destination.label);
            //if the parent set of the source and destination is equal that means they are in the same set
            if (src_set == dst_set) {
                //ignore, will create cycle
            } else {
                //add it to our final result set
                MSTResultList[edgeCounter] = edge;
                //increment the index of the number of edges 
                edgeCounter++;
                //union the two set
                union(parent, src_set, dst_set);
            }
        }
        //return the cost by call displayResultingMST method to calculate the cost
        return displayResultingMST(MSTResultList);
    }

    /**
     * This recursive method accept the parent array and x and will find the
     * parent of x
     *
     * @param parentArray -- array of edges contains the parent of all the
     * vertices
     * @param v the label of vertex to search about its parent
     * @return the parent label (since the parent is vertex)
     */
    public int find_parent(Edge parentArray[], int v) {
        //if the keysOfSets[x] =  x then return x 
        if (parentArray[v].parent.label == v) {
            return v;
        }
        //if not continue searching 
        return find_parent(parentArray, parentArray[v].parent.label);
    }

    /**/
    /**
     * This method accept the parentArray and x and y and will join the set of x
     * to be in y set
     *
     * @param parentArray -- array of edges contains the parent of all the
     * vertices
     * @param x -- source's label of edge
     * @param y -- destination's label of edge
     */
    public void union(Edge[] parentArray, int x, int y) {
        //find the key of set of x 
        int x_set_key = find_parent(parentArray, x);
        //find the key of set of y 
        int y_set_key = find_parent(parentArray, y);
        //make y as parent of x
        parentArray[x_set_key].parent.label = y_set_key;
    }

    /**
     * This method accept the parentArray and verticesNo to make set of eVery
     * vertex (intially every parent set will have the vertex label as parent
     * label)
     *
     * @param parentArray array of edges contains the parent of all the vertices
     * @param verticesNo number of vertices of the graph
     */
    public void makeSet(Edge[] parentArray, int verticesNo) {
        for (int i = 0; i < verticesNo; i++) {
            parentArray[i] = new Edge();
            parentArray[i].parent = new Vertex(i);
        }
    }

    /**
     * This method will accept the linked list of edge of minimum spanning tree
     * and display the edge and calculate the cost then return it
     *
     * @param edgesList-- which is the array of edges that is included in the
     * minimum spanning tree
     * @return the cost of minimum spanning tree
     */
    @Override
    public int displayResultingMST(Edge[] edgesList) {
        int cost = 0;
        //for loop to calculate the cost of all edge in minimum spanning tree 
        for (int i = 0; i < edgesList.length; i++) {
            Edge edge = edgesList[i];
            //add weight to the cost
            cost += edge.weight;
        }
        //return cost
        return cost;
    }

}
