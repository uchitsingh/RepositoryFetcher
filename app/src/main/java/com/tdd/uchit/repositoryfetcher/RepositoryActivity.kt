package com.tdd.uchit.repositoryfetcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tdd.uchit.repositoryfetcher.vierwmodel.RepositoryViewModel
import kotlinx.android.synthetic.main.activity_main.*

class RepositoryActivity : AppCompatActivity() {

    private lateinit var viewModel: RepositoryViewModel //informing the compiler that this will variable will be assigned later
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRecyvlerView()
        viewModel = ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
        viewModel.getData()
        viewModel.getRepos().observe(this, Observer { repos ->
            repositoryAdapter.setData(repos)
        })
    }

    private fun initializeRecyvlerView() {
        repositoryRecyclerView.layoutManager = LinearLayoutManager(this)
        repositoryAdapter = RepositoryAdapter()
        repositoryRecyclerView.adapter = repositoryAdapter
    }

}
