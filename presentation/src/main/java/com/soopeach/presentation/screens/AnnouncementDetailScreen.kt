package com.soopeach.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soopeach.presentation.ui.theme.PLColor
import com.soopeach.presentation.ui.theme.PLTypography
import com.soopeach.presentation.viewmodel.AnnouncementState
import com.soopeach.presentation.viewmodel.AnnouncementViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnnouncementDetailScreen(
    announcementId: String
) {

    val viewModel = koinViewModel<AnnouncementViewModel>()

    LaunchedEffect(announcementId) {
        viewModel.getAnnouncementDetail(announcementId)
    }

    AnnouncementDetailScreenContent(viewModel.state)
}

@Composable
fun AnnouncementDetailScreenContent(
    state: AnnouncementState
) {

    state.detailedAnnouncementItem?.let { announcementItem ->

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 64.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = announcementItem.title,
                style = PLTypography.Korean.H0.copy(
                    fontSize = 52.sp,
                    color = Color.White
                ),
            )

            Spacer(modifier = Modifier.height(48.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = PLColor.Main)
                .padding(vertical = 16.dp, horizontal = 20.dp)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = announcementItem.content,
                    style = PLTypography.Korean.H0.copy(
                        fontSize = 32.sp,
                        color = PLColor.Gray800
                    ),
                )
            }
        }
    }

}