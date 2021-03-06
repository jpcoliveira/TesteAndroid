package jpcoliveira.com.br.testeandroid.robot

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton
import jpcoliveira.com.br.testeandroid.util.FileReader
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.asApp
import jpcoliveira.com.br.testeandroid.dashboard.MainActivity
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.hamcrest.Matchers.allOf
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FundRobot(val rule: ActivityTestRule<MainActivity>?) {

    var server: MockWebServer? = null
    val app = InstrumentationRegistry.getInstrumentation().targetContext.asApp()

    fun prepare(): FundRobot {

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

    fun checkButtonShareIsVisible(): FundRobot {
        onView(allOf(withId(R.id.action_share), withContentDescription("compartilhar")))
                .check(matches(isDisplayed()))
        return this
    }

    fun checkTitleFundToolbar(): FundRobot {
        onView(allOf(withId(R.id.toolbar_title)))
                .check(matches(ViewMatchers.withText("Investimento")))
        return this
    }

    fun checkTitleFund(): FundRobot {
        onView(withId(R.id.title)).check(matches(withText("Fundos de investimento")))
        return this
    }

    fun checkNameFund(): FundRobot {
        onView(withId(R.id.fund_name)).check(matches(withText("Vinci Valorem FI Multimercado")))
        return this
    }


    fun checkTextWhatIs(): FundRobot {
        onView(withId(R.id.what_is)).check(matches(withText("O que é?")))
        return this
    }

    fun checkTextDefinition(): FundRobot {
        onView(withId(R.id.definition)).check(matches(
                withText("O Fundo tem por objetivo proporcionar aos seus " +
                        "cotistas rentabilidade no longo prazo através de investimentos.")))
        return this
    }

    fun checkRiskTitle(): FundRobot {
        onView(withId(R.id.risk_title)).check(matches(withText("Grau de risco do investimento")))
        return this
    }

    fun checkHighRiskIsDisplayed(): FundRobot {
        onView(withId(R.id.highRisk)).check(matches(isDisplayed()))
        return this
    }

    fun checkRecyclerViewFundIsDisplayed(): FundRobot {
        onView(withId(R.id.recyclerViewFund)).perform(scrollTo())
        onView(withId(R.id.recyclerViewFund)).check(matches(isDisplayed()))
        return this
    }

    fun checkInfoTitle(): FundRobot {
        onView(withId(R.id.info_title))
                .check(matches(withText("Mais informações sobre o investimento")))
        return this
    }

    fun checkMoreInfoFundMonth(): FundRobot {
        onView(withId(R.id.more_info_fund_month))
                .check(matches(withText("0.3%")))
        return this
    }

    fun checkMoreInfoCdiMonth(): FundRobot {
        onView(withId(R.id.more_info_cdi_month))
                .check(matches(withText("0.3%")))
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
}