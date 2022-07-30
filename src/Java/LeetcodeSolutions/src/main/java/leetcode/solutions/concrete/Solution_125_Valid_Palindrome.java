package leetcode.solutions.concrete;

import leetcode.solutions.*;
import org.jetbrains.annotations.NotNull;

import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import static leetcode.solutions.Complexity.*;

/**
 * @Problem: A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all
 * non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and
 * numbers. Given a string s, return true if it is a palindrome, or false otherwise.
 * @Constraints:
 * <ul>
 *     <li>1 <= s.length <= 2 * 10^5
 *     <li>s consists only of printable ASCII characters.
 * </ul>
 * @Solution1: Mutate string checking weather current character is letter or digit. Then using two-sided comparison for
 * each character.
 * @Complexity: O(N)
 * @Solution2: This solution is more efficient in terms of speed and memory. An intermediate string is not created here,
 * but the passage through the given string occurs immediately. At the same time, the counters i and j are synchronized -
 * as long as one of them has an invalid symbol, the other counter does not move.
 * @Complexity: O(N)
 * @See:     {@link Solution_9_Palindrome_Number}
 * @Author: Daniil Kuprianov
 */

public final class Solution_125_Valid_Palindrome extends LeetcodeSolution {

    public Solution_125_Valid_Palindrome() { super(EASY); resolveConcreteSolutionInfo(this); }

    @ProblemSolution(complexity = O_N)
    private boolean isPalindrome1(@NotNull String s) {
        var filteredStr = new StringBuilder(s.toLowerCase());
        for (var ch = 0; ch < filteredStr.length(); ++ch) {
            if (!Character.isLetterOrDigit(filteredStr.charAt(ch))) {
                filteredStr.deleteCharAt(ch--);
            }
        }
        for (int i = 0, j = filteredStr.length() - 1; i < filteredStr.length() / 2; ++i, --j) {
            if (filteredStr.charAt(i) != filteredStr.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    @ProblemSolution(complexity = O_N)
    private boolean isPalindrome2(@NotNull String s) {
        int i = 0, j = s.length() - 1;
        while (i != j + 1 && i != j) {
            if (Character.isLetterOrDigit(s.charAt(i)) && Character.isLetterOrDigit(s.charAt(j))) {
                if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                    return false;
                }
                ++i; --j;
            } else {
                if (!Character.isLetterOrDigit(s.charAt(i))) {
                    ++i;
                } else {
                    --j;
                }
            }
        }
        return true;
    }

    @Override
    @ProblemInputData
    public void run() {
        ASSERT_TRUE(isPalindrome1("A man, a plan, a canal: Panama"));
        ASSERT_FALSE(isPalindrome1("race a car"));
        ASSERT_TRUE(isPalindrome1(" "));
        ASSERT_FALSE(isPalindrome1(",,,,,,,,,,,,some"));

        ASSERT_TRUE(isPalindrome2("A man, a plan, a canal: Panama"));
        ASSERT_FALSE(isPalindrome2("race a car"));
        ASSERT_TRUE(isPalindrome2(" "));
        ASSERT_FALSE(isPalindrome2(",,,,,,,,,,,,some"));
    }
}
