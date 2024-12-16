package com.soopeach.presentation.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun AnnounceScreen() {
    AnnounceScreenContent()
}

@Composable
fun AnnounceScreenContent() {

    LazyColumn {

        val itemList = listOf("공지사항1", "공지사항2", "공지사항3", "공지사항4", "공지사항5")


    }
}