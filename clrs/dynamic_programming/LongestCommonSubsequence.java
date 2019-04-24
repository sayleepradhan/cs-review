package clrs.dynamic_programming;

public class LongestCommonSubsequence {
    class Solution {
        char[][] path; // array b
        int[][] score; // array c
        public Solution(char[][] b, int[][] c) {
            path = b;
            score = c;
        }
    }

    private Solution solveLCS(
            char[] firstSeq,
            char[] secondSeq) {
        int m = firstSeq.length;
        int n = secondSeq.length;
        char[][] path = new char[m][n];
        int[][] score = new int[m+1][n+1];
        for (int i = 1; i < score.length; i++) {
            score[i][0] = 0;
        }
        for (int j = 0; j < score[0].length; j++) {
            score[0][j] = 0;
        }
        for (int i = 1; i <=m ; i++) {
            for (int j = 1; j <= n; j++) {
                if (firstSeq[i-1] == secondSeq[j-1]) {
                    score[i][j] = score[i-1][j-1] + 1;
                    path[i-1][j-1] = 'd'; // for diagonal
                } else if (score[i-1][j] >= score[i][j-1]) {
                    score[i][j] = score[i-1][j];
                    path[i-1][j-1] = 'u'; // for upwards
                } else {
                    score[i][j] = score[i][j-1];
                    path[i-1][j-1] = 'r'; // for right
                }
            }
        }
        return new Solution(path, score);
    }

    private void printLCS(char[][] path, char[] firstSeq, int i, int j) {
        if (i == -1 || j == -1) {
            return;
        }
        if (path[i][j] == 'd') {
            printLCS(path,firstSeq, i-1, j-1);
            System.out.print(firstSeq[i]+" ");
        } else if (path[i][j] == 'u') {
            printLCS(path,firstSeq, i-1, j);
        } else {
            printLCS(path, firstSeq, i, j-1);
        }
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        char[] x = {'A','B','C','B','D','A','B'};
        char[] y = {'B','D','C','A','B','A'};
        Solution solution = lcs.solveLCS(x,y);
        lcs.printLCS(solution.path, x, x.length-1, y.length-1);
    }
}
