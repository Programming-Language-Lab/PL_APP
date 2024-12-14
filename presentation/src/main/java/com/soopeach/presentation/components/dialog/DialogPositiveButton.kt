package com.soopeach.presentation.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.soopeach.presentation.ui.theme.PLColor
import com.soopeach.presentation.ui.theme.PLTypography

@Composable
fun DialogPositiveButton(
    text: String,
    onClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(8.dp)
                )
                .background(PLColor.Gray300)
                .clickable {
                    onClicked()
                }
                .padding(vertical = 8.dp, horizontal = 12.dp),
            text = text,
            style = PLTypography.Korean.B2_regular.copy(
                color = Color.White
            )
        )
    }
}