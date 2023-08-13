package datastructures.linkedlist

/*
Deleting a node from a linked list is fairly straightforward. Given a node n,
we find the previous node prev and set prev.next equal to n.next.
If the list is doubly linked, we must also update n.next to set n.next.prev equal to n.prev.
The important things to remember are (1) to check for the null pointer and
(2) to update the head or tail pointer as necessary.
 */
class SinglyLinkedList<T> {
    class Node<T>(val value: T, var next: Node<T>? = null)

    private var head: Node<T>? = null

    fun delete(value: T) {
        //if it's node
        if (head?.value == value) {
            //when head goes to next node and previous head is detached means linkedlist head is deleted
            head = head?.next //make head equals to head?.next
            return
        }

        var current = head //current starts from first node
        var prev: Node<T>? = null

        current?.let {
            while (current?.value != value) {
                prev = current //from prev to I am gonna go to next because the item to delete not matched
                current = current?.next
            }
            //when current.value = value
            //we skip the current by removing it from
            prev?.next = current?.next
        }

    }

    fun add(value: T) {
        val newNode = Node(value)
        if (head == null) {
            head = newNode
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newNode
        }
    }

    fun findFirstDuplicateNode(): Node<T>? {
        var current = head
        while (current != null) {
            var runner = current.next
            while (runner != null) {
                if (current.value == runner.value) {
                    return current
                }
                runner = runner.next
            }
            current = current.next
        }
        return null
    }

    fun printList() {
        var current = head
        while (current != null) {
            print("${current.value} -> ")
            current = current.next
        }
        println("null")
    }
}

fun main() {
   /* val singlyLinkedList = SinglyLinkedList<Int>()
    singlyLinkedList.add(10)
    singlyLinkedList.add(20)
    singlyLinkedList.add(30)

    singlyLinkedList.printList()*/

//    singlyLinkedList.delete(20)
//    singlyLinkedList.printList()


    val list = SinglyLinkedList<Int>()

    // Add nodes with values
    list.add(10)
    list.add(20)
    list.add(30)
    list.add(40)
    list.add(20) // Add a duplicate node

    val duplicateNode = list.findFirstDuplicateNode()
    if (duplicateNode != null) {
        println("First duplicate node found with value: ${duplicateNode.value}")
    } else {
        println("No duplicate node found.")
    }
}

