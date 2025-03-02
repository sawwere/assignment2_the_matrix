package mmcs.assignment2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class MatrixPlusTest {
    @Test
    fun testPlus() {
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
            e = 6
        )
        expected[0, 1] = 8
        expected[1, 0] = 10
        expected[1, 1] = 12
        assertEquals(expected, matrix1 + matrix2)
    }

    @Test
    fun testAddSingleElementMatrices() {
        val matrix1 = createMatrix(
            height = 1,
            width = 1,
            e = 42
        )
        val matrix2 = createMatrix(
            height = 1,
            width = 1,
            e = 58
        )
        val expected = createMatrix(
            height = 1,
            width = 1,
            e = 100
        )
        assertEquals(expected, matrix1 + matrix2)
    }

    @Test
    fun testAddDifferentSizeMatrices() {
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
            matrix1 + matrix2
        }
    }
}