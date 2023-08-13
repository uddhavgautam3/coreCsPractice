package datastructures.linkedlist

class CircularLinkedList<T> {
    class Node<T>(val value: T, var next: Node<T>? = null)

    private var head: Node<T>? = null

    fun add(value: T) {
        val newNode = Node(value)
        if (head == null) {
            head = newNode
            head?.next = head
        } else {
            var lastNode = head!!.next
            while (lastNode?.next != head) {
                lastNode?.next?.let { lastNode = it }
            }
            lastNode?.next = newNode
            newNode.next = head
        }
    }

    fun findLoopStart(): Node<T>? {
        var tortoise = head
        var hare = head

        // Move hare twice as fast as the tortoise, this using two pointers is also called runner technique
        while (hare?.next != null && hare.next?.next != null) {
            tortoise = tortoise?.next
            hare = hare.next?.next

            // If tortoise and hare meet, there is a loop
            if (tortoise == hare) {
                break
            }
        }

        // No loop found
        if (hare?.next == null || hare.next?.next == null) {
            return null
        }

        // Move tortoise to the head
        tortoise = head

        // Move tortoise and hare at the same pace; they will meet at the loop start
        while (tortoise != hare) {
            tortoise = tortoise?.next
            hare = hare?.next
        }

        return tortoise
    }

    fun printList() {
        if (head == null) {
            println("List is empty.")
            return
        }

        var current = head
        do {
            print("${current!!.value} -> ")
            current = current.next
        } while (current != head)
        println("...")
    }
}

fun main() {
    val circularList = CircularLinkedList<Int>()
    circularList.add(10)
    circularList.add(20)
    circularList.add(30)
    circularList.add(40)

    circularList.add(30)

    circularList.printList()

    val loopStartNode = circularList.findLoopStart()

    if (loopStartNode != null) {
        println("Loop starts at node with value: ${loopStartNode.value}")
    } else {
        println("No loop found.")
    }
}

