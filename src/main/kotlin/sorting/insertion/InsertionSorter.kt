package sorting.insertion

class InsertionSorter {
    fun insertionSort(arr: IntArray): IntArray {
        val sortedArr = arr.copyOf()
        val n = sortedArr.size
        //compare from 2nd index and keep comparing all the way to index 0, at then very last, the key should be inserted in right position
        for (i in 1 until n) {
            val key = sortedArr[i]
            var j = i - 1

            // Move elements of arr[0..i-1] that are greater than key to one position ahead of their current position
            while (j >= 0 && sortedArr[j] > key) {
                sortedArr[j + 1] = sortedArr[j]
                j--
            }
            sortedArr[j + 1] = key
        }
        return sortedArr
    }

}

fun main() {
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${numbers.contentToString()}")

    val sorter = InsertionSorter()
    val sortedArray = sorter.insertionSort(numbers)

    println("Sorted array: ${sortedArray.contentToString()}")
}
