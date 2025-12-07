class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Step 1: Ensure nums1 is the smaller array for efficiency
        // If nums1 is larger, swap them to run binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;

        // Step 2: Perform Binary Search on the partition of nums1
        while (low <= high) {
            // partitionX is the index where we cut nums1
            int partitionX = (low + high) / 2;
            
            // partitionY is calculated so that the left side has half the total elements
            // (m + n + 1) / 2 ensures that if total is odd, the extra element is on the left
            int partitionY = (m + n + 1) / 2 - partitionX;

            // Step 3: Handle edge cases where the cut is at the very start or end
            // If cut is at 0, use MIN_VALUE; if at end, use MAX_VALUE
            double maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            double minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            double maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            double minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            // Step 4: Check if we found the correct partition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // Correct partition found!
                
                // If the total number of elements is even
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } 
                // If the total number of elements is odd
                else {
                    return Math.max(maxLeftX, maxLeftY);
                }
            } 
            // If maxLeftX is too big, move the partition in nums1 to the left
            else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } 
            // If maxLeftY is too big (meaning maxLeftX is too small), move partition in nums1 to the right
            else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}