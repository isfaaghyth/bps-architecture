package app.bps.architecture.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bps_notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "content")
    val note: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
)