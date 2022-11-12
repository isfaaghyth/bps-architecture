package app.bps.architecture.data.repository

import app.bps.architecture.data.entity.Note
import app.bps.architecture.data.source.NoteDao

interface NoteRepository {
    suspend fun getAll(): List<Note>
    fun insert(note: Note)
}

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {

    override suspend fun getAll(): List<Note> {
        return dao.getAll()
    }

    override fun insert(note: Note) {
        dao.insert(note)
    }

}