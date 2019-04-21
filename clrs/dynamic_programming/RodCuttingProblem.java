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
    private int memoizedTopDownSolution(int[] prices, int rodLength) {

        int[] auxArray = new int[rodLength];
        Arrays.fill(auxArray, Integer.MIN_VALUE);

        return memoizedTopDownAux(prices, rodLength-1, auxArray);
    }

    private int memoizedTopDownAux(int[] prices, int n, int[] aux) {
        if (n == -1) {
            return 0;
        }
        if (aux[n] >= 0) {
            return aux[n];
        }

        int q = Integer.MIN_VALUE;
        for (int i = 0; i <= n; i++) {
            q = Math.max(q, prices[i] +
                    memoizedTopDownAux(prices, n - i - 1, aux));
        }
        aux[n] = q;
        return q;
    }


    public static void main(String[] args) {
        RodCuttingProblem instance = new RodCuttingProblem();
        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        System.out.println("Optimal price (using naive algorithm) for length 9: "
                + instance.recursiveNaiveCutRod(prices, 8));
        System.out.println("Optimal price (using naive algorithm)  for length 7: "
                + instance.recursiveNaiveCutRod(prices, 6));

        System.out.println("Optimal price (using Top Down DP algorithm) for length 9: "
                + instance.memoizedTopDownSolution(prices, 9));
        System.out.println("Optimal price (using Top Down DP algorithm) for length 7: "
                + instance.memoizedTopDownSolution(prices, 7));

    }
}
