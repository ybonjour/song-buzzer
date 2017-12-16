package ch.yvu.songbuzzer.main

import ch.yvu.songbuzzer.ApplicationComponent
import ch.yvu.songbuzzer.R
import ch.yvu.songbuzzer.presentation.PresentingActivity
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
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

    override fun songSearchChanges(): Observable<String> =
        RxView.keys(songName).map { songName.text.toString() }

    override fun showSong(song: String) {
        songDisplay.text = song
    }

    override fun showErrorMessage(message: String) {
        songDisplay.text = message
    }
}
