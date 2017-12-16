package ch.yvu.songbuzzer.presentation

import ch.yvu.songbuzzer.lifecycle.LifecycleListener

interface PresenterWithLifecycle<ViewType> : Presenter<ViewType>, LifecycleListener