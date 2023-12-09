package com.example.apper.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apper.R
import com.example.apper.utils.convertSimpleDateFormat
import com.example.apper.utils.convertStringToUpperCase
import com.example.domain.model.Note

class MainAdapter(
    val onItemDelete: (Note) -> Unit,
    val onItemClick: (Note) -> Unit
) : PagingDataAdapter<Note, MainAdapter.MainViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
                oldItem.timestamp == newItem.timestamp

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
                oldItem == newItem
        }
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }
}