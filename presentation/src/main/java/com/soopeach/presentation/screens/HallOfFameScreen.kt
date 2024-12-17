package com.soopeach.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soopeach.presentation.components.HallOfFameFrame
import com.soopeach.presentation.ui.theme.PLTypography
import com.soopeach.presentation.viewmodel.HallOfFameScreenState
import com.soopeach.presentation.viewmodel.HallOfFameScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HallOfFameScreen(
    onProjectItemClicked: (String) -> Unit
) {

    val viewModel = koinViewModel<HallOfFameScreenViewModel>()

    LaunchedEffect(true) {
        viewModel.getProjectList()
    }

    HallOfFameScreenContent(
        state = viewModel.state,
        onProjectItemClicked = {
            onProjectItemClicked(it)
        }
    )
}

@Composable
fun HallOfFameScreenContent(
    state: HallOfFameScreenState,
    onProjectItemClicked: (String) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(48.dp),
        horizontalArrangement = Arrangement.spacedBy(64.dp),
        verticalArrangement = Arrangement.spacedBy(120.dp),
    ) {
        items(state.projectItems) { curProjectItem ->
            HallOfFameFrame(
                curProjectItem,
                onProjectItemClicked
            )
        }
    }
}