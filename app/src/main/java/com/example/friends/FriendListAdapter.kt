package com.example.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class FriendListAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class FriendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: ConstraintLayout = view.findViewById(R.id.ConstraintLayout_FriendListItem_Root)

        init {
            // The view passed in to this class has a findNavController method.
            root.setOnClickListener{
                view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FriendViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.friend_list_item, viewGroup, false)

        return FriendViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(friendViewHolder: FriendViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val friendName: TextView = friendViewHolder.root
            .findViewById(R.id.TextView_FriendListItem_FriendName)

        friendName.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
