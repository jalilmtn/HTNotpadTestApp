package com.example.htnotpadtestapp.presentation.addnote.components
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.htnotpadtestapp.R

@Composable
fun FontSettingView() {
    Row {
        IconToggleButton(
            checked = true,
            onCheckedChange = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_textalign_left),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        IconToggleButton(
            checked = false,
            onCheckedChange = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_textalign_center),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        IconToggleButton(
            checked = false,
            onCheckedChange = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_textalign_right),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        IconToggleButton(
            checked = false,
            onCheckedChange = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_text_underline),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        IconToggleButton(
            checked = false,
            onCheckedChange = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pharagraph_spacing),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}