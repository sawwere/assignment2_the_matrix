@file:Suppress("UNUSED_PARAMETER")
package mmcs.assignment2
/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    return MatrixImpl(width, height, e)
}

/**
 * Реализация интерфейса "матрица"
 */

@Suppress("EqualsOrHashCode")
class MatrixImpl< E>(override val width: Int, override val height: Int, private val e: E ) : Matrix<E>{
    val matr: List<MutableList< E>> = List(height) { MutableList(width) {e}  }

    override fun get(row: Int, column: Int): E {
        return matr.get(row).get(column)
    }

    override fun get(cell: Cell): E {
        return matr.get(cell.row).get(cell.column)
    }

    override fun set(row: Int, column: Int, value: E) {
        matr[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        matr[cell.row][cell.column] = value
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix<*>) {
            return false;
        }
        if (other.height != height
            || other.width != width) {
            return false
        }
        for (row in 0 until height) {
            for (column in 0 until width) {
                if (!get(row, column)!!.equals(other[row, column])) {
                    return false
                }
            }
        }
        return true
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.appendLine("{")
        for (row in 0 until height) {
            for (column in 0 until width) {
                sb.append(get(row, column))
                sb.append(" ")
            }
            sb.appendLine()
        }
        sb.appendLine("} height = ${height} width = ${width}")

        return sb.toString()
    }
}
