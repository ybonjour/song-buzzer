package ch.yvu.songbuzzer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ch.yvu.songbuzzer.lifecycle.LifecycleRegistry

abstract class PresentingActivity : AppCompatActivity() {

    private val lifecycleRegistry = LifecycleRegistry()

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(SongBuzzerApplication.applicationComponent)
        setupView()
    }

    protected fun <ViewType> registerPresenter(presenter: PresenterWithLifecycle<ViewType>, view: ViewType) {
        presenter.view = view
        lifecycleRegistry.register(presenter)
    }

    abstract fun inject(component: ApplicationComponent)
    abstract fun setupView()
    abstract fun registerPresenters()
}