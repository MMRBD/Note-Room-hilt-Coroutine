package com.mmrbd.note_room_hilt_coroutine.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmrbd.note_room_hilt_coroutine.data.models.Note
import com.mmrbd.note_room_hilt_coroutine.databinding.ItemNoteBinding

class NoteAdapter(val context: Context, private val onItemClick: (Note) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes: List<Note> = emptyList()

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            with(binding) {
                title.text = if (!note.title.isNullOrEmpty()) note.title else note.description
                if (!note.title.isNullOrEmpty()) {
                    description.text = note.description
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
        holder.itemView.setOnClickListener { onItemClick.invoke(notes[position]) }
    }

    override fun getItemCount(): Int = notes.size

    override fun getItemViewType(position: Int) = position

    fun setNoteDataList(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}