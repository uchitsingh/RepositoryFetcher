package com.tdd.uchit.repositoryfetcher

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tdd.uchit.repositoryfetcher.model.Repository
import kotlinx.android.synthetic.main.repository_recyclerview.view.*

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    private val repos = mutableListOf<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflatedView = parent.inflate(R.layout.repository_recyclerview, false)
        return RepositoryViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindView(repos[position])
    }

    fun setData(repositoryList: List<Repository>) {
        repos.clear()
        repos.addAll(repositoryList)
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id: TextView? = itemView.repository_id
        private val name: TextView? = itemView.name

        fun bindView(repository: Repository) {
            id?.text = repository.id
            name?.text = repository.name
        }
    }

}


