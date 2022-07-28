package leetcode.solutions.concrete;

import leetcode.solutions.*;

import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import static leetcode.solutions.Complexity.*;

/**
 * @Problem: Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
 * parentheses substring.
 * @Constraints:
 * <ul>
 *     <li>0 <= s.length <= 3 * 10^4
 *     <li>s[i] is '(', or ')'
 * </ul>
 * @Solution:
 * Iterating the array, counting the number of parentheses. If number of left and right ones matches, then we select the maximum value
 * from the current sum of the passed parentheses and the currently maximum long valid part. If there are more right parentheses,
 * then we reset the counters. This algorithm is valid only if it used on both sides, so we repeat the same from the other end of the string.
 * The solution has O(1) space complexity.
 * @Complexity: O(N)
 * @Author: Daniil Kuprianov
 */

public final class Solution_32_Longest_Valid_Parentheses extends LeetcodeSolution {

    public Solution_32_Longest_Valid_Parentheses() { super(32, "Longest Valid Parentheses", HARD); }

    @ProblemSolution(complexity = O_N)
    public int longestValidParentheses2(String s) {
        if (s.length() < 2) return 0;
        int leftsCounter = 0, rightsCounter = 0, result = 0;
        for (var i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++leftsCounter;
            } else if (s.charAt(i) == ')') {
                ++rightsCounter;
            }
            if (rightsCounter == leftsCounter) {
                result = Math.max(result, leftsCounter * 2);
            } else if (rightsCounter > leftsCounter) {
                leftsCounter = rightsCounter = 0;
            }
        }
        leftsCounter = rightsCounter = 0;
        for (var i = s.length()-1; i >= 0; --i) {
            if (s.charAt(i) == '(') {
                ++leftsCounter;
            } else if (s.charAt(i) == ')') {
                ++rightsCounter;
            }
            if (rightsCounter == leftsCounter) {
                result = Math.max(result, leftsCounter * 2);
            } else if (leftsCounter > rightsCounter) {
                leftsCounter = rightsCounter = 0;
            }
        }
        return result;
    }

    @Override
    @ProblemInputData
    public void run() {
        ASSERT_EQ(2, longestValidParentheses2("(()"));
        ASSERT_EQ(4, longestValidParentheses2(")()())"));
        ASSERT_EQ(6, longestValidParentheses2("()(())"));
        ASSERT_EQ(6, longestValidParentheses2(")((()))))"));
        ASSERT_EQ(0, longestValidParentheses2(""));
    }
}
