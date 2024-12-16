package com.soopeach.presentation.ui.theme

import androidx.compose.ui.graphics.Color
import com.soopeach.domain.model.MemberStatus

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

object PLColor {

    val Main = Color(0xFF95B2F7)

    // Object, Line, Text Colors
    val White = Color(0xFFFFFFFF)
    val Gray100 = Color(0xFFD3D4DA)
    val Gray200 = Color(0xFFA2A9B2)
    val Gray300 = Color(0xFF77808C)
    val Gray400 = Color(0xFF454751)
    val Gray500 = Color(0xFF2C2E34)
    val Gray600 = Color(0xFF202026)
    val Gray700 = Color(0xFF17171B)
    val Gray800 = Color(0xFF101012)

    val In = Color(0xFF5037DC)
    val Out = Color(0xFFFF964B)
    val Class = Color(0xFF4DE71A)
    val Home = Color(0xFFEE68E5)


    fun getColor(text: String): Color {
        return when (text) {
            MemberStatus.IN.text -> In
            MemberStatus.OUT.text -> Out
            MemberStatus.CLASS.text -> Class
            MemberStatus.HOME.text -> Home
            else -> Gray100
        }
    }
}

