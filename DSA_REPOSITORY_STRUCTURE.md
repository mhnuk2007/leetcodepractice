# DSA Repository Structure (Java) — Updated July 2026

This document explains the folder organization used in this repository.
The goal is to keep solutions clean, organized, and easy to navigate by **algorithm pattern** and **problem type**.

---

## 📋 Repository Overview

```
LeetCodePractice/
├── .idea/                    [IntelliJ IDEA configuration - ignore for code review]
├── out/                      [Compiled class files - auto-generated]
├── src/                      [SOURCE CODE - Main directory]
├── Documentation (*.md)      [Reference guides and tracking]
├── LeetCodePractice.iml      [Project module configuration]
└── solvedproblems.txt        [List of completed problems]
```

**Total Statistics:**
- **Java Source Files**: 330+
- **LeetCode Problems Solved**: 180+
- **Topic Packages**: 23+
- **Subcategories**: 40+

---

## 📁 Source Code Directory (`src/`)

### 1. **Arrays** (22+ files · 5 subcategories)
Core array manipulation problems.

| Subcategory | Files | Content |
|-------------|-------|---------|
| `basics/` | 5 | PlusOne, ContainsDuplicate, PascalsTriangle (2 versions), RichestCustomerWealth |
| `advanced/` | 2 | KadaneAlgorithm, CountInversions |
| `greedy/` | 6 | BestTimeToBuyAndSellStock, MajorityElement, ArrayPartition, LargestNumberTwiceOthers, MaxProfit, MergeIntervals |
| `matrix/` | 4 | SpiralMatrix, SpecialPositionsInBinaryMatrix, SearchInRotatedSortedArray, SetMatrixZeroes |
| `prefix/` | 5 | FindPivotIndex, RunningSum1d, TrappingRainWater (2 versions), ProductOfArrayExceptSelf |
| `rotation/` | 4 | RotateArray, NextPermutation, CircularArrayLoop, RotateFunction |

**Key Concepts**: Array traversal, prefix sum, element rotation, greedy selection

---

### 2. **Arrays101** (24 files)
Beginner-friendly LeetCode Array Track — foundational problems for interview prep.

**Files**: CheckIfExists, CheckIfNAndItsDoubleExist, CountPairs, DuplicateZeros, EvenNoOfDigits, FindAllNumbersDisappearedInAnArray, FindDisappearedNumber, FindNumbersWithEvenNumberOfDigits, HeightChecker, MaxConsecutiveOnes, MergeSortedArray, MinChangesAlternatingBinaryString, MoveZeroes, MoveZeros, RemoveDuplicates, RemoveDuplicates2, RemoveDuplicatesFromSortedArrayII, RemoveElement, ReplaceElement, ReplaceElementsWithGreatestElementOnRightSide, SquaresOfASortedArray, SquaresOfSorted, ThirdMaximumNumber, ValidMountainArray

**Key Concepts**: Basic array operations, traversal, element manipulation

---

### 3. **Binary Search** (11 files · 3 subcategories)
Search algorithms and variants.

| Subcategory | Files | Content |
|-------------|-------|---------|
| `basics/` | 4 | BinarySearch, FirstBadVersion, SearchInsert, SqrtX |
| `rotated/` | 4 | FindMinRotatedArray, FindPeakElement, SearchRotatedSortedArray, SearchRotatedSortedArrayII |
| `advanced/` | 3 | FindKClosestElements, FirstAndLastPosition, SearchIn2DMatrix |
| (root) | 1 | GuessNumber |

**Key Concepts**: Binary search pattern, rotated array search, 2D matrix search

---

### 4. **Dynamic Programming** (29 files · 6 subcategories)
DP optimization strategies with memoization and tabulation.

| Subcategory | Files | Content |
|-------------|-------|---------|
| `linear/` | 9 | ClimbingStairsDpArray, ClimbingStairsDpMap, DeleteAndEarn, HouseRobberArray, HouseRobberMap, MaxScoreMulOps, MinCostClimbingStairs, Tribonacci (3 versions) |
| `knapsack/` | 6 | CoinChange (2 versions), Knapsack01, PartitionEqualSubSetSum, TargetSum, UnboundedKnapsack |
| `memoization/` | 5 | Fibonacci, FibDP (2 versions), TopDownSum, BottomUpSum |
| `optimization/` | 4 | FindAllPossibleStableArraysI, FindAllPossibleStableArraysII, UniqueBST, UniqueBSTII |
| `grid/` | 2 | MaximumPathScore, MultiStageGraph |
| `subsequence/` | 2 | LongestCommonSubsequence, LongestCommonSubstring |
| `digitdp/` | 1 | CountOnes |

**Key Concepts**: Memoization, tabulation, state transitions, overlapping subproblems

---

### 5. **Graph** (70+ files · 7 subcategories)
Graph algorithms covering traversal, shortest path, MST, and topological sorting.

| Subcategory | Files | Key Algorithms |
|-------------|-------|-----------------|
| `traversal/` | 15 | BFS, DFS, Flood Fill, Island problems, All Paths |
| `shortestpath/` | 8 | Dijkstra, Bellman-Ford, Weighted Graph, Network Delay |
| `mst/` | 8 | Kruskal, Prim, Spanning Tree, Min Cost Connect |
| `topological/` | 13 | Topological Sort (DFS/BFS), Kahn's Algorithm, Course Schedule |
| `cycles/` | 7 | Cycle Detection (Directed/Undirected), 2D Grid Cycles |
| `unionfind/` | 5 | Disjoint Set Union, DSU, Path Existence |
| `advanced/` | 5 | Clone Graph, Trie, WordSearchII, Tarjan Algorithm |

**Key Concepts**: DFS/BFS, shortest paths, MST, topological sort, cycle detection, DSU

---

### 6. **Strings** (25 files · 4 subcategories)
String manipulation and pattern matching.

| Subcategory | Files | Content |
|-------------|-------|---------|
| `basic/` | 10 | AddBinary, AddStrings, BinaryGap, JavaStringTokens, ReverseString, ReverseWordsInString (3 versions), RotatingString, SplitString |
| `pattern/` | 9 | Anagram, Palindrome (3 versions), ValidPalindrome, ValidAnagram, IsomorphicStrings, ValidWordAbbreviation, SmallestAndLargestSub |
| `advanced/` | 5 | FindIndexOfFirstOccurrence, LongestSubstringWithoutRepeatingCharacters, MinChangesAlternatingBinaryString, PartitionDeciBinary, PartitioningIntoMinDeciBinaryNumbers |
| `encoding/` | Reserved | [For encoding-specific problems] |

**Key Concepts**: String traversal, pattern matching, palindromes, anagrams

---

### 7. **Linked List** (14 files · 4 subcategories + root)
Linked list data structures and operations.

| Subcategory | Files | Content |
|-------------|-------|---------|
| `basic/` | 4 | DeleteNode, ListNode, MyLinkedList, SinglyLinkedList |
| `traversal/` | 3 | MaximumTwinSumOfLinkedList, ReverseLinkedList, Main |
| `operations/` | 4 | AddTwoNumbers, DesignLinkedList, MergeTwoSortedLists, RemoveNthNodeFromEnd |
| `cycles/` | 2 | LinkedListCycle, LinkedListCycleII |
| `advanced/` | 3 | LinkedListsIntersection, PalindromeLinkedList, SplitCircularLinkedList |
| (root) | 1 | Main |

**Key Concepts**: Node manipulation, cycle detection, list reversal, merging

---

### 8. **Tree** (13 files · 3 subcategories)
Binary tree traversals and operations.

| Subcategory | Files | Content |
|-------------|-------|---------|
| `basic/` | 5 | CountNodes, InvertBinaryTree, MaxDepth, MyBinaryTree, SameTree |
| `traversal/` | 5 | BinaryTreeInorder, BinaryTreePreorder, BinaryTreePostorder, LevelOrderTraversal, RightSideView |
| `advanced/` | 8 | BalancedBinaryTree, ConstructBinaryTree (2 versions), DiameterOfBinaryTree, LowestCommonAncestor (2 versions), PathSum (2 versions) |

**Key Concepts**: Tree traversal (inorder, preorder, postorder), LCA, path sum

---

### 9. **Heap** (13 files)
Priority queue and heap-based problems.

**Files**: KthFactorOfN, KthLargestElement, KWeakestRows, LastStoneWeight, Main, MaxHeap, MedianFromDataStream, MedianFromDataStreamOptimal, MergeKSortedLists, MinHeap, TopKFrequentElements, Twitter, UglyNumberII

**Key Concepts**: Min/Max heap, heap operations, priority queue, heap sort

---

### 10. **Recursion** (28 files)
Recursive problem solving strategies.

**Key Problems**: ClimbingStairs, Combinations, CombinationSum, FibonacciNumber, GenerateParenthesis, KthSymbolInGrammar, LetterCombinations, NQueens, NQueensII, PalindromePartitioning, Permutations, PowXN, QuickSort, RecursionIntro, RecursionSimulation, ReverseLinkedList, ReverseString, SearchA2DMatrixII, SearchInBST, StringPermutations, Subsets, SubsetsII, SudokuSolver (2 versions), SumOfNNums, SwapNodesInPairs, ValidateBST, WordSearch

**Key Concepts**: Backtracking, recursion trees, base cases, memoization

---

### 11. **Two Pointer** (11+ files)
Two-pointer technique problems.

**Files**: AppendCharacters, ContainerWithMostWater, FindTheDuplicateNumber, FourSum, HappyNumber, LengthOfLoop, MiddleOfLinkedList, StrobogrammaticNumber, ThreeSum, TwoSum, TwoSumII

**Key Concepts**: Two-pointer approach, opposite directions, collision detection

---

### 12. **Sliding Window** (9+ files)
Window-based substring/subarray problems.

**Files**: DynamicWindow, LongestSubstringWithoutRepeatingCharacters, MaximumAverageSubarrayI, MaxSumOfDistinctSubarray, MaxSumOfSubArray, MinimumWindowSubstring, MinSizeSubArraySum, RepeatedDNASequence, SlidingWindow

**Key Concepts**: Fixed/dynamic window, character frequency, optimal window

---

### 13. **Stack** (11 files)
Stack-based problems and applications.

**Files**: ArrayListStack, ArrayStack, DailyTemperatures, LinkedListStack, MinStack, MinStackLinkedList, NextGreaterElement, NextGreaterElementII, ReversePolishNotation, ValidParenthesis, ValidParenthesisMap

**Key Concepts**: Stack operations, monotonic stack, parenthesis matching

---

### 14. **Queue** (9 files)
Queue implementations and problems.

**Files**: ArrayListQueue, ArrayQueue, LinkedListQueue, MovingAverage, MyCircularQueue, MyCircularQueueArrayList, MyCircularQueueLinkedList, MyQueueUsingStacks, MyStackUsingQueue

**Key Concepts**: Queue operations, circular queue, queue vs stack

---

### 15. **Hash Map / Hashing** (5 files)
Hash-based problems and implementations.

**Files**: ContainsDuplicatesII, FirstUniqueCharacter, IntersectionOfArrays, IntersectionOfArraysII, MinimumIndexSum

**Key Concepts**: Hash map operations, frequency counting, duplicate detection

---

### 16. **Set** (2 files + subcategory)
Set-based problems.

- `designhashset/`: MyHashSet (custom hash set implementation)
- Root: SingleNumber

**Key Concepts**: Set operations, hash set implementation

---

### 17. **Map** (1+ files)

---

## ✅ Recently Verified Solution
- [src/sortalgorithms/CountSmallerAfterSelf.java](src/sortalgorithms/CountSmallerAfterSelf.java)
- Verified on 2026-07-17: the solution compiled successfully and produced the expected outputs for the sample test cases.
Custom map implementations.

- `designhashmap/`: MyHashMap (custom hash map implementation)

**Key Concepts**: Hash map design, collision resolution

---

### 18. **Sort Algorithms** (11 files)
Sorting algorithm implementations.

**Files**: BubbleSort, BubbleSortRec, InsertionSort, InsertionSortRec, MergeSort, MergeSortRec, PatternsViaRecursion, SelectionSort, SelectionSortRec, SortColors, TwoColorSort

**Key Concepts**: O(n²) and O(n log n) sorting, recursive implementations

---

### 19. **Additional Topics**

| Topic | Files | Purpose |
|-------|-------|---------|
| `interval/` | 1 | RemoveCoveredIntervals |
| `math/` | 4 | GCDOfOddEvenSums, RotatedDigits, SumOfGCDOfFormedPairs, UglyNumber |
| `patterns/` | 2 | FloydTriangle, FloydTriangleMathematically |
| `dailychallenges/` | 6 | CheckOneSegment, ComplementBase10Integer, FindKthBit, FindUniqueBinaryString, MinFlips, MinimumSwaps |
| `_sandbox/` | 2 | Main, Solution (testing/prototyping) |

---

## 📂 Generated & Configuration Directories

### `.idea/`
IntelliJ IDEA project configuration and settings.
- **Safe to ignore** for code review and version control
- Contains: workspace state, modules, VCS settings

### `out/`
Compiled Java class files (auto-generated).
- **Safe to delete** — will be regenerated on recompilation
- Structure mirrors `src/` organization
- Also contains `production/LeetCodePractice/` for production builds

### `LeetCodePractice.iml`
IntelliJ module file — project metadata and configuration.

---

## 📚 Documentation Files

| File | Purpose |
|------|---------|
| `README.md` | Main documentation with quick start & problem sets |
| `FOLDER_STRUCTURE_TREE.md` | **This file** — Detailed folder structure |
| `DSA_REPOSITORY_STRUCTURE.md` | Repository overview & explanation |
| `QUICK_REFERENCE.md` | Quick reference guide for file locations |
| `LEETCODE_SOLUTIONS_INDEX.md` | Complete index of all solutions |
| `DSA_TRACKER.md` | Progress tracking & completed problems |
| `DSA_NOTES.md` | Algorithm notes & patterns |
| `REORGANIZATION_SUMMARY.md` | Summary of recent reorganization |
| `assessment.md` | Assessment notes |
| `solvedproblems.txt` | Simple list of solved problem names |

---

## 🎯 How to Use This Repository

### For Quick Navigation
1. **Know the pattern?** Look up the pattern folder (e.g., `dp/knapsack/`, `graph/traversal/`)
2. **Know the topic?** Look up the topic folder (e.g., `arrays/`, `strings/`, `linkedlist/`)
3. **Looking for a specific problem?** Check `LEETCODE_SOLUTIONS_INDEX.md`

### For Learning
1. Start with **basics** subfolder if available
2. Progress to **advanced** variants
3. Mix with **patterns** from different categories to strengthen understanding

### For Interview Prep
1. Follow the **30-Day Interview Roadmap** in README.md
2. Use **Problem Sets** sections organized by difficulty & topic
3. Refer to **DSA_NOTES.md** for algorithm refreshers

---

## 🔄 Folder Organization Principles

This repository follows **pattern-based organization**:
- **By Topic**: Arrays, Strings, Trees, Graphs, etc.
- **By Algorithm**: Prefix Sum, Sliding Window, Binary Search, etc.
- **By Technique**: Two Pointer, Dynamic Programming, Recursion, etc.
- **By Difficulty**: Basics → Advanced within each category

This approach enables:
✅ Pattern recognition for interview problems
✅ Faster problem-solving during timed interviews
✅ Systematic skill building

---

## 📊 File Count Summary

| Category | Count |
|----------|-------|
| Arrays & Variants | 45 |
| Strings | 25 |
| Graph Algorithms | 73 |
| Dynamic Programming | 29 |
| Trees | 13 |
| Recursion & Backtracking | 28 |
| Data Structures (Stack, Queue, Heap, etc.) | 37 |
| Other Topics | 38 |
| **TOTAL** | **288+** |

---

## 🔗 Related Files

- Source code: `src/` directory
- Compiled output: `out/production/LeetCodePractice/`
- Project config: `LeetCodePractice.iml`
- Git ignore: `.gitignore`
- IDE config: `.idea/` directory

### Arrays101
LeetCode Arrays 101 practice.

Examples:
- RemoveDuplicates.java
- MergeSortedArray.java
- MoveZeroes.java
- SquaresOfASortedArray.java
- ValidMountainArray.java

### Strings
Problems related to:
- String manipulation
- Substring problems
- Palindrome checks

Examples:
- ValidPalindrome.java
- LongestSubstringWithoutRepeatingCharacters.java
- ValidAnagram.java
- ValidWordAbbreviation.java
- PartitioningIntoMinDeciBinaryNumbers.java

### Linked List
Linked list operations and pointer manipulation.

Examples:
- ReverseLinkedList.java
- RemoveNthNodeFromEnd.java
- LinkedListCycle.java
- MaximumTwinSumOfLinkedList.java
- MiddleOfLinkedList.java
- MergeTwoSortedLists.java
- LinkedListsIntersection.java

### Two Pointer
Two index pointer problems.

Examples:
- TwoSum.java
- TwoSumII.java
- ThreeSum.java
- ContainerWithMostWater.java
- FindTheDuplicateNumber.java

### Sliding Window
Subarray and substring problems.

Examples:
- MaxSlidingWindow.java
- SlidingWindow.java

### Dynamic Programming
DP exercises.

Examples:
- FindAllPossibleStableArraysI.java

### Sorting Algorithms
Sorting implementations and related problems.

Examples:
- BubbleSort.java
- SortColors.java
- TwoColorSort.java

### Daily Challenges
Daily challenge solutions and experiments.

Examples:
- CheckOneSegment.java
- FindKthBit.java
- FindUniqueBinaryString.java
- MinFlips.java
- MinimumSwaps.java

### Patterns
Pattern printing and related exercises.

Examples:
- FloydTriangle.java
- FloydTriangleMathematically.java

### Root src files
The `src` root also contains a few standalone or legacy files such as:
- Main.java
- Solution.java
- MaxProfit.java
- RotateArray.java
- FirstOccuranceInAString.java

## Naming Convention
File naming style:
- ProblemName.java

Examples:
- TwoSum.java
- ReverseLinkedList.java
- MaximumTwinSumOfLinkedList.java

## Repository Goals
- Solve 200+ DSA problems
- Master 15 core patterns
- Build strong problem-solving skills
- Prepare for technical interviews

## Tips for Maintaining This Repo
- Write clean code
- Add problem number in comments
- Add time complexity
- Add space complexity
- Write a short explanation

Example:
```java
/*
LeetCode 206
Reverse Linked List

Time Complexity: O(n)
Space Complexity: O(1)
*/
```
