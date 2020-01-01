package com.github.tonybaines.sudoku

import com.marcinmoskala.math.permutations
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.streams.toList

class GeneratorSpec {
    val MAX = 4
    val ROW_VALUES = (1..MAX).toSet()

    private val PERMUTATIONS = ROW_VALUES.permutations().asSequence()

    @Test
    fun `permutations of a single row`() {
        val permutations = PERMUTATIONS
        assertTrue(permutations.all { it.size == MAX })
//        assertThat(permutations.size, equalTo(24))
    }

    @Test
    fun `permutations of two rows`() {
        val permutations = PERMUTATIONS.flatMap { a ->
            PERMUTATIONS.map { b ->
                a to b
            }
        }
        println(permutations.toList())
    }

    @Test
    fun `permutations of three rows`() {
        val permutations = PERMUTATIONS.flatMap { a ->
            PERMUTATIONS.flatMap { b ->
                PERMUTATIONS.map { c ->
                    listOf(a,b,c)
                }
            }
        }
        println(permutations.toList())
    }

    @Test
    fun `permutations of four rows`() {
        val permutations = PERMUTATIONS.flatMap { a ->
            PERMUTATIONS.flatMap { b ->
                PERMUTATIONS.flatMap { c ->
                    PERMUTATIONS.map { d ->
                        listOf(a, b, c, d)
                    }
                }
            }
        }
        println(permutations.take(10).toList())
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
        assertThat(allSolutions.size, equalTo(288))
    }
}