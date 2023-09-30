package com.br.highbee.view
import kotlin.random.Random
object RandomNum {
    fun get(min: Int, max: Int): String {
        return String.format("%06d", Random.nextInt(min, max))
    }
}