package ch.yvu.songbuzzer

import ch.yvu.songbuzzer.lifecycle.LifecycleListener
import ch.yvu.songbuzzer.lifecycle.NoOpLifecycleListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainPresenter @Inject constructor() : LifecycleListener by NoOpLifecycleListener {
}