package CPCS324_Project_Phase1;
//-----------------------------------------------------------------------------------------------//
/////////////////////////               Class : GRAPH                      ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

//imports : 
import java.util.ArrayList;
import java.util.Random;

/**
 * This class to create graph's object take the number vertices number and edge
 * number and make graph accroding to it
 *
 * @author Enas, Munera, Randa
 */
public class Graph {

    //----------------------------Attributes Section----------------------------
    //deleare verticesNo variable to store vertices's number of the graph
    int verticesNo;
    //deleare edgeNo variable to store edge's number of the graph
    int edgeNo;
    //decleare boolean variable true 
    //-->if the graph is directed graph, false -->if the graph is undirected
    boolean isDigraph;
    //decleare variable to store the list of vertices of a graph
    Vertex[] vertices;

    //----------------------------Constructors section----------------------------
    /**
     * Default constructor
     */
    public Graph() {
    }

    //
    /**
     * Constructor with specific parameter
     *
     * @param verticesNo -- number of vertices of graph
     * @param edgeNo -- number of edges between the vertices
     * @param isDigraph -- its directed graph or undirected
     */
    public Graph(int verticesNo, int edgeNo, boolean isDigraph) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph = isDigraph;
        vertices = new Vertex[verticesNo];
    }

    //----------------------------Methods section----------------------------
    /**
     * This method pass the source and destination to create edge and add it to
     * the adjList of the source (and destination if its undirected graph)
     *
     * @param sourceId -- source vertex label
     * @param destinationId -- destination vertex label
     * @param weight -- weight of the edge to be created
     */
    public void addEdge(Vertex sourceId, Vertex destinationId, int weight) {
        //create edge's object
        Edge edge = new Edge(sourceId, destinationId, weight);
        //add the edge to the source's adjList
        sourceId.adjList.add(edge);
        //is undirected graph create another edge with the destinationId as source and add it to the destiantion adjList
        if (!isDigraph) {
            //create another edge's object(the destination will be the source and source will be destination)
            Edge edge2 = new Edge(destinationId, sourceId, weight);
            //add it to the destination adjList
            destinationId.adjList.add(edge2); //for undirected graph
        }
    }

    /**
     * This method to make graph - first will create vertex object and label the
     * verteices with random label - second make the nessecary edge with random
     * weight to ensure its connected - third make the remaining edge with
     * random weight
     */
    public void makeGraph() {
        Random random = new Random();
        // --- STEP 1: Create the graph ---
        //Create arrayList to store the used label (avoid duplicated label)
        //ensuring all vertex has unique label
        ArrayList<Integer> checkList = new ArrayList<>(verticesNo);
        for (int i = 0; i < verticesNo; i++) {
            //generate random integer from 0 to vertices number-1 to label vertices
            int randomLabel = random.nextInt(verticesNo);
            //if checkList not contains the randomLabel
            if (!checkList.contains(randomLabel)) {
                //then create vertex with randomLabel and add it to the array of vertices in randomLabel indes
                //to faster direct access later
                vertices[randomLabel] = new Vertex(randomLabel);
                //add the randomLabel to checkList to avoid choice it as label for another verex again
                checkList.add(randomLabel);
            } //if the checkList contains the randomLabel decrement i by 1 
            //to generate another random label again for the same vertex
            //if not doing this some vertices will not created and will be null 
            else {
                --i;
            }
        }

        // --- STEP 2: create the necessary edges to ensuring the graph is connected ---
        for (int i = 0; i < verticesNo - 1; i++) {
            //generate random integer from 0 to 50(included) to weight the edge 
            int randomWeight = random.nextInt(50) + 1;
            //invoking addEdge to create edge between vertices[i - 1] and vertices[i]
            addEdge(vertices[i], vertices[i + 1], randomWeight);
        }

        //calculate the remainig edges to generate it randomly
        int remaning = edgeNo - (verticesNo - 1);

        // --- STEP 3: Add the remainig edges randomly ---
        for (int i = remaning; i < edgeNo; i++) {
            //sourceID is the vertex that will have an adjacent vertex
            int sourceId = (int) (Math.random() * (verticesNo));
            //destinationID randomly chooses which vertex to add to sourceID as an adjacent
            int destinationId = (int) (Math.random() * (verticesNo));
            /* Avoid self-edges or having an adjacent vertex that already exists
                1- A self-edge happens when the sourceID = destinationID, thus the vertex will point to itself as an adjacent
                2- An adjacent that already exists: when we want to add a new adjacent vertex yet it already exists
            If one of these cases appeared, the iteration should not be counted and should be ignored without
            affecting the number of wanted edges in the graph
            We will avoid those cases by using the following if statement
             */
            if (sourceId == destinationId || isDuplicated(sourceId, destinationId) || isDuplicated(destinationId, sourceId)) {
                --i; // Since this iteration should not be counted, decrease i
                continue; // Skip this iteration
            }
            //initiate the weight value as well
            int randomWeight = (int) (1 + Math.random() * 20);
            //invoking addEdge to create edge between vertices[sourceId] and vertices[destinationId]
            addEdge(vertices[sourceId], vertices[destinationId], randomWeight);
        }
    }

    /* */
    /**
     * This method to check if the edge already exists (to avoid duplicate)
     *
     * @param SourceId -- label of the source of the edge
     * @param DestinationId -- label of the destination of the edge
     * @return true if the edge between this vertices already exists and false
     * if not
     */
    public boolean isDuplicated(int SourceId, int DestinationId) {
        //for loop walk through all the edge in adjList of the source
        for (Edge edge : vertices[SourceId].adjList) {
            //check if the source label of the edge = sourceId "and" destination label of the edge = detinationId
            if ((edge.source.label == SourceId && edge.destination.label == DestinationId)) {
                //return true
                return true;
            }
        }
        //if there is no edge have this sourceId and detinationId return false
        return false;
    }
}
