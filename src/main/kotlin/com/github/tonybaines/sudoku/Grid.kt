package com.github.tonybaines.sudoku

import com.marcinmoskala.math.permutations
import kotlin.math.roundToInt
import kotlin.math.sqrt

typealias Group = List<Char>
typealias Groups = List<Group>

class Grid(private val size: Int, private val rows: Groups) {
    private val cellSize = sqrt(size.toDouble()).roundToInt()
    private val columns:Groups
            get() = (0 until  size).map {col ->
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


    fun permutations(): Sequence<Grid> {
//        val permutations = setOf('A', 'C', 'G', 'T').permutations().asSequence()
        val permutations = setOf('1', '2', '3', '4').permutations().asSequence()
        return permutations.filter(validFor(rows[0])).flatMap { a ->
            permutations.filter(validFor(rows[1])).flatMap { b ->
                permutations.filter(validFor(rows[2])).flatMap { c ->
                    permutations.filter(validFor(rows[3])).map { d ->
                        Grid.from(listOf(a, b, c, d))
                    }
                }
            }
        }
    }

    private fun validFor(template: List<Char>): (List<Char>) -> Boolean = { permutation ->
        permutation.mapIndexed { i, c ->
            template[i] == '?' || c == template[i]
        }.all { it == true }
    }

    fun isValid(): Boolean =
        rows.areValid && columns.areValid && cells.areValid


    private val Iterable<Group>.areValid: Boolean
        get() = this.all { it.isValid }

    private val Group.isValid: Boolean
        get() = toSortedSet().size == size

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
                .asSequence()
                .filter(String::isNotBlank)
                .filter { !it.matches("^(-)+$".toRegex()) } // remove horizontal separators
                .map { it.filter { it != '|' } } // remove vertical separators
                .map { it.replace("""[^0-9]""".toRegex(), "?") } // homogenise unknown cells
                .map { it.toCharArray().asList() } // convert to chars
                .toList()

            val gridSize = gridRows.first().size

            return Grid(size = gridSize, rows = gridRows)
        }

        fun from(values: Groups): Grid {
            return Grid(size = values.first().size, rows = values)
        }
    }

}
