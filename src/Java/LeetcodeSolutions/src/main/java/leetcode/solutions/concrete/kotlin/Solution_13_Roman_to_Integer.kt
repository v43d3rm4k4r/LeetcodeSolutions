package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.SolutionValidator.*
import leetcode.solutions.Complexity.*

/**
 * __Problem:__ Given a roman numeral, convert it to an integer.
 *
 * __Constraints:__
 * - 1 <= num <= 3999

 * __Solution 1:__
 * - Iterate over the provided string, checking each character.
 * - Increment the result variable by the appropriate amount, but with a check for the following situations:
 *
 * 1) I can be placed before V (5) and X (10) to make 4 and 9.
 * 2) X can be placed before L (50) and C (100) to make 40 and 90.
 * 3) C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * __Complexity:__ O(N)
 * @see Solution_12_Integer_to_Roman
 * @author Daniil Kuprianov
 */

class Solution_13_Roman_to_Integer : LeetcodeSolution(EASY) {
    init { resolveConcreteSolutionInfo(this) }

    @ProblemSolution(complexity = O_N)
    private fun romanToInt(s: String): Int {
        var result = 0
        var charBefore = '0'
        for (i in s.indices) {
            if (i != 0) charBefore = s[i - 1]
            when (s[i]) {
                'I' -> {
                    ++result
                }
                'V' -> {
                    result += if (charBefore == 'I') 3 else 5
                }
                'X' -> {
                    result += if (charBefore == 'I') 8 else 10
                }
                'L' -> {
                    result += if (charBefore == 'X') 30 else 50
                }
                'C' -> {
                    result += if (charBefore == 'X') 80 else 100
                }
                'D' -> {
                    result += if (charBefore == 'C') 300 else 500
                }
                'M' -> {
                    result += if (charBefore == 'C') 800 else 1000
                }
            }
        }
        return result
    }

    @ProblemInputData
    override fun run() {
        ASSERT_EQ(3,    romanToInt("III"))
        ASSERT_EQ(58,   romanToInt("LVIII"))
        ASSERT_EQ(1994, romanToInt("MCMXCIV"))
    }
}
