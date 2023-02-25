package leetcode.solutions.concrete.kotlin

import leetcode.solutions.*
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.validation.SolutionValidator.*
import leetcode.solutions.complexity.Complexity.*
import leetcode.solutions.annotations.ProblemInputData
import leetcode.solutions.annotations.ProblemSolution

/**
 * __Problem:__ Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the
 * following rules:

- Each row must contain the digits `1-9` without repetition.
- Each column must contain the digits `1-9` without repetition.
- Each of the nine `3 x 3` sub-boxes of the grid must contain the digits `1-9` without repetition.

 *
 * __Constraints:__
 * - `board.length` == 9
 * - `board[i].length` == 9
 * - `board[i][j]` is a digit `1-9` or `'.'`
 *
 * Note:

- A Sudoku board (partially filled) could be valid but is not necessarily solvable.
- Only the filled cells need to be validated according to the mentioned rules.

 * __Solution:__ Use a `HashSet` to write the properties of each cell. This allows you to check the validity of the
 * field in just one pass. When using strings, you need to be aware of collisions, so the patterns for each
 * property are different.
 *
 * __Time:__ O(N)
 *
 * __Space:__ O(N)
 *
 *
 * @author Daniil Kupriyanov
 */

class Solution_36_Valid_Sudoku : LeetcodeSolution(MEDIUM) {

    @ProblemSolution(timeComplexity = O_N, spaceComplexity = O_N)
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val seen = HashSet<String>()
        for ((rowIdx, row) in board.withIndex()) {
            for ((colIdx, value) in row.withIndex()) {
                if (value == '.') continue
                if (!seen.add("$rowIdx($value)") ||
                    !seen.add("($value)$colIdx") ||
                    !seen.add("($value)${rowIdx / 3}-${colIdx / 3}")) {
                    return false
                }
            }
        }
        return true
    }

    @ProblemInputData
    override fun run() {
        val board = arrayOf(
            charArrayOf('5','3','.','.','7','.','.','.','.'),
            charArrayOf('6','.','.','1','9','5','.','.','.'),
            charArrayOf('.','9','8','.','.','.','.','6','.'),
            charArrayOf('8','.','.','.','6','.','.','.','3'),
            charArrayOf('4','.','.','8','.','3','.','.','1'),
            charArrayOf('7','.','.','.','2','.','.','.','6'),
            charArrayOf('.','6','.','.','.','.','2','8','.'),
            charArrayOf('.','.','.','4','1','9','.','.','5'),
            charArrayOf('.','.','.','.','8','.','.','7','9')
        )
        ASSERT_EQ(true, isValidSudoku(board))

        board[0][0] = '8'
        ASSERT_EQ(false, isValidSudoku(board))
    }
}