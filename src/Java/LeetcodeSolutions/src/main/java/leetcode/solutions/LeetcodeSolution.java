package leetcode.solutions;

/**
 * @Problem: Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * <p>
 * //@Solution:
 * @Solution:
 * @Complexity:
 * @Author: Daniil Kuprianov
 */

public abstract class LeetcodeSolution implements Runnable {
    private final int    _solutionID;
    private final String _solutionName;
    private final ProblemDifficulty _problemDifficulty;

    protected static LeetcodeSolution _INSTANCE;

    protected LeetcodeSolution(int solutionID, String solutionName, ProblemDifficulty problemDifficulty) {
        _solutionID        = solutionID;
        _solutionName      = solutionName;
        _problemDifficulty = problemDifficulty;
        SolutionValidator.registerSolution(this);
    }

    public int    getSolutionID()   { return _solutionID; }
    public String getSolutionName() { return _solutionName; }

    @Override
    public String toString() {
        return "problem \"" + _solutionName + "\" (" + _solutionID + "), " + _problemDifficulty.toString().toLowerCase() +
                " difficulty\n";
    }
}

