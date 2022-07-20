package leetcode.solutions;

/**
 * {@code LeetcodeSolution} is the superclass of all solutions and includes common info about each solution.
 *  Every solution registered self with the {@code SolutionValidator} to get access to assertion methods.
 * @Author: Daniil Kuprianov
 */

public abstract class LeetcodeSolution implements Runnable {
    protected final int               _solutionID;
    protected final String            _solutionName;
    protected final ProblemDifficulty _problemDifficulty;

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

