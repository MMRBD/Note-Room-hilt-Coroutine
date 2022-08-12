package com.mmrbd.note_room_hilt_coroutine.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String?,
    val description: String?,
//    val updatedDate: Date = Date(System.currentTimeMillis()),
//    val createdDate: Date = Date(System.currentTimeMillis()),
)

open class Other {
    var isSelected: Boolean = false

    fun fullName(firstName: String, lastName: String): String {
        return "${firstName.title()} ${lastName.title()}"
    }
}

fun String.title(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() };
}
