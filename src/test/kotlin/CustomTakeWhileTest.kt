package fr.amanin.bench

import kotlin.test.Test
import kotlin.test.assertEquals

class CustomTakeWhileTest {

    @Test
    fun takeWhileTest() {
        val array = arrayOf("1", "2", "3", "4", "5", "6", "7")
        val condition : (String) -> Boolean = { i -> i.toInt() < 5 }
        assertEquals(array.takeWhile(condition), array.takeWhileNoLoop(condition))
    }

    @Test
    fun takeWhileNeverMeetConditionTest() {
        assertEquals(emptyList(), arrayOf("1", "2", "3").takeWhileNoLoop { false })
    }
}
