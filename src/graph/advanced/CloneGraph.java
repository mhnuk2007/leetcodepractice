package graph;

import java.util.*;

/**
 * <h2>LC 133 · Clone Graph</h2>
 *
 * <p><b>Problem:</b> Given a reference to a node in a connected undirected graph,
 * return a deep copy of the entire graph. Each node contains a value and a list
 * of its neighbours.</p>
 *
 * <p><b>Approach 1 (BFS + HashMap):</b>
 * A {@code HashMap<Node, Node>} serves as both the visited set and the clone
 * registry, ensuring each node is cloned exactly once and cycles are handled
 * safely. BFS traverses the original graph level by level; for each dequeued
 * node, unseen neighbours are cloned and enqueued, then wired into the current
 * clone's adjacency list.</p>
 *
 * <p><b>Approach 2 (DFS + HashMap):</b>
 * Same clone map, but traversal is recursive. The clone is registered
 * <em>before</em> recursing into neighbours — this is what breaks cycles.</p>
 *
 * <p><b>Example:</b>
 * <pre>
 *   Input:  1 -- 2       Output: deep copy — same structure,
 *           |    |                no shared object references
 *           4 -- 3
 * </pre>
 * </p>
 *
 * <p><b>Time  Complexity:</b> O(V + E) — every vertex and edge visited once.</p>
 * <p><b>Space Complexity:</b> O(V) — clone map and BFS queue / call stack each
 * hold at most V entries.</p>
 */
public class CloneGraph {

    // ---------------------------------------------------------------------- //
    //  Node definition                                                         //
    // ---------------------------------------------------------------------- //

    /**
     * A graph node holding an integer value and an adjacency list.
     */
    static class Node {

        /** Value stored at this node. */
        public int val;

        /** Adjacent nodes in the undirected graph. */
        public List<Node> neighbors;

        /**
         * Constructs a node with the given value and an empty neighbour list.
         *
         * @param val integer value for this node
         */
        public Node(int val) {
            this.val  = val;
            neighbors = new ArrayList<>();
        }
    }

    // ---------------------------------------------------------------------- //
    //  Solution 1 — BFS (iterative)                                           //
    // ---------------------------------------------------------------------- //

    /**
     * Returns a deep copy of the connected undirected graph reachable from
     * {@code node} using iterative BFS.
     *
     * <p><b>Implementation note:</b> {@code map.get(curr)} is cached as
     * {@code clone} before the neighbour loop to avoid a redundant hash lookup
     * on every iteration.</p>
     *
     * <p>Prefer this over DFS for very deep graphs — BFS uses an explicit
     * queue so there is no risk of stack overflow.</p>
     *
     * @param node any node in the connected undirected graph, or {@code null}
     * @return the corresponding node in the deep-copied graph,
     *         or {@code null} if {@code node} is {@code null}
     */
    public static Node cloneGraph(Node node) {
        if (node == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue   = new ArrayDeque<>();

        map.put(node, new Node(node.val));
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node curr  = queue.poll();
            Node clone = map.get(curr);          // cache — avoids repeated lookup

            for (Node neighbor : curr.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                clone.neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }

    // ---------------------------------------------------------------------- //
    //  Solution 2 — DFS (recursive)                                           //
    // ---------------------------------------------------------------------- //

    /**
     * Returns a deep copy of the connected undirected graph reachable from
     * {@code node} using recursive DFS.
     *
     * <p>Functionally identical to {@link #cloneGraph(Node)}.
     * Prefer BFS for graphs that may be very deep to avoid stack overflow
     * on graphs with O(V) depth.</p>
     *
     * @param node any node in the connected undirected graph, or {@code null}
     * @return the corresponding node in the deep-copied graph,
     *         or {@code null} if {@code node} is {@code null}
     */
    public static Node cloneGraphDFS(Node node) {
        if (node == null) return null;
        return dfs(node, new HashMap<>());
    }

    /**
     * Recursively clones the subgraph reachable from {@code node}.
     *
     * <p>The clone is registered in {@code cloneMap} <em>before</em> recursing
     * into neighbours — this is what breaks cycles. If a neighbour points back
     * to an already-cloned node, the existing clone is returned immediately
     * rather than cloning it again.</p>
     *
     * @param node     current node being cloned
     * @param cloneMap maps each original node to its clone
     * @return the clone of {@code node}
     */
    private static Node dfs(Node node, Map<Node, Node> cloneMap) {
        if (cloneMap.containsKey(node)) return cloneMap.get(node);

        Node copy = new Node(node.val);
        cloneMap.put(node, copy);              // register before recursing — breaks cycles

        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(dfs(neighbor, cloneMap));
        }

        return copy;
    }

    // ---------------------------------------------------------------------- //
    //  Utility: print graph via BFS                                           //
    // ---------------------------------------------------------------------- //

    /**
     * Prints the adjacency list of each reachable node in BFS order.
     *
     * @param node root of the graph to print, or {@code null}
     */
    public static void printGraph(Node node) {
        if (node == null) {
            System.out.println("  (null)");
            return;
        }

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();

        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print("  Node " + curr.val + " -> ");

            for (Node neighbor : curr.neighbors) {
                System.out.print(neighbor.val + " ");
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
            System.out.println();
        }
    }

    // ---------------------------------------------------------------------- //
    //  Utility: deep-copy validator                                           //
    // ---------------------------------------------------------------------- //

    /**
     * BFS-validates that {@code clone} is a structurally correct, fully
     * independent deep copy of {@code original}:
     * <ul>
     *   <li>Every node value matches.</li>
     *   <li>Every neighbour count and value matches.</li>
     *   <li>No node object is shared between the two graphs.</li>
     * </ul>
     *
     * @param original root of the source graph
     * @param clone    root of the cloned graph
     * @return {@code true} if the clone is valid; {@code false} otherwise
     */
    private static boolean isValidClone(Node original, Node clone) {
        if (original == null && clone == null) return true;
        if (original == null || clone == null) return false;

        Map<Integer, Node> seenOrig  = new HashMap<>();
        Map<Integer, Node> seenClone = new HashMap<>();
        Queue<Node> queue            = new ArrayDeque<>();

        seenOrig.put(original.val, original);
        seenClone.put(clone.val, clone);
        queue.offer(original);

        while (!queue.isEmpty()) {
            Node o = queue.poll();
            Node c = seenClone.get(o.val);

            if (o == c)                                    return false;
            if (o.neighbors.size() != c.neighbors.size()) return false;

            for (int i = 0; i < o.neighbors.size(); i++) {
                Node on = o.neighbors.get(i);
                Node cn = c.neighbors.get(i);

                if (on.val != cn.val) return false;
                if (on == cn)         return false;

                if (!seenOrig.containsKey(on.val)) {
                    seenOrig.put(on.val, on);
                    seenClone.put(cn.val, cn);
                    queue.offer(on);
                }
            }
        }
        return true;
    }

    // ---------------------------------------------------------------------- //
    //  Main — labelled test cases                                              //
    // ---------------------------------------------------------------------- //

    /**
     * Entry point. Runs labelled test cases covering the standard 4-node cycle,
     * a single node, a self-loop, a linear chain, the null-input guard, and
     * BFS vs DFS equivalence.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {

        // ------------------------------------------------------------------ //
        // Test 1: 4-node cycle  1-2-3-4-1  (LC example)                      //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 1: 4-node cycle ===");
        {
            Node n1 = new Node(1), n2 = new Node(2),
                    n3 = new Node(3), n4 = new Node(4);

            n1.neighbors.add(n2); n1.neighbors.add(n4);
            n2.neighbors.add(n1); n2.neighbors.add(n3);
            n3.neighbors.add(n2); n3.neighbors.add(n4);
            n4.neighbors.add(n1); n4.neighbors.add(n3);

            Node clone = cloneGraph(n1);

            System.out.println("Original:"); printGraph(n1);
            System.out.println("Clone:");    printGraph(clone);
            System.out.println("Deep copy valid: "
                    + (isValidClone(n1, clone) ? "PASS" : "FAIL"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 2: Single node, no neighbours                                  //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 2: Single node, no neighbours ===");
        {
            Node original = new Node(10);
            Node clone    = cloneGraph(original);

            printGraph(clone);
            System.out.println("Different object : " + (original != clone         ? "PASS" : "FAIL"));
            System.out.println("Value preserved  : " + (clone.val == 10           ? "PASS" : "FAIL"));
            System.out.println("No neighbours    : " + (clone.neighbors.isEmpty() ? "PASS" : "FAIL"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 3: Single node with a self-loop                                //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 3: Self-loop ===");
        {
            Node original = new Node(1);
            original.neighbors.add(original);

            Node clone = cloneGraph(original);

            boolean selfLoop = !clone.neighbors.isEmpty()
                    && clone.neighbors.get(0) == clone;

            System.out.println("Different object   : " + (original != clone ? "PASS" : "FAIL"));
            System.out.println("Self-loop on clone : " + (selfLoop           ? "PASS" : "FAIL"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 4: Linear chain  1 -- 2 -- 3                                  //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 4: Linear chain ===");
        {
            Node n1 = new Node(1), n2 = new Node(2), n3 = new Node(3);
            n1.neighbors.add(n2);
            n2.neighbors.add(n1); n2.neighbors.add(n3);
            n3.neighbors.add(n2);

            Node clone = cloneGraph(n1);

            printGraph(clone);
            System.out.println("Deep copy valid: "
                    + (isValidClone(n1, clone) ? "PASS" : "FAIL"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 5: Null input                                                   //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 5: Null input ===");
        {
            Node clone = cloneGraph(null);
            System.out.println("Returns null: " + (clone == null ? "PASS" : "FAIL"));
        }
        System.out.println();

        // ------------------------------------------------------------------ //
        // Test 6: BFS vs DFS produce equivalent clones                        //
        // ------------------------------------------------------------------ //
        System.out.println("=== Test 6: BFS vs DFS equivalence ===");
        {
            Node n1 = new Node(1), n2 = new Node(2),
                    n3 = new Node(3), n4 = new Node(4);

            n1.neighbors.add(n2); n1.neighbors.add(n4);
            n2.neighbors.add(n1); n2.neighbors.add(n3);
            n3.neighbors.add(n2); n3.neighbors.add(n4);
            n4.neighbors.add(n1); n4.neighbors.add(n3);

            Node bfsClone = cloneGraph(n1);
            Node dfsClone = cloneGraphDFS(n1);

            System.out.println("BFS clone valid: " + (isValidClone(n1, bfsClone) ? "PASS" : "FAIL"));
            System.out.println("DFS clone valid: " + (isValidClone(n1, dfsClone) ? "PASS" : "FAIL"));
        }
    }
}