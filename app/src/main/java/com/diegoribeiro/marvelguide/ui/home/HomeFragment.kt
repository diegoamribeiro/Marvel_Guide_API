package com.diegoribeiro.marvelguide.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegoribeiro.marvelguide.data.remote.RemoteClient
import com.diegoribeiro.marvelguide.data.repository.Repository
import com.diegoribeiro.marvelguide.databinding.FragmentHomeBinding
import com.diegoribeiro.marvelguide.ui.adapters.AdapterHome
import com.diegoribeiro.marvelguide.utils.ResourceProject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val listAdapter: AdapterHome by lazy { AdapterHome() }
    private lateinit var viewModelHome: ViewModelHome
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelHome = ViewModelHome()
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvList.showShimmer()
        observeViewModel()
        setupRecyclerView()

        return binding.root
    }

    private fun observeViewModel(){
        viewModelHome.allCharacters.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is ResourceProject.Success->{
                    binding.rvList.hideShimmer()
                    response.data?.let { allResults->
                        listAdapter.setData(allResults.data.result.toList())
                    }
                }
                is ResourceProject.Error->{
                    binding.rvList.hideShimmer()
                    response.message?.let { message->
                        Log.d("###ResourceProject", message)
                    }
                }
                is ResourceProject.Loading->{
                    binding.rvList.showShimmer()
                }
            }
        })
    }

    private fun setupRecyclerView(){
        recyclerView = binding.rvList
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = listAdapter
    }


}