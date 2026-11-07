
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        );

        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Long.compare(a[0], b[0]);
                if (a[1] != b[1])
                    return Long.compare(a[1], b[1]);
                return Long.compare(a[2], b[2]);
            }
        );

        for (int i = 0; i < servers.length; i++) {
            available.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[tasks.length];

        long time = 0;

        for (int i = 0; i < tasks.length; i++) {

            time = Math.max(time, i);

            // Release completed servers
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] s = busy.poll();

                available.offer(new int[]{
                    (int) s[1], (int) s[2]
                });
            }

            // Wait if no server is available
            if (available.isEmpty()) {

                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] s = busy.poll();

                    available.offer(new int[]{
                        (int) s[1], (int) s[2]
                    });
                }
            }

            // Select server with minimum weight and index
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            // Assign task
            busy.offer(new long[]{
                time + tasks[i], weight, index
            });
        }

        return ans;
    }
}
