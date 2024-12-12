package com.soopeach.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WebHookMessage(
    val text: String
)