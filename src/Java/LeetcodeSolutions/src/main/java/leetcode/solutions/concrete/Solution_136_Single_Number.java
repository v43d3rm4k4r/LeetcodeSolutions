package leetcode.solutions.concrete;

import leetcode.solutions.*;
import org.jetbrains.annotations.NotNull;

import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import static leetcode.solutions.Complexity.*;

/**
 * @Problem: Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 * @Constraints:
 * <ul>
 *     <li>1 <= nums.length <= 3 * 10^4
 *     <li>-3 * 10^4 <= nums[i] <= 3 * 10^4
 *     <li>Each element in the array appears twice except for one element which appears only once.
 * </ul>
 * @Solution:
 * Iterating the array and apply XOR operation from each of them to result variable. When the operation is applied with
 * the same number a second time, it will be removed from result. As a result, only a unique value will remain. This
 * solution applies to all cases when all numbers except one occur in the array an even number of times.
 * @Complexity: O(N)
 * @Author: Daniil Kuprianov
 */

public final class Solution_136_Single_Number extends LeetcodeSolution {

    public Solution_136_Single_Number() { super(136, "Single Number", EASY); }

    @ProblemSolution(complexity = O_N)
    private int singleNumber(int @NotNull [] nums) {
        var result = 0;
        for (var i = 0; i < nums.length; ++i) {
            result ^= nums[i];
        }
        return result;
    }

    @Override
    @ProblemInputData
    public void run() {
        ASSERT_EQ(1, singleNumber(new int[] {2, 2, 1}));
        ASSERT_EQ(4, singleNumber(new int[] {4, 1, 2, 1, 2}));
        ASSERT_EQ(4, singleNumber(new int[] {4, 1, 2, 1, 2, 1, 2, 1, 2}));
    }
}
