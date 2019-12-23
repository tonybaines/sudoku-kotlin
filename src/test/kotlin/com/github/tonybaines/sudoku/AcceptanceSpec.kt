package com.github.tonybaines.sudoku

import io.kotlintest.Spec
import junit.framework.Assert.assertTrue
import org.junit.Test

object AcceptanceSpec {
    val PARTIAL_9x9 = """
           |26 |7 1
        68 | 7 | 9 
        19 |  4|5  
        ---|---|---
        82 |1  | 4 
          4|6 2|9  
         5 |  3| 28
        ---|---|---
          9|3  | 74
         4 | 5 | 36
        7 3| 18|   
    """.trimIndent()

    val SOLVED_9x9 = """
        435|269|781
        682|571|493
        197|834|562
        ---|---|---
        826|195|347
        374|682|915
        951|743|628
        ---|---|---
        519|326|874
        248|957|136
        763|418|259
    """.trimIndent()

    @Test
    fun `solves an example 9x9 grid`() {
        assertTrue(Sudoku.solutionsFor(PARTIAL_9x9).contains(SOLVED_9x9))
    }
}