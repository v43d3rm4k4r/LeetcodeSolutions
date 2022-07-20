package leetcode.solutions.concrete;

import leetcode.solutions.*;
import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;

// TODO

public class Solution_9_Palindrome_Number extends LeetcodeSolution {

    @ProblemSolution
    private boolean isPalindrome(int x) {
        var asString = String.valueOf(x);
        var digitsCount = asString.length();
        for (int i = 0, j = asString.length() - 1; i < digitsCount / 2; ++i, --j) {
            var rem = (int)asString.charAt(i) - (int)asString.charAt(j);
            if (rem * rem != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    @ProblemInputData
    public void run() {
        ASSERT_TRUE(isPalindrome(321123));
    }

    private Solution_9_Palindrome_Number(int solutionID, String solutionName, ProblemDifficulty problemDifficulty) {
        super(solutionID, solutionName, problemDifficulty);
    }

    public static synchronized LeetcodeSolution instance() {
        if (_INSTANCE == null) {
            _INSTANCE = new Solution_9_Palindrome_Number(9, "Palindrome Number", EASY);
        }
        return _INSTANCE;
    }
}
