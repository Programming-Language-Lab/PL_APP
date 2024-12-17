package com.soopeach.presentation.screens.announcement

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.soopeach.presentation.Screen
import com.soopeach.presentation.components.button.PLFloatingButton
import com.soopeach.presentation.ui.theme.PLColor
import com.soopeach.presentation.ui.theme.PLTypography
import com.soopeach.presentation.viewmodel.AnnouncementState
import com.soopeach.presentation.viewmodel.AnnouncementViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnnouncementScreen(
    navController: NavController
) {

    val viewModel = koinViewModel<AnnouncementViewModel>()

    LaunchedEffect(true) {
        viewModel.getAnnouncementList()
    }

    AnnouncementScreenContent(
        state = viewModel.state,
        onItemClicked = {
            navController.navigate(Screen.AnnouncementDetail.route.replace("{announcementId}", it))
        }
    ) {
        navController.navigate(Screen.AnnouncementWrite.route)
    }

}

@Composable
fun AnnouncementScreenContent(
    state: AnnouncementState,
    onItemClicked: (String) -> Unit,
    onFloatingButtonClicked: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .padding(48.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        items(state.announcementItems) { item ->

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = PLColor.Main)
                    .clickable {
                        onItemClicked(item.id)
                    }
                    .padding(horizontal = 24.dp, vertical = 40.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.title,
                    style = PLTypography.Korean.H0.copy(
                        fontSize = 32.sp
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    PLFloatingButton {
        onFloatingButtonClicked()
    }

}