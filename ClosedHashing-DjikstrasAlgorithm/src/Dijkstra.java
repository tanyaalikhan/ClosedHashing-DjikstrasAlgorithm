import java.util.LinkedList;
import java.util.Scanner;

public class Dijkstra {
    static int vertices = 10;
    static int mtrx[][] = {
            {0,  53, 10, 12,   0,   0,   0,   0,   0,   0},
            {53,  0, 33,  0,   2,   0, 101,   0,   0,   0},
            {10, 33,  0,  9,  30,  18,   0,   0,   0,   0},
            {12,  0,  9,  0,   0,  17,   0,   0,   6,   0},
            {0,   2, 30,  0,   0,  14, 123, 122,   0,   0},
            {0,   0, 18, 17,  14,   0,   0, 137,   7,   0},
            {0, 101,  0,  0, 123,   0,   0,   8,   0,  71},
            {0,   0,  0,  0, 122, 137,   8,   0, 145,  66},
            {0,   0,  0,  6,   0,   7,   0, 145,   0, 212},
            {0,   0,  0,  0,   0,   0,  71,  66, 212,   0}
    };
    static LinkedList<Integer> journey = new LinkedList<>();

    /***
     * minVert is a helper function that helps
     * the main algorithm where we compute Djikstra's algorithm
     * @param mst
     * @param key
     * @return int vert
     */
    static int minVertex(boolean [] mst, int [] key){
        int min, vert;
        min = Integer.MAX_VALUE; vert = -1;
        for (int i = 0; i < vertices ; i++) {
            if (!mst[i] && min>key[i]){
                min = key[i];
                vert = i;
            }
        }
        return vert;
    }

    /***
     * actualAlgo is the large overarching method that
     * Djikstra's algorithm is computed in
     * @param start
     * @param end
     * @return void
     */
    public static void actualAlgo(int start, int end){
        boolean[] spt = new boolean[vertices];
        int [] distance = new int[vertices];
        int INF = Integer.MAX_VALUE;

        for (int i = 0; i < vertices; i++) {
            distance[i] = INF;
        }

        distance[start] = 0;

        for (int i = 0; i < vertices; i++) {

            int vertex_U = minVertex(spt, distance);

            journey.add(vertex_U);

            spt[vertex_U] = true;

            for (int vertex_V = 0; vertex_V < vertices; vertex_V++) {
                if (mtrx[vertex_U][vertex_V] > 0){

                    if (!spt[vertex_V] && mtrx[vertex_U][vertex_V] != INF){

                        int newKey = mtrx[vertex_U][vertex_V] + distance[vertex_U];
                        if (newKey < distance[vertex_V]) {
                            distance[vertex_V] = newKey;
                        }
                    }
                }
            }
        }
        System.out.println("Starting Vertex: " + start + " to Ending Vertex: " + end +
                " distance: " + distance[end]);
        for (int i : journey){
            if (i == end){
                System.out.println(i);
                break;
            }else
                System.out.println(i);
        }
    }


    /***
     * main method will display the I/O which will allow
     * user to interact with the program
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        System.out.println("Dijkstra's Algorithm");
        System.out.println();
        System.out.println("Reference Key:");
        System.out.println("A = 0");
        System.out.println("J = 1");
        System.out.println("M = 2");
        System.out.println("R = 3");
        System.out.println("K = 4");
        System.out.println("S = 5");
        System.out.println("I = 6");
        System.out.println("N = 7");
        System.out.println("T = 8");
        System.out.println("D = 9");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int start, end;
        start = -1; end = -2;
        while (start > 9 || start < 0){
            System.out.println("Starting vertex?");
            System.out.println("Enter a number from 0-9");
            start = scanner.nextInt();
            System.out.println();
        }
        while (end > 9 || end < 0 || end == start){
            System.out.println("Ending vertex?");
            System.out.println("Enter a number from 0-9");
            System.out.println("You can't enter" + start + " since that's where you started");
            end = scanner.nextInt();
            System.out.println();
        }
        actualAlgo(start,end);
    }

}
