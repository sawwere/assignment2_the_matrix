package mmcs.assignment2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MatrixHolesTest {
    @Test
    fun test() {
        val matrix = createMatrix(
            height = 5,
            width = 4,
            e = 0
        )
        matrix[0, 0] = 1
        matrix[0, 2] = 1
        matrix[1, 2] = 1
        matrix[2, 0] = 1
        matrix[3, 2] = 1
        println(matrix)
        val actual = findHoles(matrix)

        assertEquals(listOf(4), actual.rows)
        assertEquals(listOf(1, 3), actual.columns)
    }
}