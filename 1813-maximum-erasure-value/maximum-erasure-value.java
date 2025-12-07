class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        // Use a HashSet to track unique elements in the current window
        Set<Integer> set = new HashSet<>();
        
        int left = 0;
        int currentSum = 0;
        int maxScore = 0;
        
        // Iterate with the right pointer
        for (int right = 0; right < nums.length; right++) {
            // If we find a duplicate, shrink the window from the left
            while (set.contains(nums[right])) {
                set.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }
            
            // Add the new element to the window and sum
            set.add(nums[right]);
            currentSum += nums[right];
            
            // Update the maximum score found so far
            maxScore = Math.max(maxScore, currentSum);
        }
        
        return maxScore;
    }
}