package ch.yvu.songbuzzer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class PresentingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(SongBuzzerApplication.applicationComponent)
    }

    abstract fun inject(component: ApplicationComponent)
}