package app.bps.architecture.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.bps.architecture.data.entity.Note
import app.bps.architecture.data.source.NoteDao

@Database(
    entities = [
        Note::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        fun createDb(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "notes"
            ).build()
        }
    }
}