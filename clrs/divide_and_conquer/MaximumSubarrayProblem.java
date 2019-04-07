package clrs.divide_and_conquer;

public class MaximumSubarrayProblem {
    class TradeInfo {
        int startIndex;
        int endIndex;
        int totalProfit;

        TradeInfo(int start, int end, int profit) {
            startIndex = start;
            endIndex = end;
            totalProfit = profit;
        }
    }

    private TradeInfo findSpanningTrade(int[] dayChangeInfo,
                                        int start,
                                        int mid,
                                        int end) {
        int totalLeftSum = Integer.MIN_VALUE, totalRightSum = Integer.MIN_VALUE;
        int leftSum = 0, rightSum = 0;
        int maxLeft = -1, maxRight = -1;
        for (int i = mid; i>=start; i--) {
            leftSum += dayChangeInfo[i];
            if (leftSum > totalLeftSum) {
                totalLeftSum = leftSum;
                maxLeft = i;
            }
        }

        for (int j = mid+1; j<=end; j++) {
            rightSum += dayChangeInfo[j];
            if (rightSum > totalRightSum) {
                totalRightSum = rightSum;
                maxRight = j;
            }
        }

        return new TradeInfo(maxLeft, maxRight, (totalLeftSum+totalRightSum));
    }

    private TradeInfo findMaxTotalProfit(int[] dayChangeInfo,
                                         int start,
                                         int end) {
        if (start == end)
            return new TradeInfo(start, end, dayChangeInfo[start]);
        else {
            int mid = (start + end)/2;
            TradeInfo firstHalf = findMaxTotalProfit(dayChangeInfo, start, mid);
            TradeInfo secondHalf = findMaxTotalProfit(dayChangeInfo, mid+1, end);
            TradeInfo middlePart = findSpanningTrade(dayChangeInfo, start, mid, end);

            if (
                    firstHalf.totalProfit > secondHalf.totalProfit
                    && firstHalf.totalProfit > middlePart.totalProfit
            ) {
                return firstHalf;
            } else if (
                    secondHalf.totalProfit > firstHalf.totalProfit
                    && secondHalf.totalProfit > middlePart.totalProfit
            ) {
                return secondHalf;
            }
            return middlePart;
        }
    }

    public static void main(String[] args) {
        MaximumSubarrayProblem problem = new MaximumSubarrayProblem();
        int[] dayChange = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        TradeInfo bestTrade = problem
                .findMaxTotalProfit(dayChange, 0, dayChange.length-1);
        System.out.println("Start day: "+bestTrade.startIndex+ "\n" +
                "Last day: "+bestTrade.endIndex + "\n" +
                "Max profit: "+bestTrade.totalProfit);

    }
}
