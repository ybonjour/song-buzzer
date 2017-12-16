package ch.yvu.songbuzzer

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}