package leetcode.solutions;

import leetcode.solutions.annotations.ProblemSolution;

/**
 * {@code Complexity} is the enum for {@link ProblemSolution} annotations.
 * @author Daniil Kupriyanov
 */

public enum Complexity {
    O_1,
    O_logN,
    O_N,
    O_NlogN,
    O_NM,
    O_N2,
    O_NlogN2,
    O_N3,          // O(N^3)
    O_2N,          // O(2^N)
    O_NFactorial,
    DEFAULT        // for higher-order methods (not implementations)
}
