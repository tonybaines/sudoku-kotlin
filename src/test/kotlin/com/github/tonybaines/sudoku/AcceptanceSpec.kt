package com.github.tonybaines.sudoku

import com.github.tonybaines.sudoku.Fixture.ONE_TO_FOUR
import com.github.tonybaines.sudoku.Fixture.ONE_TO_NINE
import com.github.tonybaines.sudoku.Fixture.PARTIAL_9x9
import com.github.tonybaines.sudoku.Fixture.SOLVED_9x9
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.isEmpty
import org.junit.Ignore
import org.junit.Test
import org.mockito.AdditionalMatchers.not

class AcceptanceSpec {

    @Test
    @Ignore("Would take ~ 1.6x10^9 years to complete the 5x10^18 permutations")
    fun `solves an example 9x9 grid`() {
        assertThat(Sudoku.solutionsFor(PARTIAL_9x9, ONE_TO_NINE).first(), equalTo(SOLVED_9x9))
    }

    @Test
    fun `solves an example 9x9 grid with just one missing value`() {
        assertThat(Sudoku.solutionsFor(
            SOLVED_9x9
                .replaceFirst('3', '?'),
            ONE_TO_NINE).toList().first().toString(), equalTo(SOLVED_9x9))
    }

    @Test
    fun `solves an example 9x9 grid with two missing values per row`() {
        assertThat(Sudoku.solutionsFor(
            SOLVED_9x9
                .replace('3', '?')
                .replace('9', '?'),
            ONE_TO_NINE).first().toList(), !isEmpty)
    }

    @Test
    fun `solves an example 9x9 grid with three missing values per row`() {
        assertThat(Sudoku.solutionsFor(
            SOLVED_9x9
                .replace('3', '?')
                .replace('6', '?')
                .replace('9', '?'),
            ONE_TO_NINE).first().toList(), !isEmpty)
    }

    @Test
    @Ignore
    fun `solves an example 9x9 grid with four missing values per row`() {
        assertThat(Sudoku.solutionsFor(
            SOLVED_9x9
                .replace('3', '?')
                .replace('6', '?')
                .replace('8', '?')
                .replace('9', '?'),
            ONE_TO_NINE).first().toList(), !isEmpty)
    }

    @Test
    @Ignore
    fun `solves an example 9x9 grid with five missing values per row`() {
        assertThat(Sudoku.solutionsFor(
            SOLVED_9x9
                .replace('3', '?')
                .replace('4', '?')
                .replace('6', '?')
                .replace('8', '?')
                .replace('9', '?'),
            ONE_TO_NINE).first().toList(), !isEmpty)
    }

    @Test
    @Ignore
    fun `solves a partial 4x4 grid`() {
        val solutions = Sudoku.solutionsFor(
            """
                    32|4*
                    *1|*3
                    -----
                    **|1*
                    1*|**
                """.trimIndent(),
            ONE_TO_FOUR
        ).toList()
        assertThat(
            solutions.first(), equalTo(
                """
                    32|41
                    41|23
                    -----
                    23|14
                    14|32
                """.trimIndent()
            )
        )
        assertThat(solutions.size, equalTo(1))
    }

    @Test
    @Ignore
    fun `solve a partially complete 4x4`() {
        val gridString = """
            12|34
            34|12
            -----
            **|**
            **|**
        """.trimIndent()

        val allSolutions = Sudoku.solutionsFor(gridString, ONE_TO_FOUR).toList()
        println(allSolutions.mapIndexed { i, it -> "\n========$i========\n$it" })
        assertThat(allSolutions.size, com.natpryce.hamkrest.equalTo(4))
    }

    @Test
    @Ignore
    fun `solve 4x4`() {
        val gridString = """
            ??|??
            ??|??
            -----
            ??|??
            ??|??
        """.trimIndent()

        val allSolutions = Sudoku.solutionsFor(gridString, ONE_TO_FOUR).toList()
        assertThat(allSolutions.size, com.natpryce.hamkrest.equalTo(288))
    }
}