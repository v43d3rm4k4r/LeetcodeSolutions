package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*;
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.validation.SolutionValidator.*
import leetcode.solutions.complexity.Complexity.*
import leetcode.solutions.annotations.ProblemInputData

/**
 * @Problem: You are given an integer array height of length n. There are n vertical lines drawn such that the two
 * endpoints of the ith line are (i, 0) and (i, height[i]). Find two lines that together with the x-axis form a
 * container, such that the container contains the most water. Return the maximum amount of water a container can store.
 * Notice that you may not slant the container.
 * @Constraints:
 * <ul>
 *     <li>n == height.length
       <li>2 <= n <= 10^5
       <li>0 <= height[i] <= 10^4
 * </ul>
 * @Solution:
 * @Complexity:
 * @Author: Daniil Kupriyanov
 */

class Solution_11_Container_With_Most_Water : LeetcodeSolution(MEDIUM) {

    //@ProblemSolution(timeComplexity = O_??)
    private fun maxArea(height: IntArray): Int {
        var maxWaterArea = 0
        var i = 0;
        var j = height.size - 1
        while (i <= height.size / 2) { // because of terrible for loop in Kotlin using while
            // TODO: ПО ИДЕЕ, УЖЕ НА ВТОРОЙ ИТЕРАЦИИ ДОЛЖНЫ ПОЛУЧИТЬ 49...
            maxWaterArea = Math.max(maxWaterArea, Math.min(height[i], height[j]) * (j - i))
            //maxWaterArea = maxWaterArea.coerceAtLeast(height[i].coerceAtMost(height[j]) * (j - i))
            ++i; --j
        }
        return maxWaterArea
    }

    @ProblemInputData
    override fun run() {
        //ASSERT_EQ(1,  maxArea(intArrayOf(1,1)))
        //ASSERT_EQ(2,  maxArea(intArrayOf(1,3,1)))
        //ASSERT_EQ(8,  maxArea(intArrayOf(4,5,6)))
        ASSERT_EQ(49, maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)))
    }
}