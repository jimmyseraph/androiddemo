package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val BASIC_SAMPLE_PACKAGE = "com.example.myapplication"

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 21)
@LargeTest
class UIAutomatorTest {
    private lateinit var device : UiDevice

    @Before
    fun startMainActivity() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.pressHome()
        // Wait for launcher
        val launcherPackage : String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            5000L
        )
        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)?.apply {
            // Clear out any previous instances
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            5000L
        )
    }

    @Test
    fun testInputMessage() {
        val nameInput: UiObject = device.findObject(
            UiSelector().resourceId("com.example.myapplication:id/nameInput")
        )
        val gameSelector: UiObject = device.findObject(
            UiSelector().resourceId("com.example.myapplication:id/spinner")
        )
        nameInput.text = "UiAutomator"
        gameSelector.click()
        val game: UiObject = device.findObject(
            UiSelector().text("StarCraft").className("android.widget.CheckedTextView")
        )
        game.click()
        val button: UiObject = device.findObject(
            UiSelector().resourceId("com.example.myapplication:id/button")
        )
        button.click()

        val text1: UiObject = device.findObject(
            UiSelector().resourceId("com.example.myapplication:id/textView1")
        )
        val text2: UiObject = device.findObject(
            UiSelector().resourceId("com.example.myapplication:id/textView2")
        )
        assertEquals("UiAutomator", text1.text)
        assertEquals("StarCraft", text2.text)
    }
}