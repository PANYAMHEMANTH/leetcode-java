import java.util.*;

class Solution {
    public int maxFreeTime(int eventTime, int k, int[] start, int[] end) {
        int n = start.length;

        // durations and prefix sums
        long[] dur = new long[n];
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            dur[i] = (long)end[i] - (long)start[i];
            pref[i + 1] = pref[i] + dur[i];
        }
        long totalDur = pref[n];
        long initialFree = (long)eventTime - totalDur;
        if (initialFree <= 0) return 0;

        // compute original max gap without moves
        long maxGap = Math.max(start[0] - 0, eventTime - end[n - 1]);
        for (int i = 1; i < n; ++i) {
            maxGap = Math.max(maxGap, (long)start[i] - (long)end[i - 1]);
        }

        // prepare A[j] and B[i]
        long[] A = new long[n]; // A[j] = nextStart - P[j+1]
        long[] B = new long[n]; // B[i] = prevEnd - P[i]
        for (int j = 0; j < n; ++j) {
            long nextStart = (j == n - 1) ? (long) eventTime : (long) start[j + 1];
            A[j] = nextStart - pref[j + 1];
        }
        for (int i = 0; i < n; ++i) {
            long prevEnd = (i == 0) ? 0L : (long) end[i - 1];
            B[i] = prevEnd - pref[i];
        }

        // Sliding window min on B for windows of i in [j-k+1, j]
        Deque<Integer> dq = new ArrayDeque<>(); // store indices of B with increasing B[]
        long best = 0;

        for (int j = 0; j < n; ++j) {
            int left = Math.max(0, j - k + 1);

            // push B index j into deque for future j' >= j
            // But we need to ensure deque always holds candidates in current window of i indices.
            // For each j we want min B[i] for i in [left..j].
            // So first, add B[j] (index j) in deque in increasing B order.
            while (!dq.isEmpty() && B[dq.peekLast()] >= B[j]) dq.pollLast();
            dq.offerLast(j);

            // remove indices < left
            while (!dq.isEmpty() && dq.peekFirst() < left) dq.pollFirst();

            // Now dq.peekFirst() is index of minimal B[i] in [left..j]
            long minB = B[dq.peekFirst()];
            long candidate = A[j] - minB;
            if (candidate > best) best = candidate;
        }

        long ans = Math.max(maxGap, best);
        if (ans > initialFree) ans = initialFree; // safeguard (shouldn't be necessary)
        return (int) Math.max(0, ans);
    }
}
