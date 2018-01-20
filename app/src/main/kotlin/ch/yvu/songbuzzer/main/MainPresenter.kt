package ch.yvu.songbuzzer.main

import ch.yvu.songbuzzer.lifecycle.LifecycleListener
import ch.yvu.songbuzzer.lifecycle.NoOpLifecycleListener
import ch.yvu.songbuzzer.presentation.PresenterWithLifecycle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenter @Inject constructor() : PresenterWithLifecycle<MainView>, LifecycleListener by NoOpLifecycleListener {
    override lateinit var view: MainView
}