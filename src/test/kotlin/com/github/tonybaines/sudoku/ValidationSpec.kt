package com.github.tonybaines.sudoku

import com.github.tonybaines.sudoku.Fixture.ONE_TO_NINE
import com.github.tonybaines.sudoku.Fixture.PARTIAL_9x9
import com.github.tonybaines.sudoku.Fixture.SOLVED_9x9
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertFalse
import org.junit.Test

class ValidationSpec {
    @Test
    fun `recognises a valid 9x9 grid`() {
        assertTrue(Grid.from(SOLVED_9x9, ONE_TO_NINE).isValid())
    }

    @Test
    fun `recognises an incomplete 9x9 grid`() {
        assertFalse(Grid.from(PARTIAL_9x9, ONE_TO_NINE).isValid())
    }
}