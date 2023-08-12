package sorting.bucket

class BucketSorter {
        fun bucketSort(arr: IntArray): IntArray {
            if (arr.isEmpty()) return arr
            val bucketCount = 10 // The number of buckets, can be adjusted as needed

            // Step 1: Create an array of empty buckets
            val buckets = Array(bucketCount) { mutableListOf<Int>() }

            // Step 2: Place elements into buckets based on their value range
            val maxValue = arr.maxOrNull() ?: 0
            for (num in arr) {
                val bucketIndex = (num * bucketCount / (maxValue + 1)).toInt()
                buckets[bucketIndex].add(num)
            }

            // Step 3: Sort elements within each bucket (using a sub-sorting algorithm)
            val sortedArray = mutableListOf<Int>()
            for (bucket in buckets) {
                insertionSort(bucket)
                sortedArray.addAll(bucket)
            }

            return sortedArray.toIntArray()
        }

        private fun insertionSort(arr: MutableList<Int>) {
            for (i in 1 until arr.size) {
                val key: Int = arr[i]
                var j = i - 1

                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j]
                    j--
                }

                arr[j + 1] = key
            }
        }
}

fun main() {
    val numbers = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${numbers.contentToString()}")

    val sorter = BucketSorter()
    val sortedArray = sorter.bucketSort(numbers)

    println("Sorted array: ${sortedArray.contentToString()}")
}

