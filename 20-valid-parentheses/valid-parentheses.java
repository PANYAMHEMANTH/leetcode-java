import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        // Create a stack to keep track of opening brackets
        Stack<Character> stack = new Stack<>();
        
        // Loop through every character in the string
        for (char c : s.toCharArray()) {
            // 1. If it's an opening bracket, push it onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // 2. If it's a closing bracket, we need to check the stack
            else {
                // If stack is empty, we have a closing bracket without an opening one -> Invalid
                if (stack.isEmpty()) {
                    return false;
                }
                
                // Get the most recent opening bracket
                char top = stack.pop();
                
                // Check if the current closing bracket matches the top opening bracket
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        
        // 3. If the stack is empty, all brackets were closed correctly.
        // If not empty, we have leftover opening brackets -> Invalid.
        return stack.isEmpty();
    }
}