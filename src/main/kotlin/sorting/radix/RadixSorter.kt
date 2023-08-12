package sorting.radix

class RadixSorter {

    fun radixSort(arr: IntArray): IntArray {
        if (arr.isEmpty()) return arr

        // Find the maximum number to determine the number of digits
        val maxNumber = arr.maxOrNull() ?: 0
        var exponent = 1

        // Perform counting sort for each digit (from the least significant digit to the most significant digit)
        //in each pass, it puts LSD in the correct bucket/bin (bin of size 10 always)
        while (maxNumber / exponent > 0) {
            countingSortByDigit(arr, exponent)
            exponent *= 10
        }

        return arr
    }

    private fun countingSortByDigit(arr: IntArray, exponent: Int) {
        val n = arr.size
        val outputArr = IntArray(n)
        val tenBins = IntArray(10)

        for (i in 0 until n) {
            val digit = (arr[i] / exponent) % 10 //gives LSD in each pass
            tenBins[digit]++ //if LSD 4 then 5th bin (index 4 bin) increments by 1
        }

        for (i in 1 until 10) {
            tenBins[i] += tenBins[i - 1]
        }

        for (i in n - 1 downTo 0) {
            val digit = (arr[i] / exponent) % 10
            //-1 because index starts from 0
            //tenBins[digit] is a count like 5, 4, 3
            outputArr[tenBins[digit] - 1] = arr[i]
            tenBins[digit]-- //reduce count by 1 each time
        }

        for (i in 0 until n) {
            arr[i] = outputArr[i]
        }
    }

}

fun main() {
    val numbers = intArrayOf(11111, 1111, 111, 11, 1)
    println("Original array: ${numbers.contentToString()}")

    val sorter = RadixSorter()
    val sortedArray = sorter.radixSort(numbers)

    println("Sorted array: ${sortedArray.contentToString()}")
}
