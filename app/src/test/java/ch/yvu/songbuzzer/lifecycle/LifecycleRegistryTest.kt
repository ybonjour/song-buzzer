package ch.yvu.songbuzzer.lifecycle

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class LifecycleRegistryTest {

    @Test
    fun notifiesRegisteredListenerOnResume() {
        val listener: LifecycleListener = mock()
        val lifecycleRegistry = LifecycleRegistry()
        lifecycleRegistry.register(listener)

        lifecycleRegistry.onResume()

        verify(listener).onResume()
    }

}