package com.cloudin.task.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cloudin.task.R
import com.cloudin.task.data.model.Result
import com.cloudin.task.databinding.ItemUsersBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var _items: List<Result> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_users, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val item = _items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return _items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<Result>?) {
        _items = items ?: emptyList()
        notifyDataSetChanged()
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemUsersBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(item: Result) {

            binding.apply {

                userName.text = "${item.name.first} ${item.name.last}"
                userPhNo.text = item.phone
                userEmail.text = item.email
                userLocation.text = item.location.country

                Glide.with(itemView.context)
                    .load(item.picture.medium)
                    .placeholder(R.drawable.ic_user_photo)
                    .into(binding.userImage)
            }
        }
    }
}