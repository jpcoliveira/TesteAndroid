package jpcoliveira.com.br.testeandroid.extensions

operator fun <T> List<T>.plus(list: List<T>): List<T> {
    val mutable = toMutableList()
    mutable.addAll(list)
    return mutable
}