package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.SolutionValidator.*
import leetcode.solutions.Complexity.*

/**
 * __Problem:__ Given a string containing just the characters '(' and ')', find the length of the longest valid
 * (well-formed) parentheses substring.
 *
 * __Constraints:__
 * - 0 <= s.length <= 3 * 10^4
 * - s at _i_ is '(', or ')'
 *
 * __Solution:__ Iterating the array, counting the number of parentheses. If number of left and right ones matches, then
 * we select the maximum value from the current sum of the passed parentheses and the currently maximum long valid part.
 * If there are more right parentheses, then we reset the counters. This algorithm is valid only if it used on both
 * sides, so we repeat the same from the other end of the string. The solution has O(1) space complexity.
 *
 * __Complexity:__ O(N)
 * @see    Solution_20_Valid_Parentheses
 * @author Daniil Kuprianov
 */

class Solution_32_Longest_Valid_Parentheses : LeetcodeSolution(HARD) {
    init { resolveConcreteSolutionInfo(this) }

   @ProblemSolution(complexity = O_N)
    private fun longestValidParentheses(s: String): Int {
        if (s.length < 2) return 0
        var leftsCounter = 0; var rightsCounter = 0; var result = 0
        for (i in s.indices) {
            if (s[i] == '(') {
                ++leftsCounter;
            } else if (s[i] == ')') {
                ++rightsCounter
            }
            if (rightsCounter == leftsCounter) {
                result = result.coerceAtLeast(minimumValue = leftsCounter * 2);
            } else if (rightsCounter > leftsCounter) {
                leftsCounter = 0; rightsCounter = 0
            }
        }
        leftsCounter = 0; rightsCounter = 0
        for (i in s.indices.reversed()) {
            if (s[i] == '(') {
                ++leftsCounter
            } else if (s[i] == ')') {
                ++rightsCounter
            }
            if (rightsCounter == leftsCounter) {
                result = result.coerceAtLeast(leftsCounter * 2)
            } else if (leftsCounter > rightsCounter) {
                leftsCounter = 0; rightsCounter = 0
            }
        }
        return result;
    }

    @ProblemInputData
    override fun run() {
        ASSERT_EQ(2, longestValidParentheses("(()"))
        ASSERT_EQ(4, longestValidParentheses(")()())"))
        ASSERT_EQ(6, longestValidParentheses("()(())"))
        ASSERT_EQ(6, longestValidParentheses(")((()))))"))
        ASSERT_EQ(0, longestValidParentheses(""))
    }
}
