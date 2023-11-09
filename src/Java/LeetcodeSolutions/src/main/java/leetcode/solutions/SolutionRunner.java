package leetcode.solutions;

import java.util.*;
import static java.lang.System.out;
import org.jetbrains.annotations.NotNull;
import leetcode.solutions.concrete.java.*;
import leetcode.solutions.concrete.kotlin.*;
import leetcode.solutions.validation.NoSuchSolutionException;
import leetcode.solutions.validation.SolutionValidationException;

/**
 * {@code SolutionRunner} is the main class which hold solutions list and provides the solutions tests.
 * User can specify concrete solution to run ({@code --run=X} where X is a solution ID), or run all
 * solutions one by one ({@code --runAll}). Use {@code --stats} to show statistics.
 * @author Daniil Kupriyanov
 */

// TODO: Add problems tags

public final class SolutionRunner {

    private static final LinkedHashMap<Integer, SolutionsFactory<LeetcodeSolution>>
            solutionsFactories = new LinkedHashMap<>();

    private static void initSolutions() {
        solutionsFactories.put(1,    Solution_1_Two_Sum::new);
        solutionsFactories.put(9,    Solution_9_Palindrome_Number::new);
        //solutionsFactories.put(11,   Solution_11_Container_With_Most_Water::new);
        solutionsFactories.put(12,   Solution_12_Integer_to_Roman::new);
        solutionsFactories.put(13,   Solution_13_Roman_to_Integer::new);
        solutionsFactories.put(14,   Solution_14_Longest_Common_Prefix::new);
        solutionsFactories.put(20,   Solution_20_Valid_Parentheses::new);
        solutionsFactories.put(21,   Solution_21_Merge_Two_Sorted_Lists::new);
        solutionsFactories.put(22,   Solution_22_Generate_Parentheses::new);
        solutionsFactories.put(32,   Solution_32_Longest_Valid_Parentheses::new);
        solutionsFactories.put(35,   Solution_35_Search_Insert_Position::new);
        solutionsFactories.put(36,   Solution_36_Valid_Sudoku::new);
        // TODO: solutionsFactories.put(46,   Solution_46_Permutations::new);
        solutionsFactories.put(66,   Solution_66_Plus_One::new);
        solutionsFactories.put(125,  Solution_125_Valid_Palindrome::new);
        solutionsFactories.put(136,  Solution_136_Single_Number::new);
        solutionsFactories.put(137,  Solution_137_Single_Number_II::new);
        solutionsFactories.put(807,  Solution_807_Max_Increase_to_Keep_City_Skyline::new);
        solutionsFactories.put(2125, Solution_2125_Number_of_Laser_Beams_in_a_Bank::new);
    }

    public static void main(String @NotNull [] args) {
        initSolutions();
        for (String arg : args) {
            if (arg.contains("--runAll")) {
                try {
                    runAll();
                } catch (SolutionValidationException exception) {
                    out.printf("%s: solutionID: %d solutionName: %s",
                            exception.getMessage(),
                            exception.getSolutionID(),
                            exception.getSolutionName()
                    );
                }
                break;
            } else if (arg.contains("--run=")) {
               final var solutionToRun = Integer.parseInt(arg.substring(6));
                try {
                    final var solution = solutionsFactories.get(solutionToRun);
                    if (solution == null)
                        throw new NoSuchSolutionException("There is no solution with '" + solutionToRun + "' ID");
                    out.printf("Running %s", solution);
                    try {
                        solution.create().run();
                    } catch (SolutionValidationException exception) {
                        out.printf("%s: solutionID: %d solutionName: %s",
                                exception.getMessage(),
                                exception.getSolutionID(),
                                exception.getSolutionName()
                        );
                    }
                } catch (NoSuchSolutionException exception) {
                    out.printf("%s", exception.getMessage());
                }
                break;
            }
            else if (arg.contains("--stats")) {
                showStats();
            }
        }
    }

    private static void runAll() {
        for (final var solution : solutionsFactories.entrySet()) {
            out.printf("Running %s", solution);
            solution.getValue().create().run();
        }
    }

    private static int totalAccepted;
    private static void showStats() {
        int easy = 0, medium = 0, hard = 0;
        int time1 = 0, timeLogN = 0, timeN = 0, timeNLogN = 0, timeNM = 0, timeN2 = 0,
            timeNLogN2 = 0, timeN3 = 0, time2N = 0, timeKN = 0, timeNFactorial = 0,
                timeUnknown = 0;
        int space1 = 0, spaceLogN = 0, spaceN = 0, spaceNLogN = 0, spaceNM = 0, spaceN2 = 0,
                spaceNLogN2 = 0, spaceN3 = 0, space2N = 0, spaceKN = 0, spaceNFactorial = 0,
                spaceUnknown = 0;
        int totalProblems = 0;

        for (final var solution : solutionsFactories.entrySet()) {
            final var concreteSolution = solution.getValue().create();

            final var difficulty = concreteSolution.getProblemDifficulty();
            if (difficulty == ProblemDifficulty.EASY)        ++easy;
            else if (difficulty == ProblemDifficulty.MEDIUM) ++medium;
            else if (difficulty == ProblemDifficulty.HARD)   ++hard;

            final var timeComplexities = concreteSolution.getSolutionTimeComplexities();
            for (final var complexity : timeComplexities) {
                switch (complexity) {
                    case "O(1)"        -> ++time1;
                    case "O(logN)"     -> ++timeLogN;
                    case "O(N)"        -> ++timeN;
                    case "O(N*logN)"   -> ++timeNLogN;
                    case "O(N*M)"      -> ++timeNM;
                    case "O(N^2)"      -> ++timeN2;
                    case "O(N*logN^2)" -> ++timeNLogN2;
                    case "O(N^3)"      -> ++timeN3;
                    case "O(2^N)"      -> ++time2N;
                    case "O(K^N)"      -> ++timeKN;
                    case "O(N!)"       -> ++timeNFactorial;
                    default            -> ++timeUnknown;
                }
            }
            final var spaceComplexities = concreteSolution.getSolutionSpaceComplexities();
            for (final var complexity : spaceComplexities) {
                switch (complexity) {
                    case "O(1)"        -> ++space1;
                    case "O(logN)"     -> ++spaceLogN;
                    case "O(N)"        -> ++spaceN;
                    case "O(N*logN)"   -> ++spaceNLogN;
                    case "O(N*M)"      -> ++spaceNM;
                    case "O(N^2)"      -> ++spaceN2;
                    case "O(N*logN^2)" -> ++spaceNLogN2;
                    case "O(N^3)"      -> ++spaceN3;
                    case "O(2^N)"      -> ++space2N;
                    case "O(K^N)"      -> ++spaceKN;
                    case "O(N!)"       -> ++spaceNFactorial;
                    default            -> ++spaceUnknown;
                }
                ++totalAccepted;
            }
            ++totalProblems;
        }

        out.println("\nTotal solutions accepted: " + totalAccepted);
        out.println("Total problems solved: " + totalProblems);
        out.println("Easy: "   + easy);
        out.println("Medium: " + medium);
        out.println("Hard: "   + hard);

        out.println("\nTime complexity stats:");
        printfIfNotZero("O(1)",        time1);
        printfIfNotZero("O(logN)",     timeLogN);
        printfIfNotZero("O(N)",        timeN);
        printfIfNotZero("O(N*logN)",   timeNLogN);
        printfIfNotZero("O(N*M)",      timeNM);
        printfIfNotZero("O(N^2)",      timeN2);
        printfIfNotZero("O(N*logN^2)", timeNLogN2);
        printfIfNotZero("O(N^3)",      timeN3);
        printfIfNotZero("O(2^N)",      time2N);
        printfIfNotZero("O(K^N)",      timeKN);
        printfIfNotZero("O(N!)",       timeNFactorial);
        printfIfNotZero("[Unknown]",   timeUnknown);

        out.println("\nSpace complexity stats:");
        printfIfNotZero("O(1)",        space1);
        printfIfNotZero("O(logN)",     spaceLogN);
        printfIfNotZero("O(N)",        spaceN);
        printfIfNotZero("O(N*logN)",   spaceNLogN);
        printfIfNotZero("O(N*M)",      spaceNM);
        printfIfNotZero("O(N^2)",      spaceN2);
        printfIfNotZero("O(N*logN^2)", spaceNLogN2);
        printfIfNotZero("O(N^3)",      spaceN3);
        printfIfNotZero("O(2^N)",      space2N);
        printfIfNotZero("O(K^N)",      spaceKN);
        printfIfNotZero("O(N!)",       spaceNFactorial);
        printfIfNotZero("[Unknown]",   spaceUnknown);
    }

    private static void printfIfNotZero(@NotNull String str, int value) {
        if (value == 0) return;
        final var percent = ((float)value / (float) totalAccepted) * 100f;
        final var delimiter = 2;
        final var sharpsCount = (int)percent / delimiter; // 2% == one sharp
        final var padding = 10 - str.length();
        out.printf("%-65s%d (%.2f%s)%n", str + ": " + " ".repeat(padding) + "#".repeat(sharpsCount), value, percent, "%");
    }
}