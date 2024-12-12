package com.soopeach.presentation

import androidx.annotation.DrawableRes
import androidx.navigation.NamedNavArgument
import com.soopeach.presentation.R

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
        unSelectedIcon = R.drawable.ic_my_24,
        selectedIcon = R.drawable.ic_my_24_filled,
    )

    data object AI : Screen(
        route = "ai",
        title = "AI",
        unSelectedIcon = R.drawable.ic_my_show_24,
        selectedIcon = R.drawable.ic_my_show_24_filled,
    )

    companion object {
        val bottomNavigationItems = listOf(Attendance, AI)
    }

}