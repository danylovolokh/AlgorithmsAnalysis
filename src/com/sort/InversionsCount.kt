package com.sort

import java.io.File
import java.util.*

/**
 * Created by danylo.volokh on 7/28/16.
 *
 * The largest amount of inversions in an array is:
 *
 * n * (n-1) / 2
 *
 * intArrayOf(1, 3, 5, 2, 4, 6)
 */
val inputArray = getArrayFromFile()
val inversionsOutPutArray : IntArray = IntArray(inputArray.size)

fun main(args: Array<String>){
    var splitInversionsCount = sortAndCountInversions(inputArray, 0, inputArray.size - 1)
    println("splitInversionsCount " + splitInversionsCount)

}

fun getArrayFromFile() : IntArray {

    val scanner = Scanner(File("CourseraTask.txt"))
    val numbers = IntArray(100000)
    var i = 0
    while (scanner.hasNextInt()) {
        numbers[i++] = scanner.nextInt()
        println("num " + i + ", " + numbers[i-1])
    }

    return numbers
}

fun sortAndCountInversions(intArray: IntArray, start: Int, end: Int) : Long{

    println(">> sortAndCountInversions")

    println("start $start, end $end")

    if(start < end){

        var middle = (start + end) / 2

        var firstHalfInversionsCount = sortAndCountInversions(intArray, start, middle)
        var secondHalfInversionsCount = sortAndCountInversions(intArray, middle + 1, end)
        var splitInversionsCount = mergeAndCountSplitInversions(intArray, start, middle, end)

        return firstHalfInversionsCount +
                secondHalfInversionsCount +
                splitInversionsCount
    } else {
        // base case
        println("sortAndCountInversions, base count")

        return 0
    }
}


fun mergeAndCountSplitInversions(inputArray: IntArray, start: Int, middle: Int, end: Int) : Long {

    var splitInversionsCount : Long = 0

    println(">> mergeAndCountSplitInversions, start $start, middle $middle, end $end")

    // index of original array
    var index = start

    // index of first array
    var i = start

    // index of second array
    var j = middle + 1

    /**
     * Fill the list one by one until we reach the end of any of two lists [start .. middle] and [middle + 1 .. end]
     *
     */
    while (i <= middle && j <= end){

        if(inputArray[i] > inputArray[j]){
            inversionsOutPutArray[index] = inputArray[j++]
            splitInversionsCount+= middle - i + 1
                    /**
                     * Explanation:
                     * (1, 3, 5) (2, 4, 6)
                     *
                     * When "2" is moved from right sub-array to position 1(starting from zero) in first sub-array
                     * it counts 2 inversions because 3 > 2 and 5 > 2
                     *
                     * middle(2) - i(1) + 1 (because starting from zero)
                     */


        } else {
            inversionsOutPutArray[index] = inputArray[i++]
        }

        index++

    }

    while(i <= middle){
        inversionsOutPutArray[index++] = inputArray[i++]
    }

    while(j <= end){
        inversionsOutPutArray[index++] = inputArray[j++]
    }

    /**
     * This is necessary. We have to save the sorted output array into original array
     * because original array is passed for further processing
     */
    println("mergeAndCountSplitInversions, > outputArray")
    for (outIndex in start..end) {
        inputArray[outIndex] = inversionsOutPutArray[outIndex]
        print(" " + inputArray[outIndex] + " ")
    }
    println("\nmergeAndCountSplitInversions")

    println("<< mergeAndCountSplitInversions, splitInversionsCount " + splitInversionsCount)
    return splitInversionsCount
}

//fun sortAndCountInversions(numbers : IntArray, lenght : Int) : Int{
//
//    /**
//     * Zero inversions in a one-element array
//     */
//    if(lenght == 1) return 0
//
//    var firstHalfInversionsCount = sortAndCountInversions(numbers.copyOfRange(0, lenght/2 ), lenght / 2)
//    var secondHalfInversionsCount = sortAndCountInversions(numbers.copyOfRange(lenght/2 + 1, lenght), lenght / 2)
//    var splitInversionsCount = mergeAndCountSplitInversions(numbers.copyOfRange(lenght/2 + 1, lenght), lenght / 2)
//
//    return firstHalfInversionsCount +
//            secondHalfInversionsCount +
//            splitInversionsCount
//}

//fun  mergeAndCountSplitInversions(numbers : IntArray, lenght : Int) : Int{
//
//}
