package leetcode.solutions;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import leetcode.solutions.concrete.*;

// TODO: measure solutions time
// TODO: fix logger or use System.out

/**
 * {@code SolutionRunner} is the main class which hold solutions list and provides the solutions tests.
 * User can specify concrete solution to run ("--run=X" where X is a solution ID), or run all solutions one by one ("--runAll").
 * @Author: Daniil Kuprianov
 */

public class SolutionRunner {

    private static HashMap<Integer, SolutionsFactory<LeetcodeSolution>> _solutionsFactories;
    private static final Logger _LOGGER = Logger.getLogger(SolutionRunner.class.getName());

    static {
        _LOGGER.setLevel(Level.FINEST);
        var consoleHandler = new ConsoleHandler();
        _LOGGER.addHandler(consoleHandler);
        _LOGGER.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
    }

    public static void main(String[] args) {
        _LOGGER.log(Level.FINE, "Started with args: {0}", Arrays.toString(args));
        initSolutions();
        boolean solutionFound = false;
        for (String arg : args) {
            if (arg.contains("--runAll")) {
                try {
                    runAll();
                } catch (SolutionValidationException exception) {
                    _LOGGER.log(Level.SEVERE, "{0}: solutionID: {1} solutionName: {2}",
                            new Object[] {exception.getMessage(), exception.getSolutionID(), exception.getSolutionName()});
                }
                solutionFound = true;
                break;
            } else if (arg.contains("--run=")) {
                var solutionToRun = Integer.parseInt(arg.substring(6));
                var solution = _solutionsFactories.get(solutionToRun);
                if (solution == null) break;
                _LOGGER.log(Level.FINE, "Running {0}", solution.toString());
                try {
                    solution.create().run();
                } catch (SolutionValidationException exception) {
                    _LOGGER.log(Level.SEVERE, "{0}: solutionID: {1} solutionName: {2}",
                                new Object[] {exception.getMessage(), exception.getSolutionID(), exception.getSolutionName()});
                }

                solutionFound = true;
                break;
            }
        }
        if (!solutionFound) {
            _LOGGER.log(Level.FINE, "No such problem to solve");
        }
    }

    private static void runAll() {
        for (var solution : _solutionsFactories.entrySet()) {
            _LOGGER.log(Level.FINE, "Running {0}", solution.toString());
            solution.getValue().create().run();
        }
    }

    private static void initSolutions() {
        _solutionsFactories = new HashMap<>();
        _solutionsFactories.put(1,  Solution_1_Two_Sum::new);
        _solutionsFactories.put(9,  Solution_9_Palindrome_Number::new);
        _solutionsFactories.put(13, Solution_13_Roman_to_Integer::new);
        _solutionsFactories.put(20, Solution_20_Valid_Parentheses::new);
        _solutionsFactories.put(32, Solution_32_Longest_Valid_Parentheses::new);
        // TODO: _solutionsFactories.put(46, Solution_46_Permutations::new);
    }
}

