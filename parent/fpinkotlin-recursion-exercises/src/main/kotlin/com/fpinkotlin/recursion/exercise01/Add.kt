package com.fpinkotlin.recursion.exercise01


fun add(a: Int, b: Int): Int {
    tailrec fun add(a: Int, b: Int): Int = if (b == 0) a else add(inc(a), dec(b))
    return add(a, b)
}

fun inc(n: Int) = n + 1
fun dec(n: Int) = n - 1

