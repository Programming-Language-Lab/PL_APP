package com.soopeach.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.soopeach.domain.model.MemberState
import com.soopeach.domain.model.MemberStatus
import com.soopeach.presentation.viewmodel.AttendanceScreenState
import com.soopeach.presentation.viewmodel.AttendanceScreenViewModel
import com.soopeach.presentation.components.bottomSheet.PLBottomSheet
import com.soopeach.presentation.utils.getStatusColor
import com.soopeach.presentation.ui.theme.PLColor
import com.soopeach.presentation.ui.theme.PLTypography
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun AttendanceScreen(
    navController: NavController
) {

    val viewModel = koinViewModel<AttendanceScreenViewModel>()

    AttendanceScreenContent(
        state = viewModel.state,
        changeBottomSheetVisibility = { isVisible ->
            viewModel.changeBottomSheetVisibility(isVisible)
        },
        changeMemberState = {
            viewModel.changeMemberState(it)
        },
        setSelectedMemberState = { memberState ->
            viewModel.setSelectedMemberState(memberState)
        })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreenContent(
    state: AttendanceScreenState,
    changeBottomSheetVisibility: (Boolean) -> Unit = {},
    changeMemberState: (String) -> Unit = {},
    setSelectedMemberState: (MemberState) -> Unit = {}
) {

    val scope = rememberCoroutineScope()

    // 바텀시트
    if (state.isBottomSheetVisible) {
        PLBottomSheet(onDismissRequest = { changeBottomSheetVisibility(false) }) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp),

                ) {

                MemberStatus.allNormalStatus().forEach { status ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(PLColor.Gray600)
                            .clickable {
                                scope.launch {
                                    changeBottomSheetVisibility(false)
                                    changeMemberState(status.text)
                                }
                            },
                        textAlign = TextAlign.Center,
                        text = status.text,
                        style = PLTypography.Korean.H0.copy(
                            fontSize = 28.sp,
                            color = Color.White
                        ),
                    )
                }

                Spacer(modifier = Modifier.navigationBarsPadding())

            }
        }
    }

    // 멤버 현황 목록
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        items(state.memberState) { curMemberState ->

            Column(
                Modifier
                    .clip(shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp))
                    .background(color = curMemberState.status.text.getStatusColor())
                    .clickable {
                        setSelectedMemberState(curMemberState)
                        changeBottomSheetVisibility(true)
                    }
                    .padding(horizontal = 16.dp, vertical = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = curMemberState.name,
                    style = PLTypography.Korean.H0.copy(
                        fontSize = 28.sp,
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = curMemberState.status.text,
                    style = PLTypography.Korean.H0.copy(
                        color = PLColor.Gray600
                    )
                )
            }

        }
    }
}