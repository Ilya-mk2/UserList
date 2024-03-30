package com.example.userlist.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.userlist.api.ApiHolder
import com.example.userlist.data.DetailUser
import com.example.userlist.databinding.FragmentDetailsBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UserDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: DetailsViewModel by viewModels()
        val login = arguments?.getString(TAG)!!

        viewModel.loadDetails(login)

        viewModel.detailsState.observe(viewLifecycleOwner) {
            when (it) {

                is DetailsScreenState.Loading -> {
                    binding.secondWaitIv.visibility = View.VISIBLE
                }

                is DetailsScreenState.Success -> {
                    Glide.with(binding.avatarImageView).load(it.user.avatar)
                        .into(binding.avatarImageView)
                    binding.nameTextView.text = it.user.name
                    binding.dateTextView.text = it.user.created
                    binding.emailTextView.text = it.user.email
                    binding.followersTextView.text = it.user.followers
                    binding.followingTextView.text = it.user.following
                    binding.organizationTextView.text =
                        it.user.organizations.joinToString { it.login }
                    binding.secondWaitIv.visibility = View.GONE
                }

                is DetailsScreenState.Error -> {
                    binding.secondWaitIv.visibility = View.GONE
                    Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    companion object {
        private const val TAG = "user"

        fun newInstance(login: String): UserDetailsFragment {

            val fragment = UserDetailsFragment()
            val arguments = Bundle()
            arguments.putString(TAG, login)
            fragment.arguments = arguments
            return fragment
        }
    }

}