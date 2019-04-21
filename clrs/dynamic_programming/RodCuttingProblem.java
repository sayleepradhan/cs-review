package clrs.dynamic_programming;

import java.util.Arrays;

public class RodCuttingProblem {
    private int recursiveNaiveCutRod(int[] p, int n) {
        if (n == -1) {
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for (int i = 0; i<= n; i++) {
            q = Math.max(q, p[i] + recursiveNaiveCutRod(p, n - i - 1));
        }
        return q;
    }
    private int memoizedTopDownCutRod(int[] prices, int rodLength) {

        int[] auxArray = new int[rodLength];
        Arrays.fill(auxArray, Integer.MIN_VALUE);

        return memoizedTopDownCutRodAux(prices, rodLength-1, auxArray);
    }

    private int memoizedTopDownCutRodAux(int[] prices, int n, int[] aux) {
        if (n == -1) {
            return 0;
        }
        if (aux[n] >= 0) {
            return aux[n];
        }

        int q = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            q = Math.max(q, prices[i] +
                    memoizedTopDownCutRodAux(prices, n - i - 1, aux));
        }
        aux[n] = q;
        return q;
    }

    private int bottomUpCutRod(int[] p, int n) {
        int[] r = new int[n+1];
        r[0] = 0;
        for (int j = 1; j <= n; j++) {
            int q = Integer.MIN_VALUE;
            for (int i = 1; i<=j; i++ ) {
                q = Math.max(q, p[i-1] + r[j-i]);
            }
            r[j] = q;
        }
        return r[n];
    }


    public static void main(String[] args) {
        RodCuttingProblem instance = new RodCuttingProblem();
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        System.out.println("Optimal price (using naive algorithm) for length 9: "
                + instance.recursiveNaiveCutRod(prices, 8));
        System.out.println("Optimal price (using naive algorithm)  for length 7: "
                + instance.recursiveNaiveCutRod(prices, 6));

        System.out.println("Optimal price (using Top Down DP algorithm) for length 9: "
                + instance.memoizedTopDownCutRod(prices, 9));
        System.out.println("Optimal price (using Top Down DP algorithm) for length 7: "
                + instance.memoizedTopDownCutRod(prices, 7));

        System.out.println("Optimal price (using Bottom up DP algorithm) for length 9: "
                + instance.bottomUpCutRod(prices, 9));
        System.out.println("Optimal price (using Bottom up DP algorithm) for length 7: "
                + instance.bottomUpCutRod(prices, 7));

    }
}
