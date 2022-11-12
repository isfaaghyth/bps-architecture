package app.bps.architecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import app.bps.architecture.data.AppDatabase
import app.bps.architecture.data.repository.GreetingRepository
import app.bps.architecture.data.repository.GreetingRepositoryImpl
import app.bps.architecture.data.repository.NoteRepository
import app.bps.architecture.data.repository.NoteRepositoryImpl
import app.bps.architecture.databinding.ActivityMainBinding
import app.bps.architecture.domain.GetGreetingUseCase
import app.bps.architecture.domain.GetGreetingUseCaseImpl
import app.bps.architecture.domain.model.GreetingUiModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.createDb(applicationContext)

        val noteRepository: NoteRepository = NoteRepositoryImpl(
            database.noteDao()
        )

        val viewModel = MainViewModel(noteRepository)

        viewModel.notes.observe(this) {
            it.forEach { note ->
                Log.d("BPSArch", note.note)
            }
        }

        viewModel.getAll()
    }

}