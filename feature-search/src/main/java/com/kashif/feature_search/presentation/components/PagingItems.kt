package com.kashif.feature_search.presentation.components

import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import com.kashif.data.network.Api

inline fun <T> LazyGridScope.paging(
    items: List<T>,
    currentPage: Int,
    threshold: Int = 4,
    pageSize: Int = Api.PAGING_SIZE,
    crossinline fetch: () -> Unit,
    crossinline itemContent: @Composable LazyGridItemScope.(item: T) -> Unit,
) {

    itemsIndexed(items, key = { _, item -> item.hashCode() }, contentType = { _, item ->
        item!!::class.java.name
    }) { index, item ->

        itemContent(item)

        if ((index + threshold + 1) >= pageSize * (currentPage - 1)) {
            fetch()
        }
    }
}