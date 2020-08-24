package com.domain.interactor

import com.domain.executer.PostExecutionThread
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUserCase<in Params> constructor(
    private val postExecutionThread: PostExecutionThread
){
    private val disposables = CompositeDisposable()
    protected abstract fun buildUserCaseObservable(params: Params?= null): Completable
    open fun execute(observable: DisposableCompletableObserver , params: Params?= null) {
        val observable = this.buildUserCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
    }
    fun dispose() =
        disposables.dispose()

    private fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }
}