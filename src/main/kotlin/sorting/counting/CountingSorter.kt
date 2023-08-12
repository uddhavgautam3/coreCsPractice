package sorting.counting

class CountingSorter {
    fun countingSort(arr: IntArray): IntArray {
        if (arr.isEmpty()) return arr

        // Find the maximum number to determine the size of the count array
        val maxNumber = arr.maxOrNull() ?: 0
        val countArraySize = maxNumber + 1

        // Initialize the count array with all elements as 0
        val countArray = IntArray(countArraySize) { 0 }

        // Count the occurrences of each element in the input array
        for (num in arr) {
            countArray[num]++
        }

        // Update the count array to store the actual position of each element in the sorted array
        for (i in 1 until countArraySize) {
            countArray[i] += countArray[i - 1]
        }

        // Build the output array using the count array
        val sortedArray = IntArray(arr.size)
        for (num in arr) {
            val position = countArray[num] - 1
            sortedArray[position] = num
            countArray[num]--
        }

        return sortedArray
    }

}

fun main() {
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${numbers.contentToString()}")

    val sorter = CountingSorter()
    val sortedArray = sorter.countingSort(numbers)

    println("Sorted array: ${sortedArray.contentToString()}")
}
