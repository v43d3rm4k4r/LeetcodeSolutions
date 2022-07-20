package leetcode.solutions.concrete;

import leetcode.solutions.*;
import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Problem: Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * <p>
 * @Solution: // TODO
 * @Complexity: O(N)
 * @Author: Daniil Kuprianov
 */

public class Solution_1_Two_Sum extends LeetcodeSolution {

    @ProblemSolution
    private int[] twoSum(List<Integer> nums, int target) {
        var numToIndex = new HashMap<Integer, Integer>();
        for (var i = 0; i < nums.size(); ++i) {

            int rem = target - nums.get(i);

        if (numToIndex.containsKey(rem)) {
            return new int[] {numToIndex.get(rem), i};
        }

        numToIndex.put(nums.get(i), i);
        }
        return new int[] {-1, -1};
    }

    @Override
    @ProblemInputData
    public void run() {
        var nums = Arrays.asList(3,2,4);
        var solution = twoSum(nums, 6);
        var test = Arrays.compare(solution, new int[]{1, 2});
        ASSERT_THROW(Arrays.compare(solution, new int[]{1, 2}) == 0);
    }

    private Solution_1_Two_Sum(int solutionID, String solutionName, ProblemDifficulty problemDifficulty) {
        super(solutionID, solutionName, problemDifficulty);
    }

    public static synchronized LeetcodeSolution instance() {
        if (_INSTANCE == null) {
            _INSTANCE = new Solution_1_Two_Sum(1, "Solution Name", EASY);
        }
        return _INSTANCE;
    }
}
