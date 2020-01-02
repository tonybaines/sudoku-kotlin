package com.github.tonybaines.sudoku

import com.natpryce.hamkrest.assertion.assertThat
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Ignore
import org.junit.Test

class AcceptanceSpec {
    companion object {
        val PARTIAL_9x9 = """
            ***|26*|7*1
            68*|*7*|*9*
            19*|**4|5**
            -----------
            82*|1**|*4*
            **4|6*2|9**
            *5*|**3|*28
            -----------
            **9|3**|*74
            *4*|*5*|*36
            7*3|*18|***
        """.trimIndent()

        val SOLVED_9x9 = """
            435|269|781
            682|571|493
            197|834|562
            -----------
            826|195|347
            374|682|915
            951|743|628
            -----------
            519|326|874
            248|957|136
            763|418|259
        """.trimIndent()

        val ONE_TO_NINE = setOf('1', '2', '3', '4', '5', '6', '7', '8', '9')
        val ONE_TO_FOUR = setOf('1', '2', '3', '4')
    }

    @Test
    @Ignore("Would take ~ 1.6x10^9 years to complete the 5x10^18 permutations")
    fun `solves an example 9x9 grid`() {
        assertThat(Sudoku.solutionsFor(PARTIAL_9x9, ONE_TO_NINE).first(), equalTo(SOLVED_9x9))
    }

    @Test
    fun `solves an example 9x9 grid with just one missing value`() {
        assertThat(Sudoku.solutionsFor(SOLVED_9x9.replaceFirst('3', '?'), ONE_TO_NINE).toList().first().toString(), equalTo(SOLVED_9x9))
    }

    @Test
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