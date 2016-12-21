package com.algorithms.coursera

import java.util.*

/**
 * Created by danylo.volokh on 9/2/16.
 *
 * Depth-First-Search is very good for finding exit from a maze.
 *
 * This example is using matrix for set up verticies.
 *
 *
 */

private val NODES_COUNT = 100

/** A shared random number generator. */
private val RANDOM = Random()

/**
 * Two dimensional array. Matrix of vertices. It's not optimal to use DFS.
 * It's better to have them in this format
 * vertex1 : [3 -> 5, 4, 32, 76, 4]
 * vertex2 : [2 -> 3, 1, 36, 6, 9]
 *
 * Example:
 *
 *       a       b       c       d
 *    _______________________________
 *   |                               |
 * a | false , false , true  , false |
 *   |                               |
 * b | true  , false , true  , true  |
 *   |                               |
 * c | false , true  , false , true  |
 *   |                               |
 * d | true  , false , true  , false |
 *   |_______________________________|
 *
 *   a is connected to c
 *   b is connected to c, d
 *   c is connected to b, d
 *   d is connected to a, a
 */
private val GRAPH_MATRIX: Array<Array<Boolean>> = Array(
        NODES_COUNT,
        init = {
            Array(
                    NODES_COUNT,
                    init = {
                        RANDOM.nextBoolean()
                    }
            )
        })

/**
 * We want to have a list of Booleans that indicates if the vertex at specific index is already visited
 */
private val VISITED_VERTICES_LIST: Array<Boolean> = Array(NODES_COUNT, init = { false } )

fun main(args: Array<String>){
    /**
     * Here we start from the vertex #4 (with no reason :) )
     */
    DFS(4)
}

private fun DFS(startVertex: Int){
    println("DFS, startVertex $startVertex")

    /**
     * Mark start vertex as visited
     */
    VISITED_VERTICES_LIST[startVertex] = true

    val stack : Stack<Int>  = Stack()
    stack.push(startVertex)

    while (!stack.isEmpty()){


        val vertex: Int = stack.pop()
        println("DFS, vertex $vertex")

        /**
         * Performance of this is horrible but it works !
         */
        for(possibleNeighbour in 0..NODES_COUNT - 1){

            println("DFS loop, vertex $vertex possibleNeighbour $possibleNeighbour" )

            if(!isVisited(possibleNeighbour) && isNeighbour(possibleNeighbour, vertex)){
                /**
                 * Mark the possibleNeighbour as visited
                 */
                VISITED_VERTICES_LIST[possibleNeighbour] = true

                /**
                 * Push the possibleNeighbour to the stack
                 */
                stack.push(possibleNeighbour)

                handleTheEdge(vertex, possibleNeighbour)

            }
        }
    }
}

private fun isNeighbour(neighbour: Int, vertex: Int) : Boolean{
    println("isNeighbour, " + GRAPH_MATRIX[vertex][neighbour])
    return GRAPH_MATRIX[vertex][neighbour]
}

private fun isVisited(neighbourIndex: Int) : Boolean {
    println("isVisited, " + VISITED_VERTICES_LIST[neighbourIndex])
    return VISITED_VERTICES_LIST[neighbourIndex]
}

/**
 * In this method we do the check of what we need.
 * For example :
 *  We know that there is a path from vertex -> neighbour.
 */
private fun handleTheEdge(vertex: Int, neighbour: Int) {
    println("handleTheEdge, vertex $vertex neighbour -> $neighbour")
}
