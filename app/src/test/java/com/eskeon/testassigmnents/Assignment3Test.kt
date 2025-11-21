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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], manifest = Config.NONE, application = Application::class)
class Assignment3Test {

    private lateinit var activity: Assignment3Activity
    private lateinit var parent: LinearLayout
    val rootWidth = 500
    private var expectedWidth = 0

    private lateinit var tvHelloWorld: TextView
    private lateinit var imgLogo: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvSubtitle: TextView
    private lateinit var tvSecondSubtitle: TextView
    private lateinit var etInput: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUsername: EditText
    private lateinit var btnSubmit: Button

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(Assignment3Activity::class.java)
            .create()
            .start()
            .resume()
            .get()

        tvHelloWorld = activity.findViewById(R.id.tvHelloWorld)
        tvSubtitle = activity.findViewById(R.id.tvSubtitle)
        etInput = activity.findViewById(R.id.etInput)
        btnSubmit = activity.findViewById(R.id.btnSubmit)
        imgLogo = activity.findViewById(R.id.imgLogo)
        tvTitle = activity.findViewById(R.id.tvTitle)
        tvSecondSubtitle = activity.findViewById(R.id.tvSecondSubtitle)
        etEmail = activity.findViewById(R.id.etEmail)
        etUsername = activity.findViewById(R.id.etUsername)


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
            .inflate(R.layout.activity_assignment3, root, false) as ScrollView
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

    //Hello world Tests
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

        assertEquals(40, tvHelloWorld.marginTop)
    }

    @Test
    fun verifyTextViews_gravity() {

        assertEquals(Gravity.CENTER, tvHelloWorld.gravity)
    }

    //Second subtitle tests
    @Test
    fun verifySubtitleTextViews_label() {

        assertEquals("This is a subtitle", tvSecondSubtitle.text.toString())
    }

    @Test
    fun verifySubtitleTextViews_width_height() {
        val textView: TextView = parent.findViewById(R.id.tvSecondSubtitle)

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
        assertEquals(expectedColor, tvSecondSubtitle.currentTextColor)
    }

    @Test
    fun verifySubtitleTextViews_textSize() {

        assertEquals(16.0f, tvSecondSubtitle.textSize)
    }

    @Test
    fun verifySubtitleTextViews_margin() {

        assertEquals(20, tvSecondSubtitle.marginTop)
    }

    @Test
    fun verifySubtitleTextViews_gravity() {

        assertEquals(Gravity.CENTER, tvSecondSubtitle.gravity)
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

        assertEquals(15, etInput.marginTop)
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

        assertEquals(30, btnSubmit.marginTop)
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

    //Title TextView Tests

    @Test
    fun verifyTitleView_label() {

        assertEquals("Welcome", tvTitle.text.toString())
    }

    @Test
    fun verifyTitleView_width_height() {
        val textView: TextView = parent.findViewById(R.id.tvTitle)

        textView.text = "Welcome"
        val height = textView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, textView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyTitleView_textColor() {

        val expectedColor = Color.parseColor("#000000")
        assertEquals(expectedColor, tvTitle.currentTextColor)
    }

    @Test
    fun verifyTitleView_textStyle() {
        val isBold = (tvTitle.typeface?.style ?: 0) and Typeface.BOLD != 0
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertTrue("TitleView should be bold", isBold)
    }

    @Test
    fun verifyTitleView_textSize() {

        assertEquals(26.0f, tvTitle.textSize)
    }

    @Test
    fun verifyTitleView_margin() {

        assertEquals(20, tvTitle.marginTop)
    }

    @Test
    fun verifyTitleView_gravity() {

        assertEquals(Gravity.CENTER, tvTitle.gravity)
    }

    //Subtitle TextView Tests

    @Test
    fun verifySubtitleView_label() {

        assertEquals("Please enter your details below", tvSubtitle.text.toString())
    }

    @Test
    fun verifySubtitleView_width_height() {
        val textView: TextView = parent.findViewById(R.id.tvSubtitle)

        textView.text = "Please enter your details below"
        val height = textView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, textView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifySubtitleView_textColor() {

        val expectedColor = Color.parseColor("#555555")
        assertEquals(expectedColor, tvSubtitle.currentTextColor)
    }

    @Test
    fun verifySubtitleView_textSize() {

        assertEquals(16.0f, tvSubtitle.textSize)
    }

    @Test
    fun verifySubtitleView_margin() {

        assertEquals(10, tvSubtitle.marginTop)
    }

    @Test
    fun verifySubtitleView_gravity() {

        assertEquals(Gravity.CENTER, tvSubtitle.gravity)
    }


    // Username EditText Tests

    @Test
    fun verifyUsernameEditText_width_height() {
        val editText: EditText = parent.findViewById(R.id.etUsername)

        val height = editText.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, editText.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyUsernameEditText_margin() {

        assertEquals(20, etUsername.marginTop)
    }

    @Test
    fun verifyUsernameEditText_padding() {

        assertEquals(12, etUsername.paddingStart)
        assertEquals(12, etUsername.paddingEnd)
        assertEquals(12, etUsername.paddingTop)
        assertEquals(12, etUsername.paddingBottom)
    }

    @Test
    fun verifyUsernameEditText_hint() {

        assertEquals("Username", etUsername.hint)

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

        assertEquals(15, etEmail.marginTop)
    }

    @Test
    fun verifyEmailEditText_padding() {

        assertEquals(12, etEmail.paddingStart)
        assertEquals(12, etEmail.paddingEnd)
        assertEquals(12, etEmail.paddingTop)
        assertEquals(12, etEmail.paddingBottom)
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


    //ImageView Tests


    @Test
    fun verifyImageView_width_height() {
        val expectedWidth = (120 * activity.resources.displayMetrics.density).toInt()
        val expectedHeight = (120 * activity.resources.displayMetrics.density).toInt()

        assertEquals(expectedWidth, imgLogo.layoutParams.width)
        assertEquals(expectedHeight, imgLogo.layoutParams.height)
    }


    @Test
    fun verifyImageView_margin() {

        assertEquals(40, imgLogo.marginTop)
    }


    @Test
    fun verifyImageView_source() {

        // Get assigned drawable
        val drawable = imgLogo.drawable

        // Ensure drawable exists
        assertNotNull("ImageView drawable should not be null", drawable)

        // Convert drawable into a resource ID
        val actualResId = shadowOf(drawable).createdFromResId

        // Assert expected drawable resource
        assertEquals(R.mipmap.ic_launcher, actualResId)
    }


    //positions of the views

    @Test
    fun verifyPositionsOfViews() {

        val tvHelloWorld: TextView = parent.findViewById(R.id.tvHelloWorld)
        val tvSubtitle: TextView = parent.findViewById(R.id.tvSubtitle)
        val etInput: EditText = parent.findViewById(R.id.etInput)
        val btnSubmit: Button = parent.findViewById(R.id.btnSubmit)

        val imgLogo: ImageView = parent.findViewById(R.id.imgLogo)
        val tvTitle: TextView = parent.findViewById(R.id.tvTitle)
        val tvSecondSubtitle: TextView = parent.findViewById(R.id.tvSecondSubtitle)
        val etEmail: EditText = parent.findViewById(R.id.etEmail)
        val etUsername: EditText = parent.findViewById(R.id.etUsername)

        val imgLogoBottom = imgLogo.bottom
        val tvTitleTop = tvTitle.top

        val tvTitleBottom = tvTitle.bottom
        val tvSubtitleTop = tvSubtitle.top

        val tvSubtitleBottom = tvSubtitle.bottom
        val tvHelloWorldTop = tvHelloWorld.top

        val tvHelloWorldBottom = tvHelloWorld.bottom
        val tvSecondSubtitleTop = tvSecondSubtitle.top

        val tvSecondSubtitleBottom = tvSecondSubtitle.bottom
        val etUsernameTop = etUsername.top

        val etUsernameBottom = etUsername.bottom
        val etEmailTop = etEmail.top

        val etEmailBottom = etEmail.bottom
        val etInputTop = etInput.top


        val etInputBottom = etInput.bottom
        val btnSubmitTop = btnSubmit.top

        assertTrue(tvTitleTop >= imgLogoBottom)
        assertTrue(tvSubtitleTop >= tvTitleBottom)
        assertTrue(tvHelloWorldTop >= tvSubtitleBottom)

        assertTrue(tvSecondSubtitleTop >= tvHelloWorldBottom)
        assertTrue(etUsernameTop >= tvSecondSubtitleBottom)
        assertTrue(etEmailTop >= etUsernameBottom)

        assertTrue(etInputTop >= etEmailBottom)
        assertTrue(btnSubmitTop >= etInputBottom)

    }


}