# Java DSA — 417 LeetCode Problems & Solutions

> **Pattern-based interview preparation in Java.**
> Structured by topic, tracked by progress, built for FAANG-level readiness.

**Author:** Mohan Lal &nbsp;·&nbsp; **GitHub / LeetCode:** [mhnuk2007](https://github.com/mhnuk2007) &nbsp;·&nbsp; **Language:** Java
&nbsp;·&nbsp; [LinkedIn](https://linkedin.com/in/mhnuk2007) &nbsp;·&nbsp; [Portfolio](https://mhnuk2007.github.io)

---

## Table of Contents

- [Progress Overview](#progress-overview)
- [30-Day Interview Roadmap](#30-day-interview-roadmap)
- [Daily Log](#daily-log)
- [Problem Sets](#problem-sets)
  - [Arrays & Two Pointers](#1-arrays--two-pointers--4049)
  - [Sliding Window](#2-sliding-window--316)
  - [Strings](#3-strings--1426)
  - [HashMap / HashSet](#4-hashmap--hashset--1123)
  - [Binary Search](#5-binary-search--1129)
  - [Linked List](#6-linked-list--1326)
  - [Stack & Queue](#7-stack--queue--1231)
  - [Heap / Priority Queue](#8-heap--priority-queue--921)
  - [Trees](#9-trees--1150)
  - [Backtracking](#10-backtracking--1422)
  - [Graphs](#11-graphs--2042)
  - [Dynamic Programming](#12-dynamic-programming--1043)
  - [Greedy](#13-greedy--213)
  - [Trie](#14-trie--25)
  - [System Design / LLD](#15-system-design--lld--03)
  - [Bit Manipulation](#16-bit-manipulation--412)
  - [Daily Challenges](#17-daily-challenges--56)
- [Multi-Pattern Index](#multi-pattern-index)
- [Project Structure](#project-structure)
- [Problem-Solving Philosophy](#problem-solving-philosophy)
- [Contributing](#contributing)
- [License & Disclaimer](#license--disclaimer)

---

## Progress Overview

| # | Category | Done | Total | Progress |
|---|---|:---:|:---:|---|
| 1 | Arrays & Two Pointers | 40 | 49 | `████████░░` 82% |
| 2 | Sliding Window | 3 | 16 | `██░░░░░░░░` 19% |
| 3 | Strings | 14 | 26 | `█████░░░░░` 54% |
| 4 | HashMap / HashSet | 11 | 23 | `█████░░░░░` 48% |
| 5 | Binary Search | 11 | 29 | `████░░░░░░` 38% |
| 6 | Linked List | 13 | 26 | `█████░░░░░` 50% |
| 7 | Stack & Queue | 12 | 31 | `████░░░░░░` 39% |
| 8 | Heap / Priority Queue | 9 | 21 | `████░░░░░░` 43% |
| 9 | Trees | 11 | 50 | `██░░░░░░░░` 22% |
| 10 | Backtracking | 14 | 22 | `██████░░░░` 64% |
| 11 | Graphs | 20 | 42 | `█████░░░░░` 48% |
| 12 | Dynamic Programming | 10 | 43 | `████░░░░░░` 23% |
| 13 | Greedy | 2 | 13 | `██░░░░░░░░` 15% |
| 14 | Trie | 2 | 5 | `████░░░░░░` 40% |
| 15 | System Design / LLD | 0 | 3 | `░░░░░░░░░░` 0% |
| 16 | Bit Manipulation | 4 | 12 | `███░░░░░░░` 33% |
| 17 | Daily Challenges | 5 | 6 | `████████░░` 83% |
| | **Total** | **181** | **417** | `████░░░░░░` **43%** |

> Last updated: April 30, 2026. Graphs section reflects all completed concept files and LC problems. Trie updated after LC 208 and LC 212 completion. DP updated with 0-1 Knapsack, Unbounded Knapsack, 198, 322, 416, 494, 518, 746, 1137, 3129, and 3130.

---

## 30-Day Interview Roadmap

**Priority order based on current tracker:** DP (daily) → Trees → Sliding Window → Graphs → Heap polish.

### Ground rules

- 3 problems per day — strict, no exceptions
- 1 DP problem every day — non-negotiable regardless of which phase you're in
- 1 re-solve from a previous day — if you can't do it without notes, you don't own the pattern yet
- 1 easy skip per day — only if the pattern already feels completely automatic
- For every problem, write: pattern · time/space complexity · 1 mistake you made · why brute force fails here
- Re-solve without notes after 24 hours

### Revision buckets

| Bucket | Re-solve after | Purpose |
|---|---|---|
| Day+1 | Next day | Confirms initial understanding |
| Day+3 | 3 days later | Short-term retention check |
| Day+7 | 1 week later | Pattern solidification |
| Day+21 | 3 weeks later | Long-term retention before interview |

---

### Phase 1 — Core Interview Patterns (Days 1–10)

> **Checkpoint goal:** Tree / BST / Heap / Graph basics feel structured, not random.

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

---

### Phase 2 — Backtracking + DP (Days 11–20)

> **Checkpoint goal:** Write subsets, combination sum, and core 1D DP patterns from memory.

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
| Day 19 | 139 | Prefix / state transitions |
| Day 20 | DP review · re-solve 2 from memory | Memoization → tabulation fluency |

---

### Phase 3 — Interview Simulation (Days 21–30)

> **Checkpoint goal:** Explain BFS vs DFS, topological sort, two-heaps, sliding window, and DP transitions without notes.

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
| Day 29 | 3 problems from weakest category | Weak-area attack only |
| Day 30 | 1 Graph · 1 DP · 1 Tree · 1 Backtracking | Final memory re-solve day |

---

## Daily Log

| Date | Problems | Notes |
|---|---|---|
| Apr 30 | 322, 518 | Coin Change · Coin Change II |
| Apr 29 | 416, 494, 746, Knapsack01, UnboundedKnapsack, MultiStageGraph, TribonacciTopDown, TribonacciBottomUp | Partition Equal Subset Sum · Target Sum · Min Cost Climbing Stairs · 0/1 Knapsack (concept) · Unbounded Knapsack (concept) · Multi-Stage Graph (concept) · N-th Tribonacci Number (Top-Down) · N-th Tribonacci Number (Bottom-Up) |
| Apr 24 | 79, 212, 198, 1137 | Word Search · Word Search II · House Robber (Map/Array) · Tribonacci |
| Apr 23 | 208, 684, 207, 210, DSU, CycleDetection, TopologicalSort | Trie · Redundant Connection (DFS+BFS+DSU) · Cycle detection undirected/directed · Topological sort both approaches · DSU from scratch |
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
| Mar 19 | 20, 155 | Started Stacks |
| Mar 18 | 561, 119, 209, 54, 67, 868, 415, 151, 186, 557 | Big push on Strings & Arrays |
| Mar 16 | 118, 66, 747, 724, 169, 42, 189, 31, 2, 237 | Arrays, LL cleanup |
| Mar 14 | 118, 66, 747, 724 | Array Day |
| Mar 13 | 31, 42, 2, 599, 387, 350, 219, 205, 136 | Arrays, LL & Hashing |
| Mar 12 | 136, 706, 705, 1009, 69, 2486, 33 | Very productive day |
| Mar 11 | 3130, 2130, 21 | DP and Linked Lists |
| Mar 10 | 234, 287, 206, 3129, 19 | Heavy on Linked Lists |

---

## Problem Sets

**Status legend:** ✅ Solved &nbsp;·&nbsp; ⬜ Pending

**Company abbreviations:** G = Google · A = Amazon · M = Microsoft · Meta = Meta/Facebook · Ap = Apple · B = Bloomberg · Ub = Uber · Ly = LinkedIn · Ad = Adobe · Or = Oracle · Nt = Netflix · Sl = Salesforce · Db = Databricks · DD = DoorDash · Tw = Twitter/X · Wa = Walmart · Sq = Square · Sh = Shopify · By = ByteDance · Ci = Citadel · Bk = Booking.com · At = Atlassian · Ai = Airbnb

---

### 1. Arrays & Two Pointers — 40/49

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | Kadane's Algorithm | — | ⬜ | — | — |
| — | Pow(x, n) | — | ⬜ | — | — |
| — | 4Sum | — | ⬜ | — | — |
| — | Search a 2D Matrix | — | ⬜ | Binary Search | A, G, Meta |
| — | Merge Overlapping Intervals | — | ⬜ | — | — |
| — | Set Matrix Zeroes | — | ⬜ | — | — |
| — | Product of Array Except Itself | — | ⬜ | — | — |
| — | Count Inversions | — | ⬜ | — | — |
| — | Reverse Pairs | — | ⬜ | — | — |
| 1 | Two Sum | Easy | ✅ | Hashing | A, G, Ap, M, Meta, Ad, B |
| 11 | Container With Most Water | Medium | ✅ | Two Pointers | A, G, Meta, B, Ad |
| 15 | 3Sum | Medium | ✅ | Two Pointers | A, G, Meta, M, Ad, B |
| 26 | Remove Duplicates from Sorted Array | Easy | ✅ | Two Pointers | A, G, M, Ap |
| 27 | Remove Element | Easy | ✅ | Two Pointers | A, G |
| 31 | Next Permutation | Medium | ✅ | Array Manipulation | G, M, Ap |
| 33 | Search in Rotated Sorted Array | Medium | ✅ | Binary Search | A, G, M, Meta, B, Ub |
| 42 | Trapping Rain Water | Hard | ✅ | Two Pointers / Stack | A, G, Ap, B, Meta |
| 54 | Spiral Matrix | Medium | ✅ | Matrix Traversal | A, G, M, Ap, Meta |
| 66 | Plus One | Easy | ✅ | Array Manipulation | G, Ap |
| 75 | Sort Colors | Medium | ✅ | Two Pointers | A, M, B |
| 80 | Remove Duplicates from Sorted Array II | Medium | ✅ | Two Pointers | A, G |
| 88 | Merge Sorted Array | Easy | ✅ | Two Pointers | A, G, M, Ap, Meta, B |
| 118 | Pascal's Triangle | Easy | ✅ | DP / Simulation | A, G, Ap |
| 119 | Pascal's Triangle II | Easy | ✅ | DP / Simulation | A, G |
| 121 | Best Time to Buy and Sell Stock | Easy | ✅ | Array | A, G, M, Ap, B, Meta |
| 167 | Two Sum II — Input Array Is Sorted | Medium | ✅ | Two Pointers | A, G, M |
| 169 | Majority Element | Easy | ✅ | Sorting / Boyer-Moore | A, G, M, Ap |
| 189 | Rotate Array | Medium | ✅ | Array Manipulation | A, M, B |
| 202 | Happy Number | Easy | ✅ | Fast & Slow Pointers | A, G, Ad |
| 246 | Strobogrammatic Number | Easy | ✅ | Two Pointers | G, Meta |
| 283 | Move Zeroes | Easy | ✅ | Two Pointers | A, G, M, B, Ad |
| 287 | Find the Duplicate Number | Medium | ✅ | Floyd's Cycle | A, G, B |
| 414 | Third Maximum Number | Easy | ✅ | Tracking | A |
| 448 | Find All Numbers Disappeared in an Array | Easy | ✅ | Array Traversal | A, G |
| 457 | Circular Array Loop | Medium | ✅ | Fast & Slow Pointers | A |
| 485 | Max Consecutive Ones | Easy | ✅ | Array Traversal | A, G |
| 561 | Array Partition | Easy | ✅ | Sorting | A |
| 724 | Find Pivot Index | Easy | ✅ | Prefix Sum | A, G |
| 747 | Largest Number At Least Twice of Others | Easy | ✅ | Array Traversal | A |
| 1051 | Height Checker | Easy | ✅ | Sorting | A |
| 1089 | Duplicate Zeros | Easy | ✅ | Two Pointers | A, G |
| 1295 | Find Numbers with Even Number of Digits | Easy | ✅ | Array Traversal | A |
| 1299 | Replace Elements with Greatest on Right | Easy | ✅ | Reverse Traversal | A |
| 1346 | Check If N and Its Double Exist | Easy | ✅ | Hashing | A |
| 1480 | Running Sum of 1d Array | Easy | ✅ | Prefix Sum | A, G |
| 1582 | Special Positions in a Binary Matrix | Easy | ✅ | Matrix Traversal | A |
| 1672 | Richest Customer Wealth | Easy | ✅ | Matrix Traversal | A |
| 2176 | Count Equal and Divisible Pairs in an Array | Easy | ✅ | Array Traversal | A |
| 2486 | Append Characters to String to Make Subsequence | Medium | ✅ | Two Pointers | G |

---

### 2. Sliding Window — 3/16

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| 3 | Longest Substring Without Repeating Characters | Medium | ✅ | Sliding Window | A, G, M, Ap, Meta, B, Ad |
| 76 | Minimum Window Substring | Hard | ⬜ | Sliding Window | A, G, Meta, B, Ly |
| 209 | Minimum Size Subarray Sum | Medium | ✅ | Sliding Window | A, G, M |
| 239 | Sliding Window Maximum | Hard | ✅ | Monotonic Deque | A, G, Meta, B |
| 424 | Longest Repeating Character Replacement | Medium | ⬜ | Sliding Window | G, Meta, B |
| 438 | Find All Anagrams in a String | Medium | ⬜ | Sliding Window | A, G, Meta, B |
| 567 | Permutation in String | Medium | ⬜ | Sliding Window | A, G, M, B |
| 713 | Subarray Product Less Than K | Medium | ⬜ | Sliding Window | A, G |
| 904 | Fruit Into Baskets | Medium | ⬜ | Sliding Window | G |
| 1004 | Max Consecutive Ones III | Medium | ⬜ | Sliding Window | A, G |
| 1208 | Get Equal Substrings Within Budget | Medium | ⬜ | Sliding Window | G |
| 1343 | Number of Sub-arrays of Size K and Average ≥ Threshold | Medium | ⬜ | Sliding Window | A |
| 1423 | Maximum Points You Can Obtain from Cards | Medium | ⬜ | Sliding Window | G, Meta |
| 1493 | Longest Subarray of 1's After Deleting One Element | Medium | ⬜ | Sliding Window | A, G |
| 1838 | Frequency of the Most Frequent Element | Medium | ⬜ | Sliding Window | A |
| 2090 | K Radius Subarray Averages | Medium | ⬜ | Sliding Window | A |

---

### 3. Strings — 14/26

> Additional practice files in repo (non-LeetCode): `Anagram`, `Palindrome`, `PalindromeI`, `SplitString`, `SmallestAndLargestSub`, `JavaStringTokens`

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | Remove All Occurrences of String | — | ⬜ | — | — |
| — | String Compression | — | ⬜ | — | — |
| — | KMP Algorithm | — | ⬜ | — | — |
| — | Rabin-Karp | — | ⬜ | — | — |
| 3 | Longest Substring Without Repeating Characters | Medium | ✅ | Sliding Window | A, G, M, Ap, Meta, B, Ad |
| 5 | Longest Palindromic Substring | Medium | ⬜ | DP / Expand Around Center | A, G, M, Ap, B |
| 8 | String to Integer (atoi) | Medium | ⬜ | String Parsing | A, M, B |
| 14 | Longest Common Prefix | Easy | ⬜ | String | A, G, M |
| 28 | Find the Index of the First Occurrence in a String | Easy | ✅ | Two Pointers | A, M |
| 49 | Group Anagrams | Medium | ⬜ | Hashing | A, G, M, Meta, B, Ub |
| 58 | Length of Last Word | Easy | ⬜ | String | A, G |
| 76 | Minimum Window Substring | Hard | ⬜ | Sliding Window | A, G, Meta, B, Ly |
| 125 | Valid Palindrome | Easy | ✅ | Two Pointers | A, G, M, Meta, Ap |
| 151 | Reverse Words in a String | Medium | ✅ | Two Pointers | A, G, M, B |
| 186 | Reverse Words in a String II | Medium | ✅ | In-place / O(1) space | A, M |
| 205 | Isomorphic Strings | Easy | ✅ | Hashing | A, G |
| 242 | Valid Anagram | Easy | ✅ | Hashing | A, G, M, Ap |
| 271 | Encode and Decode Strings | Medium | ⬜ | String | G, A, Meta, Ub |
| 344 | Reverse String | Easy | ✅ | Two Pointers | A, G, M |
| 408 | Valid Word Abbreviation | Easy | ✅ | Two Pointers | G, Meta |
| 415 | Add Strings | Easy | ✅ | String Math | A, G |
| 424 | Longest Repeating Character Replacement | Medium | ⬜ | Sliding Window | G, Meta, B |
| 557 | Reverse Words in a String III | Easy | ✅ | Two Pointers | A, M |
| 680 | Valid Palindrome II | Easy | ✅ | Two Pointers | A, G, Meta, B |
| 1689 | Partitioning Into Minimum Number of Deci-Binary Numbers | Easy | ✅ | Greedy | A |
| 1758 | Minimum Changes to Make Alternating Binary String | Easy | ✅ | String | A |

---

### 4. HashMap / HashSet — 11/23

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| 49 | Group Anagrams | Medium | ⬜ | Hashing | A, G, M, Meta, B, Ub |
| 128 | Longest Consecutive Sequence | Medium | ⬜ | Hashing | A, G, Meta, B |
| 205 | Isomorphic Strings | Easy | ✅ | Hashing | A, G |
| 217 | Contains Duplicate | Easy | ✅ | Hashing | A, G, M, Ap, B |
| 219 | Contains Duplicate II | Easy | ✅ | Hashing + Sliding Window | A, G |
| 242 | Valid Anagram | Easy | ✅ | Hashing | A, G |
| 290 | Word Pattern | Easy | ⬜ | Hashing | A, G |
| 347 | Top K Frequent Elements | Medium | ✅ | Hashing + Heap | A, G, Meta, B, Ub, Ly |
| 349 | Intersection of Two Arrays | Easy | ✅ | Hashing | A, G, M |
| 350 | Intersection of Two Arrays II | Easy | ✅ | Hashing | A, G, Ap, M |
| 380 | Insert Delete GetRandom O(1) | Medium | ⬜ | Hashing + Array | A, G, Meta, B, Ub |
| 387 | First Unique Character in a String | Easy | ✅ | Hashing | A, G, M, B |
| 525 | Contiguous Array | Medium | ⬜ | Hashing + Prefix Sum | A, G, Meta |
| 560 | Subarray Sum Equals K | Medium | ⬜ | Hashing + Prefix Sum | A, G, Meta, B, Ly |
| 594 | Longest Harmonious Subsequence | Easy | ⬜ | Hashing | A |
| 599 | Minimum Index Sum of Two Lists | Easy | ✅ | Hashing | A |
| 692 | Top K Frequent Words | Medium | ⬜ | Hashing + Heap | A, G, Meta, B |
| 705 | Design HashSet | Easy | ✅ | Hashing | A, Ap |
| 706 | Design HashMap | Easy | ✅ | Hashing | A, Ap |
| 763 | Partition Labels | Medium | ⬜ | Greedy + Hashing | A, G, Meta |
| 811 | Subdomain Visit Count | Medium | ⬜ | Hashing | A, B |
| 884 | Uncommon Words from Two Sentences | Easy | ⬜ | Hashing | A, G |
| 1002 | Find Common Characters | Easy | ⬜ | Hashing | A |

---

### 5. Binary Search — 11/29

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | Peak Index in Mountain Array | — | ⬜ | — | — |
| — | Single Element in Sorted Array | — | ⬜ | — | — |
| — | Book Allocation (Min Pages) | — | ⬜ | BS on Answer | — |
| — | Painter's Partition | — | ⬜ | BS on Answer | — |
| — | Aggressive Cows | — | ⬜ | BS on Answer | — |
| — | Median of 2 Sorted Arrays | — | ⬜ | Binary Search | — |
| 33 | Search in Rotated Sorted Array | Medium | ✅ | Binary Search | A, G, M, Meta, B, Ub |
| 34 | Find First and Last Position of Element in Sorted Array | Medium | ✅ | Binary Search | A, G, M, Meta |
| 35 | Search Insert Position | Easy | ✅ | Binary Search | A, G, Ap |
| 69 | Sqrt(x) | Easy | ✅ | Binary Search | A, G, Ap, B |
| 81 | Search in Rotated Sorted Array II | Medium | ✅ | Binary Search | A, G, M |
| 153 | Find Minimum in Rotated Sorted Array | Medium | ✅ | Binary Search | A, G, M, Meta, B |
| 162 | Find Peak Element | Medium | ✅ | Binary Search | A, G, M, B |
| 278 | First Bad Version | Easy | ✅ | Binary Search | A, G, M, Meta |
| 374 | Guess Number Higher or Lower | Easy | ✅ | Binary Search | A, G |
| 410 | Split Array Largest Sum | Hard | ⬜ | BS on Answer | A, G, Meta |
| 658 | Find K Closest Elements | Medium | ✅ | Binary Search | A, G, Meta |
| 704 | Binary Search | Easy | ✅ | Binary Search | A, G, M |
| 875 | Koko Eating Bananas | Medium | ⬜ | BS on Answer | A, G, Meta |
| 1011 | Capacity To Ship Packages Within D Days | Medium | ⬜ | BS on Answer | A, G |
| 1283 | Find the Smallest Divisor Given a Threshold | Medium | ⬜ | BS on Answer | G |
| 1482 | Minimum Number of Days to Make m Bouquets | Medium | ⬜ | BS on Answer | A, G |
| 1760 | Minimum Limit of Balls in a Bag | Medium | ⬜ | BS on Answer | G |
| 1802 | Maximum Value at a Given Index in a Bounded Array | Medium | ⬜ | BS on Answer | A |
| 1891 | Cutting Ribbons | Medium | ⬜ | BS on Answer | A |
| 2187 | Minimum Time to Complete Trips | Medium | ⬜ | BS on Answer | G |
| 2226 | Maximum Candies Allocated to K Children | Medium | ⬜ | BS on Answer | A |
| 2300 | Successful Pairs of Spells and Potions | Medium | ⬜ | Binary Search | A, G |
| 3296 | Minimum Number of Seconds to Make Mountain Height Zero | Medium | ⬜ | BS on Answer | G |

---

### 6. Linked List — 13/26

> Additional files in repo: `MyLinkedList`, `SinglyLinkedList`, `SplitCircularLinkedList`, `ListNode`

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | Remove Cycle in LL | — | ⬜ | — | — |
| — | Flatten LL | — | ⬜ | — | — |
| — | Reverse Nodes in K Groups | — | ⬜ | — | — |
| 2 | Add Two Numbers | Medium | ✅ | LL Math | A, G, M, Meta, B |
| 19 | Remove Nth Node From End of List | Medium | ✅ | Two Pointers | A, G, M, Meta, Ap |
| 21 | Merge Two Sorted Lists | Easy | ✅ | Two Pointers | A, G, M, Ap, Meta, B |
| 24 | Swap Nodes in Pairs | Medium | ✅ | Pointer Manipulation / Recursion | A, G, M |
| 61 | Rotate List | Medium | ⬜ | Two Pointers | A, M, B |
| 82 | Remove Duplicates from Sorted List II | Medium | ⬜ | Two Pointers | A, G |
| 83 | Remove Duplicates from Sorted List | Easy | ⬜ | Pointer Manipulation | A, M |
| 92 | Reverse Linked List II | Medium | ⬜ | Pointer Manipulation | A, G, M, Meta |
| 138 | Copy List with Random Pointer | Medium | ⬜ | Hashing | A, G, Meta, B, M |
| 141 | Linked List Cycle | Easy | ✅ | Fast & Slow Pointers | A, G, M, Ap, Meta, B |
| 142 | Linked List Cycle II | Medium | ✅ | Fast & Slow Pointers | A, G, M, Meta |
| 143 | Reorder List | Medium | ⬜ | Fast & Slow Pointers | A, G, Meta, B |
| 148 | Sort List | Medium | ⬜ | Merge Sort | A, G, Meta, B |
| 160 | Intersection of Two Linked Lists | Easy | ✅ | Two Pointers | A, G, M, Meta, B |
| 206 | Reverse Linked List | Easy | ✅ | Pointer Manipulation | A, G, M, Ap, Meta, B, Ad |
| 234 | Palindrome Linked List | Easy | ✅ | Fast & Slow Pointers | A, G, M, Ap, B |
| 237 | Delete Node in a Linked List | Medium | ✅ | Pointer Manipulation | A, M, B |
| 328 | Odd Even Linked List | Medium | ⬜ | Pointer Manipulation | A, G, M |
| 445 | Add Two Numbers II | Medium | ⬜ | LL Math | B, A |
| 707 | Design Linked List | Medium | ✅ | LL Design | A, G |
| 725 | Split Linked List in Parts | Medium | ⬜ | Pointer Manipulation | A |
| 876 | Middle of the Linked List | Easy | ✅ | Fast & Slow Pointers | A, G, M |
| 2130 | Maximum Twin Sum of a Linked List | Medium | ✅ | Fast & Slow Pointers | A, G |

---

### 7. Stack & Queue — 12/31

> Additional implementations in repo: `ArrayStack`, `ArrayListStack`, `LinkedListStack`, `ArrayQueue`, `ArrayListQueue`, `LinkedListQueue`, `MovingAverage`

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | 1st Non-Repeating in Stream | — | ⬜ | — | — |
| — | Reverse 1st K Elements of Queue | — | ⬜ | — | — |
| — | Time Needed to Buy Tickets | — | ⬜ | — | — |
| — | Previous Smaller Element | — | ⬜ | — | — |
| — | Celebrity Problem | — | ⬜ | — | — |
| — | Stock Span | — | ⬜ | — | — |
| 20 | Valid Parentheses | Easy | ✅ | Stack | A, G, M, Ap, Meta, B, Ad |
| 71 | Simplify Path | Medium | ⬜ | Stack | A, G, Meta |
| 84 | Largest Rectangle in Histogram | Hard | ⬜ | Monotonic Stack | A, G, Meta, B |
| 85 | Maximal Rectangle | Hard | ⬜ | Monotonic Stack | A, G, Meta |
| 102 | Binary Tree Level Order Traversal | Medium | ✅ | BFS / Queue | A, G, M, Meta, B |
| 127 | Word Ladder | Hard | ⬜ | BFS / Queue | A, G, Meta, B, Ly |
| 150 | Evaluate Reverse Polish Notation | Medium | ✅ | Stack | A, B |
| 155 | Min Stack | Medium | ✅ | Stack | A, G, M, Ap, B, Ad |
| 207 | Course Schedule | Medium | ✅ | BFS / Topological Sort | A, G, M, Meta, B |
| 224 | Basic Calculator | Hard | ⬜ | Stack | A, G, Meta, B |
| 225 | Implement Stack using Queues | Easy | ✅ | Queue | A, M |
| 232 | Implement Queue using Stacks | Easy | ✅ | Stack | A, M, B |
| 286 | Walls and Gates | Medium | ⬜ | BFS / Queue | A, G, Meta |
| 394 | Decode String | Medium | ⬜ | Stack | A, G, M, B |
| 496 | Next Greater Element I | Easy | ✅ | Monotonic Stack | A, G |
| 503 | Next Greater Element II | Medium | ✅ | Monotonic Stack | A, G, B |
| 622 | Design Circular Queue | Medium | ✅ | Queue Design | A, G |
| 641 | Design Circular Deque | Medium | ⬜ | Deque Design | A, G |
| 739 | Daily Temperatures | Medium | ✅ | Monotonic Stack | A, G, Meta, B |
| 853 | Car Fleet | Medium | ⬜ | Monotonic Stack | G |
| 862 | Shortest Subarray with Sum ≥ K | Hard | ⬜ | Monotonic Deque | G, Meta |
| 907 | Sum of Subarray Minimums | Medium | ⬜ | Monotonic Stack | A, G |
| 933 | Number of Recent Calls | Easy | ⬜ | Queue / Sliding Window | A |
| 994 | Rotting Oranges | Medium | ✅ | BFS (multi-source) | A, G, M, Meta | Day 7 |
| 1091 | Shortest Path in Binary Matrix | Medium | ⬜ | BFS | A, G, Meta, B | Day 7 |

---

### 8. Heap / Priority Queue — 9/21

> **30-Day focus:** Day 4 → 215, 1046 &nbsp;·&nbsp; Day 5 → 347, 703, 295

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | Heap Sort | — | ⬜ | — | — |
| — | Smallest Range in K Sorted Lists | — | ⬜ | — | — |
| 23 | Merge K Sorted Lists | Hard | ✅ | Heap | A, G, M, Meta, B, Ly |
| 215 | Kth Largest Element in an Array | Medium | ✅ | Heap / QuickSelect | A, G, M, Meta, B |
| 264 | Ugly Number II | Medium | ✅ | Heap / DP | G, A, B |
| 295 | Find Median from Data Stream | Hard | ✅ | Two Heaps | A, G, M, Meta, B |
| 347 | Top K Frequent Elements | Medium | ✅ | Heap + Hashing | A, G, Meta, B, Ub, Ly |
| 355 | Design Twitter | Medium | ✅ | Heap | A, B |
| 373 | Find K Pairs with Smallest Sums | Medium | ⬜ | Heap | A, G, B |
| 378 | Kth Smallest Element in a Sorted Matrix | Medium | ⬜ | Heap | A, G, B |
| 407 | Trapping Rain Water II | Hard | ⬜ | Heap | A, G |
| 502 | IPO | Hard | ⬜ | Heap + Greedy | G |
| 621 | Task Scheduler | Medium | ⬜ | Heap + Greedy | A, G, Meta, B |
| 630 | Course Schedule III | Hard | ⬜ | Heap + Greedy | G, B |
| 632 | Smallest Range Covering Elements from K Lists | Hard | ⬜ | Heap | G |
| 703 | Kth Largest Element in a Stream | Easy | ⬜ | Heap | A, G, Ly |
| 743 | Network Delay Time | Medium | ⬜ | Heap + Dijkstra's | A, G, Meta |
| 778 | Swim in Rising Water | Hard | ⬜ | Heap | G |
| 1046 | Last Stone Weight | Easy | ✅ | Heap | A, G |
| 1337 | The K Weakest Rows in a Matrix | Easy | ✅ | Heap + Binary Search | A, G |
| 1492 | The Kth Factor of N | Medium | ✅ | Math + Heap | A |

---

### 9. Trees — 11/50

> **30-Day focus:** Days 1–3 cover 104, 110, 543, 98, 700, 235, 236, 112, 113
> Additional tree files in repo: `MyBinaryTree` (custom implementation)

| # | Problem | Difficulty | Status | Pattern | Companies | Roadmap |
|---|---|---|:---:|---|---|---|
| — | Minimum Distance Between Nodes | — | ⬜ | — | — | — |
| — | Are 2 Trees Identical | — | ⬜ | — | — | — |
| — | Check if BT Mirror of Itself | — | ⬜ | — | — | — |
| — | LCA (generic) | — | ⬜ | — | — | — |
| — | Construct BT from Inorder & Preorder | — | ⬜ | — | — | — |
| — | Construct BT from Inorder & Postorder | — | ⬜ | — | — | — |
| — | Flatten BT to LL | — | ⬜ | — | — | — |
| — | Max Width of BT | — | ⬜ | — | — | — |
| — | Zig-Zag Traversal | — | ⬜ | — | — | — |
| — | Kth Ancestor | — | ⬜ | — | — | — |
| — | Recover BST | — | ⬜ | — | — | — |
| — | BST Iterator | — | ⬜ | — | — | — |
| — | Flatten BST to Sorted List | — | ⬜ | — | — | — |
| — | Inorder Successor | — | ⬜ | — | — | — |
| — | Inorder Predecessor | — | ⬜ | — | — | — |
| — | Largest BST in BT | — | ⬜ | — | — | — |
| — | Merge 2 BSTs | — | ⬜ | — | — | — |
| — | Sorted Array to Balanced BST | — | ⬜ | — | — | — |
| 94 | Binary Tree Inorder Traversal | Easy | ✅ | DFS | A, G, M, B | — |
| 98 | Validate Binary Search Tree | Medium | ✅ | DFS / Recursion | A, G, M, Meta, B | Day 2 re-solve |
| 100 | Same Tree | Easy | ✅ | DFS / BFS | A, G, M, B | — |
| 101 | Symmetric Tree | Easy | ⬜ | DFS / BFS | A, G, M, B | — |
| 102 | Binary Tree Level Order Traversal | Medium | ✅ | BFS | A, G, M, Meta, B | — |
| 104 | Maximum Depth of Binary Tree | Easy | ✅ | DFS | A, G, M, Ap, B | Day 1 re-solve |
| 105 | Construct Binary Tree from Preorder and Inorder | Medium | ⬜ | Recursion | A, G, M, Meta, B | — |
| 106 | Construct Binary Tree from Inorder and Postorder | Medium | ⬜ | Recursion | A, G, B | — |
| 110 | Balanced Binary Tree | Easy | ⬜ | DFS | A, G, M, B | Day 1 |
| 112 | Path Sum | Easy | ⬜ | DFS | A, G, M, B | Day 3 |
| 113 | Path Sum II | Medium | ⬜ | DFS + Backtracking | A, G, M, B | Day 3 |
| 116 | Populating Next Right Pointers in Each Node | Medium | ⬜ | BFS / Pointer | A, G, M | — |
| 124 | Binary Tree Maximum Path Sum | Hard | ⬜ | DFS | A, G, M, Meta, B | — |
| 144 | Binary Tree Preorder Traversal | Easy | ✅ | DFS | A, G, M | — |
| 145 | Binary Tree Postorder Traversal | Easy | ✅ | DFS | A, G, M | — |
| 199 | Binary Tree Right Side View | Medium | ✅ | BFS | A, G, Meta, B | — |
| 222 | Count Complete Tree Nodes | Medium | ✅ | DFS | A, G | — |
| 226 | Invert Binary Tree | Easy | ✅ | DFS / BFS | A, G, M, Ap, B | — |
| 230 | Kth Smallest Element in a BST | Medium | ⬜ | DFS | A, G, M, Meta, B | — |
| 235 | Lowest Common Ancestor of a BST | Medium | ⬜ | BST Properties | A, G, M, Meta, B | Day 2 |
| 236 | Lowest Common Ancestor of a Binary Tree | Medium | ⬜ | DFS | A, G, M, Meta, B | Day 3 |
| 257 | Binary Tree Paths | Easy | ⬜ | DFS + Backtracking | A, G | — |
| 297 | Serialize and Deserialize Binary Tree | Hard | ⬜ | DFS / BFS | A, G, Meta, B, Ly | — |
| 337 | House Robber III | Medium | ⬜ | DP on Trees | A, G | — |
| 404 | Sum of Left Leaves | Easy | ⬜ | DFS | A, G | — |
| 437 | Path Sum III | Medium | ⬜ | DFS + Prefix Sum | A, G, Meta | — |
| 450 | Delete Node in a BST | Medium | ⬜ | BST Properties | A, G, M | — |
| 543 | Diameter of Binary Tree | Easy | ⬜ | DFS | A, G, Meta, B | Day 1 |
| 572 | Subtree of Another Tree | Easy | ⬜ | DFS | A, G | — |
| 700 | Search in a Binary Search Tree | Easy | ✅ | BST Properties / Recursion | A, G | Day 2 re-solve |
| 814 | Binary Tree Pruning | Medium | ⬜ | DFS | G | — |
| 1650 | Lowest Common Ancestor of a Binary Tree III | Medium | ⬜ | Two Pointers | Meta, G | — |

---

### 10. Backtracking — 14/22

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | Knight's Tour | — | ⬜ | — | — |
| — | M Coloring | — | ⬜ | — | — |
| — | Rat in a Maze | — | ⬜ | — | — |
| — | Merge Sort | — | ✅ | Recursion | A, G |
| — | Count Inversions | — | ⬜ | — | — |
| 17 | Letter Combinations of a Phone Number | Medium | ✅ | Backtracking | A, G, M, Meta, B |
| 22 | Generate Parentheses | Medium | ✅ | Backtracking | A, G, M, Meta, B, Ub |
| 37 | Sudoku Solver | Hard | ✅ | Backtracking | A, G, M, Ap |
| 39 | Combination Sum | Medium | ✅ | Backtracking | A, G, M, B |
| 40 | Combination Sum II | Medium | ⬜ | Backtracking | A, G, B |
| 46 | Permutations | Medium | ✅ | Backtracking | A, G, M, Meta, B |
| 47 | Permutations II | Medium | ⬜ | Backtracking | A, G |
| 51 | N-Queens | Hard | ✅ | Backtracking | A, G, Ap, B |
| 52 | N-Queens II | Hard | ✅ | Backtracking | A, G |
| 77 | Combinations | Medium | ✅ | Backtracking | A, G, M |
| 78 | Subsets | Medium | ✅ | Backtracking | A, G, M, Meta, B |
| 79 | Word Search | Medium | ✅ | Backtracking + DFS | A, G, M, Meta, B |
| 90 | Subsets II | Medium | ✅ | Backtracking | A, G |
| 131 | Palindrome Partitioning | Medium | ✅ | Backtracking | A, G, Meta |
| 212 | Word Search II | Hard | ✅ | Backtracking + Trie | A, G, Meta, B |
| 216 | Combination Sum III | Medium | ⬜ | Backtracking | A, G |
| 491 | Non-decreasing Subsequences | Medium | ⬜ | Backtracking | A, G |

---

### 11. Graphs — 20/42

> **30-Day focus:** Day 6 → 200, 695 &nbsp;·&nbsp; Day 7 → 994, 1091 &nbsp;·&nbsp; Day 8 → 207, 210

#### Graph concept implementations (non-LeetCode)

| Concept | Status | File |
|---|:---:|---|
| BFS Traversal | ✅ | `BFSTraversalInGraph.java` |
| DFS Traversal | ✅ | `DFSTraversalInGraph.java` |
| Adjacency Matrix | ✅ | `AdjacencyMatrix.java` |
| Cycle Detection — Undirected (BFS + DFS) | ✅ | `CycleDetectionUndirectedGraph.java` |
| Cycle Detection — Directed (BFS + DFS) | ✅ | `CourseSchedule.java` |
| Topological Sort (DFS) | ✅ | `TopologicalSortingDFS.java` · `CourseScheduleII.java` |
| Topological Sort (BFS / Kahn's) | ✅ | `TopologicalSortingBFS.java` · `CourseScheduleII.java` |
| Dijkstra's Algorithm | ✅ | `DijkstrasAlgorithm.java` |
| Prim's Algorithm (MST) | ✅ | `PrimsAlgorithm.java` |
| Kruskal's Algorithm (MST) | ✅ | `KruskalAlgorithm.java` |
| Bellman-Ford Algorithm | ✅ | `BellmanFordAlgorithm.java` |
| Disjoint Set Union (DSU) | ✅ | `DisjointSetUnion.java` |
| Floyd-Warshall | ⬜ | — |
| Check Bipartite Graph | ⬜ | — |
| Kosaraju (SCCs) | ⬜ | — |

#### LeetCode problems

| # | Problem | Difficulty | Status | Pattern | Companies | Roadmap |
|---|---|---|:---:|---|---|---|
| 133 | Clone Graph | Medium | ✅ | DFS / BFS | A, G, M, Meta, B | — |
| 200 | Number of Islands | Medium | ✅ | DFS / BFS | A, G, M, Meta, B, Ub | Day 6 |
| 207 | Course Schedule | Medium | ✅ | Topological Sort (BFS + DFS) | A, G, M, Meta, B | Day 8 |
| 208 | Implement Trie (Prefix Tree) | Medium | ✅ | Trie | A, G, M, Meta, B | — |
| 210 | Course Schedule II | Medium | ✅ | Topological Sort (BFS + DFS) | A, G, Meta, B | Day 8 |
| 261 | Graph Valid Tree | Medium | ✅ | Union Find / DFS | A, G, Ly | — |
| 269 | Alien Dictionary | Hard | ⬜ | Topological Sort | A, G, Meta, B, Ai | — |
| 286 | Walls and Gates | Medium | ⬜ | BFS | A, G, Meta | — |
| 323 | Number of Connected Components | Medium | ⬜ | Union Find / DFS | A, G, Ly | — |
| 329 | Longest Increasing Path in a Matrix | Hard | ⬜ | DFS + Memoization | A, G, Meta | — |
| 417 | Pacific Atlantic Water Flow | Medium | ⬜ | DFS / BFS | A, G, B | — |
| 547 | Number of Provinces | Medium | ⬜ | Union Find / DFS | A, G, M | — |
| 684 | Redundant Connection | Medium | ✅ | Union Find + DFS + BFS | A, G | — |
| 695 | Max Area of Island | Medium | ⬜ | DFS / BFS | A, G, Meta | Day 6 |
| 721 | Accounts Merge | Medium | ⬜ | Union Find / DFS | A, G, Meta | — |
| 733 | Flood Fill | Easy | ✅ | DFS on grid | A, G, M | — |
| 743 | Network Delay Time | Medium | ✅ | Dijkstra's | A, G, Meta, B | — |
| 787 | Cheapest Flights Within K Stops | Medium | ✅ | Bellman-Ford | A, G, Meta | — |
| 797 | All Paths From Source to Target | Medium | ✅ | DFS / Backtracking | A, G | — |
| 802 | Find Eventual Safe States | Medium | ⬜ | DFS | G, Meta | — |
| 827 | Making A Large Island | Hard | ⬜ | DFS | A, G | — |
| 994 | Rotting Oranges | Medium | ✅ | BFS (multi-source) | A, G, M, Meta | Day 7 |
| 1091 | Shortest Path in Binary Matrix | Medium | ⬜ | BFS | A, G, Meta, B | Day 7 |
| 1584 | Min Cost to Connect All Points | Medium | ✅ | MST (Prim's) | A, G, Meta | — |

---

### 12. Dynamic Programming — 10/43

> 📁 Additional DP practice files and custom implementations in repo: `Fibonacci`, `FibDP`, `FibDP2`, `BottonUpSum`, `TopDownSum`, `ClimbingStairsDpArray`, `ClimbingStairsDpMap`, `HouseRobberArray`, `HouseRobberMap`, `Knapsack01`, `MinCostClimbingStairs`, `MultiStageGraph`, `PartitionEqualSubSetSum`, `TargetSum`, `UnboundedKnapsack`, `Tribonacci`, `TribonacciBottomUp`, `TribonacciTopDown`, `CoinChange`, `CoinChangeII`
> 
> These files demonstrate memoization, top-down DP, bottom-up DP, and custom practice beyond roadmap problem entries.
> 
> **DP is your biggest gap — treat it as a 4-week dedicated focus, with at least one problem every single day.**

**Before writing any code on a DP problem, write these four lines as comments:**

```java
// State:      what does dp[i] (or dp[i][j]) represent?
// Transition: how do I compute dp[i] from previous values?
// Base case:  what are the smallest known answers?
// Answer:     where in the dp array does the final answer live?
```

If you cannot write all four lines before touching the code, stop — you are not ready to implement yet. This is the single habit that separates candidates who "kind of understand DP" from those who can solve it under pressure.

#### DP pattern groups (for quick recognition)

| Pattern | Problems | Core idea |
|---|---|---|
| **1D Linear DP** | 70, 198, 213, 91, 1137, 746 | `dp[i]` depends on 1–3 previous states |
| **Kadane / Subarray** | 53, 152 | Track best result ending at index `i` |
| **Grid DP** | 62, 63, 64, 931 | `dp[i][j] = top + left` |
| **0/1 Knapsack** | 416, 494 | Pick or skip — each item used once |
| **Unbounded Knapsack** | 322, 518, 279 | Pick or skip — item reuse allowed |
| **LIS** | 300, 673, 1218 | Compare all previous states |
| **String / 2D DP** | 1143, 72, 5, 139 | Match / no-match row-column transitions |
| **Counting DP** | 91, 1155 | Count number of valid ways |
| **DP on Trees** | 337 | Return multiple states from DFS |
| **Stock State Machine** | 123, 309, 714 | Buy / sell / hold / cooldown states |
| **Interval DP** | 312, palindrome partition | Try every partition point in `[i, j]` |

#### Problem list

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | 0-1 Knapsack | — | ✅ | 0/1 Knapsack | — |
| — | Unbounded Knapsack | — | ✅ | Unbounded Knapsack | — |
| — | Longest Common Substring | — | ⬜ | String DP | — |
| — | Palindromic Partitioning (MCM) | — | ⬜ | Interval DP | — |
| — | Unique BSTs | — | ⬜ | — | — |
| — | Nth Catalan | — | ⬜ | — | — |
| — | Wildcard Pattern Matching | — | ⬜ | String DP | — |
| — | Rod Cutting | — | ⬜ | — | — |
| — | Egg Dropping | — | ⬜ | — | — |
| — | Matrix Chain Multiplication | — | ⬜ | Interval DP | — |
| — | Longest Bitonic Subsequence | — | ⬜ | LIS | — |
| 5 | Longest Palindromic Substring | Medium | ⬜ | String DP | A, G, M, Ap, B |
| 53 | Maximum Subarray | Medium | ⬜ | Kadane's | A, G, M, Ap, B, Meta |
| 62 | Unique Paths | Medium | ⬜ | Grid DP | A, G, M, B |
| 63 | Unique Paths II | Medium | ⬜ | Grid DP | A, G, B |
| 64 | Minimum Path Sum | Medium | ⬜ | Grid DP | A, G, B |
| 70 | Climbing Stairs | Easy | ✅ | 1D Linear DP | A, G, M, Ap, B |
| 72 | Edit Distance | Medium | ⬜ | String DP | A, G, M, Meta, B |
| 91 | Decode Ways | Medium | ⬜ | Counting DP | A, G, Meta, B |
| 1137 | N-th Tribonacci Number | Easy | ✅ | 1D Linear DP | A, G |
| 123 | Best Time to Buy and Sell Stock III | Hard | ⬜ | Stock State Machine | A, G, Meta, B |
| 139 | Word Break | Medium | ⬜ | String DP | A, G, M, Meta, B |
| 152 | Maximum Product Subarray | Medium | ⬜ | Kadane's | A, G, Ly, B |
| 198 | House Robber | Medium | ✅ | 1D Linear DP | A, G, M, B, Ad |
| 213 | House Robber II | Medium | ⬜ | 1D Linear DP | A, G |
| 221 | Maximal Square | Medium | ⬜ | Grid DP | A, G, Meta, B |
| 279 | Perfect Squares | Medium | ⬜ | Unbounded Knapsack | A, G |
| 300 | Longest Increasing Subsequence | Medium | ⬜ | LIS | A, G, M, Meta, B |
| 309 | Best Time to Buy and Sell Stock with Cooldown | Medium | ⬜ | Stock State Machine | A, G |
| 312 | Burst Balloons | Hard | ⬜ | Interval DP | A, G, Meta |
| 322 | Coin Change | Medium | ✅ | Unbounded Knapsack | A, G, M, Meta, B, Ad |
| 337 | House Robber III | Medium | ⬜ | DP on Trees | A, G |
| 416 | Partition Equal Subset Sum | Medium | ✅ | 0/1 Knapsack | A, G, Meta |
| 494 | Target Sum | Medium | ✅ | 0/1 Knapsack | A, G |
| 518 | Coin Change II | Medium | ✅ | Unbounded Knapsack | A, G |
| 673 | Number of Longest Increasing Subsequence | Medium | ⬜ | LIS | A, G |
| 714 | Best Time to Buy and Sell Stock with Transaction Fee | Medium | ⬜ | Stock State Machine | A, G |
| 746 | Min Cost Climbing Stairs | Easy | ✅ | 1D Linear DP | A, G |
| 740 | Delete and Earn | Medium | ⬜ | 1D Linear DP | A, G |
| 931 | Minimum Falling Path Sum | Medium | ⬜ | Grid DP | A, G |
| 1143 | Longest Common Sequence | Medium | ⬜ | String DP | A, G, M, Meta, B |
| 1155 | Number of Dice Rolls With Target Sum | Medium | ⬜ | Counting DP | G |
| 1218 | Longest Arithmetic Subsequence of Given Difference | Medium | ⬜ | LIS | A, G |
| 1639 | Number of Ways to Form a Target String Given a Dictionary | Hard | ⬜ | String DP | G, Meta |
| 3129 | Find All Possible Stable Binary Arrays I | Hard | ✅ | DP + Prefix Sum | G |
| 3130 | Find All Possible Stable Binary Arrays II | Hard | ✅ | DP + Prefix Sum | G |

---

### 13. Greedy — 2/13

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | Indian Coins | — | ⬜ | — | — |
| — | Fractional Knapsack | — | ⬜ | — | — |
| — | Maximum Length of Pair Chain | — | ⬜ | — | — |
| — | Activity Selection | — | ⬜ | — | — |
| — | Job Scheduling | — | ⬜ | — | — |
| 45 | Jump Game II | Medium | ⬜ | Greedy | A, G, Meta, B |
| 55 | Jump Game | Medium | ⬜ | Greedy | A, G, M, Meta, B |
| 121 | Best Time to Buy and Sell Stock | Easy | ✅ | Greedy | A, G, M, Ap, B, Meta |
| 134 | Gas Station | Medium | ⬜ | Greedy | A, G, B |
| 435 | Non-overlapping Intervals | Medium | ⬜ | Greedy + Sorting | A, G, Meta, B |
| 452 | Minimum Arrows to Burst Balloons | Medium | ⬜ | Greedy + Sorting | A, G |
| 763 | Partition Labels | Medium | ⬜ | Greedy + Hashing | A, G, Meta |
| 1689 | Partitioning Into Min Number of Deci-Binary Numbers | Easy | ✅ | Greedy | A |

---

### 14. Trie — 2/5

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| — | Implement a Phone Directory | — | ⬜ | Trie | — |
| — | Longest String with All Prefixes | — | ⬜ | Trie | — |
| 208 | Implement Trie (Prefix Tree) | Medium | ✅ | Trie | A, G, M, Meta, B |
| 211 | Design Add and Search Word | Medium | ⬜ | Trie + DFS | A, G, Meta, B |
| 212 | Word Search II | Hard | ✅ | Trie + Backtracking | A, G, Meta, B |

---

### 15. System Design / LLD — 0/3

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| 146 | LRU Cache | Medium | ⬜ | Doubly LL + HashMap | A, G, M, Meta, B, Ub |
| 460 | LFU Cache | Hard | ⬜ | HashMap + TreeMap | A, G, Meta |
| 588 | Design In-Memory File System | Hard | ⬜ | Trie + HashMap | A, G, Dropbox |

---

### 16. Bit Manipulation — 4/12

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| 67 | Add Binary | Easy | ✅ | Bit Manipulation | A, G, Ap |
| 136 | Single Number | Easy | ✅ | XOR | A, G, M, B |
| 190 | Reverse Bits | Easy | ⬜ | Bit Manipulation | A, Ap, B |
| 191 | Number of 1 Bits | Easy | ⬜ | Bit Manipulation | A, Ap, B |
| 231 | Power of Two | Easy | ⬜ | Bit Manipulation | A, G |
| 268 | Missing Number | Easy | ⬜ | XOR / Math | A, G, M, B |
| 338 | Counting Bits | Easy | ⬜ | DP + Bits | A, G |
| 371 | Sum of Two Integers | Medium | ⬜ | Bit Manipulation | A, G, Meta |
| 461 | Hamming Distance | Easy | ⬜ | XOR | A, G |
| 693 | Binary Number with Alternating Bits | Easy | ⬜ | Bit Manipulation | A |
| 868 | Binary Gap | Easy | ✅ | Bit Manipulation | A |
| 1009 | Complement of Base 10 Integer | Easy | ✅ | Bit Mask | A, G |

---

### 17. Daily Challenges — 5/6

| # | Problem | Difficulty | Status | Pattern | Companies |
|---|---|---|:---:|---|---|
| 1318 | Minimum Flips to Make a OR b Equal to c | Medium | ✅ | Bit Manipulation | A, G |
| 1536 | Minimum Swaps to Arrange a Binary Grid | Medium | ✅ | Greedy / Simulation | A |
| 1545 | Find Kth Bit in Nth Binary String | Medium | ✅ | Recursion | A, G |
| 1784 | Check if Binary String Has at Most One Segment of Ones | Easy | ✅ | String | A |
| 1980 | Find Unique Binary String | Medium | ✅ | Backtracking | A, G |
| 2193 | Minimum Number of Moves to Make Palindrome | Hard | ⬜ | Greedy | G, Meta |

---

## Multi-Pattern Index

Problems that combine two or more core patterns — useful for recognizing overlaps under pressure.

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

## Project Structure

```
src/
├── arrays/              # Core array problems
├── arrays101/           # LeetCode arrays101 explore card
├── binarysearch/        # Binary search problems
├── dailychallenges/     # Daily challenge solutions
├── dp/                  # Dynamic programming
├── graph/               # Graph problems + concept implementations
├── hashing/             # HashMap / HashSet problems
├── heap/                # Heap / Priority Queue problems
├── linkedlist/          # Linked list problems
├── map/designhashmap/   # LC 706 Design HashMap
├── patterns/            # Pattern practice (Floyd's triangle etc.)
├── queue/               # Queue problems + implementations
├── recursion/           # Recursion fundamentals + backtracking
├── set/designhashset/   # LC 705 Design HashSet
├── slidingwindow/       # Sliding window problems
├── sortalgorithms/      # Sorting implementations + recursive variants
├── stack/               # Stack problems + implementations
├── strings/             # String problems
├── tree/                # Tree problems
└── twopointer/          # Two pointer problems
```

---

## Problem-Solving Philosophy

- Brute force first → optimize step by step
- Top-down recursion → bottom-up DP → space optimization
- Add memoization manually after understanding the recurrence
- Recognize patterns across problems (Floyd's cycle, two pointers, prefix sum, XOR)
- For every problem: state the pattern, write time/space complexity, note one mistake made
- Re-solve without notes after 24 hours — if you can't, you don't own the pattern yet
- Write production-quality Java with full Javadoc and labeled test cases

---

## Contributing

Contributions are welcome.

- **Add solutions** — implement unsolved problems following the existing code style
- **Improve code** — optimize existing solutions or add clearer explanations
- **Fix bugs** — report and fix any issues in the code
- **Documentation** — improve comments, README, or add new sections

Workflow: fork → feature branch → changes → test → pull request with a clear description.

---

## License & Disclaimer

Solutions are provided for educational purposes. All problem statements and test cases are the property of LeetCode — please respect their terms of service.

Code in this repository is provided as-is for learning. Use at your own risk.

---

*Last updated: April 30, 2026*