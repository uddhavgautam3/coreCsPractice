package sorting.heap

class HeapSorter {
    fun heapSort(arr: IntArray): IntArray {
        val n = arr.size
        val sortedArray = arr.copyOf()

        // Build a max heap
        for (i in (n / 2 - 1) downTo 0) {
            heapify(sortedArray, n, i)
        }

        // Extract elements from the heap one by one
        for (i in (n - 1) downTo 0) {
            // Move the current root (max element) to the end
            val temp = sortedArray[0]
            sortedArray[0] = sortedArray[i]
            sortedArray[i] = temp

            // Call heapify on the reduced heap
            heapify(sortedArray, i, 0)
        }
        return sortedArray
    }

    private fun heapify(arr: IntArray, n: Int, root: Int) {
        var largest = root
        val left = 2 * root + 1
        val right = 2 * root + 2

        // Find the largest among the root, left child, and right child
        if (left < n && arr[left] > arr[largest]) {
            largest = left
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right
        }

        // If the largest is not the root, swap and heapify the affected subtree
        if (largest != root) {
            val temp = arr[root]
            arr[root] = arr[largest]
            arr[largest] = temp

            heapify(arr, n, largest)
        }
    }

}

fun main() {
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${numbers.contentToString()}")

    val sorter = HeapSorter()
    val sortedArray = sorter.heapSort(numbers)
    println("Sorted array: ${sortedArray.contentToString()}")
}
