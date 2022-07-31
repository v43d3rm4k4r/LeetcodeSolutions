package leetcode.solutions.concrete

import leetcode.solutions.*;

import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.SolutionValidator.*
import leetcode.solutions.Complexity.*

class Solution_135_Search_Insert_Position : LeetcodeSolution(EASY) {
    init { resolveConcreteSolutionInfo(this) }

    @ProblemSolution(complexity = O_logN)
    private fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0; var mid = 0; var right = nums.size
        while (left <= right) {
            mid = (right + left) / 2
            if (nums[mid] == target) {
                return mid
            }
            if (nums[mid] < target) {
                left = mid + 1
            } else if (nums[mid] > target) {
                right = mid - 1
            }
        }
        return -1
    }

    @ProblemInputData
    override fun run() {
        ASSERT_EQ(2, searchInsert(nums = intArrayOf(1,3,5,6), target = 5))
        //ASSERT_EQ(1, searchInsert(nums = intArrayOf(1,3,5,6), target = 2))
        //ASSERT_EQ(4, searchInsert(nums = intArrayOf(1,3,5,6), target = 7))
    }
}