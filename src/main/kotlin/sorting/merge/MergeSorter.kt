package sorting.merge

class MergeSorter {
    fun mergeSort(arr: IntArray): IntArray {
        // Base case: If the array has one or zero elements, it is already sorted
        if (arr.size <= 1) {
            return arr
        }

        // Split the array into two halves
        val middle = arr.size / 2
        val left = arr.copyOfRange(0, middle)
        val right = arr.copyOfRange(middle, arr.size)

        // Recursively sort the two halves
        val sortedLeft = mergeSort(left)
        val sortedRight = mergeSort(right)

        // Merge the two sorted halves
        return merge(sortedLeft, sortedRight)
    }

    private fun merge(left: IntArray, right: IntArray): IntArray {
        val mergedArray = IntArray(left.size + right.size)

        var leftIndex = 0
        var rightIndex = 0
        var mergedIndex = 0

        // Merge the two arrays in sorted order
        while (leftIndex < left.size && rightIndex < right.size) {
            if (left[leftIndex] <= right[rightIndex]) {
                mergedArray[mergedIndex] = left[leftIndex]
                leftIndex++
            } else {
                mergedArray[mergedIndex] = right[rightIndex]
                rightIndex++
            }
            mergedIndex++
        }

        // Add any remaining elements from the left and right arrays
        while (leftIndex < left.size) {
            mergedArray[mergedIndex] = left[leftIndex]
            leftIndex++
            mergedIndex++
        }

        while (rightIndex < right.size) {
            mergedArray[mergedIndex] = right[rightIndex]
            rightIndex++
            mergedIndex++
        }

        return mergedArray
    }

}

fun main() {
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${numbers.contentToString()}")

    val sorter = MergeSorter()
    val sortedArray = sorter.mergeSort(numbers)

    println("Sorted array: ${sortedArray.contentToString()}")
}
