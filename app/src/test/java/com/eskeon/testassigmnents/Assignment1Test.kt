package com.eskeon.testassigmnents

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE, application = Application::class)
class Assignment1Test {

    private lateinit var activity: Assignment1Activity
    private lateinit var tvHelloWorld: TextView

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(Assignment1Activity::class.java)
            .create()
            .start()
            .resume()
            .get()

        tvHelloWorld = activity.findViewById(R.id.tvHelloWorld)

    }

    @Test
    fun verifyTextViews_label() {

        assertEquals("Hello World", tvHelloWorld.text.toString())
    }

    @Test
    fun verifyTextViews_width_height() {

        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        // --- Root with fixed width (simulate screen) ---
        val rootWidth = 500
        val root = ConstraintLayout(context).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                rootWidth,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Inflate layout into root
        val parent = android.view.LayoutInflater.from(context)
            .inflate(R.layout.activity_assignment_1, root, false) as ConstraintLayout
        root.addView(parent)

        val textView: TextView = parent.findViewById(R.id.tvHelloWorld)

        // Measure the hierarchy
        val widthSpec = View.MeasureSpec.makeMeasureSpec(rootWidth, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)

        root.measure(widthSpec, heightSpec)
        root.layout(0, 0, rootWidth, root.measuredHeight)
        val paddingStart = parent.paddingStart
        val paddingEnd = parent.paddingEnd
        val expectedWidth = rootWidth - (paddingStart + paddingEnd)
        val expectedHeight = (200 * activity.resources.displayMetrics.density).toInt()

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, textView.measuredWidth)
        assertEquals(expectedHeight, tvHelloWorld.layoutParams.height)
    }

    @Test
    fun verifyTextViews_textColor() {

        val expectedColor = Color.parseColor("#000000")
        assertEquals(expectedColor, tvHelloWorld.currentTextColor)
    }

    @Test
    fun verifyTextViews_textSize() {

        assertEquals(24.0f, tvHelloWorld.textSize)
    }

    @Test
    fun verifyTextViews_textStyle() {
        val isBold = (tvHelloWorld.typeface?.style ?: 0) and Typeface.BOLD != 0
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertTrue("TextView should be bold", isBold)
    }

    @Test
    fun verifyTextViews_margin() {

        assertEquals(100, tvHelloWorld.marginTop)
    }

    @Test
    fun verifyTextViews_gravity() {

        assertEquals(Gravity.CENTER, tvHelloWorld.gravity)
    }
}