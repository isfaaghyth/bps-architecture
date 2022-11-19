package app.bps.architecture.data.source

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import app.bps.architecture.data.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM bps_notes")
    fun getAll(): LiveData<List<Note>>

    @Query("SELECT * FROM bps_notes WHERE id=:id")
    suspend fun getById(id: Int): List<Note>

    @Insert
    suspend fun insert(note: Note): Long

    @Update
    suspend fun update(note: Note): Int

    @Delete
    suspend fun delete(note: Note): Int
}