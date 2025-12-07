import java.util.*;

class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        // Adjacency list to store pairs based on their starting index (smaller value)
        // List stores int[]{end_index, original_pair_index}
        List<int[]>[] adj = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < conflictingPairs.length; i++) {
            int u = conflictingPairs[i][0];
            int v = conflictingPairs[i][1];
            // Normalize so pair is [min, max]
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }
            adj[u].add(new int[]{v, i});
        }

        // State variables for the backwards sweep
        long totalBaseSubarrays = 0;
        long[] gains = new long[conflictingPairs.length];
        
        // Initialize limits to n + 1 (meaning no restriction)
        long min_b = n + 1;
        long second_min_b = n + 1;
        int min_b_count = 0;
        int limiting_pair_id = -1;

        // Sweep from n down to 1
        for (int i = n; i >= 1; i--) {
            // Add all pairs starting at current index i into our state
            for (int[] pair : adj[i]) {
                int b = pair[0];
                int id = pair[1];

                if (b < min_b) {
                    // New global minimum found
                    second_min_b = min_b;
                    min_b = b;
                    min_b_count = 1;
                    limiting_pair_id = id;
                } else if (b == min_b) {
                    // Duplicate minimum found
                    min_b_count++;
                    limiting_pair_id = -1; // Not unique anymore
                } else if (b < second_min_b) {
                    // Updates second minimum (relevant for gain calculation)
                    second_min_b = b;
                }
            }

            // 1. Add base count (assuming no removal)
            // The valid range is [i, min_b - 1], length is min_b - i
            totalBaseSubarrays += (min_b - i);

            // 2. Calculate potential gain
            // If the current min_b is enforced by exactly ONE pair, removing it 
            // extends the range to second_min_b.
            if (min_b_count == 1 && limiting_pair_id != -1) {
                gains[limiting_pair_id] += (second_min_b - min_b);
            }
        }

        // Find the maximum gain achievable
        long maxGain = 0;
        for (long gain : gains) {
            maxGain = Math.max(maxGain, gain);
        }

        return totalBaseSubarrays + maxGain;
    }
}