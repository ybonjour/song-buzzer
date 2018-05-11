package ch.yvu.songbuzzer.lifecycle

object NoOpLifecycleListener : LifecycleListener {
    override fun onResume() {}

    override fun onPause() {}
}