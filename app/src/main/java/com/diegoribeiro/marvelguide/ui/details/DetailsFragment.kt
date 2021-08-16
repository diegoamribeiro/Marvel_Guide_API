package com.diegoribeiro.marvelguide.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import coil.load
import com.bumptech.glide.Glide
import com.diegoribeiro.marvelguide.databinding.FragmentDetailsBinding
import com.diegoribeiro.marvelguide.databinding.FragmentHomeBinding
import com.diegoribeiro.marvelguide.ui.details.DetailsFragmentArgs
import com.diegoribeiro.marvelguide.utils.Constants
import com.diegoribeiro.marvelguide.utils.Constants.Companion.STANDARD_LARGE


class DetailsFragment : Fragment() {
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.tvCurrentHeroName.text = args.currentCharacter.name
        binding.tvCurrentOverview.text = args.currentCharacter.description
        Glide.with(binding.ivDetails)
            .load(args.currentCharacter.thumbnail.path + STANDARD_LARGE + ".${args.currentCharacter.thumbnail.extension}")
            .into(binding.ivDetails)


        binding.btCurrentInfo.setOnClickListener {
            binding.currentExternalInfo.apply {
                webViewClient = WebViewClient()
                loadUrl(args.currentCharacter.urls[0].url)
            }
        }


        return binding.root
    }


}