package com.kawler.effmobile.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PopularCity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val text: String,
    val img: String? = null
)
