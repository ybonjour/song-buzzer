package ch.yvu.songbuzzer.lifecycle

class LifecycleRegistry : LifecycleListener {

    private val listeners = mutableListOf<LifecycleListener>()

    override fun onResume() {
        listeners.forEach {
            it.onResume()
        }
    }

    fun register(listener: LifecycleListener) {
        listeners.add(listener)
    }
}