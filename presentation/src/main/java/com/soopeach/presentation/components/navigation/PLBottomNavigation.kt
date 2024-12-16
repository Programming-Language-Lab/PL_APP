package com.soopeach.presentation.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soopeach.presentation.Screen
import com.soopeach.presentation.ui.theme.PLColor

@Composable
fun PLBottomNavigation(
    bottomNavigationItems: List<Screen>,
    selectedScreenId: String,
    onItemCallback: (Screen) -> Unit
) {

    Box(
        Modifier
            .background(color = PLColor.Gray800)
            .height(64.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            bottomNavigationItems.forEach { screen ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    val interactionSource = remember { MutableInteractionSource() }
                    BottomNavItem(
                        modifier = Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            onItemCallback(screen)
                        },
                        screen = screen,
                        isSelected = selectedScreenId == screen.route
                    )
                }
            }

        }
    }

}