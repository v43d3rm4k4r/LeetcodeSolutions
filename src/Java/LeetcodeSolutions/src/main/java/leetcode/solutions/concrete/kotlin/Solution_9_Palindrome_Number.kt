package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.validation.SolutionValidator.*
import leetcode.solutions.complexity.Complexity.*
import leetcode.solutions.annotations.ProblemInputData
import leetcode.solutions.annotations.ProblemSolution

/**
 * __Problem:__ Given an integer x, return true if x is palindrome integer.
 * An integer is a palindrome when it reads the same backward as forward.
 *
 * __Constraints:__
 * - -2^31 <= x <= 2^31 - 1

 * __Solution 1:__
 * - Convert a number to a string for iteration.
 * - Iterate through the array to the middle using counters for the beginning and end, comparing the values.
 *
 * __Time:__ O(N)
 *
 * __Space:__ O(1)
 *
 * __Solution 2:__
 * - Reverse half of x while dividing it to become the upper half.
 * - In the end check if reversed lower half is equal to the above half, or (in case if it's an odd length palindrome)
 * we check that equality with reversed rhs divided by 10 to skip that middle part.
 *
 * __Time:__ O(log(N))
 *
 * __Space:__ O(1)
 * @author Daniil Kupriyanov
 */

class Solution_9_Palindrome_Number : LeetcodeSolution(EASY) {

    @ProblemSolution(timeComplexity = O_N, spaceComplexity = O_1)
    private fun isPalindrome1(x: Int): Boolean {
        if (x < 0 || (x > 0 && (x % 10 == 0))) return false
        val asString = x.toString()
        val digitsCount = asString.length
        var i = 0; var j = asString.length - 1
        while (i < digitsCount / 2) {
            if (asString[i] != asString[j]) {
                return false
            }
            ++i; --j
        }
        return true
    }

    @ProblemSolution(timeComplexity = O_logN, spaceComplexity = O_1)
    private fun isPalindrome2(x: Int): Boolean {
        if (x < 0 || (x > 0 && (x % 10 == 0))) return false
        var xCpy = x; var rhs = 0
        while (xCpy > rhs) {
            rhs *= 10
            rhs += xCpy % 10
            xCpy /= 10 // mutating x itself is not allowed in this magnificent language
        }
        return rhs == xCpy || rhs / 10 == xCpy
    }

    @ProblemInputData
    override fun run() {
        ASSERT_TRUE(isPalindrome1(321123))
        ASSERT_TRUE(isPalindrome1(543212345))
        ASSERT_FALSE(isPalindrome1(12345))

        ASSERT_TRUE(isPalindrome2(12533521))
        ASSERT_FALSE(isPalindrome2(543211234))
        ASSERT_FALSE(isPalindrome2(12345))
    }
}