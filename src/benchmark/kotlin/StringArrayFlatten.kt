package fr.amanin.bench

import kotlin.random.Random
import kotlinx.benchmark.*

@Warmup(iterations = 5, time = 5)
@Measurement(iterations = 5, time = 5)
@State(Scope.Benchmark)
open class StringArrayFlatten {

    @Param("1", "3", "100")
    var arraySize: Int? = null

    @Param("1", "32", "1000")
    var innerSize: Int? = null

    lateinit var array: Array<Array<String>>

    @Setup
    open fun init() {
        val arraySize = checkNotNull(arraySize) { "arraySize parameter not set"}
        val innerSize = checkNotNull(innerSize) { "innerSize parameter not set"}
        array = Array(arraySize) {
            i ->
            Array(innerSize) { Random.nextInt(0, 200).toString() }
        }
    }

    @Benchmark
    open fun flattenStd() = array.flatten()

    @Benchmark
    open fun flattenOptimized() = array.flattenOptimized()

    @Benchmark
    open fun flattenSuggestion() = array.flattenSuggestion()
}