package com.soopeach.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.soopeach.presentation.ui.theme.PLColor
import com.soopeach.presentation.ui.theme.PLTypography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemberStatusCard(
    cardColor: Color,
    onCardClicked: () -> Unit,
    name: String,
    status: String,
    imageUrl: String = "https://picsum.photos/250/250",
    onCardLongClicked: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = cardColor)
            .combinedClickable(
                onClick = {
                    onCardClicked()
                },
                onLongClick = {
                    onCardLongClicked()
                },
            )
            .padding(horizontal = 16.dp, vertical = 48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = "Member Image",
            modifier = Modifier
                .size(200.dp, 150.dp)
                .clip(shape = RoundedCornerShape(14.dp)),
            contentScale = ContentScale.Crop
        )

        Column {
            Text(
                text = name,
                style = PLTypography.Korean.H0.copy(
                    fontSize = 28.sp,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = status,
                style = PLTypography.Korean.H1.copy(
                    color = PLColor.Gray600
                )
            )
        }
    }
}