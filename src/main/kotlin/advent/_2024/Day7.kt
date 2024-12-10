package advent._2024

import advent.helper.readInput
import advent.helper.runMeasured
import java.util.concurrent.atomic.AtomicBoolean

class Day7Part1 {
    data class TreeNode(
        val value: Long,
        val children: MutableList<TreeNode> = mutableListOf()
    )

    fun run(): Long {
        val lines = readInput("2024/7.txt")
        var sum = 0L

        runMeasured {
            for (line in lines) {
                val split = line.split(":")
                val target = split[0].toLong()

                val values = split[1].split(" ").toMutableList()
                values.removeIf { it.isEmpty() }
                var tree = buildTree(values[0].toLong() + values[1].toLong(), values)

                val result = AtomicBoolean(false)
                findLastNode(tree, target, result)
                if (!result.get()) {
                    tree = buildTree(values[0].toLong() * values[1].toLong(), values)
                    findLastNode(tree, target, result)
                }

                if (result.get()) {
                    sum += target
                }
            }
        }
        return sum
    }


    private fun findLastNode(node: TreeNode, target: Long, accumulator: AtomicBoolean) {
        if (node.children.isEmpty() && node.value == target) {
            accumulator.set(true)
            return
        }
        if (node.value > target) {
            return
        }
        for (child in node.children) {
            findLastNode(child, target, accumulator)
        }
    }

    // Function to build a tree
    private fun buildTree(rootValue: Long, values: List<String>): TreeNode {

        fun createLevel(currentNode: TreeNode, currentLevel: Int) {
            val nextLevel = currentLevel + 1
            if (nextLevel == values.size) {
                return
            }

            val toLong = values[nextLevel].toLong()

            val additionNode = TreeNode(value = currentNode.value + toLong)
            currentNode.children.add(additionNode)
            createLevel(additionNode, nextLevel)

            val multiplyNode = TreeNode(value = currentNode.value * toLong)
            currentNode.children.add(multiplyNode)
            createLevel(multiplyNode, nextLevel)
        }

        val root = TreeNode(rootValue)
        createLevel(root, 1)
        return root
    }
}

class Day7Part2 {

    data class TreeNode(
        val value: Long,
        val children: MutableList<TreeNode> = mutableListOf()
    )

    fun run(): Long {
        val lines = readInput("2024/7.txt")
        var sum = 0L

        runMeasured {
            for (line in lines) {
                val split = line.split(":")
                val target = split[0].toLong()

                val values = split[1].split(" ").toMutableList()
                values.removeIf { it.isEmpty() }
                var tree = buildTree(values[0].toLong() + values[1].toLong(), values)

                val result = AtomicBoolean(false)
                findLastNode(tree, target, result)
                if (!result.get()) {
                    tree = buildTree(values[0].toLong() * values[1].toLong(), values)
                    findLastNode(tree, target, result)
                }
                if (!result.get()) {
                    tree = buildTree((values[0] + values[1]).toLong(), values)
                    findLastNode(tree, target, result)
                }

                if (result.get()) {
                    sum += target
                }
            }
        }
        return sum
    }

    private fun findLastNode(node: TreeNode, target: Long, accumulator: AtomicBoolean) {
        if (node.children.isEmpty() && node.value == target) {
            accumulator.set(true)
            return
        }
        if (node.value > target) {
            return
        }
        for (child in node.children) {
            findLastNode(child, target, accumulator)
        }
    }

    // Function to build a tree
    private fun buildTree(rootValue: Long, values: List<String>): TreeNode {

        fun createLevel(currentNode: TreeNode, currentLevel: Int) {
            val nextLevel = currentLevel + 1
            if (nextLevel == values.size) {
                return
            }

            val toLong = values[nextLevel].toLong()

            val additionNode = TreeNode(value = currentNode.value + toLong)
            currentNode.children.add(additionNode)
            createLevel(additionNode, nextLevel)

            val multiplyNode = TreeNode(value = currentNode.value * toLong)
            currentNode.children.add(multiplyNode)
            createLevel(multiplyNode, nextLevel)

            val concatenationNode = TreeNode(value = "${currentNode.value}$toLong".toLong())
            currentNode.children.add(concatenationNode)
            createLevel(concatenationNode, nextLevel)
        }

        val root = TreeNode(rootValue)
        createLevel(root, 1)
        return root
    }
}