package sorting.bubble

class BubbleSorter {

    fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    fun bubbleSort(arr: IntArray): IntArray {
        var n = arr.size
        //always good practice not to modify the original arr
        val sortedArr = arr.copyOf()
        var swapped: Boolean
        do {
            swapped = false
            for (i in 0 until n - 1) {
                if (sortedArr[i] > sortedArr[i + 1]) {
                    // Swap elements at indices i and i + 1
                    val temp = sortedArr[i]
                    sortedArr[i] = sortedArr[i + 1]
                    sortedArr[i + 1] = temp

                    //or sortedArr.swap(i, i+1)
                    swapped = true
                }
            }
            n-- //decrease depth by 1 next time
        } while (swapped) //if no swap ever hapened then exit early
        return sortedArr
    }

    //another variation where the largest goes to the top while smallest goes to the bottom at the same time
    fun cocktailShakerSort(arr: IntArray): IntArray {
        val n = arr.size
        //always good practice not to modify the original arr
        val sortedArr = arr.copyOf()
        var swapped: Boolean
        var start = 0
        var end = n - 1
        do {
            swapped = false
            for (i in start until end) {
                if (sortedArr[i] > sortedArr[i + 1]) {
                    // Swap elements at indices i and i + 1
                    val temp = sortedArr[i]
                    sortedArr[i] = sortedArr[i + 1]
                    sortedArr[i + 1] = temp
                    swapped = true
                }
            }
            end--
            if (!swapped) break
            swapped = false
            for (i in end - 1 downTo start) {
                if (sortedArr[i] > sortedArr[i + 1]) {
                    // Swap elements at indices i and i + 1
                    val temp = sortedArr[i]
                    sortedArr[i] = sortedArr[i + 1]
                    sortedArr[i + 1] = temp
                    swapped = true
                }
            }
            start++
        } while (swapped)
        return sortedArr
    }
}

fun main() {
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${numbers.contentToString()}")

    val sorter = BubbleSorter()
    val sortedArray = sorter.bubbleSort(numbers)

    println("Sorted array: ${sortedArray.contentToString()}")
}



