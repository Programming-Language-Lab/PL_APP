package com.soopeach.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.soopeach.domain.EMPTY
import com.soopeach.presentation.components.navigation.PLBottomNavigation
import com.soopeach.presentation.Screen
import com.soopeach.presentation.Screen.Companion.bottomNavigationItems
import com.soopeach.presentation.screens.announcement.AnnouncementDetailScreen
import com.soopeach.presentation.screens.announcement.AnnouncementScreen
import com.soopeach.presentation.screens.announcement.AnnouncementWriteScreen
import com.soopeach.presentation.ui.theme.PLColor

@Composable
fun AppScreen(
    onProjectItemClicked: (String) -> Unit
) {
    AppScreenContent {
        onProjectItemClicked(it)
    }
}

@Composable
fun AppScreenContent(
    onProjectItemClicked: (String) -> Unit
) {

    val navController = rememberNavController()

    Scaffold(
        containerColor = PLColor.Gray700,
        bottomBar = {

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            AnimatedVisibility(
                visible = currentDestination?.route in bottomNavigationItems.map { it.route },
                enter = slideInVertically(initialOffsetY = {
                    it
                }),
                exit = slideOutVertically(targetOffsetY = {
                    it
                })
            ) {
                PLBottomNavigation(
                    bottomNavigationItems = bottomNavigationItems,
                    currentDestination?.route ?: String.EMPTY
                ) {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }

        }
    ) { innerPadding ->

        NavHost(
            navController,
            startDestination = Screen.Attendance.route,
            Modifier.padding(innerPadding),
        ) {

            composable(Screen.Attendance.route) {
                AttendanceScreen()
            }

            composable(Screen.Announcement.route) {
                AnnouncementScreen(navController)
            }

            composable(
                Screen.AnnouncementDetail.route,
                Screen.AnnouncementDetail.arguments
            ) {backStackEntry ->
                AnnouncementDetailScreen(
                    announcementId = backStackEntry.arguments?.getString("announcementId") ?: String.EMPTY,
                )
            }

            composable(Screen.AnnouncementWrite.route) {
                AnnouncementWriteScreen(navController)
            }

            composable(Screen.HallOfFame.route) {
                HallOfFameScreen {
                    onProjectItemClicked(it)
                }
            }

            composable(Screen.AI.route) {
                AIScreen()
            }

        }
    }
}