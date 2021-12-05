package com.example.kirozh.chucknorrisjokesapp.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kirozh.chucknorrisjokesapp.MainViewModel
import com.example.kirozh.chucknorrisjokesapp.MainViewModelFactory
import com.example.kirozh.chucknorrisjokesapp.R
import com.example.kirozh.chucknorrisjokesapp.databinding.FragmentHomeBinding
import com.example.kirozh.chucknorrisjokesapp.network.RetrofitApi
import com.example.kirozh.chucknorrisjokesapp.repositories.JokeRepository
import com.example.kirozh.chucknorrisjokesapp.ui.JokeAdapter

class HomeFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentHomeBinding

    private lateinit var reloadBtn: Button
    private lateinit var reloadEt: EditText

    lateinit var recyclerView: RecyclerView
    private val adapter = JokeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view = binding.root

        reloadBtn = view.findViewById(R.id.reloadButton)
        reloadEt = view.findViewById(R.id.jokesEt)

        reloadEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    reloadBtn.isEnabled = p0.isNotEmpty()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        val retrofitService = RetrofitApi.getInstance()
        val mainRepository = JokeRepository(retrofitService)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        )[MainViewModel::class.java]

        reloadBtn.setOnClickListener {

            if (reloadEt.text.isNotEmpty()) {

                val jokeNum = Integer.valueOf(reloadEt.text.toString())

                viewModel.jokeList.observe(viewLifecycleOwner, {
                    recyclerView = binding.jokeRecyclerView
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    adapter.setJokes(it)
                    recyclerView.adapter = adapter

                    recyclerView.addItemDecoration(
                        DividerItemDecoration(
                            context,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                })

                viewModel.errorMessage.observe(viewLifecycleOwner, {
                    Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
                })

                viewModel.getChuckNorrisJokes(jokeNum)
            } else
                Toast.makeText(activity, "Empty input", Toast.LENGTH_LONG).show()
        }
        return view
    }
}