package com.github.tonybaines.sudoku

import com.github.tonybaines.sudoku.Fixture.ONE_TO_FOUR
import com.github.tonybaines.sudoku.Fixture.ONE_TO_NINE
import com.github.tonybaines.sudoku.Fixture.rows
import com.github.tonybaines.sudoku.Fixture.row
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import org.junit.Ignore
import org.junit.Test

class PermutationsSpec {

    @Test
    fun `generates permutations for a template full of placeholders`() {
        assertThat(
            Permutations.permutationsOf(ONE_TO_FOUR, row('?', '?', '?', '?')),
            hasSize(equalTo(24))
        )
    }

    @Test
    fun `generates permutations for a template with no placeholders`() {
        assertThat(
            Permutations.permutationsOf(ONE_TO_FOUR, row('1', '2', '3', '4')),
            hasSize(equalTo(1))
        )
    }

    @Test
    fun `generates permutations for a template with one placeholder`() {
        assertThat(
            Permutations.permutationsOf(ONE_TO_FOUR, row('1', '2', '3', '?')),
            hasSize(equalTo(1))
        )
    }

    @Test
    fun `generates permutations for a template with two placeholders`() {
        assertThat(
            Permutations.permutationsOf(ONE_TO_FOUR, row('1', '2', '?', '?')),
            hasSize(equalTo(2))
        )
    }

    @Test
    fun `generates permutations for a template with three placeholders`() {
        assertThat(
            Permutations.permutationsOf(ONE_TO_FOUR, row('1', '?', '?', '?')),
            hasSize(equalTo(6))
        )
    }

    @Test
    fun `efficiently derives the permutations from a grid`() {
        val rows = rows(
            row('?', '?', '?', '?', '5', '6', '7', '8', '9'), // 4! = 24
            row('1', '2', '3', '?', '5', '6', '7', '8', '9'), // 1
            row('1', '2', '?', '?', '5', '6', '7', '8', '9'), // 2! = 2
            row('1', '?', '?', '?', '5', '6', '7', '8', '9'), // 3! = 6
            row('1', '2', '3', '4', '5', '6', '7', '8', '9'), // 1
            row('1', '2', '3', '4', '5', '6', '7', '8', '9'), // 1
            row('1', '2', '3', '4', '5', '6', '7', '8', '9'), // 1
            row('1', '2', '3', '4', '5', '6', '7', '8', '9'), // 1
            row('1', '2', '3', '4', '5', '6', '7', '8', '9')  // 1
        )

        assertThat(Permutations.permutationsOf(ONE_TO_NINE, rows).toList(), hasSize(equalTo(288)))
    }

    @Test
    @Ignore("Takes far too long")
    fun `efficiently derives the permutations from a grid with many placeholders`() {
        val rows = rows(
            row('?', '?', '?', '2', '6', '?', '7', '?', '1'), // 5! = 120
            row('6', '8', '?', '?', '7', '?', '?', '9', '?'), // 120
            row('1', '9', '?', '?', '?', '4', '5', '?', '?'), // 120
            row('8', '2', '?', '1', '?', '?', '?', '4', '?'), // 120
            row('?', '?', '4', '6', '?', '2', '9', '?', '?'), // 120
            row('?', '5', '?', '?', '?', '3', '?', '2', '8'), // 120
            row('?', '?', '9', '3', '?', '?', '?', '7', '4'), // 120
            row('?', '4', '?', '?', '5', '?', '?', '3', '6'), // 120
            row('7', '?', '3', '?', '1', '8', '?', '?', '?')  // 120
        ) // 5x10^18 permutations !

        assertThat(Permutations.permutationsOf(ONE_TO_NINE, rows).toList(), hasSize(equalTo(100)))
    }
}