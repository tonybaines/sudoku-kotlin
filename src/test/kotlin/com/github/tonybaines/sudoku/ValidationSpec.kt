package com.github.tonybaines.sudoku

import com.github.tonybaines.sudoku.AcceptanceSpec.SOLVED_9x9
import junit.framework.Assert.assertTrue
import org.junit.Test

class ValidationSpec {
    @Test
    fun `recognises a valid 9x9 grid`() {
        assertTrue(Grid.from(SOLVED_9x9).isValid())
    }
}