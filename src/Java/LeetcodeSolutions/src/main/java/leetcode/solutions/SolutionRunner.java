package leetcode.solutions;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import static java.lang.System.out;

import leetcode.solutions.concrete.java.*;
import leetcode.solutions.concrete.kotlin.*;
import leetcode.solutions.validation.NoSuchSolutionException;
import leetcode.solutions.validation.SolutionValidationException;
import org.jetbrains.annotations.NotNull;

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

    private static int totalSolved;
    private static void showStats() {
        int easy = 0, medium = 0, hard = 0;
        int time1 = 0, timeLogN = 0, timeN = 0, timeNLogN = 0, timeNM = 0, timeN2 = 0,
            timeNLogN2 = 0, timeN3 = 0, time2N = 0, timeNFactorial = 0;
        int space1 = 0, spaceLogN = 0, spaceN = 0, spaceNLogN = 0, spaceNM = 0, spaceN2 = 0,
                spaceNLogN2 = 0, spaceN3 = 0, space2N = 0, spaceNFactorial = 0;
        int totalProblems = 0;

        for (var solution : solutionsFactories.entrySet()) {
            var concreteSolution = solution.getValue().create();

            var difficulty = concreteSolution.getProblemDifficulty();
            if (difficulty == ProblemDifficulty.EASY)        ++easy;
            else if (difficulty == ProblemDifficulty.MEDIUM) ++medium;
            else if (difficulty == ProblemDifficulty.HARD)   ++hard;

            var timeComplexities = concreteSolution.getSolutionTimeComplexities();
            for (var complexity : timeComplexities) {
                switch (complexity) {
                    case O_1          -> ++time1;
                    case O_logN       -> ++timeLogN;
                    case O_N          -> ++timeN;
                    case O_NlogN      -> ++timeNLogN;
                    case O_NM         -> ++timeNM;
                    case O_N2         -> ++timeN2;
                    case O_NlogN2     -> ++timeNLogN2;
                    case O_N3         -> ++timeN3;
                    case O_2N         -> ++time2N;
                    case O_NFactorial -> ++timeNFactorial;
                }
            }
            var spaceComplexities = concreteSolution.getSolutionSpaceComplexities();
            for (var complexity : spaceComplexities) {
                switch (complexity) {
                    case O_1          -> ++space1;
                    case O_logN       -> ++spaceLogN;
                    case O_N          -> ++spaceN;
                    case O_NlogN      -> ++spaceNLogN;
                    case O_NM         -> ++spaceNM;
                    case O_N2         -> ++spaceN2;
                    case O_NlogN2     -> ++spaceNLogN2;
                    case O_N3         -> ++spaceN3;
                    case O_2N         -> ++space2N;
                    case O_NFactorial -> ++spaceNFactorial;
                }
                ++totalSolved;
            }
            ++totalProblems;
        }

        out.println("\nTotal solutions accepted: " + totalSolved);
        out.println("Total problems solved: " + totalProblems);
        out.println("Easy: "   + easy);
        out.println("Medium: " + medium);
        out.println("Hard: "   + hard);

        out.println("\nTime complexity stats:");
        printfIfNotZero("O(1)",         time1);
        printfIfNotZero("O(log(N))",    timeLogN);
        printfIfNotZero("O(N)",         timeN);
        printfIfNotZero("O(Nlog(N))",   timeNLogN);
        printfIfNotZero("O(N*M)",       timeNM);
        printfIfNotZero("O(N^2)",       timeN2);
        printfIfNotZero("O(Nlog(N)^2)", timeNLogN2);
        printfIfNotZero("O(N^3)",       timeN3);
        printfIfNotZero("O(2N)",        time2N);
        printfIfNotZero("O(N!)",        timeNFactorial);

        out.println("\nSpace complexity stats:");
        printfIfNotZero("O(1)",         space1);
        printfIfNotZero("O(log(N))",    spaceLogN);
        printfIfNotZero("O(N)",         spaceN);
        printfIfNotZero("O(Nlog(N))",   spaceNLogN);
        printfIfNotZero("O(N*M)",       spaceNM);
        printfIfNotZero("O(N^2)",       spaceN2);
        printfIfNotZero("O(Nlog(N)^2)", spaceNLogN2);
        printfIfNotZero("O(N^3)",       spaceN3);
        printfIfNotZero("O(2N)",        space2N);
        printfIfNotZero("O(N!)",        spaceNFactorial);
    }

    private static void printfIfNotZero(@NotNull String str, int value) {
        if (value == 0) return;
        var percent = ((float)value / (float)totalSolved) * 100f;
        var delimiter = 2;
        var sharpsCount = (int)percent / delimiter; // 2% == one sharp
        var padding = 10 - str.length();
        out.printf("%-65s%d (%.2f%s)%n", str + ": " + " ".repeat(padding) + "#".repeat(sharpsCount), value, percent, "%");
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