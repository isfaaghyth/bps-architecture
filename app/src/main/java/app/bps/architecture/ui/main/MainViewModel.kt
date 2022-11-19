package app.bps.architecture.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bps.architecture.data.entity.Note
import app.bps.architecture.data.repository.NoteRepository
import kotlinx.coroutines.*

class MainViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    val getAll: LiveData<List<Note>> get() = noteRepository.getAll()

    private var _isDeleted = MediatorLiveData<Boolean>()
    val isDeleted: LiveData<Boolean> get() = _isDeleted

    fun deleteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = async { noteRepository.getById(id) }
            val isDeleted = noteRepository.delete(note.await())

            withContext(Dispatchers.Main) {
                _isDeleted.value = isDeleted > 0
            }
        }
    }

}