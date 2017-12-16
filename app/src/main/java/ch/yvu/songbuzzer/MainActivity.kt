package ch.yvu.songbuzzer

import ch.yvu.songbuzzer.presentation.PresentingActivity
import javax.inject.Inject

class MainActivity : PresentingActivity(), MainView {
    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
    }

    override fun registerPresenters() {
        registerPresenter(mainPresenter, this)
    }

    override fun setupView() {
        setContentView(R.layout.activity_main)
    }
}
