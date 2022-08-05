package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*;
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.SolutionValidator.*
import leetcode.solutions.Complexity.*

// TODO: fix comments
/**
 * @Problem: Given a sorted array of distinct integers and a target value, return the index if the target is found. If
 * not, return the index where it would be if it were inserted in order. You must write an algorithm with O(log n)
 * runtime complexity.
 * @Constraints:
 * <ul>
 *     <li>1 <= nums.length <= 10^4
       <li>-10^4 <= nums[i] <= 10^4
       <li>nums contains distinct values sorted in ascending order.
       <li>-10^4 <= target <= 10^4
 * </ul>
 * @Solution: Need to user classic binary search. In the absence of the desired value, we return left, which corresponds
 * to the index for inserting the desired value.
 * @Complexity: O(log(N))
 * @Author: Daniil Kuprianov
 */

class Solution_135_Search_Insert_Position : LeetcodeSolution(EASY) {
    init { resolveConcreteSolutionInfo(this) }

    @ProblemSolution(complexity = O_logN)
    private fun searchInsert(nums: IntArray, target: Int): Int {
        var left  = 0
        var mid   = -1
        var right = nums.size - 1
        while (left <= right) {
            mid = (right + left) / 2
            if (nums[mid] == target) {
                return mid
            }
            if (nums[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return left
    }

    @ProblemInputData
    override fun run() {
        ASSERT_EQ(2, searchInsert(nums = intArrayOf(1,3,5,6), target = 5))
        ASSERT_EQ(1, searchInsert(nums = intArrayOf(1,3,5,6), target = 2))
        ASSERT_EQ(4, searchInsert(nums = intArrayOf(1,3,5,6), target = 7))
        ASSERT_EQ(0, searchInsert(nums = intArrayOf(1,3,5,6), target = 0))
    }
}