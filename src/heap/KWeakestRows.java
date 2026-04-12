package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LC 1337 - K Weakest Rows in a Matrix
 *
 * <p>Given an {@code m x n} binary matrix {@code mat} where each row represents
 * a regiment — 1s are soldiers, 0s are civilians — and all 1s appear before 0s
 * in every row, return the indices of the {@code k} weakest rows ordered from
 * weakest to strongest. A row is weaker if it has fewer soldiers; ties are
 * broken by the smaller row index.
 *
 * <p><b>Approach:</b> Max-Heap of size k + Binary Search.
 * <ul>
 *   <li>Binary search counts soldiers per row in O(log n) instead of O(n).</li>
 *   <li>A max-heap of size k stores {@code [soldierCount, rowIndex]} pairs,
 *       evicting the strongest row when size exceeds k.</li>
 *   <li>After all rows, the heap holds the k weakest — drain in reverse
 *       (heap pops strongest-of-the-weak first) to get weakest-first order.</li>
 * </ul>
 *
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>Time:  O(m log n + m log k) — binary search per row + heap ops</li>
 *   <li>Space: O(k)                 — heap never exceeds k pairs</li>
 * </ul>
 */
public class KWeakestRows {

    public static void main(String[] args) {
        // TC1: LC example 1
        // soldiers per row: [3,1,2,2,1] → weakest order: row2(1),row4(1) tie→idx,row0(2),row3(2) tie→idx
        // k=3 → [2,0,3]... wait soldiers: row0=3,row1=4,row2=1,row3=2,row4=5
        // sorted: row2(1),row3(2)... actually:
        // row0:[1,1,0,0,0]=2, row1:[1,1,1,1,0]=4, row2:[1,0,0,0,0]=1, row3:[1,1,0,0,0]=2, row4:[1,1,1,1,1]=5
        // weakest: row2(1), row0(2), row3(2) → k=3 → [2,0,3]
        int[][] mat1 = {
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        };
        System.out.println("TC1 (expect [2,0,3]): " + Arrays.toString(kWeakestRows(mat1, 3)));

        // TC2: LC example 2
        // row0:[1,0,0,0]=1, row1:[1,1,1,1]=4, row2:[1,0,0,0]=1, row3:[1,0,0,0]=1
        // weakest k=2: tie at 1 → row0,row2,row3 → pick row0,row2
        int[][] mat2 = {
                {1, 0, 0, 0},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
        System.out.println("TC2 (expect [0,2]): " + Arrays.toString(kWeakestRows(mat2, 2)));

        // TC3: k=m — return all rows sorted by weakness
        // soldiers: row0=0, row1=1, row2=2 → [0,1,2]
        int[][] mat3 = {
                {0, 0, 0},
                {1, 0, 0},
                {1, 1, 0}
        };
        System.out.println("TC3 (expect [0,1,2]): " + Arrays.toString(kWeakestRows(mat3, 3)));

        // TC4: k=1 — only the single weakest row
        // soldiers: row0=3, row1=0, row2=2 → weakest row1
        int[][] mat4 = {
                {1, 1, 1},
                {0, 0, 0},
                {1, 1, 0}
        };
        System.out.println("TC4 (expect [1]): " + Arrays.toString(kWeakestRows(mat4, 1)));

        // TC5: All rows equally weak (all zeros) — tie broken by index
        // soldiers: [0,0,0] → k=2 → [0,1]
        int[][] mat5 = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        System.out.println("TC5 (expect [0,1]): " + Arrays.toString(kWeakestRows(mat5, 2)));

        // TC6: All rows full (all ones) — tie broken by index
        // soldiers: [3,3,3] → k=2 → [0,1]
        int[][] mat6 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        System.out.println("TC6 (expect [0,1]): " + Arrays.toString(kWeakestRows(mat6, 2)));

        // TC7: Single row, k=1
        // soldiers: row0=1 → [0]
        int[][] mat7 = {
                {1, 0, 0}
        };
        System.out.println("TC7 (expect [0]): " + Arrays.toString(kWeakestRows(mat7, 1)));

        // TC8: Reverse order — strongest rows first, weakest last
        // soldiers: row0=3, row1=2, row2=1, row3=0 → k=2 → [3,2]
        int[][] mat8 = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };
        System.out.println("TC8 (expect [3,2]): " + Arrays.toString(kWeakestRows(mat8, 2)));

        // TC9: Large tie block — index ordering decides
        // soldiers: row0=1, row1=1, row2=1, row3=2 → k=3 → [0,1,2]
        int[][] mat9 = {
                {1, 0, 0},
                {1, 0, 0},
                {1, 0, 0},
                {1, 1, 0}
        };
        System.out.println("TC9 (expect [0,1,2]): " + Arrays.toString(kWeakestRows(mat9, 3)));

        // TC10: Single column matrix
        // soldiers: row0=1, row1=0, row2=1 → k=2 → [1,0]
        int[][] mat10 = {
                {1},
                {0},
                {1}
        };
        System.out.println("TC10 (expect [1,0]): " + Arrays.toString(kWeakestRows(mat10, 2)));
    }

    /**
     * Returns the indices of the k weakest rows in ascending weakness order.
     *
     * @param mat m x n binary matrix; 1s precede 0s in every row
     * @param k   number of weakest rows to return; 1 <= k <= m
     * @return int array of length k with row indices, weakest first
     */
    public static int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]
        );
        for (int row = 0; row < mat.length; row++) {
            int soldiers = countSoldiers(mat[row]);
            maxHeap.offer(new int[]{soldiers, row});
            if (maxHeap.size() > k) maxHeap.poll();
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = maxHeap.poll()[1];
        }
        return res;
    }

    /**
     * Counts soldiers (1s) in a sorted binary row using binary search.
     * All 1s appear before 0s, so the count equals the first index of 0.
     *
     * @param row sorted binary array (1s then 0s)
     * @return number of 1s in the row
     */
    public static int countSoldiers(int[] row) {
        int low = 0;
        int high = row.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) low = mid + 1;
            else high = mid;
        }
        return low;
    }
}