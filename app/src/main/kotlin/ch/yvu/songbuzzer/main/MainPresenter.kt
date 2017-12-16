package ch.yvu.songbuzzer.main

import ch.yvu.songbuzzer.lifecycle.LifecycleListener
import ch.yvu.songbuzzer.lifecycle.NoOpLifecycleListener
import ch.yvu.songbuzzer.presentation.PresenterWithLifecycle
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenter @Inject constructor() : PresenterWithLifecycle<MainView>, LifecycleListener by NoOpLifecycleListener {
    override lateinit var view: MainView

    private var songSearchDisposable: Disposable? = null

    override fun onResume() {
        songSearchDisposable = view.songSearchChanges()
            .subscribeBy(
                onNext = { view.showSong(it) },
                onError = { view.showErrorMessage(it.message ?: "Error") }
            )
    }
}