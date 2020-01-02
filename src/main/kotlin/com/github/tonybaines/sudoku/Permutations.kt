package com.github.tonybaines.sudoku

import com.marcinmoskala.math.permutations

object Permutations {

    fun permutationsOf(required: Set<Char>, rows: List<List<Char>>): Sequence<Grid> {
        return permutationsOf(required, rows[0]).asSequence().flatMap { a ->
            permutationsOf(required, rows[1]).asSequence().flatMap { b ->
                permutationsOf(required, rows[2]).asSequence().flatMap { c ->
                    //                    permutationsOf(required, rows[3]).asSequence().map { d ->
//                        Grid.from(listOf(a, b, c, d))
//                    }
                    permutationsOf(required, rows[3]).asSequence().flatMap { d ->
                        permutationsOf(required, rows[4]).asSequence().flatMap { e ->
                            permutationsOf(required, rows[5]).asSequence().flatMap { f ->
                                permutationsOf(required, rows[6]).asSequence().flatMap { g ->
                                    permutationsOf(required, rows[7]).asSequence().flatMap { h ->
                                        permutationsOf(required, rows[8]).asSequence().map { i ->
                                            Grid.from(listOf(a, b, c, d, e, f, g, h, i), required)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun permutationsOf(required: Set<Char>, template: List<Char>): Set<List<Char>> {
        val notYetDefined = (required - template.toSet()).permutations()

        if (notYetDefined.isEmpty()) return setOf(template)
        else return notYetDefined.map { permutation ->
            val iterator = permutation.iterator()
            template.fold(listOf()) { acc: List<Char>, next ->
                acc.plus(if (next == '?') iterator.next() else next)
            }
        }.toSet()
    }

}
