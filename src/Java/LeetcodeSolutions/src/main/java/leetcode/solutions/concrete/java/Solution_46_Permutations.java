package leetcode.solutions.concrete.java;

import leetcode.solutions.*;
import leetcode.solutions.annotations.ProblemInputData;
import leetcode.solutions.annotations.ProblemSolution;
import leetcode.solutions.annotations.ProblemSolutionData;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

import static leetcode.solutions.ProblemDifficulty.*;
import static leetcode.solutions.SolutionValidator.*;
import static leetcode.solutions.Complexity.*;

// To solve this one we will use recursion tree

public final class Solution_46_Permutations extends LeetcodeSolution {

    public Solution_46_Permutations() { super(MEDIUM); resolveConcreteSolutionInfo(this); }

    private @ProblemSolutionData ArrayList<List<Integer>> _result = new ArrayList<>();

    @ProblemSolution(complexity = O_NFactorial)
    private void backtrack(@NotNull List<Integer> nums, int index) {
        if (index == nums.size()) {
            _result.add(nums);
            return;
        }
        for (var i = index; i < nums.size(); ++i) {
            Collections.swap(nums, index, i);
            if (nums.equals(Arrays.asList(1,3,2))) {
                int breakpoint = 0;
            }
            backtrack(nums, index + 1);
            Collections.swap(nums, index, i);
        }
    }

    @ProblemSolution
    private @NotNull List<List<Integer>> permute(int[] nums) {
        backtrack(Arrays.stream(nums).boxed().collect(Collectors.toList()), 0);
        return _result;
    }

    @Override
    @ProblemInputData
    public void run() {
        final var nums = new int[] {1,2,3};
        ArrayList<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(1,2,3));
        expected.add(Arrays.asList(1,3,2));
        expected.add(Arrays.asList(2,1,3));
        expected.add(Arrays.asList(2,3,1));
        expected.add(Arrays.asList(3,1,2));
        expected.add(Arrays.asList(3,2,1));
        ASSERT_TRUE(expected.equals(permute(nums)));
    }
}