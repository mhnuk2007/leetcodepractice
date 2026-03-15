# Java DSA Roadmap – 266 Essential LeetCode Problems

**Author:** Mohan Lal | **GitHub:** [mhnuk2007](https://github.com/mhnuk2007) | **LeetCode:** [mhnuk2007](https://leetcode.com/mhnuk2007) | **Language:** Java

> **Goal:** Master data structures and algorithms for software engineering interviews — 200+ problems solved in Java.
>
> *Note: Some problems appear in multiple sections because they combine several core patterns (e.g., HashMap + Heap).*

---

## 📊 Progress Tracker

| Category               | Total | Completed | Progress              |
|------------------------|-------|-----------|----------------------|
| Arrays & Two Pointers  |  37   |    36     | ██████████  97%      |
| Sliding Window         |  16   |     2     | █░░░░░░░░░   13%     |
| Strings                |  14   |     7     | █████░░░░░  50%      |
| HashMap / HashSet      |  22   |    10     | ████░░░░░░  45%      |
| Binary Search          |  20   |     1     | ░░░░░░░░░░   5%      |
| Linked List            |  21   |    12     | █████░░░░░  57%      |
| Stack                  |  15   |     0     | ░░░░░░░░░░   0%      |
| Heap / Priority Queue  |  15   |     0     | ░░░░░░░░░░   0%      |
| Trees                  |  25   |     1     | ░░░░░░░░░░   4%      |
| Backtracking           |  15   |     0     | ░░░░░░░░░░   0%      |
| Graphs                 |  21   |     0     | ░░░░░░░░░░   0%      |
| Dynamic Programming    |  30   |     2     | █░░░░░░░░░   7%      |
| Bit Manipulation       |  10   |     2     | ██░░░░░░░░  20%      |
| Daily Challenges       |   5   |     0     | ░░░░░░░░░░   0%      |
| **Total**              | **266** | **73** | **████░░░░░░ 27.4%** |

---

## 📌 Current Focus & Recommended Next Problems

**Priority 1 — Stacks (Very Common in Interviews)**
```
20.  Valid Parentheses          (Easy)
155. Min Stack                  (Medium)
739. Daily Temperatures         (Medium) ← Monotonic Stack
496. Next Greater Element I     (Easy)   ← Monotonic Stack
84.  Largest Rectangle in Hist. (Hard)   ← Monotonic Stack
```

**Priority 2 — Sliding Window (Essential Pattern)**
```
209. Minimum Size Subarray Sum  (Medium)
438. Find All Anagrams          (Medium)
567. Permutation in String      (Medium)
424. Longest Repeating Char...  (Medium)
```

**Priority 3 — Trees (Core Interview Topic)**
```
104. Maximum Depth of Binary Tree (Easy)
226. Invert Binary Tree           (Easy)
102. Binary Tree Level Order      (Medium) ← BFS
543. Diameter of Binary Tree      (Easy)   ← DFS
98.  Validate BST                 (Medium)
```

---

## 📅 Daily Progress

| Date   | Problems Solved                              | Notes                        |
|--------|----------------------------------------------|------------------------------|
| Mar 16 | 118, 66, 747, 724, 169, 42, 189, 31, 2, 237 | Arrays, LL cleanup           |
| Mar 14 | 118, 66, 747, 724                            | Array Day                    |
| Mar 13 | 31, 42, 2, 599, 387, 350, 219, 205, 136      | Arrays, LL & Hashing         |
| Mar 12 | 136, 706, 705, 1009, 69, 2486, 33            | Very productive day!         |
| Mar 11 | 3130, 2130, 21                               | DP and Linked Lists          |
| Mar 10 | 234, 287, 206, 3129, 19                      | Heavy on Linked Lists        |

---

## 1️⃣ Arrays & Two Pointers (36 / 37)

| #    | Problem                                     | Difficulty | Status                    | Pattern               |
|------|---------------------------------------------|------------|---------------------------|-----------------------|
| 1    | Two Sum                                     | Easy       | ✅ [Solution](src/twopointer/TwoSum.java) | Hashing |
| 11   | Container With Most Water                   | Medium     | ✅ [Solution](src/twopointer/ContainerWithMostWater.java) | Two Pointers |
| 15   | 3Sum                                        | Medium     | ✅ [Solution](src/twopointer/ThreeSum.java) | Two Pointers |
| 26   | Remove Duplicates from Sorted Array         | Easy       | ✅ [Solution](src/arrays101/RemoveDuplicates.java) | Two Pointers |
| 27   | Remove Element                              | Easy       | ✅ [Solution](src/arrays101/RemoveElement.java) | Two Pointers |
| 31   | Next Permutation                            | Medium     | ✅ [Solution](src/arrays/NextPermutation.java) | Array Manipulation    |
| 33   | Search in Rotated Sorted Array              | Medium     | ✅ [Solution](src/arrays/SearchInRotatedSortedArray.java) | Binary Search |
| 42   | Trapping Rain Water                         | Hard       | ✅ [Solution](src/arrays/TrappingRainWater.java) | Two Pointers / Stack  |
| 66   | Plus One                                    | Easy       | ✅ [Solution](src/arrays/PlusOne.java) | Array Manipulation |
| 75   | Sort Colors                                 | Medium     | ✅ [Solution](src/arrays/SortColors.java) | Two Pointers |
| 88   | Merge Sorted Array                          | Easy       | ✅ [Solution](src/arrays101/MergeSortedArray.java) | Two Pointers |
| 118  | Pascal's Triangle                           | Easy       | ✅ [Solution](src/arrays/PascalsTriangle.java) | DP / Simulation |
| 121  | Best Time to Buy and Sell Stock             | Easy       | ✅ [Solution](src/arrays/BestTimeToBuyAndSellStock.java) | Greedy |
| 189  | Rotate Array                                | Medium     | ✅ [Solution](src/arrays/RotateArray.java) | Array Manipulation    |
| 283  | Move Zeroes                                 | Easy       | ✅ [Solution](src/arrays101/MoveZeroes.java) | Two Pointers |
| 287  | Find the Duplicate Number                   | Medium     | ✅ [Solution](src/twopointer/FindTheDuplicateNumber.java) | Floyd's Cycle |
| 414  | Third Maximum Number                        | Easy       | ✅ [Solution](src/arrays101/ThirdMaximumNumber.java) | Tracking |
| 485  | Max Consecutive Ones                        | Easy       | ✅ [Solution](src/arrays101/MaxConsecutiveOnes.java) | Array Traversal |
| 941  | Valid Mountain Array                        | Easy       | ✅ [Solution](src/arrays101/ValidMountainArray.java) | Array Traversal |
| 977  | Squares of a Sorted Array                   | Easy       | ✅ [Solution](src/arrays101/SquaresOfASortedArray.java) | Two Pointers |
| 1299 | Replace Elements with Greatest on Right     | Easy       | ✅ [Solution](src/arrays101/ReplaceElementsWithGreatestElementOnRightSide.java) | Reverse Traversal |
| 1346 | Check If N and Its Double Exist             | Easy       | ✅ [Solution](src/arrays101/CheckIfNAndItsDoubleExist.java) | Hashing |
| 1480 | Running Sum of 1d Array                     | Easy       | ✅ [Solution](src/arrays/RunningSum1d.java) | Prefix Sum |
| 1672 | Richest Customer Wealth                     | Easy       | ✅ [Solution](src/arrays/RichestCustomerWealth.java) | Matrix Traversal |
| 724  | Find Pivot Index                            | Easy       | ✅ [Solution](src/arrays/FindPivotIndex.java) | Prefix Sum |
| 747  | Largest Number At Least Twice of Others     | Easy       | ✅ [Solution](src/arrays/LargestNumberTwiceOthers.java) | Array Traversal |
| 80   | Remove Duplicates from Sorted Array II      | Medium     | ✅ [Solution](src/arrays101/RemoveDuplicatesFromSortedArrayII.java) | Two Pointers |
| 167  | Two Sum II - Input Array Is Sorted          | Medium     | ✅ [Solution](src/twopointer/TwoSumII.java) | Two Pointers |
| 202  | Happy Number                                | Easy       | ✅ [Solution](src/twopointer/HappyNumber.java) | Fast & Slow Pointers |
| 246  | Strobogrammatic Number                      | Easy       | ✅ [Solution](src/twopointer/StrobogrammaticNumber.java) | Two Pointers |
| 448  | Find All Numbers Disappeared in an Array    | Easy       | ✅ [Solution](src/arrays101/FindAllNumbersDisappearedInAnArray.java) | Array Traversal |
| 457  | Circular Array Loop                         | Medium     | ✅ [Solution](src/arrays/CircularArrayLoop.java) | Fast & Slow Pointers |
| 1051 | Height Checker                              | Easy       | ✅ [Solution](src/arrays101/HeightChecker.java) | Sorting |
| 1089 | Duplicate Zeros                             | Easy       | ✅ [Solution](src/arrays101/DuplicateZeros.java) | Two Pointers |
| 1295 | Find Numbers with Even Number of Digits     | Easy       | ✅ [Solution](src/arrays101/FindNumbersWithEvenNumberOfDigits.java) | Array Traversal |
| 1582 | Special Positions in a Binary Matrix        | Easy       | ✅ [Solution](src/arrays/SpecialPositionsInBinaryMatrix.java) | Matrix Traversal |
| 1758 | Minimum Changes to Make Alternating String  | Easy       | ✅ [Solution](src/strings/MinChangesAlternatingBinaryString.java) | String |
| 2176 | Count Equal and Divisible Pairs in an Array | Easy       | ✅ [Solution](src/arrays/CountPairs.java) | Array Traversal |
| 2486 | Append Characters to String to Make Subsequence | Medium | ✅ [Solution](src/twopointer/AppendCharacters.java) | Two Pointers |

---

## 2️⃣ Sliding Window (2 / 16)

| #    | Problem                                               | Difficulty | Status | Pattern        |
|------|-------------------------------------------------------|------------|--------|----------------|
| 3    | Longest Substring Without Repeating Characters        | Medium     | ✅ [Solution](src/strings/LongestSubstringWithoutRepeatingCharacters.java) | Sliding Window |
| 76   | Minimum Window Substring                              | Hard       | ⬜      | Sliding Window |
| 209  | Minimum Size Subarray Sum                             | Medium     | ⬜      | Sliding Window |
| 424  | Longest Repeating Character Replacement               | Medium     | ⬜      | Sliding Window |
| 438  | Find All Anagrams in a String                         | Medium     | ⬜      | Sliding Window |
| 567  | Permutation in String                                 | Medium     | ⬜      | Sliding Window |
| 713  | Subarray Product Less Than K                          | Medium     | ⬜      | Sliding Window |
| 904  | Fruit Into Baskets                                    | Medium     | ⬜      | Sliding Window |
| 1004 | Max Consecutive Ones III                              | Medium     | ⬜      | Sliding Window |
| 1208 | Get Equal Substrings Within Budget                    | Medium     | ⬜      | Sliding Window |
| 1343 | Number of Sub-arrays of Size K and Average ≥ Threshold | Medium   | ⬜      | Sliding Window |
| 1423 | Maximum Points You Can Obtain from Cards              | Medium     | ⬜      | Sliding Window |
| 1493 | Longest Subarray of 1's After Deleting One Element    | Medium     | ⬜      | Sliding Window |
| 1838 | Frequency of the Most Frequent Element                | Medium     | ⬜      | Sliding Window |
| 2090 | K Radius Subarray Averages                            | Medium     | ⬜      | Sliding Window |
| 239  | Sliding Window Maximum                                | Hard       | ✅ [Solution](src/slidingwindow/MaxSlidingWindow.java) | Sliding Window |

---

## 2️⃣b Strings (7 / 14)

| #    | Problem                                               | Difficulty | Status | Pattern        |
|------|-------------------------------------------------------|------------|--------|----------------|
| 28   | Find the Index of the First Occurrence in a String    | Easy       | ✅ [Solution](src/strings/FindIndexOfFirstOccurrence.java) | Two Pointers |
| 125  | Valid Palindrome                                      | Easy       | ✅ [Solution](src/strings/ValidPalindrome.java) | Two Pointers |
| 205  | Isomorphic Strings                                    | Easy       | ✅ [Solution](src/strings/IsomorphicStrings.java) | Hashing |
| 242  | Valid Anagram                                         | Easy       | ✅ [Solution](src/strings/ValidAnagram.java) | Hashing |
| 408  | Valid Word Abbreviation                               | Easy       | ✅ [Solution](src/strings/ValidWordAbbreviation.java) | Two Pointers |
| 680  | Valid Palindrome II                                   | Easy       | ✅ [Solution](src/strings/PalindromeII.java) | Two Pointers |
| 1689 | Partitioning Into Minimum Number of Deci-Binary Numbers | Easy    | ✅ [Solution](src/strings/PartitioningIntoMinDeciBinaryNumbers.java) | Greedy |
| 1758 | Minimum Changes to Make Alternating Binary String     | Easy       | ✅ [Solution](src/strings/MinChangesAlternatingBinaryString.java) | String |
| 3    | Longest Substring Without Repeating Characters        | Medium     | ✅ [Solution](src/strings/LongestSubstringWithoutRepeatingCharacters.java) | Sliding Window |
| 49   | Group Anagrams                                        | Medium     | ⬜      | Hashing |
| 76   | Minimum Window Substring                              | Hard       | ⬜      | Sliding Window |
| 151  | Reverse Words in a String                             | Medium     | ⬜      | Two Pointers |
| 271  | Encode and Decode Strings                             | Medium     | ⬜      | String |
| 424  | Longest Repeating Character Replacement               | Medium     | ⬜      | Sliding Window |

---

## 3️⃣ HashMap / HashSet (10 / 22)

| #    | Problem                            | Difficulty | Status | Pattern              |
|------|------------------------------------|------------|--------|----------------------|
| 49   | Group Anagrams                     | Medium     | ⬜      | Hashing              |
| 128  | Longest Consecutive Sequence       | Medium     | ⬜      | Hashing              |
| 205  | Isomorphic Strings                 | Easy       | ✅ [Solution](src/strings/IsomorphicStrings.java) | Hashing |
| 217  | Contains Duplicate                 | Easy       | ✅ [Solution](src/arrays/ContainsDuplicate.java) | Hashing |
| 219  | Contains Duplicate II              | Easy       | ✅ [Solution](src/hashing/ContainsDuplicatesII.java) | Hashing + Sliding Window |
| 242  | Valid Anagram                      | Easy       | ✅ [Solution](src/strings/ValidAnagram.java) | Hashing |
| 290  | Word Pattern                       | Easy       | ⬜      | Hashing              |
| 347  | Top K Frequent Elements            | Medium     | ⬜      | Hashing + Heap       |
| 349  | Intersection of Two Arrays         | Easy       | ✅ [Solution](src/hashing/IntersectionOfArrays.java) | Hashing |
| 350  | Intersection of Two Arrays II      | Easy       | ✅ [Solution](src/hashing/IntersectionOfArraysII.java) | Hashing |
| 387  | First Unique Character in a String | Easy       | ✅ [Solution](src/hashing/FirstUniqueCharacter.java) | Hashing |
| 525  | Contiguous Array                   | Medium     | ⬜      | Hashing + Prefix Sum |
| 560  | Subarray Sum Equals K              | Medium     | ⬜      | Hashing + Prefix Sum |
| 594  | Longest Harmonious Subsequence     | Easy       | ⬜      | Hashing              |
| 599  | Minimum Index Sum of Two Lists     | Easy       | ✅ [Solution](src/hashing/MinimumIndexSum.java) | Hashing |
| 692  | Top K Frequent Words               | Medium     | ⬜      | Hashing + Heap       |
| 705  | Design HashSet                     | Easy       | ✅ [Solution](src/set/designhashset/MyHashSet.java) | Hashing |
| 706  | Design HashMap                     | Easy       | ✅ [Solution](src/map/designhashmap/MyHashMap.java) | Hashing |
| 763  | Partition Labels                   | Medium     | ⬜      | Greedy + Hashing     |
| 811  | Subdomain Visit Count              | Medium     | ⬜      | Hashing              |
| 884  | Uncommon Words from Two Sentences  | Easy       | ⬜      | Hashing              |
| 1002 | Find Common Characters             | Easy       | ⬜      | Hashing              |

---

## 4️⃣ Binary Search (1 / 20)

| #    | Problem                                               | Difficulty | Status | Pattern                 |
|------|-------------------------------------------------------|------------|--------|-------------------------|
| 34   | Find First and Last Position of Element in Sorted Array | Medium   | ⬜      | Binary Search           |
| 35   | Search Insert Position                                | Easy       | ⬜      | Binary Search           |
| 69   | Sqrt(x)                                               | Easy       | ✅ [Solution](src/binarysearch/Sqrt.java) | Binary Search |
| 81   | Search in Rotated Sorted Array II                     | Medium     | ⬜      | Binary Search           |
| 153  | Find Minimum in Rotated Sorted Array                  | Medium     | ⬜      | Binary Search           |
| 162  | Find Peak Element                                     | Medium     | ⬜      | Binary Search           |
| 278  | First Bad Version                                     | Easy       | ⬜      | Binary Search           |
| 374  | Guess Number Higher or Lower                          | Easy       | ⬜      | Binary Search           |
| 410  | Split Array Largest Sum                               | Hard       | ⬜      | Binary Search on Answer |
| 704  | Binary Search                                         | Easy       | ⬜      | Binary Search           |
| 875  | Koko Eating Bananas                                   | Medium     | ⬜      | Binary Search on Answer |
| 1011 | Capacity To Ship Packages Within D Days               | Medium     | ⬜      | Binary Search on Answer |
| 1283 | Find the Smallest Divisor Given a Threshold           | Medium     | ⬜      | Binary Search on Answer |
| 1482 | Minimum Number of Days to Make m Bouquets             | Medium     | ⬜      | Binary Search on Answer |
| 1760 | Minimum Limit of Balls in a Bag                       | Medium     | ⬜      | Binary Search on Answer |
| 1802 | Maximum Value at a Given Index in a Bounded Array     | Medium     | ⬜      | Binary Search on Answer |
| 1891 | Cutting Ribbons                                       | Medium     | ⬜      | Binary Search on Answer |
| 2187 | Minimum Time to Complete Trips                        | Medium     | ⬜      | Binary Search on Answer |
| 2226 | Maximum Candies Allocated to K Children               | Medium     | ⬜      | Binary Search on Answer |
| 2300 | Successful Pairs of Spells and Potions                | Medium     | ⬜      | Binary Search           |

---

## 5️⃣ Linked List (12 / 21)

| #    | Problem                              | Difficulty | Status | Pattern              |
|------|--------------------------------------|------------|--------|----------------------|
| 2    | Add Two Numbers                      | Medium     | ✅ [Solution](src/linkedlist/AddTwoNumbers.java) | Linked List Math     |
| 19   | Remove Nth Node From End of List     | Medium     | ✅ [Solution](src/linkedlist/RemoveNthNodeFromEnd.java) | Two Pointers |
| 21   | Merge Two Sorted Lists               | Easy       | ✅ [Solution](src/linkedlist/MergeTwoSortedLists.java) | Two Pointers |
| 61   | Rotate List                          | Medium     | ⬜      | Two Pointers         |
| 82   | Remove Duplicates from Sorted List II | Medium    | ⬜      | Two Pointers         |
| 92   | Reverse Linked List II               | Medium     | ⬜      | Pointer Manipulation |
| 138  | Copy List with Random Pointer        | Medium     | ⬜      | Hashing              |
| 141  | Linked List Cycle                    | Easy       | ✅ [Solution](src/linkedlist/LinkedListCycle.java) | Fast & Slow Pointers |
| 142  | Linked List Cycle II                 | Medium     | ✅ [Solution](src/linkedlist/LinkedListCycleII.java) | Fast & Slow Pointers |
| 143  | Reorder List                         | Medium     | ⬜      | Fast & Slow Pointers |
| 148  | Sort List                            | Medium     | ⬜      | Merge Sort           |
| 160  | Intersection of Two Linked Lists     | Easy       | ✅ [Solution](src/linkedlist/LinkedListsIntersection.java) | Two Pointers |
| 206  | Reverse Linked List                  | Easy       | ✅ [Solution](src/linkedlist/ReverseLinkedList.java) | Pointer Manipulation |
| 234  | Palindrome Linked List               | Easy       | ✅ [Solution](src/linkedlist/PalindromeLinkedList.java) | Fast & Slow Pointers |
| 237  | Delete Node in a Linked List         | Medium     | ✅ [Solution](src/linkedlist/DeleteNode.java) | Pointer Manipulation |
| 328  | Odd Even Linked List                 | Medium     | ⬜      | Pointer Manipulation |
| 445  | Add Two Numbers II                   | Medium     | ⬜      | Linked List Math     |
| 725  | Split Linked List in Parts           | Medium     | ⬜      | Pointer Manipulation |
| 876  | Middle of the Linked List            | Easy       | ✅ [Solution](src/twopointer/MiddleOfLinkedList.java) | Fast & Slow Pointers |
| 2130 | Maximum Twin Sum of a Linked List    | Medium     | ✅ [Solution](src/linkedlist/MaximumTwinSumOfLinkedList.java) | Fast & Slow Pointers |
| 707  | Design Linked List                   | Medium     | ✅ [Solution](src/linkedlist/DesignLinkedList.java) | Linked List Design   |

---

## 6️⃣ Stack (0 / 15)

| #   | Problem                          | Difficulty | Status | Pattern         |
|-----|----------------------------------|------------|--------|-----------------|
| 20  | Valid Parentheses                | Easy       | ⬜      | Stack           |
| 71  | Simplify Path                    | Medium     | ⬜      | Stack           |
| 84  | Largest Rectangle in Histogram   | Hard       | ⬜      | Monotonic Stack |
| 85  | Maximal Rectangle                | Hard       | ⬜      | Monotonic Stack |
| 150 | Evaluate Reverse Polish Notation | Medium     | ⬜      | Stack           |
| 155 | Min Stack                        | Medium     | ⬜      | Stack           |
| 224 | Basic Calculator                 | Hard       | ⬜      | Stack           |
| 225 | Implement Stack using Queues     | Easy       | ⬜      | Stack           |
| 232 | Implement Queue using Stacks     | Easy       | ⬜      | Stack           |
| 394 | Decode String                    | Medium     | ⬜      | Stack           |
| 496 | Next Greater Element I           | Easy       | ⬜      | Monotonic Stack |
| 503 | Next Greater Element II          | Medium     | ⬜      | Monotonic Stack |
| 739 | Daily Temperatures               | Medium     | ⬜      | Monotonic Stack |
| 853 | Car Fleet                        | Medium     | ⬜      | Monotonic Stack |
| 907 | Sum of Subarray Minimums         | Medium     | ⬜      | Monotonic Stack |

---

## 7️⃣ Heap / Priority Queue (0 / 15)

| #    | Problem                                       | Difficulty | Status | Pattern           |
|------|-----------------------------------------------|------------|--------|-------------------|
| 23   | Merge K Sorted Lists                          | Hard       | ⬜      | Heap              |
| 215  | Kth Largest Element in an Array               | Medium     | ⬜      | Heap              |
| 295  | Find Median from Data Stream                  | Hard       | ⬜      | Heap              |
| 347  | Top K Frequent Elements                       | Medium     | ⬜      | Heap + Hashing    |
| 355  | Design Twitter                                | Medium     | ⬜      | Heap              |
| 373  | Find K Pairs with Smallest Sums               | Medium     | ⬜      | Heap              |
| 378  | Kth Smallest Element in a Sorted Matrix       | Medium     | ⬜      | Heap              |
| 407  | Trapping Rain Water II                        | Hard       | ⬜      | Heap              |
| 621  | Task Scheduler                                | Medium     | ⬜      | Heap + Greedy     |
| 630  | Course Schedule III                           | Hard       | ⬜      | Heap + Greedy     |
| 632  | Smallest Range Covering Elements from K Lists | Hard       | ⬜      | Heap              |
| 703  | Kth Largest Element in a Stream               | Easy       | ⬜      | Heap              |
| 743  | Network Delay Time                            | Medium     | ⬜      | Heap + Dijkstra's |
| 778  | Swim in Rising Water                          | Hard       | ⬜      | Heap              |
| 1046 | Last Stone Weight                             | Easy       | ⬜      | Heap              |

---

## 8️⃣ Trees (1 / 25)

| #    | Problem                                         | Difficulty | Status | Pattern                |
|------|-------------------------------------------------|------------|--------|------------------------|
| 94   | Binary Tree Inorder Traversal                   | Easy       | ⬜      | DFS                    |
| 98   | Validate Binary Search Tree                     | Medium     | ⬜      | DFS                    |
| 100  | Same Tree                                       | Easy       | ⬜      | DFS / BFS              |
| 101  | Symmetric Tree                                  | Easy       | ⬜      | DFS / BFS              |
| 102  | Binary Tree Level Order Traversal               | Medium     | ⬜      | BFS                    |
| 104  | Maximum Depth of Binary Tree                    | Easy       | ⬜      | DFS / BFS              |
| 105  | Construct Binary Tree from Preorder and Inorder | Medium     | ⬜      | Recursion              |
| 110  | Balanced Binary Tree                            | Easy       | ⬜      | DFS                    |
| 112  | Path Sum                                        | Easy       | ⬜      | DFS                    |
| 124  | Binary Tree Maximum Path Sum                    | Hard       | ⬜      | DFS                    |
| 199  | Binary Tree Right Side View                     | Medium     | ⬜      | BFS                    |
| 226  | Invert Binary Tree                              | Easy       | ⬜      | DFS / BFS              |
| 230  | Kth Smallest Element in a BST                   | Medium     | ⬜      | DFS                    |
| 235  | Lowest Common Ancestor of a BST                 | Medium     | ⬜      | BST Properties         |
| 236  | Lowest Common Ancestor of a Binary Tree         | Medium     | ⬜      | DFS                    |
| 297  | Serialize and Deserialize Binary Tree           | Hard       | ⬜      | DFS / BFS              |
| 337  | House Robber III                                | Medium     | ⬜      | DP on Trees            |
| 404  | Sum of Left Leaves                              | Easy       | ⬜      | DFS                    |
| 437  | Path Sum III                                    | Medium     | ⬜      | DFS + Prefix Sum       |
| 450  | Delete Node in a BST                            | Medium     | ⬜      | BST Properties         |
| 543  | Diameter of Binary Tree                         | Easy       | ⬜      | DFS                    |
| 572  | Subtree of Another Tree                         | Easy       | ⬜      | DFS                    |
| 700  | Search in a Binary Search Tree                  | Easy       | ⬜      | BST Properties         |
| 814  | Binary Tree Pruning                             | Medium     | ⬜      | DFS                    |
| 1650 | Lowest Common Ancestor of a Binary Tree III       | Medium     | ✅ [Solution](src/tree/LowestCommonAncestorIII.java) | Two Pointers |

---

## 9️⃣ Backtracking (0 / 15)

| #   | Problem                               | Difficulty | Status | Pattern             |
|-----|---------------------------------------|------------|--------|---------------------|
| 17  | Letter Combinations of a Phone Number | Medium     | ⬜      | Backtracking        |
| 22  | Generate Parentheses                  | Medium     | ⬜      | Backtracking        |
| 39  | Combination Sum                       | Medium     | ⬜      | Backtracking        |
| 40  | Combination Sum II                    | Medium     | ⬜      | Backtracking        |
| 46  | Permutations                          | Medium     | ⬜      | Backtracking        |
| 47  | Permutations II                       | Medium     | ⬜      | Backtracking        |
| 51  | N-Queens                              | Hard       | ⬜      | Backtracking        |
| 52  | N-Queens II                           | Hard       | ⬜      | Backtracking        |
| 77  | Combinations                          | Medium     | ⬜      | Backtracking        |
| 78  | Subsets                               | Medium     | ⬜      | Backtracking        |
| 90  | Subsets II                            | Medium     | ⬜      | Backtracking        |
| 131 | Palindrome Partitioning               | Medium     | ⬜      | Backtracking        |
| 212 | Word Search II                        | Hard       | ⬜      | Backtracking + Trie |
| 216 | Combination Sum III                   | Medium     | ⬜      | Backtracking        |
| 491 | Non-decreasing Subsequences           | Medium     | ⬜      | Backtracking        |

---

## 🔟 Graphs (0 / 20)

| #    | Problem                                      | Difficulty | Status | Pattern             |
|------|----------------------------------------------|------------|--------|---------------------|
| 133  | Clone Graph                                  | Medium     | ⬜      | DFS / BFS           |
| 200  | Number of Islands                            | Medium     | ⬜      | DFS / BFS           |
| 207  | Course Schedule                              | Medium     | ⬜      | Topological Sort    |
| 208  | Implement Trie (Prefix Tree)                 | Medium     | ⬜      | Trie                |
| 210  | Course Schedule II                           | Medium     | ⬜      | Topological Sort    |
| 261  | Graph Valid Tree                             | Medium     | ⬜      | Union Find / DFS    |
| 269  | Alien Dictionary                             | Hard       | ⬜      | Topological Sort    |
| 286  | Walls and Gates                              | Medium     | ⬜      | BFS                 |
| 323  | Number of Connected Components               | Medium     | ⬜      | Union Find / DFS    |
| 329  | Longest Increasing Path in a Matrix          | Hard       | ⬜      | DFS + Memoization   |
| 417  | Pacific Atlantic Water Flow                  | Medium     | ⬜      | DFS / BFS           |
| 547  | Number of Provinces                          | Medium     | ⬜      | Union Find / DFS    |
| 684  | Redundant Connection                         | Medium     | ⬜      | Union Find          |
| 695  | Max Area of Island                           | Medium     | ⬜      | DFS / BFS           |
| 721  | Accounts Merge                               | Medium     | ⬜      | Union Find / DFS    |
| 743  | Network Delay Time                           | Medium     | ⬜      | Dijkstra's          |
| 787  | Cheapest Flights Within K Stops              | Medium     | ⬜      | Bellman-Ford        |
| 802  | Find Eventual Safe States                    | Medium     | ⬜      | DFS                 |
| 827  | Making A Large Island                        | Hard       | ⬜      | DFS                 |
| 1091 | Shortest Path in Binary Matrix               | Medium     | ⬜      | BFS                 |
| 1584 | Min Cost to Connect All Points               | Medium     | ⬜      | MST (Prim's)        |

---

## 1️⃣1️⃣ Dynamic Programming (2 / 30)

| #    | Problem                                                   | Difficulty | Status | Pattern         |
|------|-----------------------------------------------------------|------------|--------|-----------------|
| 62   | Unique Paths                                              | Medium     | ⬜      | DP              |
| 63   | Unique Paths II                                           | Medium     | ⬜      | DP              |
| 64   | Minimum Path Sum                                          | Medium     | ⬜      | DP              |
| 70   | Climbing Stairs                                           | Easy       | ⬜      | DP              |
| 72   | Edit Distance                                             | Medium     | ⬜      | DP              |
| 91   | Decode Ways                                               | Medium     | ⬜      | DP              |
| 139  | Word Break                                                | Medium     | ⬜      | DP              |
| 152  | Maximum Product Subarray                                  | Medium     | ⬜      | DP              |
| 198  | House Robber                                              | Medium     | ⬜      | DP              |
| 213  | House Robber II                                           | Medium     | ⬜      | DP              |
| 221  | Maximal Square                                            | Medium     | ⬜      | DP              |
| 279  | Perfect Squares                                           | Medium     | ⬜      | DP              |
| 300  | Longest Increasing Subsequence                            | Medium     | ⬜      | DP              |
| 309  | Best Time to Buy and Sell Stock with Cooldown             | Medium     | ⬜      | DP              |
| 312  | Burst Balloons                                            | Hard       | ⬜      | DP              |
| 322  | Coin Change                                               | Medium     | ⬜      | DP              |
| 337  | House Robber III                                          | Medium     | ⬜      | DP on Trees     |
| 416  | Partition Equal Subset Sum                                | Medium     | ⬜      | DP              |
| 494  | Target Sum                                                | Medium     | ⬜      | DP              |
| 518  | Coin Change II                                            | Medium     | ⬜      | DP              |
| 673  | Number of Longest Increasing Subsequence                  | Medium     | ⬜      | DP              |
| 714  | Best Time to Buy and Sell Stock with Transaction Fee      | Medium     | ⬜      | DP              |
| 740  | Delete and Earn                                           | Medium     | ⬜      | DP              |
| 931  | Minimum Falling Path Sum                                  | Medium     | ⬜      | DP              |
| 1143 | Longest Common Subsequence                                | Medium     | ⬜      | DP              |
| 1155 | Number of Dice Rolls With Target Sum                      | Medium     | ⬜      | DP              |
| 1218 | Longest Arithmetic Subsequence of Given Difference        | Medium     | ⬜      | DP              |
| 1639 | Number of Ways to Form a Target String Given a Dictionary | Hard       | ⬜      | DP              |
| 3129 | Find All Possible Stable Binary Arrays I                  | Hard       | ✅ [Solution](src/dp/StableBinaryArrayI.java) | DP + Prefix Sum |
| 3130 | Find All Possible Stable Binary Arrays II                 | Hard       | ✅ [Solution](src/dp/StableBinaryArrayII.java) | DP + Prefix Sum |

---

## 1️⃣2️⃣ Bit Manipulation (2 / 10)

| #    | Problem                             | Difficulty | Status | Pattern          |
|------|-------------------------------------|------------|--------|------------------|
| 136  | Single Number                       | Easy       | ✅ [Solution](src/bitmanipulation/SingleNumber.java) | XOR |
| 190  | Reverse Bits                        | Easy       | ⬜      | Bit Manipulation |
| 191  | Number of 1 Bits                    | Easy       | ⬜      | Bit Manipulation |
| 231  | Power of Two                        | Easy       | ⬜      | Bit Manipulation |
| 268  | Missing Number                      | Easy       | ⬜      | XOR / Math       |
| 338  | Counting Bits                       | Easy       | ⬜      | DP + Bits        |
| 371  | Sum of Two Integers                 | Medium     | ⬜      | Bit Manipulation |
| 461  | Hamming Distance                    | Easy       | ⬜      | XOR              |
| 693  | Binary Number with Alternating Bits | Easy       | ⬜      | Bit Manipulation |
| 1009 | Complement of Base 10 Integer       | Easy       | ✅ [Solution](src/bitmanipulation/ComplementBase10Integer.java) | Bit Mask |

---

## 🧠 Problem-Solving Philosophy

- Implement brute force first → optimize step by step
- Top-down recursion → bottom-up DP → space optimization
- Add memoization manually after understanding the recurrence
- Recognize patterns across problems (Floyd's cycle, two pointers, prefix sum, XOR)
- Write production-quality Java with full Javadoc and labeled test cases

---

*Last updated: March 2026*