package leetcode.solutions;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;

/**
 * {@code SolutionValidator} used to validate solutions using static assertion methods. It is obtains the solution info
 * by having the {@code LeetcodeSolution} reference that gets by the {@link #registerSolution}() method.
 * @see     LeetcodeSolution
 * @author Daniil Kupriyanov
 */

public final class SolutionValidator {
    private static LeetcodeSolution _currentSolution;

    private static void throwException(String message) throws SolutionValidationException {
         throw new SolutionValidationException(message, _currentSolution.getSolutionID(),
                                                        _currentSolution.getSolutionName());
    }

    public static void registerSolution(LeetcodeSolution solution) {
        _currentSolution = solution;
    }

    public static void ASSERT_TRUE(boolean statement) throws SolutionValidationException {
        if (!statement) {
            throwException("ASSERT_TRUE fails");
        }
    };

    public static void ASSERT_FALSE(boolean statement) throws SolutionValidationException {
        if (statement) {
            throwException("ASSERT_FALSE fails");
        }
    };

    public static void ASSERT_EQ(boolean expected, boolean actual) throws SolutionValidationException {
        if (expected != actual) {
            throwException("ASSERT_EQ fails");
        }
    }

    public static void ASSERT_EQ(@NotNull Integer expected, @NotNull Integer actual) throws SolutionValidationException {
        if (expected.compareTo(actual) != 0) {
            throwException("ASSERT_EQ fails");
        }
    }

    public static void ASSERT_EQ(int[] expected, int[] actual) throws SolutionValidationException { // TODO: use generics ?
        if (!Arrays.equals(expected, actual)) {
            throwException("ASSERT_EQ fails");
        }
    }

    public static void ASSERT_STREQ(@NotNull String expected,@NotNull String actual) throws SolutionValidationException {
        if (expected.compareTo(actual) != 0) {
            throwException("ASSERT_STREQ fails (expected: " + expected + ", actual: " + actual + ")");
        }
    }
}