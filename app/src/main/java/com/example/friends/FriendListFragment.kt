package com.example.friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.friends.databinding.FragmentFriendListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FriendListFragment : Fragment() {

    private var _binding: FragmentFriendListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // We used the viewModels delegate to create the view model.
    // Access the application, then get the Singleton database instance, then pass to ViewModel.
    private val friendViewModel: FriendsViewModel by viewModels {
        FriendsViewModelFactory((requireActivity().application as FriendsApplication).database)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFriendListBinding.inflate(inflater, container, false)

        val customAdapter = FriendListAdapter()

        val recyclerView: RecyclerView = binding.RecyclerViewFriendList
        recyclerView.adapter = customAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        friendViewModel.allFriends.observe(viewLifecycleOwner) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { customAdapter.submitList(it) }
        }

        binding.button.setOnClickListener{
            friendViewModel.deleteAll()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}