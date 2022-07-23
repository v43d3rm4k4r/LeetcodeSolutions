package leetcode.solutions.concrete;

import leetcode.solutions.*;
import org.jetbrains.annotations.NotNull;

import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import static leetcode.solutions.Complexity.*;

/**
 * @Problem: Given a roman numeral, convert it to an integer.
 * @Solution:
 * <ul>
 * <li>Iterate over the provided string, checking each character.
 * <li>Increment the result variable by the appropriate amount, but with a check for the following situations:
 * <ol>
 *  <li>I can be placed before V (5) and X (10) to make 4 and 9.
 *  <li>X can be placed before L (50) and C (100) to make 40 and 90.
 *  <li>C can be placed before D (500) and M (1000) to make 400 and 900.
 *  </ol>
 * </ul>
 * @Complexity: O(N)
 * @Author: Daniil Kuprianov
 */

public class Solution_13_Roman_to_Integer extends LeetcodeSolution {

    public Solution_13_Roman_to_Integer() { super(13, "Roman to Integer", EASY); }

    @ProblemSolution(complexity = O_N)
    private int romanToInt(@NotNull String s) {
        if (s.length() > 15 || (!s.contains("I") && !s.contains("V") && !s.contains("X") &&
                                !s.contains("L") && !s.contains("C") && !s.contains("D") &&
                                !s.contains("M"))
           )
            return -1;

        int result = 0;
        char charBefore = '0';
        for (var i = 0; i < s.length(); ++i) {
            if (i != 0) charBefore = s.charAt(i - 1);
            switch (s.charAt(i)) {
                case 'I':
                    ++result;
                    break;
                case 'V':
                    if (charBefore == 'I') {
                        result += 3;
                    } else {
                        result += 5;
                    }
                    break;
                case 'X':
                    if (charBefore == 'I') {
                        result += 8;
                    } else {
                        result += 10;
                    }
                    break;
                case 'L':
                    if (charBefore == 'X') {
                        result += 30;
                    } else {
                        result += 50;
                    }
                    break;
                case 'C':
                    if (charBefore == 'X') {
                        result += 80;
                    } else {
                        result += 100;
                    }
                    break;
                case 'D':
                    if (charBefore == 'C') {
                        result += 300;
                    } else {
                        result += 500;
                    }
                    break;
                case 'M':
                    if (charBefore == 'C') {
                        result += 800;
                    } else {
                        result += 1000;
                    }
            }
        }
        return result;
    }

    @Override
    @ProblemInputData
    public void run() {
        ASSERT_EQ(3,    romanToInt("III"));
        ASSERT_EQ(58,   romanToInt("LVIII"));
        ASSERT_EQ(1994, romanToInt("MCMXCIV"));
    }
}
