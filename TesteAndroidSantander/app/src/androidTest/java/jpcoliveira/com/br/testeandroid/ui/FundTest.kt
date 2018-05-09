package jpcoliveira.com.br.testeandroid.ui

import android.app.Instrumentation
import android.content.Intent
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.singleton
import jpcoliveira.com.br.testeandroid.FileReader
import jpcoliveira.com.br.testeandroid.dashboard.MainActivity
import jpcoliveira.com.br.testeandroid.di.BASE_URL
import jpcoliveira.com.br.testeandroid.di.apiModule
import jpcoliveira.com.br.testeandroid.robot.FundRobot
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RunWith(AndroidJUnit4::class)
class FundTest : Instrumentation() {

    //    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)
    var robot: FundRobot? = null

    var server: MockWebServer? = null

    @Before
    fun setUp() {
        server = MockWebServer()
        server?.setDispatcher(dispatcher)
        server?.start()

        BASE_URL = server?.url("/").toString()

        robot = FundRobot(server, null)
    }

    @Test
    fun shouldCheckShare_isVisible() {

        val intent = Intent(activityRule.activity, MainActivity::class.java)

        activityRule.launchActivity(intent)

        robot?.prepare()
                ?.checkButtonShareIsVisible()
    }

    @Test
    fun shouldCheckToobalTitleFund_isOK() {
        robot?.prepare()?.checkTitleFundToolbar()
    }

    @Test
    fun shouldCheckTitleFund_isOK() {
        robot?.prepare()?.checkTitleFund()
    }

    @Test
    fun shouldCheckNameFund_isOK() {
        robot?.prepare()?.checkNameFund()
    }

    @Test
    fun shouldCheckTextWhatIs_isOK() {


        robot?.prepare()?.checkTextWhatIs()
    }

    @Test
    fun shouldCheckTextDefinition_isOK() {
        robot?.prepare()?.checkTextDefinition()
    }

    @Test
    fun shouldCheckRiskTitle_isOK() {
        robot?.prepare()?.checkRiskTitle()
    }

    @Test
    fun shouldCheckHighRisk_isDisplayed() {
        robot?.prepare()?.checkHighRiskIsDisplayed()
    }

    @Test
    fun shouldCheckRecyclerViewFund_isDisplayed() {
        robot?.prepare()?.checkRecyclerViewFundIsDisplayed()
    }

    @Test
    fun shouldCheckInfoTitle_isOK() {
        robot?.prepare()?.checkInfoTitle()
    }

    @Test
    fun shouldCheckInfoFundMonth_isOk() {
        robot?.prepare()?.checkMoreInfoFundMonth()
    }

    @Test
    fun shouldCheckinfoCdiMonth_isOk() {
        robot?.prepare()?.checkMoreInfoCdiMonth()
    }

    @Test
    fun shouldCheckItemtabLayoutInvestiment_isDisplayed() {
        robot?.prepare()?.checkInvestimentTabLayoutIsDisplayed()
    }

    @After
    fun tearDown() {
        robot = null
    }

    val dispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest?): MockResponse {
            if (request?.path?.contains("cells.json")!!) {
                val json = FileReader.readFile("local_cells.json")
                return MockResponse().setResponseCode(200).setBody(json)
            } else if (request.path.contains("fund.json")) {
                val json = FileReader.readFile("local_fund.json")
                return MockResponse().setResponseCode(200).setBody(json)
            } else {
                return MockResponse().setResponseCode(404)
            }
        }
    }
}