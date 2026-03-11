package set.designhashset;

/**
 * LeetCode 705 - Design HashSet
 *
 * Problem:
 * Design a HashSet without using any built-in hash table libraries.
 *
 * Implement the following:
 *   - MyHashSet()   : Initializes the HashSet.
 *   - add(key)      : Inserts key into the HashSet.
 *   - remove(key)   : Removes key if it exists, otherwise does nothing.
 *   - contains(key) : Returns true if key exists, false otherwise.
 *
 * Constraints:
 *   - 0 <= key <= 10^6
 *   - At most 10^4 calls to add, remove, contains.
 *
 * Approach: Separate Chaining with Custom Linked List Buckets
 *   - Array of Bucket objects, each holding a dummy-headed singly linked list.
 *   - Double hash: (key ^ key >>> 16) % NUM_BUCKETS folds upper bits into
 *     lower bits before modding, reducing clustering for sequential keys.
 *   - NUM_BUCKETS = 769 (prime) further reduces collision patterns.
 *   - Bucket uses findPrev() for insert/remove (needs the node before target).
 *   - contains() traverses directly from dummy.next — no prev pointer needed.
 *
 * Time Complexity : O(n/k) average — n keys across k buckets
 * Space Complexity: O(n + k)
 */

/**
 * Pure data container — val and next pointer only.
 * No logic lives here; all traversal and mutation belongs in Bucket.
 */
class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
    }
}

/**
 * Single bucket backed by a dummy-headed singly linked list.
 *
 * Chain structure:
 *   [dummy(-1)] → [key1] → [key2] → null
 *
 * findPrev() is the shared traversal helper for insert and remove.
 * contains() uses a direct forward scan — no prev pointer needed.
 */
class Bucket {
    private final Node dummy;

    public Bucket() {
        this.dummy = new Node(-1);
    }

    /**
     * Returns the node just before the node with the given val.
     * Returns the last node if val is not found.
     * Dummy head ensures prev is never null.
     */
    private Node findPrev(int val) {
        Node prev = dummy;
        while (prev.next != null && prev.next.val != val) {
            prev = prev.next;
        }
        return prev;
    }

    // insert only if key does not already exist
    public void insert(int val) {
        Node prev = findPrev(val);
        if (prev.next == null) {
            prev.next = new Node(val); // append at end of chain
        }
    }

    // unlink target node if found, no-op otherwise
    public void remove(int val) {
        Node prev = findPrev(val);
        if (prev.next != null) {
            prev.next = prev.next.next; // bypass target node
        }
    }

    // direct forward scan — no prev pointer needed for a read-only check
    public boolean contains(int val) {
        Node curr = dummy.next; // skip dummy head
        while (curr != null) {
            if (curr.val == val) return true;
            curr = curr.next;
        }
        return false;
    }
}

class MyHashSet {

    private static final int NUM_BUCKETS = 769; // prime → better distribution
    private final Bucket[] buckets;

    public MyHashSet() {
        this.buckets = new Bucket[NUM_BUCKETS];
        for (int i = 0; i < NUM_BUCKETS; i++) {
            this.buckets[i] = new Bucket();
        }
    }

    // double hash: folds upper 16 bits into lower 16 before modding
    // reduces clustering for sequential or patterned keys
    private int getIndex(int key) {
        return (key ^ (key >>> 16)) % NUM_BUCKETS;
    }

    public void add(int key) {
        this.buckets[getIndex(key)].insert(key);
    }

    public void remove(int key) {
        this.buckets[getIndex(key)].remove(key);
    }

    public boolean contains(int key) {
        return this.buckets[getIndex(key)].contains(key);
    }

    public static void main(String[] args) {
        MyHashSet set = new MyHashSet();

        // Test 1: basic add and contains
        set.add(1);
        set.add(2);
        System.out.println(set.contains(1)); // true
        System.out.println(set.contains(3)); // false

        // Test 2: duplicate add (no change)
        set.add(2);
        System.out.println(set.contains(2)); // true

        // Test 3: remove existing key
        set.remove(2);
        System.out.println(set.contains(2)); // false

        // Test 4: remove non-existent key (no-op)
        set.remove(999);

        // Test 5: collision — keys that map to same bucket
        set.add(0);
        set.add(769);
        System.out.println(set.contains(0));   // true
        System.out.println(set.contains(769)); // true

        // Test 6: remove one colliding key, other remains intact
        set.remove(0);
        System.out.println(set.contains(0));   // false
        System.out.println(set.contains(769)); // true

        // Test 7: boundary — max key in constraint range
        set.add(1000000);
        System.out.println(set.contains(1000000)); // true
        set.remove(1000000);
        System.out.println(set.contains(1000000)); // false
    }
}