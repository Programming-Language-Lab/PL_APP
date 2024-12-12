package com.soopeach.presentation.components.bottomSheet

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soopeach.presentation.ui.theme.PLColor

@Composable
fun SheetHandler() {
    Column {
        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier
                .width(43.dp)
                .height(4.dp)
                .border(3.dp, PLColor.Gray400)
        )

        Spacer(modifier = Modifier.height(15.dp))
    }
}