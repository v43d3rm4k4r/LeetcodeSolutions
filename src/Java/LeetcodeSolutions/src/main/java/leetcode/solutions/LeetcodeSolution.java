package leetcode.solutions;

/**
 * {@code LeetcodeSolution} is the superclass of all solutions and includes common info about each solution.
 *  It has reference on itself so concrete solutions will be implemented as singletons.
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

