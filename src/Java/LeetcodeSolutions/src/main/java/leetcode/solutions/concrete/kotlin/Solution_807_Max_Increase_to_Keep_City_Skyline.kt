package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.validation.SolutionValidator.*
import leetcode.solutions.annotations.ProblemInputData
import leetcode.solutions.annotations.ProblemSolution

/**
 * __Problem:__ There is a city composed of `n x n` blocks, where each block contains a single building
 * shaped like a vertical square prism. You are given a __0-indexed__ `n x n` integer matrix `grid`
 * where `grid[r][c]` represents the __height__ of the building located in the block at row `r` and column `c`.
 *
 * A city's __skyline__ is the outer contour formed by all the building when viewing
 * the side of the city from a distance. The __skyline__ from each cardinal direction
 * north, east, south, and west may be different.
 *
 * We are allowed to increase the height of __any number of buildings by any amount__ (the amount can be
 * different per building). The height of a `0`-height building can also be increased. However,
 * increasing the height of a building should __not__ affect the city's __skyline__ from any cardinal direction.
 *
 * Return _the __maximum total sum__ that the height of the buildings can be increased by __without__ changing
 * the city's __skyline__ from any cardinal direction_.
 *
 * __Constraints:__
 * - `n == grid.length`
 * - `n == grid[r].length`
 * - `2 <= n <= 50`
 * - `0 <= grid[r][c] <= 100`
 *
 * __Solution:__ Each of the "buildings" can be increased by a value equal to the minimum value of the
 * two maximum values on each axis. To find these values for each cell, you first need to do two actions:
 * 1. Find the maximum values for each row and column
 * 2. Find the minimum of them for each cell, and subtract the initial height of the cell from it
 *
 * __Time:__ O(N^2)
 *
 * __Space:__ O(N)
 *
 * @author Daniil Kupriyanov
 */

class Solution_807_Max_Increase_to_Keep_City_Skyline : LeetcodeSolution(MEDIUM) {

    @ProblemSolution(timeComplexity = "O(N^2)", spaceComplexity = "O(N)")
    fun maxIncreaseKeepingSkyline(grid: Array<IntArray>): Int {
        val rowMaxes = Array(grid.size) { 0 }
        val colMaxes = Array(grid.size) { 0 }
        var result = 0

        for (row in grid.indices) {
            for (col in grid.indices) {
                rowMaxes[row] = grid[row][col].coerceAtLeast(rowMaxes[row])
                colMaxes[col] = grid[row][col].coerceAtLeast(colMaxes[col])
            }
        }
        for (row in grid.indices) {
            for (col in grid.indices) {
                result += rowMaxes[row].coerceAtMost(colMaxes[col]) - grid[row][col]
            }
        }
        return result
    }

    @ProblemInputData
    override fun run() {
        val input = arrayOf(
            intArrayOf(3, 0, 8, 4),
            intArrayOf(2, 4, 5, 7),
            intArrayOf(9, 2, 6, 3),
            intArrayOf(0, 3, 1, 0),
        )
        ASSERT_EQ(35, maxIncreaseKeepingSkyline(input))

        val input1 = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
        )
        ASSERT_EQ(0, maxIncreaseKeepingSkyline(input1))
    }
}