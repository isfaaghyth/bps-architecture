package app.bps.architecture.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.bps.architecture.BpsApplication
import app.bps.architecture.data.entity.Note
import app.bps.architecture.data.repository.NoteRepository
import app.bps.architecture.databinding.ActivityMainBinding
import app.bps.architecture.di.AppModule
import app.bps.architecture.ui.adapter.NoteAdapter
import app.bps.architecture.ui.note.NoteBottomSheet

class MainActivity : AppCompatActivity(), NoteAdapter.NoteViewHolder.Listener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val adapter = NoteAdapter(listener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        viewModel = AppModule.getViewModel(
            (applicationContext as BpsApplication).repository
        )

        viewModel.getAll.observe(this) { notes ->
            adapter.setData(notes)
        }

        binding.btnAddNote.setOnClickListener {
            NoteBottomSheet.show(supportFragmentManager)
        }
    }

    private fun setupRecyclerView() {
        binding.lstNotes.layoutManager = LinearLayoutManager(this)
        binding.lstNotes.adapter = adapter
    }

    override fun onNoteClicked(id: Int) {
        NoteBottomSheet.show(supportFragmentManager, id)
    }

    override fun onNoteLongClicked(id: Int) {
        viewModel.deleteById(id)
    }

}