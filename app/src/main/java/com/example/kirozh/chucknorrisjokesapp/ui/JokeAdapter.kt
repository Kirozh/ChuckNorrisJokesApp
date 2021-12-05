package com.example.kirozh.chucknorrisjokesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kirozh.chucknorrisjokesapp.model.entities.Joke
import com.example.kirozh.chucknorrisjokesapp.R
import com.example.kirozh.chucknorrisjokesapp.databinding.JokeItemBinding

/**
 * @author Kirill Ozhigin on 19.11.2021
 */
class JokeAdapter() : RecyclerView.Adapter<JokeAdapter.ListViewHolder>() {
    private lateinit var jokes: List<Joke>
    val adapterCallBack: AdapterCallBack? = null


    @JvmName("setAlbums1")
    fun setJokes(jokes: List<Joke>) {
        this.jokes = jokes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = DataBindingUtil.inflate<JokeItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.joke_item,
            parent,
            false
        )
        return ListViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val joke = jokes[position]
        holder.bind(joke)

    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    class ListViewHolder(val binding: JokeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(joke: Joke){
            binding.joke = joke
            binding.executePendingBindings()
        }
    }

    interface AdapterCallBack{
        fun onItemClicked(joke: Joke)
    }
}