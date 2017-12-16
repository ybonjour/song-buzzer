package ch.yvu.songbuzzer.lifecycle

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class LifecycleRegistryTest {

    private lateinit var lifecycleRegistry: LifecycleRegistry

    @Before
    fun setUp() {
        lifecycleRegistry = LifecycleRegistry()
    }

    @Test
    fun notifiesRegisteredListenerOnResume() {
        val listener: LifecycleListener = mock()
        lifecycleRegistry.register(listener)

        lifecycleRegistry.onResume()

        verify(listener).onResume()
    }

    @Test
    fun notifiesAllRegisteredListeners() {
        val listener1: LifecycleListener = mock()
        val listener2: LifecycleListener = mock()
        lifecycleRegistry.register(listener1)
        lifecycleRegistry.register(listener2)

        lifecycleRegistry.onResume()

        verify(listener1).onResume()
        verify(listener2).onResume()
    }

    @Test
    fun doesNotThrowAnExceptionIfNoListenerIsRegistered() {
        lifecycleRegistry.onResume()
    }
}