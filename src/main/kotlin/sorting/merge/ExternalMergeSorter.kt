package sorting.merge

import java.io.*
import java.nio.file.Paths
import java.util.*

class ExternalMergeSorter {
    private val chunkSize = 100 // Adjust the chunk size as per available memory

    fun externalMergeSort(inputFile: String, outputFile: String) {
        val chunks = mutableListOf<String>()

        // Step 1: Divide Phase - Read data, create sorted chunks, and write them to temporary files
        BufferedReader(FileReader(inputFile)).use { br ->
            val chunk = mutableListOf<String>()
            var line: String?

            while (br.readLine().also { line = it } != null) {
                chunk.add(line!!)
                if (chunk.size >= chunkSize) {
                    chunk.sort()
                    val tempFileName = "chunk_${chunks.size}.tmp"
                    writeChunkToFile(chunk, tempFileName)
                    chunks.add(tempFileName)
                    chunk.clear()
                }
            }

            if (chunk.isNotEmpty()) {
                chunk.sort()
                val tempFileName = "chunk_${chunks.size}.tmp"
                writeChunkToFile(chunk, tempFileName)
                chunks.add(tempFileName)
            }
        }

        // Step 2: Merge Phase - Merge the sorted chunks using a min-heap
        val outputWriter = BufferedWriter(FileWriter(outputFile))
        val minHeap = PriorityQueue<BufferedReader> { br1, br2 ->
            br1.readLine().compareTo(br2.readLine())
        }

        chunks.forEach { chunkFileName ->
            val br = BufferedReader(FileReader(chunkFileName))
            minHeap.offer(br)
        }

        while (minHeap.isNotEmpty()) {
            val br = minHeap.poll()
            val line = br.readLine()
            if (line != null) {
                outputWriter.write(line)
                outputWriter.newLine()
                minHeap.offer(br)
            } else {
                br.close()
            }
        }

        outputWriter.close()

        // Clean up temporary files
        chunks.forEach { chunkFileName ->
            File(chunkFileName).delete()
        }
    }

    private fun writeChunkToFile(chunk: List<String>, fileName: String) {
        BufferedWriter(FileWriter(fileName)).use { bw ->
            chunk.forEach { line ->
                bw.write(line)
                bw.newLine()
            }
        }
    }
}

fun main() {
    val inputFile = Paths.get("${System.getProperty("user.dir")}/src/main/kotlin/sorting/merge/input.txt").toString()
    val outputFile = Paths.get("${System.getProperty("user.dir")}/src/main/kotlin/sorting/merge/output.txt").toString()

    val sorter = ExternalMergeSorter()
    sorter.externalMergeSort(inputFile, outputFile)

    println("External Merge Sort completed. Output written to $outputFile.")
}

