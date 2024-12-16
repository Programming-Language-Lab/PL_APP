package com.soopeach.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.soopeach.domain.model.MemberState
import com.soopeach.domain.model.MemberStatus
import com.soopeach.presentation.components.MemberStatusCard
import com.soopeach.presentation.viewmodel.AttendanceScreenState
import com.soopeach.presentation.viewmodel.AttendanceScreenViewModel
import com.soopeach.presentation.components.bottomSheet.PLBottomSheet
import com.soopeach.presentation.components.dialog.DialogPositiveButton
import com.soopeach.presentation.components.dialog.PLDialog
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
        },
        changeModalVisibility = { isVisible ->
            viewModel.changeModalVisibility(isVisible)
        },
        changeAddingMemberText = {
            viewModel.changeAddingMemberText(it)
        },
        onAddMemberButtonClicked = {
            viewModel.addMember()
        },
        changeDeletingAlertVisibility = { isVisible ->
            viewModel.changeDeletingAlertVisibility(isVisible)
        },
        onDeleteMemberButtonClicked = {
            viewModel.deleteMember()
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceScreenContent(
    state: AttendanceScreenState,
    changeBottomSheetVisibility: (Boolean) -> Unit = {},
    changeMemberState: (String) -> Unit = {},
    setSelectedMemberState: (MemberState) -> Unit = {},
    changeModalVisibility: (Boolean) -> Unit = {},
    changeAddingMemberText: (String) -> Unit = {},
    onAddMemberButtonClicked: () -> Unit = {},
    changeDeletingAlertVisibility: (Boolean) -> Unit = {},
    onDeleteMemberButtonClicked: () -> Unit = {}
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

    // 멤버 추가를 위한 다이어로그
    if (state.isModalVisible) {
        PLDialog(
            onDismissRequest = {
                changeModalVisibility(false)
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .clip(
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(PLColor.Gray500)
                        .padding(24.dp),
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "멤버 추가",
                        style = PLTypography.Korean.B1_semiBold.copy(
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    BasicTextField(
                        value = state.addingMemberText,
                        onValueChange = {
                            changeAddingMemberText(it)
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        textStyle = PLTypography.Korean.B2_regular.copy(
                            color = PLColor.Gray200
                        ),
                        cursorBrush = SolidColor(Color.Black),
                        singleLine = true,
                    ) { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .background(PLColor.Gray400)
                                .padding(12.dp),
                        ) {

                            if (state.addingMemberText.isEmpty()) {
                                Text(
                                    text = "추가할 멤버의 이름을 알려주세요.",
                                    style = PLTypography.Korean.B2_regular.copy(
                                        color = PLColor.Gray200
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                            innerTextField()
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    DialogPositiveButton(
                        text = "추가하기",
                        onClicked = {
                            onAddMemberButtonClicked()
                            changeModalVisibility(false)
                        }
                    )

                }
            }
        )
    }

    // 멤버 삭제를 위한 다이어로그
    if (state.isDeletingAlertVisible) {
        PLDialog(
            onDismissRequest = {
                changeDeletingAlertVisibility(false)
            },
            content = {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .clip(
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(PLColor.Gray500)
                        .padding(24.dp),
                ) {

                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "정말 ${state.selectedMember.name}님을 삭제하시겠어요?",
                        style = PLTypography.Korean.B1_semiBold.copy(
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DialogPositiveButton(
                        text = "삭제하기",
                        onClicked = {
                            onDeleteMemberButtonClicked()
                            changeDeletingAlertVisibility(false)
                        }
                    )

                }
            }
        )
    }

    // 멤버 현황 목록
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalArrangement = Arrangement.spacedBy(52.dp),
    ) {
        items(state.memberState) { curMemberState ->

            MemberStatusCard(
                onCardClicked = {
                    setSelectedMemberState(curMemberState)
                    changeBottomSheetVisibility(true)
                },
                onCardLongClicked = {
                    setSelectedMemberState(curMemberState)
                    changeDeletingAlertVisibility(true)
                },
                name = curMemberState.name,
                status = curMemberState.status.text,
                position = curMemberState.position
            )

        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(25.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            modifier =
            Modifier.size(52.dp),
            onClick = {
                changeModalVisibility(true)
            },
            containerColor = PLColor.Gray400,
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "add member button",
                tint = Color.White
            )
        }
    }
}