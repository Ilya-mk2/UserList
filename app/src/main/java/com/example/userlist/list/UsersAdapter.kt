package com.example.userlist.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userlist.R
import com.example.userlist.data.User

class UsersAdapter(val listener: OnItemClickListener) :
    RecyclerView.Adapter<UsersAdapter.UserHolder>() {

    private val uList = ArrayList<User>()

    fun getList(): List<User> = uList

    fun updateList(list: List<User>?) {
        uList.clear()
        uList.addAll(list ?: emptyList())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        val holder = UserHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(uList[position].login)
            }
        }
        return holder
    }

    override fun getItemCount() = uList.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val userItem = uList[position]
        holder.login.text = userItem.login
        holder.id.text = userItem.id
        Glide.with(holder.avatar.context).load(userItem.avatar).into(holder.avatar);
    }

    inner class UserHolder(v: View) : RecyclerView.ViewHolder(v) {

        val avatar: ImageView = v.findViewById(R.id.avatar_imageView)
        val login: TextView = v.findViewById(R.id.login_textView)
        val id: TextView = v.findViewById(R.id.id_textView)

    }

    fun interface OnItemClickListener {
        fun onItemClick(login: String)
    }
}