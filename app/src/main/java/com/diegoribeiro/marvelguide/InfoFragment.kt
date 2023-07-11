package com.diegoribeiro.marvelguide

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.webView.apply {
            Log.d("***Passou", "${args.infoArgs.urls[0].url}")
            webViewClient = WebViewClient()
            if (args.infoArgs.urls[0].url != null){
                loadUrl(args.infoArgs.urls[0].url)
            }else{
                Toast.makeText(requireContext(), "No info", Toast.LENGTH_SHORT).show()
            }

        }
    }


}