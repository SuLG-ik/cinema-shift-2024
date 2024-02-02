package ru.sulgik.card.ui

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text.transform(),
            DateOffsetMapping,
        )
    }


    private fun AnnotatedString.transform(): AnnotatedString {
        if (length <= 2) {
            return this
        }
        return buildAnnotatedString {
            append(text[0])
            append(text[1])
            append("/")
            for (i in 2 until text.length) {
                append(text[i])
            }
        }
    }

}

object DateOffsetMapping : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        if (offset > 2) {
            return offset + 1
        }
        return offset
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset > 3) {
            return offset - 1
        }
        return offset
    }


}
