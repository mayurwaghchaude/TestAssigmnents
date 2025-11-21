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
import androidx.cardview.widget.CardView
import androidx.core.view.marginBottom
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
class Assignment5Test {

    private lateinit var activity: Assignment5Activity
    private lateinit var parent: LinearLayout
    val rootWidth = 500
    private var parentPadding = 20 * 2
    private var cardViewPadding = 16 * 2


    private lateinit var cvProfile: CardView
    private lateinit var cvAbout: CardView
    private lateinit var cvContact: CardView
    private lateinit var cvSocial: CardView

    private lateinit var imgProfile: ImageView
    private lateinit var tvProfileName: TextView
    private lateinit var tvProfileRole: TextView

    private lateinit var tvAboutTitle: TextView
    private lateinit var tvAboutText: TextView

    private lateinit var tvContactTitle: TextView
    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etMessage: EditText
    private lateinit var btnSubmit: Button

    private lateinit var tvConnectTitle: TextView
    private lateinit var imgFacebook: ImageView
    private lateinit var imgLinkedIn: ImageView
    private lateinit var imgTwitter: ImageView

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(Assignment5Activity::class.java)
            .create()
            .start()
            .resume()
            .get()

        cvProfile = activity.findViewById(R.id.cvProfile)
        cvAbout = activity.findViewById(R.id.cvAbout)
        cvContact = activity.findViewById(R.id.cvContact)
        cvSocial = activity.findViewById(R.id.cvSocial)

        imgProfile = activity.findViewById(R.id.imgProfile)
        tvProfileName = activity.findViewById(R.id.tvProfileName)
        tvProfileRole = activity.findViewById(R.id.tvProfileRole)

        tvAboutTitle = activity.findViewById(R.id.tvAboutTitle)
        tvAboutText = activity.findViewById(R.id.tvAboutText)

        tvContactTitle = activity.findViewById(R.id.tvContactTitle)
        etName = activity.findViewById(R.id.etName)
        etMessage = activity.findViewById(R.id.etMessage)
        btnSubmit = activity.findViewById(R.id.btnSubmit)
        etEmail = activity.findViewById(R.id.etEmail)

        tvConnectTitle = activity.findViewById(R.id.tvConnectTitle)
        imgFacebook = activity.findViewById(R.id.imgFacebook)
        imgLinkedIn = activity.findViewById(R.id.imgLinkedIn)
        imgTwitter = activity.findViewById(R.id.imgTwitter)


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
            .inflate(R.layout.activity_assignment5, root, false) as ScrollView
        root.addView(scrollView)


        // Measure the hierarchy
        val widthSpec = View.MeasureSpec.makeMeasureSpec(rootWidth, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)

        root.measure(widthSpec, heightSpec)
        root.layout(0, 0, rootWidth, root.measuredHeight)
        parent = scrollView.findViewById(R.id.layout)


    }


    //CardView Tests
    @Test
    fun verifyProfileCardView_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - parentPadding
        val cardView: CardView = parent.findViewById(R.id.cvProfile)

        val height = cardView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, cardView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected cardView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyAboutCardView_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - parentPadding
        val cardView: CardView = parent.findViewById(R.id.cvAbout)

        val height = cardView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, cardView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected cardView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyContactCardView_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - parentPadding
        val cardView: CardView = parent.findViewById(R.id.cvContact)

        val height = cardView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, cardView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected cardView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifySocialCardView_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - parentPadding
        val cardView: CardView = parent.findViewById(R.id.cvSocial)

        val height = cardView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, cardView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected cardView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyCardView_backgroundColor() {
        val expectedColor = Color.parseColor("#001431")
        assertEquals(expectedColor, cvProfile.cardBackgroundColor.defaultColor)
        assertEquals(expectedColor, cvAbout.cardBackgroundColor.defaultColor)
        assertEquals(expectedColor, cvContact.cardBackgroundColor.defaultColor)
        assertEquals(expectedColor, cvSocial.cardBackgroundColor.defaultColor)
    }

    @Test
    fun verifyCardView_margin() {

        assertEquals(24, cvProfile.marginBottom)
        assertEquals(24, cvAbout.marginBottom)
        assertEquals(24, cvContact.marginBottom)
        assertEquals(24, cvSocial.marginBottom)
    }

    @Test
    fun verifyCardView_radius() {

        assertEquals(10.0f, cvProfile.radius)
        assertEquals(10.0f, cvAbout.radius)
        assertEquals(10.0f, cvContact.radius)
        assertEquals(10.0f, cvSocial.radius)
    }

    //Profile Section
    //Profile Image Tests

    @Test
    fun verifyProfileImageView_width_height() {
        val expectedWidth = (120 * activity.resources.displayMetrics.density).toInt()
        val expectedHeight = (120 * activity.resources.displayMetrics.density).toInt()

        assertEquals(expectedWidth, imgProfile.layoutParams.width)
        assertEquals(expectedHeight, imgProfile.layoutParams.height)
    }


    @Test
    fun verifyProfileImageView_margin() {

        assertEquals(16, imgProfile.marginBottom)
    }


    @Test
    fun verifyProfileImageView_source() {

        // Get assigned drawable
        val drawable = imgProfile.drawable

        // Ensure drawable exists
        assertNotNull("ImageView drawable should not be null", drawable)

        // Convert drawable into a resource ID
        val actualResId = shadowOf(drawable).createdFromResId

        // Assert expected drawable resource
        assertEquals(R.drawable.profile, actualResId)
    }


    //Profile Name TextView Tests

    @Test
    fun verifyProfileNameTextView_label() {

        assertEquals("John Doe", tvProfileName.text.toString())
    }

    @Test
    fun verifyProfileNameTextView_width_height() {

        val textView: TextView = parent.findViewById(R.id.tvProfileName)

        textView.text = "John Doe"
        val height = textView.measuredHeight
        val width = textView.measuredWidth


        assertTrue("Expected TextView Width > 0 but was $width", width > 0)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyProfileNameTextView_textColor() {

        val expectedColor = Color.parseColor("#99D7EE")
        assertEquals(expectedColor, tvProfileName.currentTextColor)
    }

    @Test
    fun verifyProfileNameTextView_textStyle() {
        val isBold = (tvProfileName.typeface?.style ?: 0) and Typeface.BOLD != 0
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertTrue("TitleView should be bold", isBold)
    }

    @Test
    fun verifyProfileNameTextView_textSize() {

        assertEquals(34.0f, tvProfileName.textSize)
    }

    @Test
    fun verifyProfileNameTextView_margin() {

        assertEquals(4, tvProfileName.marginBottom)
    }


    //Profile Role TextView Tests

    @Test
    fun verifyProfileRoleTextView_label() {

        assertEquals("Android Application Developer", tvProfileRole.text.toString())
    }

    @Test
    fun verifyProfileRoleTextView_width_height() {
        val textView: TextView = parent.findViewById(R.id.tvProfileRole)

        textView.text = "Android Application Developer"
        val height = textView.measuredHeight
        val width = textView.measuredWidth


        assertTrue("Expected TextView Width > 0 but was $width", width > 0)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyProfileRoleTextView_textColor() {

        val expectedColor = Color.parseColor("#CCDAEE")
        assertEquals(expectedColor, tvProfileRole.currentTextColor)
    }

    @Test
    fun verifyProfileRoleTextView_textSize() {

        assertEquals(16.0f, tvProfileRole.textSize)
    }


    //About Section
    //About Title TextView Tests

    @Test
    fun verifyAboutTitleTextView_label() {

        assertEquals("About Me", tvAboutTitle.text.toString())
    }

    @Test
    fun verifyAboutTitleTextView_width_height() {

        val textView: TextView = parent.findViewById(R.id.tvAboutTitle)

        textView.text = "About Me"
        val height = textView.measuredHeight
        val width = textView.measuredWidth


        assertTrue("Expected TextView Width > 0 but was $width", width > 0)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyAboutTitleTextView_textColor() {

        val expectedColor = Color.parseColor("#BFD1EA")
        assertEquals(expectedColor, tvAboutTitle.currentTextColor)
    }

    @Test
    fun verifyAboutTitleTextView_textStyle() {
        val isBold = (tvAboutTitle.typeface?.style ?: 0) and Typeface.BOLD != 0
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertTrue("TitleView should be bold", isBold)
    }

    @Test
    fun verifyAboutTitleTextView_textSize() {

        assertEquals(25.0f, tvAboutTitle.textSize)
    }

    @Test
    fun verifyAboutTitleTextView_margin() {

        assertEquals(10, tvAboutTitle.marginBottom)
    }

    //About Text TextView Tests

    @Test
    fun verifyAboutTextView_label() {

        assertEquals(
            "I am a passionate Android developer with 3+ years of experience in building mobile applications. I specialize in Kotlin, XML UI design, and modern Android development practices. I enjoy creating clean, user-friendly interfaces and writing readable, scalable code.",
            tvAboutText.text.toString()
        )
    }

    @Test
    fun verifyAboutTextView_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - (parentPadding + cardViewPadding)
        val textView: TextView = parent.findViewById(R.id.tvAboutText)

        textView.text =
            "I am a passionate Android developer with 3+ years of experience in building mobile applications. I specialize in Kotlin, XML UI design, and modern Android development practices. I enjoy creating clean, user-friendly interfaces and writing readable, scalable code."
        val height = textView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, textView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyAboutTextView_textColor() {

        val expectedColor = Color.parseColor("#CCDAEE")
        assertEquals(expectedColor, tvAboutText.currentTextColor)
    }

    @Test
    fun verifyAboutTextView_textSize() {

        assertEquals(18.0f, tvAboutText.textSize)
    }

    @Test
    fun verifyAboutTextView_lineSpacingExtra() {

        assertEquals(6.0f, tvAboutText.lineSpacingExtra)
    }

    // Contact Us Section
    // Contact Us CardView Tests

    @Test
    fun verifyContactUsCardView_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - parentPadding
        val cardView: CardView = parent.findViewById(R.id.cvContact)

        val height = cardView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, cardView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected cardView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyContactUsCardView_backgroundColor() {
        val expectedColor = Color.parseColor("#001431")
        assertEquals(expectedColor, cvContact.cardBackgroundColor.defaultColor)
    }

    @Test
    fun verifyContactUsCardView_margin() {

        assertEquals(24, cvContact.marginBottom)
    }

    @Test
    fun verifyContactUsCardView_radius() {

        assertEquals(10.0f, cvContact.radius)
    }

    //Contact Us TextView Tests
    @Test
    fun verifyContactUsTextViews_label() {

        assertEquals("Contact Us", tvContactTitle.text.toString())
    }

    @Test
    fun verifyContactUsTextViews_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - (parentPadding + cardViewPadding)
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

        val expectedColor = Color.parseColor("#CCDAEE")
        assertEquals(expectedColor, tvContactTitle.currentTextColor)
    }

    @Test
    fun verifyContactUsTextViews_textSize() {

        assertEquals(25.0f, tvContactTitle.textSize)
    }

    @Test
    fun verifyContactUsTextViews_textStyle() {
        val isBold = (tvContactTitle.typeface?.style ?: 0) and Typeface.BOLD != 0
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertTrue("TextView should be bold", isBold)
    }

    @Test
    fun verifyContactUsTextViews_margin() {

        assertEquals(20, tvContactTitle.marginBottom)
    }

    @Test
    fun verifyContactUsTextViews_gravity() {
        val horizontalGravity = tvContactTitle.gravity and Gravity.HORIZONTAL_GRAVITY_MASK
        assertEquals(Gravity.LEFT, horizontalGravity)
    }


    // Name EditText Tests

    @Test
    fun verifyNameEditText_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - (parentPadding + cardViewPadding)
        val editText: EditText = parent.findViewById(R.id.etName)

        val height = editText.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, editText.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyNameEditText_margin() {

        assertEquals(16, etName.marginBottom)
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


    //Button Tests

    @Test
    fun verifyButton_label() {

        assertEquals("Submit", btnSubmit.text.toString())
    }

    @Test
    fun verifyButton_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - (parentPadding + cardViewPadding)
        val button: Button = parent.findViewById(R.id.btnSubmit)

        button.text = "Submit"
        val height = button.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, button.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
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
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - (parentPadding + cardViewPadding)

        val textView: TextView = parent.findViewById(R.id.etMessage)

        val expectedHeight = (150 * activity.resources.displayMetrics.density).toInt()

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, textView.measuredWidth)
        assertEquals(expectedHeight, etMessage.layoutParams.height)
    }

    @Test
    fun verifyMessageEditText_margin() {

        assertEquals(20, etMessage.marginBottom)
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
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - (parentPadding + cardViewPadding)
        val editText: EditText = parent.findViewById(R.id.etEmail)

        val height = editText.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, editText.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyEmailEditText_margin() {

        assertEquals(16, etEmail.marginBottom)
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


    //Social Section

    //Connect Title TextView Tests
    @Test
    fun verifyConnectTitleTextViews_label() {

        assertEquals("Connect with me", tvConnectTitle.text.toString())
    }

    @Test
    fun verifyConnectTitleTextViews_width_height() {
        //Start and end padding should be removed to get the match_parent width of a view
        val expectedWidth = parent.measuredWidth - (parentPadding + cardViewPadding)
        val textView: TextView = parent.findViewById(R.id.tvConnectTitle)

        textView.text = "Connect with me"
        val height = textView.measuredHeight

        assertEquals(rootWidth, parent.measuredWidth)
        assertEquals(expectedWidth, textView.measuredWidth)
        // Assert the height is > 0 (wrap_content should produce measurable height)
        assertTrue("Expected TextView height > 0 but was $height", height > 0)
    }

    @Test
    fun verifyConnectTitleTextViews_textColor() {

        val expectedColor = Color.parseColor("#CCDAEE")
        assertEquals(expectedColor, tvConnectTitle.currentTextColor)
    }

    @Test
    fun verifyConnectTitleTextViews_textSize() {

        assertEquals(25.0f, tvConnectTitle.textSize)
    }

    @Test
    fun verifyConnectTitleTextViews_textStyle() {
        val isBold = (tvConnectTitle.typeface?.style ?: 0) and Typeface.BOLD != 0
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        assertTrue("TextView should be bold", isBold)
    }

    @Test
    fun verifyConnectTitleTextViews_margin() {

        assertEquals(20, tvConnectTitle.marginBottom)
    }

    @Test
    fun verifyConnectTitleTextViews_gravity() {

        val horizontalGravity = tvConnectTitle.gravity and Gravity.HORIZONTAL_GRAVITY_MASK
        assertEquals(Gravity.LEFT, horizontalGravity)
    }


    //Facebook ImageView Tests

    @Test
    fun verifyFacebookImageView_width_height() {
        val expectedWidth = (40 * activity.resources.displayMetrics.density).toInt()
        val expectedHeight = (40 * activity.resources.displayMetrics.density).toInt()

        assertEquals(expectedWidth, imgFacebook.layoutParams.width)
        assertEquals(expectedHeight, imgFacebook.layoutParams.height)
    }


    @Test
    fun verifyFacebookImageView_margin() {

        assertEquals(10, imgFacebook.marginTop)
    }


    @Test
    fun verifyFacebookImageView_source() {

        // Get assigned drawable
        val drawable = imgFacebook.drawable

        // Ensure drawable exists
        assertNotNull("ImageView drawable should not be null", drawable)

        // Convert drawable into a resource ID
        val actualResId = shadowOf(drawable).createdFromResId

        // Assert expected drawable resource
        assertEquals(R.drawable.fb, actualResId)
    }

    //Linkedin ImageView Tests

    @Test
    fun verifyLinkedinImageView_width_height() {
        val expectedWidth = (40 * activity.resources.displayMetrics.density).toInt()
        val expectedHeight = (40 * activity.resources.displayMetrics.density).toInt()

        assertEquals(expectedWidth, imgLinkedIn.layoutParams.width)
        assertEquals(expectedHeight, imgLinkedIn.layoutParams.height)
    }


    @Test
    fun verifyLinkedinImageView_margin() {

        assertEquals(10, imgLinkedIn.marginTop)
    }


    @Test
    fun verifyLinkedinImageView_source() {

        // Get assigned drawable
        val drawable = imgLinkedIn.drawable

        // Ensure drawable exists
        assertNotNull("ImageView drawable should not be null", drawable)

        // Convert drawable into a resource ID
        val actualResId = shadowOf(drawable).createdFromResId

        // Assert expected drawable resource
        assertEquals(R.drawable.insta, actualResId)
    }


    //Twitter ImageView Tests

    @Test
    fun verifyTwitterImageView_width_height() {
        val expectedWidth = (40 * activity.resources.displayMetrics.density).toInt()
        val expectedHeight = (40 * activity.resources.displayMetrics.density).toInt()

        assertEquals(expectedWidth, imgTwitter.layoutParams.width)
        assertEquals(expectedHeight, imgTwitter.layoutParams.height)
    }


    @Test
    fun verifyTwitterImageView_margin() {

        assertEquals(10, imgTwitter.marginTop)
    }


    @Test
    fun verifyTwitterImageView_source() {

        // Get assigned drawable
        val drawable = imgTwitter.drawable

        // Ensure drawable exists
        assertNotNull("ImageView drawable should not be null", drawable)

        // Convert drawable into a resource ID
        val actualResId = shadowOf(drawable).createdFromResId

        // Assert expected drawable resource
        assertEquals(R.drawable.wa, actualResId)
    }


    //positions of the views

    @Test
    fun verifyPositionsOfViews() {
        val cvProfile: CardView = parent.findViewById(R.id.cvProfile)
        val cvAbout: CardView = parent.findViewById(R.id.cvAbout)
        val cvContact: CardView = parent.findViewById(R.id.cvContact)
        val cvSocial: CardView = parent.findViewById(R.id.cvSocial)

        val imgProfile: ImageView = parent.findViewById(R.id.imgProfile)
        val tvProfileName: TextView = parent.findViewById(R.id.tvProfileName)
        val tvProfileRole: TextView = parent.findViewById(R.id.tvProfileRole)

        val tvAboutTitle: TextView = parent.findViewById(R.id.tvAboutTitle)
        val tvAboutText: TextView = parent.findViewById(R.id.tvAboutText)

        val tvContactTitle: TextView = parent.findViewById(R.id.tvContactTitle)
        val etMessage: EditText = parent.findViewById(R.id.etMessage)
        val btnSubmit: Button = parent.findViewById(R.id.btnSubmit)
        val etEmail: EditText = parent.findViewById(R.id.etEmail)
        val etName: EditText = parent.findViewById(R.id.etName)

        val imgFacebook: ImageView = parent.findViewById(R.id.imgFacebook)
        val imgLinkedIn: ImageView = parent.findViewById(R.id.imgLinkedIn)
        val imgTwitter: ImageView = parent.findViewById(R.id.imgTwitter)

        shadowOf(Looper.getMainLooper()).idle()
        assertTrue(cvAbout.top >= cvProfile.bottom)
        assertTrue(cvContact.top >= cvAbout.bottom)
        assertTrue(cvSocial.top >= cvContact.bottom)

        assertTrue(tvProfileName.top >= imgProfile.bottom)
        assertTrue(tvProfileRole.top >= tvProfileName.bottom)
        assertTrue(tvAboutText.top >= tvAboutTitle.bottom)
        assertTrue(etName.top >= tvContactTitle.bottom)
        assertTrue(etEmail.top >= etName.bottom)
        assertTrue(etMessage.top >= etEmail.bottom)
        assertTrue(btnSubmit.top >= etMessage.bottom)

        // VERIFY LEFT-TO-RIGHT ORDER
        assertTrue(imgFacebook.left < imgLinkedIn.left)
        assertTrue(imgLinkedIn.left < imgTwitter.left)

        // Verify image positions respect margin spacing:
        // Image2 should start after image1.right + margin
        assertEquals(imgFacebook.right + 20, imgLinkedIn.left)

        // Image3 should start after image2.right + margin
        assertEquals(imgLinkedIn.right + 20, imgTwitter.left)

        // Verify vertical alignment
        assertEquals(imgFacebook.top, imgLinkedIn.top)
        assertEquals(imgLinkedIn.top, imgTwitter.top)


    }


}