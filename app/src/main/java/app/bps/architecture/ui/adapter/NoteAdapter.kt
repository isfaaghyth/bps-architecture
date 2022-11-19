package app.bps.architecture.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import app.bps.architecture.R
import app.bps.architecture.data.entity.Note

class NoteAdapter constructor(
    private val notes: MutableList<Note> = mutableListOf(),
    private val listener: NoteViewHolder.Listener
) : Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBinding(notes[position])
    }

    override fun getItemCount() = notes.size

    fun setData(dataNotes: List<Note>) {
        notes.clear()
        notes.addAll(dataNotes)

        notifyDataSetChanged()
    }

    class NoteViewHolder(
        view: View,
        private val listener: Listener
    ) : RecyclerView.ViewHolder(view) {
        private val txtNote = view.findViewById<TextView>(R.id.txt_note)
        private val txtDate = view.findViewById<TextView>(R.id.txt_date)

        fun onBinding(note: Note) {
            txtNote.text = note.note
            txtDate.text = note.createdAt.toString()

            itemView.setOnClickListener {
                listener.onNoteClicked(note.id)
            }

            itemView.setOnLongClickListener {
                listener.onNoteLongClicked(note.id)

                true
            }
        }

        interface Listener {
            fun onNoteClicked(id: Int)
            fun onNoteLongClicked(id: Int)
        }

        companion object {
            fun create(
                parent: ViewGroup,
                listener: Listener
            ): NoteViewHolder {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_note, parent, false)

                return NoteViewHolder(view, listener)
            }
        }
    }
}