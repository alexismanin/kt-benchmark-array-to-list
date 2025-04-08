package fr.amanin.bench

fun <T> Array<T>.takeUntilNoLoop(stopCondition: (T) -> Boolean) : List<T> {
    return when (size) {
        0 -> emptyList()
        1 -> if (stopCondition(this[0])) emptyList() else listOf(this[0])
        else -> {
            var i = 0
            while (i < size && !stopCondition(this[i])) i++
            return if (i == 0) emptyList() else copyOfRange(0, i).asList()
        }
    }
}

fun <T> Array<T>.takeLastUntilNoLoop(stopCondition: (T) -> Boolean) : List<T> {
    return when (size) {
        0 -> emptyList()
        1 -> if (stopCondition(this[0])) emptyList() else listOf(this[0])
        else -> {
            var i = lastIndex
            while (i >= 0 && !stopCondition(this[i])) i++
            return if (i >= lastIndex) emptyList() else copyOfRange(i, size).asList()
        }
    }
}


fun <T> Array<T>.takeWhileNoLoop(predicate: (T) -> Boolean) : List<T> {
    return when (size) {
        0 -> emptyList()
        1 -> if (predicate(this[0])) listOf(this[0]) else emptyList()
        else -> {
            var i = 0
            while (i < size && predicate(this[i])) i++
            return if (i == 0) emptyList() else copyOfRange(0, i).asList()
        }
    }
}

fun <T> Array<T>.takeLastWhileNoLoop(predicate: (T) -> Boolean) : List<T> {
    return when (size) {
        0 -> emptyList()
        1 -> if (predicate(this[0])) listOf(this[0]) else emptyList()
        else -> {
            var i = lastIndex
            while (i >= 0 && predicate(this[i])) i++
            return if (i >= lastIndex) emptyList() else copyOfRange(i, size).asList()
        }
    }
}

fun <T> Array<T>.takeWhileSuggestion(predicate: (T) -> Boolean) : List<T> {
    var i = 0
    while (i < size && predicate(this[i])) i++
    return if (i == 0) emptyList() else this.copyOfRange(0, i).asList()
}
