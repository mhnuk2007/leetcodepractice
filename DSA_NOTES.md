# DSA Notes (Java)

This document summarizes core patterns, techniques, and Java templates used for common data structures and algorithms problems.

## 1. Two Pointers
Idea
- Use two indices that move toward each other or in the same direction.

Use when
- Array is sorted
- You need pairs or ranges
- You need palindrome checks

Example problems
- Two Sum (sorted)
- Container With Most Water
- 3Sum
- Valid Palindrome
- Remove Duplicates from Sorted Array

Java template
```java
int left = 0;
int right = arr.length - 1;

while (left < right) {
    if (condition) {
        left++;
    } else {
        right--;
    }
}
```

## 2. Sliding Window
Idea
- Expand and shrink a window to avoid recomputing subarrays.

Use when
- Subarray or substring problems
- You need max/min over a range

Example problems
- Longest Substring Without Repeating Characters
- Minimum Window Substring
- Maximum Sum Subarray

Java template
```java
int left = 0;
for (int right = 0; right < arr.length; right++) {
    // expand window

    while (windowInvalid) {
        left++; // shrink window
    }

    // update answer
}
```

## 3. Fast and Slow Pointer (Floyd)
Idea
- Two pointers move at different speeds.

Use when
- Cycle detection
- Find middle node
- Duplicate detection

Example problems
- Linked List Cycle
- Find Duplicate Number
- Middle of Linked List
- Happy Number

Java template
```java
ListNode slow = head;
ListNode fast = head;

while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {
        // cycle detected
        break;
    }
}
```

## 4. Binary Search
Idea
- Works on sorted arrays or monotonic search space.

Time complexity
- O(log n)

Java template
```java
int left = 0;
int right = nums.length - 1;

while (left <= right) {
    int mid = left + (right - left) / 2;

    if (nums[mid] == target) {
        return mid;
    }

    if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
```

## 5. DFS (Depth First Search)
Use when
- Trees
- Graphs
- Backtracking

Java recursive template
```java
void dfs(TreeNode node) {
    if (node == null) return;

    dfs(node.left);
    dfs(node.right);
}
```

## 6. BFS (Breadth First Search)
Use when
- Level order traversal
- Shortest path in unweighted graph

Java template
```java
Queue<TreeNode> queue = new LinkedList<>();
queue.offer(root);

while (!queue.isEmpty()) {
    TreeNode node = queue.poll();

    if (node.left != null) queue.offer(node.left);
    if (node.right != null) queue.offer(node.right);
}
```

## 7. Hashing
Use when
- You need O(1) lookups
- Counting frequencies

Example problems
- Two Sum
- Group Anagrams
- Contains Duplicate
- Top K Frequent Elements

Java template
```java
Map<Integer, Integer> map = new HashMap<>();
for (int num : nums) {
    map.put(num, map.getOrDefault(num, 0) + 1);
}
```

## 8. Monotonic Stack
Use when
- Next greater element
- Previous smaller element
- Temperature or histogram problems

Example problems
- Next Greater Element
- Daily Temperatures
- Largest Rectangle in Histogram

Java template
```java
Stack<Integer> stack = new Stack<>();

for (int i = 0; i < nums.length; i++) {
    while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
        stack.pop();
    }
    stack.push(i);
}
```

## 9. Dynamic Programming
Use when
- Overlapping subproblems
- Optimal substructure

DP steps
1. Define state
2. Define transition
3. Define base case
4. Compute result

Fibonacci example
```java
int[] dp = new int[n + 1];
dp[0] = 0;
dp[1] = 1;

for (int i = 2; i <= n; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}
```

## 10. Heap / Priority Queue
Use when
- Top K problems
- Streaming data
- Median problems

Java example
```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

## Complexity Cheat Sheet
| Complexity | Meaning |
| --- | --- |
| O(1) | Constant |
| O(log n) | Binary search |
| O(n) | Linear scan |
| O(n log n) | Sorting |
| O(n^2) | Nested loops |
| O(2^n) | Recursive branching |

## Problem Solving Strategy
1. Understand input and output.
2. Write a brute force approach.
3. Optimize using known patterns.
4. Write clean Java code.
5. Analyze time and space complexity.

## Revision Rule
- Revisit after 1 day
- Revisit after 1 week
- Revisit after 1 month

## Goal
- Recognize patterns quickly
- Write optimized Java solutions
- Solve most medium problems confidently

## Repository Mapping
Current folders in `src` and how they map to patterns:
- `arrays` and `arrays101`: core array problems and Arrays 101 practice
- `strings`: string manipulation and parsing
- `twopointer`: two-pointer patterns
- `slidingwindow`: window-based problems
- `linkedlist`: linked list fundamentals and variants
- `dp`: dynamic programming problems
- `sortalgrithms`: sorting algorithms and related problems
- `dailychallenges`: daily challenge exercises
