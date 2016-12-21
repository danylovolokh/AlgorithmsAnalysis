package com.algorithms.coursera

import java.io.File
import java.util.*

/**
 * This is a Quick Sort. It works with O(n log(n)) on average if pivots are chosen randomly.
 *
 * 1. We find the pivot element: in our example it's a first/last element.
 * But it should be chosen randomly to get O(n log(n))
 *
 * 2. We need to partition the array. Partitioning means moving all the elements that are bigger/smaller
 * than the pivot to the right/left part of the pivot in the array.
 *
 *
 *   |
 *   v
 * [ 3, 2, 1, 6, 5, 8]
 *
 *
 *  Array after first partitioning. All the elements that are less than the pivot are situated to the left of the pivot.
 *         |
 *         v
 * [ 2, 1, 3, 6, 5, 8]
 *
 *
 * Array after second partitioning.
 *      |
 *      v
 * [ 1, 2, 3, 6, 5, 8]
 *
 *
 */

fun main(args: Array<String>){


    val intArray: IntArray = createInputArray()

    quickSort(intArray, 0, intArray.size - 1)

    println("\nresult")
    for (index in 0..intArray.size - 1) {

        print(" " + intArray[index] + " ")
    }

}

fun quickSort(intArray: IntArray, from: Int, to: Int) {
    println("quickSort from $from, to $to")

    // base case
    if (from < to){

        var pivot = partitionAroundPivotFirstElement(intArray, from, to)
        println("quickSort pivot " + pivot)

        quickSort(intArray, from, pivot - 1)

        quickSort(intArray, pivot + 1, to)
    }

}

/**
 * intArray = [3, 1, 2, 6, 7, 8]
 */
fun partitionAroundPivotFirstElement(intArray: IntArray, leftMostIndex: Int, rightMostIndex: Int): Int{
    var pivot = intArray[leftMostIndex]

    /**
     * "i" is a divider between items smaller than the pivot and bigger than the pivot.
     * Example: pivot == 3
     *
     * partitioned array = [
     * "pivot" = 3,
     *              1,
     *                  "i" = 2,
     *                      6,
     *                          7,
     *                              8
     * ]
     *
     */

    var i = leftMostIndex + 1

    // iterate over the array and swap necessary values
    for (j in leftMostIndex + 1.. rightMostIndex){

        if(intArray[j] < pivot){
            // swap j-th element with the left most element bigger than the pivot
            swapArrayElements(i, intArray, j)
            i++
        }
    }

    // swap the pivot with the biggest element of the elements that are less than the pivot.
    swapArrayElements(leftMostIndex, intArray, i - 1)

    // return the pivot
    return i - 1
}

fun partitionAroundPivotLastElement(intArray: IntArray, leftMostIndex: Int, rightMostIndex: Int): Int{
    var pivot = intArray[rightMostIndex]

    /**
     * "i" is a divider between items smaller than the pivot and bigger than the pivot.
     * Example: pivot == 3
     *
     * partitioned array = ["pivot" = 3, 1, "i" = 2, 6, 7, 8]
     *
     */

    var i = leftMostIndex

    // iterate over the array and swap necessary values
    for (j in leftMostIndex.. rightMostIndex - 1){

        if(intArray[j] < pivot){
            // swap j-th element with the left most element bigger than the pivot
            swapArrayElements(i, intArray, j)
            i++
        }
    }

    // swap the pivot with the biggest element of the elements that are less than the pivot.
    swapArrayElements(rightMostIndex, intArray, i)
    return i
}

private fun swapArrayElements(i: Int, intArray: IntArray, j: Int) {
    var temp = intArray[j]
    intArray[j] = intArray[i]
    intArray[i] = temp
}

private fun createInputArray(): IntArray {
    val scanner = Scanner(File("quick_sort_input.txt"))
    val numbers = IntArray(10000)
    var i = 0
    while (scanner.hasNextInt()) {
        numbers[i++] = scanner.nextInt()
        println("index= " + i + ", value =" + numbers[i-1])
    }

    return numbers
}