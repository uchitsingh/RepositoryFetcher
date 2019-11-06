package com.tdd.uchit.repositoryfetcher.vierwmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tdd.uchit.repositoryfetcher.RepoApi
import com.tdd.uchit.repositoryfetcher.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepositoryViewModel : ViewModel() {

    private var _reposObservable: MutableLiveData<List<Repository>> = MutableLiveData()
    val repoObservable: LiveData<List<Repository>>
        get() = _reposObservable

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main
    )

    fun getData() {
        coroutineScope.launch {
            val getReposDeferred = RepoApi
                .retrofitService
                .getRepos()

            try {
                val listResult = getReposDeferred.await()
                Log.i(TAG, listResult.toString())
                _reposObservable.value = listResult
            } catch (e: Exception) {
                Log.i(TAG, e.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    companion object {
        private const val TAG = "TAG"
    }
}