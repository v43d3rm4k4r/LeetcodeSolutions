package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.validation.SolutionValidator.*
import leetcode.solutions.complexity.Complexity.*
import leetcode.solutions.annotations.ProblemInputData
import leetcode.solutions.annotations.ProblemSolution

/**
 * __Problem:__ Anti-theft security devices are activated inside a bank. You are given a __0-indexed__ binary string
 * array bank representing the floor plan of the bank, which is an `m x n` 2D matrix. `bank[i]` represents the `ith`
 * row, consisting of `'0'`s and `'1'`s. `'0'` means the cell is empty, while `'1'` means the cell has a
 * security device.
 *
 * There is __one__ laser beam between any __two__ security devices __if both__ conditions are met:
 * - The two devices are located on two __different rows__: `r1` and `r2`, where `r1 < r2`.
 * - For __each__ row `i` where `r1 < i < r2`, there are __no security devices__ in the `ith` row.
 *
 * Laser beams are independent, i.e., one beam does not interfere nor join with another.
 *
 * Return _the total number of laser beams in the bank_.
 *
 * __Constraints:__
 * - `m == bank.length`
 * - `n == bank[i].length`
 * - `1 <= m, n <= 500`
 * - `bank[i][j]` is either `'0'` or `'1'`.
 *
 * __Solution :__ Since laser beams correspond to the number of devices in adjacent rows, multiplied by each
 * other, there are quite a few variables in this problem, so space complexity is O(1). It remains only to bypass
 * the matrix to multiply neighboring devices.
 *
 * __Time:__ O(N*M)
 *
 * __Space:__ O(1)
 *
 * @author Daniil Kupriyanov
 */

class Solution_2125_Number_of_Laser_Beams_in_a_Bank : LeetcodeSolution(MEDIUM) {

    @ProblemSolution(timeComplexity = O_NM, spaceComplexity = O_1)
    fun numberOfBeams(bank: Array<String>): Int {
        var lastRowDevices = 0
        var currentRowDevices = 0
        var laserBeams = 0

        for (row in bank) {
            if (currentRowDevices != 0) {
                lastRowDevices = currentRowDevices
                currentRowDevices = 0
            }
            row.forEach { cell ->
                if (cell == '1') {
                    ++currentRowDevices
                }
            }
            if (currentRowDevices != 0)
                laserBeams += lastRowDevices * currentRowDevices
        }
        return laserBeams
    }

    @ProblemInputData
    override fun run() {
        ASSERT_EQ(0, numberOfBeams(arrayOf("000", "111", "000")))
        ASSERT_EQ(8, numberOfBeams(arrayOf("011001", "000000", "010100", "001000")))
    }
}