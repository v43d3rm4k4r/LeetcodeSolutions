package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*;
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.SolutionValidator.*
import leetcode.solutions.Complexity.*
import java.util.LinkedList

// TODO

class Solution_66_Plus_One : LeetcodeSolution(EASY) {
    init { resolveConcreteSolutionInfo(this) }

    // TODO: II solution - convert array to Int and vice versa

    @ProblemSolution(complexity = O_N)
    private fun plusOne(digits: IntArray): IntArray {
        var result = digits.toCollection(LinkedList<Int>())
        var needToIncrement = true
        var i = digits.size - 1

        //for (i in result.indices.reversed()) {
        while (needToIncrement) {
            if (result[i] == 9) {
                result[i] = 0
            }
            if (result[i] < 9) {
                ++result[i]
                needToIncrement = false
            }
            --i
        }
        return result.toIntArray()
    }

    @ProblemInputData
    override fun run() {
    }
}