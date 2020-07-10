package com.fpinkotlin.functions.exercise09

fun <A, B, C, D> curried() = { a: A ->
    { b: B ->
        { c: C ->
            { d: D -> "$a, $b, $c, $d" }
        }
    }
}