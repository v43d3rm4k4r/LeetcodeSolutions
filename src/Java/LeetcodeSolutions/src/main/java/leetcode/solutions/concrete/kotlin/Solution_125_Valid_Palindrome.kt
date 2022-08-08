package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.SolutionValidator.*
import leetcode.solutions.Complexity.*

/**
 * __Problem:__ A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing
 * all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and
 * numbers. Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * __Constraints:__
 * - 1 <= s.length <= 2 * 10^5
 * - s consists only of printable ASCII characters.
 *
 * __Solution 1:__ Mutate string checking weather current character is letter or digit. Then using two-sided comparison
 * for each character.
 *
 * __Complexity:__ O(N)
 *
 * __Solution 2:__ This solution is more efficient in terms of speed and memory. An intermediate string is not created
 * here, but the passage through the given string occurs immediately. At the same time, the counters i and j are
 * synchronized - as long as one of them has an invalid symbol, the other counter does not move.
 *
 * __Complexity:__ O(N)
 * @see    Solution_9_Palindrome_Number
 * @author Daniil Kuprianov
 */

class Solution_125_Valid_Palindrome : LeetcodeSolution(EASY) {
    init { resolveConcreteSolutionInfo(this) }

    @ProblemSolution(complexity = O_N)
    private fun isPalindrome1(s: String): Boolean {
        val filteredStr = StringBuilder()
        for (i in s.indices) {
            if (Character.isLetterOrDigit(s[i])) {
                filteredStr.append(Character.toLowerCase(s[i]))
            }
        }
        var i = 0
        var j = filteredStr.length - 1
        while (i < filteredStr.length) {
            if (filteredStr[i] != filteredStr[j]) {
                return false
            }
            ++i; --j
        }
        return true
    }

    @ProblemSolution(complexity = O_N)
    private fun isPalindrome2(s: String): Boolean {
        var i = 0
        var j = s.length - 1
        while (i != j + 1 && i != j) {
            if (Character.isLetterOrDigit(s[i]) && Character.isLetterOrDigit(s[j])) {
                if (Character.toLowerCase(s[i]) != Character.toLowerCase(s[j])) {
                    return false
                }
                ++i; --j
            } else if (!Character.isLetterOrDigit(s[i])) ++i else --j
        }
        return true
    }

    @ProblemInputData
    override fun run() {
        ASSERT_TRUE(isPalindrome1("A man, a plan, a canal: Panama"))
        ASSERT_FALSE(isPalindrome1("race a car"))
        ASSERT_TRUE(isPalindrome1(" "))
        ASSERT_FALSE(isPalindrome1(",,,,,,,,,,,,some"))

        ASSERT_TRUE(isPalindrome2("A man, a plan, a canal: Panama"))
        ASSERT_FALSE(isPalindrome2("race a car"))
        ASSERT_TRUE(isPalindrome2(" "))
        ASSERT_FALSE(isPalindrome2(",,,,,,,,,,,,some"))
    }
}
