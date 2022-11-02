package leetcode.solutions;

import org.jetbrains.annotations.NotNull;

/**
 * {@code LeetcodeSolution} is the superclass of all solutions and includes common info about each solution.
 *  Every solution registered self with the {@code SolutionValidator} to get access to assertion methods.
 * @Author: Daniil Kuprianov
 */

public abstract class LeetcodeSolution implements Runnable {

    protected int               _solutionID;
    protected String            _solutionName;
    protected ProblemDifficulty _problemDifficulty;

    protected LeetcodeSolution(ProblemDifficulty problemDifficulty) {
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

    protected void resolveConcreteSolutionInfo(@NotNull LeetcodeSolution concrete) {
        var className = concrete.getClass().getSimpleName();
        _solutionID   = Integer.parseInt(className.split("_")[1]);
        _solutionName = className.replace("_", " ");
        _solutionName =  _solutionName.substring(_solutionName.indexOf(" ", _solutionName.indexOf(" ") + 1) + 1);
    }
}