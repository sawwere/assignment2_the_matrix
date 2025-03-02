package mmcs.assignment2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class MatrixTimesTest {
    @Test
    fun testTimes() {
        val matrix1 = createMatrix(
            height = 2,
            width = 2,
            e = 1
        )
        matrix1[0, 1] = 2
        matrix1[1, 0] = 3
        matrix1[1, 1] = 4
        val matrix2 = createMatrix(
            height = 2,
            width = 2,
            e = 5
        )
        matrix2[0, 1] = 6
        matrix2[1, 0] = 7
        matrix2[1, 1] = 8
        val expected = createMatrix(
            height = 2,
            width = 2,
            e = 19
        )
        expected[0, 1] = 22
        expected[1, 0] = 43
        expected[1, 1] = 50
        assertEquals(expected, matrix1 * matrix2)
    }

    @Test
    fun testTimesSingleElementMatrices() {
        val matrix1 = createMatrix(
            height = 1,
            width = 1,
            e = 10
        )
        val matrix2 = createMatrix(
            height = 1,
            width = 1,
            e = 12
        )
        val expected = createMatrix(
            height = 1,
            width = 1,
            e = 120
        )
        assertEquals(expected, matrix1 * matrix2)
    }

    @Test
    fun testTimesDifferentSizeMatrices() {
        val matrix1 = createMatrix(
            height = 2,
            width = 2,
            e = 1
        )
        matrix1[0, 1] = 2
        matrix1[1, 0] = 3
        matrix1[1, 1] = 4
        val matrix2 = createMatrix(
            height = 1,
            width = 1,
            e = 58
        )
        assertFailsWith<IllegalArgumentException> {
            matrix1 * matrix2
        }
    }
}