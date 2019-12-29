package com.github.tonybaines.sudoku

object Sudoku {
    fun solutionsFor(partial: String): Sequence<String> =
        Grid.from(partial)
            .permutations()
            .filter(Grid::isValid)
            .map(Grid::toString)

}
