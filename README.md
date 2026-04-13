# Java DSA Roadmap – 310 Essential LeetCode Problems

**Author:** Mohan Lal | **GitHub:** [mhnuk2007](https://github.com/mhnuk2007) | **LeetCode:** [mhnuk2007](https://leetcode.com/mhnuk2007) | **Language:** Java

> **Goal:** Master data structures and algorithms for software engineering interviews — 300+ problems solved in Java.
>
> *Note: Some problems appear in multiple sections because they combine several core patterns (e.g., HashMap + Heap).*
>
> 📁 Full file inventory: see [solvedproblems.txt](solvedproblems.txt) — includes practice files, alternate approaches, and design implementations (213 total Java files under `src`).

---

## 📊 Progress Tracker

| Category               | Total | Completed | Progress              |
|------------------------|-------|-----------|----------------------|
| Arrays & Two Pointers  |  40   |    40     | ██████████ 100%      |
| Sliding Window         |  16   |     3     | ██░░░░░░░░  19%      |
| Strings                |  22   |    14     | ██████░░░░  64%      |
| HashMap / HashSet      |  23   |    11     | █████░░░░░  48%      |
| Binary Search          |  23   |    11     | █████░░░░░  48%      |
| Linked List            |  23   |    13     | ██████░░░░  57%      |
| Stack & Queue          |  25   |    10     | ████░░░░░░  40%      |
| Heap / Priority Queue  |  17   |     8     | ████░░░░░░  47%      |
| Trees                  |  32   |    11     | ███░░░░░░░  34%      |
| Backtracking           |  16   |    10     | ██████░░░░  63%      |
| Graphs                 |  22   |     0     | ░░░░░░░░░░   0%      |
| Dynamic Programming    |  33   |     3     | █░░░░░░░░░   9%      |
| Bit Manipulation       |  12   |     4     | ███░░░░░░░  33%      |
| Daily Challenges       |   6   |     5     | ████████░░  83%      |
| **Total**              | **310** | **142** | **████░░░░░░ 46%**  |

> 📌 Trees expanded to 32 — added LC 144 (Preorder Traversal), LC 145 (Postorder Traversal), and LC 222 (Count Complete Tree Nodes) to the tracked roadmap.

---

## 🚀 30-Day Interview-Ready Roadmap

> Priority based on the current tracker: **Heap, Graphs, Dynamic Programming, Sliding Window**, then Trees and Backtracking polish.

### ⚡ Rules

- 3 problems/day, max 4
- 1 easy skip/day only if the pattern already feels automatic
- For every problem, write: pattern, time/space, and 1 mistake you made
- Re-solve without notes after 24 hours

### 🧠 Phase 1 (Days 1–10): Core Interview Patterns

| Day | Problems | Focus |
|-----|----------|-------|
| **Day 1** | 110 · 543 · 104 (re-solve) | Height-based recursion · returning values cleanly |
| **Day 2** | 98 (re-solve) · 700 (re-solve) · 235 | BST rules · search space pruning |
| **Day 3** | 236 · 112 · 113 | DFS + backtracking on trees |
| **Day 4** | 215 · 1046 · PriorityQueue drill | Min heap vs max heap · Java comparator fluency |
| **Day 5** | 347 · 703 · 295 (stretch) | Top-K pattern · stream heap · two-heaps pattern |
| **Day 6** | 200 · 695 | DFS on grids · visited thinking |
| **Day 7** | 994 · 1091 | Multi-source BFS · shortest path on grids |
| **Day 8** | 207 · 210 | Topological sort · course schedule pattern |
| **Day 9** | 424 · 567 | Variable sliding window · frequency map |
| **Day 10** | 438 · 1004 | Expand/shrink discipline · review weak window mistakes |

### 🔁 Phase 2 (Days 11–20): Backtracking + DP

| Day | Problems | Focus |
|-----|----------|-------|
| **Day 11** | 22 (re-solve) · 78 (re-solve) | Backtracking template · choose → explore → undo |
| **Day 12** | 39 (re-solve) · 40 | Reuse vs no-reuse branches |
| **Day 13** | 79 · 131 (re-solve) | Grid backtracking · path partitioning |
| **Day 14** | 70 (re-solve) · 198 | 1D DP basics · state definition |
| **Day 15** | 213 · 53 | Circular DP vs Kadane |
| **Day 16** | 322 · 518 | Unbounded knapsack intuition |
| **Day 17** | 300 · LIS binary-search optimization | DP to optimized pattern jump |
| **Day 18** | 1143 | 2D DP · subsequence thinking |
| **Day 19** | 139 | Prefix/state transitions |
| **Day 20** | DP review · re-solve 2 from memory | Memoization → tabulation fluency |

### 🧪 Phase 3 (Days 21–30): Interview Simulation Mode

| Day | Problems | Focus |
|-----|----------|-------|
| **Day 21** | 200 · 215 · 347 · 295 (try) | Graph + Heap mixed set |
| **Day 22** | 198 · 424 · 567 · 300 (try) | DP + Sliding Window mixed set |
| **Day 23** | 98 · 39 (re-solve) · 79 · 131 (re-solve) | Tree + Backtracking mixed set |
| **Day 24** | 207 · 322 · 139 · 210 | Graph + DP decision-making |
| **Day 25** | 703 · 110 · 438 · 994 | Timed mixed practice |
| **Day 26** | 2 problems in 45 min | No help · no notes · review after |
| **Day 27** | 2 new problems in 45 min | Same rules · different mix |
| **Day 28** | 1 hard problem for 45 min | Deep review after timeout |
| **Day 29** | 3 problems from your weakest category | Weak-area attack only |
| **Day 30** | 1 Graph · 1 DP · 1 Tree · 1 Backtracking | Final memory re-solve day |

### 📊 Checkpoints

- **Day 10:** tree/BST/heap/graph basics should feel structured instead of random.
- **Day 20:** you should be able to write subsets, combination sum, and core 1D DP patterns from memory.
- **Day 30:** you should be able to explain BFS vs DFS, topological sort, two-heaps, sliding window, and DP transitions without notes.

### 🧱 Daily Template

```java
/**
 * Problem: [LC Number] [Problem Name]
 * Pattern:
 * Time Complexity:
 * Space Complexity:
 *
 * Key Idea:
 *
 * Similar Problems:
 */
```

### 📈 Expected Result After 30 Days

| Category | Before | After |
|----------|--------|-------|
| Heap     |  0     | ~5+   |
| Graphs   |  0     | ~6+   |
| DP       |  3     | ~10+  |
| Sliding Window | 3 | ~7+   |
| Backtracking | 10 | ~11+ |

---

## 📅 Daily Progress

| Date   | Problems Solved                                        | Notes                          |
|--------|--------------------------------------------------------|--------------------------------|
| Apr 13 | 264, 355, 1492                                         | Heap deep dive |
| Apr 12 | 1046, 1337                                             | Heap deep dive |
| Apr 10 | 23, 215, 295                                           | Heap deep dive |
| Apr 9  | 17, 22, 347                                            | Backtracking push before switching focus |
| Apr 8  | 52, 77, 98                                             | Backtracking + BST expansion   |
| Apr 5  | 24, 21, 50, 70, 119, 206, 700, 779                     | Recursion re-solves + BST search |
| Apr 4  | 131, Sudoku solver, recursion re-solves (24, 206, 344) | Backtracking + recursion expansion |
| Mar 21 | 155 (4 approaches), 622, queue implementations         | Stack & Queue deep dive        |
| Mar 20 | 739, 150, 496, 503, 622                                | Stack & Queue                  |
| Mar 19 | 20, 155                                                | Started Stacks!                |
| Mar 18 | 561, 119, 209, 54, 67, 868, 415, 151, 186, 557         | Big push on Strings & Arrays   |
| Mar 16 | 118, 66, 747, 724, 169, 42, 189, 31, 2, 237            | Arrays, LL cleanup             |
| Mar 14 | 118, 66, 747, 724                                      | Array Day                      |
| Mar 13 | 31, 42, 2, 599, 387, 350, 219, 205, 136                | Arrays, LL & Hashing           |
| Mar 12 | 136, 706, 705, 1009, 69, 2486, 33                      | Very productive day!           |
| Mar 11 | 3130, 2130, 21                                         | DP and Linked Lists            |
| Mar 10 | 234, 287, 206, 3129, 19                                | Heavy on Linked Lists          |

---

## 1️⃣ Arrays & Two Pointers (40 / 40)

| #    | Problem                                         | Difficulty | Status | Pattern               |
|------|-------------------------------------------------|------------|--------|-----------------------|
| 1    | Two Sum                                         | Easy       | ✅ [Solution](src/twopointer/TwoSum.java) | Hashing |
| 11   | Container With Most Water                       | Medium     | ✅ [Solution](src/twopointer/ContainerWithMostWater.java) | Two Pointers |
| 15   | 3Sum                                            | Medium     | ✅ [Solution](src/twopointer/ThreeSum.java) | Two Pointers |
| 26   | Remove Duplicates from Sorted Array             | Easy       | ✅ [Solution](src/arrays101/RemoveDuplicates.java) | Two Pointers |
| 27   | Remove Element                                  | Easy       | ✅ [Solution](src/arrays101/RemoveElement.java) | Two Pointers |
| 31   | Next Permutation                                | Medium     | ✅ [Solution](src/arrays/NextPermutation.java) | Array Manipulation |
| 33   | Search in Rotated Sorted Array                  | Medium     | ✅ [Solution](src/arrays/SearchInRotatedSortedArray.java) | Binary Search |
| 42   | Trapping Rain Water                             | Hard       | ✅ [Solution](src/arrays/TrappingRainWater.java) | Two Pointers / Stack |
| 54   | Spiral Matrix                                   | Medium     | ✅ [Solution](src/arrays/SpiralMatrix.java) | Matrix Traversal |
| 66   | Plus One                                        | Easy       | ✅ [Solution](src/arrays/PlusOne.java) | Array Manipulation |
| 75   | Sort Colors                                     | Medium     | ✅ [Solution](src/sortalgrithms/SortColors.java) | Two Pointers |
| 80   | Remove Duplicates from Sorted Array II          | Medium     | ✅ [Solution](src/arrays101/RemoveDuplicatesFromSortedArrayII.java) | Two Pointers |
| 88   | Merge Sorted Array                              | Easy       | ✅ [Solution](src/arrays101/MergeSortedArray.java) | Two Pointers |
| 118  | Pascal's Triangle                               | Easy       | ✅ [Solution](src/arrays/PascalsTriangle.java) | DP / Simulation |
| 119  | Pascal's Triangle II                            | Easy       | ✅ [Solution](src/arrays/PascalsTriangleII.java) | DP / Simulation |
| 121  | Best Time to Buy and Sell Stock                 | Easy       | ✅ [Solution](src/arrays/BestTimeToBuyAndSellStock.java) | Greedy |
| 167  | Two Sum II - Input Array Is Sorted              | Medium     | ✅ [Solution](src/twopointer/TwoSumII.java) | Two Pointers |
| 169  | Majority Element                                | Easy       | ✅ [Solution](src/arrays/MajorityElement.java) | Sorting / Boyer-Moore |
| 189  | Rotate Array                                    | Medium     | ✅ [Solution](src/arrays/RotateArray.java) | Array Manipulation |
| 202  | Happy Number                                    | Easy       | ✅ [Solution](src/twopointer/HappyNumber.java) | Fast & Slow Pointers |
| 246  | Strobogrammatic Number                          | Easy       | ✅ [Solution](src/twopointer/StrobogrammaticNumber.java) | Two Pointers |
| 283  | Move Zeroes                                     | Easy       | ✅ [Solution](src/arrays101/MoveZeroes.java) | Two Pointers |
| 287  | Find the Duplicate Number                       | Medium     | ✅ [Solution](src/twopointer/FindTheDuplicateNumber.java) | Floyd's Cycle |
| 414  | Third Maximum Number                            | Easy       | ✅ [Solution](src/arrays101/ThirdMaximumNumber.java) | Tracking |
| 448  | Find All Numbers Disappeared in an Array        | Easy       | ✅ [Solution](src/arrays101/FindAllNumbersDisappearedInAnArray.java) | Array Traversal |
| 457  | Circular Array Loop                             | Medium     | ✅ [Solution](src/arrays/CircularArrayLoop.java) | Fast & Slow Pointers |
| 485  | Max Consecutive Ones                            | Easy       | ✅ [Solution](src/arrays101/MaxConsecutiveOnes.java) | Array Traversal |
| 561  | Array Partition                                 | Easy       | ✅ [Solution](src/arrays/ArrayPartition.java) | Sorting |
| 724  | Find Pivot Index                                | Easy       | ✅ [Solution](src/arrays/FindPivotIndex.java) | Prefix Sum |
| 747  | Largest Number At Least Twice of Others         | Easy       | ✅ [Solution](src/arrays/LargestNumberTwiceOthers.java) | Array Traversal |
| 1051 | Height Checker                                  | Easy       | ✅ [Solution](src/arrays101/HeightChecker.java) | Sorting |
| 1089 | Duplicate Zeros                                 | Easy       | ✅ [Solution](src/arrays101/DuplicateZeros.java) | Two Pointers |
| 1295 | Find Numbers with Even Number of Digits         | Easy       | ✅ [Solution](src/arrays101/FindNumbersWithEvenNumberOfDigits.java) | Array Traversal |
| 1299 | Replace Elements with Greatest on Right         | Easy       | ✅ [Solution](src/arrays101/ReplaceElementsWithGreatestElementOnRightSide.java) | Reverse Traversal |
| 1346 | Check If N and Its Double Exist                 | Easy       | ✅ [Solution](src/arrays101/CheckIfNAndItsDoubleExist.java) | Hashing |
| 1480 | Running Sum of 1d Array                         | Easy       | ✅ [Solution](src/arrays/RunningSum1d.java) | Prefix Sum |
| 1582 | Special Positions in a Binary Matrix            | Easy       | ✅ [Solution](src/arrays/SpecialPositionsInBinaryMatrix.java) | Matrix Traversal |
| 1672 | Richest Customer Wealth                         | Easy       | ✅ [Solution](src/arrays/RichestCustomerWealth.java) | Matrix Traversal |
| 2176 | Count Equal and Divisible Pairs in an Array     | Easy       | ✅ [Solution](src/arrays101/CountPairs.java) | Array Traversal |
| 2486 | Append Characters to String to Make Subsequence | Medium     | ✅ [Solution](src/twopointer/AppendCharacters.java) | Two Pointers |

---

## 2️⃣ Sliding Window (3 / 16)

| #    | Problem                                                 | Difficulty | Status | Pattern        |
|------|---------------------------------------------------------|------------|--------|----------------|
| 3    | Longest Substring Without Repeating Characters          | Medium     | ✅ [Solution](src/strings/LongestSubstringWithoutRepeatingCharacters.java) | Sliding Window |
| 76   | Minimum Window Substring                                | Hard       | ⬜      | Sliding Window |
| 209  | Minimum Size Subarray Sum                               | Medium     | ✅ [Solution](src/arrays/MinSizeSubArraySum.java) | Sliding Window |
| 239  | Sliding Window Maximum                                  | Hard       | ✅ [Solution](src/slidingwindow/MaxSlidingWindow.java) | Monotonic Deque |
| 424  | Longest Repeating Character Replacement                 | Medium     | ⬜      | Sliding Window |
| 438  | Find All Anagrams in a String                           | Medium     | ⬜      | Sliding Window |
| 567  | Permutation in String                                   | Medium     | ⬜      | Sliding Window |
| 713  | Subarray Product Less Than K                            | Medium     | ⬜      | Sliding Window |
| 904  | Fruit Into Baskets                                      | Medium     | ⬜      | Sliding Window |
| 1004 | Max Consecutive Ones III                                | Medium     | ⬜      | Sliding Window |
| 1208 | Get Equal Substrings Within Budget                      | Medium     | ⬜      | Sliding Window |
| 1343 | Number of Sub-arrays of Size K and Average ≥ Threshold  | Medium     | ⬜      | Sliding Window |
| 1423 | Maximum Points You Can Obtain from Cards                | Medium     | ⬜      | Sliding Window |
| 1493 | Longest Subarray of 1's After Deleting One Element      | Medium     | ⬜      | Sliding Window |
| 1838 | Frequency of the Most Frequent Element                  | Medium     | ⬜      | Sliding Window |
| 2090 | K Radius Subarray Averages                              | Medium     | ⬜      | Sliding Window |

---

## 3️⃣ Strings (14 / 22)

> 📁 Additional practice files in repo (not LeetCode submissions): `Anagram`, `Palindrome`, `PalindromeI`, `SplitString`, `SmallestAndLargestSub`, `JavaStringTokens`

| #    | Problem                                                  | Difficulty | Status | Pattern               |
|------|----------------------------------------------------------|------------|--------|-----------------------|
| 3    | Longest Substring Without Repeating Characters           | Medium     | ✅ [Solution](src/strings/LongestSubstringWithoutRepeatingCharacters.java) | Sliding Window |
| 5    | Longest Palindromic Substring                            | Medium     | ⬜      | DP / Expand Around Center |
| 8    | String to Integer (atoi)                                 | Medium     | ⬜      | String Parsing        |
| 14   | Longest Common Prefix                                    | Easy       | ⬜      | String                |
| 28   | Find the Index of the First Occurrence in a String       | Easy       | ✅ [Solution](src/strings/FindIndexOfFirstOccurrence.java) | Two Pointers |
| 49   | Group Anagrams                                           | Medium     | ⬜      | Hashing               |
| 58   | Length of Last Word                                      | Easy       | ⬜      | String                |
| 76   | Minimum Window Substring                                 | Hard       | ⬜      | Sliding Window        |
| 125  | Valid Palindrome                                         | Easy       | ✅ [Solution](src/strings/ValidPalindrome.java) | Two Pointers |
| 151  | Reverse Words in a String                                | Medium     | ✅ [Solution](src/strings/ReverseWordsInString.java) | Two Pointers |
| 186  | Reverse Words in a String II                             | Medium     | ✅ [Solution](src/strings/ReverseWordsInStringII.java) | In-place / O(1) space |
| 205  | Isomorphic Strings                                       | Easy       | ✅ [Solution](src/strings/IsomorphicStrings.java) | Hashing |
| 242  | Valid Anagram                                            | Easy       | ✅ [Solution](src/strings/ValidAnagram.java) | Hashing |
| 271  | Encode and Decode Strings                                | Medium     | ⬜      | String                |
| 344  | Reverse String                                           | Easy       | ✅ [Solution](src/strings/ReverseString.java) | Two Pointers |
| 408  | Valid Word Abbreviation                                  | Easy       | ✅ [Solution](src/strings/ValidWordAbbreviation.java) | Two Pointers |
| 415  | Add Strings                                              | Easy       | ✅ [Solution](src/strings/AddStrings.java) | String Math |
| 424  | Longest Repeating Character Replacement                  | Medium     | ⬜      | Sliding Window        |
| 557  | Reverse Words in a String III                            | Easy       | ✅ [Solution](src/strings/ReverseWordsInStringIII.java) | Two Pointers |
| 680  | Valid Palindrome II                                      | Easy       | ✅ [Solution](src/strings/PalindromeII.java) | Two Pointers |
| 1689 | Partitioning Into Minimum Number of Deci-Binary Numbers  | Easy       | ✅ [Solution](src/strings/PartitioningIntoMinDeciBinaryNumbers.java) | Greedy |
| 1758 | Minimum Changes to Make Alternating Binary String        | Easy       | ✅ [Solution](src/strings/MinChangesAlternatingBinaryString.java) | String |

---

## 4️⃣ HashMap / HashSet (11 / 23)

| #    | Problem                               | Difficulty | Status | Pattern              |
|------|---------------------------------------|------------|--------|----------------------|
| 49   | Group Anagrams                        | Medium     | ⬜      | Hashing              |
| 128  | Longest Consecutive Sequence          | Medium     | ⬜      | Hashing              |
| 205  | Isomorphic Strings                    | Easy       | ✅ [Solution](src/strings/IsomorphicStrings.java) | Hashing |
| 217  | Contains Duplicate                    | Easy       | ✅ [Solution](src/arrays/ContainsDuplicate.java) | Hashing |
| 219  | Contains Duplicate II                 | Easy       | ✅ [Solution](src/hashing/ContainsDuplicatesII.java) | Hashing + Sliding Window |
| 242  | Valid Anagram                         | Easy       | ✅ [Solution](src/strings/ValidAnagram.java) | Hashing |
| 290  | Word Pattern                          | Easy       | ⬜      | Hashing              |
| 347  | Top K Frequent Elements               | Medium     | ✅ [Solution](src/heap/TopKFrequentElements.java) | Hashing + Heap       |
| 349  | Intersection of Two Arrays            | Easy       | ✅ [Solution](src/hashing/IntersectionOfArrays.java) | Hashing |
| 350  | Intersection of Two Arrays II         | Easy       | ✅ [Solution](src/hashing/IntersectionOfArraysII.java) | Hashing |
| 380  | Insert Delete GetRandom O(1)          | Medium     | ⬜      | Hashing + Array      |
| 387  | First Unique Character in a String    | Easy       | ✅ [Solution](src/hashing/FirstUniqueCharacter.java) | Hashing |
| 525  | Contiguous Array                      | Medium     | ⬜      | Hashing + Prefix Sum |
| 560  | Subarray Sum Equals K                 | Medium     | ⬜      | Hashing + Prefix Sum |
| 594  | Longest Harmonious Subsequence        | Easy       | ⬜      | Hashing              |
| 599  | Minimum Index Sum of Two Lists        | Easy       | ✅ [Solution](src/hashing/MinimumIndexSum.java) | Hashing |
| 692  | Top K Frequent Words                  | Medium     | ⬜      | Hashing + Heap       |
| 705  | Design HashSet                        | Easy       | ✅ [Solution](src/set/designhashset/MyHashSet.java) | Hashing |
| 706  | Design HashMap                        | Easy       | ✅ [Solution](src/map/designhashmap/MyHashMap.java) | Hashing |
| 763  | Partition Labels                      | Medium     | ⬜      | Greedy + Hashing     |
| 811  | Subdomain Visit Count                 | Medium     | ⬜      | Hashing              |
| 884  | Uncommon Words from Two Sentences     | Easy       | ⬜      | Hashing              |
| 1002 | Find Common Characters                | Easy       | ⬜      | Hashing              |

---

## 5️⃣ Binary Search (11 / 23)

| #    | Problem                                                | Difficulty | Status | Pattern                 |
|------|--------------------------------------------------------|------------|--------|-------------------------|
| 33   | Search in Rotated Sorted Array                        | Medium    | ✅ [Solution](src/binarysearch/SearchRotatedSortedArray.java) | Binary Search           |
| 34   | Find First and Last Position of Element in Sorted Array | Medium    | ✅ [Solution](src/binarysearch/FirstAndLastPosition.java) | Binary Search           |
| 35   | Search Insert Position                                 | Easy       | ✅ [Solution](src/binarysearch/SearchInsert.java) | Binary Search           |
| 69   | Sqrt(x)                                                | Easy       | ✅ [Solution](src/binarysearch/SqrtX.java) | Binary Search |
| 81   | Search in Rotated Sorted Array II                      | Medium     | ✅ [Solution](src/binarysearch/SearchRotatedSortedArrayII.java) | Binary Search           |
| 153  | Find Minimum in Rotated Sorted Array                   | Medium     | ✅ [Solution](src/binarysearch/FindMinRotatedArray.java) | Binary Search           |
| 162  | Find Peak Element                                      | Medium     | ✅ [Solution](src/binarysearch/FindPeakElement.java) | Binary Search           |
| 278  | First Bad Version                                      | Easy       | ✅ [Solution](src/binarysearch/FirstBadVersion.java) | Binary Search           |
| 374  | Guess Number Higher or Lower                           | Easy       | ✅ [Solution](src/binarysearch/GuessNumber.java) | Binary Search           |
| 410  | Split Array Largest Sum                                | Hard       | ⬜      | Binary Search on Answer |
| 658  | Find K Closest Elements                                | Medium     | ✅ [Solution](src/binarysearch/FindKClosestElements.java) | Binary Search           |
| 704  | Binary Search                                          | Easy       | ✅ [Solution](src/binarysearch/BinarySearch.java) | Binary Search           |
| 875  | Koko Eating Bananas                                    | Medium     | ⬜      | Binary Search on Answer |
| 1011 | Capacity To Ship Packages Within D Days                | Medium     | ⬜      | Binary Search on Answer |
| 1283 | Find the Smallest Divisor Given a Threshold            | Medium     | ⬜      | Binary Search on Answer |
| 1482 | Minimum Number of Days to Make m Bouquets              | Medium     | ⬜      | Binary Search on Answer |
| 1760 | Minimum Limit of Balls in a Bag                        | Medium     | ⬜      | Binary Search on Answer |
| 1802 | Maximum Value at a Given Index in a Bounded Array      | Medium     | ⬜      | Binary Search on Answer |
| 1891 | Cutting Ribbons                                        | Medium     | ⬜      | Binary Search on Answer |
| 2187 | Minimum Time to Complete Trips                         | Medium     | ⬜      | Binary Search on Answer |
| 2226 | Maximum Candies Allocated to K Children                | Medium     | ⬜      | Binary Search on Answer |
| 2300 | Successful Pairs of Spells and Potions                 | Medium     | ⬜      | Binary Search           |
| 3296 | Minimum Number of Seconds to Make Mountain Height Zero  | Medium    | ⬜      | Binary Search on Answer |

---

## 6️⃣ Linked List (13 / 23)

> 📁 Additional files in repo (practice/helpers): `MyLinkedList`, `SinglyLinkedList`, `SplitCircularLinkedList`, `ListNode`

| #    | Problem                               | Difficulty | Status | Pattern              |
|------|---------------------------------------|------------|--------|----------------------|
| 2    | Add Two Numbers                       | Medium     | ✅ [Solution](src/linkedlist/AddTwoNumbers.java) | Linked List Math |
| 19   | Remove Nth Node From End of List      | Medium     | ✅ [Solution](src/linkedlist/RemoveNthNodeFromEnd.java) | Two Pointers |
| 21   | Merge Two Sorted Lists                | Easy       | ✅ [Solution](src/linkedlist/MergeTwoSortedLists.java) | Two Pointers |
| 24   | Swap Nodes in Pairs                   | Medium     | ✅ [Solution](src/recursion/SwapNodesInPairs.java) | Pointer Manipulation / Recursion |
| 61   | Rotate List                           | Medium     | ⬜      | Two Pointers         |
| 82   | Remove Duplicates from Sorted List II | Medium     | ⬜      | Two Pointers         |
| 83   | Remove Duplicates from Sorted List    | Easy       | ⬜      | Pointer Manipulation |
| 92   | Reverse Linked List II                | Medium     | ⬜      | Pointer Manipulation |
| 138  | Copy List with Random Pointer         | Medium     | ⬜      | Hashing              |
| 141  | Linked List Cycle                     | Easy       | ✅ [Solution](src/linkedlist/LinkedListCycle.java) | Fast & Slow Pointers |
| 142  | Linked List Cycle II                  | Medium     | ✅ [Solution](src/linkedlist/LinkedListCycleII.java) | Fast & Slow Pointers |
| 143  | Reorder List                          | Medium     | ⬜      | Fast & Slow Pointers |
| 148  | Sort List                             | Medium     | ⬜      | Merge Sort           |
| 160  | Intersection of Two Linked Lists      | Easy       | ✅ [Solution](src/linkedlist/LinkedListsIntersection.java) | Two Pointers |
| 206  | Reverse Linked List                   | Easy       | ✅ [Solution](src/linkedlist/ReverseLinkedList.java) | Pointer Manipulation |
| 234  | Palindrome Linked List                | Easy       | ✅ [Solution](src/linkedlist/PalindromeLinkedList.java) | Fast & Slow Pointers |
| 237  | Delete Node in a Linked List          | Medium     | ✅ [Solution](src/linkedlist/DeleteNode.java) | Pointer Manipulation |
| 328  | Odd Even Linked List                  | Medium     | ⬜      | Pointer Manipulation |
| 445  | Add Two Numbers II                    | Medium     | ⬜      | Linked List Math     |
| 707  | Design Linked List                    | Medium     | ✅ [Solution](src/linkedlist/DesignLinkedList.java) | Linked List Design |
| 725  | Split Linked List in Parts            | Medium     | ⬜      | Pointer Manipulation |
| 876  | Middle of the Linked List             | Easy       | ✅ [Solution](src/twopointer/MiddleOfLinkedList.java) | Fast & Slow Pointers |
| 2130 | Maximum Twin Sum of a Linked List     | Medium     | ✅ [Solution](src/linkedlist/MaximumTwinSumOfLinkedList.java) | Fast & Slow Pointers |

---

## 7️⃣ Stack & Queue (10 / 25)

> 📁 Additional files in repo (implementations): `ArrayStack`, `ArrayListStack`, `LinkedListStack`, `ArrayQueue`, `ArrayListQueue`, `LinkedListQueue`, `MovingAverage`

| #    | Problem                             | Difficulty | Status | Pattern                |
|------|-------------------------------------|------------|--------|------------------------|
| 20   | Valid Parentheses                   | Easy       | ✅ [Solution](src/stack/ValidParenthesis.java) | Stack |
| 71   | Simplify Path                       | Medium     | ⬜      | Stack |
| 84   | Largest Rectangle in Histogram      | Hard       | ⬜      | Monotonic Stack        |
| 85   | Maximal Rectangle                   | Hard       | ⬜      | Monotonic Stack        |
| 102  | Binary Tree Level Order Traversal   | Medium     | ✅ [Solution](src/tree/LevelOrderTraversal.java) | BFS / Queue            |
| 127  | Word Ladder                         | Hard       | ⬜      | BFS / Queue            |
| 150  | Evaluate Reverse Polish Notation    | Medium     | ✅ [Solution](src/stack/ReversePolishNotation.java) | Stack |
| 155  | Min Stack                           | Medium     | ✅ [Solution](src/stack/MinStack.java) | Stack |
| 207  | Course Schedule                     | Medium     | ⬜      | BFS / Topological Sort |
| 224  | Basic Calculator                    | Hard       | ⬜      | Stack                  |
| 225  | Implement Stack using Queues        | Easy       | ✅ [Solution](src/queue/MyStackUsingQueue.java) | Queue |
| 232  | Implement Queue using Stacks        | Easy       | ✅ [Solution](src/queue/MyQueueUsingStacks.java) | Stack |
| 286  | Walls and Gates                     | Medium     | ⬜      | BFS / Queue            |
| 394  | Decode String                       | Medium     | ⬜      | Stack                  |
| 496  | Next Greater Element I              | Easy       | ✅ [Solution](src/stack/NextGreaterElement.java) | Monotonic Stack |
| 503  | Next Greater Element II             | Medium     | ✅ [Solution](src/stack/NextGreaterElementII.java) | Monotonic Stack |
| 622  | Design Circular Queue               | Medium     | ✅ [Solution](src/queue/MyCircularQueue.java) | Queue Design |
| 641  | Design Circular Deque               | Medium     | ⬜      | Deque Design           |
| 739  | Daily Temperatures                  | Medium     | ✅ [Solution](src/stack/DailyTemperatures.java) | Monotonic Stack |
| 853  | Car Fleet                           | Medium     | ⬜      | Monotonic Stack        |
| 862  | Shortest Subarray with Sum ≥ K      | Hard       | ⬜      | Monotonic Deque        |
| 907  | Sum of Subarray Minimums            | Medium     | ⬜      | Monotonic Stack        |
| 933  | Number of Recent Calls              | Easy       | ⬜      | Queue / Sliding Window |
| 994  | Rotting Oranges                     | Medium     | ⬜      | BFS / Queue            |
| 1091 | Shortest Path in Binary Matrix      | Medium     | ⬜      | BFS / Queue            |

---

## 8️⃣ Heap / Priority Queue (8 / 17)

> 📌 **30-Day roadmap focus:** Day 4 → 215, 1046 · Day 5 → 347, 703, 295

| #    | Problem                                       | Difficulty | Status | Pattern            |
|------|-----------------------------------------------|------------|--------|--------------------|
| 23   | Merge K Sorted Lists                          | Hard       | ✅ [Solution](src/heap/MergeKSortedLists.java) | Heap               |
| 215  | Kth Largest Element in an Array               | Medium     | ✅ [Solution](src/heap/KthLargestElement.java) | Heap / QuickSelect · Day 4 |
| 264  | Ugly Number II                                | Medium     | ✅ [Solution](src/heap/UglyNumberII.java) | Heap / DP          |
| 295  | Find Median from Data Stream                  | Hard       | ✅ [Solution](src/heap/MedianFromDataStreamOptimal.java) | Two Heaps · Day 5 |
| 347  | Top K Frequent Elements                       | Medium     | ✅ [Solution](src/heap/TopKFrequentElements.java) | Heap + Hashing · Day 5 |
| 355  | Design Twitter                                | Medium     | ✅ [Solution](src/heap/Twitter.java) | Heap               |
| 373  | Find K Pairs with Smallest Sums               | Medium     | ⬜      | Heap               |
| 378  | Kth Smallest Element in a Sorted Matrix       | Medium     | ⬜      | Heap               |
| 407  | Trapping Rain Water II                        | Hard       | ⬜      | Heap               |
| 502  | IPO                                           | Hard       | ⬜      | Heap + Greedy      |
| 621  | Task Scheduler                                | Medium     | ⬜      | Heap + Greedy      |
| 630  | Course Schedule III                           | Hard       | ⬜      | Heap + Greedy      |
| 632  | Smallest Range Covering Elements from K Lists | Hard       | ⬜      | Heap               |
| 703  | Kth Largest Element in a Stream               | Easy       | ⬜      | Heap · Day 5       |
| 743  | Network Delay Time                            | Medium     | ⬜      | Heap + Dijkstra's  |
| 778  | Swim in Rising Water                          | Hard       | ⬜      | Heap               |
| 1046 | Last Stone Weight                             | Easy       | ✅ [Solution](src/heap/LastStoneWeight.java) | Heap · Day 4       |
| 1337 | The K Weakest Rows in a Matrix                | Easy       | ✅ [Solution](src/heap/KWeakestRows.java) | Heap + Binary Search |
| 1492 | The Kth Factor of N                           | Medium     | ✅ [Solution](src/heap/KthFactorOfN.java) | Math + Heap |

---

## 9️⃣ Trees (11 / 32)

> 📌 **30-Day roadmap focus:** Days 1–3 cover 104 (re-solve), 110, 543, 98, 700, 235, 236, 112, 113.
>
> 📁 Additional tree files in repo: `MyBinaryTree` (custom implementation)

| #    | Problem                                          | Difficulty | Status | Pattern                | 30-Day Roadmap |
|------|--------------------------------------------------|------------|--------|------------------------|----------------|
| 104  | Maximum Depth of Binary Tree                     | Easy       | ✅ [Solution](src/tree/MaxDepth.java) | DFS                    | Day 1 re-solve |
| 100  | Same Tree                                        | Easy       | ✅ [Solution](src/tree/SameTree.java) | DFS / BFS              |                |
| 226  | Invert Binary Tree                               | Easy       | ✅ [Solution](src/tree/InvertBinaryTree.java) | DFS / BFS              |                |
| 94   | Binary Tree Inorder Traversal                    | Easy       | ✅ [Solution](src/tree/BinaryTreeInorder.java)       | DFS                    |                |
| 144  | Binary Tree Preorder Traversal                   | Easy       | ✅ [Solution](src/tree/BinaryTreePreorder.java)       | DFS                    |                |
| 145  | Binary Tree Postorder Traversal                  | Easy       | ✅ [Solution](src/tree/BinaryTreePostorder.java) | DFS                    |                |
| 102  | Binary Tree Level Order Traversal                | Medium     | ✅ [Solution](src/tree/LevelOrderTraversal.java) | BFS                    |                |
| 199  | Binary Tree Right Side View                      | Medium     | ✅ [Solution](src/tree/RightSideView.java) | BFS                    |                |
| 110  | Balanced Binary Tree                             | Easy       | ⬜      | DFS                    | Day 1          |
| 543  | Diameter of Binary Tree                          | Easy       | ⬜      | DFS                    | Day 1          |
| 700  | Search in a Binary Search Tree                   | Easy       | ✅ [Solution](src/recursion/SearchInBST.java) | BST Properties / Recursion | Day 2 re-solve |
| 98   | Validate Binary Search Tree                      | Medium     | ✅ [Solution](src/recursion/ValidateBST.java) | DFS / Recursion        | Day 2 re-solve |
| 112  | Path Sum                                         | Easy       | ⬜      | DFS                    | Day 3          |
| 257  | Binary Tree Paths                                | Easy       | ⬜      | DFS + Backtracking     |                |
| 113  | Path Sum II                                      | Medium     | ⬜      | DFS + Backtracking     | Day 3          |
| 235  | Lowest Common Ancestor of a BST                  | Medium     | ⬜      | BST Properties         | Day 2          |
| 236  | Lowest Common Ancestor of a Binary Tree          | Medium     | ⬜      | DFS                    | Day 3          |
| 105  | Construct Binary Tree from Preorder and Inorder  | Medium     | ⬜      | Recursion              |                |
| 124  | Binary Tree Maximum Path Sum                     | Hard       | ⬜      | DFS                    |                |
| 101  | Symmetric Tree                                   | Easy       | ⬜      | DFS / BFS              |                |
| 106  | Construct Binary Tree from Inorder and Postorder | Medium     | ⬜      | Recursion              |                |
| 116  | Populating Next Right Pointers in Each Node      | Medium     | ⬜      | BFS / Pointer          |                |
| 222  | Count Complete Tree Nodes                        | Medium     | ✅ [Solution](src/tree/CountNodes.java) | DFS                    |                |
| 230  | Kth Smallest Element in a BST                    | Medium     | ⬜      | DFS                    |                |
| 297  | Serialize and Deserialize Binary Tree            | Hard       | ⬜      | DFS / BFS              |                |
| 337  | House Robber III                                 | Medium     | ⬜      | DP on Trees            |                |
| 404  | Sum of Left Leaves                               | Easy       | ⬜      | DFS                    |                |
| 437  | Path Sum III                                     | Medium     | ⬜      | DFS + Prefix Sum       |                |
| 450  | Delete Node in a BST                             | Medium     | ⬜      | BST Properties         |                |
| 572  | Subtree of Another Tree                          | Easy       | ⬜      | DFS                    |                |
| 814  | Binary Tree Pruning                              | Medium     | ⬜      | DFS                    |                |
| 1650 | Lowest Common Ancestor of a Binary Tree III      | Medium     | ⬜      | Two Pointers           |                |

---

## 🔟 Backtracking (10 / 16)

> 📁 Current backtracking solutions live in `src/recursion/`, alongside recursion-only practice and re-solve drills, while a dedicated `src/backtracking/` package is still pending.

| #   | Problem                               | Difficulty | Status | Pattern             |
|-----|---------------------------------------|------------|--------|---------------------|
| 17  | Letter Combinations of a Phone Number | Medium     | ✅ [Solution](src/recursion/LetterCombinations.java) | Backtracking        |
| 22  | Generate Parentheses                  | Medium     | ✅ [Solution](src/recursion/GenerateParenthesis.java) | Backtracking        |
| 39  | Combination Sum                       | Medium     | ✅ [Solution](src/recursion/CombinationSum.java) | Backtracking        |
| 40  | Combination Sum II                    | Medium     | ⬜      | Backtracking        |
| 46  | Permutations                          | Medium     | ✅ [Solution](src/recursion/Permutations.java) | Backtracking        |
| 47  | Permutations II                       | Medium     | ⬜      | Backtracking        |
| 51  | N-Queens                              | Hard       | ✅ [Solution](src/recursion/NQueens.java) | Backtracking        |
| 52  | N-Queens II                           | Hard       | ✅ [Solution](src/recursion/NQueensII.java) | Backtracking        |
| 77  | Combinations                          | Medium     | ✅ [Solution](src/recursion/Combinations.java) | Backtracking        |
| 78  | Subsets                               | Medium     | ✅ [Solution](src/recursion/Subsets.java) | Backtracking        |
| 79  | Word Search                           | Medium     | ⬜      | Backtracking + DFS  |
| 90  | Subsets II                            | Medium     | ✅ [Solution](src/recursion/SubsetsII.java) | Backtracking        |
| 131 | Palindrome Partitioning               | Medium     | ✅ [Solution](src/recursion/PalindromePartitioning.java) | Backtracking        |
| 212 | Word Search II                        | Hard       | ⬜      | Backtracking + Trie |
| 216 | Combination Sum III                   | Medium     | ⬜      | Backtracking        |
| 491 | Non-decreasing Subsequences           | Medium     | ⬜      | Backtracking        |

---

## 1️⃣1️⃣ Graphs (0 / 22)

> 📌 **30-Day roadmap focus:** Day 6 → 200, 695 · Day 7 → 994, 1091 · Day 8 → 207, 210

| #    | Problem                                      | Difficulty | Status | Pattern             | 30-Day Roadmap |
|------|----------------------------------------------|------------|--------|---------------------|----------------|
| 200  | Number of Islands                            | Medium     | ⬜      | DFS / BFS           | Day 6          |
| 133  | Clone Graph                                  | Medium     | ⬜      | DFS / BFS           |                |
| 994  | Rotting Oranges                              | Medium     | ⬜      | BFS (multi-source)  | Day 7          |
| 1091 | Shortest Path in Binary Matrix               | Medium     | ⬜      | BFS                 | Day 7          |
| 207  | Course Schedule                              | Medium     | ⬜      | Topological Sort    | Day 8          |
| 210  | Course Schedule II                           | Medium     | ⬜      | Topological Sort    | Day 8          |
| 208  | Implement Trie (Prefix Tree)                 | Medium     | ⬜      | Trie                |                |
| 261  | Graph Valid Tree                             | Medium     | ⬜      | Union Find / DFS    |                |
| 269  | Alien Dictionary                             | Hard       | ⬜      | Topological Sort    |                |
| 286  | Walls and Gates                              | Medium     | ⬜      | BFS                 |                |
| 323  | Number of Connected Components               | Medium     | ⬜      | Union Find / DFS    |                |
| 329  | Longest Increasing Path in a Matrix          | Hard       | ⬜      | DFS + Memoization   |                |
| 417  | Pacific Atlantic Water Flow                  | Medium     | ⬜      | DFS / BFS           |                |
| 547  | Number of Provinces                          | Medium     | ⬜      | Union Find / DFS    |                |
| 684  | Redundant Connection                         | Medium     | ⬜      | Union Find          |                |
| 695  | Max Area of Island                           | Medium     | ⬜      | DFS / BFS           | Day 6          |
| 721  | Accounts Merge                               | Medium     | ⬜      | Union Find / DFS    |                |
| 743  | Network Delay Time                           | Medium     | ⬜      | Dijkstra's          |                |
| 787  | Cheapest Flights Within K Stops              | Medium     | ⬜      | Bellman-Ford        |                |
| 802  | Find Eventual Safe States                    | Medium     | ⬜      | DFS                 |                |
| 827  | Making A Large Island                        | Hard       | ⬜      | DFS                 |                |
| 1584 | Min Cost to Connect All Points               | Medium     | ⬜      | MST (Prim's)        |                |

---

## 1️⃣2️⃣ Dynamic Programming (3 / 33)

| #    | Problem                                                   | Difficulty | Status | Pattern              |
|------|-----------------------------------------------------------|------------|--------|----------------------|
| 5    | Longest Palindromic Substring                             | Medium     | ⬜      | DP / Expand Center   |
| 53   | Maximum Subarray                                          | Medium     | ⬜      | Kadane's / DP        |
| 62   | Unique Paths                                              | Medium     | ⬜      | DP                   |
| 63   | Unique Paths II                                           | Medium     | ⬜      | DP                   |
| 64   | Minimum Path Sum                                          | Medium     | ⬜      | DP                   |
| 70   | Climbing Stairs                                           | Easy       | ✅ [Solution](src/recursion/ClimbingStairs.java) | DP / Recursion      |
| 72   | Edit Distance                                             | Medium     | ⬜      | DP                   |
| 91   | Decode Ways                                               | Medium     | ⬜      | DP                   |
| 123  | Best Time to Buy and Sell Stock III                       | Hard       | ⬜      | DP                   |
| 139  | Word Break                                                | Medium     | ⬜      | DP                   |
| 152  | Maximum Product Subarray                                  | Medium     | ⬜      | DP                   |
| 198  | House Robber                                              | Medium     | ⬜      | DP                   |
| 213  | House Robber II                                           | Medium     | ⬜      | DP                   |
| 221  | Maximal Square                                            | Medium     | ⬜      | DP                   |
| 279  | Perfect Squares                                           | Medium     | ⬜      | DP                   |
| 300  | Longest Increasing Subsequence                            | Medium     | ⬜      | DP                   |
| 309  | Best Time to Buy and Sell Stock with Cooldown             | Medium     | ⬜      | DP                   |
| 312  | Burst Balloons                                            | Hard       | ⬜      | DP                   |
| 322  | Coin Change                                               | Medium     | ⬜      | DP                   |
| 337  | House Robber III                                          | Medium     | ⬜      | DP on Trees          |
| 416  | Partition Equal Subset Sum                                | Medium     | ⬜      | DP                   |
| 494  | Target Sum                                                | Medium     | ⬜      | DP                   |
| 518  | Coin Change II                                            | Medium     | ⬜      | DP                   |
| 673  | Number of Longest Increasing Subsequence                  | Medium     | ⬜      | DP                   |
| 714  | Best Time to Buy and Sell Stock with Transaction Fee      | Medium     | ⬜      | DP                   |
| 740  | Delete and Earn                                           | Medium     | ⬜      | DP                   |
| 931  | Minimum Falling Path Sum                                  | Medium     | ⬜      | DP                   |
| 1143 | Longest Common Subsequence                                | Medium     | ⬜      | DP                   |
| 1155 | Number of Dice Rolls With Target Sum                      | Medium     | ⬜      | DP                   |
| 1218 | Longest Arithmetic Subsequence of Given Difference        | Medium     | ⬜      | DP                   |
| 1639 | Number of Ways to Form a Target String Given a Dictionary | Hard       | ⬜      | DP                   |
| 3129 | Find All Possible Stable Binary Arrays I                  | Hard       | ✅ [Solution](src/dp/FindAllPossibleStableArraysI.java) | DP + Prefix Sum |
| 3130 | Find All Possible Stable Binary Arrays II                 | Hard       | ✅ [Solution](src/dp/FindAllPossibleStableArraysII.java) | DP + Prefix Sum |

---

## 1️⃣3️⃣ Bit Manipulation (4 / 12)

| #    | Problem                             | Difficulty | Status | Pattern          |
|------|-------------------------------------|------------|--------|------------------|
| 67   | Add Binary                          | Easy       | ✅ [Solution](src/strings/AddBinary.java) | Bit Manipulation |
| 136  | Single Number                       | Easy       | ✅ [Solution](src/set/SingleNumber.java) | XOR |
| 190  | Reverse Bits                        | Easy       | ⬜      | Bit Manipulation |
| 191  | Number of 1 Bits                    | Easy       | ⬜      | Bit Manipulation |
| 231  | Power of Two                        | Easy       | ⬜      | Bit Manipulation |
| 268  | Missing Number                      | Easy       | ⬜      | XOR / Math       |
| 338  | Counting Bits                       | Easy       | ⬜      | DP + Bits        |
| 371  | Sum of Two Integers                 | Medium     | ⬜      | Bit Manipulation |
| 461  | Hamming Distance                    | Easy       | ⬜      | XOR              |
| 693  | Binary Number with Alternating Bits | Easy       | ⬜      | Bit Manipulation |
| 868  | Binary Gap                          | Easy       | ✅ [Solution](src/strings/BinaryGap.java) | Bit Manipulation |
| 1009 | Complement of Base 10 Integer       | Easy       | ✅ [Solution](src/dailychallenges/ComplementBase10Integer.java) | Bit Mask |

---

## 🌟 Daily Challenges (5 / 6)

| #    | Problem                                                  | Difficulty | Status | Pattern          |
|------|----------------------------------------------------------|------------|--------|------------------|
| 1318 | Minimum Flips to Make a OR b Equal to c                  | Medium     | ✅ [Solution](src/dailychallenges/MinFlips.java) | Bit Manipulation |
| 1545 | Find Kth Bit in Nth Binary String                        | Medium     | ✅ [Solution](src/dailychallenges/FindKthBit.java) | Recursion        |
| 1784 | Check if Binary String Has at Most One Segment of Ones   | Easy       | ✅ [Solution](src/dailychallenges/CheckOneSegment.java) | String           |
| 1980 | Find Unique Binary String                                | Medium     | ✅ [Solution](src/dailychallenges/FindUniqueBinaryString.java) | Backtracking     |
| 2193 | Minimum Number of Moves to Make Palindrome               | Hard       | ⬜      | Greedy           |
| 1536 | Minimum Swaps to Arrange a Binary Grid                   | Medium     | ✅ [Solution](src/dailychallenges/MinimumSwaps.java) | Greedy / Simulation |

---

## 📁 Project Structure

```
src/
├── arrays/              ← Core array problems
├── arrays101/           ← LeetCode arrays101 explore card
├── binarysearch/        ← Binary search problems
├── dailychallenges/     ← Daily challenge solutions
├── dp/                  ← Dynamic programming
├── hashing/             ← HashMap/HashSet problems
├── heap/                ← Heap / Priority Queue problems
├── linkedlist/          ← Linked list problems
├── map/designhashmap/   ← LC 706 Design HashMap
├── patterns/            ← Pattern practice (Floyd's triangle etc.)
├── queue/               ← Queue problems + implementations
├── recursion/           ← Recursion fundamentals + backtracking + recursion re-solves
├── set/designhashset/   ← LC 705 Design HashSet
├── slidingwindow/       ← Sliding window problems
├── sortalgrithms/       ← Sorting implementations + recursive variants  ⚠️ typo in folder name
├── stack/               ← Stack problems + implementations
├── strings/             ← String problems
├── tree/                ← Tree problems
└── twopointer/          ← Two pointer problems
```

> **⚠️ Typo to fix:** `src/sortalgrithms/` → rename to `src/sortalgorithms/`
>
> **📦 Packages to create as you progress:**
> - `src/bitmanipulation/` — LC 190, 191, 231, 268, 338, 371, 461, 693
> - `src/graph/`           — all Graphs problems
> - `src/backtracking/`    — all Backtracking problems
>
> **📁 Files to relocate eventually:**
> - `src/dailychallenges/ComplementBase10Integer.java` → `src/bitmanipulation/`
> - `src/set/SingleNumber.java`                        → `src/bitmanipulation/`
> - `src/strings/BinaryGap.java`                       → `src/bitmanipulation/`
> - `src/strings/AddBinary.java`                       → `src/bitmanipulation/`
> - `src/strings/MinChangesAlternatingBinaryString.java` — duplicate also in `src/arrays101/`

---

## 🧠 Problem-Solving Philosophy

- Implement brute force first → optimize step by step
- Top-down recursion → bottom-up DP → space optimization
- Add memoization manually after understanding the recurrence
- Recognize patterns across problems (Floyd's cycle, two pointers, prefix sum, XOR)
- Write production-quality Java with full Javadoc and labeled test cases

---

## 🔗 Connect with Me

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mhnuk2007/)
[![Portfolio](https://img.shields.io/badge/Portfolio-000000?style=for-the-badge&logo=github&logoColor=white)](https://mhnuk2007.github.io/)
[![Netlify](https://img.shields.io/badge/Netlify-00C7B7?style=for-the-badge&logo=netlify&logoColor=white)](https://mhnuk2007.netlify.app/)
[![LeetCode](https://img.shields.io/badge/LeetCode-FFA116?style=for-the-badge&logo=leetcode&logoColor=white)](https://leetcode.com/u/mhnuk2007/)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/mhnuk2007)

---

*Last updated: April 13, 2026*
