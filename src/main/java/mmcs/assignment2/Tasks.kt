package mmcs.assignment2

/**
 * Пример
 *
 * Транспонировать заданную матрицу matrix.
 */
fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0 until matrix.width) {
        for (j in 0 until matrix.height) {
            result[i, j] = matrix[j, i]
        }
    }
    return result
}

fun <E> rotate(matrix: Matrix<E>): Matrix<E> {
    val result = createMatrix(matrix.width, matrix.height, matrix[0, 0])
    for (row in 0 until matrix.height) {
        for (column in 0 until matrix.width) {
            result[column, row] = matrix[row, column]
        }
    }
    return result
}

/**
 * Сложить две заданные матрицы друг с другом.
 * Складывать можно только матрицы совпадающего размера -- в противном случае бросить IllegalArgumentException.
 * При сложении попарно складываются соответствующие элементы матриц
 */
operator fun Matrix<Int>.plus(other: Matrix<Int>): Matrix<Int> {
    if (other.height != height
        || other.width != width) {
        throw IllegalArgumentException("Нельзя выполнить сложение для матриц разного размера")
    }
    val result = createMatrix(this.width, this.height, this[0, 0])
    for (row in 0 until this.height) {
        for (column in 0 until this.width) {
            result[row, column] = this[row, column] + other[row, column]
        }
    }
    return result
}

/**
 * Инвертировать заданную матрицу.
 * При инвертировании знак каждого элемента матрицы следует заменить на обратный
 */
operator fun Matrix<Int>.unaryMinus(): Matrix<Int> {
    val result = createMatrix(this.width, this.height, this[0, 0])
    for (row in 0 until this.height) {
        for (column in 0 until this.width) {
            result[row, column] = -this[row, column]
        }
    }
    return result
}

/**
 * Перемножить две заданные матрицы друг с другом.
 * Матрицы можно умножать, только если ширина первой матрицы совпадает с высотой второй матрицы.
 * В противном случае бросить IllegalArgumentException.
 * Подробно про порядок умножения см. статью Википедии "Умножение матриц".
 */
operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> {
    // Проверяем, можно ли перемножить матрицы
    if (width != other.height) {
        throw IllegalArgumentException("Матрицы нельзя перемножить: количество столбцов первой матрицы не равно количеству строк второй матрицы.")
    }

    // Создаем результирующую матрицу
    val result = createMatrix(height, width, 0)

    // Перемножаем матрицы
    for (i in 0 until height) {
        for (j in 0 until other.width) {
            for (k in 0 until width) {
                result[i, j] += this[i, k] * other[k, j]
            }
        }
    }

    return result
}


/**
 * Целочисленная матрица matrix состоит из "дырок" (на их месте стоит 0) и "кирпичей" (на их месте стоит 1).
 * Найти в этой матрице все ряды и колонки, целиком состоящие из "дырок".
 * Результат вернуть в виде mmcs.assignment2.Holes(rows = список дырчатых рядов, columns = список дырчатых колонок).
 * Ряды и колонки нумеруются с нуля. Любой из спискоов rows / columns может оказаться пустым.
 *
 * Пример для матрицы 5 х 4:
 * 1 0 1 0
 * 0 0 1 0
 * 1 0 0 0 ==> результат: mmcs.assignment2.Holes(rows = listOf(4), columns = listOf(1, 3)): 4-й ряд, 1-я и 3-я колонки
 * 0 0 1 0
 * 0 0 0 0
 */
fun findHoles(matrix: Matrix<Int>): Holes {
    val rowHoles = HashMap<Int, Int>()
    val columnHoles = HashMap<Int, Int>()
    for (row in 0 until matrix.height) {
        rowHoles[row] = 0
        for (column in 0 until matrix.width) {
            rowHoles[row] = rowHoles[row]!!.plus(matrix[row, column])
            columnHoles[column] = columnHoles.getOrDefault(column, 0).plus(matrix[row, column])
        }
    }
    return Holes(rowHoles.filter { (k, v) -> v == 0 }.keys.toList(), columnHoles.filter { (k, v) -> v == 0 }.keys.toList())
}

/**
 * Класс для описания местонахождения "дырок" в матрице
 */
data class Holes(val rows: List<Int>, val columns: List<Int>)

/**
 * Даны мозаичные изображения замочной скважины и ключа. Пройдет ли ключ в скважину?
 * То есть даны две матрицы key и lock, key.height <= lock.height, key.width <= lock.width, состоящие из нулей и единиц.
 *
 * Проверить, можно ли наложить матрицу key на матрицу lock (без поворота, разрешается только сдвиг) так,
 * чтобы каждой единице в матрице lock (штырь) соответствовал ноль в матрице key (прорезь),
 * а каждому нулю в матрице lock (дырка) соответствовала, наоборот, единица в матрице key (штырь).
 * Ключ при сдвиге не может выходить за пределы замка.
 *
 * Пример: ключ подойдёт, если его сдвинуть на 1 по ширине
 * lock    key
 * 1 0 1   1 0
 * 0 1 0   0 1
 * 1 1 1
 *
 * Вернуть тройку (Triple) -- (да/нет, требуемый сдвиг по высоте, требуемый сдвиг по ширине).
 * Если наложение невозможно, то первый элемент тройки "нет" и сдвиги могут быть любыми.
 */
fun canOpenLock(key: Matrix<Int>, lock: Matrix<Int>): Triple<Boolean, Int, Int> {

    // Перебираем все возможные сдвиги ключа относительно замка
    for (shiftHeight in 0..lock.height - key.height) {
        for (shiftWidth in 0..lock.width - key.width) {
            var isFit = true

            // Проверяем, подходит ли ключ при текущем сдвиге
            for (i in 0 until key.height) {
                for (j in 0 until key.width) {
                    // Если в замке штырь (1), то в ключе должна быть прорезь (0)
                    // Если в замке дырка (0), то в ключе должен быть штырь (1)
                    if (lock[shiftHeight + i, shiftWidth + j] == key[i, j]) {
                        isFit = false
                        break
                    }
                }
                if (!isFit) break
            }

            // Если ключ подошел, возвращаем результат
            if (isFit) {
                return Triple(true, shiftHeight, shiftWidth)
            }
        }
    }

    // Если ни один сдвиг не подошел
    return Triple(false, -1, -1)
}

