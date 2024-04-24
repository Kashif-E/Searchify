package com.kashif.feature_search.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.kashif.designsystem.components.TextInputField

@Composable
fun SearchCardWithBorder(
    modifier: Modifier = Modifier,
    valueText: String = "",
    label: String = "",
    onTextChange: (text: String) -> Unit,
    onSubmit: () -> Unit,
) {
    TextInputField(
        modifier = modifier.testTag("search")
            .fillMaxWidth(),
        label = label,
        placeHolder = label,
        value = valueText,
        isSingleLine = true,
        imeAction = ImeAction.Search,
        containerHeight = 56.dp,
        keyboardActions = KeyboardActions {
            onSubmit()
        },
        onTextChanged = onTextChange
    )


}
