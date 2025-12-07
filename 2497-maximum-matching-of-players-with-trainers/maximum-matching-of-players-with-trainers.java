import java.util.Arrays;

class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        // 1. Sort both arrays to enable greedy matching
        Arrays.sort(players);
        Arrays.sort(trainers);
        
        int p_ptr = 0; // Pointer for players
        int t_ptr = 0; // Pointer for trainers
        int count = 0; // Number of valid matches
        
        // 2. Iterate while both arrays have elements left to check
        while (p_ptr < players.length && t_ptr < trainers.length) {
            
            // 3. Check if current trainer can handle current player
            if (players[p_ptr] <= trainers[t_ptr]) {
                // Match found!
                count++;
                p_ptr++; // Move to next player
                t_ptr++; // Move to next trainer
            } else {
                // Trainer is too weak. 
                // Move to next trainer, but keep the same player waiting.
                t_ptr++;
            }
        }
        
        return count;
    }
}