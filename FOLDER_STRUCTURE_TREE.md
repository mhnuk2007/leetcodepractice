# 📂 FOLDER STRUCTURE - COMPLETE TREE (July 2026)

> Updated: July 17, 2026
> System: Windows 10 (Version 10.0.22000.2538)

```
D:\leetcodepractice\
│
├─── 📁 .idea/                          [IntelliJ IDEA Configuration]
│    ├─ .gitignore
│    ├─ misc.xml
│    ├─ modules.xml
│    ├─ vcs.xml
│    └─ workspace.xml
│
├─── 📁 out/                            [Compiled Output Directory]
│    ├─ (All .class files - Auto-generated)
│    └─ production/LeetCodePractice/    [Production build output]
│
├─── 📁 src/                            [SOURCE CODE - MAIN]
│    │
│    ├─── 📁 _sandbox/                  [Testing & Prototyping]
│    │    ├─ Main.java
│    │    └─ Solution.java
│    │
│    ├─── 📁 arrays/                    [5 subcategories · 22+ files]
│    │    ├─ basics/
│    │    │  └─ (5) PlusOne, ContainsDuplicate, PascalsTriangle, PascalsTriangleII, RichestCustomerWealth
│    │    ├─ advanced/
│    │    │  └─ (2) KadaneAlgorithm, CountInversions
│    │    ├─ greedy/
│    │    │  └─ (6) BestTimeToBuyAndSellStock, MajorityElement, ArrayPartition, LargestNumberTwiceOthers, MaxProfit, MergeIntervals
│    │    ├─ matrix/
│    │    │  └─ (4) SpiralMatrix, SpecialPositionsInBinaryMatrix, SearchInRotatedSortedArray, SetMatrixZeroes
│    │    ├─ prefix/
│    │    │  └─ (5) FindPivotIndex, RunningSum1d, TrappingRainWater, TrappingRainWaterPrefix, ProductOfArrayExceptSelf
│    │    └─ rotation/
│    │       └─ (4) RotateArray, NextPermutation, CircularArrayLoop, RotateFunction
│    │
│    ├─── 📁 arrays101/                 [Beginner LeetCode Track · 24+ files]
│    │    └─ CheckIfExists, CheckIfNAndItsDoubleExist, CountPairs, DuplicateZeros, EvenNoOfDigits,
│    │       FindAllNumbersDisappearedInAnArray, FindDisappearedNumber, FindNumbersWithEvenNumberOfDigits,
│    │       HeightChecker, MaxConsecutiveOnes, MergeSortedArray, MinChangesAlternatingBinaryString,
│    │       MoveZeroes, MoveZeros, RemoveDuplicates, RemoveDuplicates2, RemoveDuplicatesFromSortedArrayII,
│    │       RemoveElement, ReplaceElement, ReplaceElementsWithGreatestElementOnRightSide,
│    │       SquaresOfASortedArray, SquaresOfSorted, ThirdMaximumNumber, ValidMountainArray
│    │
│    ├─── 📁 binarysearch/              [3 subcategories · 11+ files]
│    │    ├─ basics/
│    │    │  └─ (4) BinarySearch, FirstBadVersion, SearchInsert, SqrtX
│    │    ├─ advanced/
│    │    │  └─ (3) FindKClosestElements, FirstAndLastPosition, SearchIn2DMatrix
│    │    ├─ rotated/
│    │    │  └─ (4) FindMinRotatedArray, FindPeakElement, SearchRotatedSortedArray, SearchRotatedSortedArrayII
│    │    └─ (root)
│    │       └─ (1) GuessNumber
│    │
│    ├─── 📁 dailychallenges/           [6 files]
│    │    └─ CheckOneSegment, ComplementBase10Integer, FindKthBit, FindUniqueBinaryString, MinFlips, MinimumSwaps
│    │
│    ├─── 📁 dp/                        [6 subcategories · 29+ files]
│    │    ├─ digitdp/
│    │    │  └─ (1) CountOnes
│    │    ├─ grid/
│    │    │  └─ (2) MaximumPathScore, MultiStageGraph
│    │    ├─ knapsack/
│    │    │  └─ (6) CoinChange, CoinChangeII, Knapsack01, PartitionEqualSubSetSum, TargetSum, UnboundedKnapsack
│    │    ├─ linear/
│    │    │  └─ (9) ClimbingStairsDpArray, ClimbingStairsDpMap, DeleteAndEarn, HouseRobberArray, HouseRobberMap,
│    │    │         MaxScoreMulOps, MinCostClimbingStairs, Tribonacci, TribonacciBottomUp, TribonacciTopDown
│    │    ├─ memoization/
│    │    │  └─ (5) BottomUpSum, FibDP, FibDP2, Fibonacci, TopDownSum
│    │    ├─ optimization/
│    │    │  └─ (4) FindAllPossibleStableArraysI, FindAllPossibleStableArraysII, UniqueBST, UniqueBSTII
│    │    └─ subsequence/
│    │       └─ (2) LongestCommonSubsequence, LongestCommonSubstring
│    │
│    ├─── 📁 graph/                     [7 subcategories · 70+ files]
│    │    ├─ advanced/
│    │    │  └─ (5) CloneGraph, Graph, TarjanAlgorithm, Trie, WordSearchII
│    │    ├─ cycles/
│    │    │  └─ (7) CriticalConInANet, CycleDetectionDirectedGraph, CycleDetectionUndirectedGraph,
│    │    │         DetectCycleBFS, DetectCycleDFS, DetectCycleDirectedDFS, DetectCycleIn2DGrid
│    │    ├─ mst/
│    │    │  └─ (8) Kruskal, KruskalAlgorithm, MaximizeSpanningTreeStability,
│    │    │         MinCostToConAllPoints, MinCostToConAllPointsOpt, MinCostToConnectAllPoints,
│    │    │         PrimAlgorithm, PrimsAlgorithm
│    │    ├─ shortestpath/
│    │    │  └─ (7) BellmanFordAdjacencyList, BellmanFordAlgorithm, BellmanFordAlgorithmEdge,
│    │    │         CheapestFlightsWithinKStops, DijkstraAdjacencyList, DijkstraAlgorithm, NetworkDelayTime, WeightedGraph
│    │    ├─ topological/
│    │    │  └─ (13) CourseSchedule, CourseScheduleCycleDetection, CourseScheduleDfs, CourseScheduleDFSOptimal,
│    │    │          CourseScheduleII, CourseScheduleIIBfs, CourseScheduleIIDfs, CourseScheduleOptimal,
│    │    │          KahnsAlgorithm, LargestColorValueInDirGraph, TopologicalSort, TopologicalSortingBFS, TopologicalSortingDFS
│    │    ├─ traversal/
│    │    │  └─ (15) AdjacencyMatrix, AllPaths, AllPathsSourceTarget, BfsTraversal, BFSTraversalInGraph,
│    │    │          CheckDuplicatesBruteForce, DfsTraversal, DFSTraversalInGraph, FloodFill, FloodFillSimple,
│    │    │          GraphSimple, MaxAreaOfIsland, NumberOfIslands, NumberOfIslandsInPlace, RottingOranges, RottingOrangesSimple
│    │    └─ unionfind/
│    │       └─ (5) DisjointSetUnion, DSU, PathExistenceQueries, PathExistenceQueriesDSU, RedundantConnection
│    │
│    ├─── 📁 hashing/                   [5 files]
│    │    └─ ContainsDuplicatesII, FirstUniqueCharacter, IntersectionOfArrays, IntersectionOfArraysII, MinimumIndexSum
│    │
│    ├─── 📁 heap/                      [13 files]
│    │    └─ KthFactorOfN, KthLargestElement, KWeakestRows, LastStoneWeight, Main,
│    │       MaxHeap, MedianFromDataStream, MedianFromDataStreamOptimal, MergeKSortedLists, MinHeap,
│    │       TopKFrequentElements, Twitter, UglyNumberII
│    │
│    ├─── 📁 interval/                  [1 file]
│    │    └─ RemoveCoveredIntervals
│    │
│    ├─── 📁 linkedlist/                [4 subcategories · 14 files]
│    │    ├─ advanced/
│    │    │  └─ (3) LinkedListsIntersection, PalindromeLinkedList, SplitCircularLinkedList
│    │    ├─ basic/
│    │    │  └─ (4) DeleteNode, ListNode, MyLinkedList, SinglyLinkedList
│    │    ├─ cycles/
│    │    │  └─ (2) LinkedListCycle, LinkedListCycleII
│    │    ├─ operations/
│    │    │  └─ (4) AddTwoNumbers, DesignLinkedList, MergeTwoSortedLists, RemoveNthNodeFromEnd
│    │    ├─ traversal/
│    │    │  └─ (3) MaximumTwinSumOfLinkedList, ReverseLinkedList, (Main)
│    │    └─ (root)
│    │       └─ (1) Main
│    │
│    ├─── 📁 map/                       [1 subcategory]
│    │    └─ designhashmap/
│    │       └─ MyHashMap
│    │
│    ├─── 📁 math/                      [5 files]
│    │    └─ GCDOfOddEvenSums, RotatedDigits, SortedPairGCDQueries, SumOfGCDOfFormedPairs, UglyNumber
│    │
│    ├─── 📁 patterns/                  [2 files]
│    │    └─ FloydTriangle, FloydTriangleMathematically
│    │
│    ├─── 📁 queue/                     [9 files]
│    │    └─ ArrayListQueue, ArrayQueue, LinkedListQueue, MovingAverage, MyCircularQueue,
│    │       MyCircularQueueArrayList, MyCircularQueueLinkedList, MyQueueUsingStacks, MyStackUsingQueue
│    │
│    ├─── 📁 recursion/                 [28 files]
│    │    └─ ClimbingStairs, Combinations, CombinationSum, FibonacciNumber, GenerateParenthesis,
│    │       KthSymbolInGrammar, LetterCombinations, NQueens, NQueensII, PalindromePartitioning,
│    │       Permutations, PowXN, QuickSort, RecursionIntro, RecursionIntro2, RecursionSimulation,
│    │       ReverseLinkedList, ReverseString, SearchA2DMatrixII, SearchInBST, StringPermutations,
│    │       Subsets, SubsetsII, SudokuSolver, SudokuSolver2, SumOfNNums, SwapNodesInPairs, ValidateBST, WordSearch
│    │
│    ├─── 📁 set/                       [1 subcategory + 1 file]
│    │    ├─ designhashset/
│    │    │  └─ MyHashSet
│    │    └─ SingleNumber
│    │
│    ├─── 📁 slidingwindow/             [9 files]
│    │    └─ DynamicWindow, LongestSubstringWithoutRepeatingCharacters, MaximumAverageSubarrayI,
│    │       MaxSumOfDistinctSubarray, MaxSumOfSubArray, MinimumWindowSubstring, MinSizeSubArraySum,
│    │       RepeatedDNASequence, SlidingWindow
│    │
│    ├─── 📁 sortalgorithms/            [12+ files]
│    │    └─ BubbleSort, BubbleSortRec, CountSmallerAfterSelf, InsertionSort, InsertionSortRec,
│    │       MergeSort, MergeSortRec, PatternsViaRecursion, SelectionSort, SelectionSortRec,
│    │       SortColors, TwoColorSort
│    │
│    ├─── 📁 stack/                     [11 files]
│    │    └─ ArrayListStack, ArrayStack, DailyTemperatures, LinkedListStack, MinStack, MinStackLinkedList,
│    │       NextGreaterElement, NextGreaterElementII, ReversePolishNotation, ValidParenthesis, ValidParenthesisMap
│    │
│    ├─── 📁 strings/                   [4 subcategories · 25 files]
│    │    ├─ advanced/
│    │    │  └─ (5) FindIndexOfFirstOccurrence, LongestSubstringWithoutRepeatingCharacters,
│    │    │         MinChangesAlternatingBinaryString, PartitionDeciBinary, PartitioningIntoMinDeciBinaryNumbers
│    │    ├─ basic/
│    │    │  └─ (10) AddBinary, AddStrings, BinaryGap, JavaStringTokens, ReverseString,
│    │    │          ReverseWordsInString, ReverseWordsInStringII, ReverseWordsInStringIII, RotatingString, SplitString
│    │    ├─ encoding/                  [Empty - Reserved for future use]
│    │    └─ pattern/
│    │       └─ (9) Anagram, IsomorphicStrings, Palindrome, PalindromeI, PalindromeII,
│    │              SmallestAndLargestSub, ValidAnagram, ValidPalindrome, ValidWordAbbreviation
│    │
│    ├─── 📁 tree/                      [2 subcategories · 13 files]
│    │    ├─ advanced/
│    │    │  └─ (8) BalancedBinaryTree, ConstructBinaryTree, ConstructBinaryTree106,
│    │    │         DiameterOfBinaryTree, LowestCommonAncestorOfBinaryTree, LowestCommonAncestorOfBST,
│    │    │         PathSum, PathSumII
│    │    ├─ basic/
│    │    │  └─ (5) CountNodes, InvertBinaryTree, MaxDepth, MyBinaryTree, SameTree
│    │    └─ traversal/
│    │       └─ (5) BinaryTreeInorder, BinaryTreePostorder, BinaryTreePreorder, LevelOrderTraversal, RightSideView
│    │
│    └─── 📁 twopointer/                [11 files]
│         └─ AppendCharacters, ContainerWithMostWater, FindTheDuplicateNumber, FourSum, HappyNumber,
│            LengthOfLoop, MiddleOfLinkedList, StrobogrammaticNumber, ThreeSum, TwoSum, TwoSumII
│
├─── 📄 .gitignore                      [Git ignore rules]
├─── 📄 assessment.md                   [Assessment notes]
├─── 📄 DSA_NOTES.md                    [Data structure algorithm notes]
├─── 📄 DSA_REPOSITORY_STRUCTURE.md     [Repository structure documentation]
├─── 📄 DSA_TRACKER.md                  [Progress tracking document]
├─── 📄 FOLDER_STRUCTURE_TREE.md        [This file - Visual tree structure]
├─── 📄 LEETCODE_SOLUTIONS_INDEX.md     [Index of all LeetCode solutions]
├─── 📄 LeetCodePractice.iml            [IntelliJ IDEA module file]
├─── 📄 QUICK_REFERENCE.md              [Quick reference guide]
├─── 📄 README.md                       [Main documentation]
├─── 📄 REORGANIZATION_SUMMARY.md       [Summary of reorganization changes]
└─── 📄 solvedproblems.txt              [List of solved problems]
```

## 📊 Statistics

- **Total Java Source Files**: 330+
- **LeetCode Problems Solved**: 180+
- **Main Topics/Packages**: 23+
- **Subcategories**: 40+
- **Compiled Output Directory**: `out/production/LeetCodePractice/`

## 🔍 Key Folders at a Glance

| Folder | Files | Purpose |
|--------|-------|---------|
| `arrays` | 22+ | Array manipulation, rotation, prefix sum, greedy |
| `arrays101` | 24 | Beginner-level array problems |
| `binarysearch` | 11 | Binary search & variants |
| `dp` | 29 | Dynamic programming (6 subcategories) |
| `graph` | 73 | Graph algorithms (7 subcategories) |
| `linkedlist` | 14 | Linked list operations & traversal |
| `strings` | 25 | String manipulation & pattern matching |
| `tree` | 13 | Binary tree operations |
| `recursion` | 28 | Recursive problem solving |
| `hashing` | 5 | Hash map/set problems |
| `heap` | 13 | Priority queue problems |
| `stack` | 11 | Stack-based problems |
| `queue` | 9 | Queue implementations & problems |
| `twopointer` | 11 | Two-pointer technique problems |
| `slidingwindow` | 9 | Sliding window problems |
| `sortalgorithms` | 11 | Sorting algorithms |
| `set` | 1+ | Set operations |
| `map` | 1+ | Hash map implementations |
| `interval` | 1 | Interval-based problems |
| `math` | 4 | Mathematical problems |

## 📝 Notes

- All source code is in the `src/` directory
- Compiled classes are in `out/` (auto-generated, safe to delete)
- IntelliJ configuration in `.idea/` (project settings)
- `_sandbox/` folder is used for testing and prototyping
- Each subcategory folder is organized by algorithm pattern or problem type
     │
     ├─── 📁 map/               [UNCHANGED]
     │    └─ designhashmap/
     │         └─ MyHashMap.java
     │
     └─── 📁 dailychallenges/   [UNCHANGED]
          └─ (6 files) CheckOneSegment, ComplementBase10Integer, etc.
```

---

## 📊 QUICK STATS

| Category | Subcategories | Files | Status |
|----------|---------------|-------|--------|
| arrays | 6 | 25 | ✅ Reorganized |
| binarysearch | 3 | 12 | ✅ Reorganized |
| dp | 7 | 30 | ✅ Reorganized |
| graph | 7 | 62 | ✅ Reorganized |
| strings | 4 | 24 | ✅ Reorganized |
| linkedlist | 5 | 16 | ✅ Reorganized |
| tree | 3 | 18 | ✅ Reorganized |
| math | - | 4 | ✅ NEW |
| interval | - | 1 | ✅ NEW |
| _sandbox | - | 2 | ✅ NEW |
| **Total** | **42+** | **327** | ✅ Complete |

---

## 🎯 ORGANIZATIONAL PRINCIPLES

### Arrays
- **rotation/**: Problems involving array rotation and permutation
- **prefix/**: Prefix sum and range-based queries
- **greedy/**: Greedy algorithm problems on arrays
- **matrix/**: 2D array and matrix problems
- **basics/**: Fundamental array operations
- **advanced/**: Complex array patterns

### DP
- **linear/**: 1D DP, climbing stairs, house robber patterns
- **knapsack/**: Knapsack variants and bounded/unbounded problems
- **subsequence/**: LCS, LIS, and related problems
- **grid/**: 2D DP and grid-based problems
- **memoization/**: Top-down DP with recursion
- **optimization/**: Optimized DP solutions

### Graph
- **traversal/**: DFS, BFS, and graph exploration
- **shortestpath/**: Dijkstra, Bellman-Ford, SPFA
- **mst/**: Minimum Spanning Tree (Kruskal, Prim's)
- **unionfind/**: Disjoint Set Union and connectivity
- **topological/**: Topological sorting and DAG problems
- **cycles/**: Cycle detection in directed/undirected graphs
- **advanced/**: Complex graph algorithms (Trie, etc.)

---

## 🚀 HOW TO USE

1. **Focused Learning**: Study one subcategory at a time
   ```
   Learning rotation? → cd arrays/rotation/
   Learning DP knapsack? → cd dp/knapsack/
   ```

2. **Pattern Recognition**: See how different problems use the same pattern
   ```
   All greedy array problems in one place
   All linear DP problems grouped together
   ```

3. **Interview Prep**: When you see a pattern name in an interview question, you know exactly where to look
   ```
   "This is a shortest path problem" → graph/shortestpath/
   "This is a knapsack variant" → dp/knapsack/
   ```

4. **Adding New Problems**: Place new problems in the appropriate pattern folder
   ```
   New rotation array problem? → arrays/rotation/
   New topological sort? → graph/topological/
   ```

---

## ✨ BENEFITS REALIZED

✅ **Pattern-Based Learning**: Study problems grouped by solution approach, not just topic  
✅ **FAANG Interview Ready**: Organized like professionals organize their prep materials  
✅ **Scalable**: Easy to add new problems in the correct location  
✅ **No Code Changes**: Files moved, not modified  
✅ **Zero Deletions**: All original files preserved  
✅ **Clear Navigation**: Exactly where each type of problem is located  

---

**Your repository is now organized at a professional level! 🎉**
