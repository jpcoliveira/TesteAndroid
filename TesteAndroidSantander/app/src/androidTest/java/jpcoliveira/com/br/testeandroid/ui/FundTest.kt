package jpcoliveira.com.br.testeandroid.ui

import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import jpcoliveira.com.br.testeandroid.dashboard.MainActivity
import jpcoliveira.com.br.testeandroid.robot.FundRobot
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class FundTest {

    @Rule
    @JvmField
    val activityRule =
            ActivityTestRule(
                    MainActivity::class.java,
                    true,
                    false
            )

    var robot: FundRobot? = null

    @Before
    fun setUp() {
        robot = FundRobot(activityRule)
    }

    @Test
    fun shouldCheckShare_isVisible() {
        robot?.prepare()?.checkButtonShareIsVisible()
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
        robot
                ?.prepare()
                ?.checkRecyclerViewFundIsDisplayed()
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

    @After
    fun tearDown() {
        robot?.tearDown()
        robot = null
    }
}