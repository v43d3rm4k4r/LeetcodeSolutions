package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.SolutionValidator.*
import leetcode.solutions.Complexity.*
import leetcode.solutions.annotations.ProblemInputData
import leetcode.solutions.annotations.ProblemSolution
import leetcode.solutions.annotations.ProblemSolutionData
import java.util.LinkedList

/**
 * __Problem:__ Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input
string is valid. An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
 *
 * __Constraints:__
 * - 1 <= s.length <= 10^4
 * - s consists of parentheses only '()[]{}'
 *
 * __Solution:__ A linked list is used to record characters that have already been passed. If the bracket opens, it is
 * written to the stack. If it is closing, its type is compared with the type of the last bracket on the stack. The size
 * of the stack should be zero by the end of the method.
 *
 * __Complexity:__ O(N)
 * @author Daniil Kupriyanov
 */

class Solution_20_Valid_Parentheses : LeetcodeSolution(EASY) {
    init { resolveConcreteSolutionInfo(this) }

    @ProblemSolutionData
    private enum class BracketType {
        PARENTHESES,
        SQUARE_BRACKETS,
        CURLY_BRACKETS,
    }

    @ProblemSolution(complexity = O_N)
    private fun isValid(s: String): Boolean {
        if (s.length < 2) return false
        val bracketsStack = LinkedList<BracketType>() // no need in slow java.util.Stack
        for (i in s.indices) {
            when (s[i]) {
                '(' -> bracketsStack.add(BracketType.PARENTHESES)
                ')' -> {
                    if (bracketsStack.isEmpty() || bracketsStack.last != BracketType.PARENTHESES) return false
                    bracketsStack.removeLast()
                }
                '[' -> bracketsStack.add(BracketType.SQUARE_BRACKETS)
                ']' -> {
                    if (bracketsStack.isEmpty() || bracketsStack.last != BracketType.SQUARE_BRACKETS) return false
                    bracketsStack.removeLast()
                }
                '{' -> bracketsStack.add(BracketType.CURLY_BRACKETS)
                '}' -> {
                    if (bracketsStack.isEmpty() || bracketsStack.last != BracketType.CURLY_BRACKETS) return false
                    bracketsStack.removeLast()
                }
            }
        }
        return bracketsStack.size == 0
    }

    @ProblemInputData
    override fun run() {
        ASSERT_TRUE(isValid( "()"))
        ASSERT_TRUE(isValid( "()[]{}"))
        ASSERT_FALSE(isValid("(]"))
        ASSERT_FALSE(isValid("}("))
    }
}