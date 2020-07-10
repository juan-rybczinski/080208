package com.fpinkotlin.functions.exercise03

typealias IntBinOp = (Int) -> (Int) -> Int

val add: IntBinOp = { x ->
    { y -> x + y }
}