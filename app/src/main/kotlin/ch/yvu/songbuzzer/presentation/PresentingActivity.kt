package ch.yvu.songbuzzer.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ch.yvu.songbuzzer.ApplicationComponent
import ch.yvu.songbuzzer.SongBuzzerApplication
import ch.yvu.songbuzzer.lifecycle.LifecycleRegistry

abstract class PresentingActivity : AppCompatActivity() {

    private val lifecycleRegistry = LifecycleRegistry()

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(SongBuzzerApplication.applicationComponent)
        setupView()
        registerPresenters()
    }

    protected fun <ViewType> registerPresenter(presenter: PresenterWithLifecycle<ViewType>, view: ViewType) {
        presenter.view = view
        lifecycleRegistry.register(presenter)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.onResume()
    }

    override fun onPause() {
        super.onPause()
        lifecycleRegistry.onPause()
    }

    abstract fun inject(component: ApplicationComponent)
    abstract fun setupView()
    abstract fun registerPresenters()
}