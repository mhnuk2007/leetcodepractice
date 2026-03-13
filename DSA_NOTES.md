# 📘 Data Structures & Algorithms Notes — Java

**Author:** Mohan Lal | **GitHub:** [mhnuk2007](https://github.com/mhnuk2007) | **LeetCode:** [mhnuk2007](https://leetcode.com/mhnuk2007)

> Comprehensive reference for core DSA patterns, templates, and techniques used in solving LeetCode problems in Java.

---

## 📑 Table of Contents

| # | Pattern | Complexity |
|---|---------|------------|
| 1 | [Two Pointers](#1️⃣-two-pointers) | O(n) / O(1) |
| 2 | [Sliding Window](#2️⃣-sliding-window) | O(n) / O(k) |
| 3 | [Fast & Slow Pointers](#3️⃣-fast--slow-pointers-floyds-cycle-detection) | O(n) / O(1) |
| 4 | [Prefix Sum](#4️⃣-prefix-sum) | O(n) / O(n) |
| 5 | [Hashing](#5️⃣-hashing-hashmap--hashset) | O(n) / O(n) |
| 6 | [Monotonic Stack](#6️⃣-monotonic-stack) | O(n) / O(n) |
| 7 | [Binary Search](#7️⃣-binary-search) | O(log n) / O(1) |
| 8 | [DFS](#8️⃣-depth-first-search-dfs) | O(V+E) / O(V) |
| 9 | [BFS](#9️⃣-breadth-first-search-bfs) | O(V+E) / O(V) |
| 10 | [Heap / Priority Queue](#🔟-heap--priority-queue) | O(n log k) / O(k) |
| 11 | [Backtracking](#1️⃣1️⃣-backtracking) | O(2^n) / O(n) |
| 12 | [Union Find](#1️⃣2️⃣-union-find-disjoint-set-union) | O(α(n)) / O(n) |
| 13 | [Dynamic Programming](#1️⃣3️⃣-dynamic-programming-dp) | O(n²) / O(n²) |
| 14 | [Bit Manipulation](#1️⃣4️⃣-bit-manipulation) | O(1) / O(1) |
| — | [Complexity Cheat Sheet](#-complexity-cheat-sheet) | — |
| — | [Pattern Combinations](#-pattern-combinations) | — |
| — | [Decision Framework](#-problem-solving-decision-framework) | — |

---

## 1️⃣ Two Pointers

**Idea:** Use two indices that traverse the data structure from different directions or at different speeds to reduce nested loops from O(n²) to O(n).

### When to use
- Input array is **sorted** (Two Sum II, 3Sum, Squares of Sorted Array)
- Finding **pairs or triplets** that satisfy a condition
- **Palindrome** checks (Valid Palindrome)
- Removing duplicates or elements **in-place**
- **Merging** two sorted arrays

### Common Patterns
1. **Opposite Direction** — `left = 0`, `right = n-1`, move towards center
2. **Same Direction** — `fast` and `slow` (removing elements, linked list problems)

### Java Template

```java
int left = 0;
int right = arr.length - 1;

while (left < right) {
    int sum = arr[left] + arr[right];
    if (sum == target) {
        return new int[]{left, right};
    } else if (sum < target) {
        left++;
    } else {
        right--;
    }
}
```

### Example Problems
- 1. Two Sum
- 11. Container With Most Water
- 15. 3Sum
- 26. Remove Duplicates from Sorted Array
- 88. Merge Sorted Array
- 977. Squares of a Sorted Array

> ⏱ **Time:** O(n) &nbsp;&nbsp; 💾 **Space:** O(1)

---

## 2️⃣ Sliding Window

**Idea:** Convert nested loops O(n²) into a single loop O(n) by maintaining a "window" of elements that satisfies a certain condition.

### When to use
- **Subarray** or **substring** problems
- Finding the **longest** or **shortest** subarray with a specific property
- Calculating a **sum or product** within a window of size `k`
- Problems with **"at most k distinct"** or **"exactly k"** conditions

### Common Patterns
1. **Fixed Window** — window size is constant (sum of k elements)
2. **Variable Window** — shrink/expand based on a condition (most common)

### Java Template

```java
int left = 0;
int currentSum = 0;
int ans = 0;

for (int right = 0; right < arr.length; right++) {
    currentSum += arr[right];        // 1. Expand window

    while (currentSum > target) {    // 2. Shrink if invalid
        currentSum -= arr[left];
        left++;
    }

    ans = Math.max(ans, right - left + 1); // 3. Update answer
}
```

### Example Problems
- 3. Longest Substring Without Repeating Characters
- 76. Minimum Window Substring
- 209. Minimum Size Subarray Sum
- 424. Longest Repeating Character Replacement
- 438. Find All Anagrams in a String
- 567. Permutation in String

> ⏱ **Time:** O(n) &nbsp;&nbsp; 💾 **Space:** O(k) or O(1)

---

## 3️⃣ Fast & Slow Pointers (Floyd's Cycle Detection)

**Idea:** Use two pointers moving at different speeds — typically `1x` (slow) and `2x` (fast). If a cycle exists, they will eventually meet.

### When to use
- **Cycle detection** in Linked Lists or Arrays
- Finding the **middle** of a Linked List
- Determining if a Linked List is a **palindrome**
- **Happy Number** — cycle detection in number sequences

### Common Patterns
1. **Cycle detection** — slow=1x, fast=2x; they meet inside the cycle
2. **Cycle entry** — after meeting, reset one pointer to head; they meet at cycle start
3. **Middle** — when fast reaches end, slow is at middle

### Java Template

```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;          // Move 1 step
    fast = fast.next.next;     // Move 2 steps

    if (slow == fast) {
        return true;           // Cycle detected
    }
}
return false;
```

### Example Problems
- 141. Linked List Cycle
- 142. Linked List Cycle II
- 876. Middle of the Linked List
- 234. Palindrome Linked List
- 287. Find the Duplicate Number ← Floyd's applied to array

> ⏱ **Time:** O(n) &nbsp;&nbsp; 💾 **Space:** O(1)

---

## 4️⃣ Prefix Sum

**Idea:** Precompute cumulative sums so any range sum query can be answered in O(1) instead of O(n).

### When to use
- **Range sum** queries (sum from index l to r in O(1))
- **Subarray sum equals K** — combine with HashMap
- Contiguous array with **equal 0s and 1s**
- **2D matrix** range sum queries

### Common Patterns
1. `prefix[i] = prefix[i-1] + nums[i-1]` — 1-indexed for easier range queries
2. `sum(l, r) = prefix[r+1] - prefix[l]`
3. **With HashMap** — store `prefixSum → first seen index` for subarray sum = K

### Java Template

```java
// Build prefix sum array
int[] prefix = new int[n + 1];
for (int i = 1; i <= n; i++)
    prefix[i] = prefix[i - 1] + nums[i - 1];

// Range sum query O(1)
int sum = prefix[r + 1] - prefix[l];

// ── Subarray Sum Equals K (Prefix Sum + HashMap) ──
Map<Integer, Integer> map = new HashMap<>();
map.put(0, 1);  // empty prefix has sum 0
int count = 0, prefixSum = 0;

for (int num : nums) {
    prefixSum += num;
    count += map.getOrDefault(prefixSum - k, 0);
    map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
}
```

### Example Problems
- 303. Range Sum Query - Immutable
- 437. Path Sum III
- 525. Contiguous Array
- 560. Subarray Sum Equals K
- 1480. Running Sum of 1d Array

> ⏱ **Time:** O(n) &nbsp;&nbsp; 💾 **Space:** O(n)

---

## 5️⃣ Hashing (HashMap / HashSet)

**Idea:** Use a Hash Table to store data for O(1) average time complexity lookups, inserts, and deletions.

### When to use
- Checking for **existence** or duplicates (HashSet)
- Counting **frequencies** of elements (HashMap)
- **O(1) lookup** (Two Sum — store complement)
- **Mapping relationships** between two sequences (Isomorphic Strings)
- Tracking **last-seen indices** (Contains Duplicate II)

### Common Patterns
1. **Frequency map** — `map.getOrDefault(key, 0) + 1`
2. **int[26] or int[128]** — faster than HashMap for fixed character sets, O(1) space
3. **Two maps** — bidirectional mapping (Isomorphic Strings)
4. **Single int[512]** — last-seen index comparison (optimal isomorphic check)

### Java Template

```java
// Frequency Map
Map<Integer, Integer> map = new HashMap<>();
for (int num : nums) {
    map.put(num, map.getOrDefault(num, 0) + 1);
}

// Fixed charset — int[26] faster than HashMap, O(1) space
int[] freq = new int[26];
for (char c : s.toCharArray())
    freq[c - 'a']++;

// Existence check with HashSet
Set<Integer> set = new HashSet<>();
for (int num : nums) {
    if (!set.add(num)) return true; // duplicate found
}
```

### Example Problems
- 1. Two Sum
- 49. Group Anagrams
- 128. Longest Consecutive Sequence
- 205. Isomorphic Strings
- 217. Contains Duplicate
- 219. Contains Duplicate II
- 347. Top K Frequent Elements
- 560. Subarray Sum Equals K
- 705. Design HashSet
- 706. Design HashMap

> ⏱ **Time:** O(n) avg &nbsp;&nbsp; 💾 **Space:** O(n) or O(1) for fixed charset

---

## 6️⃣ Monotonic Stack

**Idea:** Maintain a stack that is always sorted (monotonically increasing or decreasing). Elements are popped when the monotonic property is violated, which is exactly when we find the "next greater/smaller" answer.

### When to use
- **Next Greater Element** (to the right or left)
- **Next Smaller Element**
- Nearest element satisfying a condition
- **Histogram area** problems
- Stock span / temperature waiting time

### Common Patterns
1. **Decreasing Stack** — pop when `current > top` → finds Next Greater Element
2. **Increasing Stack** — pop when `current < top` → finds Next Smaller Element
3. **Store indices**, not values — enables distance/area calculation

### Java Template

```java
Stack<Integer> stack = new Stack<>();  // stores indices

for (int i = 0; i < nums.length; i++) {
    // Pop while stack top is smaller than current element
    while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
        int idx = stack.pop();
        // nums[i] is the Next Greater Element for nums[idx]
        result[idx] = nums[i];
    }
    stack.push(i);  // always push index, not value
}
```

### Example Problems
- 496. Next Greater Element I
- 503. Next Greater Element II
- 739. Daily Temperatures
- 84. Largest Rectangle in Histogram
- 853. Car Fleet
- 907. Sum of Subarray Minimums

> ⏱ **Time:** O(n) — each element pushed/popped once &nbsp;&nbsp; 💾 **Space:** O(n)

---

## 7️⃣ Binary Search

**Idea:** Divide the search space in half at every step. Reduces time from O(n) to O(log n). Requires the search space to be sorted or have a **monotonic property**.

### When to use
- Input array is **sorted** or monotonic
- Find a **target value**, insertion point, or boundary
- **Search space** problems — binary search on the answer, not the array

### Common Patterns
1. **Standard** — find exact target in sorted array
2. **Left Boundary** — find first occurrence (bias left)
3. **Right Boundary** — find last occurrence (bias right)
4. **Binary Search on Answer** — search on result range, check feasibility with `feasible(mid)`

### Java Template

```java
// Standard Binary Search
int left = 0, right = nums.length - 1;

while (left <= right) {
    int mid = left + (right - left) / 2; // Prevent overflow vs (left+right)/2

    if (nums[mid] == target) {
        return mid;
    } else if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
return -1;

// ── Binary Search on Answer ──
int lo = minVal, hi = maxVal, ans = -1;
while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (feasible(mid)) {
        ans = mid;
        lo = mid + 1;  // try higher (maximize)
    } else {
        hi = mid - 1;
    }
}
```

### Example Problems
- 69. Sqrt(x)
- 153. Find Minimum in Rotated Sorted Array
- 162. Find Peak Element
- 704. Binary Search
- 875. Koko Eating Bananas ← Binary Search on Answer
- 1011. Capacity To Ship Packages ← Binary Search on Answer
- 410. Split Array Largest Sum ← Binary Search on Answer

> ⏱ **Time:** O(log n) &nbsp;&nbsp; 💾 **Space:** O(1)

---

## 8️⃣ Depth First Search (DFS)

**Idea:** Explore as far as possible along each branch before backtracking. Uses a **Stack** (explicit or via recursion call stack).

### When to use
- Traversing **Trees** (Preorder, Inorder, Postorder)
- **Graph** problems: connected components, path finding, cycle detection
- **Backtracking** problems (permutations, subsets, N-Queens)
- **Island** problems on 2D grids

### Common Patterns
1. **Recursive** (implicit stack) — cleaner, risks stack overflow on very deep trees
2. **Iterative** (explicit stack) — safer for deep inputs
3. **Preorder**: process → left → right
4. **Inorder**: left → process → right ← gives sorted order for BST
5. **Postorder**: left → right → process

### Java Template

```java
// Tree DFS (Recursive)
void dfs(TreeNode node) {
    if (node == null) return;

    // Preorder: process node here
    dfs(node.left);
    // Inorder: process node here
    dfs(node.right);
    // Postorder: process node here
}

// Graph DFS (with visited array)
void dfs(int node, boolean[] visited, List<List<Integer>> adj) {
    visited[node] = true;
    for (int neighbor : adj.get(node)) {
        if (!visited[neighbor])
            dfs(neighbor, visited, adj);
    }
}

// Grid DFS (Number of Islands)
void dfs(char[][] grid, int r, int c) {
    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length
            || grid[r][c] == '0') return;
    grid[r][c] = '0'; // mark visited
    dfs(grid, r+1, c); dfs(grid, r-1, c);
    dfs(grid, r, c+1); dfs(grid, r, c-1);
}
```

### Example Problems
- 104. Maximum Depth of Binary Tree
- 200. Number of Islands
- 543. Diameter of Binary Tree
- 695. Max Area of Island
- 236. Lowest Common Ancestor of a Binary Tree
- 1650. Lowest Common Ancestor of a Binary Tree III

> ⏱ **Time:** O(V + E) &nbsp;&nbsp; 💾 **Space:** O(V) — recursion stack

---

## 9️⃣ Breadth First Search (BFS)

**Idea:** Explore all neighbor nodes at the current level before moving to the next level. Uses a **Queue** (FIFO).

### When to use
- **Shortest path** in unweighted graphs
- **Level order traversal** of a tree
- Finding all nodes at **distance k**
- **Multi-source BFS** (walls and gates, rotting oranges)

### Common Patterns
1. Capture `queue.size()` before inner loop to process **one level at a time**
2. **Multi-source BFS** — add ALL sources to queue before starting
3. Use `visited` set or modify grid in-place to avoid revisits

### Java Template

```java
// Level Order BFS (Tree)
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);

while (!queue.isEmpty()) {
    int size = queue.size(); // number of nodes at current level
    for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        // Process node
        if (node.left  != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
    // All nodes at this level processed
}

// Graph BFS (Shortest Path)
Queue<Integer> q = new LinkedList<>();
boolean[] visited = new boolean[n];
q.offer(start);
visited[start] = true;
int steps = 0;

while (!q.isEmpty()) {
    int size = q.size();
    for (int i = 0; i < size; i++) {
        int node = q.poll();
        if (node == target) return steps;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                q.offer(neighbor);
            }
        }
    }
    steps++;
}
```

### Example Problems
- 102. Binary Tree Level Order Traversal
- 199. Binary Tree Right Side View
- 286. Walls and Gates
- 1091. Shortest Path in Binary Matrix

> ⏱ **Time:** O(V + E) &nbsp;&nbsp; 💾 **Space:** O(V) — queue width

---

## 🔟 Heap / Priority Queue

**Idea:** A heap maintains the min (or max) element at the top in O(1), with O(log n) insert and delete. Java's `PriorityQueue` is a **min heap** by default.

### When to use
- **Top K** largest / smallest elements
- **Streaming data** — maintain running min or max
- **Merge K sorted lists**
- **Find Median** from data stream (two heaps)

### Common Patterns
1. **Min Heap** (default) — `PriorityQueue<>()` — top = smallest
2. **Max Heap** — `PriorityQueue<>(Collections.reverseOrder())` — top = largest
3. **Top K Largest** — min heap of size K (pop when size > K)
4. **Top K Smallest** — max heap of size K

### Java Template

```java
// Min Heap (default)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// Max Heap
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

// Custom comparator (by frequency)
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

// Top K Largest — Min Heap of size K
PriorityQueue<Integer> pq = new PriorityQueue<>();
for (int num : nums) {
    pq.offer(num);
    if (pq.size() > k) pq.poll(); // remove smallest
}
// pq.peek() = Kth largest
```

### Example Problems
- 215. Kth Largest Element in an Array
- 295. Find Median from Data Stream
- 347. Top K Frequent Elements
- 23. Merge K Sorted Lists
- 703. Kth Largest Element in a Stream

> ⏱ **Time:** O(n log k) &nbsp;&nbsp; 💾 **Space:** O(k)

---

## 1️⃣1️⃣ Backtracking

**Idea:** Build candidates for the solution incrementally and **abandon** a candidate ("backtrack") as soon as it cannot lead to a valid solution.

### When to use
- Generate all **combinations**, **permutations**, or **subsets**
- Solving puzzles like **Sudoku** or **N-Queens**
- Search through possibilities with **pruning**
- Constrained path problems

### Common Patterns
1. **Choose → Explore → Unchoose** — the 3-step pattern
2. **Pruning** — skip invalid branches early to improve performance
3. Use `start` index to avoid revisiting for **combinations**
4. Use `visited[]` array for **permutations**

### Java Template

```java
void backtrack(int start, List<Integer> path,
               List<List<Integer>> result, int[] nums) {
    // Base case: valid solution found
    result.add(new ArrayList<>(path));

    for (int i = start; i < nums.length; i++) {
        path.add(nums[i]);                       // Choose
        backtrack(i + 1, path, result, nums);   // Explore
        path.remove(path.size() - 1);            // Unchoose (Backtrack)
    }
}
```

### Example Problems
- 39. Combination Sum
- 46. Permutations
- 78. Subsets
- 22. Generate Parentheses
- 131. Palindrome Partitioning
- 51. N-Queens

> ⏱ **Time:** O(2^n) combinations, O(n!) permutations &nbsp;&nbsp; 💾 **Space:** O(n) — recursion depth

---

## 1️⃣2️⃣ Union Find (Disjoint Set Union)

**Idea:** Efficiently track which elements belong to the same connected component. Supports **union** (merge two components) and **find** (which component does this element belong to).

### When to use
- **Connected components** in a graph
- **Cycle detection** in undirected graphs
- **Dynamic connectivity** — merging groups at runtime
- **Minimum Spanning Tree** — Kruskal's Algorithm

### Common Patterns
1. **Path Compression** — flatten tree during `find()` for near O(1)
2. **Union by Rank** — always attach smaller tree under larger
3. Combined: nearly **O(α(n)) ≈ O(1)** amortized per operation

### Java Template

```java
int[] parent, rank;

void init(int n) {
    parent = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; i++) parent[i] = i;
}

int find(int x) {
    if (parent[x] != x)
        parent[x] = find(parent[x]); // Path compression
    return parent[x];
}

boolean union(int x, int y) {
    int px = find(x), py = find(y);
    if (px == py) return false;       // Already connected — cycle!
    if (rank[px] < rank[py]) { int t = px; px = py; py = t; }
    parent[py] = px;                  // Union by rank
    if (rank[px] == rank[py]) rank[px]++;
    return true;
}
```

### Example Problems
- 547. Number of Provinces
- 684. Redundant Connection
- 721. Accounts Merge
- 261. Graph Valid Tree
- 323. Number of Connected Components
- 1584. Min Cost to Connect All Points ← Kruskal's MST

> ⏱ **Time:** O(α(n)) ≈ O(1) amortized &nbsp;&nbsp; 💾 **Space:** O(n)

---

## 1️⃣3️⃣ Dynamic Programming (DP)

**Idea:** Break a complex problem into simpler overlapping subproblems, solve each once, and store results to avoid recomputation.

### When to use
- **Optimization** problems (min/max value)
- Counting the number of **ways** to do something
- Problem has **overlapping subproblems** and **optimal substructure**
- Any recursion with repeated subproblems → add memoization

### Steps
1. **State** — define what `dp[i]` (or `dp[i][j]`) represents
2. **Transition** — find the recurrence relation (e.g., `dp[i] = dp[i-1] + dp[i-2]`)
3. **Base Case** — initialize `dp[0]`, `dp[1]`, etc.
4. **Result** — identify which dp value holds the final answer

### Common Patterns
1. **Top-Down** — recursion + memoization (`memo[]`)
2. **Bottom-Up** — iterative tabulation (usually more space-efficient)
3. **Space Optimization** — reduce 2D → 1D when only previous row needed

### Java Template

```java
// 1D DP — Climbing Stairs
int[] dp = new int[n + 1];
dp[0] = 1;  // base case
dp[1] = 1;  // base case
for (int i = 2; i <= n; i++)
    dp[i] = dp[i - 1] + dp[i - 2];

// 2D DP — Unique Paths
int[][] dp = new int[m][n];
for (int i = 0; i < m; i++) dp[i][0] = 1;
for (int j = 0; j < n; j++) dp[0][j] = 1;
for (int i = 1; i < m; i++)
    for (int j = 1; j < n; j++)
        dp[i][j] = dp[i-1][j] + dp[i][j-1];

// Top-Down (Memoization)
int[] memo = new int[n + 1];
Arrays.fill(memo, -1);
int solve(int n) {
    if (n <= 1) return n;
    if (memo[n] != -1) return memo[n];
    return memo[n] = solve(n - 1) + solve(n - 2);
}
```

### Example Problems
- 70. Climbing Stairs
- 198. House Robber
- 300. Longest Increasing Subsequence
- 322. Coin Change
- 62. Unique Paths
- 1143. Longest Common Subsequence
- 3129. Stable Binary Arrays I ← DP + Prefix Sum
- 3130. Stable Binary Arrays II ← DP + Prefix Sum

> ⏱ **Time:** O(n) to O(n²) &nbsp;&nbsp; 💾 **Space:** O(n) to O(n²), often reducible

---

## 1️⃣4️⃣ Bit Manipulation

**Idea:** Manipulate bits directly using bitwise operators. Often provides O(1) solutions for problems that seem to require O(n).

### When to use
- **XOR tricks** for finding unique elements
- **Check, set, or clear** specific bits
- **Power of 2** checks
- **Counting set bits**
- **Complement** / inverse of a binary number

### Common Tricks

| Operation | Code | Notes |
|-----------|------|-------|
| `x ^ x` | `= 0` | Same values cancel |
| `x ^ 0` | `= x` | XOR with 0 is identity |
| Check bit k | `(n & (1 << k)) != 0` | Is bit k set? |
| Set bit k | `n \| (1 << k)` | Turn bit k on |
| Clear bit k | `n & ~(1 << k)` | Turn bit k off |
| Clear lowest set bit | `n & (n - 1)` | Useful for counting bits |
| Isolate lowest set bit | `n & (-n)` | |
| Power of 2 check | `n > 0 && (n & (n-1)) == 0` | |
| Unsigned right shift | `n >>> k` | Fills with 0s (vs `>>` which sign-extends) |

### Java Template

```java
// XOR — find single number (all others appear twice)
int result = 0;
for (int num : nums) result ^= num;

// Count set bits (Brian Kernighan's Algorithm)
int count = 0;
while (n != 0) {
    n &= (n - 1); // clear lowest set bit
    count++;
}

// Build bit mask for complement
int mask = 0, temp = n;
while (temp > 0) {
    mask = (mask << 1) | 1;
    temp >>= 1;
}
int complement = n ^ mask;
```

### Example Problems
- 136. Single Number ← XOR
- 191. Number of 1 Bits
- 231. Power of Two
- 268. Missing Number ← XOR
- 338. Counting Bits ← DP + Bits
- 371. Sum of Two Integers ← no `+` operator
- 461. Hamming Distance ← XOR + popcount
- 1009. Complement of Base 10 Integer ← Bit Mask

> ⏱ **Time:** O(1) to O(n) &nbsp;&nbsp; 💾 **Space:** O(1)

---

## 📊 Complexity Cheat Sheet

| Algorithm / Pattern | Avg Time | Worst Time | Space |
|---------------------|----------|------------|-------|
| Array Access / Modify | O(1) | O(1) | O(1) |
| Array Search (linear) | O(n) | O(n) | O(1) |
| Binary Search | O(log n) | O(log n) | O(1) |
| Two Pointers | O(n) | O(n) | O(1) |
| Sliding Window | O(n) | O(n) | O(k) |
| Prefix Sum (build) | O(n) | O(n) | O(n) |
| HashMap Get / Put | O(1) avg | O(n) | O(n) |
| HashSet Contains / Add | O(1) avg | O(n) | O(n) |
| Sorting (Merge / Quick) | O(n log n) | O(n log n) | O(n) |
| Heap Insert / Poll | O(log n) | O(log n) | O(n) |
| Linked List Access | O(n) | O(n) | O(1) |
| Stack / Queue Op | O(1) | O(1) | O(n) |
| Monotonic Stack | O(n) | O(n) | O(n) |
| DFS / BFS (Graph) | O(V + E) | O(V + E) | O(V) |
| Union Find (optimized) | O(α(n)) | O(log n) | O(n) |
| DP (1D) | O(n) | O(n) | O(n) |
| DP (2D) | O(n²) | O(n²) | O(n²) |
| Backtracking | O(2^n) | O(n!) | O(n) |

---

## 🔗 Pattern Combinations

> Most interview problems are **combinations** of 2 or more patterns. Recognizing the combination is the key skill.

| Problem | Patterns Combined |
|---------|------------------|
| 560. Subarray Sum Equals K | Prefix Sum + HashMap |
| 347. Top K Frequent Elements | HashMap + Heap |
| 739. Daily Temperatures | Monotonic Stack |
| 200. Number of Islands | DFS / BFS on 2D Grid |
| 128. Longest Consecutive Sequence | HashSet (start of sequence only) |
| 138. Copy List with Random Pointer | HashMap (original → copy) |
| 76. Minimum Window Substring | Sliding Window + HashMap |
| 437. Path Sum III | DFS + Prefix Sum + HashMap |
| 525. Contiguous Array | Prefix Sum + HashMap |
| 23. Merge K Sorted Lists | Heap + Linked List |
| 1584. Min Cost to Connect All Points | Union Find / Prim's (MST) |
| 1091. Shortest Path in Binary Matrix | BFS on 2D Grid |

---

## 🧠 Problem-Solving Decision Framework

### Step 1 — Clarify
- What are the constraints? (size, value range, sorted?)
- Edge cases: empty, null, negatives, duplicates, single element
- Can we modify the input? What should we return?

### Step 2 — Pattern Recognition

```
Is it a subarray / substring problem?
  → Sliding Window or Prefix Sum

Is it sorted?
  → Two Pointers or Binary Search

Need next greater / smaller element?
  → Monotonic Stack

Tree or Graph traversal?
  → DFS (deep path) or BFS (shortest path / levels)

Shortest path in unweighted graph?
  → BFS

Top K elements?
  → Heap / Priority Queue

Generate all combinations / permutations?
  → Backtracking

Connected components or cycle detection?
  → Union Find or DFS

Overlapping subproblems / optimization?
  → Dynamic Programming

XOR / bit properties?
  → Bit Manipulation

O(1) lookup / frequency count?
  → HashMap / HashSet

Linked list cycle / middle?
  → Fast & Slow Pointers
```

### Step 3 — Strategy
1. State the **brute force** first (shows you understand the problem)
2. Identify the **bottleneck** (nested loops = O(n²) → look for O(n) pattern)
3. Apply the appropriate **pattern** to optimize
4. State **Time and Space complexity** before coding

### Step 4 — Code
- Handle **edge cases** first (null, empty, single element)
- Use **meaningful variable names** (`left/right` not `i/j` for two pointers)
- Write **modular code** with helper functions for clarity

### Step 5 — Test & Analyze
- Dry run with the **given example**
- Test **edge cases** manually
- State final **Time O(?) and Space O(?)** complexity

---

*Last updated: March 2026 | github.com/mhnuk2007 | leetcode.com/mhnuk2007*