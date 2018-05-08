package jpcoliveira.com.br.testeandroid.fund

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import jpcoliveira.com.br.testeandroid.R
import jpcoliveira.com.br.testeandroid.base.BaseFragment
import jpcoliveira.com.br.testeandroid.fund.model.Fund
import kotlinx.android.synthetic.main.fragment_fund.*
import kotlinx.android.synthetic.main.include_unavailable_connection.*

class FundFragment : BaseFragment(), FundContract.View {

    private var presenter: FundContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fund, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewFund.layoutManager = LinearLayoutManager(activity)

        btn_invest.setOnClickListener({
            Toast.makeText(activity, activity?.getString(R.string.invest), Toast.LENGTH_SHORT).show()
        })

        btn_retry.setOnClickListener({ presenter?.getFund() })
    }

    override fun setPresenter(presenter: FundContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        presenter?.getFund()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_share -> {
                Toast.makeText(activity, activity?.getString(R.string.share), Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showFund(fund: Fund?) {
        fund?.let {
            container_content.visibility = View.VISIBLE
            internet_unavailable.visibility = View.GONE
            title.text = fund.screen?.title
            fund_name.text = fund.screen?.fundName
            what_is.text = fund.screen?.whatIs
            definition.text = fund.screen?.definition
            more_info.setInfoTitle(fund.screen?.infoTitle)
            more_info.setMoreInfo(fund.screen?.moreInfo)
            risk_title.text = fund.screen?.riskTitle
            risk.selectRisk(fund.screen?.risk!!)
            val infoList = fund.screen.info!! + fund.screen.downInfo!!
            recyclerViewFund.adapter = InfoAdapter(infoList)
        }
    }

    override fun noInternetAvailable() {
        super.noInternetAvailable()
        container_content.visibility = View.GONE
    }
}