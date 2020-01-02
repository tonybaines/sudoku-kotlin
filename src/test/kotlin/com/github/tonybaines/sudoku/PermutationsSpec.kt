package com.github.tonybaines.sudoku

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import org.junit.Test

class PermutationsSpec {

    private val required = setOf('1', '2', '3', '4')

    @Test
    fun `generates permutations for a template full of placeholders`() {
        assertThat(
            Permutations.permutationsOf(required, listOf('?', '?', '?', '?')),
            hasSize(equalTo(24))
        )
    }

    @Test
    fun `generates permutations for a template with no placeholders`() {
        assertThat(
            Permutations.permutationsOf(required, listOf('1', '2', '3', '4')),
            hasSize(equalTo(1))
        )
    }

    @Test
    fun `generates permutations for a template with one placeholder`() {
        assertThat(
            Permutations.permutationsOf(required, listOf('1', '2', '3', '?')),
            hasSize(equalTo(1))
        )
    }

    @Test
    fun `generates permutations for a template with two placeholders`() {
        assertThat(
            Permutations.permutationsOf(required, listOf('1', '2', '?', '?')),
            hasSize(equalTo(2))
        )
    }

    @Test
    fun `generates permutations for a template with three placeholders`() {
        assertThat(
            Permutations.permutationsOf(required, listOf('1', '?', '?', '?')),
            hasSize(equalTo(6))
        )
    }

    @Test
    fun `efficiently derives the permutations from a grid`() {
        val rows = listOf(
            listOf('?', '?', '?', '?', '5', '6', '7', '8', '9'), // 4! = 24
            listOf('1', '2', '3', '?', '5', '6', '7', '8', '9'), // 1
            listOf('1', '2', '?', '?', '5', '6', '7', '8', '9'), // 2! = 2
            listOf('1', '?', '?', '?', '5', '6', '7', '8', '9'), // 3! = 6
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9'), // 1
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9'), // 1
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9'), // 1
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9'), // 1
            listOf('1', '2', '3', '4', '5', '6', '7', '8', '9')  // 1
        )

        assertThat(Permutations.permutationsOf(setOf('1', '2', '3', '4', '5', '6', '7', '8', '9'), rows).toList(), hasSize(equalTo(288)))
    }

//    @Test
//    fun `efficiently derives the permutations from a grid with many placeholders`() {
//        val rows = listOf(
//            listOf('?', '?', '?', '2', '6', '?', '7', '?', '1'), // 5! = 120
//            listOf('6', '8', '?', '?', '7', '?', '?', '9', '?'), // 120
//            listOf('1', '9', '?', '?', '?', '4', '5', '?', '?'), // 120
//            listOf('8', '2', '?', '1', '?', '?', '?', '4', '?'), // 120
//            listOf('?', '?', '4', '6', '?', '2', '9', '?', '?'), // 120
//            listOf('?', '5', '?', '?', '?', '3', '?', '2', '8'), // 120
//            listOf('?', '?', '9', '3', '?', '?', '?', '7', '4'), // 120
//            listOf('?', '4', '?', '?', '5', '?', '?', '3', '6'), // 120
//            listOf('7', '?', '3', '?', '1', '8', '?', '?', '?')  // 120
//        ) // 5x10^18 permutations !
//
//        assertThat(Permutations.permutationsOf(setOf('1', '2', '3', '4', '5', '6', '7', '8', '9'), rows).toList(), hasSize(equalTo()))
//    }
}