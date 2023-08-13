package datastructures.queue

class Queue<T> {
    class Node<T>(val value: T, var next: Node<T>? = null)

    //two pointers
    private var front: Node<T>? = null
    private var rear: Node<T>? = null

    fun enqueue(value: T) {
        val newNode = Node(value)
        if (isEmpty()) {
            front = newNode
            rear = newNode
        } else {
            rear?.next = newNode //next points the new one node_before_rear -> rear -> newNode
            rear = newNode //now, after enqueue newNode is our rear
        }
    }

    fun dequeue(): T? {
        if (isEmpty()) {
            println("Queue is empty.")
            return null
        }
        val value = front?.value
        front = front?.next //update the front to previous front
        if (front == null) { //newly updated front should not be null
            // If the queue becomes empty, update rear to null as well
            rear = null
        }
        return value
    }

    fun peek(): T? {
        return front?.value
    }

    private fun isEmpty(): Boolean {
        return front == null
    }

    fun printQueue() {
        var current = front
        while (current != null) {
            println(current.value)
            current = current.next
        }
    }
}

fun main() {
    val queue = Queue<Int>()

    queue.enqueue(10)
    queue.enqueue(20)
    queue.enqueue(30)

    println("Peek: ${queue.peek()}") // Output: Peek: 10

    println("Dequeued: ${queue.dequeue()}") // Output: Dequeued: 10
    println("Dequeued: ${queue.dequeue()}") // Output: Dequeued: 20

    queue.printQueue()
}

