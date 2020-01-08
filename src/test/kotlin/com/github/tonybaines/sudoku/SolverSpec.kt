package com.github.tonybaines.sudoku

import com.github.tonybaines.sudoku.Fixture.ONE_TO_FOUR
import com.github.tonybaines.sudoku.Fixture.rows
import com.github.tonybaines.sudoku.Fixture.row
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test


class SolverSpec {
    @Test
    fun `improves a grid row with one missing value`() {
        val groups: Groups = rows(
            row('1', '2', '?', '4')
        )

        assertThat(
            Solver.improve(Grid.from(groups, ONE_TO_FOUR)).toString(),
            equalTo(Grid.from(rows(row('1', '2', '3', '4')), ONE_TO_FOUR).toString())
        )
    }
}