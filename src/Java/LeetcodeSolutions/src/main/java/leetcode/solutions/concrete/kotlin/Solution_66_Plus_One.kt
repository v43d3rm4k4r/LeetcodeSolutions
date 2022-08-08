package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.SolutionValidator.*
import leetcode.solutions.Complexity.*
import kotlin.math.log10

/**
 * __Problem:__ You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit
 * of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large
 * integer does not contain any leading 0's. Increment the large integer by one and return the resulting array of
 * digits.
 *
 * __Constraints:__
 * - 1 <= digits.length <= 100
   - 0 <= digits at _i_ <= 9
   - digits does not contain any leading 0's.

 * __Solution 1:__ This solution is based on converting an array to a number and vice versa. It does not look too elegant
 * and is not the most effective.
 *
 * __Complexity:__ O(N)
 *
 * __Solution 2:__ Iteratively go through the array from lower to higher digits. If the current number is less than 9,
 * just increment it and return an array. If the number is 9, then we reset the current digit and move on to the next
 * iteration. If the array was not returned when exiting the loop, this means that all values are zeros, and you will
 * have to add one to the beginning.
 *
 * __Complexity:__ O(N)
 * @author Daniil Kuprianov
 */

class Solution_66_Plus_One : LeetcodeSolution(EASY) {
    init { resolveConcreteSolutionInfo(this) }

    @ProblemSolution(complexity = O_N)
    private fun plusOne1(digits: IntArray): IntArray {
        var asInteger = 0
        var digit = 1
        for (i in digits.indices.reversed()) {
            asInteger += digits[i] * digit
            digit *= 10
        }
        ++asInteger
        val result = mutableListOf<Int>()
        for (i in log10(asInteger.toFloat()).toInt() downTo 0) {
            digit = asInteger % 10
            result.add(0, digit)
            asInteger /= 10
        }
        return result.toIntArray()
    }

    @ProblemSolution(complexity = O_N)
    private fun plusOne2(digits: IntArray): IntArray {
        for (i in digits.indices.reversed()) {
            if (digits[i] == 9) {
                digits[i] = 0
                continue;
            }
            ++digits[i]
            return digits
        }
        val tempList = digits.toMutableList() // don`t now why leetcode solutions templates takes static arrays ...
        tempList.add(0, 1)
        return tempList.toIntArray()
    }

    @ProblemInputData
    override fun run() {
        ASSERT_EQ(intArrayOf(1,2,4),     plusOne1(intArrayOf(1,2,3)))
        ASSERT_EQ(intArrayOf(4,3,2,2),   plusOne1(intArrayOf(4,3,2,1)))
        ASSERT_EQ(intArrayOf(1,0,0,0,0), plusOne1(intArrayOf(9,9,9,9)))

        ASSERT_EQ(intArrayOf(1,2,4),     plusOne2(intArrayOf(1,2,3)))
        ASSERT_EQ(intArrayOf(4,3,2,2),   plusOne2(intArrayOf(4,3,2,1)))
        ASSERT_EQ(intArrayOf(1,0,0,0,0), plusOne2(intArrayOf(9,9,9,9)))
    }
}