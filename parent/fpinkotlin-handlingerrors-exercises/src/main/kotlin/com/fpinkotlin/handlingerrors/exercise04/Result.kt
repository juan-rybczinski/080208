package com.fpinkotlin.handlingerrors.exercise04

import java.io.Serializable


sealed class Result<out A>: Serializable {

    abstract fun <B> map(f: (A) -> B): Result<B>

    abstract fun <B> flatMap(f: (A) ->  Result<B>): Result<B>

    fun getOrElse(defaultValue: @UnsafeVariance A): A = when (this) {
        is Failure -> defaultValue
        is Success -> this.value
    }

    fun getOrElse(defaultValue: () -> @UnsafeVariance A): A = when (this) {
        is Failure -> defaultValue()
        is Success -> this.value
    }

    fun orElse(defaultValue: () -> Result<@UnsafeVariance A>): Result<A> = when (this) {
        is Failure -> try {
            defaultValue()
        } catch (e: RuntimeException) {
            Result.failure<A>(e)
        } catch (e: Exception) {
            Result.failure<A>(RuntimeException(e))
        }
        is Success -> this
    }

    internal class Failure<out A>(private val exception: RuntimeException): Result<A>() {

        override fun <B> map(f: (A) -> B): Result<B> = Failure(exception)

        override fun <B> flatMap(f: (A) -> Result<B>): Result<B> = Failure(exception)

        override fun toString(): String = "Failure(${exception.message})"
    }

    internal class Success<out A>(internal val value: A) : Result<A>() {

        override fun <B> map(f: (A) -> B): Result<B> = try {
            Success(f(value))
        } catch (e: RuntimeException) {
            Failure(e)
        } catch (e: Exception) {
            Failure(RuntimeException(e))
        }

        override fun <B> flatMap(f: (A) -> Result<B>): Result<B> = try {
            f(value)
        } catch (e: RuntimeException) {
            Failure(e)
        } catch (e: Exception) {
            Failure(RuntimeException(e))
        }

        override fun toString(): String = "Success($value)"
    }

    companion object {

        operator fun <A> invoke(a: A? = null): Result<A> = when (a) {
            null -> Failure(NullPointerException())
            else -> Success(a)
        }

        fun <A> failure(message: String): Result<A> = Failure(IllegalStateException(message))

        fun <A> failure(exception: RuntimeException): Result<A> = Failure(exception)

        fun <A> failure(exception: Exception): Result<A> = Failure(IllegalStateException(exception))
    }
}

