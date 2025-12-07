class Solution {
    public int romanToInt(String s) {
        int total = 0;
        int prevValue = 0;

        // Loop from the end of the string to the beginning
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = getValue(s.charAt(i));

            // If current value is smaller than the previous one we saw,
            // it's a subtraction case (like the I in IV).
            if (currentValue < prevValue) {
                total -= currentValue;
            } else {
                // Otherwise, just add it to the total.
                total += currentValue;
            }

            // Update prevValue for the next iteration
            prevValue = currentValue;
        }

        return total;
    }

    // Helper method to get the value of a Roman character
    private int getValue(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}