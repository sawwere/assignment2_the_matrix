package mmcs.assignment2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MatrixTransposeTest {
    @Test
    fun testTransposeSquareMatrix() {
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
            e = 1
        )
        expected[0, 1] = 4
        expected[0, 2] = 7
        expected[1, 0] = 2
        expected[1, 1] = 5
        expected[1, 2] = 8
        expected[2, 0] = 3
        expected[2, 1] = 6
        expected[2, 2] = 9

        assertEquals(expected, transpose(matrix))
    }

    @Test
    fun testTransposeTallMatrix() {
        val matrix = createMatrix(
            height = 3,
            width = 2,
            e = 1
        )
        matrix[0, 1] = 2
        matrix[1, 0] = 3
        matrix[1, 1] = 4
        matrix[2, 0] = 5
        matrix[2, 1] = 6

        val expected = createMatrix(
            height = 2,
            width = 3,
            e = 1
        )
        expected[0, 1] = 3
        expected[0, 2] = 5
        expected[1, 0] = 2
        expected[1, 1] = 4
        expected[1, 2] = 6


        assertEquals(expected, transpose(matrix))
    }

    @Test
    fun testTransposeSingleElementMatrix() {
        val matrix = createMatrix(
            height = 1,
            width = 1,
            e = 42
        )
        val expected = createMatrix(
            height = 1,
            width = 1,
            e = 42
        )
        assertEquals(expected, transpose(matrix))
    }

    @Test
    fun testTransposeStringMatrix() {
        val matrix = createMatrix(
            height = 2,
            width = 2,
            e = "a"
        )
        matrix[0, 1] = "b"
        matrix[1, 0] = "c"
        matrix[1, 1] = "d"

        val expected = createMatrix(
            height = 2,
            width = 2,
            e = "a"
        )

        expected[0, 1] = "c"
        expected[1, 0] = "b"
        expected[1, 1] = "d"
        assertEquals(expected, transpose(matrix))
    }
}