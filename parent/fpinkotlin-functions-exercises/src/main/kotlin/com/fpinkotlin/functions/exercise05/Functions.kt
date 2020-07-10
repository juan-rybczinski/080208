package com.fpinkotlin.functions.exercise05

fun square(n: Int) = n * n
fun triple(n: Int) = n * 3

fun compose(f: (Int) -> Int, g: (Int) -> Int): (Int) -> Int = {
    f(g(it))
}

typealias IntUnaryOp = (Int) -> Int

val square: IntUnaryOp = { it * it }
val triple: IntUnaryOp = { it * 3 }

val compose = { x: IntUnaryOp -> { y: IntUnaryOp -> { z: Int -> x(y(z)) } } }

fun <A, B, C> higherCompose() = { x: (B) -> C ->
    { y: (A) -> B ->
        { z: A -> x(y(z))}
    }
}