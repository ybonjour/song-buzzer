package ch.yvu.songbuzzer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ch.yvu.songbuzzer.lifecycle.LifecycleListener
import ch.yvu.songbuzzer.lifecycle.LifecycleRegistry

abstract class PresentingActivity : AppCompatActivity() {

    private val lifecycleRegistry = LifecycleRegistry()

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(SongBuzzerApplication.applicationComponent)
        setupView()
    }

    protected fun registerPresenter(lifecycleListener: LifecycleListener) {
        lifecycleRegistry.register(lifecycleListener)
    }

    abstract fun inject(component: ApplicationComponent)
    abstract fun setupView()
    abstract fun registerPresenters()
}