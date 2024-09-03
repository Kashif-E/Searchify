package com.example.shared.domain.utils



import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun <T, R> suspendMap(list: List<T>, transform: suspend (T) -> R): List<R> =
    withContext(Dispatchers.Default) {
        list.suspendMap { element -> withContext(Dispatchers.Default) { transform(element) } }
    }
suspend inline fun <T, R> Iterable<T>.suspendMap(crossinline transform: suspend (T) -> R): List<R> =
    withContext(Dispatchers.Default) {
        mapTo(ArrayList<R>(collectionSizeOrDefault(10))) { element ->
            withContext(Dispatchers.Default) { transform(element) }
        }
    }

fun <T> Iterable<T>.collectionSizeOrDefault(default: Int): Int =
    if (this is Collection<*>) this.size else default
