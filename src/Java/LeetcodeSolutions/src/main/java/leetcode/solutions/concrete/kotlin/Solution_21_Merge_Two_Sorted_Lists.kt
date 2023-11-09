package leetcode.solutions.concrete.kotlin

import leetcode.solutions.LeetcodeSolution
import leetcode.solutions.ProblemDifficulty.*
import leetcode.solutions.annotations.ProblemInputData
import leetcode.solutions.annotations.ProblemSolution
import leetcode.solutions.utils.ListNode
import leetcode.solutions.utils.createList
import leetcode.solutions.validation.SolutionValidator.ASSERT_EQ

/**
 * __Problem:__ You are given the heads of two sorted linked lists `list1` and `list2`.
 * Merge the two lists into one __sorted__ list. The list should be made by splicing together the nodes of the
 * first two lists.
 *
 * Return _the head of the merged linked list_.
 *
 * __Constraints:__
 * - The number of nodes in both lists is in the range [[0, 50]]
 * - `[-100 <= ListNode.val <= 100]`
 * - Both `list1` and `list2` are sorted in __non-decreasing__ order
 *
 * __Solution:__ Create iterators for lists. Compare the values of the elements: if the value of the current iterator
 * of the first list is less than the value of the iterator of the second list, we add it to the new list. Otherwise,
 * we add a node from the second list. At the end, you need to add the last node to the list, which is not null,
 * since it was not added in the loop.
 *
 * __Time:__ O(N)
 *
 * __Space:__ O(1)
 *
 * @author Daniil Kupriyanov
 */

class Solution_21_Merge_Two_Sorted_Lists : LeetcodeSolution(EASY) {

    @ProblemSolution(timeComplexity = "O(N)", spaceComplexity = "O(1)")
    private fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        var currentNode1 = list1
        var currentNode2 = list2
        val result = ListNode(0)
        var currentResult = result

        while (currentNode1 != null && currentNode2 != null) {

            if (currentNode1.`val` < currentNode2.`val`) {
                currentResult.next = currentNode1
                currentNode1 = currentNode1.next
            } else {
                currentResult.next = currentNode2
                currentNode2 = currentNode2.next
            }

            currentResult = currentResult.next!!
        }

        currentNode1?.let { currentResult.next = currentNode1 }
        currentNode2?.let { currentResult.next = currentNode2 }

        return result.next
    }

    @ProblemInputData
    override fun run() {
        ASSERT_EQ(createList(1, 1, 2, 3, 4, 4), mergeTwoLists(createList(1, 2, 4), createList(1, 3, 4)))
        ASSERT_EQ(createList(), mergeTwoLists(createList(), createList()))
        ASSERT_EQ(createList(0), mergeTwoLists(createList(), createList(0)))
    }
}