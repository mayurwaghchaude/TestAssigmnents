package com.eskeon.testassigmnents

import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.os.Looper
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.marginBottom
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
class Assignment4Test {

    private lateinit var activity: Assignment4Activity
    private lateinit var parent: LinearLayout
    val rootWidth = 500
    private var expectedWidth = 0

    private lateinit var tvContactTitle: TextView
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSubmit: Button

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(Assignment4Activity::class.java)
            .create()
            .start()
            .resume()
            .get()

        tvContactTitle = activity.findViewById(R.id.tvContactTitle)
        etName = activity.findViewById(R.id.etName)
        etMessage = activity.findViewById(R.id.etMessage)
        btnSubmit = activity.findViewById(R.id.btnSubmit)
        etEmail = activity.findViewById(R.id.etEmail)


        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        // --- Root with fixed width (simulate screen) ---

        val root = ScrollView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                rootWidth,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        // Inflate layout into root
        val scrollView = android.view.LayoutInflater.from(context)
            .inflate(R.layout.activity_assignment4, root, false) as ScrollView
        root.addView(scrollView)


        // Measure the hierarchy
        val widthSpec = View.MeasureSpec.makeMeasureSpec(rootWidth, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)

        root.measure(widthSpec, heightSpec)
        root.layout(0, 0, rootWidth, root.measuredHeight)
        parent = scrollView.findViewById(R.id.layout)
        val paddingStart = parent.paddingStart
        val paddingEnd = parent.paddingEnd

        //Start and end padding should be removed to get the match_parent width of a view
        expectedWidth = parent.measuredWidth - (paddingStart + paddingEnd)

    }

    //Contact Us TextView Tests
    @Test
    fun verifyContactUsTextViews_label() {

        assertEquals("Contact Us", tvContactTitle.text.toString())
    }

    @Test
    fun verifyContactUsTextViews_width_height() {
        val textView: TextView = parent.findViewById(R.id.tvContactTitle)

        textView.text = "Contact Us"
        val height = textView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, textView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyContactUsTextViews_textColor() {

        val expectedColor = Color.parseColor("#000000")
        assertEquals(expectedColor, tvContactTitle.currentTextColor)
    }

    @Test
    fun verifyContactUsTextViews_textSize() {

        assertEquals(28.0f, tvContactTitle.textSize)
    }

    @Test
    fun verifyContactUsTextViews_textStyle() {
        val isBold = (tvContactTitle.typeface?.style ?: 0) and Typeface.BOLD != 0
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertTrue("TextView should be bold", isBold)
    }

    @Test
    fun verifyContactUsTextViews_margin() {

        assertEquals(20, tvContactTitle.marginTop)
        assertEquals(30, tvContactTitle.marginBottom)
    }

    @Test
    fun verifyContactUsTextViews_gravity() {

        assertEquals(Gravity.CENTER, tvContactTitle.gravity)
    }


    // Name EditText Tests

    @Test
    fun verifyNameEditText_width_height() {
        val editText: EditText = parent.findViewById(R.id.etName)

        val height = editText.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, editText.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyNameEditText_margin() {

        assertEquals(20, etName.marginBottom)
    }

    @Test
    fun verifyNameEditText_padding() {

        assertEquals(14, etName.paddingStart)
        assertEquals(14, etName.paddingEnd)
        assertEquals(14, etName.paddingTop)
        assertEquals(14, etName.paddingBottom)
    }

    @Test
    fun verifyNameEditText_hint() {

        assertEquals("Name", etName.hint)

    }

    @Test
    fun verifyNameEditText_inputType() {

        assertEquals(
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PERSON_NAME,
            etName.inputType
        )

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

        assertEquals(10, btnSubmit.marginTop)
    }

    @Test
    fun verifyButton_padding() {
        assertEquals(14, btnSubmit.paddingStart)
        assertEquals(14, btnSubmit.paddingEnd)

        //Button automatically adds 4dp to the top and bottom padding
        assertEquals(18, btnSubmit.paddingTop)
        assertEquals(18, btnSubmit.paddingBottom)
    }

    @Test
    fun verifyButton_isAllCaps() {

        assertTrue("Expected isAllCaps is false ", !btnSubmit.isAllCaps)
    }


    // Message EditText Tests

    @Test
    fun verifyMessageEditText_width_height() {

        val textView: TextView = parent.findViewById(R.id.etMessage)

        val expectedHeight = (160 * activity.resources.displayMetrics.density).toInt()

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, textView.measuredWidth)
        assertEquals(expectedHeight, etMessage.layoutParams.height)
    }

    @Test
    fun verifyMessageEditText_margin() {

        assertEquals(30, etMessage.marginBottom)
    }

    @Test
    fun verifyMessageEditText_padding() {

        assertEquals(14, etMessage.paddingStart)
        assertEquals(14, etMessage.paddingEnd)
        assertEquals(14, etMessage.paddingTop)
        assertEquals(14, etMessage.paddingBottom)
    }

    @Test
    fun verifyMessageEditText_hint() {

        assertEquals("Message", etMessage.hint)

    }

    @Test
    fun verifyMessageEditText_gravity() {

        // Check top flag
        assertTrue((etMessage.gravity and Gravity.TOP) == Gravity.TOP)

        // Check start flag
        assertTrue((etMessage.gravity and Gravity.START) == Gravity.START)
    }

    @Test
    fun verifyMessageEditText_inputType() {

        assertEquals(
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE,
            etMessage.inputType
        )

    }


    // Email EditText Tests

    @Test
    fun verifyEmailEditText_width_height() {
        val editText: EditText = parent.findViewById(R.id.etEmail)

        val height = editText.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, editText.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyEmailEditText_margin() {

        assertEquals(20, etEmail.marginBottom)
    }

    @Test
    fun verifyEmailEditText_padding() {

        assertEquals(14, etEmail.paddingStart)
        assertEquals(14, etEmail.paddingEnd)
        assertEquals(14, etEmail.paddingTop)
        assertEquals(14, etEmail.paddingBottom)
    }

    @Test
    fun verifyEmailEditText_hint() {

        assertEquals("Email", etEmail.hint)

    }

    @Test
    fun verifyEmailEditText_inputType() {

        assertEquals(
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
            etEmail.inputType
        )

    }


    //positions of the views

    @Test
    fun verifyPositionsOfViews() {

        val tvContactTitle: TextView = parent.findViewById(R.id.tvContactTitle)
        val etMessage: EditText = parent.findViewById(R.id.etMessage)
        val btnSubmit: Button = parent.findViewById(R.id.btnSubmit)
        val etEmail: EditText = parent.findViewById(R.id.etEmail)
        val etName: EditText = parent.findViewById(R.id.etName)

        val tvContactTitleBottom = tvContactTitle.bottom
        val etNameTop = etName.top

        val etNameBottom = etName.bottom
        val etEmailTop = etEmail.top

        val etEmailBottom = etEmail.bottom
        val etMessageTop = etMessage.top

        val etMessageBottom = etMessage.bottom
        val btnSubmitTop = btnSubmit.top


        assertTrue(etNameTop >= tvContactTitleBottom)
        assertTrue(etEmailTop >= etNameBottom)
        assertTrue(etMessageTop >= etEmailBottom)
        assertTrue(btnSubmitTop >= etMessageBottom)


    }


}