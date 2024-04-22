package com.kashif.designsystem.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kashif.designsystem.theme.colors.LocalColors
import com.kashif.designsystem.theme.shapes.LocalShapes
import com.kashif.designsystem.theme.spacing.LocalSpacing

/**
 * A customizable text input field with optional leading and trailing icons.
 * @param modifier The modifier to be applied to the composable.
 * @param keyboardType The type of keyboard to be displayed when this field is in focus.
 * @param label The optional label to be displayed above the input field.
 * @param placeHolder The optional placeholder text to be displayed when the field is empty.
 * @param textLimit The maximum character limit allowed for the input.
 * @param isError Set to true if the input has an error, which will change the appearance of the
 *   field.
 * @param value The current value of the input field.
 * @param isSingleLine Set to true to restrict the input to a single line.
 * @param enabled Set to false to disable user interaction with the input field.
 * @param readOnly Set to true to make the input read-only (non-editable).
 * @param imeAction The type of IME (Input Method Editor) action to be displayed on the keyboard.
 * @param onTextChanged The callback to be invoked when the text in the input field changes.
 */
@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    label: String? = null,
    placeHolder: String? = null,
    textLimit: Int = 1000,
    isError: Boolean = false,
    errorText: String? = null,
    value: String = "",
    isSingleLine: Boolean = true,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    containerHeight: Dp = 44.dp,
    onTextChanged: ((String) -> Unit)? = null,
) {

    var isFocused by remember { mutableStateOf(false) }

    var textColor = LocalColors.current.fieldPlaceHolderText
    var color = LocalColors.current.grey
    when {
        isError -> {
            textColor = LocalColors.current.error
            color = LocalColors.current.error
        }

        isFocused -> {
            textColor = LocalColors.current.primary
            color = LocalColors.current.primary
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(LocalSpacing.current.xs)
    ) {
        OutlinedTextField(keyboardActions = keyboardActions,
            isError = isError,
            enabled = enabled,
            value = value,
            readOnly = readOnly,
            onValueChange = { value ->
                if (value.length <= textLimit) {
                    if (onTextChanged != null) {
                        onTextChanged(value)
                    }
                }
            },
            placeholder = {
                placeHolder?.let {
                    Text(
                        placeHolder,
                        style = MaterialTheme.typography.body1,
                        color = LocalColors.current.fieldPlaceHolderText
                    )
                }
            },
            modifier = Modifier
                .height(
                    containerHeight
                )
                .onFocusChanged { isFocused = it.isFocused }
                .fillMaxWidth(),
            singleLine = isSingleLine,
            shape = LocalShapes.current.textFieldShape,
            textStyle = MaterialTheme.typography.body1,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = color,
                unfocusedBorderColor = LocalColors.current.grey,
                backgroundColor = LocalColors.current.surface,
                focusedLabelColor = LocalColors.current.primary,
                placeholderColor = LocalColors.current.onSurface,
                unfocusedLabelColor = LocalColors.current.greyMid,
                errorBorderColor = LocalColors.current.error,
                errorLabelColor = LocalColors.current.error,
                disabledBorderColor = LocalColors.current.grey
            ),
            label = label?.let {
                {
                    Text(
                        color = textColor,
                        text = label,
                        style = if (isFocused || value.isBlank()
                                .not()
                        ) MaterialTheme.typography.body2
                        else MaterialTheme.typography.body1
                    )
                }
            } ?: run { null })
        if (isError && errorText != null) {
            Text(
                text = errorText,
                style = MaterialTheme.typography.body2.copy(LocalColors.current.error)
            )
        }
    }
}


@Composable
fun setNullableIcon(
    @DrawableRes icon: Int?, contentDescription: String? = null
): @Composable (() -> Unit)? {
    return if (icon != null) {
        {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(icon),
                contentDescription = contentDescription,
                tint = LocalColors.current.onSurface
            )
        }
    } else {

        null
    }
}