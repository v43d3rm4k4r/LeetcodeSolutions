package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.validation.SolutionValidator.*
import leetcode.solutions.annotations.ProblemInputData
import leetcode.solutions.annotations.ProblemSolution

/**
 * __Problem:__ Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string `""`.
 *
 * __Constraints:__
 * - `1 <= strs.length <= 200`
 * - `0 <= strs[i].length <= 200`
 * - `strs[i]` consists of only lowercase English letters.

 * __Solution :__
 * Using two loops, the outer one will be responsible for the total character index, which is checked for strings.
 * The inner one will be for passing through the lines themselves. We compare characters until we reach the end of
 * the smallest string, or until we stumble upon a mismatch between the corresponding characters of the strings.
 *
 * __Time:__ O(N*M)
 *
 * __Space:__ O(1)
 *
 * @author Daniil Kupriyanov
 */

class Solution_14_Longest_Common_Prefix : LeetcodeSolution(EASY) {

    @ProblemSolution(timeComplexity = "O(N*M)", spaceComplexity = "O(1)")
    private fun longestCommonPrefix(strs: Array<String>): String {
        val output = StringBuilder()
        var currentCharIndex = 0
        var currentChar: Char
        var isLastChar = false
        var isWrongChar = false

        while (!isLastChar && !isWrongChar) {
            currentChar = if (strs[0].isNotEmpty()) strs[0][currentCharIndex] else break
            strs.forEach { str ->
                if (str.isEmpty()) {
                    isWrongChar = true
                    return@forEach
                }
                if (str[currentCharIndex] != currentChar) {
                    isWrongChar = true
                    return@forEach
                }
                currentChar = str[currentCharIndex]
                if (currentCharIndex == str.length - 1) isLastChar = true
            }
            if (!isWrongChar)
                output.append(currentChar)
            ++currentCharIndex
        }
        return output.toString()
    }

    @ProblemInputData
    override fun run() {
        ASSERT_EQ("fl",     longestCommonPrefix(arrayOf("flower", "flow", "flight")))
        ASSERT_EQ("",       longestCommonPrefix(arrayOf("dog", "racecar", "car")))
        ASSERT_EQ("flower", longestCommonPrefix(arrayOf("flower","flower","flower","flower")))
        ASSERT_EQ("c",      longestCommonPrefix(arrayOf("cir","car")))
        ASSERT_EQ("",       longestCommonPrefix(arrayOf("abab","aba","")))
    }
}