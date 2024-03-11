package com.learning.assignment.parentheses;

import java.util.Stack;

public class ParenthesesChecker {

    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                 } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) {
                    return false; // Unmatched closing parenthesis
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false; // Mismatched opening and closing parenthesis
                }
            }
        }

        return stack.isEmpty(); // If stack is empty, all parentheses are properly matched
    }

    public static void main(String[] args) {
        String testString1 = "(){}[]";
        String testString2 = "((())";

        System.out.println("Test String 1 is valid: " + isValidParentheses(testString1));
        System.out.println("Test String 4 is valid: " + isValidParentheses(testString2));

    }
}