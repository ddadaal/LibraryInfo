package libraryinfo.util

fun <E> arrayListOf(vararg items: E): ArrayList<E> {
    val list = ArrayList<E>()
    items.forEach { list.add(it) }
    return list
}