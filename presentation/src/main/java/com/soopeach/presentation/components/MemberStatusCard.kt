package com.soopeach.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.style.TextAlign
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
    position: String,
    imageUrl: String = "https://picsum.photos/250/250",
    onCardLongClicked: () -> Unit
) {
    Box {
        Row(
            Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = PLColor.Main)
                .combinedClickable(
                    onClick = {
                        onCardClicked()
                    },
                    onLongClick = {
                        onCardLongClicked()
                    },
                )
                .padding(horizontal = 16.dp, vertical = 40.dp),
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 24.dp),
                    text = position,
                    style = PLTypography.Korean.B1_regular.copy(
                        color = PLColor.Gray600
                    ),
                    textAlign = TextAlign.End
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = name,
                    style = PLTypography.Korean.H0.copy(
                        fontSize = 28.sp,
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(6.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Text(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(PLColor.getColor(status))
                    .padding(vertical = 4.dp, horizontal = 8.dp),
                text = status,
                style = PLTypography.Korean.B1_semiBold.copy(
                    color = PLColor.Gray600
                )
            )
        }
    }
}