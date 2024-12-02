package advent._2022

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.*
import kotlin.math.absoluteValue

class Vertex(val height: Int, val sign: Char, val x: Int, val y: Int) {
    var dist: Int = 0
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vertex

        if (height != other.height) return false
        if (sign != other.sign) return false
        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + sign.hashCode()
        result = 31 * result + x
        result = 31 * result + y
        return result
    }

}

class Day12 {
    private val vertexes = mutableListOf<Vertex>()
    private val adjVertices: MutableMap<Vertex, MutableSet<Vertex>> = mutableMapOf()
    private val matrix = mutableListOf<MutableList<Pair<Int, Char>>>()

    fun xNeighbour(vertex: Vertex, x: Int) {
        try {
            val height = matrix[x][vertex.y].first
            val heightDiff = (vertex.height - height).absoluteValue
            if (heightDiff == 1 || height <= vertex.height) {
                var v = vertexes.find { it.x == x && it.y == vertex.y }!!
                adjVertices[vertex]?.add(v)
            }
        } catch (ex: Exception) {

        }
    }

    fun yNeighbour(vertex: Vertex, y: Int) {
        try {
            val height = matrix[vertex.x][y].first
            val heightDiff = (vertex.height - height).absoluteValue
            if (heightDiff == 1 || height <= vertex.height) {
                var v = vertexes.find { it.x == vertex.x && it.y == y }!!
                adjVertices[vertex]?.add(v)
            }
        } catch (ex: Exception) {

        }
    }

    fun addEdges(vertex: Vertex) {
        adjVertices[vertex] = mutableSetOf();
        xNeighbour(vertex, vertex.x - 1)
        xNeighbour(vertex, vertex.x + 1)

        yNeighbour(vertex, vertex.y - 1)
        yNeighbour(vertex, vertex.y + 1)
    }

    fun run() {
        readInput()
        val roots = mutableListOf<Vertex>()
        for (x in matrix.withIndex()) {
            for (y in x.value.withIndex()) {
                val m = matrix[x.index][y.index]
                val vertex = Vertex(m.first, m.second, x.index, y.index)
                if (m.first == 'a'.code) {
                    roots.add(vertex)
                }

                vertexes.add(vertex)
            }
        }
        for (vertex in vertexes) {
            addEdges(vertex)
        }
        var min = Int.MAX_VALUE
        for (root in roots) {
            for (adjVertex in adjVertices) {
                adjVertex.key.dist = 0
                for (v in adjVertex.value) {
                    v.dist = 0
                }
            }
            var steps = findEndPoint(root)
            if(steps < min){
                min = steps
            }
        }
        println(min)
        /*for (x in 0 until matrix.size) {
            for (y in 0 until matrix[0].size) {
                if (visited.any { it.x == x && it.y == y }) {
                    print(".")
                } else {
                    print("#")
                }
            }
        }*/
    }

    fun findEndPoint(from: Vertex): Int {
        var start = from
        var queue = LinkedList<Vertex>();
        var visited = mutableSetOf<Vertex>()
        queue.add(start)
        visited.add(start)

        while (!queue.isEmpty()) {
            val current = queue.pop();
            if (current.sign == 'E') {
                return current.dist
            }

            adjVertices[current]!!.forEach {
                if (!visited.contains(it)) {
                    it.dist = current.dist + 1
                    queue.add(it)
                    visited.add(it)
                }
            }
//            if (queue.isEmpty()) {
//                println("${current.x} ${current.y}")
//            }
        }
        return Int.MAX_VALUE
    }
    //257 low

    private fun readInput() {
        BufferedReader(FileReader("/Users/hoffmann-daniel-mbp/dev/advent/advent//resources/day12.txt")).use {
            try {
                var index = 0;
                var line = it.readLine()
                do {
                    matrix.add(mutableListOf())
                    for (c in line.toCharArray()) {
                        var height = c.code
                        if (c == 'S') {
                            height = 'a'.code
                        }
                        if (c == 'E') {
                            height = 'z'.code
                        }
                        matrix[index].add(Pair(height, c))
                    }
                    index++

                    line = it.readLine()
                } while (line != null)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

//aabqponm
//abcryxxl
//accszExk
//acctuvwj
//abdefghi
