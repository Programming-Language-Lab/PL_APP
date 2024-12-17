package com.soopeach.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.soopeach.presentation.ui.theme.PLColor
import com.soopeach.presentation.ui.theme.PLTypography
import com.soopeach.presentation.viewmodel.AnnouncementEvent
import com.soopeach.presentation.viewmodel.AnnouncementState
import com.soopeach.presentation.viewmodel.AnnouncementViewModel
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnnouncementWriteScreen(
    navController: NavController
) {

    val viewModel = koinViewModel<AnnouncementViewModel>()

    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.event.receiveAsFlow().collect { event ->
            when (event) {
                is AnnouncementEvent.PostSuccess -> {
                    Toast.makeText(context, "게시 성공", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }

                is AnnouncementEvent.PostFailure -> {
                    Toast.makeText(context, "게시 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    AnnouncementWriteScreenContent(
        state = viewModel.state,
        onTitleInputChanged = { viewModel.changeTitleInputText(it) },
        onContentInputChanged = { viewModel.changeContentInputText(it) },
        onPostButtonClicked = {
            viewModel.postAnnouncement()
        }
    )
}

@Composable
fun AnnouncementWriteScreenContent(
    state: AnnouncementState,
    onTitleInputChanged: (String) -> Unit,
    onContentInputChanged: (String) -> Unit,
    onPostButtonClicked: () -> Unit
) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BasicTextField(
            value = state.titleInputText,
            onValueChange = {
                onTitleInputChanged(it)
            },
            modifier = Modifier.fillMaxWidth(),
            textStyle = PLTypography.Korean.H0.copy(
                fontSize = 52.sp,
                color = Color.White
            ),
            decorationBox = { innerTextField ->
                Box(Modifier.fillMaxWidth()) {
                    if (state.titleInputText.isEmpty()) {
                        Text(
                            text = "제목을 입력해주세요.",
                            style = PLTypography.Korean.H0.copy(
                                fontSize = 52.sp,
                                color = Color.Gray
                            )
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(48.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 120.dp)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = PLColor.Main)
                .padding(vertical = 16.dp, horizontal = 20.dp)
        ) {
            BasicTextField(
                value = state.contentInputText,
                onValueChange = {
                    onContentInputChanged(it)
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = PLTypography.Korean.H0.copy(
                    fontSize = 32.sp,
                    color = PLColor.Gray800
                ),
                decorationBox = { innerTextField ->
                    Box(Modifier.fillMaxWidth()) {
                        if (state.contentInputText.isEmpty()) {
                            Text(
                                text = "내용을 입력해주세요.",
                                style = PLTypography.Korean.H0.copy(
                                    fontSize = 32.sp,
                                    color = Color.Gray
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(bottom = 40.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(PLColor.Main)
                .clickable {
                    onPostButtonClicked()
                }
                .padding(horizontal = 24.dp, vertical = 12.dp),
            text = "게시하기",
            style = PLTypography.Korean.H0.copy(
                fontSize = 32.sp,
                color = Color.White
            )
        )
    }

}