package sorting.quick

import java.util.*

class QuickSorter {
    fun quickSort(arr: IntArray): IntArray {
        val sortedArr = arr.copyOfRange(0, arr.size)
        quickSort(sortedArr, 0, sortedArr.size - 1)
        return sortedArr
    }

    private fun quickSort(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pivotIndex = medianOfThree(arr, low, high)
            quickSort(arr, low, pivotIndex - 1)
            quickSort(arr, pivotIndex + 1, high)
        }
    }

    private fun randomizedPartition(arr: IntArray, low: Int, high: Int): Int {
        // Choose a random pivot element and swap it with the rightmost element
        val randomIndex = Random().nextInt(high - low + 1) + low
        swap(arr, randomIndex, high)

        // Continue with the same partition function as before
        val pivot: Int = arr[high]
        var i = low - 1

        for (j in low until high) {
            if (arr[j] <= pivot) {
                i++
                swap(arr, i, j)
            }
        }

        swap(arr, i + 1, high)
        return i + 1
    }

    private fun medianOfThree(arr: IntArray, low: Int, high: Int): Int {
        val mid = (low + high) / 2

        // Find the median among the first, middle, and last elements
        if (arr[low] > arr[mid]) swap(arr, low, mid)
        if (arr[low] > arr[high]) swap(arr, low, high)
        if (arr[mid] > arr[high]) swap(arr, mid, high)

        // Move the pivot (median) element to the rightmost position (high)
        swap(arr, mid, high)

        // Continue with the same partition function as before
        val pivot: Int = arr[high]
        var i = low - 1

        for (j in low until high) {
            if (arr[j] <= pivot) {
                i++
                swap(arr, i, j)
            }
        }

        swap(arr, i + 1, high)
        return i + 1
    }

    private fun partition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1

        for (j in low until high) {
            if (arr[j] <= pivot) {
                i++
                swap(arr, i, j)
            }
        }

        swap(arr, i + 1, high)
        return i + 1
    }

    private fun swap(arr: IntArray, i: Int, j: Int) {
        val temp = arr[i]
        arr[i] = arr[j]
        arr[j] = temp
    }

}

fun main() {
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${numbers.contentToString()}")

    val sorter = QuickSorter()
    val sortedArray = sorter.quickSort(numbers)

    println("Sorted array: ${sortedArray.contentToString()}")
}
