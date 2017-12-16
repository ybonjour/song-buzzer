package ch.yvu.songbuzzer

import android.os.Bundle
import javax.inject.Inject

class MainActivity : PresentingActivity() {
    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
