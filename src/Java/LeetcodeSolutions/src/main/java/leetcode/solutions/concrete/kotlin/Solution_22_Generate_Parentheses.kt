package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.validation.SolutionValidator.*
import leetcode.solutions.annotations.ProblemInputData
import leetcode.solutions.annotations.ProblemSolution
import leetcode.solutions.annotations.ProblemSolutionCompanionMethod

/**
 * __Problem:__ Given `n` pairs of parentheses, write a function to generate all combinations of well-formed
 * parentheses.
 *
 * __Constraints:__
 * - 1 <= n <= 8
 *
 * __Solution:__ The main idea is to add `)` only after valid `(`. We use two integer variables `left` & `right` to see
 * how many `(` & `)` are in the current string. If
 *
 * __Time:__ O(K^N)
 *
 * __Space:__ O(K^N)
 *
 * @author Daniil Kupriyanov
 */

class Solution_22_Generate_Parentheses : LeetcodeSolution(MEDIUM) {

    @ProblemSolution(timeComplexity = "O(K^N)", spaceComplexity = "O(K^N)")
    private fun generateParenthesis(n: Int): List<String> {
        val parentheses = mutableListOf<String>()
        doGenerate(parentheses, "", 0, 0, n)
        return parentheses
    }

    @ProblemSolutionCompanionMethod
    private fun doGenerate(result: MutableList<String>, current: String, left: Int, right: Int, n: Int) {
        if (current.length == n * 2) {
            result += current
            return
        }

        if (left < n)
            doGenerate(result, "$current(", left + 1, right, n)

        if (right < left)
            doGenerate(result, "$current)", left, right + 1, n)
    }

    @ProblemInputData
    override fun run() {
        ASSERT_EQ(listOf("()"), generateParenthesis(1))
        ASSERT_EQ(listOf("(())", "()()"), generateParenthesis(2))
        ASSERT_EQ(listOf("((()))", "(()())", "(())()", "()(())", "()()()"), generateParenthesis(3))
    }
}