package fr.amanin.bench

import kotlin.test.Test
import kotlin.test.assertEquals

class CustomToListTest {

    @Test
    fun testToListUsingListOf() {
        var array = arrayOf<String?>()
        assertEquals(array.toList(), array.toListUsingListOf())
        array = arrayOf(null)
        assertEquals(array.toList(), array.toListUsingListOf())
        array = arrayOf("a", "b", "c")
        assertEquals(array.toList(), array.toListUsingListOf())
    }

    @Test
    fun testToListUsingAsList() {
        var array = arrayOf<String?>()
        assertEquals(array.toList(), array.toListUsingAsList())
        array = arrayOf(null)
        assertEquals(array.toList(), array.toListUsingAsList())
        array = arrayOf("a", "b", "c")
        assertEquals(array.toList(), array.toListUsingAsList())
    }
}