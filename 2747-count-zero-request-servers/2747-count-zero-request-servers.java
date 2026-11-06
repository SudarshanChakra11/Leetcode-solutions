
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs by timestamp
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;

        // Store query time and original index
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        // Sort queries by time
        Arrays.sort(sortedQueries,
            (a, b) -> Integer.compare(a[0], b[0]));

        int[] answer = new int[m];
        int[] count = new int[n + 1];

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int[] query : sortedQueries) {

            int q = query[0];
            int index = query[1];

            int start = q - x;

            // Add logs up to query time
            while (right < logs.length &&
                   logs[right][1] <= q) {

                int server = logs[right][0];

                if (count[server] == 0) {
                    activeServers++;
                }

                count[server]++;
                right++;
            }

            // Remove logs outside the time window
            while (left < right &&
                   logs[left][1] < start) {

                int server = logs[left][0];

                count[server]--;

                if (count[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            // Count servers with zero requests
            answer[index] = n - activeServers;
        }

        return answer;
    }
}
