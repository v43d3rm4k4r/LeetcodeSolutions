package leetcode.solutions;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import static java.lang.System.out;
import static leetcode.solutions.Complexity.*;

import leetcode.solutions.concrete.java.*;
import leetcode.solutions.concrete.kotlin.*;

// TODO: measure solutions time
// TODO: fix logger or use System.out
// TODO: do time complexity refactoring in another branch

/**
 * {@code SolutionRunner} is the main class which hold solutions list and provides the solutions tests.
 * User can specify concrete solution to run ("--run=X" where X is a solution ID), or run all solutions one by one ("--runAll").
 * @author Daniil Kupriyanov
 */

public final class SolutionRunner {

    private static final LinkedHashMap<Integer, SolutionsFactory<LeetcodeSolution>> solutionsFactories = new LinkedHashMap<>();
    private static final Logger LOGGER = Logger.getLogger(SolutionRunner.class.getName());

    static {
        LOGGER.setLevel(Level.FINEST);
        var consoleHandler = new ConsoleHandler();
        LOGGER.addHandler(consoleHandler);
        LOGGER.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
    }

    public static void main(String[] args) {
        LOGGER.log(Level.FINE, "Started with args: {0}", Arrays.toString(args));
        initSolutions();
        for (String arg : args) {
            if (arg.contains("--runAll")) {
                try {
                    runAll();
                } catch (SolutionValidationException exception) {
                    LOGGER.log(Level.SEVERE, "{0}: solutionID: {1} solutionName: {2}",
                            new Object[] {exception.getMessage(), exception.getSolutionID(), exception.getSolutionName()});
                }
                break;
            } else if (arg.contains("--run=")) {
                var solutionToRun = Integer.parseInt(arg.substring(6));
                try {
                    var solution = solutionsFactories.get(solutionToRun);
                    if (solution == null) throw new NoSuchSolutionException("There is no solution with '" + solutionToRun + "' ID");
                    LOGGER.log(Level.FINE, "Running {0}", solution.toString());
                    try {
                        solution.create().run();
                    } catch (SolutionValidationException exception) {
                        LOGGER.log(Level.SEVERE, "{0}: solutionID: {1} solutionName: {2}",
                                new Object[] {exception.getMessage(), exception.getSolutionID(), exception.getSolutionName()});
                    }
                } catch (NoSuchSolutionException exception) {
                    LOGGER.log(Level.SEVERE, "{0}", exception.getMessage());
                }
                break;
            }
            else if (arg.contains("--stats")) {
                showStats();
            }
        }
    }

    private static void runAll() {
        for (var solution : solutionsFactories.entrySet()) {
            LOGGER.log(Level.FINE, "Running {0}", solution.toString());
            solution.getValue().create().run();
        }
    }

    private static void showStats() {
        int easyCount = 0, mediumCount = 0, hardCount = 0, totalSolved = solutionsFactories.entrySet().size();
        int time1Count = 0, timeLogNCount = 0, timeNCount = 0, timeNLogNCount = 0, timeNMCount = 0, timeN2Count = 0,
            timeNLogN2Count = 0, timeN3Count = 0, time2NCount = 0, timeNFactorialCount = 0;
        for (var solution : solutionsFactories.entrySet()) {
            var concreteSolution = solution.getValue().create();

            var difficulty = concreteSolution.getProblemDifficulty();
            if (difficulty == ProblemDifficulty.EASY)        ++easyCount;
            else if (difficulty == ProblemDifficulty.MEDIUM) ++mediumCount;
            else if (difficulty == ProblemDifficulty.HARD)   ++hardCount;

            var complexity = concreteSolution.getSolutionComplexity();
            switch (complexity) {
                case O_1          -> ++time1Count;
                case O_logN       -> ++timeLogNCount;
                case O_N          -> ++timeNCount;
                case O_NlogN      -> ++timeNLogNCount;
                case O_NM         -> ++timeNMCount;
                case O_N2         -> ++timeN2Count;
                case O_NlogN2     -> ++timeNLogN2Count;
                case O_N3         -> ++timeN3Count;
                case O_2N         -> ++time2NCount;
                case O_NFactorial -> ++timeNFactorialCount;
            }
        }

        out.println("Total solutions solved: " + totalSolved);
        out.println("Easy: "   + easyCount);
        out.println("Medium: " + mediumCount);
        out.println("Hard: "   + hardCount + "\n");

        out.println("Time complexity stats:");
        // TODO: implement
    }

    private static void initSolutions() {
        solutionsFactories.put(1,    Solution_1_Two_Sum::new);
        solutionsFactories.put(9,    Solution_9_Palindrome_Number::new);
        //solutionsFactories.put(11,   Solution_11_Container_With_Most_Water::new);
        solutionsFactories.put(12,   Solution_12_Integer_to_Roman::new);
        solutionsFactories.put(13,   Solution_13_Roman_to_Integer::new);
        solutionsFactories.put(20,   Solution_20_Valid_Parentheses::new);
        solutionsFactories.put(32,   Solution_32_Longest_Valid_Parentheses::new);
        solutionsFactories.put(35,   Solution_35_Search_Insert_Position::new);
        // TODO: solutionsFactories.put(46,   Solution_46_Permutations::new);
        solutionsFactories.put(66,   Solution_66_Plus_One::new);
        solutionsFactories.put(125,  Solution_125_Valid_Palindrome::new);
        solutionsFactories.put(136,  Solution_136_Single_Number::new);
        solutionsFactories.put(137,  Solution_137_Single_Number_II::new);
        solutionsFactories.put(807,  Solution_807_Max_Increase_to_Keep_City_Skyline::new);
        solutionsFactories.put(2125, Solution_2125_Number_of_Laser_Beams_in_a_Bank::new);
    }
}