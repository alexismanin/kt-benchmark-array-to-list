package fr.amanin.bench

import kotlin.random.Random
import kotlinx.benchmark.*

@Warmup(iterations = 5, time = 2)
@Measurement(iterations = 5, time = 5)
@State(Scope.Benchmark)
open class StringArrayTakeWhile {


    @Param("0", "1", "3", "32", "1000", "100000")
    var arraySize: Int? = null
    lateinit var array: Array<String>

    @Setup
    open fun init() {
        val arraySize = checkNotNull(arraySize) { "arraySize parameter not set"}
        array = Array(arraySize) { Random.nextInt(0, 200).toString() }
        if (arraySize > 1) array[arraySize/2] = "STOP"
    }

    @Benchmark
    open fun takeWhileStd() = array.takeWhile { it != "STOP" }

    @Benchmark
    open fun takeWhileNoLoop() = array.takeWhileNoLoop { it != "STOP" }


    @Benchmark
    open fun takeWhileSuggestion() = array.takeWhileSuggestion { it != "STOP" }
}