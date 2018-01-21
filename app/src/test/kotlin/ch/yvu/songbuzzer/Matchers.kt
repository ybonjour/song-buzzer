package ch.yvu.songbuzzer

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matcher

fun <T> iz(value: T) = `is`(value)
fun <T> iz(matcher: Matcher<T>) = `is`(matcher)
