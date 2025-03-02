package mmcs.assignment2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MatrixKeyLockTest {
    @Test
    fun test() {
        val lock = createMatrix(3, 3, 1)
        /**
        1 0 1
        0 1 0
        1 1 1
         */
        lock[0, 1] = 0
        lock[1, 0] = 0
        lock[1, 2] = 0

        val key = createMatrix(2, 2, 1)
        key[0, 1] = 0
        key[1, 0] = 0

        val actual = canOpenLock(key, lock)

        assertTrue(actual.first)
        assertEquals(0, actual.second)
        assertEquals(1, actual.third)
    }
}