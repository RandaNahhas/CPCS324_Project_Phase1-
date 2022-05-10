package CPCS324_Project_Phase1;
//-----------------------------------------------------------------------------------------------//
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////// NAMES : Enas Batarfi,Munera Sadaqah,RandaNahhas  ////////////////////////
/////////////////////////       ID : 1906982,1911020,1906161               ////////////////////////
/////////////////////////               SECTION : BAR                      ////////////////////////
/////////////////////////               Class : MAIN                       ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

//imports : 
import java.util.Scanner;

/**
 * This class It is the starting point of the program and contains the main
 * function
 *
 * @author Enas, Munera, Randa
 */
public class Application {

    /**
     * this function should be responsible for running the make graph function
     * according to the number of vertices n and number of edges m that are
     * specified by the user, and invoking the 3 algorithm and displaying the
     * returned results and the measured running time.
     *
     * @param args from the command line
     */
    public static void main(String[] args) {

        //Scanner to read input from user 
        Scanner input = new Scanner(System.in);
        //Intiliaze n = number of vertexs of the graph
        int n = 0;
        //Intiliaze  m = number of edges of the graph 
        int m = 0;
        //Intiliaze graph = instant form class graph
        Graph graph;
        //Intiliaze user_Choice = user choice form menu  
        int User_Choice;

        //print menu msg to user 
        System.out.println("*******************************************************************************************");
        System.out.println("           Welcome to the program (Difference Between Minimum Spanning Tree Algorithms)             ");
        System.out.println("*******************************************************************************************\n");
        System.out.println("Please choice 1 or 2 to calculate the difference between two algorithms\n ");
        System.out.println("1- Kruskal's Algorithm & Prim's Algorithm (based on Priority Queue)");
        System.out.println("2- Prim's Algorithm (based on Min Heap) & Prim's Algorithm (based on Priority Queue)");
        System.out.print("\nEnter your choice : ");

        //Get user Choice :1-Kruskal's & Prim's Priority Queue  
        //2- Prim's Min Heap & Prim's Priority Queue - Otherwise it's wrong input
        User_Choice = input.nextInt();
        System.out.println(); //New line 

        //If user enter Invalid User_Choice not 1 or 2 
        //loop until user enter correct input
        while (User_Choice != 1 && User_Choice != 2) {
            System.out.println("***Your choice is incorrect****");
            System.out.print("Please try again : ");
            User_Choice = input.nextInt();
            System.out.println();//New line 

        }
        //If user enter 1 or 2 complete the rest of the program 
        //Print msg of available cases to test with n(vetices number) and m(edges number)  
        System.out.println("Please choice one of the cases to test (Where n is number of vertices and m is number of edges)");
        System.out.println("1-  n=1,000 - m=10,000");
        System.out.println("2-  n=1,000 - m=15,000");
        System.out.println("3-  n=1,000 - m=25,000");
        System.out.println("4-  n=5,000 - m=15,000");
        System.out.println("5-  n=5,000 - m=25,000");
        System.out.println("6- n=10,000 - m=15,000");
        System.out.println("7-  n=10,000 - m=25,000");
        System.out.println("8- n=20,000 - m=200,000");
        System.out.println("9- n=20,000 - m=300,000");
        System.out.println("10- n=50,000 - m=1,000,000\n");
        System.out.print("Enter your choice : ");

        //Get user choice : 1 to 10 - Otherwise it's wrong input 
        int Choice_Case = input.nextInt();
        //Create instant form class Kruskal's 
        KruskalAlg ks = new KruskalAlg();
        //Create instant form class Prim's PriorityQueue
        PQPrimAlg pq = new PQPrimAlg();
        //Create instant form class Prim's MinHeap
        MHPrimAlg mh = new MHPrimAlg();
        //Decleare startTime variable for kruskal
        double startTime_kruskal;
        //Decleare finishTime variable for kruskal
        double finishTime_kruskal;
        //Decleare startTime variable for prim priority queue
        double startTime_primPQ;
        //Decleare finishTime variable for prim priority queue
        double finishTime_primPQ;
        //Decleare startTime variable for prim min heap
        double startTime_primMH;
        //Decleare finishTime variable for prim min heap
        double finishTime_primMH;
        //Decleare cost variable for kruskal
        long cost_ks;
        //Decleare cost variable for prim proirity queue
        long cost_pq;
        //Decleare cost variable for prim min heap
        long cost_mh;

        //If user enter Invalid Choice_Case not 1 to 10 
        //loop until user enter correct input 
        while (Choice_Case < 1 || Choice_Case > 10) {
            System.out.println("***Your choice is incorrect**** ");
            System.out.print("Please try again : ");
            Choice_Case = input.nextInt();
            System.out.println(); //New line
        }
        //print note that all result using only undirected graph
        System.out.println("Note: Kruskal and Prim algoritms fails on directed graph. Hence, all the result based on undirected graph\n\n");
        //Switch for all avaliable cases of the test 
        //Choice case base on user choice 
        switch (Choice_Case) {
            case 1: {
                n = 1000;
                m = 10000;
                break;
            }
            case 2: {
                n = 1000;
                m = 15000;
                break;
            }
            case 3: {
                n = 1000;
                m = 25000;
                break;
            }
            case 4: {
                n = 5000;
                m = 15000;
                break;
            }
            case 5: {
                n = 5000;
                m = 25000;
                break;
            }
            case 6: {
                n = 10000;
                m = 15000;
                break;
            }
            case 7: {
                n = 10000;
                m = 25000;
                break;
            }
            case 8: {
                n = 20000;
                m = 200000;
                break;
            }
            case 9: {
                n = 20000;
                m = 300000;
                break;
            }
            case 10: {
                n = 50000;
                m = 1000000;
                break;
            }
        }

        //Pass parmetter for class graph : number of vertexs(n) , edges(m) and isDigraph?true:fales 
        graph = new Graph(n, m, false);
        //Call (makeGraph method) to randomly generate Graph
        graph.makeGraph();

        //If User_Choioce = 1 --> Perform kruskal and prim priority queue
        if (User_Choice == 1) {

            //Start time for Kruskal's Algorithm
            startTime_kruskal = System.nanoTime();
            //Call method KruskalAlg to find minimum-spanning-tree and cost of graph
            // store the returned valuse in cost_mh variable
            cost_ks = ks.KruskalAlg(graph);
            //Finish time of the Kruskal's Algorithm
            finishTime_kruskal = System.nanoTime();

            //Start time for Prim's PriorityQueue Algorithm
            startTime_primPQ = System.nanoTime();
            //Call method PQPrimAlg to find minimum-spanning-tree and cost of graph 
            // store the returned valuse in cost_mh variable
            cost_pq = pq.PQPrimAlg(graph);
            //Finish time of the Prim's PriorityQueue Algorithm
            finishTime_primPQ = System.nanoTime();

            //Print total time of Kruskal's Algorithm & Prim's PriorityQueue Algorithm 
            System.out.println("Total runtime of Kruskal's Algorithm : " + ((finishTime_kruskal - startTime_kruskal) / 1000000) + " ms.");
            System.out.println("Total runtime of Prim's PriorityQueue Algorithm : " + ((finishTime_primPQ - startTime_primPQ) / 1000000) + " ms.");

            //Print minimum spanning tree cost of Kruskal's Algorithm & Prim's PriorityQueue Algorithm 
            System.out.println("\nMinimum Spanning Tree Cost of  Kruskal's Algorithm : " + cost_ks);
            System.out.println("Minimum Spanning Tree Cost of Prim's PriorityQueue Algorithm : " + cost_pq);
        } //If User_Choioce = 2 --> Perform prim min heap and prim priority queue
        else if (User_Choice == 2) {

            //start time
            startTime_primMH = System.nanoTime();
            //call min heap algorithm by passing the graph as parameter and store the returned valuse in cost_mh variable
            cost_mh = mh.MHPrimAlg(graph);
            //finish time of the algorithm
            finishTime_primMH = System.nanoTime();

            //Start time for Prim's PriorityQueue Algorithm
            startTime_primPQ = System.nanoTime();
            //Create instant form class Prim's PriorityQueue
            //Call method PQPrimAlg to find minimum-spanning-tree and cost of graph 
            //store the returned valuse in cost_mh variable
            cost_pq = pq.PQPrimAlg(graph);
            //Finish time of the Prim's PriorityQueue Algorithm
            finishTime_primPQ = System.nanoTime();

            //Print total time of Prim's MinHeap Algorithm & Prim's PriorityQueue Algorithm 
            System.out.println("Total runtime of Prim's MinHeap Algorithm : " + ((finishTime_primMH - startTime_primMH) / 1000000) + " ms.");
            System.out.println("Total runtime of Prim's PriorityQueue Algorithm : " + ((finishTime_primPQ - startTime_primPQ) / 1000000) + " ms.");

            //Print minimum spanning tree cost of Prim's MinHeap Algorithm & Prim's PriorityQueue Algorithm 
            System.out.println("\nMinimum Spanning Tree Cost of Prim's MinHeap Algorithm : " + cost_mh);
            System.out.println("Minimum Spanning Tree Cost of Prim's PriorityQueue Algorithm : " + cost_pq);

        }
    }
}
//END 
