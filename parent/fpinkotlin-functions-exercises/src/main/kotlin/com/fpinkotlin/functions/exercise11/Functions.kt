package com.fpinkotlin.functions.exercise11

fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = { a ->
    { b -> f(a, b) }
}

fun <A, B, C> swapArgs(f: (B) -> (A) -> C): (A) -> (B) -> C = { a ->
    { b -> f(b)(a) }
}