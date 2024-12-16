package com.soopeach.presentation.components.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.soopeach.presentation.utils.conditional
import com.soopeach.presentation.Screen
import com.soopeach.presentation.ui.theme.PLColor
import com.soopeach.presentation.ui.theme.PLTypography

@Composable
fun BottomNavItem(
    modifier: Modifier = Modifier,
    screen: Screen,
    isSelected: Boolean,
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier
                .height(if (isSelected) 44.dp else 26.dp)
                .conditional(isSelected) {
                    background(
                        color = PLColor.Gray500,
                        shape = RoundedCornerShape(5.dp)
                    )
                }
                .padding(horizontal = if (isSelected) 11.dp else 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Icon(
                painterResource(
                    if (isSelected) requireNotNull(screen.selectedIcon) else requireNotNull(
                        screen.unSelectedIcon
                    )
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
                tint = if (isSelected) Color.White else PLColor.Gray400
            )

            AnimatedVisibility(visible = isSelected) {
                Row {

                    Spacer(modifier = Modifier.size(6.dp))

                    Text(
                        text = screen.title,
                        style = PLTypography.Korean.B1_semiBold.copy(
                            color = Color.White
                        ),
                    )
                }
            }
        }
    }
}