package leetcode.solutions.concrete.java;

import leetcode.solutions.*;
import org.jetbrains.annotations.NotNull;

import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import static leetcode.solutions.Complexity.*;

/**
 * @Problem: Given an integer array nums where every element appears three times except for one, which appears exactly
 * once. Find the single element and return it.
You must implement a solution with a linear runtime complexity and use only constant extra space.
 * @Constraints:
 * <ul>
 *     <li>1 <= nums.length <= 3 * 10^4
 *     <li>-2^31 <= nums[i] <= 2^31 - 1
 *     <li>Each element in nums appears exactly three times except for one element which appears once.
 * </ul>
 * @Solution: Will need two variables to store bits of numbers that occur once and twice, while using the XOR
 * operator from the previous problem. The combination of the & and ~ operators literally means this: if it doesn't
 * contain in once, then it should be here, and vice versa.
 * @Complexity: O(N)
 * @See:     {@link Solution_136_Single_Number}
 * @Author: Daniil Kuprianov
 */

public final class Solution_137_Single_Number_II extends LeetcodeSolution {

    public Solution_137_Single_Number_II() { super(MEDIUM); resolveConcreteSolutionInfo(this);  }

    @ProblemSolution(complexity = O_N)
    private int singleNumber(int @NotNull [] nums) {
        int ones = 0, twos = 0;
        for (var num : nums) {
            ones ^= num;
            ones &= ~twos;
            twos ^= num;
            twos &= ~ones;
        }
        return ones;
    }

    @Override
    @ProblemInputData
    public void run() {
        ASSERT_EQ(3,  singleNumber(new int[] {2, 2, 3, 2}));
        ASSERT_EQ(99, singleNumber(new int[] {0, 1, 0, 1, 0, 1, 99}));
    }
}
