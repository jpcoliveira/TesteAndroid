package jpcoliveira.com.br.testeandroid.ui

import android.support.test.rule.ActivityTestRule
import jpcoliveira.com.br.testeandroid.dashboard.MainActivity
import jpcoliveira.com.br.testeandroid.robot.ContactRobot
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ContactTest {

    @Rule
    @JvmField
    val activityRule =
            ActivityTestRule(
                    MainActivity::class.java,
                    true,
                    false
            )

    var robot: ContactRobot? = null

    @Before
    fun setUp() {
        robot = ContactRobot(activityRule)
    }

    @Test
    fun shouldCheckTextPresentation_isVisible() {
        robot
                ?.prepare()
                ?.clickTabContact()
                ?.checkTextPresentation()
    }

    @Test
    fun shouldCheckName_isVisible() {
        robot
                ?.prepare()
                ?.clickTabContact()
                ?.checkNameIsVisible()
    }

    @Test
    fun shouldCheckPhone_isVisible() {
        robot
                ?.prepare()
                ?.clickTabContact()
                ?.checkPhoneIsVisible()
    }

    @Test
    fun shouldCheckEmail_isVisible() {
        robot
                ?.prepare()
                ?.clickTabContact()
                ?.clickCheckbox()
                ?.checkEmailIsVisible()
    }

    @Test
    fun shouldCheckEmail_isHide() {
        robot
                ?.prepare()
                ?.clickTabContact()
                ?.checkEmailIsHide()
    }

    @Test
    fun shouldCheckValidationFieldName_notValid() {
        robot
                ?.prepare()
                ?.clickTabContact()
                ?.clickButton()
                ?.checkNameError()
    }

    @Test
    fun shouldCheckValidationFieldPhone_notValid() {
        robot
                ?.prepare()
                ?.clickTabContact()
                ?.typeName("Joao Oliveira")
                ?.clickButton()
                ?.checkPhoneError()
                ?.clearName()
                ?.clearPhone()
                ?.typeName("Joao Oliveira")
                ?.typePhone("118888888")
                ?.clickButton()
                ?.checkPhoneError()
    }

    @Test
    fun shouldCheckValidationFieldEmail_notValid() {
        robot
                ?.prepare()
                ?.clickTabContact()
                ?.clickCheckbox()
                ?.typeName("Joao Oliveira")
                ?.typePhone("11999999999")
                ?.clickButton()
                ?.checkEmailError()
    }

    @After
    fun tearDown() {
        robot?.tearDown()
        robot = null
    }
}