package CPCS324_Project_Phase1;
//-----------------------------------------------------------------------------------------------//
/////////////////////////               Class : MST-ALGORITHM              ////////////////////////
//-----------------------------------------------------------------------------------------------//

/**
 * is a superclass representing the general characteristics of an algorithm for
 * computing the minimum spanning tree. It has three subclasses: KruskalAlg,
 * MHPrimAlg and PQPrimAlg.
 *
 * @author Enas, Munera, Randa
 */
public abstract class MSTAlgorithm {

    //----------------------------Attributes Section----------------------------
    //MSTResultList attribute is array of objects of the type Edge.
    //It stores the parent of the vertex and the weight needed by the MST algorithm
    Edge[] MSTResultList;

    //----------------------------Methods Section----------------------------
    /**
     * it is an abstract function that should be implemented by the subclasses
     * MST algorithms -- to calculate the cost of the minimum spanning tree and
     * return it
     *
     * @param edgesList-- which is the array of edges that is included in the
     * minimum spanning tree
     * @return the cost of minimum spanning tree
     */
    public abstract int displayResultingMST(Edge[] edgesList);

}
