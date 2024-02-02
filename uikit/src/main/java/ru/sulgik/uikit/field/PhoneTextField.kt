package ru.sulgik.uikit.field

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import ru.sulgik.uikit.R
import ru.sulgik.uikit.UIKitOutlineTextField

@Composable
fun UIKitPhoneTextField(
    phone: String,
    onPhoneChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorText: @Composable() (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
) {
    UIKitOutlineTextField(
        title = stringResource(R.string.phone_number_title),
        value = phone,
        onValueChange = onPhoneChanged,
        placeholder = { Text(stringResource(R.string.phone_number_placeholder)) },
        visualTransformation = PhoneVisualTransformation(),
        singleLine = true,
        errorText = errorText,
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        modifier = modifier,
    )
}

class PhoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trans = format(text)
        return TransformedText(
            text = trans,
            PhoneOffsetMapping,
        )
    }

    private fun format(input: AnnotatedString): AnnotatedString {
        return buildAnnotatedString {
            append("+7 ")
            var code = getCode(input.text, 0, 3)
            append(code)
            code = getCode(input.text, 3, 3)
            append(code)
            code = getCode(input.text, 6, 2)
            append(code)
            code = getCode(input.text, 8, 2)
            append(code)
        }
    }

    fun getCode(text: String, start: Int, length: Int): String {
        if (text.length <= start)
            return ""
        return buildString {
            val code = text.sliceIfAvailable(start until start + length)
            append(code)
            if (code.length < length)
                return@buildString
            if (code.length == length)
                append(" ")
        }
    }

}


private fun String.sliceIfAvailable(indices: IntRange): String {
    if (length <= indices.first)
        return ""
    if (length <= indices.last + 1)
        return slice(indices.start until length)
    return slice(indices)
}

object PhoneOffsetMapping : OffsetMapping {

    const val BASE_OFFSET = 3

    override fun originalToTransformed(offset: Int): Int {
        return when {
            offset < 3 -> BASE_OFFSET + offset
            offset < 6 -> BASE_OFFSET + offset + 1
            offset < 8 -> BASE_OFFSET + offset + 2
            else -> BASE_OFFSET + offset + 3
        }
    }

    override fun transformedToOriginal(offset: Int): Int {
        return when {
            offset < BASE_OFFSET + 3 -> maxOf(0, offset - BASE_OFFSET)
            offset < BASE_OFFSET + 6 -> offset - BASE_OFFSET - 1
            offset < BASE_OFFSET + 8 -> offset - BASE_OFFSET - 2
            else -> minOf(offset - BASE_OFFSET - 3, 10)
        }
    }
}
