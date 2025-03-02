package mmcs.assignment2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MatrixUnaryMinusTest {
    @Test
    fun testUnaryMinusMatrix() {
        val matrix = createMatrix(
            height = 3,
            width = 3,
            e = 1
        )
        matrix[0, 1] = 2
        matrix[0, 2] = 3
        matrix[1, 0] = 4
        matrix[1, 1] = 5
        matrix[1, 2] = 6
        matrix[2, 0] = 7
        matrix[2, 1] = 8
        matrix[2, 2] = 9

        val expected = createMatrix(
            height = 3,
            width = 3,
            e = -1
        )
        expected[0, 1] = -2
        expected[0, 2] = -3
        expected[1, 0] = -4
        expected[1, 1] = -5
        expected[1, 2] = -6
        expected[2, 0] = -7
        expected[2, 1] = -8
        expected[2, 2] = -9
        assertEquals(expected, -matrix)
    }

    @Test
    fun testUnaryMinusSingleElementMatrix() {
        val matrix = createMatrix(
            height = 1,
            width = 1,
            e = 42
        )
        val expected = createMatrix(
            height = 1,
            width = 1,
            e = -42
        )
        assertEquals(expected, -matrix)
    }
}