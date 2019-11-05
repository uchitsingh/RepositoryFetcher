package com.tdd.uchit.repositoryfetcher.vierwmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tdd.uchit.repositoryfetcher.RepoApi
import com.tdd.uchit.repositoryfetcher.model.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    private val reposObservable: MutableLiveData<List<Repository>> = MutableLiveData()

    fun getData() {
        disposable.add(
            RepoApi.retrofitService.getRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { repos ->
                    Log.i(TAG, repos.toString())
                    reposObservable.value = repos
                },
                {
                    Log.i(TAG, it.message)
                }))
    }

    fun getRepos() = reposObservable

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    companion object {
        private const val TAG = "TAG"
    }
}