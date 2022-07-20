package leetcode.solutions;

/**
 * {@code SolutionValidator} used to validate solutions using static assertion methods. It is obtains the solution info
 * by having the {@code LeetcodeSolution} reference that gets by the {@link #registerSolution}() method.
 * @see     LeetcodeSolution
 * @Author: Daniil Kuprianov
 */

public final class SolutionValidator {
    private static LeetcodeSolution _solution;

    private static void throwException(String message) throws SolutionValidationException {
         throw new SolutionValidationException(message, _solution.getSolutionID(),
                                                        _solution.getSolutionName());
    }

    public static void registerSolution(LeetcodeSolution solution) {
        _solution = solution;
    }

    public static void ASSERT_TRUE(boolean statement) {
        if (!statement) {
            throwException("ASSERT_TRUE fails");
        }
    };

    public static void ASSERT_EQ(boolean expected, boolean actual) {
        if (expected != actual) {
            throwException("ASSERT_EQ fails");
        }
    }

    public static void ASSERT_STREQ(String expected, String actual) {
        if (expected.compareTo(actual) != 0) {
            throwException("ASSERT_STREQ fails (expected: " + expected + ", actual: " + actual + ")");
        }
    }
}