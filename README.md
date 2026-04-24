# Java DSA Roadmap: 410+ LeetCode Problems & Solutions for Coding Interviews

A complete Java DSA roadmap covering 410 essential LeetCode problems for mastering data structures and algorithms.

This repository is designed for coding interview preparation, featuring structured problem sets, pattern-based learning (arrays, graphs, DP, trees), and clean Java solutions.

**Perfect for:**
- Software engineering interview prep (FAANG, Big Tech)
- Mastering problem-solving patterns in Java (Spring Boot developers)
- Comprehensive algorithm practice from beginner to advanced

**Includes:**
- 410+ LeetCode problems with optimized Java implementations
- Pattern-based roadmaps (Graph, DP, Sliding Window, Heap, etc.)
- 30-day structured interview plan for high-impact revision
- Real-time progress tracking across all major DSA categories

**Author:** Mohan Lal | **GitHub:** mhnuk2007 | **LeetCode:** mhnuk2007 | **Language:** Java

🔎 **Keywords:** Java DSA, Data Structures and Algorithms in Java, LeetCode Java Solutions, Coding Interview Preparation, Algorithms Practice Java, Java Backend Interview Preparation, Problem Solving in Java, DSA Roadmap, Data Structures Java, Coding Interview Java

---

## Table of Contents

- [📊 Progress Tracker](#-progress-tracker)
- [🚀 30-Day Interview-Ready Roadmap](#-30-day-interview-ready-roadmap)
- [📅 Daily Progress](#-daily-progress)
- [🤝 Contributing](#-contributing)
- [📄 License & Disclaimer](#-license--disclaimer)
- [1️⃣ Arrays & Two Pointers](#1️⃣-arrays--two-pointers-40--49)
- [2️⃣ Sliding Window](#2️⃣-sliding-window-3--16)
- [3️⃣ Strings](#3️⃣-strings-14--26)
- [4️⃣ HashMap / HashSet](#4️⃣-hashmap--hashset-11--23)
- [5️⃣ Binary Search](#5️⃣-binary-search-11--29)
- [6️⃣ Linked List](#6️⃣-linked-list-13--26)
- [7️⃣ Stack & Queue](#7️⃣-stack--queue-12--31)
- [8️⃣ Heap / Priority Queue](#8️⃣-heap--priority-queue-9--21)
- [9️⃣ Trees](#9️⃣-trees-11--50)
- [🔟 Backtracking](#-backtracking-14--22)
- [1️⃣1️⃣ Graphs](#1️⃣1️⃣-graphs-20--42)
- [1️⃣2️⃣ Dynamic Programming](#1️⃣2️⃣-dynamic-programming-3--43)
- [1️⃣3️⃣ Greedy](#1️⃣3️⃣-greedy-2--13)
- [1️⃣4️⃣ Trie](#1️⃣4️⃣-trie-2--5)
- [1️⃣5️⃣ System Design / LLD](#1️⃣5️⃣-system-design--lld-0--3)
- [1️⃣6️⃣ Bit Manipulation](#1️⃣6️⃣-bit-manipulation-4--12)
- [🌟 Daily Challenges](#-daily-challenges-5--6)

---

## 📊 Progress Tracker

| Category | Total | Completed | Progress |
|---|---|---|---|
| Arrays & Two Pointers | 49 | 40 | ████████░░ 82% |
| Sliding Window | 16 | 3 | ██░░░░░░░░ 19% |
| Strings | 26 | 14 | █████░░░░░ 54% |
| HashMap / HashSet | 23 | 11 | █████░░░░░ 48% |
| Binary Search | 29 | 11 | ████░░░░░░ 38% |
| Linked List | 26 | 13 | █████░░░░░ 50% |
| Stack & Queue | 31 | 12 | ████░░░░░░ 39% |
| Heap / Priority Queue | 21 | 9 | ████░░░░░░ 43% |
| Trees | 50 | 11 | ██░░░░░░░░ 22% |
| Backtracking | 22 | 14 | ██████░░░░ 64% |
| Graphs | 42 | 20 | █████░░░░░ 48% |
| Dynamic Programming | 43 | 3 | █░░░░░░░░░ 7% |
| Greedy | 13 | 2 | ██░░░░░░░░ 15% |
| Trie | 5 | 2 | ████░░░░░░ 40% |
| System Design / LLD | 3 | 0 | ░░░░░░░░░░ 0% |
| Bit Manipulation | 12 | 4 | ███░░░░░░░ 33% |
| Daily Challenges | 6 | 5 | ████████░░ 83% |
| **Total** | **417** | **174** | **████░░░░░░ 42%** |

> 📌 Graphs section updated to reflect all completed concept files and LC problems. Trie updated after LC 208 and LC 212 completion. Total reflects all sessions through April 24, 2026.

---

## 🚀 30-Day Interview-Ready Roadmap

Priority based on the current tracker: Heap, Graphs, Dynamic Programming, Sliding Window, then Trees and Backtracking polish.

### ⚡ Rules
- 3 problems/day, max 4
- 1 easy skip/day only if the pattern already feels automatic
- For every problem, write: pattern, time/space, and 1 mistake you made
- Re-solve without notes after 24 hours

### 🧠 Phase 1 (Days 1–10): Core Interview Patterns

| Day | Problems | Focus |
|---|---|---|
| Day 1 | 110 · 543 · 104 (re-solve) | Height-based recursion · returning values cleanly |
| Day 2 | 98 (re-solve) · 700 (re-solve) · 235 | BST rules · search space pruning |
| Day 3 | 236 · 112 · 113 | DFS + backtracking on trees |
| Day 4 | 215 · 1046 · PriorityQueue drill | Min heap vs max heap · Java comparator fluency |
| Day 5 | 347 · 703 · 295 (stretch) | Top-K pattern · stream heap · two-heaps pattern |
| Day 6 | 200 · 695 | DFS on grids · visited thinking |
| Day 7 | 994 · 1091 | Multi-source BFS · shortest path on grids |
| Day 8 | 207 · 210 | Topological sort · course schedule pattern |
| Day 9 | 424 · 567 | Variable sliding window · frequency map |
| Day 10 | 438 · 1004 | Expand/shrink discipline · review weak window mistakes |

### 🔁 Phase 2 (Days 11–20): Backtracking + DP

| Day | Problems | Focus |
|---|---|---|
| Day 11 | 22 (re-solve) · 78 (re-solve) | Backtracking template · choose → explore → undo |
| Day 12 | 39 (re-solve) · 40 | Reuse vs no-reuse branches |
| Day 13 | 79 (re-solve) · 131 (re-solve) | Grid backtracking · path partitioning |
| Day 14 | 70 (re-solve) · 198 | 1D DP basics · state definition |
| Day 15 | 213 · 53 | Circular DP vs Kadane |
| Day 16 | 322 · 518 | Unbounded knapsack intuition |
| Day 17 | 300 · LIS binary-search optimization | DP to optimized pattern jump |
| Day 18 | 1143 | 2D DP · subsequence thinking |
| Day 19 | 139 | Prefix/state transitions |
| Day 20 | DP review · re-solve 2 from memory | Memoization → tabulation fluency |

### 🧪 Phase 3 (Days 21–30): Interview Simulation Mode

| Day | Problems | Focus |
|---|---|---|
| Day 21 | 200 · 215 · 347 · 295 (try) | Graph + Heap mixed set |
| Day 22 | 198 · 424 · 567 · 300 (try) | DP + Sliding Window mixed set |
| Day 23 | 98 · 39 (re-solve) · 79 · 131 (re-solve) | Tree + Backtracking mixed set |
| Day 24 | 207 · 322 · 139 · 210 | Graph + DP decision-making |
| Day 25 | 703 · 110 · 438 · 994 | Timed mixed practice |
| Day 26 | 2 problems in 45 min | No help · no notes · review after |
| Day 27 | 2 new problems in 45 min | Same rules · different mix |
| Day 28 | 1 hard problem for 45 min | Deep review after timeout |
| Day 29 | 3 problems from your weakest category | Weak-area attack only |
| Day 30 | 1 Graph · 1 DP · 1 Tree · 1 Backtracking | Final memory re-solve day |

### 📊 Checkpoints
- **Day 10:** tree/BST/heap/graph basics should feel structured instead of random.
- **Day 20:** you should be able to write subsets, combination sum, and core 1D DP patterns from memory.
- **Day 30:** you should be able to explain BFS vs DFS, topological sort, two-heaps, sliding window, and DP transitions without notes.

### 🔁 Revision Buckets

| Bucket | Re-solve after | Purpose |
|---|---|---|
| Day+1 | Next day | Confirms initial understanding |
| Day+3 | 3 days later | Short-term retention check |
| Day+7 | 1 week later | Pattern solidification |
| Day+21 | 3 weeks later | Long-term retention before interview |

---

## 📅 Daily Progress

| Date | Problems Solved | Notes |
|---|---|---|
| Apr 24 | 79, 212 | Word Search (Backtracking + DFS) · Word Search II (Trie + Backtracking) |
| Apr 23 | 208, 684, 207, 210, DSU, CycleDetection, TopologicalSort | Trie implementation · Redundant Connection (DFS+BFS+DSU) · Cycle detection undirected/directed · Topological sort both approaches · DSU from scratch |
| Apr 19 | 733, 210 (DFS + BFS) | Graph DFS on grids · Course Schedule II both approaches |
| Apr 13 | 264, 355, 1492 | Heap deep dive |
| Apr 12 | 1046, 1337 | Heap deep dive |
| Apr 10 | 23, 215, 295 | Heap deep dive |
| Apr 9 | 17, 22, 347 | Backtracking push before switching focus |
| Apr 8 | 52, 77, 98 | Backtracking + BST expansion |
| Apr 5 | 24, 21, 50, 70, 119, 206, 700, 779 | Recursion re-solves + BST search |
| Apr 4 | 131, Sudoku solver, recursion re-solves (24, 206, 344) | Backtracking + recursion expansion |
| Mar 21 | 155 (4 approaches), 622, queue implementations | Stack & Queue deep dive |
| Mar 20 | 739, 150, 496, 503, 622 | Stack & Queue |
| Mar 19 | 20, 155 | Started Stacks! |
| Mar 18 | 561, 119, 209, 54, 67, 868, 415, 151, 186, 557 | Big push on Strings & Arrays |
| Mar 16 | 118, 66, 747, 724, 169, 42, 189, 31, 2, 237 | Arrays, LL cleanup |
| Mar 14 | 118, 66, 747, 724 | Array Day |
| Mar 13 | 31, 42, 2, 599, 387, 350, 219, 205, 136 | Arrays, LL & Hashing |
| Mar 12 | 136, 706, 705, 1009, 69, 2486, 33 | Very productive day! |
| Mar 11 | 3130, 2130, 21 | DP and Linked Lists |
| Mar 10 | 234, 287, 206, 3129, 19 | Heavy on Linked Lists |

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

- **Add Solutions:** Implement unsolved problems following the existing code style
- **Improve Code:** Optimize existing solutions or add better explanations
- **Fix Bugs:** Report and fix any issues in the code
- **Documentation:** Improve comments, README, or add new sections

Please: Fork the repository → Create a feature branch → Make your changes → Test thoroughly → Submit a pull request with a clear description.

---

## 📄 License & Disclaimer

This repository contains solutions to LeetCode problems for educational purposes. All problem statements and test cases are property of LeetCode. Please respect their terms of service.

The code in this repository is provided as-is for learning purposes. Use at your own risk.

---

## Company Interview Tags

The Company column lists major companies known to ask this problem in interviews. Tags are sourced from LeetCode premium data, community reports, and widely verified interview datasets.

**Abbreviations:** G = Google, A = Amazon, M = Microsoft, Meta = Meta/Facebook, Ap = Apple, B = Bloomberg, Ub = Uber, Ly = LinkedIn, Ad = Adobe, Or = Oracle, Nt = Netflix, Sl = Salesforce, Db = Databricks, DD = DoorDash, Tw = Twitter/X, Wa = Walmart, Sq = Square/Block, Sh = Shopify, By = ByteDance, Ci = Citadel, Bk = Booking.com, At = Atlassian, Ai = Airbnb

---

## 1️⃣ Arrays & Two Pointers (40 / 49)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | Kadane's Algorithm | - | ⬜ | - | - |
| - | Pow xn | - | ⬜ | - | - |
| - | 4Sum | - | ⬜ | - | - |
| - | Search a 2D matrix | - | ✅ Solution | Binary Search | Amazon, Google, Meta |
| - | Merge overlapping intervals | - | ⬜ | - | - |
| - | Set matrix zeroes | - | ⬜ | - | - |
| - | Product of array except itself | - | ⬜ | - | - |
| - | Count Inversions | - | ⬜ | - | - |
| - | Reverse pairs | - | ⬜ | - | - |
| 1 | Two Sum | Easy | ✅ Solution | Hashing | Amazon, Google, Apple, Microsoft, Meta, Adobe, Bloomberg |
| 11 | Container With Most Water | Medium | ✅ Solution | Two Pointers | Amazon, Google, Meta, Bloomberg, Adobe |
| 15 | 3Sum | Medium | ✅ Solution | Two Pointers | Amazon, Google, Meta, Microsoft, Adobe, Bloomberg |
| 26 | Remove Duplicates from Sorted Array | Easy | ✅ Solution | Two Pointers | Amazon, Google, Microsoft, Apple |
| 27 | Remove Element | Easy | ✅ Solution | Two Pointers | Amazon, Google |
| 31 | Next Permutation | Medium | ✅ Solution | Array Manipulation | Google, Microsoft, Apple |
| 33 | Search in Rotated Sorted Array | Medium | ✅ Solution | Binary Search | Amazon, Google, Microsoft, Meta, Bloomberg, Uber |
| 42 | Trapping Rain Water | Hard | ✅ Solution | Two Pointers / Stack | Amazon, Google, Apple, Bloomberg, Meta, Zenefits |
| 54 | Spiral Matrix | Medium | ✅ Solution | Matrix Traversal | Amazon, Google, Microsoft, Apple, Meta |
| 66 | Plus One | Easy | ✅ Solution | Array Manipulation | Google, Apple |
| 75 | Sort Colors | Medium | ✅ Solution | Two Pointers | Amazon, Microsoft, Bloomberg |
| 80 | Remove Duplicates from Sorted Array II | Medium | ✅ Solution | Two Pointers | Amazon, Google |
| 88 | Merge Sorted Array | Easy | ✅ Solution | Two Pointers | Amazon, Google, Microsoft, Apple, Meta, Bloomberg |
| 118 | Pascal's Triangle | Easy | ✅ Solution | DP / Simulation | Amazon, Google, Apple |
| 119 | Pascal's Triangle II | Easy | ✅ Solution | DP / Simulation | Amazon, Google |
| 121 | Best Time to Buy and Sell Stock | Easy | ✅ Solution | Array | Amazon, Google, Microsoft, Apple, Bloomberg, Meta |
| 167 | Two Sum II - Input Array Is Sorted | Medium | ✅ Solution | Two Pointers | Amazon, Google, Microsoft |
| 169 | Majority Element | Easy | ✅ Solution | Sorting / Boyer-Moore | Amazon, Google, Microsoft, Apple |
| 189 | Rotate Array | Medium | ✅ Solution | Array Manipulation | Amazon, Microsoft, Bloomberg |
| 202 | Happy Number | Easy | ✅ Solution | Fast & Slow Pointers | Amazon, Google, Adobe |
| 246 | Strobogrammatic Number | Easy | ✅ Solution | Two Pointers | Google, Facebook |
| 283 | Move Zeroes | Easy | ✅ Solution | Two Pointers | Amazon, Google, Microsoft, Bloomberg, Adobe |
| 287 | Find the Duplicate Number | Medium | ✅ Solution | Floyd's Cycle | Amazon, Google, Bloomberg |
| 414 | Third Maximum Number | Easy | ✅ Solution | Tracking | Amazon |
| 448 | Find All Numbers Disappeared in an Array | Easy | ✅ Solution | Array Traversal | Amazon, Google |
| 457 | Circular Array Loop | Medium | ✅ Solution | Fast & Slow Pointers | Amazon |
| 485 | Max Consecutive Ones | Easy | ✅ Solution | Array Traversal | Amazon, Google |
| 561 | Array Partition | Easy | ✅ Solution | Sorting | Amazon |
| 724 | Find Pivot Index | Easy | ✅ Solution | Prefix Sum | Amazon, Google |
| 747 | Largest Number At Least Twice of Others | Easy | ✅ Solution | Array Traversal | Amazon |
| 1051 | Height Checker | Easy | ✅ Solution | Sorting | Amazon |
| 1089 | Duplicate Zeros | Easy | ✅ Solution | Two Pointers | Amazon, Google |
| 1295 | Find Numbers with Even Number of Digits | Easy | ✅ Solution | Array Traversal | Amazon |
| 1299 | Replace Elements with Greatest on Right | Easy | ✅ Solution | Reverse Traversal | Amazon |
| 1346 | Check If N and Its Double Exist | Easy | ✅ Solution | Hashing | Amazon |
| 1480 | Running Sum of 1d Array | Easy | ✅ Solution | Prefix Sum | Amazon, Google |
| 1582 | Special Positions in a Binary Matrix | Easy | ✅ Solution | Matrix Traversal | Amazon |
| 1672 | Richest Customer Wealth | Easy | ✅ Solution | Matrix Traversal | Amazon |
| 2176 | Count Equal and Divisible Pairs in an Array | Easy | ✅ Solution | Array Traversal | Amazon |
| 2486 | Append Characters to String to Make Subsequence | Medium | ✅ Solution | Two Pointers | Google |

---

## 2️⃣ Sliding Window (3 / 16)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| 3 | Longest Substring Without Repeating Characters | Medium | ✅ Solution | Sliding Window | Amazon, Google, Microsoft, Apple, Meta, Bloomberg, Adobe |
| 76 | Minimum Window Substring | Hard | ⬜ | Sliding Window | Amazon, Google, Meta, Bloomberg, LinkedIn |
| 209 | Minimum Size Subarray Sum | Medium | ✅ Solution | Sliding Window | Amazon, Google, Microsoft |
| 239 | Sliding Window Maximum | Hard | ✅ Solution | Monotonic Deque | Amazon, Google, Meta, Bloomberg |
| 424 | Longest Repeating Character Replacement | Medium | ⬜ | Sliding Window | Google, Meta, Bloomberg |
| 438 | Find All Anagrams in a String | Medium | ⬜ | Sliding Window | Amazon, Google, Meta, Bloomberg |
| 567 | Permutation in String | Medium | ⬜ | Sliding Window | Amazon, Google, Microsoft, Bloomberg |
| 713 | Subarray Product Less Than K | Medium | ⬜ | Sliding Window | Amazon, Google |
| 904 | Fruit Into Baskets | Medium | ⬜ | Sliding Window | Google |
| 1004 | Max Consecutive Ones III | Medium | ⬜ | Sliding Window | Amazon, Google |
| 1208 | Get Equal Substrings Within Budget | Medium | ⬜ | Sliding Window | Google |
| 1343 | Number of Sub-arrays of Size K and Average ≥ Threshold | Medium | ⬜ | Sliding Window | Amazon |
| 1423 | Maximum Points You Can Obtain from Cards | Medium | ⬜ | Sliding Window | Google, Meta |
| 1493 | Longest Subarray of 1's After Deleting One Element | Medium | ⬜ | Sliding Window | Amazon, Google |
| 1838 | Frequency of the Most Frequent Element | Medium | ⬜ | Sliding Window | Amazon |
| 2090 | K Radius Subarray Averages | Medium | ⬜ | Sliding Window | Amazon |

---

## 3️⃣ Strings (14 / 26)

> 📁 Additional practice files in repo (not LeetCode submissions): Anagram, Palindrome, PalindromeI, SplitString, SmallestAndLargestSub, JavaStringTokens

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | Remove All Occurrences of String | - | ⬜ | - | - |
| - | String Compression | - | ⬜ | - | - |
| - | KMP Algorithm | - | ⬜ | - | - |
| - | Rabin-Karp | - | ⬜ | - | - |
| 3 | Longest Substring Without Repeating Characters | Medium | ✅ Solution | Sliding Window | Amazon, Google, Microsoft, Apple, Meta, Bloomberg, Adobe |
| 5 | Longest Palindromic Substring | Medium | ⬜ | DP / Expand Around Center | Amazon, Google, Microsoft, Apple, Bloomberg |
| 8 | String to Integer (atoi) | Medium | ⬜ | String Parsing | Amazon, Microsoft, Bloomberg |
| 14 | Longest Common Prefix | Easy | ⬜ | String | Amazon, Google, Microsoft |
| 28 | Find the Index of the First Occurrence in a String | Easy | ✅ Solution | Two Pointers | Amazon, Microsoft |
| 49 | Group Anagrams | Medium | ⬜ | Hashing | Amazon, Google, Microsoft, Meta, Bloomberg, Uber |
| 58 | Length of Last Word | Easy | ⬜ | String | Amazon, Google |
| 76 | Minimum Window Substring | Hard | ⬜ | Sliding Window | Amazon, Google, Meta, Bloomberg, LinkedIn |
| 125 | Valid Palindrome | Easy | ✅ Solution | Two Pointers | Amazon, Google, Microsoft, Meta, Apple |
| 151 | Reverse Words in a String | Medium | ✅ Solution | Two Pointers | Amazon, Google, Microsoft, Bloomberg |
| 186 | Reverse Words in a String II | Medium | ✅ Solution | In-place / O(1) space | Amazon, Microsoft |
| 205 | Isomorphic Strings | Easy | ✅ Solution | Hashing | Amazon, Google |
| 242 | Valid Anagram | Easy | ✅ Solution | Hashing | Amazon, Google, Microsoft, Apple |
| 271 | Encode and Decode Strings | Medium | ⬜ | String | Google, Amazon, Meta, Uber |
| 344 | Reverse String | Easy | ✅ Solution | Two Pointers | Amazon, Google, Microsoft |
| 408 | Valid Word Abbreviation | Easy | ✅ Solution | Two Pointers | Google, Meta |
| 415 | Add Strings | Easy | ✅ Solution | String Math | Amazon, Google |
| 424 | Longest Repeating Character Replacement | Medium | ⬜ | Sliding Window | Google, Meta, Bloomberg |
| 557 | Reverse Words in a String III | Easy | ✅ Solution | Two Pointers | Amazon, Microsoft |
| 680 | Valid Palindrome II | Easy | ✅ Solution | Two Pointers | Amazon, Google, Meta, Bloomberg |
| 1689 | Partitioning Into Minimum Number of Deci-Binary Numbers | Easy | ✅ Solution | Greedy | Amazon |
| 1758 | Minimum Changes to Make Alternating Binary String | Easy | ✅ Solution | String | Amazon |

---

## 4️⃣ HashMap / HashSet (11 / 23)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| 49 | Group Anagrams | Medium | ⬜ | Hashing | Amazon, Google, Microsoft, Meta, Bloomberg, Uber |
| 128 | Longest Consecutive Sequence | Medium | ⬜ | Hashing | Amazon, Google, Meta, Bloomberg |
| 205 | Isomorphic Strings | Easy | ✅ Solution | Hashing | Amazon, Google |
| 217 | Contains Duplicate | Easy | ✅ Solution | Hashing | Amazon, Google, Microsoft, Apple, Bloomberg |
| 219 | Contains Duplicate II | Easy | ✅ Solution | Hashing + Sliding Window | Amazon, Google |
| 242 | Valid Anagram | Easy | ✅ Solution | Hashing | Amazon, Google, Microsoft, Apple |
| 290 | Word Pattern | Easy | ⬜ | Hashing | Amazon, Google |
| 347 | Top K Frequent Elements | Medium | ✅ Solution | Hashing + Heap | Amazon, Google, Meta, Bloomberg, Uber, LinkedIn |
| 349 | Intersection of Two Arrays | Easy | ✅ Solution | Hashing | Amazon, Google, Microsoft |
| 350 | Intersection of Two Arrays II | Easy | ✅ Solution | Hashing | Amazon, Google, Apple, Microsoft |
| 380 | Insert Delete GetRandom O(1) | Medium | ⬜ | Hashing + Array | Amazon, Google, Meta, Bloomberg, Uber |
| 387 | First Unique Character in a String | Easy | ✅ Solution | Hashing | Amazon, Google, Microsoft, Bloomberg |
| 525 | Contiguous Array | Medium | ⬜ | Hashing + Prefix Sum | Amazon, Google, Meta |
| 560 | Subarray Sum Equals K | Medium | ⬜ | Hashing + Prefix Sum | Amazon, Google, Meta, Bloomberg, LinkedIn |
| 594 | Longest Harmonious Subsequence | Easy | ⬜ | Hashing | Amazon |
| 599 | Minimum Index Sum of Two Lists | Easy | ✅ Solution | Hashing | Amazon |
| 692 | Top K Frequent Words | Medium | ⬜ | Hashing + Heap | Amazon, Google, Meta, Bloomberg |
| 705 | Design HashSet | Easy | ✅ Solution | Hashing | Amazon, Apple |
| 706 | Design HashMap | Easy | ✅ Solution | Hashing | Amazon, Apple |
| 763 | Partition Labels | Medium | ⬜ | Greedy + Hashing | Amazon, Google, Meta |
| 811 | Subdomain Visit Count | Medium | ⬜ | Hashing | Amazon, Bloomberg |
| 884 | Uncommon Words from Two Sentences | Easy | ⬜ | Hashing | Amazon, Google |
| 1002 | Find Common Characters | Easy | ⬜ | Hashing | Amazon |

---

## 5️⃣ Binary Search (11 / 29)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | Peak index in mountain array | - | ⬜ | - | - |
| - | Single element in sorted array | - | ⬜ | - | - |
| - | Allocate Minimum Pages (Book Allocation) | - | ⬜ | - | - |
| - | Painter's Partition | - | ⬜ | - | - |
| - | Aggressive cows | - | ⬜ | - | - |
| - | Median of 2 Sorted Arrays | - | ⬜ | - | - |
| 33 | Search in Rotated Sorted Array | Medium | ✅ Solution | Binary Search | Amazon, Google, Microsoft, Meta, Bloomberg, Uber |
| 34 | Find First and Last Position of Element in Sorted Array | Medium | ✅ Solution | Binary Search | Amazon, Google, Microsoft, Meta |
| 35 | Search Insert Position | Easy | ✅ Solution | Binary Search | Amazon, Google, Apple |
| 69 | Sqrt(x) | Easy | ✅ Solution | Binary Search | Amazon, Google, Apple, Bloomberg |
| 81 | Search in Rotated Sorted Array II | Medium | ✅ Solution | Binary Search | Amazon, Google, Microsoft |
| 153 | Find Minimum in Rotated Sorted Array | Medium | ✅ Solution | Binary Search | Amazon, Google, Microsoft, Meta, Bloomberg |
| 162 | Find Peak Element | Medium | ✅ Solution | Binary Search | Amazon, Google, Microsoft, Bloomberg |
| 278 | First Bad Version | Easy | ✅ Solution | Binary Search | Amazon, Google, Microsoft, Meta |
| 374 | Guess Number Higher or Lower | Easy | ✅ Solution | Binary Search | Amazon, Google |
| 410 | Split Array Largest Sum | Hard | ⬜ | Binary Search on Answer | Amazon, Google, Meta |
| 658 | Find K Closest Elements | Medium | ✅ Solution | Binary Search | Amazon, Google, Meta |
| 704 | Binary Search | Easy | ✅ Solution | Binary Search | Amazon, Google, Microsoft |
| 875 | Koko Eating Bananas | Medium | ⬜ | Binary Search on Answer | Amazon, Google, Meta |
| 1011 | Capacity To Ship Packages Within D Days | Medium | ⬜ | Binary Search on Answer | Amazon, Google |
| 1283 | Find the Smallest Divisor Given a Threshold | Medium | ⬜ | Binary Search on Answer | Google |
| 1482 | Minimum Number of Days to Make m Bouquets | Medium | ⬜ | Binary Search on Answer | Amazon, Google |
| 1760 | Minimum Limit of Balls in a Bag | Medium | ⬜ | Binary Search on Answer | Google |
| 1802 | Maximum Value at a Given Index in a Bounded Array | Medium | ⬜ | Binary Search on Answer | Amazon |
| 1891 | Cutting Ribbons | Medium | ⬜ | Binary Search on Answer | Amazon |
| 2187 | Minimum Time to Complete Trips | Medium | ⬜ | Binary Search on Answer | Google |
| 2226 | Maximum Candies Allocated to K Children | Medium | ⬜ | Binary Search on Answer | Amazon |
| 2300 | Successful Pairs of Spells and Potions | Medium | ⬜ | Binary Search | Amazon, Google |
| 3296 | Minimum Number of Seconds to Make Mountain Height Zero | Medium | ⬜ | Binary Search on Answer | Google |

---

## 6️⃣ Linked List (13 / 26)

> 📁 Additional files in repo (practice/helpers): MyLinkedList, SinglyLinkedList, SplitCircularLinkedList, ListNode

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | Remove Cycle in LL | - | ⬜ | - | - |
| - | Flatten LL | - | ⬜ | - | - |
| - | Reverse Nodes in K Groups | - | ⬜ | - | - |
| 2 | Add Two Numbers | Medium | ✅ Solution | Linked List Math | Amazon, Google, Microsoft, Meta, Bloomberg |
| 19 | Remove Nth Node From End of List | Medium | ✅ Solution | Two Pointers | Amazon, Google, Microsoft, Meta, Apple |
| 21 | Merge Two Sorted Lists | Easy | ✅ Solution | Two Pointers | Amazon, Google, Microsoft, Apple, Meta, Bloomberg |
| 24 | Swap Nodes in Pairs | Medium | ✅ Solution | Pointer Manipulation / Recursion | Amazon, Google, Microsoft |
| 61 | Rotate List | Medium | ⬜ | Two Pointers | Amazon, Microsoft, Bloomberg |
| 82 | Remove Duplicates from Sorted List II | Medium | ⬜ | Two Pointers | Amazon, Google |
| 83 | Remove Duplicates from Sorted List | Easy | ⬜ | Pointer Manipulation | Amazon, Microsoft |
| 92 | Reverse Linked List II | Medium | ⬜ | Pointer Manipulation | Amazon, Google, Microsoft, Meta |
| 138 | Copy List with Random Pointer | Medium | ⬜ | Hashing | Amazon, Google, Meta, Bloomberg, Microsoft |
| 141 | Linked List Cycle | Easy | ✅ Solution | Fast & Slow Pointers | Amazon, Google, Microsoft, Apple, Meta, Bloomberg |
| 142 | Linked List Cycle II | Medium | ✅ Solution | Fast & Slow Pointers | Amazon, Google, Microsoft, Meta |
| 143 | Reorder List | Medium | ⬜ | Fast & Slow Pointers | Amazon, Google, Meta, Bloomberg |
| 148 | Sort List | Medium | ⬜ | Merge Sort | Amazon, Google, Meta, Bloomberg |
| 160 | Intersection of Two Linked Lists | Easy | ✅ Solution | Two Pointers | Amazon, Google, Microsoft, Meta, Bloomberg |
| 206 | Reverse Linked List | Easy | ✅ Solution | Pointer Manipulation | Amazon, Google, Microsoft, Apple, Meta, Bloomberg, Adobe |
| 234 | Palindrome Linked List | Easy | ✅ Solution | Fast & Slow Pointers | Amazon, Google, Microsoft, Apple, Bloomberg |
| 237 | Delete Node in a Linked List | Medium | ✅ Solution | Pointer Manipulation | Amazon, Microsoft, Bloomberg |
| 328 | Odd Even Linked List | Medium | ⬜ | Pointer Manipulation | Amazon, Google, Microsoft |
| 445 | Add Two Numbers II | Medium | ⬜ | Linked List Math | Bloomberg, Amazon |
| 707 | Design Linked List | Medium | ✅ Solution | Linked List Design | Amazon, Google |
| 725 | Split Linked List in Parts | Medium | ⬜ | Pointer Manipulation | Amazon |
| 876 | Middle of the Linked List | Easy | ✅ Solution | Fast & Slow Pointers | Amazon, Google, Microsoft |
| 2130 | Maximum Twin Sum of a Linked List | Medium | ✅ Solution | Fast & Slow Pointers | Amazon, Google |

---

## 7️⃣ Stack & Queue (12 / 31)

> 📁 Additional files in repo (implementations): ArrayStack, ArrayListStack, LinkedListStack, ArrayQueue, ArrayListQueue, LinkedListQueue, MovingAverage

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | 1st Non Repeating in Stream | - | ⬜ | - | - |
| - | Reverse 1st K Elements of Queue | - | ⬜ | - | - |
| - | Time needed to Buy Tickets | - | ⬜ | - | - |
| - | Previous Smaller Element | - | ⬜ | - | - |
| - | Celebrity Problem | - | ⬜ | - | - |
| - | Stock Span | - | ⬜ | - | - |
| 20 | Valid Parentheses | Easy | ✅ Solution | Stack | Amazon, Google, Microsoft, Apple, Meta, Bloomberg, Adobe |
| 71 | Simplify Path | Medium | ⬜ | Stack | Amazon, Google, Meta |
| 84 | Largest Rectangle in Histogram | Hard | ⬜ | Monotonic Stack | Amazon, Google, Meta, Bloomberg |
| 85 | Maximal Rectangle | Hard | ⬜ | Monotonic Stack | Amazon, Google, Meta |
| 102 | Binary Tree Level Order Traversal | Medium | ✅ Solution | BFS / Queue | Amazon, Google, Microsoft, Meta, Bloomberg |
| 127 | Word Ladder | Hard | ⬜ | BFS / Queue | Amazon, Google, Meta, Bloomberg, LinkedIn |
| 150 | Evaluate Reverse Polish Notation | Medium | ✅ Solution | Stack | Amazon, Bloomberg |
| 155 | Min Stack | Medium | ✅ Solution | Stack | Amazon, Google, Microsoft, Apple, Bloomberg, Adobe |
| 207 | Course Schedule | Medium | ✅ Solution | BFS / Topological Sort | Amazon, Google, Microsoft, Meta, Bloomberg |
| 224 | Basic Calculator | Hard | ⬜ | Stack | Amazon, Google, Meta, Bloomberg |
| 225 | Implement Stack using Queues | Easy | ✅ Solution | Queue | Amazon, Microsoft |
| 232 | Implement Queue using Stacks | Easy | ✅ Solution | Stack | Amazon, Microsoft, Bloomberg |
| 286 | Walls and Gates | Medium | ⬜ | BFS / Queue | Amazon, Google, Meta |
| 394 | Decode String | Medium | ⬜ | Stack | Amazon, Google, Microsoft, Bloomberg |
| 496 | Next Greater Element I | Easy | ✅ Solution | Monotonic Stack | Amazon, Google |
| 503 | Next Greater Element II | Medium | ✅ Solution | Monotonic Stack | Amazon, Google, Bloomberg |
| 622 | Design Circular Queue | Medium | ✅ Solution | Queue Design | Amazon, Google |
| 641 | Design Circular Deque | Medium | ⬜ | Deque Design | Amazon, Google |
| 739 | Daily Temperatures | Medium | ✅ Solution | Monotonic Stack | Amazon, Google, Meta, Bloomberg |
| 853 | Car Fleet | Medium | ⬜ | Monotonic Stack | Google |
| 862 | Shortest Subarray with Sum ≥ K | Hard | ⬜ | Monotonic Deque | Google, Meta |
| 907 | Sum of Subarray Minimums | Medium | ⬜ | Monotonic Stack | Amazon, Google |
| 933 | Number of Recent Calls | Easy | ⬜ | Queue / Sliding Window | Amazon |
| 994 | Rotting Oranges | Medium | ✅ Solution | BFS / Queue | Amazon, Google, Microsoft, Meta, Bloomberg |
| 1091 | Shortest Path in Binary Matrix | Medium | ⬜ | BFS / Queue | Amazon, Google, Meta, Bloomberg |

---

## 8️⃣ Heap / Priority Queue (9 / 21)

> 📌 30-Day roadmap focus: Day 4 → 215, 1046 · Day 5 → 347, 703, 295

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | Heap Sort | - | ⬜ | - | - |
| - | Smallest Range in K Sorted List | - | ⬜ | - | - |
| 23 | Merge K Sorted Lists | Hard | ✅ Solution | Heap | Amazon, Google, Microsoft, Meta, Bloomberg, LinkedIn |
| 215 | Kth Largest Element in an Array | Medium | ✅ Solution | Heap / QuickSelect · Day 4 | Amazon, Google, Microsoft, Meta, Bloomberg, Facebook |
| 264 | Ugly Number II | Medium | ✅ Solution | Heap / DP | Google, Amazon, Bloomberg |
| 295 | Find Median from Data Stream | Hard | ✅ Solution | Two Heaps · Day 5 | Amazon, Google, Microsoft, Meta, Bloomberg |
| 347 | Top K Frequent Elements | Medium | ✅ Solution | Heap + Hashing · Day 5 | Amazon, Google, Meta, Bloomberg, Uber, LinkedIn |
| 355 | Design Twitter | Medium | ✅ Solution | Heap | Amazon, Bloomberg |
| 373 | Find K Pairs with Smallest Sums | Medium | ⬜ | Heap | Amazon, Google, Bloomberg |
| 378 | Kth Smallest Element in a Sorted Matrix | Medium | ⬜ | Heap | Amazon, Google, Bloomberg |
| 407 | Trapping Rain Water II | Hard | ⬜ | Heap | Amazon, Google |
| 502 | IPO | Hard | ⬜ | Heap + Greedy | Google |
| 621 | Task Scheduler | Medium | ⬜ | Heap + Greedy | Amazon, Google, Meta, Bloomberg |
| 630 | Course Schedule III | Hard | ⬜ | Heap + Greedy | Google, Bloomberg |
| 632 | Smallest Range Covering Elements from K Lists | Hard | ⬜ | Heap | Google |
| 703 | Kth Largest Element in a Stream | Easy | ⬜ | Heap · Day 5 | Amazon, Google, LinkedIn |
| 743 | Network Delay Time | Medium | ⬜ | Heap + Dijkstra's | Amazon, Google, Meta |
| 778 | Swim in Rising Water | Hard | ⬜ | Heap | Google |
| 1046 | Last Stone Weight | Easy | ✅ Solution | Heap · Day 4 | Amazon, Google |
| 1337 | The K Weakest Rows in a Matrix | Easy | ✅ Solution | Heap + Binary Search | Amazon, Google |
| 1492 | The Kth Factor of N | Medium | ✅ Solution | Math + Heap | Amazon |

---

## 9️⃣ Trees (11 / 50)

> 📌 30-Day roadmap focus: Days 1–3 cover 104 (re-solve), 110, 543, 98, 700, 235, 236, 112, 113.
> 📁 Additional tree files in repo: MyBinaryTree (custom implementation)

| # | Problem | Difficulty | Status | Pattern | Company | 30-Day Roadmap |
|---|---|---|---|---|---|---|
| - | Minimum Distance between Nodes | - | ⬜ | - | - | - |
| - | Are 2 Trees Identical or Not | - | ⬜ | - | - | - |
| - | Check if BT Mirror of itself or not | - | ⬜ | - | - | - |
| - | LCA | - | ⬜ | - | - | - |
| - | Construct BT from Inorder & Pre order | - | ⬜ | - | - | - |
| - | Construct BT from Inorder & Post order | - | ⬜ | - | - | - |
| - | Flatten BT to LL | - | ⬜ | - | - | - |
| - | Max Width of BT | - | ⬜ | - | - | - |
| - | Zig Zag Traversal of BT | - | ⬜ | - | - | - |
| - | Kth Ancestor | - | ⬜ | - | - | - |
| - | Recover BST | - | ⬜ | - | - | - |
| - | BST Iterator | - | ⬜ | - | - | - |
| - | Flatten BST to Sorted list | - | ⬜ | - | - | - |
| - | Inorder Successor | - | ⬜ | - | - | - |
| - | Inorder Predecessor | - | ⬜ | - | - | - |
| - | Largest BST in BT | - | ⬜ | - | - | - |
| - | Merge 2 BSTS | - | ⬜ | - | - | - |
| - | Sorted Array to Balanced BST | - | ⬜ | - | - | - |
| 94 | Binary Tree Inorder Traversal | Easy | ✅ Solution | DFS | Amazon, Google, Microsoft, Bloomberg | |
| 98 | Validate Binary Search Tree | Medium | ✅ Solution | DFS / Recursion | Amazon, Google, Microsoft, Meta, Bloomberg | Day 2 re-solve |
| 100 | Same Tree | Easy | ✅ Solution | DFS / BFS | Amazon, Google, Microsoft, Bloomberg | |
| 101 | Symmetric Tree | Easy | ⬜ | DFS / BFS | Amazon, Google, Microsoft, Bloomberg | |
| 102 | Binary Tree Level Order Traversal | Medium | ✅ Solution | BFS | Amazon, Google, Microsoft, Meta, Bloomberg | |
| 104 | Maximum Depth of Binary Tree | Easy | ✅ Solution | DFS | Amazon, Google, Microsoft, Apple, Bloomberg | Day 1 re-solve |
| 105 | Construct Binary Tree from Preorder and Inorder | Medium | ⬜ | Recursion | Amazon, Google, Microsoft, Meta, Bloomberg | |
| 106 | Construct Binary Tree from Inorder and Postorder | Medium | ⬜ | Recursion | Amazon, Google, Bloomberg | |
| 110 | Balanced Binary Tree | Easy | ⬜ | DFS | Amazon, Google, Microsoft, Bloomberg | Day 1 |
| 112 | Path Sum | Easy | ⬜ | DFS | Amazon, Google, Microsoft, Bloomberg | Day 3 |
| 113 | Path Sum II | Medium | ⬜ | DFS + Backtracking | Amazon, Google, Microsoft, Bloomberg | Day 3 |
| 116 | Populating Next Right Pointers in Each Node | Medium | ⬜ | BFS / Pointer | Amazon, Google, Microsoft | |
| 124 | Binary Tree Maximum Path Sum | Hard | ⬜ | DFS | Amazon, Google, Microsoft, Meta, Bloomberg | |
| 144 | Binary Tree Preorder Traversal | Easy | ✅ Solution | DFS | Amazon, Google, Microsoft | |
| 145 | Binary Tree Postorder Traversal | Easy | ✅ Solution | DFS | Amazon, Google, Microsoft | |
| 199 | Binary Tree Right Side View | Medium | ✅ Solution | BFS | Amazon, Google, Meta, Bloomberg | |
| 222 | Count Complete Tree Nodes | Medium | ✅ Solution | DFS | Amazon, Google | |
| 226 | Invert Binary Tree | Easy | ✅ Solution | DFS / BFS | Amazon, Google, Microsoft, Apple, Bloomberg | |
| 230 | Kth Smallest Element in a BST | Medium | ⬜ | DFS | Amazon, Google, Microsoft, Meta, Bloomberg | |
| 235 | Lowest Common Ancestor of a BST | Medium | ⬜ | BST Properties | Amazon, Google, Microsoft, Meta, Bloomberg | Day 2 |
| 236 | Lowest Common Ancestor of a Binary Tree | Medium | ⬜ | DFS | Amazon, Google, Microsoft, Meta, Bloomberg | Day 3 |
| 257 | Binary Tree Paths | Easy | ⬜ | DFS + Backtracking | Amazon, Google | |
| 297 | Serialize and Deserialize Binary Tree | Hard | ⬜ | DFS / BFS | Amazon, Google, Meta, Bloomberg, LinkedIn | |
| 337 | House Robber III | Medium | ⬜ | DP on Trees | Amazon, Google | |
| 404 | Sum of Left Leaves | Easy | ⬜ | DFS | Amazon, Google | |
| 437 | Path Sum III | Medium | ⬜ | DFS + Prefix Sum | Amazon, Google, Meta | |
| 450 | Delete Node in a BST | Medium | ⬜ | BST Properties | Amazon, Google, Microsoft | |
| 543 | Diameter of Binary Tree | Easy | ⬜ | DFS | Amazon, Google, Meta, Bloomberg | Day 1 |
| 572 | Subtree of Another Tree | Easy | ⬜ | DFS | Amazon, Google | |
| 700 | Search in a Binary Search Tree | Easy | ✅ Solution | BST Properties / Recursion | Amazon, Google | Day 2 re-solve |
| 814 | Binary Tree Pruning | Medium | ⬜ | DFS | Google | |
| 1650 | Lowest Common Ancestor of a Binary Tree III | Medium | ⬜ | Two Pointers | Meta, Google | |

---

## 🔟 Backtracking (14 / 22)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | Knights Tour | - | ⬜ | - | - |
| - | M Coloring | - | ⬜ | - | - |
| - | Rat in a Maze | - | ⬜ | - | - |
| - | Merge Sort | - | ✅ Solution | Recursion | Amazon, Google |
| - | Count Inversions | - | ⬜ | - | - |
| 17 | Letter Combinations of a Phone Number | Medium | ✅ Solution | Backtracking | Amazon, Google, Microsoft, Meta, Bloomberg |
| 22 | Generate Parentheses | Medium | ✅ Solution | Backtracking | Amazon, Google, Microsoft, Meta, Bloomberg, Uber |
| 37 | Sudoku Solver | Hard | ✅ Solution | Backtracking | Amazon, Google, Microsoft, Apple |
| 39 | Combination Sum | Medium | ✅ Solution | Backtracking | Amazon, Google, Microsoft, Bloomberg |
| 40 | Combination Sum II | Medium | ⬜ | Backtracking | Amazon, Google, Bloomberg |
| 46 | Permutations | Medium | ✅ Solution | Backtracking | Amazon, Google, Microsoft, Meta, Bloomberg |
| 47 | Permutations II | Medium | ⬜ | Backtracking | Amazon, Google |
| 51 | N-Queens | Hard | ✅ Solution | Backtracking | Amazon, Google, Apple, Bloomberg |
| 52 | N-Queens II | Hard | ✅ Solution | Backtracking | Amazon, Google |
| 77 | Combinations | Medium | ✅ Solution | Backtracking | Amazon, Google, Microsoft |
| 78 | Subsets | Medium | ✅ Solution | Backtracking | Amazon, Google, Microsoft, Meta, Bloomberg |
| 79 | Word Search | Medium | ✅ Solution | Backtracking + DFS | Amazon, Google, Microsoft, Meta, Bloomberg |
| 90 | Subsets II | Medium | ✅ Solution | Backtracking | Amazon, Google |
| 131 | Palindrome Partitioning | Medium | ✅ Solution | Backtracking | Amazon, Google, Meta |
| 212 | Word Search II | Hard | ✅ Solution | Backtracking + Trie | Amazon, Google, Meta, Bloomberg |
| 216 | Combination Sum III | Medium | ⬜ | Backtracking | Amazon, Google |
| 491 | Non-decreasing Subsequences | Medium | ⬜ | Backtracking | Amazon, Google |

---

## 1️⃣1️⃣ Graphs (20 / 42)

> ✅ = LeetCode problem solved | ✅ = Concept file implemented in repo

**Concept implementations (non-LC):**

| Concept | Status | File |
|---|---|---|
| BFS Traversal | ✅ | `BFSTraversalInGraph.java` |
| DFS Traversal | ✅ | `DFSTraversalInGraph.java` |
| Adjacency Matrix | ✅ | `AdjacencyMatrix.java` |
| Detect cycle in undirected (BFS) | ✅ | `CycleDetectionUndirectedGraph.java` |
| Detect cycle in undirected (DFS) | ✅ | `CycleDetectionUndirectedGraph.java` |
| Detect cycle in directed (BFS) | ✅ | `CourseSchedule.java` |
| Detect cycle in directed (DFS) | ✅ | `CourseSchedule.java` |
| Topological Sort (DFS) | ✅ | `TopologicalSortingDFS.java` · `CourseScheduleII.java` |
| Topological Sort (BFS / Kahn's) | ✅ | `TopologicalSortingBFS.java` · `CourseScheduleII.java` |
| Dijkstra's Algorithm | ✅ | `DijkstrasAlgorithm.java` |
| Prim's Algorithm (MST) | ✅ | `PrimsAlgorithm.java` |
| Kruskal's Algorithm (MST) | ✅ | `KruskalAlgorithm.java` |
| Bellman-Ford Algorithm | ✅ | `BellmanFordAlgorithm.java` |
| Disjoint Set Union (DSU) | ✅ | `DisjointSetUnion.java` |
| Floyd-Warshall | ⬜ | — |
| Check Bipartite Graph | ⬜ | — |
| Kosaraju (Strongly Connected Components) | ⬜ | — |

> 📌 30-Day roadmap focus: Day 6 → 200, 695 · Day 7 → 994, 1091 · Day 8 → 207, 210

| # | Problem | Difficulty | Status | Pattern | Company | 30-Day Roadmap |
|---|---|---|---|---|---|---|
| 133 | Clone Graph | Medium | ✅ Solution | DFS / BFS | Amazon, Google, Microsoft, Meta, Bloomberg | |
| 200 | Number of Islands | Medium | ✅ Solution | DFS / BFS | Amazon, Google, Microsoft, Meta, Bloomberg, Uber | Day 6 |
| 207 | Course Schedule | Medium | ✅ Solution | Topological Sort (BFS + DFS) | Amazon, Google, Microsoft, Meta, Bloomberg | Day 8 |
| 208 | Implement Trie (Prefix Tree) | Medium | ✅ Solution | Trie | Amazon, Google, Microsoft, Meta, Bloomberg | |
| 210 | Course Schedule II | Medium | ✅ Solution | Topological Sort (BFS + DFS) | Amazon, Google, Meta, Bloomberg | Day 8 |
| 261 | Graph Valid Tree | Medium | ✅ Solution | Union Find / DFS | Amazon, Google, LinkedIn | |
| 269 | Alien Dictionary | Hard | ⬜ | Topological Sort | Amazon, Google, Meta, Bloomberg, Airbnb | |
| 286 | Walls and Gates | Medium | ⬜ | BFS | Amazon, Google, Meta | |
| 323 | Number of Connected Components | Medium | ⬜ | Union Find / DFS | Amazon, Google, LinkedIn | |
| 329 | Longest Increasing Path in a Matrix | Hard | ⬜ | DFS + Memoization | Amazon, Google, Meta | |
| 417 | Pacific Atlantic Water Flow | Medium | ⬜ | DFS / BFS | Amazon, Google, Bloomberg | |
| 547 | Number of Provinces | Medium | ⬜ | Union Find / DFS | Amazon, Google, Microsoft | |
| 684 | Redundant Connection | Medium | ✅ Solution | Union Find + DFS + BFS | Amazon, Google | |
| 695 | Max Area of Island | Medium | ⬜ | DFS / BFS | Amazon, Google, Meta | Day 6 |
| 721 | Accounts Merge | Medium | ⬜ | Union Find / DFS | Amazon, Google, Meta | |
| 733 | Flood Fill | Easy | ✅ Solution | DFS on grid | Amazon, Google, Microsoft | |
| 743 | Network Delay Time | Medium | ✅ Solution | Dijkstra's | Amazon, Google, Meta, Bloomberg | |
| 787 | Cheapest Flights Within K Stops | Medium | ✅ Solution | Bellman-Ford | Amazon, Google, Meta | |
| 797 | All Paths From Source to Target | Medium | ✅ Solution | DFS / Backtracking | Amazon, Google | |
| 802 | Find Eventual Safe States | Medium | ⬜ | DFS | Google, Meta | |
| 827 | Making A Large Island | Hard | ⬜ | DFS | Amazon, Google | |
| 994 | Rotting Oranges | Medium | ✅ Solution | BFS (multi-source) | Amazon, Google, Microsoft, Meta | Day 7 |
| 1091 | Shortest Path in Binary Matrix | Medium | ⬜ | BFS | Amazon, Google, Meta, Bloomberg | Day 7 |
| 1584 | Min Cost to Connect All Points | Medium | ✅ Solution | MST (Prim's) | Amazon, Google, Meta | |

---

## 1️⃣2️⃣ Dynamic Programming (3 / 43)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | 0-1 Knapsack | - | ⬜ | - | - |
| - | Unbounded Knapsack | - | ⬜ | - | - |
| - | Longest Common Substring | - | ⬜ | - | - |
| - | Palindromic Partitioning (MCM) | - | ⬜ | - | - |
| - | Unique BSTs | - | ⬜ | - | - |
| - | Nth Catalan | - | ⬜ | - | - |
| - | Wildcard Pattern Matching | - | ⬜ | - | - |
| - | Rod Cutting | - | ⬜ | - | - |
| - | Egg Dropping | - | ⬜ | - | - |
| - | MCM | - | ⬜ | - | - |
| - | Longest Bitonic Subsequence | - | ⬜ | - | - |
| 5 | Longest Palindromic Substring | Medium | ⬜ | DP / Expand Center | Amazon, Google, Microsoft, Apple, Bloomberg |
| 53 | Maximum Subarray | Medium | ⬜ | Kadane's / DP | Amazon, Google, Microsoft, Apple, Bloomberg, Meta |
| 62 | Unique Paths | Medium | ⬜ | DP | Amazon, Google, Microsoft, Bloomberg |
| 63 | Unique Paths II | Medium | ⬜ | DP | Amazon, Google, Bloomberg |
| 64 | Minimum Path Sum | Medium | ⬜ | DP | Amazon, Google, Bloomberg |
| 70 | Climbing Stairs | Easy | ✅ Solution | DP / Recursion | Amazon, Google, Microsoft, Apple, Bloomberg |
| 72 | Edit Distance | Medium | ⬜ | DP | Amazon, Google, Microsoft, Meta, Bloomberg |
| 91 | Decode Ways | Medium | ⬜ | DP | Amazon, Google, Meta, Bloomberg |
| 123 | Best Time to Buy and Sell Stock III | Hard | ⬜ | DP | Amazon, Google, Meta, Bloomberg |
| 139 | Word Break | Medium | ⬜ | DP | Amazon, Google, Microsoft, Meta, Bloomberg |
| 152 | Maximum Product Subarray | Medium | ⬜ | DP | Amazon, Google, LinkedIn, Bloomberg |
| 198 | House Robber | Medium | ⬜ | DP | Amazon, Google, Microsoft, Bloomberg, Adobe |
| 213 | House Robber II | Medium | ⬜ | DP | Amazon, Google |
| 221 | Maximal Square | Medium | ⬜ | DP | Amazon, Google, Meta, Bloomberg |
| 279 | Perfect Squares | Medium | ⬜ | DP | Amazon, Google |
| 300 | Longest Increasing Subsequence | Medium | ⬜ | DP | Amazon, Google, Microsoft, Meta, Bloomberg |
| 309 | Best Time to Buy and Sell Stock with Cooldown | Medium | ⬜ | DP | Amazon, Google |
| 312 | Burst Balloons | Hard | ⬜ | DP | Amazon, Google, Meta |
| 322 | Coin Change | Medium | ⬜ | DP | Amazon, Google, Microsoft, Meta, Bloomberg, Adobe |
| 337 | House Robber III | Medium | ⬜ | DP on Trees | Amazon, Google |
| 416 | Partition Equal Subset Sum | Medium | ⬜ | DP | Amazon, Google, Meta |
| 494 | Target Sum | Medium | ⬜ | DP | Amazon, Google |
| 518 | Coin Change II | Medium | ⬜ | DP | Amazon, Google |
| 673 | Number of Longest Increasing Subsequence | Medium | ⬜ | DP | Amazon, Google |
| 714 | Best Time to Buy and Sell Stock with Transaction Fee | Medium | ⬜ | DP | Amazon, Google |
| 740 | Delete and Earn | Medium | ⬜ | DP | Amazon, Google |
| 931 | Minimum Falling Path Sum | Medium | ⬜ | DP | Amazon, Google |
| 1143 | Longest Common Sequence | Medium | ⬜ | DP | Amazon, Google, Microsoft, Meta, Bloomberg |
| 1155 | Number of Dice Rolls With Target Sum | Medium | ⬜ | DP | Google |
| 1218 | Longest Arithmetic Subsequence of Given Difference | Medium | ⬜ | DP | Amazon, Google |
| 1639 | Number of Ways to Form a Target String Given a Dictionary | Hard | ⬜ | DP | Google, Meta |
| 3129 | Find All Possible Stable Binary Arrays I | Hard | ✅ Solution | DP + Prefix Sum | Google |
| 3130 | Find All Possible Stable Binary Arrays II | Hard | ✅ Solution | DP + Prefix Sum | Google |

---

## 1️⃣3️⃣ Greedy (2 / 13)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | Indian Coins | - | ⬜ | - | - |
| - | Fractional Knapsack | - | ⬜ | - | - |
| - | Maximum length of pair chain | - | ⬜ | - | - |
| - | Activity Selection | - | ⬜ | - | - |
| - | Job Scheduling | - | ⬜ | - | - |
| 45 | Jump Game II | Medium | ⬜ | Greedy | Amazon, Google, Meta, Bloomberg |
| 55 | Jump Game | Medium | ⬜ | Greedy | Amazon, Google, Microsoft, Meta, Bloomberg |
| 121 | Best Time to Buy and Sell Stock | Easy | ✅ Solution | Greedy | Amazon, Google, Microsoft, Apple, Bloomberg, Meta |
| 134 | Gas Station | Medium | ⬜ | Greedy | Amazon, Google, Bloomberg |
| 435 | Non-overlapping Intervals | Medium | ⬜ | Greedy + Sorting | Amazon, Google, Meta, Bloomberg |
| 452 | Minimum Arrows to Burst Balloons | Medium | ⬜ | Greedy + Sorting | Amazon, Google |
| 763 | Partition Labels | Medium | ⬜ | Greedy + Hashing | Amazon, Google, Meta |
| 1689 | Partitioning Into Min Number of Deci-Binary Nos | Easy | ✅ Solution | Greedy | Amazon |

---

## 1️⃣4️⃣ Trie (2 / 5)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| - | Implement a Phone Directory | - | ⬜ | - | - |
| - | Longest String with All Prefix | - | ⬜ | - | - |
| 208 | Implement Trie (Prefix Tree) | Medium | ✅ Solution | Trie | Amazon, Google, Microsoft, Meta, Bloomberg |
| 211 | Design Add and Search Word | Medium | ⬜ | Trie + DFS | Amazon, Google, Meta, Bloomberg |
| 212 | Word Search II | Hard | ✅ Solution | Trie + Backtracking | Amazon, Google, Meta, Bloomberg |

---

## 1️⃣5️⃣ System Design / LLD (0 / 3)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| 146 | LRU Cache | Medium | ⬜ | Doubly Linked List + HashMap | Amazon, Google, Microsoft, Meta, Bloomberg, Uber |
| 460 | LFU Cache | Hard | ⬜ | HashMap + TreeMap | Amazon, Google, Meta |
| 588 | Design In-Memory File System | Hard | ⬜ | Trie + HashMap | Amazon, Google, Dropbox |

---

## 1️⃣6️⃣ Bit Manipulation (4 / 12)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| 67 | Add Binary | Easy | ✅ Solution | Bit Manipulation | Amazon, Google, Apple |
| 136 | Single Number | Easy | ✅ Solution | XOR | Amazon, Google, Microsoft, Bloomberg |
| 190 | Reverse Bits | Easy | ⬜ | Bit Manipulation | Amazon, Apple, Bloomberg |
| 191 | Number of 1 Bits | Easy | ⬜ | Bit Manipulation | Amazon, Apple, Bloomberg |
| 231 | Power of Two | Easy | ⬜ | Bit Manipulation | Amazon, Google |
| 268 | Missing Number | Easy | ⬜ | XOR / Math | Amazon, Google, Microsoft, Bloomberg |
| 338 | Counting Bits | Easy | ⬜ | DP + Bits | Amazon, Google |
| 371 | Sum of Two Integers | Medium | ⬜ | Bit Manipulation | Amazon, Google, Meta |
| 461 | Hamming Distance | Easy | ⬜ | XOR | Amazon, Google |
| 693 | Binary Number with Alternating Bits | Easy | ⬜ | Bit Manipulation | Amazon |
| 868 | Binary Gap | Easy | ✅ Solution | Bit Manipulation | Amazon |
| 1009 | Complement of Base 10 Integer | Easy | ✅ Solution | Bit Mask | Amazon, Google |

---

## 🌟 Daily Challenges (5 / 6)

| # | Problem | Difficulty | Status | Pattern | Company |
|---|---|---|---|---|---|
| 1318 | Minimum Flips to Make a OR b Equal to c | Medium | ✅ Solution | Bit Manipulation | Amazon, Google |
| 1536 | Minimum Swaps to Arrange a Binary Grid | Medium | ✅ Solution | Greedy / Simulation | Amazon |
| 1545 | Find Kth Bit in Nth Binary String | Medium | ✅ Solution | Recursion | Amazon, Google |
| 1784 | Check if Binary String Has at Most One Segment of Ones | Easy | ✅ Solution | String | Amazon |
| 1980 | Find Unique Binary String | Medium | ✅ Solution | Backtracking | Amazon, Google |
| 2193 | Minimum Number of Moves to Make Palindrome | Hard | ⬜ | Greedy | Google, Meta |

---

## 🔀 Multi-Pattern Index

Problems that combine two or more core patterns — useful for recognising overlaps under pressure.

| Problem | Patterns |
|---|---|
| LC 219 — Contains Duplicate II | Hashing + Sliding Window |
| LC 239 — Sliding Window Maximum | Sliding Window + Monotonic Deque |
| LC 295 — Find Median from Data Stream | Two Heaps |
| LC 347 — Top K Frequent Elements | Heap + HashMap |
| LC 560 — Subarray Sum Equals K | Prefix Sum + HashMap |
| LC 630 — Course Schedule III | Greedy + Max-Heap |
| LC 684 — Redundant Connection | Union-Find + DFS + BFS |
| LC 743 — Network Delay Time | Heap + Dijkstra's |
| LC 212 — Word Search II | Trie + Backtracking |
| LC 1584 — Min Cost to Connect All Points | Heap + MST (Prim's / Kruskal's) |

---

## 📁 Project Structure

```
src/
├── arrays/              ← Core array problems
├── arrays101/           ← LeetCode arrays101 explore card
├── binarysearch/        ← Binary search problems
├── dailychallenges/     ← Daily challenge solutions
├── dp/                  ← Dynamic programming
├── graph/               ← Graph problems + concept implementations
├── hashing/             ← HashMap/HashSet problems
├── heap/                ← Heap / Priority Queue problems
├── linkedlist/          ← Linked list problems
├── map/designhashmap/   ← LC 706 Design HashMap
├── patterns/            ← Pattern practice (Floyd's triangle etc.)
├── queue/               ← Queue problems + implementations
├── recursion/           ← Recursion fundamentals + backtracking
├── set/designhashset/   ← LC 705 Design HashSet
├── slidingwindow/       ← Sliding window problems
├── sortalgorithms/      ← Sorting implementations + recursive variants
├── stack/               ← Stack problems + implementations
├── strings/             ← String problems
├── tree/                ← Tree problems
└── twopointer/          ← Two pointer problems
```

---

## 🧠 Problem-Solving Philosophy

- Implement brute force first → optimize step by step
- Top-down recursion → bottom-up DP → space optimization
- Add memoization manually after understanding the recurrence
- Recognize patterns across problems (Floyd's cycle, two pointers, prefix sum, XOR)
- Write production-quality Java with full Javadoc and labeled test cases

---

## 🔗 Connect with Me

[LinkedIn](https://linkedin.com/in/mhnuk2007) · [Portfolio](https://mhnuk2007.github.io) · [Netlify](https://mhnuk2007.netlify.app) · [LeetCode](https://leetcode.com/mhnuk2007) · [GitHub](https://github.com/mhnuk2007)

---

*Last updated: April 24, 2026*
