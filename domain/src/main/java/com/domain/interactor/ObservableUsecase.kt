package com.domain.interactor

import com.domain.executer.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class ObservableUsecase<T , in Params> constructor(
        private val postExecutionThread: PostExecutionThread
    ){
    private val disposables = CompositeDisposable()
    protected abstract fun buildUseCaseObservable(params: Params?= null): Observable<T>
    open fun execute(observable: DisposableObserver<T> , params: Params?=null){
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(postExecutionThread.scheduler)
    }
    fun dispose() =
        disposables.dispose()

    private fun addDisposable(disposable: Disposable){
        disposables.add(disposable)
    }

}