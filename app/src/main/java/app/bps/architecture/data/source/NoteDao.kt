package app.bps.architecture.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.bps.architecture.data.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>

    @Insert
    fun insert(note: Note)
}