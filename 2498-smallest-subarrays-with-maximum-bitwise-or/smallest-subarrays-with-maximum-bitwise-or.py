from typing import List

class Solution:
    def smallestSubarrays(self, nums: List[int]) -> List[int]:
        n = len(nums)
        # Array to store the result
        answer = [0] * n
        # Array to keep track of the nearest index where the k-th bit is set to 1
        # Initialized to -1 implies the bit hasn't been seen yet.
        last_seen = [-1] * 32  
        
        # Iterate backwards from the last element to the first
        for i in range(n - 1, -1, -1):
            # 1. Update last_seen for the current number 'nums[i]'
            # We check bits 0 to 30 (since nums[i] <= 10^9 < 2^30)
            for bit in range(31):
                if (nums[i] >> bit) & 1:
                    last_seen[bit] = i
            
            # 2. Find the furthest index we need to reach
            # We must reach the furthest 'last_seen' index to collect all 
            # available bits for the maximum OR.
            max_idx = i # The subarray must at least include itself
            for bit in range(31):
                if last_seen[bit] != -1:
                    max_idx = max(max_idx, last_seen[bit])
            
            # 3. Calculate length
            answer[i] = max_idx - i + 1
            
        return answer