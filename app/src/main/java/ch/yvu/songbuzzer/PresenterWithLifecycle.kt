package ch.yvu.songbuzzer

import ch.yvu.songbuzzer.lifecycle.LifecycleListener

interface PresenterWithLifecycle<ViewType> : Presenter<ViewType>, LifecycleListener