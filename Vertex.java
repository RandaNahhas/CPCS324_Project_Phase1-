package CPCS324_Project_Phase1;
//-----------------------------------------------------------------------------------------------//
/////////////////////////               Class : VERTEX                     ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

//imports : 
import java.util.LinkedList;

/**
 * This class to create vertex's object with its label and adjacent vertices to
 *it
 *
 * @author Enas, Munera, Randa
 */
public class Vertex {

    //----------------------------Attributes Section----------------------------
    //Decleare label variable to store the label of vertex 
    int label;
    //Decleare boolean variable to know if the vertex visited or not (intillay all vertices not visited)
    boolean isVisited = false;
    //Decleare the Linked list variable adjList to store the adjacent vertices to this vertex(have edge)
    LinkedList<Edge> adjList;

    //----------------------------Constructors Section----------------------------
    /**
     * Default constructor of Vertex class
     */
    public Vertex() {
    }

    /**
     * Constructor with specific label value of the vertex
     *
     * @param label must be integer
     */
    public Vertex(int label) {
        this.label = label;
        adjList = new LinkedList<>();
    }

}
