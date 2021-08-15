package com.diegoribeiro.marvelguide.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegoribeiro.marvelguide.R
import com.diegoribeiro.marvelguide.databinding.ItemCharacterBinding
import com.diegoribeiro.marvelguide.model.Character
import com.diegoribeiro.marvelguide.utils.DiffUtilsHome

class AdapterHome : RecyclerView.Adapter<AdapterHome.HomeViewHolder>(){

    private var heroList = emptyList<Character>()

    class HomeViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.binding.apply {
            tvHeroName.text = heroList[position].name
            Glide.with(ivPhoto)
                .load(heroList[position].thumbnail.path + ".${heroList[position].thumbnail.extension}")
                .placeholder(R.drawable.ic_person)
                .into(ivPhoto)
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    fun setData(listCharacter: List<Character>){
        val charDiffUtil = DiffUtilsHome(heroList, listCharacter)
        val heroListResult = DiffUtil.calculateDiff(charDiffUtil)
        this.heroList = listCharacter
        heroListResult.dispatchUpdatesTo(this)
    }
}
