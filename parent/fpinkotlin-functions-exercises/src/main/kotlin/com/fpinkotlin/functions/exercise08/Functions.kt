package com.fpinkotlin.functions.exercise08

fun <A, B, C> partialB(y: B, f: (A) -> (B) -> C): (A) -> C = { x: A -> f(x)(y) }