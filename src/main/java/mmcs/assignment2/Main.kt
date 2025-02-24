package mmcs.assignment2;

fun main() {
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

    val result = canOpenLock(key, lock)

    if (result.first) {
        println("Ключ подходит. Сдвиг по высоте: ${result.second}, сдвиг по ширине: ${result.third}")
    } else {
        println("Ключ не подходит.")
    }
}