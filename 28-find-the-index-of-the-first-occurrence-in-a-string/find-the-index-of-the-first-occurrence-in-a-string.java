class Solution {
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();
        
        // If needle is longer than haystack, it can't exist inside it
        if (nLen > hLen) {
            return -1;
        }

        // Iterate through haystack
        // We stop at hLen - nLen because there aren't enough characters left after that point
        for (int i = 0; i <= hLen - nLen; i++) {
            
            // Check if the substring matches the needle
            if (haystack.substring(i, i + nLen).equals(needle)) {
                return i;
            }
        }

        return -1;
    }
}