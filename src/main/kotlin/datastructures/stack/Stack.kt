package datastructures.stack

class Node<T>(val value: T, var next: Node<T>? = null)

class Stack<T> {
    private var top: Node<T>? = null

    fun push(value: T) {
        val newNode = Node(value)
        newNode.next = top //the previous to becomes one step below
        top = newNode //and newly added newNode becomes top
    }

    fun pop(): T? {
        if (isEmpty()) {
            println("Stack is empty.")
            return null
        }
        val value = top?.value
        top = top?.next
        return value
    }

    fun peek(): T? {
        return top?.value
    }

    private fun isEmpty(): Boolean {
        return top == null
    }

    fun printStack() {
        var current = top
        while (current != null) {
            println(current.value)
            current = current.next
        }
    }
}

fun main() {
    val stack = Stack<Int>()

    stack.push(10)
    stack.push(20)
    stack.push(30)

    println("Peek: ${stack.peek()}") // Output: Peek: 30

    println("Popped: ${stack.pop()}") // Output: Popped: 30
    println("Popped: ${stack.pop()}") // Output: Popped: 20

    stack.printStack()
}
