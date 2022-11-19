package app.bps.architecture.di

import android.content.Context
import app.bps.architecture.data.AppDatabase
import app.bps.architecture.data.repository.NoteRepository
import app.bps.architecture.data.repository.NoteRepositoryImpl
import app.bps.architecture.data.source.NoteDao
import app.bps.architecture.ui.main.MainViewModel
import app.bps.architecture.ui.note.NoteViewModel

object AppModule {

    fun getViewModel(repository: NoteRepository): MainViewModel {
        return providesMainViewModel(repository)
    }

    fun getNoteViewModel(repository: NoteRepository): NoteViewModel {
        return providesNoteViewModel(repository)
    }

    fun getNoteRepository(context: Context): NoteRepository {
        val db = providesDatabase(context)
        val dao = providesNoteDao(db)

        return NoteRepositoryImpl(dao)
    }

    private fun providesNoteViewModel(repository: NoteRepository): NoteViewModel {
        return NoteViewModel(repository)
    }

    private fun providesMainViewModel(repository: NoteRepository): MainViewModel {
        return MainViewModel(repository)
    }

    private fun providesNoteRepository(dao: NoteDao): NoteRepository {
        return NoteRepositoryImpl(dao)
    }

    private fun providesNoteDao(db: AppDatabase): NoteDao {
        return db.noteDao()
    }

    private fun providesDatabase(context: Context): AppDatabase {
        return AppDatabase.createDb(context)
    }

}