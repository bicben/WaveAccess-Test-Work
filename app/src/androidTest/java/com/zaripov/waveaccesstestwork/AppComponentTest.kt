package com.zaripov.waveaccesstestwork

import android.support.test.runner.AndroidJUnit4
import com.zaripov.waveaccesstestwork.di.DaggerTestComponent
import com.zaripov.waveaccesstestwork.di.modules.AppModule
import com.zaripov.waveaccesstestwork.general.WaveAccessApp
import org.junit.*
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class AppComponentTest {
    companion object {

        @BeforeClass
        @JvmStatic
        fun setup() {

        }

        @AfterClass
        @JvmStatic
        fun tearDown() {

        }
    }

    @Inject
    lateinit var app: WaveAccessApp

    @Before
    fun before() {
        val testComponent = DaggerTestComponent
            .builder()
            .appModule(AppModule(WaveAccessApp.instance))
            .build()

        testComponent.inject(this)
    }

    @After
    fun after() {

    }


    @Test
    fun testName() {
        assert(app.name == "WaveAccess Test Work")
    }
}