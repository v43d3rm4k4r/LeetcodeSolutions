package leetcode.solutions;

import leetcode.solutions.annotations.ProblemSolution;
import leetcode.solutions.validation.SolutionValidator;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code LeetcodeSolution} is the superclass of all solutions and includes common info about each solution.
 *  Every solution registered self with the {@code SolutionValidator} to get access to assertion methods.
 *  Concrete solutions implement the contract - their class names must match the following pattern:
 *  Solution_[ID]_[Name].
 * @author Daniil Kupriyanov
 */

public abstract class LeetcodeSolution implements Runnable {

    private int    solutionID;
    private String solutionName;
    final private List<String>      solutionTimeComplexities  = new ArrayList<>(1);
    final private List<String>      solutionSpaceComplexities = new ArrayList<>(1);
    final private ProblemDifficulty problemDifficulty;

    protected LeetcodeSolution(@NotNull ProblemDifficulty problemDifficulty) {
        this.problemDifficulty = problemDifficulty;
        SolutionValidator.registerSolution(this);
        resolveConcreteSolutionInfo();
    }

    public int               getSolutionID()                { return solutionID; }
    public String            getSolutionName()              { return solutionName; }
    public List<String>      getSolutionTimeComplexities()  { return solutionTimeComplexities; }
    public List<String>      getSolutionSpaceComplexities() { return solutionSpaceComplexities; }
    public ProblemDifficulty getProblemDifficulty()         { return problemDifficulty; }

    @Override
    public String toString() {
        return "problem \"" + solutionName + "\" (" + solutionID + "), " + problemDifficulty.toString().toLowerCase() +
                " difficulty\n";
    }

    private void resolveConcreteSolutionInfo() {
        final var className = getClass().getSimpleName();
        solutionID   = Integer.parseInt(className.split("_")[1]);
        solutionName = className.replace("_", " ");
        solutionName = solutionName.substring(solutionName.indexOf(" ", solutionName.indexOf(" ") + 1) + 1);

        final var methods = getClass().getDeclaredMethods();
        for (final var method : methods) {
            if (method.isAnnotationPresent(ProblemSolution.class)) {
                final var solutionComplexities = method.getAnnotation(ProblemSolution.class);
                solutionTimeComplexities.add(solutionComplexities.timeComplexity());
                solutionSpaceComplexities.add(solutionComplexities.spaceComplexity());
            }
        }
    }
}