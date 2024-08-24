package com.kashif.feature_search.presentation.components

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import com.example.shared.data.remote.Api


inline fun <T> LazyStaggeredGridScope.paging(
    items: List<T>,
    currentPage: Int,
    threshold: Int = 4,
    pageSize: Int = Api.PAGING_SIZE,
    crossinline fetch: () -> Unit,
    crossinline itemContent: @Composable LazyStaggeredGridItemScope.(item: T, index: Int) -> Unit,
) {

    itemsIndexed(items, key = { _, item -> item.hashCode() }, contentType = { _, item ->
        item!!::class.java.name
    }) { index, item ->

        itemContent(item, index)

        if ((index + threshold + 1) >= pageSize * (currentPage - 1)) {
            fetch()
        }
    }
}