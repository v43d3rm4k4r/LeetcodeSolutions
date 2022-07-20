package leetcode.solutions;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import leetcode.solutions.concrete.Solution_1_Two_Sum;

// TODO: measure solutions time
// TODO: fix logger or use System.out

/**
 * {@code SolutionRunner} is the main class which hold solutions list and provides the solutions tests.
 * User can specify concrete solution to run ("--run=X" where X is a solution ID), or run all solutions one by one ("--runAll").
 * @Author: Daniil Kuprianov
 */

public class SolutionRunner {

    private static final List<LeetcodeSolution> _solutions;
    private static final Logger _LOGGER = Logger.getLogger(SolutionRunner.class.getName());

    static {
        _solutions = Arrays.asList(Solution_1_Two_Sum.instance() // TODO: other solutions
        );

        _LOGGER.setLevel(Level.FINEST);
        var consoleHandler = new ConsoleHandler();
        _LOGGER.addHandler(consoleHandler);
        _LOGGER.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());
    }

    private static void runAll() {
        if (_solutions.isEmpty()) {
            return;
        }
        for (var solution : _solutions) {
            _LOGGER.log(Level.FINE, "Running {0}", solution.toString());
            solution.run();
        }
    }

    public static void main(String[] args) {
        _LOGGER.log(Level.FINE, "Started with args: {0}", Arrays.toString(args));
        boolean solutionFound = false;
        for (String arg : args) {
            if (arg.contains("--runAll")) {
                if (!_solutions.isEmpty()) {
                    try {
                        runAll();
                    } catch (SolutionValidationException exception) {
                        _LOGGER.log(Level.SEVERE, exception.getMessage() + ": solutionID: " + exception.getSolutionID() +
                                "solutionName: " + exception.getSolutionName());
                    }
                    solutionFound = true;
                    break;
                }
                _LOGGER.log(Level.FINE, "There is no solutions"); // never should be here
                break;
            } else if (arg.contains("--run=")) {
                var solutionToRun = Integer.parseInt(arg.substring(5));
                // TODO: run the specified solution
                var solution = _solutions.get(solutionToRun);
                _LOGGER.log(Level.FINE, "Running {0}", solution.toString());
                try {
                    solution.run();
                } catch (SolutionValidationException exception) {
                    _LOGGER.log(Level.SEVERE, exception.getMessage() + ": solutionID: " + exception.getSolutionID() +
                            "solutionName: " + exception.getSolutionName());
                }

                solutionFound = true;
                break;
            }
        }
        if (!solutionFound) {
            _LOGGER.log(Level.FINE, "No such problem to solve");
        }
    }
}

