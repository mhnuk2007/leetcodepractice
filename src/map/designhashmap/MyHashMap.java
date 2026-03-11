package map.designhashmap;

/**
 * LeetCode 706 - Design HashMap
 *
 * Problem:
 * Design a HashMap without using any built-in hash table libraries.
 *
 * Implement the following:
 *   - MyHashMap()        : Initializes the HashMap.
 *   - put(key, value)    : Inserts (key, value). If key exists, updates value.
 *   - get(key)           : Returns value for key, or -1 if key does not exist.
 *   - remove(key)        : Removes key and its value if key exists, otherwise no-op.
 *
 * Constraints:
 *   - 0 <= key, value <= 10^6
 *   - At most 10^4 calls to put, get, remove.
 *
 * Example:
 *   put(1, 1)   → map: {1=1}
 *   put(2, 2)   → map: {1=1, 2=2}
 *   get(1)      → 1
 *   get(3)      → -1  (not found)
 *   put(2, 1)   → map: {1=1, 2=1}  (update existing key)
 *   get(2)      → 1
 *   remove(2)   → map: {1=1}
 *   get(2)      → -1  (removed)
 *
 * Approach: Separate Chaining with Custom Linked List Buckets
 *   - Array of Bucket objects, each holding a dummy-headed singly linked list.
 *   - Each Node stores both key and value (unlike HashSet which stores key only).
 *   - Double hash: (key ^ key >>> 16) % NUM_BUCKETS reduces clustering.
 *   - NUM_BUCKETS = 769 (prime) for better key distribution.
 *   - findPrev() is the shared traversal helper across insert, remove, get.
 *   - insert() doubles as update — if key exists, overwrites value in place.
 *
 * Time Complexity : O(n/k) average — n keys across k buckets
 * Space Complexity: O(n + k)
 */

/**
 * Node stores a key-value pair and a pointer to the next node.
 * Key difference from HashSet's Node: stores both key AND val.
 */
class Node {
    int key;
    int val;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * Single bucket backed by a dummy-headed singly linked list.
 *
 * Chain structure:
 *   [dummy(-1,-1)] → [k1,v1] → [k2,v2] → null
 *
 * findPrev() matches on key (not val) — used by insert, remove, get.
 */
class Bucket {
    private final Node dummy;

    public Bucket() {
        this.dummy = new Node(-1, -1); // dummy head — never removed
    }

    /**
     * Returns the node just before the node with the given key.
     * Returns the last node if key is not found.
     * Dummy head ensures prev is never null.
     */
    private Node findPrev(int key) {
        Node prev = dummy;
        while (prev.next != null && prev.next.key != key) {
            prev = prev.next;
        }
        return prev;
    }

    // insert new key-value pair, or update value if key already exists
    public void insert(int key, int value) {
        Node prev = findPrev(key);
        if (prev.next == null) {
            prev.next = new Node(key, value); // key not found — append
        } else {
            prev.next.val = value;            // key found — update in place
        }
    }

    // unlink target node if key found, no-op otherwise
    public void remove(int key) {
        Node prev = findPrev(key);
        if (prev.next != null) {
            prev.next = prev.next.next; // bypass target node
        }
    }

    // return value for key, or -1 if key does not exist
    public int get(int key) {
        Node prev = findPrev(key);
        if (prev.next == null) {
            return -1;          // key not found
        }
        return prev.next.val;   // return associated value
    }
}

public class MyHashMap {

    private static final int NUM_BUCKETS = 769; // prime → better distribution
    private final Bucket[] buckets;

    public MyHashMap() {
        this.buckets = new Bucket[NUM_BUCKETS];
        for (int i = 0; i < NUM_BUCKETS; i++) {
            this.buckets[i] = new Bucket();
        }
    }

    // double hash: folds upper 16 bits into lower 16 before modding
    private int getIndex(int key) {
        return (key ^ (key >>> 16)) % NUM_BUCKETS;
    }

    public void put(int key, int value) {
        this.buckets[getIndex(key)].insert(key, value);
    }

    public int get(int key) {
        return this.buckets[getIndex(key)].get(key);
    }

    public void remove(int key) {
        this.buckets[getIndex(key)].remove(key);
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();

        // Test 1: basic put and get
        map.put(1, 1);
        map.put(2, 2);
        System.out.println(map.get(1)); // 1
        System.out.println(map.get(3)); // -1 (not found)

        // Test 2: update existing key
        map.put(2, 10);
        System.out.println(map.get(2)); // 10 (updated)

        // Test 3: remove existing key
        map.remove(2);
        System.out.println(map.get(2)); // -1 (removed)

        // Test 4: remove non-existent key (no-op)
        map.remove(999);

        // Test 5: collision — keys that map to same bucket
        map.put(0, 100);
        map.put(769, 200);
        System.out.println(map.get(0));   // 100
        System.out.println(map.get(769)); // 200

        // Test 6: remove one colliding key, other remains intact
        map.remove(0);
        System.out.println(map.get(0));   // -1
        System.out.println(map.get(769)); // 200

        // Test 7: boundary — max key and value in constraint range
        map.put(1000000, 1000000);
        System.out.println(map.get(1000000)); // 1000000
        map.remove(1000000);
        System.out.println(map.get(1000000)); // -1
    }
}