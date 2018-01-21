package ch.yvu.songbuzzer

import ch.yvu.songbuzzer.http.HttpModule
import ch.yvu.songbuzzer.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                HttpModule::class
        )
)
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
}