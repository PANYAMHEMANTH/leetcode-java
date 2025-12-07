class Solution:
    def countHillValley(self, nums: List[int]) -> int:
        # Step 1: Compress the array by removing adjacent duplicates
        # Example: [2, 4, 1, 1, 6, 5] becomes [2, 4, 1, 6, 5]
        distinct_nums = [nums[0]]
        for i in range(1, len(nums)):
            if nums[i] != nums[i-1]:
                distinct_nums.append(nums[i])
        
        count = 0
        
        # Step 2: Check for hills and valleys in the compressed array
        # We iterate from index 1 to len-2 because the first and last elements 
        # cannot be hills or valleys (they lack a neighbor on one side).
        for i in range(1, len(distinct_nums) - 1):
            prev_val = distinct_nums[i-1]
            curr_val = distinct_nums[i]
            next_val = distinct_nums[i+1]
            
            # Check if it is a Hill (higher than both neighbors)
            # OR a Valley (lower than both neighbors)
            if (prev_val < curr_val > next_val) or (prev_val > curr_val < next_val):
                count += 1
                
        return count