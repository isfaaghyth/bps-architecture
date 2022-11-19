package app.bps.architecture.data.repository

import androidx.lifecycle.LiveData
import app.bps.architecture.data.entity.Note
import app.bps.architecture.data.source.NoteDao

interface NoteRepository {
    fun getAll(): LiveData<List<Note>>
    suspend fun getById(id: Int): Note
    suspend fun insert(note: String): Long
    suspend fun update(note: Note): Int
    suspend fun delete(note: Note): Int
}

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
    override fun getAll(): LiveData<List<Note>> {
        return dao.getAll()
    }

    override suspend fun getById(id: Int): Note {
        return dao.getById(id).first()
    }

    override suspend fun insert(note: String): Long {
        return dao.insert(Note(
            note = note,
            createdAt = System.currentTimeMillis()
        ))
    }

    override suspend fun update(note: Note): Int {
        return dao.update(note)
    }

    override suspend fun delete(note: Note): Int {
        return dao.delete(note)
    }
}