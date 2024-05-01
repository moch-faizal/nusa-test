package com.nuta.tes.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.nuta.tes.extensions.toDp
import com.nuta.tes.extensions.toSp
import com.nuta.tes.ui.theme.Line
import com.nuta.tes.ui.theme.NeutralGray
import com.nuta.tes.ui.theme.PrimaryColor

@ExperimentalComposeUiApi
@Composable
fun FormInputField(
    modifier: Modifier = Modifier,
    modifierTextField: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholderText: String = "",
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    passwordVisibility: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    fontSize: TextUnit = 28.toSp(),
    maxLines: Int = 1,
    maxChar: Int? = null,
    isEnable: Boolean = true,
    roundedCornerShape: Dp = 8.toDp(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = imeAction, keyboardType = keyboardType, autoCorrect = false
    ),
    keyboardAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(enabled = isEnable,
        modifier = modifierTextField
            .fillMaxWidth()
            .height(110.toDp()),
        value = value,
        onValueChange = {
            if (maxChar != null) {
                if (it.length <= maxChar) onValueChange(it)
            } else {
                onValueChange(it)
            }
        },
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        }, onSearch = {
            keyboardAction()
            keyboardController?.hide()
        }, onGo = {
            keyboardAction()
            keyboardController?.hide()
        }, onSend = {
            keyboardAction()
            keyboardController?.hide()
        }),
        visualTransformation = if (visualTransformation == VisualTransformation.None && !passwordVisibility) {
            PasswordVisualTransformation()
        } else {
            visualTransformation
        },
        cursorBrush = SolidColor(PrimaryColor),
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black, fontSize = fontSize
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier
                    .background(Color.White)
                    .border(
                        BorderStroke(
                            width = 1.dp,
                            color = Line
                        ), RoundedCornerShape(roundedCornerShape)
                    )
                    .height(110.toDp()),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 24.toDp())
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholderText,
                            color = NeutralGray,
                            fontSize = fontSize,
                        )
                    }
                    innerTextField()
                }
            }
        })
}