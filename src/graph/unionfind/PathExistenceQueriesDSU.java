package graph;

import java.util.ArrayList;
import java.util.List;

public class PathExistenceQueriesDSU {
    static class DSU{
        int[] parent;
        int[] rank;
        DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
    }

    public static boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        return null;
    }


    public static void main(String[] args) {
        /*
        Input: n = 4, nums = [2,5,6,8],
        maxDiff = 2,
        queries = [[0,1],[0,2],[1,3],[2,3]]
        Output: [false,false,true,true]
         */

        int n = 4;
        int[] nums = {2, 5, 6, 8};
        int maxDiff = 2;
        int[][] queries = {
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 3}
        };

        System.out.println("Output: " + java.util.Arrays.toString(pathExistenceQueries(n, nums, maxDiff, queries)));
    }
}
