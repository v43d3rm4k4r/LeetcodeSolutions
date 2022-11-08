package leetcode.solutions;

import leetcode.solutions.annotations.ProblemSolution;
import org.jetbrains.annotations.NotNull;

/**
 * {@code LeetcodeSolution} is the superclass of all solutions and includes common info about each solution.
 *  Every solution registered self with the {@code SolutionValidator} to get access to assertion methods.
 * @author Daniil Kupriyanov
 */

public abstract class LeetcodeSolution implements Runnable {

    private int        solutionID;
    private String     solutionName;
    private Complexity solutionComplexity; // TODO: add space complexity
    final private ProblemDifficulty problemDifficulty;


    protected LeetcodeSolution(ProblemDifficulty problemDifficulty) {
        this.problemDifficulty = problemDifficulty;
        SolutionValidator.registerSolution(this);
    }

    public int               getSolutionID()         { return solutionID; }
    public String            getSolutionName()       { return solutionName; }
    public Complexity        getSolutionComplexity() { return solutionComplexity; }
    public ProblemDifficulty getProblemDifficulty()  { return problemDifficulty; }

    @Override
    public String toString() {
        return "problem \"" + solutionName + "\" (" + solutionID + "), " + problemDifficulty.toString().toLowerCase() +
                " difficulty\n";
    }

    protected void resolveConcreteSolutionInfo(@NotNull LeetcodeSolution concrete) {
        var className = concrete.getClass().getSimpleName();
        solutionID   = Integer.parseInt(className.split("_")[1]);
        solutionName = className.replace("_", " ");
        solutionName =  solutionName.substring(solutionName.indexOf(" ", solutionName.indexOf(" ") + 1) + 1);

        var methods = concrete.getClass().getDeclaredMethods();
        for (var method : methods) {
            if (method.isAnnotationPresent(ProblemSolution.class)) {
                var solution = method.getAnnotation(ProblemSolution.class);
                solutionComplexity = solution.complexity();
            }
        }
    }
}