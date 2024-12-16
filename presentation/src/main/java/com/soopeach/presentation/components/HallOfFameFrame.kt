package com.soopeach.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soopeach.domain.model.ProjectItem
import com.soopeach.presentation.ui.theme.PLTypography

@Composable
fun HallOfFameFrame(
    projectItem: ProjectItem,
    onClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable {
                onClicked(projectItem.projectUrl)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            model = projectItem.imageUrl,
            contentDescription = "프로젝트 이미지",
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(projectItem.colorCode))
                .padding(vertical = 16.dp),
            text = projectItem.name,
            style = PLTypography.Korean.H0,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}