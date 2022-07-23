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
                if (!_solutionsFactories.isEmpty()) {
                    try {
                        runAll();
                    } catch (SolutionValidationException exception) {
                        _LOGGER.log(Level.SEVERE, "{0}: solutionID: {1} solutionName: {2}",
                                new Object[] {exception.getMessage(), exception.getSolutionID(), exception.getSolutionName()});
                    }
                    solutionFound = true;
                    break;
                }
                _LOGGER.log(Level.FINE, "There is no solutions"); // never should be here
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
        if (_solutionsFactories.isEmpty()) {
            return;
        }
        for (var solution : _solutionsFactories.entrySet()) {
            _LOGGER.log(Level.FINE, "Running {0}", solution.toString());
            solution.getValue().create().run();
        }
    }

    private static void initSolutions() {
        _solutionsFactories = new HashMap<>();
        _solutionsFactories.put(1, Solution_1_Two_Sum::new);
        _solutionsFactories.put(9, Solution_9_Palindrome_Number::new);
        _solutionsFactories.put(13, Solution_13_Roman_to_Integer::new);
    }
}

