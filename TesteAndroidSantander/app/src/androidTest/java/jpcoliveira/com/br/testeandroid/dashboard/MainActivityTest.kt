package jpcoliveira.com.br.testeandroid.dashboard


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import jpcoliveira.com.br.testeandroid.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)
/*

        val textView18 = onView(
                allOf(withId(R.id.toolbar_title), withText("Contato"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                IsInstanceOf.< View > instanceOf (android.view.ViewGroup.class),
                                                0)),
                                1),
                        isDisplayed()))
        textView18.check(matches(withText("Contato")))

        val textView19 = onView(
                allOf(withText("Olá, primeiro se apresente com o seu nome:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.container_contact),
                                        0),
                                0),
                        isDisplayed()))
        textView19.check(matches(withText("Olá, primeiro se apresente com o seu nome:")))

        val editText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.< View > instanceOf (TextInputLayout.class),
                                0),
                        0),
                        isDisplayed()))
        editText.check(matches(isDisplayed()))

        val editText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.< View > instanceOf (TextInputLayout.class),
                                0),
                        0),
                        isDisplayed()))
        editText2.check(matches(isDisplayed()))

        val checkBox = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.container_contact),
                                0),
                        3),
                        isDisplayed()))
        checkBox.check(matches(isDisplayed()))

        val editText3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                IsInstanceOf.< View > instanceOf (TextInputLayout.class),
                                0),
                        0),
                        isDisplayed()))
        editText3.check(matches(isDisplayed()))

        val button = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.container_contact),
                                0),
                        4),
                        isDisplayed()))
        button.check(matches(isDisplayed()))

        val textView20 = onView(
                allOf(withId(R.id.toolbar_title), withText("Contato"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                IsInstanceOf.< View > instanceOf (android.view.ViewGroup.class),
                                                0)),
                                1),
                        isDisplayed()))
        textView20.check(matches(withText("Contato")))

        val textView21 = onView(
                allOf(withId(R.id.toolbar_title), withText("Contato"),
                        childAtPosition(
                                allOf(withId(R.id.toolbar),
                                        childAtPosition(
                                                IsInstanceOf.< View > instanceOf (android.view.ViewGroup.class),
                                                0)),
                                1),
                        isDisplayed()))
        textView21.check(matches(withText("Contato")))

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }*/
}
