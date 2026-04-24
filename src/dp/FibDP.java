package dp;

import java.util.HashMap;
import java.util.Map;

public class FibDP {

    static int fib(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        return fibHelper(n, map);
    }

    static int fibHelper(int n, Map<Integer, Integer> map) {
        if (n <= 1) return n;

        if (map.containsKey(n)) return map.get(n);

        int result = fibHelper(n - 1, map) + fibHelper(n - 2, map);
        map.put(n, result);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(FibDP.fib(4));

        int n = 10;
        for (int i = 0; i <= n; i++) {
            System.out.print(fib(i) + " ");
        }
    }
}