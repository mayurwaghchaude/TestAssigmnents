package com.eskeon.testassigmnents

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
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
class Assignment2Test {

    private lateinit var activity: Assignment2Activity
    private lateinit var parent: ConstraintLayout
    val rootWidth = 500
    private var expectedWidth = 0

    private lateinit var tvHelloWorld: TextView
    private lateinit var tvSubtitle: TextView
    private lateinit var etInput: EditText
    private lateinit var btnSubmit: Button

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(Assignment2Activity::class.java)
            .create()
            .start()
            .resume()
            .get()

        tvHelloWorld = activity.findViewById(R.id.tvHelloWorld)
        tvSubtitle = activity.findViewById(R.id.tvSubtitle)
        etInput = activity.findViewById(R.id.etInput)
        btnSubmit = activity.findViewById(R.id.btnSubmit)


        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        // --- Root with fixed width (simulate screen) ---

        val root = ConstraintLayout(context).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                rootWidth,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Inflate layout into root
        parent = android.view.LayoutInflater.from(context)
            .inflate(R.layout.activity_assignment2, root, false) as ConstraintLayout
        root.addView(parent)


        // Measure the hierarchy
        val widthSpec = View.MeasureSpec.makeMeasureSpec(rootWidth, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)

        root.measure(widthSpec, heightSpec)
        root.layout(0, 0, rootWidth, root.measuredHeight)
        val paddingStart = parent.paddingStart
        val paddingEnd = parent.paddingEnd

        //Start and end padding should be removed to get the match_parent width of a view
        expectedWidth = rootWidth - (paddingStart + paddingEnd)

    }


    //TextView Tests

    @Test
    fun verifyTextViews_label() {

        assertEquals("Hello World", tvHelloWorld.text.toString())
    }

    @Test
    fun verifyTextViews_width_height() {

        val textView: TextView = parent.findViewById(R.id.tvHelloWorld)

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


    //Subtitle textview tests

    @Test
    fun verifySubtitleTextViews_label() {

        assertEquals("This is a subtitle", tvSubtitle.text.toString())
    }

    @Test
    fun verifySubtitleTextViews_width_height() {
        val textView: TextView = parent.findViewById(R.id.tvSubtitle)

        textView.text = "This is a subtitle"
        val height = textView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, textView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifySubtitleTextViews_textColor() {

        val expectedColor = Color.parseColor("#555555")
        assertEquals(expectedColor, tvSubtitle.currentTextColor)
    }

    @Test
    fun verifySubtitleTextViews_textSize() {

        assertEquals(16.0f, tvSubtitle.textSize)
    }

    @Test
    fun verifySubtitleTextViews_margin() {

        assertEquals(20, tvSubtitle.marginTop)
    }

    @Test
    fun verifySubtitleTextViews_gravity() {

        assertEquals(Gravity.CENTER, tvSubtitle.gravity)
    }


    //EditText Tests

    @Test
    fun verifyEditText_width_height() {
        val editText: EditText = parent.findViewById(R.id.etInput)

        val height = editText.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, editText.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyEditText_margin() {

        assertEquals(20, etInput.marginTop)
    }

    @Test
    fun verifyEditText_padding() {

        assertEquals(12, etInput.paddingStart)
        assertEquals(12, etInput.paddingEnd)
        assertEquals(12, etInput.paddingTop)
        assertEquals(12, etInput.paddingBottom)
    }

    @Test
    fun verifyEditText_hint() {

        assertEquals("Enter something...", etInput.hint)

    }


    //Button Tests

    @Test
    fun verifyButton_label() {

        assertEquals("Submit", btnSubmit.text.toString())
    }

    @Test
    fun verifyButton_width_height() {
        val button: Button = parent.findViewById(R.id.btnSubmit)

        button.text = "Submit"
        val height = button.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, button.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }


    @Test
    fun verifyButton_margin() {

        assertEquals(40, btnSubmit.marginTop)
    }

    @Test
    fun verifyButton_padding() {
        assertEquals(12, btnSubmit.paddingStart)
        assertEquals(12, btnSubmit.paddingEnd)

        //Button automatically adds 4dp to the top and bottom padding
        assertEquals(16, btnSubmit.paddingTop)
        assertEquals(16, btnSubmit.paddingBottom)
    }

    @Test
    fun verifyButton_isAllCaps() {

        assertTrue("Expected isAllCaps is false ", !btnSubmit.isAllCaps)
    }

    //positions of the views

    @Test
    fun verifyPositionsOfViews() {
        val tvHelloWorld: TextView = parent.findViewById(R.id.tvHelloWorld)
        val tvSubtitle: TextView = parent.findViewById(R.id.tvSubtitle)
        val etInput: EditText = parent.findViewById(R.id.etInput)
        val btnSubmit: Button = parent.findViewById(R.id.btnSubmit)

        val tvHelloWorldBottom = tvHelloWorld.bottom
        val tvSubtitleTop = tvSubtitle.top

        val tvSubtitleBottom = tvSubtitle.bottom
        val etInputTop = etInput.top

        val etInputBottom = etInput.bottom
        val btnSubmitTop = btnSubmit.top

        assertTrue(tvSubtitleTop >= tvHelloWorldBottom)
        assertTrue(etInputTop >= tvSubtitleBottom)
        assertTrue(btnSubmitTop >= etInputBottom)

    }


}