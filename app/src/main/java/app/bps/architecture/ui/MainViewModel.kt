package app.bps.architecture.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bps.architecture.data.entity.Note
import app.bps.architecture.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel:
 *
 * Bertugas untuk melakukan pemprosesan data dan logic
 */
class MainViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> get() = _notes

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(Note(note = "Test baru"))
        }
    }

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = noteRepository.getAll()

            withContext(Dispatchers.Main) {
                _notes.value = result
            }
        }
    }

    fun insert(note: Note) {

    }

}