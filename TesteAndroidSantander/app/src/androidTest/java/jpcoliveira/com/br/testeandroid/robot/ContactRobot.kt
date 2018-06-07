package jpcoliveira.com.br.testeandroid.robot

import android.content.Intent
import android.support.design.widget.TextInputLayout
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.view.View
import android.widget.EditText
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.asApp
import jpcoliveira.com.br.testeandroid.dashboard.MainActivity
import jpcoliveira.com.br.testeandroid.util.FileReader
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContactRobot(val rule: ActivityTestRule<MainActivity>?) {

    var server: MockWebServer? = null
    val app = InstrumentationRegistry.getInstrumentation().targetContext.asApp()

    fun prepare(): ContactRobot {

        server = MockWebServer()
        server?.setDispatcher(dispatcher)
        server?.start()
        val url = server?.url("/").toString()

        app.injection()
        app.addModule(
                Kodein.Module(allowSilentOverride = true) {
                    bind<Retrofit.Builder>() with singleton {
                        Retrofit.Builder()
                                .baseUrl(url)
                                .client(OkHttpClient())
                                .addConverterFactory(GsonConverterFactory.create())
                    }
                }
        )

        rule?.launchActivity(Intent())
        return this
    }

    fun clickTabContact(): ContactRobot {
        onView(allOf(isDescendantOfA(withId(R.id.tabLayout)),
                withText("Contato"))).perform(click())
        return this
    }

    fun checkTextPresentation(): ContactRobot {
        onView(withText("Olá, primeiro se apresente com o seu nome:"))
                .check(matches(isDisplayed()))
        return this
    }

    fun checkNameIsVisible(): ContactRobot {
        onView(hasTextInputLayoutHintText("Nome completo"))
                .check(matches(isDisplayed()))
        return this
    }

    fun checkPhoneIsVisible(): ContactRobot {
        onView(hasTextInputLayoutHintText("Telefone"))
                .check(matches(isDisplayed()))
        return this
    }

    fun checkEmailIsVisible(): ContactRobot {
        onView((hasTextInputLayoutHintText("Email")))
                .check(matches(isDisplayed()))
        return this
    }

    fun checkEmailIsHide(): ContactRobot {
        onView(hasTextInputLayoutHintText("Email"))
                .check(matches(not(isDisplayed())))
        return this
    }

    fun clickCheckbox(): ContactRobot {
        onView(withText("Gostaria de cadastrar meu email")).perform(click())
        return this
    }

    fun clickButton(): ContactRobot {
        onView(withText("Enviar")).perform(click())
        return this
    }

    fun checkNameError(): ContactRobot {
        onView(hasTextInputLayoutErrorText(app.getString(R.string.required_field)))
                .check(matches(isDisplayed()))
        return this
    }

    fun checkPhoneError(): ContactRobot {
        onView(hasTextInputLayoutErrorText(app.getString(R.string.invalid_phone)))
                .check(matches(isDisplayed()))
        return this
    }

    fun checkEmailError(): ContactRobot {
        onView(hasTextInputLayoutErrorText(app.getString(R.string.invalid_mail)))
                .check(matches(isDisplayed()))
        return this
    }

    fun typeName(name: String): ContactRobot {
        onView(withTextInputHint("Nome completo"))
                .perform(typeText(name), closeSoftKeyboard())
        return this
    }

    fun typePhone(phone: String): ContactRobot {
        onView(withTextInputHint("Telefone"))
                .perform(typeText(phone), closeSoftKeyboard())
        return this
    }

    fun typeEmail(email: String): ContactRobot {
        onView(withTextInputHint("Email"))
                .perform(typeText(email), closeSoftKeyboard())
        return this
    }

    fun clearAllFields(): ContactRobot {
        onView(withTextInputHint("Nome completo"))
                .perform(clearText())

        onView(withTextInputHint("Telefone"))
                .perform(clearText())

        onView(withTextInputHint("Email"))
                .perform(clearText())

        return this
    }


    fun clearName(): ContactRobot {
        onView(withTextInputHint("Nome completo"))
                .perform(clearText())
        return this
    }

    fun clearPhone(): ContactRobot {

        onView(withTextInputHint("Telefone"))
                .perform(clearText())
        return this
    }

    fun clearEmail(): ContactRobot {
        onView(withTextInputHint("Email"))
                .perform(clearText())
        return this
    }


    fun tearDown() {
        server?.shutdown()
    }

    val dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest?): MockResponse {
            if (request?.path?.contains("cells.json")!!) {
                val json = FileReader.readFile(app, "local_cells_1.json")
                return MockResponse().setResponseCode(200).setBody(json)
            } else if (request.path.contains("fund.json")) {
                val json = FileReader.readFile(app, "local_fund_1.json")
                return MockResponse().setResponseCode(200).setBody(json)
            } else {
                return MockResponse().setResponseCode(404)
            }
        }
    }

    companion object {
        fun hasTextInputLayoutHintText(expectedText: String): TypeSafeMatcher<View> {

            return object : TypeSafeMatcher<View>() {
                override fun matchesSafely(item: View?): Boolean {
                    if (item !is TextInputLayout) {
                        return false
                    }

                    val hint = item.hint

                    hint ?: return false

                    return expectedText == hint
                }

                override fun describeTo(description: Description?) {
                    //not implemented
                }
            }
        }

        fun hasTextInputLayoutErrorText(expectedText: String): TypeSafeMatcher<View> {

            return object : TypeSafeMatcher<View>() {
                override fun matchesSafely(item: View?): Boolean {
                    if (item !is TextInputLayout) {
                        return false
                    }

                    val error = item.error

                    error ?: return false

                    return expectedText == error
                }

                override fun describeTo(description: Description?) {
                    //not implemented
                }
            }
        }

        fun withTextInputLayoutHintMatcher(stringMatcher: Matcher<String>): TypeSafeMatcher<View> {
            return object : TypeSafeMatcher<View>() {
                override fun matchesSafely(textInputEditText: View?): Boolean {
                    if (textInputEditText !is EditText) return false

                    return stringMatcher.matches((textInputEditText.parent.parent as? TextInputLayout)?.hint)
                }

                override fun describeTo(description: Description?) {
                    description?.appendText("with TextInputLayout hint: ")
                    stringMatcher.describeTo(description)
                }
            }
        }

        fun withTextInputHint(hintText: String): Matcher<View> {
            return withTextInputHint(Matchers.`is`(checkNotNull(hintText)))
        }

        fun withTextInputHint(text: Matcher<String>): Matcher<View> {
            return withTextInputLayoutHintMatcher(text)
        }
    }
}