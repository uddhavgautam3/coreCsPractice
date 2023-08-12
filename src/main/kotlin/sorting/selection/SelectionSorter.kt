package sorting.selection

class SelectionSorter {

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    //every time I get one smallest element, which I put in the beginning and then next for loop from next element
    fun selectionSort(arr: IntArray): IntArray {
        val n = arr.size
        //always good practice not to modify the original arr
        val sortedArr = arr.copyOf()

        //we don't need to take care the very last element because it doesn't need to sort because
        // all the elements before the very last are already sorted means very last is already in correct position
        for (i in 0 until n - 1) {
            var minIndex = i
            for (j in i + 1 until n) { //the last element is at n-1 index
                if (sortedArr[j] < sortedArr[minIndex]) {
                    minIndex = j
                }
            }
            if (minIndex != i) {
                // Swap elements at indices i and minIndex
                sortedArr.swap(i, minIndex)
            }
        }

        return sortedArr
    }
}

fun main() {
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${numbers.contentToString()}")

    val sorter = SelectionSorter()
    val sortedArray = sorter.selectionSort(numbers)

    println("Sorted array: ${sortedArray.contentToString()}")
}
