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
    return MatrixImpl(height, width, e)
}

/**
 * Реализация интерфейса "матрица"
 */
class MatrixImpl< E>(override val height: Int, override val width: Int, private val e: E ) : Matrix<E>{
    private val matrix: List<MutableList< E>> = List(height) { MutableList(width) {e}  }

    override fun get(row: Int, column: Int): E {
        return matrix[row][column]
    }

    override fun get(cell: Cell): E {
        return matrix[cell.row][cell.column]
    }

    override fun set(row: Int, column: Int, value: E) {
        matrix[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        matrix[cell.row][cell.column] = value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true;
        }
        if (other !is Matrix<*>) {
            return false;
        }
        if (other.height != height || other.width != width) {
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


    override fun hashCode(): Int {
        return matrix.hashCode()
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.appendLine("MatrixImpl {")
        for (row in 0 until height) {
            for (column in 0 until width) {
                sb.append(get(row, column))
                sb.append(" ")
            }
            sb.appendLine()
        }
        sb.appendLine("} height = $height width = $width")

        return sb.toString()
    }
}
