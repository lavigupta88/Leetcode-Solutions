import java.util.*;

class Solution {

    TreeMap<Integer, Integer> low = new TreeMap<>();
    TreeMap<Integer, Integer> high = new TreeMap<>();

    int sizeLow = 0, sizeHigh = 0;
    long sumLow = 0, sumHigh = 0;

    void addMap(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    void removeMap(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);

        if (count == 1)
            map.remove(val);
        else
            map.put(val, count - 1);
    }

    void moveLowToHigh() {
        int val = low.lastKey();

        removeMap(low, val);
        addMap(high, val);

        sizeLow--;
        sizeHigh++;

        sumLow -= val;
        sumHigh += val;
    }

    void moveHighToLow() {
        int val = high.firstKey();

        removeMap(high, val);
        addMap(low, val);

        sizeHigh--;
        sizeLow++;

        sumHigh -= val;
        sumLow += val;
    }

    void balance() {
        while (sizeLow > sizeHigh + 1)
            moveLowToHigh();

        while (sizeLow < sizeHigh)
            moveHighToLow();
    }

    void add(int val) {
        if (low.isEmpty() || val <= low.lastKey()) {
            addMap(low, val);
            sizeLow++;
            sumLow += val;
        } else {
            addMap(high, val);
            sizeHigh++;
            sumHigh += val;
        }

        balance();
    }

    void remove(int val) {
        if (low.containsKey(val)) {
            removeMap(low, val);
            sizeLow--;
            sumLow -= val;
        } else {
            removeMap(high, val);
            sizeHigh--;
            sumHigh -= val;
        }

        balance();
    }

    long getCost() {
        long median = low.lastKey();

        return median * sizeLow - sumLow
             + sumHigh - median * sizeHigh;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        // Calculate cost of every window of size x
        for (int i = 0; i < n; i++) {

            add(nums[i]);

            if (i >= x) {
                remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = getCost();
            }
        }

        // Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int t = 1; t <= k; t++) {
            Arrays.fill(dp[t], INF);
        }

        for (int t = 1; t <= k; t++) {

            for (int i = 1; i <= n; i++) {

                // Don't select a window ending here
                dp[t][i] = dp[t][i - 1];

                // Select the last x elements
                if (i >= x && dp[t - 1][i - x] != INF) {

                    long current = dp[t - 1][i - x]
                                 + cost[i - x];

                    dp[t][i] = Math.min(dp[t][i], current);
                }
            }
        }

        return dp[k][n];
    }
}
