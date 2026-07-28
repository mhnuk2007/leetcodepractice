package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfEratosthenes {
    public static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i*i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i*i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    return isPrime;
    }
    public static void main(String[] args) {
        int n = 30;
        boolean[] primeNumbers = sieve(n);
        List<Integer> primes = new ArrayList<>();
        for (int i = 1; i < primeNumbers.length ; i++) {
            if (primeNumbers[i]) {primes.add(i);}
        }

        System.out.println(primes);

    }
}
