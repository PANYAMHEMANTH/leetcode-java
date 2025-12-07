class Solution {
    public boolean isValid(String word) {
        // Condition 1: Minimum of 3 characters
        if (word.length() < 3) {
            return false;
        }

        boolean hasVowel = false;
        boolean hasConsonant = false;

        for (char c : word.toCharArray()) {
            // Condition 2: Must be only digits or English letters
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }

            // Check for vowels
            if (isVowel(c)) {
                hasVowel = true;
            } 
            // If it is a letter but NOT a vowel, it is a consonant
            else if (Character.isLetter(c)) {
                hasConsonant = true;
            }
        }

        // Conditions 3 & 4: Must have at least one vowel AND one consonant
        return hasVowel && hasConsonant;
    }

    // Helper method to check if a character is a vowel
    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}