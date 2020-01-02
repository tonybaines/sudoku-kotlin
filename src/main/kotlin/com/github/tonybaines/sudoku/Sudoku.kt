package com.github.tonybaines.sudoku

object Sudoku {
    fun solutionsFor(partial: String, requiredValues: Set<Char>): Sequence<String> =
        Grid.from(partial, requiredValues)
            .permutations()
            .filter(Grid::isValid)
            .map(Grid::toString)

}
