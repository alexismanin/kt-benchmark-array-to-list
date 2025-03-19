package fr.amanin.bench

import kotlin.random.Random
import kotlinx.benchmark.*

@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@State(Scope.Benchmark)
open class StringArrayTakeWhile {


    @Param("3", "32", "1000", "100000")
    var arraySize: Int? = null
    lateinit var array: Array<String>

    @Setup
    open fun init() {
        val arraySize = checkNotNull(arraySize) { "arraySize parameter not set"}
        array = Array(arraySize) { Random.nextInt(0, 200).toString() }
        array[arraySize/2] = "STOP"
    }

    @Benchmark
    open fun takeWhileStd() = array.takeWhile { it != "STOP" }

    @Benchmark
    open fun customTakeWhile() = array.takeWhileNoLoop { it != "STOP" }
}