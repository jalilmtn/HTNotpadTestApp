package com.example.htnotpadtestapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.htnotpadtestapp.presentation.navigation.NavigationTopLevelDestination
import com.example.htnotpadtestapp.presentation.theme.Grey25

@Composable
fun HTBottomBar(
    onNavigateToDestination: (NavigationTopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            NavigationTopLevelDestination.entries.forEach { destination ->
                    val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
                    val iconId =
                        if (selected) destination.iconSelected
                        else destination.icon
                    val textColor =
                        if (selected) MaterialTheme.colorScheme.primary
                        else Grey25
                    Column(
                        modifier
                            .clip(MaterialTheme.shapes.extraSmall)
                            .clickable {
                                onNavigateToDestination.invoke(destination)
                            }, horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = iconId),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        SmallestSpacer()
                        Text(
                            text = destination.name,
                            color = textColor,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }

                }
        }
        Button(
            modifier = Modifier.width(74.dp),

            onClick = onAddClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: NavigationTopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

