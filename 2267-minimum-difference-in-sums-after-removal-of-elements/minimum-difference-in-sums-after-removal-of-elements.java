import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        
        // Arrays to store the best sums at each position
        long[] minLeft = new long[nums.length];
        
        // Max Heap for the left part (to keep the smallest n numbers)
        // We use reverseOrder because Java PQ is a Min Heap by default
        PriorityQueue<Integer> leftPQ = new PriorityQueue<>(Collections.reverseOrder());
        long currentLeftSum = 0;
        
        // 1. Build the minLeft array
        // We iterate from 0 to 2*n because the left part needs at least n elements
        // and can extend up to 2*n
        for (int i = 0; i < nums.length; i++) {
            leftPQ.add(nums[i]);
            currentLeftSum += nums[i];
            
            // If we have more than n elements, remove the largest one
            if (leftPQ.size() > n) {
                currentLeftSum -= leftPQ.poll();
            }
            
            // If we have exactly n elements, record the sum
            if (leftPQ.size() == n) {
                minLeft[i] = currentLeftSum;
            }
        }
        
        // 2. Build the maxRight logic and find the result
        // Note: We don't strictly need a 'maxRight' array; we can calculate it 
        // on the fly while iterating backwards to save a bit of memory, 
        // but using an array is clearer conceptually. Let's do it on the fly.
        
        long minDiff = Long.MAX_VALUE;
        long currentRightSum = 0;
        // Min Heap for the right part (to keep the largest n numbers)
        PriorityQueue<Integer> rightPQ = new PriorityQueue<>();
        
        // Iterate backwards. 
        // The Right part starts at index i + 1. 
        // The split point i can range from n - 1 up to 2*n - 1.
        // Therefore, the Right part can start from 2*n down to n.
        for (int i = nums.length - 1; i >= n; i--) {
            rightPQ.add(nums[i]);
            currentRightSum += nums[i];
            
            if (rightPQ.size() > n) {
                currentRightSum -= rightPQ.poll(); // Remove smallest to keep sum maximal
            }
            
            if (rightPQ.size() == n) {
                // The split is at i-1. 
                // Left part ends at i-1. Right part starts at i.
                long diff = minLeft[i - 1] - currentRightSum;
                minDiff = Math.min(minDiff, diff);
            }
        }
        
        return minDiff;
    }
}