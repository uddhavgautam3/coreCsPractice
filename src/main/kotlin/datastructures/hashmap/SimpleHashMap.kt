package datastructures.hashmap

import java.util.*

class SimpleHashMap<K, V> {
    private val bucketArrayOfLinkedList = Array(10) { LinkedList<Entry<K, V>>() }

    fun put(key: K, value: V) {
        val hashCode = key.hashCode()
        val index = (hashCode % bucketArrayOfLinkedList.size + bucketArrayOfLinkedList.size) % bucketArrayOfLinkedList.size
        val bucket = bucketArrayOfLinkedList[index]

        // Check if the key already exists in the bucket
        for (entry in bucket) {
            if (entry.key == key) {
                entry.value = value
                return
            }
        }

        // If the key doesn't exist, add a new entry to the bucket
        bucket.add(Entry(key, value))
    }

    fun get(key: K): V? {
        val hashCode = key.hashCode()
        val index = hashCode % bucketArrayOfLinkedList.size
        val bucket = bucketArrayOfLinkedList[index]

        // Search for the key in the bucket using a while-loop
        var current = bucket.first
        while (current != null) {
            if (current.key == key) {
                return current.value
            }
            current = current.next
        }

        return null
    }

    private data class Entry<K, V>(val key: K, var value: V, var next: Entry<K, V>? = null)
}

fun main() {
    val map = SimpleHashMap<String, Int>()

    map.put("apple", 1)
    map.put("banana", 2)
    map.put("cat", 3)
    map.put("dog", 4)
    map.put("elephant", 5)

    // Add a key that collides with "cat"
    map.put("tac", 6)

    println("Value for 'cat': ${map.get("cat")}") // Output: Value for 'cat': 3
    println("Value for 'tac': ${map.get("tac")}") // Output: Value for 'tac': 6
}
