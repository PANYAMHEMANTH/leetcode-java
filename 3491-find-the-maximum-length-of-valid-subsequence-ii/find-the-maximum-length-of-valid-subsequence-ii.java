class Solution {
    public int maximumLength(int[] nums, int k) {
        // dp[rem][mod] stores the max length of a subsequence where:
        // 1. The sum of adjacent pairs % k == rem
        // 2. The last element added has value % k == mod
        int[][] dp = new int[k][k];
        int maxLen = 0;

        for (int num : nums) {
            int curr = num % k;
            
            // For the current number, try to extend subsequences for every possible 
            // 'pair sum remainder' (rem)
            for (int rem = 0; rem < k; rem++) {
                // Calculate what the previous number's mod must have been
                int prev = (rem - curr + k) % k;
                
                // Extend the sequence
                dp[rem][curr] = dp[rem][prev] + 1;
                
                // Update global maximum
                maxLen = Math.max(maxLen, dp[rem][curr]);
            }
        }
        
        return maxLen;
    }
}