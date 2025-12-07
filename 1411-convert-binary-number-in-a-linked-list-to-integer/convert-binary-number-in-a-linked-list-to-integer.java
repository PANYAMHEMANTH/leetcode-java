/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int getDecimalValue(ListNode head) {
        int result = 0;
        
        while (head != null) {
            // Option 1: Arithmetic approach (easier to read)
            // Multiply previous result by 2 to shift position, then add current bit
            result = (result * 2) + head.val;
            
            /* Option 2: Bitwise approach (suggested in Hint 2)
             This does the exact same thing but using bit manipulation:
             result = (result << 1) | head.val; 
            */
            
            // Move to the next node
            head = head.next;
        }
        
        return result;
    }
}