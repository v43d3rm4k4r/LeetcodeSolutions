package leetcode.solutions;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SolutionRunner {
    private static TreeMap<Integer, LeetcodeSolution> _solutions;
    private static final Logger _LOGGER = Logger.getLogger(SolutionRunner.class.getName());

    static {
        TreeMap<Integer, LeetcodeSolution> _solutions = new TreeMap<>(/*Comparator.comparingInt()*/);
        _LOGGER.setLevel(Level.FINEST);
    }

    private static boolean runAll() {
        if (_solutions.isEmpty()) {
            return false;
        }

        for (var solutionEntry : _solutions.entrySet()) {
            var solution = solutionEntry.getValue();
            _LOGGER.log(Level.FINE, "Running {0}", solution.toString());
            solution.run();
        }
        return true;
    }

    public static void main(String[] args) {
        _LOGGER.log(Level.FINE, "Started with args: {0}", Arrays.toString(args));
        boolean solutionFound = false;
        for (var i = 0; i < args.length; ++i) {
            if (args[i].contains("--runAll")) {
                runAll();
                solutionFound = true;
                break;
            } else if (args[i].contains("--run=")) {
                var solutionToRun = Integer.parseInt(args[i].substring(5));
                // TODO: run the specified solution
                var solutionEntry = _solutions.ceilingEntry(solutionToRun); // !!
                if (solutionEntry != null) {
                    var solution = solutionEntry.getValue();
                    _LOGGER.log(Level.FINE, "Running ", solution.toString());
                    solution.run();
                    solutionFound = true;
                    break;
                }
            }
        }
        if (!solutionFound) {
            _LOGGER.log(Level.FINE, "No such problem to solve");
        }
    }

    public static void registerSolution(LeetcodeSolution solution) {
        if (solution == null) {
            return;
        }
        _solutions.put(solution.getSolutionID(), solution);
    }
}

