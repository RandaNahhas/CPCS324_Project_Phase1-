package CPCS324_Project_Phase1;
//-----------------------------------------------------------------------------------------------//
///////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////               Class : EDGE                       ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

/**
 * This class to create edge's object and store the source and destination and
 * weight of the edge
 *
 * @author Enas, Munera, Randa
 */
public class Edge {

    //----------------------------Attributes Section----------------------------
    //Decleare source variable to store the source vertex of the edge 
    Vertex source;
    //Decleare destiantion variable to store the source vertex of the edge 
    Vertex destination;
    //Decleare parent variable to store the parent vertex of the edge (some algorithm need it)
    Vertex parent;
    //Decleare weight variable to store the weight of the vertex
    int weight;

    //----------------------------Constructors Section----------------------------
    /**
     * Default constructor
     */
    public Edge() {
    }

    /**
     * constructor with specific parameters
     *
     * @param source -- source vertex of the edge
     * @param destination -- destination vertex of the edge
     * @param weight -- weight of the edge
     */
    public Edge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

}
