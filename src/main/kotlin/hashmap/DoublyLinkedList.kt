package hashmap

class Node<T>(val value: T, var prev: Node<T>? = null, var next: Node<T>? = null)

class DoublyLinkedList<T> {
    var head: Node<T>? = null
    private var tail: Node<T>? = null

    fun insertAtBeginning(data: T) {
        val newNode = Node(data)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            head?.prev = newNode
            head = newNode
        }
    }

    fun insertAtEnd(data: T) {
        val newNode = Node(data)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            newNode.prev = tail
            tail?.next = newNode
            tail = newNode
        }
    }

    fun insertAfterNode(prevNode: Node<T>?, data: T) {
        if (prevNode == null) {
            println("Previous node is required.")
            return
        }
        val newNode = Node(data)
        newNode.next = prevNode.next
        prevNode.next = newNode
        newNode.prev = prevNode
        newNode.next?.prev = newNode
    }

    fun insertAfterNodeWithValue(node: Node<T>, data: T) {
        var current = head
        while (current != null) {
            if (current.value == node.value) {
                val newNode = Node(data)
                newNode.prev = current
                newNode.next = current.next

                current.next?.prev = newNode
                current.next = newNode

                return
            }
            current = current.next
        }
        println("Node with value ${node.value} not found in the list.")
    }

    fun printList() {
        var current = head
        while (current != null) {
            print("${current.value} <-> ")
            current = current.next
        }
        println("null")
    }
}

fun main() {
    val list = DoublyLinkedList<Int>()

    list.insertAtBeginning(10)
    list.insertAtBeginning(5)

    list.insertAtEnd(20)
    list.insertAtEnd(25)

    // Insert after the first node (5)
    val firstNode = list.getFirstNode()
    if (firstNode != null) {
        list.insertAfterNode(firstNode, 15)
    }


    list.insertAfterNodeWithValue(Node(10), 20) // Insert after head (empty list)
    list.insertAfterNodeWithValue(Node(10), 30) // Insert after the first occurrence of 10
    list.insertAfterNodeWithValue(Node(30), 25) // Insert after the first occurrence of 30
    list.insertAfterNodeWithValue(Node(24), 40) // Try to insert after the first occurrence of 24 (not found)

    list.printList()
}

// Helper function to get the first node of the list
fun <T> DoublyLinkedList<T>.getFirstNode(): Node<T>? {
    return head
}
