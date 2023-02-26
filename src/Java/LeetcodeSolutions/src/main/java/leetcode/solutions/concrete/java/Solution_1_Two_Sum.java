package leetcode.solutions.concrete.java;

import leetcode.solutions.*;
import leetcode.solutions.annotations.ProblemInputData;
import leetcode.solutions.annotations.ProblemSolution;
import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.validation.SolutionValidator.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Problem: Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * @Constraints:
 * <ul>
 *     <li>2 <= nums.length <= 10^4
       <li>-10^9 <= nums[i] <= 10^9
       <li>-10^9 <= target <= 10^9
       <li>Only one valid answer exists.
 * </ul>
 * @Solution:
 * <ul>
 * <li>Subtract it from target and record what's left.
 * <li>If the remainder is present in num_to_index, return the two indices.
 * <li>Otherwise record the current number-index pair into the map and continue.
 * </ul>
 * @Time: O(N)
 * @Space: O(1)
 * @Author: Daniil Kupriyanov
 */

public final class Solution_1_Two_Sum extends LeetcodeSolution {

    public Solution_1_Two_Sum() { super(EASY); }

    @ProblemSolution(timeComplexity = "O(N)", spaceComplexity = "O(1)")
    private int[] twoSum(int[] nums, int target) {
        var numToIndex = new HashMap<Integer, Integer>();
        for (var i = 0; i < nums.length; ++i) {
            int rem = target - nums[i];
            if (numToIndex.containsKey(rem)) {
                return new int[] {numToIndex.get(rem), i};
            }
            numToIndex.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }

    @Override
    @ProblemInputData
    public void run() {
        var nums = new int[] {3,2,4};
        var solution = twoSum(nums, 6);
        ASSERT_EQ(Arrays.compare(solution,  new int[]{1, 2}), 0);
    }
}