class Solution {
    public int maximumGain(String s, int x, int y) {
        // Step 1: Determine priority
        // If x > y, "ab" is high priority. If y > x, "ba" is high priority.
        String highPriorityPair = x > y ? "ab" : "ba";
        String lowPriorityPair = highPriorityPair.equals("ab") ? "ba" : "ab";
        
        // Identify points associated with high/low pairs
        int highPriorityScore = Math.max(x, y);
        int lowPriorityScore = Math.min(x, y);
        
        // Step 2: First Pass - Remove high priority substrings
        String stringAfterFirstPass = removeSubstring(s, highPriorityPair);
        int removedCount1 = (s.length() - stringAfterFirstPass.length()) / 2;
        int score = removedCount1 * highPriorityScore;
        
        // Step 3: Second Pass - Remove low priority substrings from the REMAINDER
        String stringAfterSecondPass = removeSubstring(stringAfterFirstPass, lowPriorityPair);
        int removedCount2 = (stringAfterFirstPass.length() - stringAfterSecondPass.length()) / 2;
        score += removedCount2 * lowPriorityScore;
        
        return score;
    }

    // Helper method to simulate Stack using StringBuilder
    private String removeSubstring(String input, String targetPair) {
        StringBuilder stack = new StringBuilder();
        char firstChar = targetPair.charAt(0);
        char secondChar = targetPair.charAt(1);

        for (char c : input.toCharArray()) {
            // If stack is not empty, and current char matches the closing char of our pair
            // AND the top of the stack matches the opening char...
            if (stack.length() > 0 && c == secondChar && stack.charAt(stack.length() - 1) == firstChar) {
                // "Pop" the top character (remove the pair)
                stack.deleteCharAt(stack.length() - 1);
            } else {
                // "Push" current character
                stack.append(c);
            }
        }
        return stack.toString();
    }
}