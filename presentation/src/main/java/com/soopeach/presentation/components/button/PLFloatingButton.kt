package com.soopeach.presentation.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.soopeach.presentation.ui.theme.PLColor

@Composable
fun PLFloatingButton(
    onClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(25.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            modifier =
            Modifier.size(52.dp),
            onClick = {
                onClicked()
            },
            containerColor = PLColor.Gray400,
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "버튼",
                tint = Color.White
            )
        }
    }
}