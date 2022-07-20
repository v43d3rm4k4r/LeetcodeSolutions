package leetcode.solutions.concrete;

import leetcode.solutions.*;
import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import static leetcode.solutions.Complexity.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Problem: Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * @Solution:
 * <ul>
 * <li>Subtract it from target and record what's left.
 * <li>If the remainder is present in num_to_index, return the two indices.
 * <li>Otherwise record the current number-index pair into the map and continue.
 * </ul>
 * @Complexity: O(N)
 * @Author: Daniil Kuprianov
 */

public class Solution_1_Two_Sum extends LeetcodeSolution {

    public Solution_1_Two_Sum() { super(1, "Two Sum", EASY); }

    @ProblemSolution(complexity = O_N)
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
        var test = Arrays.compare(solution, new int[]{1, 2});
        ASSERT_EQ(Arrays.compare(solution,  new int[]{1, 2}), 0);
    }
}
