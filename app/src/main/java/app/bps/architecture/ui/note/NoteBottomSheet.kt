package app.bps.architecture.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import app.bps.architecture.BpsApplication
import app.bps.architecture.data.entity.Note
import app.bps.architecture.databinding.DialogNoteBinding
import app.bps.architecture.di.AppModule
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NoteBottomSheet : BottomSheetDialogFragment() {

    private lateinit var viewModel: NoteViewModel
    private var binding: DialogNoteBinding? = null

    private lateinit var note: Note

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogNoteBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = AppModule.getNoteViewModel(
            (context?.applicationContext as BpsApplication).repository
        )

        val noteId = arguments?.getInt(KEY_NOTE_ID)?: -1

        if (noteId > 0) {
            viewModel.getById(noteId)
        }

        viewModel.note.observe(viewLifecycleOwner) {
            note = it

            binding?.edtNote?.setText(note.note)
            binding?.btnSave?.text = "EDIT"
        }

        viewModel.inInserted.observe(viewLifecycleOwner) { isInserted ->
            if (isInserted) {
                dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Gagal menambahkan data.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.isUpdated.observe(viewLifecycleOwner) { isUpdated ->
            if (isUpdated) {
                dismiss()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Gagal memperbaharui data.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding?.btnSave?.setOnClickListener {
            val edtNote = binding?.edtNote?.text.toString()
            val isEditable = ::note.isInitialized

            if (isEditable) {
                viewModel.update(note.copy(
                    note = edtNote
                ))
            } else {
                viewModel.insert(edtNote)
            }
        }
    }

    companion object {
        private const val TAG_DIALOG = "note_bottom_sheet"
        private const val KEY_NOTE_ID = "note_id"

        fun show(fragmentManager: FragmentManager, noteId: Int = -1) {
            val noteBottomSheet = NoteBottomSheet()
            val bundle = Bundle()
            bundle.putInt(KEY_NOTE_ID, noteId)
            noteBottomSheet.arguments = bundle
            noteBottomSheet.show(fragmentManager, TAG_DIALOG)
        }
    }
}