package fr.amanin.bench

import fr.amanin.bench.flattenOptimized
import kotlin.test.Test
import kotlin.test.assertEquals


class CustomFlattenTest {

    @Test
    fun testEmptyArray() {
        assertEquals(emptyList(), emptyArray<Array<String>>().flattenOptimized())
        assertEquals(emptyList(), arrayOf<Array<String>>(emptyArray()).flattenOptimized())
        assertEquals(emptyList(), arrayOf<Array<String>>(emptyArray(), emptyArray()).flattenOptimized())
    }

    @Test
    fun testSimple() {
        assertEquals(listOf("a", "b", "c"), arrayOf(arrayOf("a", "b"), arrayOf("c")).flattenOptimized())
        assertEquals(
            listOf("a", "b", "c", "d"),
            arrayOf(
                arrayOf("a", "b"), emptyArray(), arrayOf("c"), emptyArray(), arrayOf("d")
            ).flattenOptimized())
    }
}