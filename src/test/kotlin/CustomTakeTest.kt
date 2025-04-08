package fr.amanin.bench

import fr.amanin.bench.takeLastNoLoop
import fr.amanin.bench.takeNoLoop
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomTakeTest {

    @Test
    fun testTakeNoLoop() {
        var array = arrayOf<String?>()
        assertEquals(array.take(0), array.takeNoLoop(0))
        array = arrayOf(null)
        assertEquals(array.take(1), array.takeNoLoop(1))
        array = arrayOf("a", "b", "c")
        assertEquals(array.take(2), array.takeNoLoop(2))
    }

    @Test
    fun testTakeLastNoLoop() {
        var array = arrayOf<String?>()
        assertEquals(array.takeLast(0), array.takeLastNoLoop(0))
        array = arrayOf(null)
        assertEquals(array.takeLast(1), array.takeLastNoLoop(1))
        array = arrayOf("a", "b", "c")
        assertEquals(array.takeLast(2), array.takeLastNoLoop(2))
    }
}