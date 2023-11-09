package leetcode.solutions.validation;

import leetcode.solutions.LeetcodeSolution;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
            throwException("ASSERT_TRUE fails (expected: true, actual: false)");
        }
    };

    public static void ASSERT_FALSE(boolean statement) throws SolutionValidationException {
        if (statement) {
            throwException("ASSERT_FALSE fails (expected: false, actual: true)");
        }
    };

    public static void ASSERT_EQ(@Nullable Object expected, @Nullable Object actual) throws SolutionValidationException {
        if (expected == null || actual == null) return;
        if (!expected.equals(actual)) {
            throwException("ASSERT_EQ fails ");
        }
    }

    public static void ASSERT_EQ(int[] expected, int[] actual)
            throws SolutionValidationException { // TODO: use generics ?
        if (!Arrays.equals(expected, actual)) {
            throwException("ASSERT_EQ fails");
        }
    }

    public static  void ASSERT_EQ(Object[] expected, Object[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throwException("ASSERT_EQ fails");
        }
    }

    public static <T extends Comparable<T>> void ASSERT_EQ(Collection<T> expected, Collection<T> actual)
            throws SolutionValidationException {
        if (!expected.equals(actual)) {
            throwException("ASSERT_EQ fails");
        }
    }

    public static void ASSERT_STREQ(@NotNull final String expected, @NotNull final String actual)
            throws SolutionValidationException {
        if (expected.compareTo(actual) != 0) {
            throwException("ASSERT_STREQ fails (expected: " + expected + ", actual: " + actual + ")");
        }
    }
}