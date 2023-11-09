package leetcode.solutions.utils

import leetcode.solutions.annotations.ProblemDataStructure

@ProblemDataStructure
data class ListNode(
    var `val`: Int,
    var next: ListNode? = null
)

/**
 * Creates a linked list of a given [size], filling it with a given [value].
 */
fun createList(size: Int, value: Int): ListNode {
    val resultList = ListNode(value)
    var current = resultList
    repeat(size - 1) {
        current.next = ListNode(value)
        current = current.next!!
    }
    return resultList
}

/**
 * Creates a linked list from the provided [nodes].
 */
fun createList(vararg nodes: Int): ListNode? {
    if (nodes.isEmpty()) return null
    val resultList = ListNode(nodes[0])
    var current = resultList
    repeat(nodes.size - 1) {
        current.next = ListNode(nodes[it + 1])
        current = current.next!!
    }
    return resultList
}