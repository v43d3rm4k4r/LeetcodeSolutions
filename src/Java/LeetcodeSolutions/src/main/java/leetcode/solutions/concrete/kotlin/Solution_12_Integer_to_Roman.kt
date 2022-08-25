package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.SolutionValidator.*
import leetcode.solutions.Complexity.*
import kotlin.math.log10

/**
 * __Problem:__ Given an integer, convert it to a roman numeral.
 *
 * __Constraints:__
 * - 1 <= _num_ <= 3999

 * __Solution 1:__
 * - Iterate over the provided integer, checking each number.
 * - Checking the following situations:
 *
 * 1) I can be placed before V (5) and X (10) to make 4 and 9.
 * 2) X can be placed before L (50) and C (100) to make 40 and 90.
 * 3) C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * __Complexity:__ O(N)
 *
 * @see Solution_13_Roman_to_Integer
 * @author Daniil Kuprianov
 */

class Solution_12_Integer_to_Roman : LeetcodeSolution(MEDIUM) {
    init { resolveConcreteSolutionInfo(this) }

    @ProblemSolution(complexity = O_N)
    private fun intToRoman(num: Int): String {
        var numCopy = num
        var digit = 0
        var result = ""
        for (i in 0..log10(num.toFloat()).toInt()) {
            digit = numCopy % 10
            when (digit) {
                in 0..3 -> {
                    result = when (i) {
                        0 -> "I".repeat(digit) + result
                        1 -> "X".repeat(digit) + result
                        2 -> "C".repeat(digit) + result
                        3 -> "M".repeat(digit) + result
                        else -> result
                    }
                }
                4 -> {
                    result = when (i) {
                        0 -> "IV" + result
                        1 -> "XL" + result
                        2 -> "CD" + result
                        else -> result
                    }
                }
                5 -> {
                    result = when (i) {
                        0 -> "V" + result
                        1 -> "L" + result
                        2 -> "D" + result
                        else -> result
                    }
                }
                in 6..8 -> {
                    result = when (i) {
                        0 -> "V" + "I".repeat(digit - 5) + result
                        1 -> "L" + "X".repeat(digit - 5) + result
                        2 -> "D" + "C".repeat(digit - 5) + result
                        else -> result
                    }
                }
                9 -> {
                    result = when (i) {
                        0 -> "IX" + result
                        1 -> "XC" + result
                        2 -> "CM" + result
                        else -> result
                    }
                }
            }
            numCopy /= 10
        }
        return result
    }

    @ProblemInputData
    override fun run() {
        ASSERT_STREQ("III",     intToRoman(3))
        ASSERT_STREQ("LVIII",   intToRoman(58))
        ASSERT_STREQ("MCMXCIV", intToRoman(1994))
    }
}