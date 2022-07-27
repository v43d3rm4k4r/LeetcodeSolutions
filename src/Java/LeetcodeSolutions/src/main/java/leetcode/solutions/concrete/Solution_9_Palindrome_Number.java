package leetcode.solutions.concrete;

import leetcode.solutions.*;
import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import static leetcode.solutions.Complexity.*;

/**
 * @Problem: Given an integer x, return true if x is palindrome integer.
 * An integer is a palindrome when it reads the same backward as forward.
 * @Constraints:
 * <ul>
 *     <li>-2^31 <= x <= 2^31 - 1
 * </ul>
 * @Solution1:
 * <ul>
 * <li>Convert a number to a string for iteration.
 * <li>Iterate through the array to the middle using counters for the beginning and end, comparing the values.
 * </ul>
 * @Complexity: O(N)
 *
 * @Solution2:
 * <ul>
 * <li>Reverse half of x while dividing it to become the upper half.
 * <li>In the end check if reversed lower half is equal to the above half, or (in case if it's an odd length palindrome)
 * we check that equality with reversed rhs divided by 10 to skip that middle part.
 * </ul>
 * @Complexity: O(log(N))
 * @Author: Daniil Kuprianov
 */

public final class Solution_9_Palindrome_Number extends LeetcodeSolution {

    public Solution_9_Palindrome_Number() { super(9, "Palindrome Number", EASY); }

    @ProblemSolution(complexity = O_N)
    private boolean isPalindrome1(int x) {
        if (x < 0 || (x > 0 && (x % 10 == 0))) return false;
        var asString = String.valueOf(x);
        var digitsCount = asString.length();
        for (int i = 0, j = asString.length() - 1; i < digitsCount / 2; ++i, --j) {
            if ((int)asString.charAt(i) != (int)asString.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    @ProblemSolution(complexity = O_logN)
    private boolean isPalindrome2(int x) {
        if (x < 0 || (x > 0 && (x % 10 == 0))) return false;
        int rhs = 0;
        while (x > rhs) {
            rhs *= 10;
            rhs += x % 10;
            x /= 10;
        }
        return rhs == x || rhs / 10 == x;
    }

    @Override
    @ProblemInputData
    public void run() {
        ASSERT_TRUE(isPalindrome1(321123));
        ASSERT_TRUE(isPalindrome1(543212345));
        ASSERT_FALSE(isPalindrome1(12345));

        ASSERT_TRUE(isPalindrome2(12533521));
        ASSERT_FALSE(isPalindrome2(543211234));
        ASSERT_FALSE(isPalindrome2(12345));
    }
}
