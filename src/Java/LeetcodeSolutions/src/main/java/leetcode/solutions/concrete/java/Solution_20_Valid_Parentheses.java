package leetcode.solutions.concrete.java;

import leetcode.solutions.*;

import java.util.LinkedList;

import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import static leetcode.solutions.Complexity.*;

/**
 * @Problem: Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
An input string is valid if:
* <ul>
*     <li>Open brackets must be closed by the same type of brackets.
*     <li>Open brackets must be closed in the correct order.
 * </ul>
 * @Constraints:
 * <ul>
 *     <li>1 <= s.length <= 10^4
 *     <li>s consists of parentheses only '()[]{}'
 * </ul>
 * @Solution:
 * A linked list is used to record characters that have already been passed. If the bracket opens, it is written to the
 * stack. If it is closing, its type is compared with the type of the last bracket on the stack. The size of the stack
 * should be zero by the end of the method.
 * @Complexity: O(N)
 * @Author: Daniil Kuprianov
 */


public final class Solution_20_Valid_Parentheses extends LeetcodeSolution {

    public Solution_20_Valid_Parentheses() { super(EASY); resolveConcreteSolutionInfo(this); }

    @ProblemSolutionData
    private enum BracketType {
        PARENTHESES,
        SQUARE_BRACKETS,
        CURLY_BRACKETS
    }

    @ProblemSolution(complexity = O_N)
    private boolean isValid(String s) {
        if (s.length() < 2) return false;
        var bracketsStack = new LinkedList<BracketType>(); // no need in slow java.util.Stack
        for (var i = 0; i < s.length(); ++i) {
            switch (s.charAt(i)) {
                case '(' -> bracketsStack.add(BracketType.PARENTHESES);
                case ')' -> {
                    if (bracketsStack.isEmpty() || bracketsStack.getLast() != BracketType.PARENTHESES) return false;
                    bracketsStack.removeLast();
                }
                case '[' -> bracketsStack.add(BracketType.SQUARE_BRACKETS);
                case ']' -> {
                    if (bracketsStack.isEmpty() || bracketsStack.getLast() != BracketType.SQUARE_BRACKETS) return false;
                    bracketsStack.removeLast();
                }
                case '{' -> bracketsStack.add(BracketType.CURLY_BRACKETS);
                case '}' -> {
                    if (bracketsStack.isEmpty() || bracketsStack.getLast() != BracketType.CURLY_BRACKETS) return false;
                    bracketsStack.removeLast();
                }
            }
        }
        return bracketsStack.size() == 0;
    }

    @Override
    @ProblemInputData
    public void run() {
        ASSERT_TRUE(isValid("()"));
        ASSERT_TRUE(isValid("()[]{}"));
        ASSERT_FALSE(isValid("(]"));
        ASSERT_FALSE(isValid("}("));
    }
}
