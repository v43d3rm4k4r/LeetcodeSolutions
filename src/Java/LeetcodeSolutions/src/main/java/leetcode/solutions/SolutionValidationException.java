package leetcode.solutions;

/**
 * {@code SolutionValidationException} is the exception class that additionally contains the solution information.
 * In the "right" way, need to inherit from {@code Exception}, but in this case there will be a problem with changing the {@code Runnable}
 * contract in {@code LeetcodeSolution} children.
 * @Author: Daniil Kuprianov
 */

public final class SolutionValidationException extends RuntimeException {
    private final int    _solutionID;
    private final String _solutionName;

    public SolutionValidationException(String message, int solutionID, String solutionName) {
        super(message);
        _solutionID = solutionID;
        _solutionName = solutionName;
    }
    public int    getSolutionID()   { return _solutionID; }
    public String getSolutionName() { return _solutionName; }
}