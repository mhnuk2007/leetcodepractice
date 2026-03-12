# 📘 Data Structures & Algorithms Notes (Java)

This document serves as a comprehensive reference for core patterns, techniques, and Java templates used in solving DSA problems.

---

## 1️⃣ Two Pointers
**Idea:** Use two indices that traverse the data structure (usually an array or string) from different directions or at different speeds to save space or time.

**When to use:**
- Input array is **sorted** (e.g., Two Sum II, 3Sum).
- You need to find **pairs** or triplets that satisfy a condition.
- **Palindrome** checks (e.g., Valid Palindrome).
- Removing duplicates or elements in-place (e.g., Remove Duplicates).
- Merging two sorted arrays.

**Common Patterns:**
1.  **Opposite Direction:** `left = 0`, `right = n - 1`. Move towards center.
2.  **Same Direction:** `fast` and `slow` (often for removing elements).

**Java Template (Opposite Direction):**
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

---

## 2️⃣ Sliding Window
**Idea:** Convert nested loops (O(n²)) into a single loop (O(n)) by maintaining a "window" of elements that satisfies a certain condition.

**When to use:**
- **Subarray** or **substring** problems.
- Finding the **longest** or **shortest** subarray with a specific property.
- Calculating a sum or product within a window of size `k`.

**Java Template:**
```java
int left = 0;
int currentSum = 0;
int ans = 0;

for (int right = 0; right < arr.length; right++) {
    // 1. Add current element to window
    currentSum += arr[right];

    // 2. Shrink window while condition is invalid
    while (currentSum > target) {
        currentSum -= arr[left];
        left++;
    }

    // 3. Update answer (window is valid here)
    ans = Math.max(ans, right - left + 1);
}
```

---

## 3️⃣ Fast & Slow Pointers (Floyd’s Cycle Detection)
**Idea:** Use two pointers moving at different speeds (usually `1x` and `2x`).

**When to use:**
- **Cycle detection** in Linked Lists or Arrays (e.g., Linked List Cycle, Find Duplicate Number).
- Finding the **middle** of a Linked List.
- Determining if a Linked List is a **palindrome**.

**Java Template:**
```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;         // Move 1 step
    fast = fast.next.next;    // Move 2 steps

    if (slow == fast) {
        return true; // Cycle detected
    }
}
return false;
```

---

## 4️⃣ Binary Search
**Idea:** Divide the search space in half at every step. This reduces time complexity from O(n) to O(log n).

**When to use:**
- Input array is **sorted** or has a monotonic property.
- You need to find a target value, an insertion point, or a boundary.
- **Search space** problems (e.g., Koko Eating Bananas).

**Java Template:**
```java
int left = 0;
int right = nums.length - 1;

while (left <= right) {
    int mid = left + (right - left) / 2; // Prevent overflow

    if (nums[mid] == target) {
        return mid;
    } else if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
```

---

## 5️⃣ Hashing (HashMap / HashSet)
**Idea:** Use a Hash Table to store data for O(1) average time complexity lookups.

**When to use:**
- Checking for **existence** or duplicates (HashSet).
- Counting **frequencies** of elements (HashMap).
- Looking up values in O(1) time (e.g., Two Sum).

**Java Template (Frequency Map):**
```java
Map<Integer, Integer> map = new HashMap<>();
for (int num : nums) {
    map.put(num, map.getOrDefault(num, 0) + 1);
}
```

---

## 6️⃣ Depth First Search (DFS)
**Idea:** Explore as deep as possible along each branch before backtracking. Uses a **Stack** (explicit or via recursion).

**When to use:**
- Traversing **Trees** (Preorder, Inorder, Postorder).
- Graph problems: Connected components, path finding, cycle detection.
- **Backtracking** problems (Permutations, Subsets).

**Java Template (Recursive - Tree):**
```java
void dfs(TreeNode node) {
    if (node == null) return;

    // Process node
    dfs(node.left);
    dfs(node.right);
}
```

---

## 7️⃣ Breadth First Search (BFS)
**Idea:** Explore neighbor nodes first before moving to the next level neighbors. Uses a **Queue**.

**When to use:**
- **Shortest path** in unweighted graphs.
- **Level order traversal** of a tree.
- Finding all nodes at distance `k`.

**Java Template (Level Order - Tree):**
```java
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);

while (!queue.isEmpty()) {
    int size = queue.size(); // Nodes at current level
    for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        // Process node
        if (node.left != null) queue.offer(node.left);
        if (node.right != null) queue.offer(node.right);
    }
}
```

---

## 8️⃣ Backtracking
**Idea:** Build candidates for the solution incrementally and abandon a candidate ("backtrack") as soon as it determines the candidate cannot lead to a valid solution.

**When to use:**
- Generating all **combinations**, **permutations**, or **subsets**.
- Solving puzzles like **Sudoku** or **N-Queens**.

**Java Template:**
```java
void backtrack(int start, List<Integer> current, List<List<Integer>> result) {
    // Base case: solution found
    result.add(new ArrayList<>(current));

    for (int i = start; i < nums.length; i++) {
        current.add(nums[i]);      // Choose
        backtrack(i + 1, current, result); // Explore
        current.remove(current.size() - 1); // Un-choose (Backtrack)
    }
}
```

---

## 9️⃣ Dynamic Programming (DP)
**Idea:** Break a complex problem into simpler subproblems, solve each subproblem once, and store the result.

**When to use:**
- **Optimization** problems (min/max).
- Determining the number of **ways** to do something.
- Problem has **overlapping subproblems** and **optimal substructure**.

**Steps:**
1.  **State:** Define `dp[i]`.
2.  **Transition:** Find the recurrence relation (e.g., `dp[i] = dp[i-1] + dp[i-2]`).
3.  **Base Case:** Initialization (e.g., `dp[0] = 0`).
4.  **Result:** What value holds the final answer?

---

## 🔟 Bit Manipulation
**Idea:** Manipulate bits directly using operators like `&`, `|`, `^`, `~`, `<<`, `>>`.

**Common Tricks:**
- **XOR (`^`):** `x ^ x = 0`, `x ^ 0 = x`. Useful for finding the unique number.
- **Check if bit k is set:** `(n & (1 << k)) != 0`
- **Set bit k:** `n | (1 << k)`
- **Clear bit k:** `n & ~(1 << k)`
- **Check power of 2:** `(n & (n - 1)) == 0`

---

## 📊 Complexity Cheat Sheet

| Data Structure / Algo | Average Time | Worst Time | Space |
| :--- | :--- | :--- | :--- |
| **Array Access** | O(1) | O(1) | O(1) |
| **Array Search** | O(n) | O(n) | O(1) |
| **Binary Search** | O(log n) | O(log n) | O(1) |
| **Linked List Access**| O(n) | O(n) | O(1) |
| **Stack/Queue Op** | O(1) | O(1) | O(n) |
| **HashMap Get/Put** | O(1) | O(n) | O(n) |
| **Sorting (Merge/Quick)**| O(n log n) | O(n log n) | O(n) |
| **DFS / BFS (Graph)** | O(V + E) | O(V + E) | O(V) |

---

## 🧠 Problem Solving Framework
1.  **Clarify:** Ask questions. Understand constraints. Check for edge cases (empty, null, negatives).
2.  **Example:** Walk through a simple test case manually.
3.  **Brute Force:** State the naive solution first (e.g., "We could use nested loops...").
4.  **Optimize:** Identify the bottleneck (usually time complexity). Apply a pattern (Two Pointers, Hashing, etc.).
5.  **Code:** Write clean, modular code. Use meaningful variable names.
6.  **Test:** Dry run your code with the example case.
7.  **Analyze:** State the final Time and Space complexity.
