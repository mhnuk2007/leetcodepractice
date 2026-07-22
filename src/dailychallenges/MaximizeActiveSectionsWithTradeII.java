package dailychallenges;

import java.util.ArrayList;
import java.util.List;

public class MaximizeActiveSectionsWithTradeII {
    static class SegTreeNode {
        int start, end, maxVal;
        SegTreeNode left, right;

        public SegTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int[] prefixOnes = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixOnes[i + 1] = prefixOnes[i] + (s.charAt(i) == '1' ? 1 : 0);
        }
        List<Integer> blockLen = new ArrayList<>();
        List<Integer> blockLeft = new ArrayList<>();
        List<Integer> blockRight = new ArrayList<>();

        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) != '0') i++;
                blockLen.add(i - start);
                blockLeft.add(start);
                blockRight.add(i - 1);
            } else i++;
        }
        int m = blockLen.size();
        int[] pairSums = new int[Math.max(m - 1, 0)];
        for (int j = 0; j + 1 < m; j++) {
            pairSums[j] = blockLen.get(j) + blockLeft.get(j + 1);
        }

        SegTreeNode root = pairSums.length > 0 ? build(pairSums, 0, pairSums.length) : null;
        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            int activeInRange = prefixOnes[r + 1] - prefixOnes[l];
            int bestGain = 0;

            int firstBlock = firstBlockTouching(blockRight, l);
            int lastBlock = lastBlockTouching(blockLeft, r) - 1;

            if (firstBlock <= lastBlock && firstBlock < m && lastBlock >= 0) {
                if (firstBlock == lastBlock) {
                    bestGain = 0;
                } else {
                    int firstClippedLen = blockRight.get(firstBlock) - Math.max(blockLeft.get(firstBlock), l) + 1;
                    int lastClippedLen = Math.min(blockRight.get(lastBlock), r) - blockLeft.get(lastBlock) + 1;

                    if (lastBlock == firstBlock + 1) {
                        bestGain = firstClippedLen + lastClippedLen;
                    } else {
                        int optionA = firstClippedLen + blockLen.get(firstBlock + 1);
                        int optionB = blockLen.get(lastBlock - 1) + lastClippedLen;
                        int optionC = root == null ? 0 : queryMax(root, firstBlock + 1, lastBlock - 2);
                        bestGain = Math.max(optionA, Math.max(optionB, optionC));
                    }
                }
            }

            ans.add(activeInRange + bestGain);
        }

        return ans;
    }

    private SegTreeNode build(int[] data, int start, int end) {
        SegTreeNode node = new SegTreeNode(start, end);
        if (start == end) {
            node.maxVal = data[start];
            return node;
        }
        int mid = (start + end) / 2;
        node.left = build(data, start, mid);
        node.right = build(data, mid + 1, end);
        node.maxVal = Math.max(node.left.maxVal, node.right.maxVal);
        return node;
    }

    private int queryMax(SegTreeNode node, int l, int r) {
        if (node == null || r < node.start || node.end < l) return 0;
        if (l <= node.start && node.end <= r) return node.maxVal;
        return Math.max(queryMax(node.left, l, r), queryMax(node.right, l, r));
    }

    private int firstBlockTouching(List<Integer> blockRight, int target) {
        int lo = 0, hi = blockRight.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (blockRight.get(mid) >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    private int lastBlockTouching(List<Integer> blockLeft, int target) {
        int lo = 0, hi = blockLeft.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (blockLeft.get(mid) <= target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}