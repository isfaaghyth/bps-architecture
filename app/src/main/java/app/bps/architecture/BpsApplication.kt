package app.bps.architecture

import android.app.Application
import app.bps.architecture.data.repository.NoteRepository
import app.bps.architecture.di.AppModule

class BpsApplication : Application() {

    lateinit var repository: NoteRepository

    override fun onCreate() {
        super.onCreate()
        repository = AppModule.getNoteRepository(this)
    }

}