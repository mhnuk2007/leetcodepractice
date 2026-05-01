package graph;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * <h2>AdjacencyMatrix</h2>
 *
 * <p>Represents an undirected graph using a boolean adjacency matrix.
 * Self-loops are permitted; parallel edges are not (the matrix cell is
 * either {@code true} or {@code false}).</p>
 *
 * <p><b>Approach:</b> A V×V boolean matrix {@code adjMatrix} stores edge
 * presence. For an undirected edge (u, v) both {@code adjMatrix[u][v]} and
 * {@code adjMatrix[v][u]} are set to {@code true}.</p>
 *
 * <p><b>Example:</b>
 * <pre>
 *   Graph g = new Graph(4, 4);
 *   // Creates a random undirected graph with 4 vertices and 4 edges.
 * </pre>
 * </p>
 *
 * <p><b>Time  Complexity:</b> O(V²) space; O(1) edge lookup; O(V) adjacency
 * iteration per vertex; O(V²) for {@code toString()}.</p>
 * <p><b>Space Complexity:</b> O(V²)</p>
 */
public class AdjacencyMatrix {

    /**
     * An undirected graph backed by a boolean adjacency matrix.
     */
    static class Graph {

        private static final String NEWLINE = System.getProperty("line.separator");

        /** Number of vertices (immutable after construction). */
        private final int V;

        /** Number of edges (grows as edges are added). */
        private int E;

        /** V×V adjacency matrix; {@code adjMatrix[u][v] == true} iff edge (u,v) exists. */
        private final boolean[][] adjMatrix;

        // ------------------------------------------------------------------ //
        //  Constructors                                                        //
        // ------------------------------------------------------------------ //

        /**
         * Creates an empty undirected graph with {@code V} vertices and no edges.
         *
         * @param V number of vertices (must be ≥ 0)
         * @throws IllegalArgumentException if {@code V < 0}
         */
        public Graph(int V) {
            if (V < 0) throw new IllegalArgumentException("Too few vertices");
            this.V = V;
            this.E = 0;
            this.adjMatrix = new boolean[V][V];
        }

        /**
         * Creates a random undirected graph with {@code V} vertices and exactly
         * {@code E} edges chosen uniformly at random (no parallel edges).
         *
         * @param V number of vertices (must be ≥ 0)
         * @param E number of edges   (0 ≤ E ≤ V(V+1)/2)
         * @throws IllegalArgumentException if {@code E} is out of the valid range
         */
        public Graph(int V, int E) {
            this(V);
            if (E < 0)                          throw new IllegalArgumentException("Too few edges");
            if (E > V * (V - 1) / 2 + V)       throw new IllegalArgumentException("Too many edges");

            Random rand = new Random();
            while (this.E < E) {
                int u = rand.nextInt(V);
                int v = rand.nextInt(V);
                addEdge(u, v);
            }
        }

        // ------------------------------------------------------------------ //
        //  Accessors                                                           //
        // ------------------------------------------------------------------ //

        /**
         * Returns the number of vertices in this graph.
         *
         * @return V
         */
        public int V() {
            return V;
        }

        /**
         * Returns the number of edges in this graph.
         *
         * @return E
         */
        public int E() {
            return E;
        }

        // ------------------------------------------------------------------ //
        //  Mutation                                                            //
        // ------------------------------------------------------------------ //

        /**
         * Adds the undirected edge (u, v) to the graph.
         * If the edge already exists, this call is a no-op (no parallel edges).
         *
         * @param u one endpoint
         * @param v the other endpoint
         */
        public void addEdge(int u, int v) {
            if (!adjMatrix[u][v]) {
                E++;
                adjMatrix[u][v] = true;
                adjMatrix[v][u] = true;
            }
        }

        // ------------------------------------------------------------------ //
        //  Query                                                               //
        // ------------------------------------------------------------------ //

        /**
         * Returns {@code true} if the graph contains the edge (u, v).
         *
         * @param u one endpoint
         * @param v the other endpoint
         * @return {@code true} if edge (u, v) exists; {@code false} otherwise
         */
        public boolean contains(int u, int v) {
            return adjMatrix[u][v];
        }

        /**
         * Returns an {@link Iterable} over the neighbours of vertex {@code u}.
         *
         * <p><b>Time Complexity:</b> O(V) per full iteration.</p>
         *
         * @param u the source vertex
         * @return iterable of vertices adjacent to {@code u}
         */
        public Iterable<Integer> adj(int u) {
            return new AdjIterator(u);
        }

        // ------------------------------------------------------------------ //
        //  Inner iterator                                                      //
        // ------------------------------------------------------------------ //

        /**
         * Lazy row-scan iterator over column indices where
         * {@code adjMatrix[u][col] == true}.
         */
        private class AdjIterator implements Iterable<Integer>, Iterator<Integer> {

            private final int u;
            private int v = 0;

            /**
             * Constructs an iterator for the neighbours of vertex {@code u}.
             *
             * @param u source vertex
             */
            public AdjIterator(int u) {
                this.u = u;
            }

            @Override
            public Iterator<Integer> iterator() {
                return this;
            }

            /**
             * Returns {@code true} if there is at least one unvisited neighbour.
             *
             * @return {@code true} if another adjacent vertex exists
             */
            @Override
            public boolean hasNext() {
                while (v < V) {
                    if (adjMatrix[u][v]) return true;
                    v++;
                }
                return false;
            }

            /**
             * Returns the index of the next adjacent vertex.
             *
             * @return next neighbour of {@code u}
             * @throws NoSuchElementException if no more neighbours remain
             */
            @Override
            public Integer next() {
                if (!hasNext()) throw new NoSuchElementException();
                return v++;
            }

            /**
             * Removal is not supported.
             *
             * @throws UnsupportedOperationException always
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        // ------------------------------------------------------------------ //
        //  String representation  —  O(V²)                                    //
        // ------------------------------------------------------------------ //

        /**
         * Returns a human-readable adjacency matrix of the graph.
         *
         * <p><b>Time Complexity:</b> O(V²)</p>
         *
         * @return formatted string showing the full adjacency matrix
         */
        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append("Undirected Graph").append(NEWLINE);
            s.append("Vertices: ").append(V).append(" | Edges: ").append(E).append(NEWLINE);
            s.append("    ");
            for (int i = 0; i < V; i++) s.append(String.format("%4d", i));
            s.append(NEWLINE);
            s.append("    ").append("-".repeat(V * 4)).append(NEWLINE);
            for (int u = 0; u < V; u++) {
                s.append(String.format("%3d|", u));
                for (int v = 0; v < V; v++) {
                    s.append(String.format("%4s", adjMatrix[u][v] ? "1" : "."));
                }
                s.append(NEWLINE);
            }
            return s.toString();
        }
    }

    // ---------------------------------------------------------------------- //
    //  Main — labelled test cases                                             //
    // ---------------------------------------------------------------------- //

    /**
     * Entry point. Runs labelled test cases covering construction,
     * edge queries, adjacency iteration, and string output.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        // Test 1: Empty graph — no edges should exist
        System.out.println("=== Test 1: Empty Graph (V=4) ===");
        Graph emptyGraph = new Graph(4);
        System.out.println(emptyGraph);
        System.out.println("Expected E=0, Got E=" + emptyGraph.E());
        System.out.println();

        // Test 2: Manual edge insertion and contains() check
        System.out.println("=== Test 2: Manual addEdge + contains() ===");
        Graph manualGraph = new Graph(4);
        manualGraph.addEdge(0, 1);
        manualGraph.addEdge(1, 2);
        manualGraph.addEdge(2, 3);
        manualGraph.addEdge(0, 3);
        System.out.println(manualGraph);
        System.out.println("contains(0,1) → expected true,  got " + manualGraph.contains(0, 1));
        System.out.println("contains(1,0) → expected true,  got " + manualGraph.contains(1, 0));  // undirected
        System.out.println("contains(0,2) → expected false, got " + manualGraph.contains(0, 2));
        System.out.println();

        // Test 3: Duplicate edge must not inflate E
        System.out.println("=== Test 3: Duplicate addEdge (no parallel edges) ===");
        Graph dupGraph = new Graph(3);
        dupGraph.addEdge(0, 1);
        dupGraph.addEdge(0, 1);  // duplicate
        System.out.println("Expected E=1, Got E=" + dupGraph.E());
        System.out.println();

        // Test 4: adj() iterator returns correct neighbours
        System.out.println("=== Test 4: adj() Iterator ===");
        Graph adjGraph = new Graph(4);
        adjGraph.addEdge(0, 1);
        adjGraph.addEdge(0, 2);
        adjGraph.addEdge(0, 3);
        System.out.print("Neighbours of vertex 0 (expected 1 2 3): ");
        for (int neighbour : adjGraph.adj(0)) {
            System.out.print(neighbour + " ");
        }
        System.out.println();
        System.out.println();

        // Test 5: Random graph with exact edge count
        System.out.println("=== Test 5: Random Graph (V=5, E=7) ===");
        Graph randomGraph = new Graph(5, 7);
        System.out.println(randomGraph);
        System.out.println("Expected E=7, Got E=" + randomGraph.E());
        System.out.println();

        // Test 6: Self-loops
        System.out.println("=== Test 6: Self-loop ===");
        Graph selfLoop = new Graph(3);
        selfLoop.addEdge(1, 1);
        System.out.println("Self-loop on vertex 1 — contains(1,1) → expected true, got " + selfLoop.contains(1, 1));
        System.out.println("Expected E=1, Got E=" + selfLoop.E());
        System.out.println();

        // Test 7: IllegalArgumentException on negative vertices
        System.out.println("=== Test 7: IllegalArgumentException (V=-1) ===");
        try {
            new Graph(-1);
            System.out.println("FAIL — expected exception not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS — caught: " + e.getMessage());
        }
        System.out.println();

        // Test 8: IllegalArgumentException on too many edges
        System.out.println("=== Test 8: IllegalArgumentException (too many edges) ===");
        try {
            new Graph(3, 100);
            System.out.println("FAIL — expected exception not thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS — caught: " + e.getMessage());
        }
    }
}