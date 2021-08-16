package com.diegoribeiro.marvelguide

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.diegoribeiro.marvelguide.databinding.FragmentInfoBinding
import com.diegoribeiro.marvelguide.model.Character

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private val args: InfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInfoBinding.inflate(inflater, container, false)

        val bundle = Bundle()
        bundle.getSerializable("characterInfo")


        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(bundle.toString())
        }

        return binding.root
    }


}