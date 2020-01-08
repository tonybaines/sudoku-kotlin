package com.github.tonybaines.sudoku

object Solver {
    fun improve(grid: Grid): Grid {
        val improvedRows = grid.rows.map { row ->
            val permutations = Permutations.permutationsOf(grid.required, row)
            if (permutations.size == 1) permutations.first()
            else row
        }

        return Grid.from(improvedRows, grid.required)
    }

}
