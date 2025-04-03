package fr.amanin.bench

fun <T> Array<Array<T>>.flattenOptimized() : List<T> {
    return when (size) {
        0 -> emptyList()
        1 -> this[0].toList()
        else -> {
            val total: Long = sumOf { it.size.toLong() }
            check(total <= Int.MAX_VALUE.toLong()) {
                "Combining arrays in a single one is not possible, it would overflow"
            }

            val flattenArray = arrayOfNulls<Any?>(total.toInt())
            var offset = 0
            for (i in 0..<size) {
                this[i].copyInto(flattenArray, offset)
                offset += this[i].size
            }
            @Suppress("UNCHECKED_CAST")
            return flattenArray.asList() as List<T>
        }
    }
}