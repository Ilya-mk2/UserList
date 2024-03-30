package com.example.userlist.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userlist.R
import com.example.userlist.detail.UserDetailsFragment
import com.example.userlist.databinding.FragmentUsersBinding


class UsersFragment : Fragment() {

    private val adapter = UsersAdapter() { login ->
        val detailsFragment = UserDetailsFragment.newInstance(login)
        parentFragmentManager.beginTransaction().replace(R.id.main_container, detailsFragment)
            .addToBackStack("details")
            .commit()
    }
    private lateinit var binding: FragmentUsersBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: UserViewModel by viewModels()

        viewModel.screenState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is UsersScreenState.Loading -> {
                    adapter.updateList(emptyList())
                }

                is UsersScreenState.Success -> {
                    binding.loadingIv.visibility = View.GONE
                    adapter.updateList(it.list)
                    binding.swipe.isRefreshing = false
                }

                is UsersScreenState.Error -> {
                    binding.swipe.isRefreshing = false
                    binding.loadingIv.visibility = View.GONE
                    Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.loadUsers()
        val recyclerView: RecyclerView = binding.recyclerV
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = adapter
        binding.swipe.setOnRefreshListener {
            viewModel.loadUsers()
            binding.loadingIv.visibility = View.GONE
        }

    }

    companion object {

        fun newInstance() = UsersFragment()
    }

}