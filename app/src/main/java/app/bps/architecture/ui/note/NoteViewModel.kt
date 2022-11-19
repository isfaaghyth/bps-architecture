package app.bps.architecture.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.bps.architecture.data.entity.Note
import app.bps.architecture.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private var _note = MutableLiveData<Note>()
    val note: LiveData<Note> get() = _note

    private var _isInserted = MutableLiveData<Boolean>()
    val inInserted: LiveData<Boolean> get() = _isInserted

    private var _isUpdated = MutableLiveData<Boolean>()
    val isUpdated: LiveData<Boolean> get() = _isUpdated

    fun getById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = noteRepository.getById(id)

            withContext(Dispatchers.Main) {
                _note.value = result
            }
        }
    }

    fun insert(note: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val isInserted = noteRepository.insert(note)

            withContext(Dispatchers.Main) {
                _isInserted.value = isInserted > 0
            }
        }
    }

    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            val isUpdated = noteRepository.update(note)

            withContext(Dispatchers.Main) {
                _isUpdated.value = isUpdated > 0
            }
        }
    }

}