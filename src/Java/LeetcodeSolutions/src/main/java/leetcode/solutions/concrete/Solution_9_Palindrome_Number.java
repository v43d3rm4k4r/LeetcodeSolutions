package leetcode.solutions.concrete;

import leetcode.solutions.*;
import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;

// TODO

public class Solution_9_Palindrome_Number extends LeetcodeSolution {

    public Solution_9_Palindrome_Number() { super(9, "Palindrome Number", EASY); }

    @ProblemSolution
    private boolean isPalindrome(int x) {
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

    @Override
    @ProblemInputData
    public void run() {
        ASSERT_TRUE(isPalindrome(321123));
        ASSERT_TRUE(isPalindrome(543212345));
        ASSERT_FALSE(isPalindrome(12345));
    }
}
