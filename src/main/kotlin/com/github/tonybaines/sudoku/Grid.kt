package com.github.tonybaines.sudoku

import kotlin.math.roundToInt

class Grid(private val size: Int, private val rows: List<String>) {
    private val cellSize = Math.sqrt(size.toDouble()).roundToInt()

    fun permutations(): Sequence<Grid> {
        TODO()
    }

    fun isValid(): Boolean {
        TODO()
    }

    private val rowWidth = size + (size / cellSize) - 1

    override fun toString(): String {
        return rows.mapIndexed { index, row ->
            val rowString = row.mapIndexed { i, c ->
                if (isCellBoundary(i)) "|$c" else "$c"
            }.joinToString("")
            if (isCellBoundary(index)) "${"-".repeat(rowWidth)}\n".plus(rowString) else rowString
        }.joinToString("\n")

    }

    private fun isCellBoundary(i: Int): Boolean =
        (i > 0) && (i % cellSize == 0)

    companion object {
        fun from(partial: String): Grid {
            val gridRows = partial.split('\n')
                .filter(String::isNotBlank)
                .filter { !it.matches("^(-)+$".toRegex()) } // remove horizontal separators
                .map { it.filter { it != '|' } } // remove vertical separators
                .map { it.replace("""[^0-9]""".toRegex(), "?") } // homogenise unknown cells

            val gridSize = gridRows.first().length

            return Grid(size = gridSize, rows = gridRows)
        }
    }

}
