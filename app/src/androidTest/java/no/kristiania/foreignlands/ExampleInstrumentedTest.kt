package no.kristiania.foreignlands

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented ic_splash_logo, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under ic_splash_logo.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.foreignlands", appContext.packageName)
    }
}
