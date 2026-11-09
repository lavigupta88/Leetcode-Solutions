class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        // Initially all servers are available
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Stores {finishTime, serverIndex}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int[] count = new int[k];

        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {

            int time = arrival[i];

            // Release servers that have completed their tasks
            while (!busy.isEmpty() && busy.peek()[0] <= time) {

                long[] server = busy.poll();

                available.add((int) server[1]);
            }

            // If no server is available, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Preferred server
            int preferred = i % k;

            // Find the next available server
            Integer server = available.ceiling(preferred);

            // Circular search: start from index 0
            if (server == null) {
                server = available.first();
            }

            // Assign request
            available.remove(server);

            count[server]++;

            maxRequests = Math.max(maxRequests, count[server]);

            // Calculate finishing time
            busy.offer(new long[]{
                (long) time + load[i],
                server
            });
        }

        // Collect servers with maximum requests
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}
