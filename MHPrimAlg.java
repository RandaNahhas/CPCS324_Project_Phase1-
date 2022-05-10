package CPCS324_Project_Phase1;
//-----------------------------------------------------------------------------------------------//
/////////////////////////               Class : MINE-HEAP-ALG              ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

//imports : 
import java.util.LinkedList;

/**
 * Prim's algorithm is a minimum spanning tree algorithm that takes a graph as
 * input and finds the subset of the edges of that graph which form a tree that
 * includes every vertex and has the minimum sum of weights among all the trees
 * that can be formed from the graph USING MIN HEAP DATA STRUCTURE
 *
 * @author Enas, Munera, Randa
 */
public class MHPrimAlg extends MSTAlgorithm {

    //----------------------------Methods Section----------------------------
    /**
     * This method to apply the prim algorithm using min heap and find the MST
     *
     * @param graph is an object of Graph class
     * @return cost of the minimum spanning tree using prim algorithm
     * implemented using min-heap
     */
    public int MHPrimAlg(Graph graph) {
        /*boolean array with length ove verticesNo 
        if vertex in heap--> the index (which is equal to vertex label) will be true 
        if vertex not in heap--> the index (which is equal to vertex label)  will be false*/
        boolean[] inHeap = new boolean[graph.verticesNo];
        //create array heapNode for all the vertices
        HeapNode[] heapNodes = new HeapNode[graph.verticesNo];
        //Declaration of the superclass attribute 
        MSTResultList = new Edge[graph.verticesNo];
        //for loop to for each vertex to create heapNode for every vertex with key=infinity
        //and inHeap [vertex label] will be true-->means the vertex in heap
        //also fo every vertex add edge with parent null 
        for (int i = 0; i < graph.verticesNo; i++) {
            //create heapNodes for the vertex and key=infinity
            heapNodes[i] = new HeapNode(graph.vertices[i], Integer.MAX_VALUE);
            //create edge and add it to the MSTResultList  
            MSTResultList[i] = new Edge();
            inHeap[i] = true;
        }

        //decrease the key for the first heapNode(first vertex)
        heapNodes[0].key = 0;
        //add all the vertices to the MinHeap
        minHeap minHeap = new minHeap(graph.verticesNo);

        //add all heapNodes (all the vertices) to priority queue
        for (int i = 0; i < graph.verticesNo; i++) {
            minHeap.insert(heapNodes[i]);
        }

        //while minHeap is not empty
        while (!minHeap.isEmpty()) {
            //extract the min heapNode
            HeapNode extractedMinNode = minHeap.extractMin();
            //extracted vertex
            Vertex extractedVertex = extractedMinNode.vertex;
            //change to inHeap status of the vertex to false
            inHeap[extractedVertex.label] = false;
            //iterate through all the adjacent vertices (all edges)
            LinkedList<Edge> list = extractedVertex.adjList;

            //for loop to add all the adjacent edge of extractedVertex
            for (int i = 0; i < list.size(); i++) {
                //get the edge
                Edge edge = list.get(i);
                //only if edge destination is present in heap
                if (inHeap[edge.destination.label]) {
                    //variable to store destination label of the edge
                    int destination = edge.destination.label;
                    //variable to store weight of the edge 
                    int newKey = edge.weight;
                    //check if the the exisiting destiantion key(which stored in heapNode[destination])>updated key
                    //if yes--> update, if not--> remaining the exisiting one 
                    if (heapNodes[destination].key > newKey) {
                        //decrease the key in the heapNode[destination]
                        decreaseKey(minHeap, newKey, destination);
                        //update the parent node for destination
                        MSTResultList[destination].parent = extractedVertex;
                        //update the weight for destination
                        MSTResultList[destination].weight = newKey;
                        //update key of destination's heap node
                        heapNodes[destination].key = newKey;
                    }
                }
            }
        }
        //return the cost by call displayResultingMST method to calculate the cost
        return displayResultingMST(MSTResultList);
    }

    /**
     * This method decrease key of the heap node and resort the min heap and put
     * the heap node in correct position
     *
     * @param minHeap -- min-heap object to reach to the heapNode to be updated
     * @param newKey -- new key to update the heapNode
     * @param vertex -- label of vertex of the heapNode
     */
    public void decreaseKey(minHeap minHeap, int newKey, int vertex) {
        //get the index which key's needs a decrease;
        int index = minHeap.indexes[vertex];
        //get the node and update its value
        HeapNode node = minHeap.minHeapArray[index];
        node.key = newKey;
        minHeap.bubbleUp(index);
    }

    /**
     * This method will accept the linked list of edge of minimum spanning tree
     * and display the edge and calculate the cost then return it
     *
     * @param edgesList -- which is the array of edges that is included in the
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

//-----------------------------------------------------------------------------------------------//
/////////////////////////               Class : HEAP-NODE                    //////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//
/*This class to create heap node*/
class HeapNode {

    //----------------------------Attributes Section----------------------------
    //Decleare vertex variable to store the vertex of the heap node
    Vertex vertex;
    //Decleare key variable to store the key of heap node
    int key;

    //----------------------------Constructors section----------------------------
    /**
     * default constructor
     */
    public HeapNode() {
    }

    /**
     * constructor with specific value
     *
     * @param vertex --> the vertex that represented by the heapNode
     * @param key --> key the weight so far
     */
    public HeapNode(Vertex vertex, int key) {
        this.vertex = vertex;
        this.key = key;
    }

}

//-----------------------------------------------------------------------------------------------//
/////////////////////////               Class : MIN-HEAP                   ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//
class minHeap {

    //the max value of the heap size
    int size;

    // heap current size
    int currentSize;

    //array contains the values of the min heap
    HeapNode[] minHeapArray;

    //array that will be used to decrease the key
    int[] indexes;

    /**
     * Constructor with specific size value of the heap
     *
     * @param size = the max value
     */
    public minHeap(int size) {
        this.size = size;
        minHeapArray = new HeapNode[size + 1];
        indexes = new int[size];
        minHeapArray[0] = new HeapNode(new Vertex(), Integer.MIN_VALUE);
        minHeapArray[0].vertex.label = -1;
        currentSize = 0;
    }

    /**
     * This method will prints min heap values
     */
    public void display() {
        for (int i = 0; i <= currentSize; i++) {
            System.out.println(" " + minHeapArray[i].vertex + "   key   " + minHeapArray[i].key);
        }
        System.out.println("________________________");
    }

    /**
     * This method to add nodes to the min heap
     *
     * @param node
     */
    public void insert(HeapNode node) {
        currentSize++;
        int Index = currentSize;
        minHeapArray[Index] = node;
        indexes[node.vertex.label] = Index;
        bubbleUp(Index);
    }

    /**
     * This method will moves the last value added to the correct position in
     * the heap
     *
     * @param Number = current index
     */
    public void bubbleUp(int num) {
        int parentIndex = num / 2;
        int currentIndex = num;
        while (currentIndex > 0 && minHeapArray[parentIndex].key > minHeapArray[currentIndex].key) {
            HeapNode currentNode = minHeapArray[currentIndex];
            HeapNode parentNode = minHeapArray[parentIndex];

            //swap the positions
            indexes[currentNode.vertex.label] = parentIndex;
            indexes[parentNode.vertex.label] = currentIndex;
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
    }

    /**
     * Update the indexes[] array and move the last node to the top and remove
     * it
     *
     * @return min heapNode
     */
    public HeapNode extractMin() {
        HeapNode min = minHeapArray[1];
        HeapNode lastNode = minHeapArray[currentSize];
        indexes[lastNode.vertex.label] = 1;
        minHeapArray[1] = lastNode;
        minHeapArray[currentSize] = null;
        sinkDown(1);
        currentSize--;
        return min;
    }

    /**
     * This method to compare the values in the min heap to make sure all are in
     * correct position
     *
     * @param k
     */
    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIndex = 2 * k;
        int rightChildIndex = 2 * k + 1;
        if (leftChildIndex < heapSize() && minHeapArray[smallest].key > minHeapArray[leftChildIndex].key) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < heapSize() && minHeapArray[smallest].key > minHeapArray[rightChildIndex].key) {
            smallest = rightChildIndex;
        }
        if (smallest != k) {

            HeapNode smallestNode = minHeapArray[smallest];
            HeapNode kNode = minHeapArray[k];

            //swap the positions
            indexes[smallestNode.vertex.label] = k;
            indexes[kNode.vertex.label] = smallest;
            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    /**
     * swap to elements places
     *
     * @param num1 = the k value
     * @param num2 = the smallest value
     */
    public void swap(int num1, int num2) {
        HeapNode temp = minHeapArray[num1];
        minHeapArray[num1] = minHeapArray[num2];
        minHeapArray[num2] = temp;
    }

    /**
     * This method will check if the currentSize equal to zero then it means
     * that the min heap is empty
     *
     * @return either true or false
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * @return heap Current size
     */
    public int heapSize() {
        return currentSize;
    }
}
