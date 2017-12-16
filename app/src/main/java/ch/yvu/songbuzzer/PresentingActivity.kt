package ch.yvu.songbuzzer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class PresentingActivity : AppCompatActivity() {

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(SongBuzzerApplication.applicationComponent)
        setupView()
    }

    abstract fun inject(component: ApplicationComponent)
    abstract fun setupView()
}