
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        // Available servers: weight, index
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: finishTime, index
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Initially, all servers are available
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[m];

        long time = 0;

        for (int i = 0; i < m; i++) {

            // Task i arrives at time i
            time = Math.max(time, i);

            // Release all servers that have finished
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= time) {

                long[] server = busy.poll();

                int index = (int) server[1];

                available.offer(
                    new int[]{servers[index], index}
                );
            }

            // If no server is available, wait
            if (available.isEmpty()) {

                time = busy.peek()[0];

                // Release all servers finishing at this time
                while (!busy.isEmpty() &&
                       busy.peek()[0] <= time) {

                    long[] server = busy.poll();

                    int index = (int) server[1];

                    available.offer(
                        new int[]{servers[index], index}
                    );
                }
            }

            // Select the best available server
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            // Calculate when this server becomes free
            long finishTime = time + tasks[i];

            busy.offer(new long[]{finishTime, index});
        }

        return ans;
    }
}
