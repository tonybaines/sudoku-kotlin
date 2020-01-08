package com.github.tonybaines.sudoku


object Fixture {

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

    fun rows(vararg groups: Group) : Groups = groups.asList()
    fun row(vararg values: Char): Group = values.asList()
}