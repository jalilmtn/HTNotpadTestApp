package com.example.htnotpadtestapp.presentation.addnote.components
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.common.myTabIndicatorOffset

@Composable
fun TextSettingTabView() {
    val selectedTabPosition = remember {
        mutableIntStateOf(0)
    }
    val tabWith = 204
    TabRow(
        modifier = Modifier
            .width(tabWith.dp)
            .padding(start = 8.dp),
        selectedTabIndex = selectedTabPosition.intValue,
        divider = {
        },
        containerColor = Color.Transparent,
        indicator = @Composable { tabPositions ->
            if (selectedTabPosition.intValue < tabPositions.size) {
                TabRowDefaults.SecondaryIndicator(
                    Modifier.myTabIndicatorOffset(
                        tabPositions[selectedTabPosition.intValue],
                        tabCount = 5,
                        tabWith = tabWith
                    ),
                    height = 4.dp,
                    color = Color.White
                )
            }
        }
    ) {
        IconButton(
            modifier = Modifier.padding(top = 6.dp),
            onClick = {
                selectedTabPosition.intValue = 0
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_eraser),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        IconButton(
            modifier = Modifier.padding(top = 6.dp),
            onClick = { selectedTabPosition.intValue = 1 }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        IconButton(
            modifier = Modifier.padding(top = 6.dp),
            onClick = { selectedTabPosition.intValue = 2 }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_link),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        IconButton(
            modifier = Modifier.padding(top = 6.dp),
            onClick = { selectedTabPosition.intValue = 3 }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_font_size),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        IconButton(
            modifier = Modifier.padding(top = 6.dp),
            onClick = { selectedTabPosition.intValue = 4 }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_undo),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}