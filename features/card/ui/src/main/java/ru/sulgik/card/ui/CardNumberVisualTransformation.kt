package ru.sulgik.card.ui

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text.transform(),
            CardOffsetMapping,
        )
    }


    private fun AnnotatedString.transform(): AnnotatedString {
        if (length <= 4) {
            return this
        }
        return buildAnnotatedString {
            append(text.substring(0..3))
            append(" ")
            for (i in 4 until text.length) {
                append(text[i])
            }
        }
    }

}

object CardOffsetMapping : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        if (offset > 4) {
            return offset + 1
        }
        return offset
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset > 5) {
            return offset - 1
        }
        return offset
    }

}
