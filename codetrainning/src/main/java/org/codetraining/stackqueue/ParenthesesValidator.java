package org.codetraining.stackqueue;

public class ParenthesesValidator {

    public boolean isValid(String s) {
        if (s==null) {
            return false;
        }

        Stack<Character> stack = new ArrayStack<>();
            for (char c : s.toCharArray()) {
                if (c == '(' || c == '{' || c == '[') {
                    stack.push(c);
                } else if (c == ')' || c == '}' || c == ']') {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char open = stack.pop();
                    if (!matches(open, c)) {
                        return false;
                    }
                }
            }
        return stack.isEmpty();
    }
    private boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '{' && close == '}')
                || (open == '[' && close == ']');
    }
}
