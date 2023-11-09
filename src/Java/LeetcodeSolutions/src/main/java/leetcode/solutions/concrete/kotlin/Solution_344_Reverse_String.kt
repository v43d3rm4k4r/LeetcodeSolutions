package leetcode.solutions.concrete.kotlin

import leetcode.solutions.LeetcodeSolution
import leetcode.solutions.ProblemDifficulty.EASY
import leetcode.solutions.annotations.ProblemInputData
import leetcode.solutions.annotations.ProblemSolution
import leetcode.solutions.validation.SolutionValidator.ASSERT_EQ

/**
 * __Problem:__ Write a function that reverses a string. The input string is given as an array of characters `s`.
 * You must do this by modifying the input array in-place with `O(1)` extra memory.
 *
 * __Constraints:__
 * - 1 <= `s.length` <= 10^5
 * - s[i] is a printable ascii character
 *
 * __Solution:__ Going through the string with two iterators and swap characters to the string middle.
 *
 * __Time:__ O(N)
 *
 * __Space:__ O(1)
 *
 * @author Daniil Kupriyanov
 */

class Solution_344_Reverse_String : LeetcodeSolution(EASY) {

    @ProblemSolution(timeComplexity = "O(N)", spaceComplexity = "O(1)")
    private fun reverseString(s: CharArray) {
        var i = 0
        var j = s.size - 1

        while (i < s.size / 2) {
            val temp = s[i]
            s[i] = s[j]
            s[j] = temp
            ++i; --j
        }
    }

    @ProblemInputData
    override fun run() {
        val testString = charArrayOf('o','l','l','e','h').apply { reverseString(this) }
        val result = testString.contentEquals(charArrayOf('h','e','l','l', 'o'))
        ASSERT_EQ(true, result)
    }
}