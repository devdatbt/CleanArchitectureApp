package com.example.apper.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apper.R
import com.example.apper.utils.convertSimpleDateFormat
import com.example.apper.utils.convertStringToUpperCase
import com.example.domain.model.Note

class NoteAdapter(
    private val onItemClick: (Note) -> Unit,
    private val onItemDelete: (Note) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDataDiff()) {
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvItemTitle)
        private val tvContent: TextView = itemView.findViewById(R.id.tv_item_content)
        private val tvTime: TextView = itemView.findViewById(R.id.tv_item_time)
        private val btnDelete: ImageView = itemView.findViewById(R.id.btn_item_delete)
        private val layoutItem: ConstraintLayout =
            itemView.findViewById(R.id.constraint_item_layout)

        fun onBind(note: Note) {
            tvTitle.text = note.title?.convertStringToUpperCase()
            tvContent.text = note.content?.convertStringToUpperCase()
            tvTime.text = note.timestamp.convertSimpleDateFormat()
            btnDelete.setOnClickListener { onItemDelete(note) }
            layoutItem.setOnClickListener { onItemClick(note) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}

class NoteDataDiff : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.content == newItem.content &&
                oldItem.timestamp == newItem.timestamp
    }
}