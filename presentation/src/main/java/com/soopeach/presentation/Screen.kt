package com.soopeach.presentation

import androidx.annotation.DrawableRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val title: String,
    @DrawableRes val unSelectedIcon: Int? = null,
    @DrawableRes val selectedIcon: Int? = null,
    val arguments: List<NamedNavArgument> = emptyList(),
) {

    data object Attendance : Screen(
        route = "attendance",
        title = "출석",
        unSelectedIcon = R.drawable.ic_my,
        selectedIcon = R.drawable.ic_my_filled,
    )

    data object Announcement : Screen(
        route = "announcement",
        title = "공지사항",
        unSelectedIcon = R.drawable.ic_chat,
        selectedIcon = R.drawable.ic_chat_filled,
    )

    data object AnnouncementDetail : Screen(
        route = "announcementDetail/{announcementId}",
        title = "공지사항 상세",
        arguments = listOf(
            navArgument("announcementId") { type = NavType.StringType },
        ),
    )

    data object AnnouncementWrite : Screen(
        route = "announcementWrite",
        title = "공지사항 작성",
    )

    data object HallOfFame : Screen(
        route = "hallOfFame",
        title = "명예의 전당",
        unSelectedIcon = R.drawable.ic_trophy,
        selectedIcon = R.drawable.ic_trophy_filled,
    )

    data object AI : Screen(
        route = "ai",
        title = "AI",
        unSelectedIcon = R.drawable.ic_robot,
        selectedIcon = R.drawable.ic_robot_filled,
    )

    companion object {
        val bottomNavigationItems = listOf(Attendance, Announcement, HallOfFame, AI)
    }

}