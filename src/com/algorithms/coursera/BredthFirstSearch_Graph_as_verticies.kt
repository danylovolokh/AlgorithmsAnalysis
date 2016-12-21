package com.algorithms.coursera

import java.util.*

/**
 * Breadth-First-Search is very good for finding shortest path.
 * (Google maps etc..)
 *
 */

private val VERTEX_COUNT = 100

/** A shared random number generator. */
private val RANDOM = Random()

/**
 * Two dimensional array. Matrix of vertices is not optimal to use BFS.
 * It's better to have them in this format
 * vertex1 : [3 -> 5, 4, 32, 76, 4]
 * vertex2 : [2 -> 3, 1, 36, 6, 9]
 *
 * Example:
 *
 *    _______________________________
 *   |                               |
 * a | 1 -> 43, 54, 67, 32, 84, 33,  |
 *   |                               |
 * b | 2 -> 13, 44, 12, 22, 47, 34,  |
 *   |                               |
 * c | 3 -> 42, 22, 66, 22, 87, 54,  |
 *   |                               |
 * d | 4 -> 23, 34, 76, 2,  54, 55,  |
 *   |_______________________________|
 *
 *   1 is connected to 43, 54, 67, 32, 84, 33
 *   2 is connected to 13, 44, 12, 22, 47, 34,
 *   3 is connected to 42, 22, 66, 22, 87, 54,
 *   4 is connected to 23, 34, 76, 2,  54, 55,
 */

private val GRAPH : MutableList<MutableList<Int>> = mutableListOf()

/**
 * We want to have a list of Booleans that indicates if the vertex at specific index is already visited
 */
private val VISITED_VERTICES_LIST: Array<Boolean> = Array(VERTEX_COUNT, init = { false } )

fun main(args: Array<String>){

    createVertices()

    /**
     * Here we start from the vertex #43 (with no reason :) )
     */
    BFS(43)
}

private fun BFS(startVertex: Int){
    println("BFS, startVertex $startVertex")

    /**
     * Mark start vertex as visited
     */
    VISITED_VERTICES_LIST[startVertex] = true

    val queue: Queue<Int> = LinkedList()
    queue.add(startVertex)

    while (!queue.isEmpty()) {

        val vertex: Int = queue.peek()

        /**
         * So we get the neighbours for "vertex" by getting a MutableList from GRAPH[vertex]
         */
        for (neighbour in GRAPH[vertex] - 1) {

            if (!isVisited(neighbour)) {
                /**
                 * Mark the possibleNeighbour as visited
                 */
                VISITED_VERTICES_LIST[neighbour] = true

                /**
                 * Push the possibleNeighbour to the queue
                 */
                queue.add(neighbour)

                handleTheEdge(vertex, neighbour)

            }
        }
    }
}

private fun isVisited(neighbourIndex: Int) : Boolean {
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

private fun createVertices(){
    println(">> createVertices")

    /**
     * Let's fill all the vertex neighbours one by one
     */
    for (vertex in 0..VERTEX_COUNT - 1){

        val neighbours: MutableList<Int> = createNeighboursForVertex()
        println("vertex, $vertex, neighbours -> $neighbours")

        GRAPH.add(neighbours)

    }
    println("<< createVertices")
}

/**
 * This method should be called for every vertex.
 * It will generate a list of vertices that are connected to it.
 */
private fun createNeighboursForVertex(): MutableList<Int> {
    val neighbours: MutableList<Int> = mutableListOf()
    /**
     * Every vertex has random count of connections to other vertices
     */
    for (index in 0..RANDOM.nextInt(VERTEX_COUNT)) {
        /**
         * Every neighbour is chosen randomly
         */
        val neighbour: Int = RANDOM.nextInt(VERTEX_COUNT)
        neighbours.add(neighbour)
    }
    return neighbours
}
