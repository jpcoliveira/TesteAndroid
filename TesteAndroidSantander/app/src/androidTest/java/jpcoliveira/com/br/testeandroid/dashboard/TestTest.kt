package jpcoliveira.com.br.testeandroid.dashboard

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withContentDescription
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun teste() {

        Thread.sleep(3000L)

        onView(withContentDescription("compartilhar")).perform(click())

        /*onView(withId(R.id.weatherDescription))
                .check(matches(isAssignableFrom(TextView::class.java)))*/
    }
}