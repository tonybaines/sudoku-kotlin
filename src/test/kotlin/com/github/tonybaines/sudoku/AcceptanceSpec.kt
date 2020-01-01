package com.github.tonybaines.sudoku

import com.natpryce.hamkrest.assertion.assertThat
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert
import org.junit.Assert.assertThat
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
    }

    @Test
    fun `solves an example 9x9 grid`() {
        assertTrue(Sudoku.solutionsFor(PARTIAL_9x9).toList().contains(SOLVED_9x9))
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
                """.trimIndent()
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

        val allSolutions = Sudoku.solutionsFor(gridString).toList()
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

        val allSolutions = Sudoku.solutionsFor(gridString).toList()
        assertThat(allSolutions.size, com.natpryce.hamkrest.equalTo(288))
    }
}