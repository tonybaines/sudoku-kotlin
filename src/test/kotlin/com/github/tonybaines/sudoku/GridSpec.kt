package com.github.tonybaines.sudoku

import com.github.tonybaines.sudoku.AcceptanceSpec.Companion.SOLVED_9x9
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import org.junit.Test

class GridSpec {
    @Test
    fun `can read a 2x2 grid and turn it back into a  string`() {
        val gridString = """
            1|2
            ---
            2|1
        """.trimIndent()
        assertThat(
            Grid.from(
                gridString
            ).toString(), equalTo(gridString)
        )
    }

    @Test
    fun `can read a 4x4 grid and turn it back into a  string`() {
        val gridString = """
            12|34
            34|12
            -----
            23|41
            41|23
        """.trimIndent()
        assertThat(
            Grid.from(
                gridString
            ).toString(), equalTo(gridString)
        )
    }

    @Test
    fun `can read a 9x9 grid and turn it back into a string`() {
        assertThat(Grid.from(SOLVED_9x9).toString(), equalTo(SOLVED_9x9))
    }

    @Test
    fun `can generate unique permutations for a 2x2 grid`() {
        val grid = Grid.from(
            """
            ?|?
            ---
            ?|?
        """.trimIndent()
        )

        var permutations = grid.permutations()
        assertThat(permutations.toList(), hasSize(equalTo(4)))
    }
}