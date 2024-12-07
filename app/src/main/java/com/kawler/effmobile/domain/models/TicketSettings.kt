package com.kawler.effmobile.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketSettings(
    val date: String,
    val passengers: String,
    val route: String
) : Parcelable