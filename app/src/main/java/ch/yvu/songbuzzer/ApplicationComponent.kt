package ch.yvu.songbuzzer

import ch.yvu.songbuzzer.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}