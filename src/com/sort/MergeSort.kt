package com.sort

import java.util.*

/**
 * This is an implementation of Merge sorting
 */

private val NUMBERS_COUNT = 31

/**
 * Helper outputArray for easier merge. But it increases the Running time.
 */
val outPutArray : IntArray = IntArray(NUMBERS_COUNT)

/**
 * Created by danylo.volokh on 7/3/16.
 */
fun main(args: Array<String>){

    val randomizer : Random = Random()

    val intArray : IntArray = IntArray(NUMBERS_COUNT)

    for (index in 0..NUMBERS_COUNT - 1) {
        intArray[index] = randomizer.nextInt(NUMBERS_COUNT)
        print("" + intArray[index] + " ")
    }

    println("")

    recursiveMergeSort(intArray, 0, intArray.size - 1)

    println("\noutPutArray")
    for (index in 0..NUMBERS_COUNT - 1) {

        print(" " + outPutArray[index] + " ")
    }
}

fun recursiveMergeSort(intArray: IntArray, start: Int, end: Int){

    println(">> recursiveMergeSort")

    println("start $start, end $end")

    if(start < end){

        var middle = (start + end) / 2

        recursiveMergeSort(intArray, start, middle)
        recursiveMergeSort(intArray, middle + 1, end)
        merge(intArray, start, middle, end)

    }
    println("<< recursiveMergeSort")
}

fun merge(inputArray: IntArray, start: Int, middle: Int, end: Int) {
    println(">> merge")

    // index of original array
    var index = start

    // index of first array
    var i = start

    // index of second array
    var j = middle + 1

    /**
    * Fill the list one by one until we reach the end of any of two lists [start .. middle] and [middle + 1 .. end]
     */
    while (i <= middle && j <= end){

        if(inputArray[i] > inputArray[j]){
            outPutArray[index] = inputArray[j++]
        } else {
            outPutArray[index] = inputArray[i++]
        }

        index++
    }

    while(i <= middle){
        outPutArray[index++] = inputArray[i++]
    }

    while(j <= end){
        outPutArray[index++] = inputArray[j++]
    }

    /**
     * This is necessary. We have to save the sorted output array into original array
     * because original array is passed for further processing
     */
    println("merge, > outputArray")
    for (outIndex in start..end) {
        inputArray[outIndex] = outPutArray[outIndex]
        print(" " + inputArray[outIndex] + " ")
    }
    println("\nmerge, < outputArray")

    println("<< merge")
}
