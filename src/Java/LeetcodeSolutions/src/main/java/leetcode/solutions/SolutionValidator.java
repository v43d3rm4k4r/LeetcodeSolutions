package leetcode.solutions;

public final class SolutionValidator {
    private static LeetcodeSolution _solution;

    public static void registerSolution(LeetcodeSolution solution) {
        _solution = solution;
    }

    public static void ASSERT_THROW(boolean statement) {
        if (!statement) {
            throw new SolutionValidationException("ASSERT_THROW fails", _solution.getSolutionID(),
                                                                        _solution.getSolutionName());
        }
    };
}