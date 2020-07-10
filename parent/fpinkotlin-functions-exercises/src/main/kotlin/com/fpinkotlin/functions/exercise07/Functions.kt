package com.fpinkotlin.functions.exercise07

fun <A, B, C> partialA(x: A, f: (A) -> (B) -> C): (B) -> C = f(x)