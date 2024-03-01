package com.example.htnotpadtestapp.presentation.notes.components
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.presentation.components.HTAppBar
import com.example.htnotpadtestapp.presentation.components.ProfileView
import com.example.htnotpadtestapp.presentation.theme.textColor2

@Composable
fun NotesTopAppBar() {
    HTAppBar(
        title = {
            Row {
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Alireza Abbasi",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.textColor2
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_scroll),
                    contentDescription = ""
                )
            }
        },
        navigationIcon = {
            ProfileView(
                avatars = listOf(R.drawable.user1),
                paddingUnit = 20.dp,
                avatarHeight = 40.dp
            )
        },
        actions = {
            IconButton(
                onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification_status),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    )
            }
            IconButton(
                onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_direct_inbox),
                    tint = Color.Unspecified,
                    contentDescription = ""
                )
            }
        },
    )
}