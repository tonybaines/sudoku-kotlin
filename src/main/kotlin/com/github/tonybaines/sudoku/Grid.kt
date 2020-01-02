package com.github.tonybaines.sudoku

import kotlin.math.roundToInt
import kotlin.math.sqrt

typealias Group = List<Char>
typealias Groups = List<Group>

class Grid(
    private val size: Int,
    private val rows: Groups,
    val required: Set<Char>
) {
    private val cellSize = sqrt(size.toDouble()).roundToInt()
    private val columns: Groups
        get() = (0 until size).map { col ->
            rows.map { it[col] }
        }

    private val cells: Groups
        get() =
            (0 until cellSize).flatMap { cellRowNum ->
                (0 until cellSize).map { cellColNum ->
                    (0 until cellSize).flatMap { rowNum ->
                        (0 until cellSize).map { colNum ->
                            rows[rowNum + (cellRowNum * cellSize)][colNum + (cellColNum * cellSize)]
                        }
                    }
                }
            }


    fun permutations(): Sequence<Grid> = Permutations.permutationsOf(required, rows).asSequence()

    fun isValid(): Boolean =
        rows.areValid && columns.areValid && cells.areValid


    private val Iterable<Group>.areValid: Boolean
        get() = this.all { it.isValid }

    private val Group.isValid: Boolean
        get() = this.containsAll(required)

    private val rowWidth = size + (size / cellSize) - 1

    override fun toString(): String =
        rows.mapIndexed { index, row ->
            val rowString = row.mapIndexed { i, c ->
                if (isCellBoundary(i)) "|$c" else "$c"
            }.joinToString("")
            if (isCellBoundary(index)) "${"-".repeat(rowWidth)}\n".plus(rowString) else rowString
        }.joinToString("\n")


    private fun isCellBoundary(i: Int): Boolean =
        (i > 0) && (i % cellSize == 0)

    companion object {
        fun from(partial: String, requiredValues: Set<Char>): Grid {
            val gridRows = partial.split('\n')
                .asSequence()
                .filter(String::isNotBlank)
                .filter { !it.matches("^(-)+$".toRegex()) } // remove horizontal separators
                .map { it.filter { it != '|' } } // remove vertical separators
                .map { it.replace("""[^0-9]""".toRegex(), "?") } // homogenise unknown cells
                .map { it.toCharArray().asList() } // convert to chars
                .toList()

            val gridSize = gridRows.first().size

            return Grid(size = gridSize, rows = gridRows, required = requiredValues)
        }

        fun from(values: Groups, requiredValues: Set<Char>): Grid {
            val gridSize = values.first().size
            return Grid(size = gridSize, rows = values, required = requiredValues)
        }
    }

}
