package com.example.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FriendListAdapter: ListAdapter<Friend, FriendListAdapter.FriendViewHolder>(FriendsComparator()) {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FriendViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.friend_list_item, viewGroup, false)

        return FriendViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(friendViewHolder: FriendViewHolder, position: Int) {
        val current = getItem(position)
        friendViewHolder.bind(current)
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class FriendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: ConstraintLayout = view.findViewById(R.id.ConstraintLayout_FriendListItem_Root)

        fun bind(friend: Friend?) {
            root.findViewById<TextView>(R.id.TextView_FriendListItem_FriendName).text =
                friend?.fullName
        }

        init {
            // The view passed in to this class has a findNavController method.
            root.setOnClickListener{
                view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }

    class FriendsComparator : DiffUtil.ItemCallback<Friend>() {
        override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
            return oldItem.friendId == newItem.friendId
        }
    }
}
